package beepbeep.community.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.community.dao.DiseaseDAO;
import beepbeep.community.dao.FreeListDAO;

public class DiseaseDeleteService {
	private static DiseaseDeleteService instance = new DiseaseDeleteService();

	public static DiseaseDeleteService getInstance() {
		return instance;
	}

	private DiseaseDeleteService() {
		
	}

	public int diseasedelete(int dtip_seq, String pPwd) throws NamingException, SQLException {
		Connection conn = null;
		int result=0;
		try {
			conn = ConnectionProvider.getConnection();
			
			DiseaseDAO dao = DiseaseDAO.getInstance();
			String oPwd = dao.selectOpwd(conn, dtip_seq);
			if(oPwd.equals(pPwd)) {
				result = dao.diseaseDelete(conn, dtip_seq, pPwd);
				return result;
			}
		}finally {
			JdbcUtil.close(conn);
		}
		return result;
	}

}
