package beepbeep.admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.util.JdbcUtil;

import beepbeep.admin.model.Admin_ConfirmDTO;
import beepbeep.cs.model.Cs_NoticeDTO;

public class Admin_ConfirmDAO {
	private static Admin_ConfirmDAO dao = null;
	private Admin_ConfirmDAO() {
		
	}
	public static Admin_ConfirmDAO getInstance() {
		if(dao == null) {
			dao = new Admin_ConfirmDAO();
		}
		return dao;
	}
	public List<Admin_ConfirmDTO> showD_List(Connection conn,int firstRow,int endRow) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<Admin_ConfirmDTO> list = null;
		try {
			String sql = " select * from( select rownum no, t.* " + 
					" from (select c_seq,id,license_number,license_pic, certification " + 
					" from d_certification " + 
					" order by c_seq desc)t )b " + 
					" where b.no between ? and ? ";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<Admin_ConfirmDTO> admin_confirm_list = new ArrayList<>();
				do {
					admin_confirm_list.add(makeAdminconfrimResultSet(rs));
					
				}while(rs.next());
				return admin_confirm_list;
			}else {
				return null;
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	public List<Admin_ConfirmDTO> showH_List(Connection conn,int firstRow,int endRow) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<Admin_ConfirmDTO> list = null;
		try {
			String sql = " select * from( select rownum no, t.* " + 
					" from (select SEQ,c.H_REVIEW_CODE,CERTIFICATION_ATTACH,APPROVAL, h.id,h.h_code, h_name " + 
					" from H_REVIEW_CERTIFICATION  c join h_review h on c.h_review_code = h.h_review_code " + 
					" join h_info i on h.h_code = i.h_code " + 
					" order by seq desc)t )b " + 
					" where approval = 0 and b.no between ? and ?";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<Admin_ConfirmDTO> admin_Hconfirm_list = new ArrayList<>();
				do {
					admin_Hconfirm_list.add(makeAdminHconfrimResultSet(rs));
					
				}while(rs.next());
				return admin_Hconfirm_list;
			}else {
				return null;
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	private Admin_ConfirmDTO makeAdminHconfrimResultSet(ResultSet rs) throws SQLException {
		Admin_ConfirmDTO dto = new Admin_ConfirmDTO();
		dto.setSeq(rs.getInt("seq"));
		dto.setH_name(rs.getString("h_name"));
		dto.setId(rs.getString("id"));
		dto.setCertification_attach(rs.getString("certification_attach"));
		dto.setApproval(rs.getInt("approval"));	   
		return dto;
	}
	private Admin_ConfirmDTO makeAdminconfrimResultSet(ResultSet rs) throws SQLException {
		Admin_ConfirmDTO dto = new Admin_ConfirmDTO();
		dto.setC_seq(rs.getInt("c_seq"));
		dto.setId(rs.getString("id"));
		dto.setLicense_number(rs.getInt("license_number"));
		dto.setLicense_pic(rs.getString("license_pic"));
		dto.setCertification(rs.getInt("certification"));
		
		
		return dto;
	}
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from d_certification");
			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	public int selectH_Count(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from H_REVIEW_CERTIFICATION");
			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	public int delete (Connection conn, int seq) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql ="delete from H_REVIEW_CERTIFICATION where seq = ?";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.setInt(1, seq);
			return pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public int ddelete (Connection conn, int c_seq) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql ="delete from d_certification where c_seq = ?";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.setInt(1, c_seq);
			return pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public int d_update(Connection conn, int c_seq) throws SQLException {
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update d_certification set certification = 1 where c_seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_seq);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public int h_update(Connection conn, int seq) throws SQLException {
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update H_REVIEW_CERTIFICATION set approval = 1 where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public Admin_ConfirmDTO h_select(Connection conn,int seq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Admin_ConfirmDTO hdto = null;

		try {
			String sql = "select * from H_REVIEW_CERTIFICATION where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				hdto = new Admin_ConfirmDTO();
				
				hdto.setId(rs.getString("id"));
				hdto.setDates(rs.getDate("dates"));
				hdto.setContents(rs.getString("contents"));
				return hdto;
			}else {
				return null;
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	public Admin_ConfirmDTO d_select(Connection conn,int c_seq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {
			String sql = "select * from d_certification where c_seq = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return makeAdminconfrimResultSet(rs);
			}else {
				return null;
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
}
