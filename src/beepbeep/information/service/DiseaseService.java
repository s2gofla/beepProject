package beepbeep.information.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.information.dao.InfoDAO;
import beepbeep.information.dto.DiseaseDTO;

public class DiseaseService {
	private static final DiseaseService instance = new DiseaseService();
	
	public static DiseaseService getInstance(){	
		return instance;
	}
	private DiseaseService() {}
	
	public DiseaseDTO selectList(int seq) throws SQLException, NamingException{
		
		Connection conn = ConnectionProvider.getConnection();
		
		InfoDAO dao = InfoDAO.getInstance();
		DiseaseDTO dto =  dao.diseaseContent(conn, seq);
		
		return dto;
		
	}

}
