package beepbeep.information.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JdbcUtil;

import beepbeep.information.dto.PriceListDTO;

public class PriceListDAO {
	private static final PriceListDAO instance = new PriceListDAO();
	public static PriceListDAO getInstance(){	
		return instance;
	}
	
	public List<PriceListDTO> selectList(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select pinfo_code, pinfo_treatment " +
					"from price_info ";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				List<PriceListDTO> list = new ArrayList<PriceListDTO>();
				do {
					PriceListDTO dto = new PriceListDTO();					
					dto.setPinfo_code(rs.getInt("pinfo_code"));
					dto.setPinfo_treatment(rs.getString("pinfo_treatment"));
					
					list.add(dto);
				}while(rs.next());
				return list;
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
	}
	
}
