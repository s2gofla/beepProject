package beepbeep.information.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.util.JdbcUtil;

import beepbeep.information.dto.SoreSpotCategoryDTO;


public class SoreSpotCategoryDAO {
	private static SoreSpotCategoryDAO sorespotcategoryDAO = new SoreSpotCategoryDAO();

	public static SoreSpotCategoryDAO getInstance() {
		return sorespotcategoryDAO;
	}

	private SoreSpotCategoryDAO() {}
	
	public List<SoreSpotCategoryDTO> selectList(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from sore_spot";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<SoreSpotCategoryDTO> sorespotcategoryList = new ArrayList<SoreSpotCategoryDTO>();
				do {
					SoreSpotCategoryDTO dto = new SoreSpotCategoryDTO();
					dto.setSs_code(rs.getInt("ss_code"));
					dto.setSs_name(rs.getString("ss_name"));
					
					sorespotcategoryList.add(dto);
				}while(rs.next());
				return sorespotcategoryList;
			}else {
				return Collections.emptyList();
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
