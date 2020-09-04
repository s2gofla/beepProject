package beepbeep.information.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JdbcUtil;

import beepbeep.information.dto.PriceContentDTO;

public class InfoAjaxDAO {
	
	private static final InfoAjaxDAO instance = new InfoAjaxDAO();
	
	public static InfoAjaxDAO getInstance() {
		return instance;
	}
	
	private InfoAjaxDAO() {}

	PreparedStatement pstmt;
	ResultSet rs;
	
	public PriceContentDTO selectPrice(Connection conn, int pinfo_code) throws SQLException {
		String sql = "select min_price, avg_price, max_price, pinfo_treatment from price_info where pinfo_code = ?";
		PriceContentDTO dto = new PriceContentDTO();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pinfo_code);
		
		try {
			rs = pstmt.executeQuery();
			rs.next();
			dto.setMin_price(rs.getInt("min_price"));
			dto.setAvg_price(rs.getInt("avg_price"));
			dto.setMax_price(rs.getInt("max_price"));
			dto.setPinfo_treatment(rs.getString("pinfo_treatment"));
			System.out.println(dto.getMin_price());
			return dto;
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}

	public List<String> selectPic(Connection conn, int tt_code) throws SQLException {
		String sql = "select pic from toptip_board b join toptip_attach a on(b.tt_code=a.tt_code) where b.tt_code = ?  and not pic like '%t.jpg' order by tt_attach_seq";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tt_code);
			rs = pstmt.executeQuery();
			List<String> list = new ArrayList<String>();
			while(rs.next()) {
				list.add(rs.getString("pic"));
			}
			return list;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		
	}

	public String selectTTtitle(Connection conn, int tt_code) throws SQLException {
		String sql = "select title from toptip_board where tt_code = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tt_code);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getString(1);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}


	
	
	
	
}
