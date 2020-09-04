package beepbeep.information.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.util.JdbcUtil;

import beepbeep.information.dto.PriceContentDTO;

public class PriceContentDAO {
	private static PriceContentDAO pricecontentdao = new PriceContentDAO();
	public static PriceContentDAO getInstance() {
		return pricecontentdao;
	}

	public List<PriceContentDTO> selectList(Connection conn, int pinfo_code) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select min_price, avg_price, max_price " + 
						"from price_info " +
						"where pinfo_code = ?" ;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pinfo_code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<PriceContentDTO> pricecontentlist = new ArrayList<PriceContentDTO>();
				do {
					PriceContentDTO dto = new PriceContentDTO();
					dto.setMin_price(rs.getInt("min_price"));
					dto.setAvg_price(rs.getInt("avg_price"));
					dto.setMax_price(rs.getInt("max_price"));
					
					pricecontentlist.add(dto);
				} while(rs.next());
				return pricecontentlist;
			}else {
				return Collections.emptyList();
			}
		}finally {
				 				
	 
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	 
	}
	
}
