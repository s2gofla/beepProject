package beepbeep.admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.admin.dao.Admin_ConfirmDAO;


public class Admin_Hconfirm {
	private static Admin_Hconfirm instance = new Admin_Hconfirm();
	public static Admin_Hconfirm getInstance() {
		return instance;
	}
	private Admin_Hconfirm() {
		
	}
	
	public int confirm(int seq) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			Admin_ConfirmDAO dao = Admin_ConfirmDAO.getInstance();
			
			result = dao.h_update(conn, seq);
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
