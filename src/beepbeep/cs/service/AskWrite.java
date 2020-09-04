package beepbeep.cs.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.cs.dao.Cs_AskDAO;

import beepbeep.cs.model.Cs_AskDTO;



public class AskWrite {
	private static AskWrite instance = new AskWrite();
	public static AskWrite getInstance() {
		return instance;
	}
	private AskWrite() {
		
	}
	
	public int write(Cs_AskDTO dto) {
		Connection conn = null;
		int result = 0;
		try {
			conn = ConnectionProvider.getConnection();
			Cs_AskDAO dao = Cs_AskDAO.getInstance();
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

