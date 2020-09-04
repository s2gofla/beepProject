package beepbeep.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.util.JdbcUtil;

import beepbeep.cs.model.Cs_AskDTO;
import beepbeep.cs.model.Cs_FaqDTO;
import beepbeep.cs.model.Cs_NoticeDTO;

public class Cs_AskDAO {
	private static Cs_AskDAO dao = null;
	private Cs_AskDAO() {}
	public static Cs_AskDAO getInstance() {
		if(dao == null) {
			dao = new Cs_AskDAO();
		}
		return dao;
	}
	public List<Cs_AskDTO> showList(Connection conn, int firstRow,int endRow) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Cs_AskDTO>list = null;


		try {
			String sql = " select * from( select rownum no, t.* " + 
					" from (select qna_seq,id,title,attach_file,condition,dates,q.q_name, " + 
					" case when ( sysdate - dates)  between 0 and 4/24  then 'true'  else 'false' end newmark " + 
					" from qna a join qna_type q on a.qtype_seq = q.qtype_seq " + 
					" order by qna_seq desc)t )b  " + 
					" where b.no between ? and ?";
			System.out.println(sql);	
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<Cs_AskDTO>askList = new ArrayList<>();
				do {
					askList.add(makeAskFromResultSet(rs));
				}while(rs.next());
				return askList;
			}else {
				return Collections.emptyList();
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}


	private Cs_AskDTO makeAskFromResultSet(ResultSet rs) throws SQLException {
		Cs_AskDTO dto = new Cs_AskDTO();
		dto.setQna_seq(rs.getInt("qna_seq"));
		dto.setId(rs.getString("id"));
		dto.setTitle(rs.getString("title"));
		dto.setAttach_file(rs.getString("attach_file"));
		dto.setCondition(rs.getString("condition"));
		dto.setDates(rs.getDate("dates"));
		dto.setQ_name(rs.getString("q_name"));
		return dto;
	}
	public Cs_AskDTO selectTitle(Connection conn, String content) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from qna where content = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return makeAskFromResultSet(rs);
			}else {
				return null;
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	} 
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from qna";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getInt(1);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		} 
	}	

	public int insert (Connection conn, Cs_AskDTO dto) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = " insert into qna ( QNA_SEQ, QTYPE_SEQ, ID, TITLE,ATTACH_FILE,CONTENTS) " + 
					"values (qna_seq.nextval,?,?,?,?,?)";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getQtype_seq());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getAttach_file());
			pstmt.setString(5, dto.getContents());

			return pstmt.executeUpdate();
		} 
		finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);

		}
	}
	public int delete (Connection conn, int qna_seq) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql ="delete from qna where qna_seq = ?";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.setInt(1, qna_seq);
			return pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}

	}
	public Cs_AskDTO select(Connection conn,int qna_seq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Cs_AskDTO dto = null;

		try {
			String sql = "select qna_seq,title,id,dates,contents from qna where qna_seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new Cs_AskDTO();
				dto.setQna_seq(rs.getInt("qna_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setId(rs.getString("id"));
				dto.setDates(rs.getDate("dates"));
				dto.setContents(rs.getString("contents"));
				return dto;
			}else {
				return null;
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public int insertReply(Connection conn, Cs_AskDTO dto) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		if(dto.getFirst() == 0) {
			String sql = " insert into qna_reply " + 
					"(qna_reply_seq,id,contents) " + 
					"values ( qna_reply_seq.nextval,?,?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getId());
				pstmt.setString(2, dto.getContents());
				return pstmt.executeUpdate();
			} finally {
				JdbcUtil.close(pstmt);
				JdbcUtil.close(conn);
			}
			
		}
		return result;
	}
	public List<Cs_AskDTO> showRelpy(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Cs_AskDTO> replyList = new ArrayList<>();
	
		try {
			String sql = "select id , contents , date from qna_reply";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Cs_AskDTO dto = null;
			while(rs.next()) {
				dto = new Cs_AskDTO();
				dto.setId(rs.getString("id"));
				dto.setContents(rs.getString("coments"));
				dto.setDates(rs.getDate("dates"));
				replyList.add(dto);
			}
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return replyList;
		
	}

}
