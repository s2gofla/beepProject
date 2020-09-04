package beepbeep.admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.admin.dao.Admin_ConfirmDAO;
import beepbeep.admin.model.Admin_ConfirmDTO;


public class Admin_Hconfirm_Content {
	
	public Admin_ConfirmDTO selectOne(int seq) {
		Admin_ConfirmDTO dto = null;
		Admin_ConfirmDAO dao = Admin_ConfirmDAO.getInstance();
		
		
		try {
			Connection conn;
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			dto = dao.h_select(conn, seq);
			conn.commit();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	
		
	}
}
