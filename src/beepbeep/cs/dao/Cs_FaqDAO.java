package beepbeep.cs.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.util.JdbcUtil;

import beepbeep.cs.model.Cs_FaqDTO;
import beepbeep.cs.model.Cs_NoticeDTO;

public class Cs_FaqDAO {
	private static Cs_FaqDAO dao = null;
	private Cs_FaqDAO() {}
	public static Cs_FaqDAO getInstance() {
		if(dao == null) {
			dao = new Cs_FaqDAO();
		}
		return dao;
	}
	
	public List<Cs_FaqDTO> showList(Connection conn,int firstRow,int endRow) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Cs_FaqDTO>list = null;
		
		try {
			String sql = " select * from(select rownum no,t.* " + 
					" from (select faq_list_seq , TITLE, CONTENTS ,DATES " + 
					" from faq " + 
					" order by FAQ_LIST_SEQ desc)t )b " + 
					" where b.no between ? and ? ";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<Cs_FaqDTO>faqList = new ArrayList<>();
			do {
				faqList.add(makeFaqFromResultSet(rs));
			}while(rs.next());
			return faqList;
			}else {
				return Collections.emptyList();
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	private Cs_FaqDTO makeFaqFromResultSet(ResultSet rs) throws SQLException {
		Cs_FaqDTO dto = new Cs_FaqDTO();
		dto.setFaq_list_seq(rs.getInt("faq_list_seq"));
		dto.setTitle(rs.getString("title"));
		dto.setContents(rs.getString("contents"));
		return dto;
	}
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from faq");
			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	public int delete (Connection conn, int faq_list_seq) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql ="delete from faq where faq_list_seq = ?";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.setInt(1, faq_list_seq);
			return pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}

	}
	public Cs_FaqDTO select(Connection conn,int faq_list_seq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		try {
			String sql = "select * from faq where FAQ_LIST_SEQ = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, faq_list_seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return makeFaqFromResultSet(rs);
			}else {
				return null;
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

		
	public int insert (Connection conn, Cs_FaqDTO dto) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = " insert into faq ( FAQ_LIST_SEQ, TITLE, CONTENTS, DATES) " + 
					" values (FAQ_LIST_SEQ.nextval,?,?,sysdate) ";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContents());
			return pstmt.executeUpdate();
		} 
		finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);

		}
	}
	
	

	
}
