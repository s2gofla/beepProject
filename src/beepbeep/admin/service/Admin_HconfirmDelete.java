package beepbeep.admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.admin.dao.Admin_ConfirmDAO;
import beepbeep.admin.dao.Admin_ReportDAO;
import beepbeep.cs.dao.Cs_NoticeDAO;
import beepbeep.cs.model.Cs_NoticeDTO;

public class Admin_HconfirmDelete {
	private static Admin_HconfirmDelete instance = new Admin_HconfirmDelete();
	public static Admin_HconfirmDelete getInstance() {
		return instance;
	}
	private Admin_HconfirmDelete() {
		
	}
	
	public int delete(int seq) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			Admin_ConfirmDAO dao = Admin_ConfirmDAO.getInstance();
			
			result = dao.delete(conn, seq);
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
