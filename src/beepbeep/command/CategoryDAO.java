package beepbeep.command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.util.JdbcUtil;

import beepbeep.qna.dao.QnaDAO;

public class CategoryDAO {
	private static CategoryDAO categoryDAO = new CategoryDAO();

	public static CategoryDAO getInstance() {
		return categoryDAO;
	}

	private CategoryDAO() {}
	
	public List<CategoryDTO> selectList(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from m_sub_type";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<CategoryDTO> categoryList = new ArrayList<CategoryDTO>();
				do {
					CategoryDTO dto = new CategoryDTO();
					dto.setM_sub_name(rs.getString("m_sub_name"));
					dto.setM_sub_seq(rs.getInt("m_sub_seq"));
					
					categoryList.add(dto);
				}while(rs.next());
				return categoryList;
			}else {
				return Collections.emptyList();
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
