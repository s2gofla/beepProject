package beepbeep.information.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.util.JdbcUtil;

import beepbeep.information.dto.ToptipCategoryDTO;


public class ToptipCategoryDAO {
	private static ToptipCategoryDAO toptipcategoryDAO = new ToptipCategoryDAO();
	public static ToptipCategoryDAO getInstance() {
		return toptipcategoryDAO;
	}

	private ToptipCategoryDAO() {}
	
	public List<ToptipCategoryDTO> selectList(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from toptip_type";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<ToptipCategoryDTO> toptipcategoryList = new ArrayList<ToptipCategoryDTO>();
				do {
					ToptipCategoryDTO dto = new ToptipCategoryDTO();
					dto.setTt_type_code(rs.getInt("tt_type_code"));
					dto.setTt_type_name(rs.getString("tt_type_name"));
					
					toptipcategoryList.add(dto);
				}while(rs.next());
				return toptipcategoryList;
			}else {
				return Collections.emptyList();
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
