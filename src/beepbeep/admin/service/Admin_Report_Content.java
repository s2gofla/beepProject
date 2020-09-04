package beepbeep.admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.admin.dao.Admin_ConfirmDAO;
import beepbeep.admin.dao.Admin_ReportDAO;
import beepbeep.admin.model.Admin_ConfirmDTO;
import beepbeep.admin.model.Admin_ReportDTO;


public class Admin_Report_Content {
	
	public Admin_ReportDTO selectOne(int report_seq) {
		Admin_ReportDTO dto = null;
		Admin_ReportDAO dao = Admin_ReportDAO.getInstance();
		
		
		try {
			Connection conn;
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			dto = dao.report_select(conn, report_seq);
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
