package beepbeep.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.qna.dto.AnswerCommDTO;
import beepbeep.qna.dto.QnaCommentDTO;
import beepbeep.qna.dto.QnaListDTO;

public class QnaDAO {
	private static QnaDAO qnaDAO = new QnaDAO();
	
	public static QnaDAO getInstance() {
		return qnaDAO;
	}
	
	private QnaDAO() {}
	
	public List<QnaListDTO> selectList(Connection conn, int firstRow, int endRow) throws SQLException, NamingException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " select *      " + 
				"				 from (select rownum no, pq_seq, m_sub_name, id, q_title, views, dates, likes        " + 
				"				 from(        " + 
				"				     SELECT pq.pq_seq, m_sub_name, id, q_title,  views, dates ,nvl(likes,0) likes        " + 
				"                            from (select  pq_seq, count(*) likes  from question_likes   group by pq_seq) ql       " + 
				"                                  right JOIN PERSONAL_QUESTION pq ON(ql.pq_seq = pq.pq_seq)    " + 
				"                                  JOIN M_SUB_TYPE mst ON (mst.M_SUB_SEQ=pq.M_SUB_SEQ)        " + 
				"                                  order by dates desc      " + 
				"				     )t        " + 
				"				 )a    " + 
				"where a.no between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<QnaListDTO> qnaList = new ArrayList<QnaListDTO>();
				do {
					QnaListDTO dto = new QnaListDTO();
					dto.setPq_seq(rs.getInt("pq_seq"));
					dto.setM_sub_name(rs.getString("m_sub_name"));
					dto.setId(rs.getString("id"));
					dto.setQ_title(rs.getString("q_title"));
					dto.setViews(rs.getInt("views"));
					dto.setDates(rs.getString("dates"));
					dto.setLikes(rs.getInt("likes"));
					dto.setAnswerCnt(countComm(dto.getPq_seq()));
					
					qnaList.add(dto);
				} while(rs.next());
				return qnaList;
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

	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from PERSONAL_QUESTION"); 
			rs.next();
			return rs.getInt(1);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	public QnaListDTO selectContent(Connection conn, int pq_seq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT PQ_SEQ,MEMBERT.ID,  M_SUB_TYPE.M_SUB_SEQ, M_SUB_NAME, NICKNAME, Q_TITLE, (SELECT COUNT(*) FROM PERSONAL_QUESTION P JOIN QUESTION_LIKES Q ON(P.PQ_SEQ=Q.PQ_SEQ) WHERE Q.PQ_SEQ=? ) LIKES, VIEWS, DATES, PHOTO, CONTENTS  " + 
				"  FROM M_SUB_TYPE JOIN PERSONAL_QUESTION ON (M_SUB_TYPE.M_SUB_SEQ=PERSONAL_QUESTION.M_SUB_SEQ) JOIN MEMBERT ON(PERSONAL_QUESTION.ID=MEMBERT.ID) " + 
				"  WHERE pq_seq=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pq_seq);
			pstmt.setInt(2, pq_seq);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				QnaListDTO dto = new QnaListDTO();
				dto.setPq_seq(rs.getInt("pq_seq"));
				dto.setId(rs.getString("id"));
				dto.setM_sub_seq(rs.getInt("m_sub_seq"));
				dto.setM_sub_name(rs.getString("m_sub_name"));
				dto.setNickname(rs.getString("nickname"));
				dto.setQ_title(rs.getString("q_title"));
				dto.setLikes(rs.getInt("likes"));
				dto.setViews(rs.getInt("views"));
				dto.setDates(rs.getString("dates"));
				dto.setContents(rs.getString("contents"));
				dto.setImageurl(rs.getString("photo"));
				return dto;
			}else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<QnaCommentDTO> selectComment(Connection conn, int pq_seq, String sid) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT m.nickname,photo,g.grade_name, a.* " + 
				"FROM PERSONAL_QUESTION q JOIN PERSONAL_ANSWER a ON( q.PQ_SEQ= a.PQ_SEQ) " + 
				"                         JOIN MEMBERT m ON(m.id=a.id) " + 
				"                         JOIN GRADE g ON(m.mgrade_code=g.mgrade_code) " + 
				"WHERE a.pq_seq=?"
				+ " ORDER BY a.dates desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pq_seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<QnaCommentDTO> commentList = new ArrayList<QnaCommentDTO>();
				do {
					QnaCommentDTO dto = new QnaCommentDTO();
					dto.setPs_seq(rs.getInt("ps_seq"));
					dto.setId(rs.getString("id"));
					dto.setDates(rs.getString("dates"));
					dto.setContents(rs.getString("contents"));
					dto.setNickname(rs.getString("nickname"));
					dto.setMgrade_name(rs.getString("grade_name"));
					dto.setImageurl(rs.getString("photo"));
					int likes = likeCount(conn, dto.getPs_seq());
					dto.setLikes(likes);
					int id_alikes = answerLike(conn, dto.getPs_seq(), sid);
					dto.setLikes_yn(id_alikes);
					List<AnswerCommDTO> cocoList = cocomentSelect(conn, dto.getPs_seq());
					dto.setCocoList(cocoList);
					
					commentList.add(dto);
				}while(rs.next());
				return commentList;
			}else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private List<AnswerCommDTO> cocomentSelect(Connection conn, int ps_seq) throws SQLException {
		String sql = "select psa_seq, ps_seq, pac.id, dates, contents, nickname " + 
				"from personal_answer_comment pac join membert m  on(pac.id = m.id) " + 
				"where pac.ps_seq=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AnswerCommDTO> list = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ps_seq);
			rs = pstmt.executeQuery();
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
			return list;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 내가 좋아요를 했는지 여부 1또는 0
	private int answerLike(Connection conn, int ps_seq, String sid) throws SQLException {
		String sql = "select count(*) from answer_likes where ps_seq=? and id=? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ps_seq);
			pstmt.setString(2, sid);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

	}
// 좋아요 수 세기
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

	public int countReaded(Connection conn, int pq_seq) throws SQLException {
		String sql = "update personal_question  "
					+ "set views = views+1   "
					+ "where pq_seq = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pq_seq);
			result = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}

		return result;
	}

	public int qnaInsert(Connection conn, String id, int select, String title, String contents) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into personal_question  "
				+ "values(qna_seq.nextval, ?, ?, ?, 0, sysdate, ?) ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, select);
			pstmt.setString(2, id);
			pstmt.setString(3, title);
			pstmt.setString(4, contents);
			
			result = pstmt.executeUpdate();
			return result;
		}finally {
			JdbcUtil.close(pstmt);
		}
		
	}

	public int questionDelete(Connection conn, int pq_seq) throws SQLException {
		String sql = "delete from personal_question where pq_seq = ? ";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pq_seq);
			
			int result = pstmt.executeUpdate();
			return result;
		}finally {
			JdbcUtil.close(pstmt);
		}
	}

	public int updateQuestion(Connection conn, int pq_seq, String title, String contents) throws SQLException {
		String sql = "update personal_question set q_title=?, contents=? where pq_seq = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setInt(3, pq_seq);
			
			int result = pstmt.executeUpdate();
			return result;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public int questionlike(Connection conn, int pq_seq, String id) throws SQLException {
		String sql = " SELECT  COUNT(*) FROM QUESTION_LIKES WHERE ID=? AND PQ_SEQ=? ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, pq_seq);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
}
