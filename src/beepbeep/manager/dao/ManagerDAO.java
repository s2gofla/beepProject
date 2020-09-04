package beepbeep.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.util.JdbcUtil;

public class ManagerDAO {
	private static ManagerDAO instance = new ManagerDAO();
	public static ManagerDAO getInstance() {
		return instance;
	}
	private ManagerDAO() {}
	public int postReportRegister(Connection conn, int report_type, int all_board_seq, int board_seq,
			String contents, String id) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql ="insert into report values(report_seq.nextval, ?, ?, ?, 0, sysdate, ?, ? )";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, all_board_seq);
			pstmt.setInt(2, report_type);
			pstmt.setInt(3, board_seq);
			pstmt.setString(4, contents);
			pstmt.setString(5, id);
			
			result = pstmt.executeUpdate();
			
			return result;
		}finally {
			JdbcUtil.close(pstmt);
		}
		
	}
	public int commentReportRegister(Connection conn, int report_type, int all_board_seq, int comment_seq,
			String contents, String id) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql ="insert into report_comment values(report_co_seq.nextval, ?,?, 0, sysdate, ?, ? ,? )";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, all_board_seq);
			pstmt.setInt(2, comment_seq);
			pstmt.setString(3, contents);
			pstmt.setString(4, id);
			pstmt.setInt(5, report_type);
			
			result = pstmt.executeUpdate();
			
			return result;
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
}
