package beepbeep.cs.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.cs.dao.Cs_FaqDAO;

public class FaqDelete {
	private static FaqDelete instance = new FaqDelete();
	public static FaqDelete getInstance() {
		return instance;
	}
	private FaqDelete() {
		
	}
	
	public int delete(int faq_list_seq) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			Cs_FaqDAO dao = Cs_FaqDAO.getInstance();
			
			result = dao.delete(conn, faq_list_seq);
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
