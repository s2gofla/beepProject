package beepbeep.information.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JdbcUtil;

import beepbeep.information.dto.DiseaseListDTO;

public class DiseaseListDAO {
	private static final DiseaseListDAO instance = new DiseaseListDAO();
	public static DiseaseListDAO getInstance(){	
		return instance;
	}
	
	public List<DiseaseListDTO> selectList(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select seq, d_name, definition    " + 
				"from disease_board join disease on(disease_board.d_seq=disease.d_seq)  " ;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				List<DiseaseListDTO> list = new ArrayList<DiseaseListDTO>();
				do {
					DiseaseListDTO dto = new DiseaseListDTO();
					dto.setSeq(rs.getInt("seq"));
					dto.setD_name(rs.getString("d_name"));
					dto.setDefinition(rs.getString("definition"));
					
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
