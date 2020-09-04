package beepbeep.community.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.community.dao.FreeContentDAO;
import beepbeep.community.dto.FreeListDTO;

public class FreeUpdateService {
	private static FreeUpdateService instance = new FreeUpdateService();

	public static FreeUpdateService getInstance() {
		return instance;
	}

	private FreeUpdateService() {
		
	}
	
	public int freeupdate(String id, String title, String hashtag, String contents, int fboard_seq) throws NamingException, SQLException {
		Connection conn = null;
		
		
		try {
			conn = ConnectionProvider.getConnection();
			
			FreeContentDAO dao = FreeContentDAO.getInstance();
			int result = dao.freeUpdate(conn, id, title, hashtag, contents, fboard_seq);
			
			return result;
		}finally {
			JdbcUtil.close(conn);
		}
	}


}
