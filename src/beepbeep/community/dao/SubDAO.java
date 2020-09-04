package beepbeep.community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.util.JdbcUtil;

import beepbeep.community.dto.SubDTO;

public class SubDAO {
	private static SubDAO instance = new SubDAO();

	public static SubDAO getInstance() {
		return instance;
	}

	private SubDAO() {}
	
	public List<SubDTO> selectList(Connection conn) throws SQLException{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from m_sub_type";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<SubDTO> subList = new ArrayList<SubDTO>();
				do {
					SubDTO dto = new SubDTO();
					dto.setM_sub_seq(rs.getString("m_sub_seq"));
					dto.setM_sub_name(rs.getString("m_sub_name"));
					subList.add(dto);
				}while(rs.next());
				return subList;
			}else {
				return Collections.emptyList();
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

}
