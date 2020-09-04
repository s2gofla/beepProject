package beepbeep.manager.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.manager.dao.ManagerDAO;

public class PostReportService {
	private static PostReportService instance = new PostReportService();
	public static PostReportService getInstance() {
		return instance;
	}
	private PostReportService() {}
	public int postReportRegister(int report_type, int all_board_seq, int board_seq, String contents, String id) throws NamingException, SQLException {
		Connection conn = ConnectionProvider.getConnection();
		ManagerDAO dao = ManagerDAO.getInstance();
		int result = dao.postReportRegister(conn, report_type, all_board_seq, board_seq, contents, id);
		
		return result;
	}
	
}
