package beepbeep.community.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.community.dao.FreeContentDAO;

public class FreeWriteService {
	private static FreeWriteService instance = new FreeWriteService();

	public static FreeWriteService getInstance() {
		return instance;
	}

	private FreeWriteService() {
		
	}
	
	public int freewrite(String id, String title, String hashtag, String contents) throws NamingException, SQLException {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			FreeContentDAO dao = FreeContentDAO.getInstance();
			int result = dao.freeInsert(conn, id, title, hashtag, contents);
			
			return result;
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
