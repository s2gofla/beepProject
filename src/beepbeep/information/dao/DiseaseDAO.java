package beepbeep.information.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JdbcUtil;

import beepbeep.information.dto.DiseaseDTO;

public class DiseaseDAO {
	private static final DiseaseDAO instance = new DiseaseDAO();
	public static DiseaseDAO getInstance(){	
		return instance;
	}
	
	public DiseaseDTO selectList(Connection conn, int seq) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select d_name, definition, cause, symptom, diagnosis_treatment, prevention  " +
				"from disease_board join disease on(disease_board.d_seq=disease.d_seq)   " + 
				"where seq = ? " ;
 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			DiseaseDTO dto = null;
			if(rs.next()) {				
					dto = new DiseaseDTO();
					
					dto.setD_name(rs.getString("d_name"));
					dto.setDefinition(rs.getString("definition"));
					dto.setCause(rs.getString("cause"));
					dto.setSymptom(rs.getString("symptom"));
					dto.setDiagnosis_treatment(rs.getString("diagnosis_treatment"));
					dto.setPrevention(rs.getString("prevention"));
				
			}
			return dto;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
}