package beepbeep.admin.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

import com.util.JdbcUtil;

import beepbeep.admin.model.Admin_ConfirmDTO;
import beepbeep.admin.model.Admin_ReportDTO;

public class Admin_ReportDAO {
	private static Admin_ReportDAO dao = null;
	private Admin_ReportDAO() {
		
	}
	public static Admin_ReportDAO getInstance() {
		if(dao == null) {
			dao = new Admin_ReportDAO();
		}
		return dao;
	}
	
	public List<Admin_ReportDTO> showList(Connection conn, int firstRow, int endRow) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Admin_ReportDTO> list = null;
		
	
		
		try {
			String sql = " select * from( select rownum no, t.* " + 
					" from (select report_seq,t.report_type,r.contents,r.id,r.dates,r.condition,t.report_name " + 
					" from report r join report_type t on r.report_type = t.report_type  " + 
					" order by report_seq desc)t )b " + 
					" where condition = 0 and b.no between ? and ? ";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<Admin_ReportDTO> reportList = new ArrayList<>();
				do {
					reportList.add(makeReportFromResultSet(rs));
				}while(rs.next());
				return reportList;
			}else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<Admin_ReportDTO> showReplyList(Connection conn, int firstRow, int endRow) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Admin_ReportDTO> list = null;
		
	
		
		try {
			String sql = " select * from( select rownum no, t.* " + 
					" from (select r.report_seq,r.contents,r.id,dates,r.condition,t.report_name,a.name " + 
					" from report_comment r join report_type t on r.report_type = t.report_type " + 
					" join all_board a on r.all_board_seq = a.all_board_seq " + 
					" order by report_seq desc)t )b " + 
					" where condition = 0 and b.no between ? and ? ";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<Admin_ReportDTO> reportReplyList = new ArrayList<>();
				do {
					reportReplyList.add(makeReportReplyFromResultSet(rs));
				}while(rs.next());
				return reportReplyList;
			}else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	private Admin_ReportDTO makeReportFromResultSet(ResultSet rs) throws SQLException {
		Admin_ReportDTO dto = new Admin_ReportDTO();
		dto.setReport_seq(rs.getInt("report_seq"));
		dto.setReport_type(rs.getInt("report_type"));
		dto.setReport_name(rs.getString("report_name"));
		dto.setContents(rs.getString("contents"));
		dto.setId(rs.getString("id"));
		dto.setCondition(rs.getInt("condition"));
		dto.setDates(rs.getDate("dates"));
		return dto;
	}
	
	private Admin_ReportDTO makeReportReplyFromResultSet(ResultSet rs) throws SQLException {
		Admin_ReportDTO replydto = new Admin_ReportDTO();
		replydto.setReport_seq(rs.getInt("report_seq"));	
		replydto.setReport_name(rs.getString("report_name"));
		replydto.setContents(rs.getString("contents"));
		replydto.setId(rs.getString("id"));
		replydto.setCondition(rs.getInt("condition"));
		replydto.setDates(rs.getDate("dates"));
		replydto.setName(rs.getString("name"));
		return replydto;
	}
	
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from report");
			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	public int selectReplyCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from report_comment");
			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	public int delete (Connection conn, int report_seq) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql ="delete from notice where report_seq = ?";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.setInt(1, report_seq);
			return pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	//수정해야
	public int confirm (Connection conn,Admin_ReportDTO dto) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = " insert into notice ( admin_confirm_seq, ID, TITLE, DATES) " + 
					" values (notice_list_seq.nextval,?,?,sysdate) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			
	
			return pstmt.executeUpdate();
		} 
		finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);

		}
	}
	public Admin_ReportDTO report_select(Connection conn,int report_seq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Admin_ReportDTO report_dto = null;
		try {
			String sql = " select r.report_seq,r.id,r.contents,r.dates,t.report_name " + 
					" from report r join report_type t on r.report_type = t.report_type " + 
					" where report_seq = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, report_seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				report_dto = new Admin_ReportDTO();
				report_dto.setReport_name(rs.getString("report_name"));
				report_dto.setId(rs.getString("id"));
				report_dto.setDates(rs.getDate("dates"));
				report_dto.setContents(rs.getString("contents"));
				return report_dto;
			}else {
				return null;
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	public Admin_ReportDTO reportReply_select(Connection conn,int report_seq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Admin_ReportDTO reportReply_dto = null;
		try {
			
			String sql =" select c.report_seq,c.id,c.contents,c.dates,t.report_name,a.name " + 
					" from report_comment c join report_type t on c.report_type = t.report_type " + 
					" join all_board a on c.all_board_seq = a.all_board_seq " + 
					"where report_seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, report_seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reportReply_dto = new Admin_ReportDTO();
				reportReply_dto.setReport_name(rs.getString("report_name"));
				reportReply_dto.setId(rs.getString("id"));
				reportReply_dto.setDates(rs.getDate("dates"));
				reportReply_dto.setContents(rs.getString("contents"));
				reportReply_dto.setName(rs.getString("name"));
				return reportReply_dto;
			}else {
				return null;
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
}
