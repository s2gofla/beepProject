package beepbeep.admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.admin.dao.Admin_ConfirmDAO;


public class Admin_Dconfirm {
	private static Admin_Dconfirm instance = new Admin_Dconfirm();
	public static Admin_Dconfirm getInstance() {
		return instance;
	}
	private Admin_Dconfirm() {
		
	}
	
	public int confirm(int c_seq) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			Admin_ConfirmDAO dao = Admin_ConfirmDAO.getInstance();
			
			result = dao.d_update(conn, c_seq);
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
