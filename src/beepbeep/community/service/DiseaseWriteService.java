package beepbeep.community.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.community.dao.DiseaseDAO;
import beepbeep.community.dao.FreeContentDAO;

public class DiseaseWriteService {
	private static DiseaseWriteService instance = new DiseaseWriteService();

	public static DiseaseWriteService getInstance() {
		return instance;
	}

	private DiseaseWriteService() {
		
	}

	public int diseasewrite(String id, int m_sub_seq, String title, String hashtag, String contents) throws NamingException, SQLException {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			DiseaseDAO dao = DiseaseDAO.getInstance();
			int result = dao.diseaseInsert(conn, id, m_sub_seq, title, hashtag, contents);
			
			return result;
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
