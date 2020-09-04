package beepbeep.manager.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.manager.dao.ManagerDAO;

public class CommentReportService {
	private static CommentReportService instance = new CommentReportService();
	public static CommentReportService getInstance() {
		return instance;
	}
	private CommentReportService() {}
	public int commentReportRegister(int report_type, int all_board_seq, int comment_seq, String contents, String id) throws NamingException, SQLException {
		Connection conn = ConnectionProvider.getConnection();
		
		ManagerDAO dao = ManagerDAO.getInstance();
		int result = dao.commentReportRegister(conn, report_type, all_board_seq , comment_seq, contents, id);
		
		
		return result;
	}
}
