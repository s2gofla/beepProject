package beepbeep.admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.admin.dao.Admin_ReportDAO;
import beepbeep.cs.dao.Cs_NoticeDAO;
import beepbeep.cs.model.Cs_NoticeDTO;

public class Admin_ReportDelete {
	private static Admin_ReportDelete instance = new Admin_ReportDelete();
	public static Admin_ReportDelete getInstance() {
		return instance;
	}
	private Admin_ReportDelete() {
		
	}
	
	public int delete(int report_seq) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			Admin_ReportDAO dao = Admin_ReportDAO.getInstance();
			
			result = dao.delete(conn, report_seq);
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
