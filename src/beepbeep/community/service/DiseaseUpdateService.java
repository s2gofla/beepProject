package beepbeep.community.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.community.dao.DiseaseDAO;
import beepbeep.community.dao.FreeContentDAO;

public class DiseaseUpdateService {

	private static DiseaseUpdateService instance = new DiseaseUpdateService();

	public static DiseaseUpdateService getInstance() {
		return instance;
	}

	private DiseaseUpdateService() {
		
	}
	
	
	public int diseaseupdate(String id, int m_sub_seq, String title, String hashtag, String contents, int dtip_seq) throws NamingException, SQLException {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			DiseaseDAO dao = DiseaseDAO.getInstance();
			int result = dao.diseaseUpdate(conn, id, m_sub_seq, title, hashtag, contents, dtip_seq);
			
			return result;
		}finally {
			JdbcUtil.close(conn);
		}
	}

}
