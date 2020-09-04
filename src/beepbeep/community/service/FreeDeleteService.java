package beepbeep.community.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.community.dao.FreeListDAO;
import beepbeep.community.dto.FreeListDTO;

public class FreeDeleteService {
	private static FreeDeleteService instance = new FreeDeleteService();

	public static FreeDeleteService getInstance() {
		return instance;
	}

	private FreeDeleteService() {
		
	}

	public int freedelete(int fboard_seq, String pPwd) throws NamingException, SQLException {
		Connection conn = null;
		int result=0;
		try {
			conn = ConnectionProvider.getConnection();
			
			FreeListDAO dao = FreeListDAO.getInstance();
			String oPwd = dao.selectOpwd(conn, fboard_seq);
			if(oPwd.equals(pPwd)) {
				result = dao.freeDelete(conn, fboard_seq, pPwd);
				return result;
			}
		}finally {
			JdbcUtil.close(conn);
		}
		return result;


	}
	
}
