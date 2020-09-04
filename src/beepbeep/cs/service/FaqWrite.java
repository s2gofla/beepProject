package beepbeep.cs.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.cs.dao.Cs_FaqDAO;
import beepbeep.cs.model.Cs_FaqDTO;


public class FaqWrite {
	private static FaqWrite instance = new FaqWrite();
	public static FaqWrite getInstance() {
		return instance;
	}
	private FaqWrite() {
		
	}
	
	public int write(Cs_FaqDTO dto) {
		Connection conn = null;
		int result = 0;
		try {
			conn = ConnectionProvider.getConnection();
			Cs_FaqDAO dao = Cs_FaqDAO.getInstance();
			result = dao.insert(conn, dto);
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

