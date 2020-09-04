package beepbeep.cs.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.cs.dao.Cs_AskDAO;


public class AskDelete {
	private static AskDelete instance = new AskDelete();
	public static AskDelete getInstance() {
		return instance;
	}
	private AskDelete() {
		
	}
	
	public int delete(int qna_seq) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			Cs_AskDAO dao = Cs_AskDAO.getInstance();
			
			result = dao.delete(conn, qna_seq);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
			return result;
		}
		
	
		
		
	}
	
}
