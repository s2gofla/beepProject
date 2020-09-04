package beepbeep.admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.admin.dao.Admin_ConfirmDAO;


public class Admin_ConfirmDelete {
	private static Admin_ConfirmDelete instance = new Admin_ConfirmDelete();
	public static Admin_ConfirmDelete getInstance() {
		return instance;
	}
	private Admin_ConfirmDelete() {
		
	}
	
	public int delete(int c_seq) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			Admin_ConfirmDAO dao = Admin_ConfirmDAO.getInstance();
			
			result = dao.ddelete(conn, c_seq);
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
