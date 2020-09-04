package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.util.JdbcUtil;

public class TestDAO {
	private static TestDAO TestDAO = new TestDAO();
	// 싱글톤 getInstanc()
	public static TestDAO getInstance() {
		return TestDAO;
	}
	// private 생성자 new 객체 생성 x
	private TestDAO() {}
	public Message select(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from TOPTIP_BOARD where TT_CODE = 1");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// makeMessageFromResultSet() 
				return makeMessageFromResultSet(rs);
			} else {
				return null;
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Message makeMessageFromResultSet(ResultSet rs) throws SQLException {
		Message message = new Message();
		message.setTt_code(rs.getInt("tt_code"));
		message.setTitle( rs.getString("title"));		
		message.setDates(rs.getDate("dates"));
		return message;
	}
}
