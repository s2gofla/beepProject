package beepbeep.cs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.util.JdbcUtil;

import beepbeep.cs.model.Cs_NoticeDTO;

public class Cs_NoticeDAO {
	private static Cs_NoticeDAO dao = null;
	private Cs_NoticeDAO() {}
	public static Cs_NoticeDAO getInstance() {
		if(dao == null) {
			dao = new Cs_NoticeDAO();
		}
		return dao;
	}




	private Cs_NoticeDTO makeNoticeFromResultSet(ResultSet rs) throws SQLException {
		Cs_NoticeDTO dto = new Cs_NoticeDTO();
		dto.setNotice_seq(rs.getInt("notice_seq"));
		dto.setId(rs.getString("id"));
		dto.setTitle(rs.getString("title"));
		dto.setDates(rs.getDate("dates"));
		dto.setViews(rs.getInt("views"));
		dto.setContents(rs.getString("contents"));

		return dto;
	}

	public List<Cs_NoticeDTO> showList(Connection conn,int firstRow,int endRow) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql=" select * " + 
					" from( select rownum no, t.* " + 
					" from (select notice_seq,id,title,dates,views,contents, " + 
					" case when ( sysdate - dates)  between 0 and 4/24  then 'true'  else 'false' end newmark " + 
					" from notice " + 
					" order by notice_seq desc)t )b " + 
					" where b.no between ? and ? ";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<Cs_NoticeDTO> noticeList = new  ArrayList<>();
				do {
					noticeList.add(makeNoticeFromResultSet(rs));
				}while(rs.next());
				return noticeList;
			}else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public Cs_NoticeDTO selectTitle(Connection conn, String content) throws SQLException {
		PreparedStatement pstmt =null;
		ResultSet rs = null;

		try {
			String sql = "select * from notice where contents = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return makeNoticeFromResultSet(rs);
			}else {
				return null;
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public Cs_NoticeDTO selectOne(Connection conn,int notice_seq) throws SQLException {
		String sql = " select title,id,dates,views,contents from notice where notice_seq = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Cs_NoticeDTO dto = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new Cs_NoticeDTO();
				dto.setTitle(rs.getString("title"));
				dto.setId(rs.getString("id"));
				dto.setDates(rs.getDate("dates"));
				dto.setViews(rs.getInt("views"));
				dto.setContents(rs.getString("contents"));
				return dto;
			}
		}
		finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
		
	}


	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from notice");
			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	
	public int delete (Connection conn, int notice_seq) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql ="delete from notice where notice_seq = ?";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.setInt(1, notice_seq);
			return pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}

	}
	public Cs_NoticeDTO select(Connection conn,int notice_seq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		try {
			String sql = "select * from notice where notice_seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return makeNoticeFromResultSet(rs);
			}else {
				return null;
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int insert (Connection conn, Cs_NoticeDTO dto) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = " insert into notice ( NOTICE_SEQ, ID, TITLE, DATES, VIEWS, CONTENTS) " + 
					" values (notice_list_seq.nextval,?,?,sysdate,?,?) ";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getTitle());
			
			pstmt.setInt(3, dto.getViews());
			pstmt.setString(4, dto.getContents());
			return pstmt.executeUpdate();
		} 
		finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);

		}
	}
	public int updateReadcount(Connection conn, int notice_seq) throws SQLException {
		String sql = "update notice "
				+" set views = views +1 "
				+" where notice_seq = ?";

		PreparedStatement pstmt = null;		
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_seq);
			result = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return result;
	}
	
	
}


