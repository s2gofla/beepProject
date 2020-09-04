package beepbeep.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.qna.dto.AnswerCommDTO;
import beepbeep.qna.dto.QnaCommentDTO;
import beepbeep.qna.dto.QnaListDTO;

public class QnaAjaxDAO {
	private static QnaAjaxDAO instance = new QnaAjaxDAO();
	
	public static QnaAjaxDAO getInstance() {
		return instance;
	}
	
	private QnaAjaxDAO() {}
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	public int insertQComment(String id, String contents, int pq_seq) throws NamingException, SQLException {
    	conn = ConnectionProvider.getConnection();
    	
    	pstmt = conn.prepareStatement("INSERT INTO personal_answer values(answer_psseq.nextval,?,?,sysdate,?) ");
    	pstmt.setString(1, id);
    	pstmt.setInt(2, pq_seq);
    	pstmt.setString(3, contents);
    	int result = pstmt.executeUpdate();    	
    	
    	JdbcUtil.close(pstmt);
		JdbcUtil.close(conn);
    	return result;
    }

	public List<QnaCommentDTO> icSelect(int ps_seq) throws SQLException, NamingException {
		conn = ConnectionProvider.getConnection();
		String sql = "select *" + 
				"      from personal_answer a join membert m on(a.id = m.id)   " + 
				"           join grade on(m.mgrade_code = grade.mgrade_code)   " + 
				"      where pq_seq = ?   "
				+ "	   order by dates desc" ;
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, ps_seq);
		rs = pstmt.executeQuery();
		List<QnaCommentDTO> list = null;
		if(rs.next()) {
			 list = new ArrayList<QnaCommentDTO>();
			do {
				QnaCommentDTO dto = new QnaCommentDTO();
				dto.setPs_seq(rs.getInt("ps_seq"));
				dto.setId(rs.getString("id"));
				int likes = likeCount(conn, dto.getPs_seq());
				dto.setLikes(likes);
				dto.setDates(rs.getString("dates"));
				dto.setContents(rs.getString("contents"));
				dto.setNickname(rs.getString("nickname"));
				dto.setMgrade_name(rs.getString("grade_name"));
				dto.setImageurl(rs.getString("photo"));
				list.add(dto);
			}while(rs.next());
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(conn);
		
		return list;
	}
	private int likeCount(Connection conn, int ps_seq) throws SQLException {
		String sql = "select count(*) from answer_likes where ps_seq = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ps_seq);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1); 
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	public int alikeBtn(String yn, int ps_seq, String sid) throws NamingException, SQLException {
		conn = ConnectionProvider.getConnection();
		String sql = "";
		if(yn.equals("y")) {
			sql = "insert into answer_likes values (answer_likes_seq.nextval,?,?)";
		}else {
			sql = "delete from answer_likes where ps_seq= ? and id = ? ";
		}
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, ps_seq);
		pstmt.setString(2, sid);
		int result = pstmt.executeUpdate();
		
		JdbcUtil.close(pstmt);
		JdbcUtil.close(conn);
		
		return result;
	}
	
	public int selectALike(int ps_seq) throws NamingException, SQLException {
		conn = ConnectionProvider.getConnection();
		String sql = "select count(*) from answer_likes where ps_seq = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, ps_seq);
		rs = pstmt.executeQuery();
		int result = 0;
		if(rs.next()) {
			result = rs.getInt(1);
		}
		
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		
		return result;
	}

	public List<QnaListDTO> choiceList(int firstRow, int endRow, int m_sub_seq, int sort, String sWord) throws NamingException, SQLException {
		conn = ConnectionProvider.getConnection();
		String sql= "select *   " + 
				"	from (select rownum no, t.*  " + 
				"	from (select  rownum no1, pq.pq_seq, mst.m_sub_seq, m_sub_name , pq.id, q_title,  views, dates, nvl(likes,0) likes "+ 
				"	    from (select  pq_seq, count(*) likes  from question_likes   group by pq_seq) ql  " + 
				"	                                          right JOIN PERSONAL_QUESTION pq ON(ql.pq_seq = pq.pq_seq)  " + 
				"	                                          JOIN M_SUB_TYPE mst ON(mst.m_sub_seq=pq.m_sub_seq)    ";
		if(m_sub_seq != 0) sql += "         WHERE mst.m_sub_seq = ?  ";
		
		if(!sWord.equals("*") && m_sub_seq!=0)      sql+=" and regexp_like(q_title, ?) or regexp_like(contents, ?)";  
		else if(!sWord.equals("*") && m_sub_seq==0) sql+= "where regexp_like(q_title, ?) or regexp_like(contents, ?)";
		
		if(sort == 1)	   sql += "     order by dates desc  ";
		else if(sort == 2) sql += "     order by likes desc  ";
		else if(sort == 3) sql += "     order by views desc  ";
		else if(sort == 4) sql += "     order by dates asc   ";
		
		sql +=		"        )t " + 
				"    )a  " + 
				"where no between ? and ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			if(m_sub_seq!=0 && !sWord.equals("*")) {
				pstmt.setInt(1, m_sub_seq);
				pstmt.setString(2, sWord);
				pstmt.setString(3, sWord);
				pstmt.setInt(4, firstRow);
				pstmt.setInt(5, endRow);
			}else if(m_sub_seq==0 && !sWord.equals("*")) {
				pstmt.setString(1, sWord);
				pstmt.setString(2, sWord);
				pstmt.setInt(3, firstRow);
				pstmt.setInt(4, endRow);
			}else if(m_sub_seq!=0 && sWord.equals("*")) {
				pstmt.setInt(1, m_sub_seq);
				pstmt.setInt(2, firstRow);
				pstmt.setInt(3, endRow);
			}else if(m_sub_seq==0 && sWord.equals("*")) {
				pstmt.setInt(1, firstRow);
				pstmt.setInt(2, endRow);
			}
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<QnaListDTO> list = new ArrayList<QnaListDTO>();
				do {
					QnaListDTO dto = new QnaListDTO();
					dto.setPq_seq(rs.getInt("pq_seq"));
					dto.setM_sub_seq(rs.getInt("m_sub_seq"));
					dto.setId(rs.getString("id"));
					dto.setQ_title(rs.getString("q_title"));
					dto.setLikes(rs.getInt("likes"));
					dto.setViews(rs.getInt("views"));
					dto.setDates(rs.getString("dates"));
					dto.setAnswerCnt(countComm(dto.getPq_seq()));
					list.add(dto);
				}while(rs.next());
				return list;
			}else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);			
		}
		
	}
	private int countComm(int pq_seq) throws NamingException, SQLException {
		Connection conn = ConnectionProvider.getConnection();
		String sql = "select count(*) from personal_question q join personal_answer a on (q.pq_seq =a.pq_seq) where a.pq_seq = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pq_seq);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		
	}


	public int selectCount(int m_sub_seq, String sWord) throws SQLException, NamingException {
		conn = ConnectionProvider.getConnection();
		String sql;
		if(m_sub_seq==0)  sql =  "select count(*) from PERSONAL_QUESTION ";
		else              sql =  "select count(*) from PERSONAL_QUESTION where m_sub_seq = ?";
		
		if(!sWord.equals("*") && m_sub_seq!=0)      sql+=" and regexp_like(q_title, ?) or regexp_like(contents, ?)";  
		else if(!sWord.equals("*") && m_sub_seq==0) sql+= "where regexp_like(q_title, ?) or regexp_like(contents, ?)";
		try {
			int result = 0;
			pstmt = conn.prepareStatement(sql);
			if(m_sub_seq!=0 && !sWord.equals("*")) {
				pstmt.setInt(1, m_sub_seq);
				pstmt.setString(2, sWord);
				pstmt.setString(3, sWord);
			}else if(m_sub_seq==0 && !sWord.equals("*")) {
				pstmt.setString(1, sWord);
				pstmt.setString(2, sWord);
			}else if(m_sub_seq!=0 && sWord.equals("*")) {
				pstmt.setInt(1, m_sub_seq);
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
						
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public String selectMname(int m_sub_seq) throws NamingException, SQLException {
		conn = ConnectionProvider.getConnection();
		String sql = "select m_sub_name from m_sub_type where m_sub_seq = ? ";
		try{
			String result = "";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_sub_seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString(1);
			}
			return result;
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}

	public int qlikeBtn(String yn, int pq_seq, String sid) throws SQLException, NamingException {
		conn = ConnectionProvider.getConnection();
		String sql = "";
		if(yn.equals("y")) {
			sql = "insert into question_likes values (question_like_seq.nextval,?,?)";
		}else {
			sql = "delete from question_likes where pq_seq= ? and id = ? ";
		}
		System.out.println(sql);
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pq_seq);
		pstmt.setString(2, sid);
		int result = pstmt.executeUpdate();
		
		JdbcUtil.close(pstmt);
		JdbcUtil.close(conn);
		return result;
		
	}
	public int selectQLike(int pq_seq) throws NamingException, SQLException {
		conn = ConnectionProvider.getConnection();
		String sql = "select count(*) from question_likes where pq_seq = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pq_seq);
		rs = pstmt.executeQuery();
		int result = 0;
		if(rs.next()) {
			result = rs.getInt(1);
		}
		
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		
		return result;
	}


	public List<AnswerCommDTO> icASelect(int ps_seq) throws NamingException, SQLException {
		conn = ConnectionProvider.getConnection();
		String sql = "select  psa_seq, ps_seq, pac.id, dates, contents, nickname " + 
				"from personal_answer_comment pac join membert m  on(pac.id = m.id)  " + 
				"where pac.ps_seq=? " ;
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, ps_seq);
		rs = pstmt.executeQuery();
		List<AnswerCommDTO> list = null;
		if(rs.next()) {
			 list = new ArrayList<AnswerCommDTO>();
			do {
				AnswerCommDTO dto = new AnswerCommDTO();
				dto.setPsa_seq(rs.getInt("psa_seq"));
				dto.setPs_seq(rs.getInt("ps_seq"));
				dto.setId(rs.getString("id"));
				dto.setDates(rs.getString("dates"));
				dto.setContents(rs.getString("contents"));
				dto.setNickname(rs.getString("nickname"));
				list.add(dto);
			}while(rs.next());
		}

		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(conn);
		
		return list;
	}

	public int insertAComment(String id, String contents, int ps_seq) throws NamingException, SQLException {
		conn = ConnectionProvider.getConnection();
    	
    	pstmt = conn.prepareStatement("INSERT INTO  personal_answer_comment values(ans_comm_seq.nextval,?,?,sysdate,?) ");
    	pstmt.setInt(1, ps_seq);
    	pstmt.setString(2, id);
    	pstmt.setString(3, contents);
    	int result = pstmt.executeUpdate();    	
    	
    	JdbcUtil.close(pstmt);
		JdbcUtil.close(conn);
    	return result;

	}

	public int updateAnswer(String ps_seq, String contents) throws NamingException, SQLException {
		conn = ConnectionProvider.getConnection();
		String sql = "update personal_answer set contents = ? where ps_seq = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, contents);
		pstmt.setString(2, ps_seq);
		int result =  pstmt.executeUpdate();
		
		JdbcUtil.close(pstmt);
		JdbcUtil.close(conn);
		
		return result;
	}

	public int answerDelete(int ps_seq) throws NamingException, SQLException {
		conn = ConnectionProvider.getConnection();
		String sql = "DELETE FROM PERSONAL_ANSWER WHERE PS_SEQ=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, ps_seq);
		int result = pstmt.executeUpdate();
		
		JdbcUtil.close(pstmt);
		JdbcUtil.close(conn);
		return result;
	}

}
