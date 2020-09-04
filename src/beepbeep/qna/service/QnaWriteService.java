package beepbeep.qna.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.qna.dao.QnaDAO;

public class QnaWriteService {
	private static QnaWriteService instance = new QnaWriteService();

	public static QnaWriteService getInstance() {
		return instance;
	}
	private QnaWriteService() {}
	
	public int qnaWirte(String id, int select, String title, String contents) throws NamingException, SQLException {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			QnaDAO dao = QnaDAO.getInstance();
			int result = dao.qnaInsert(conn, id, select ,title, contents);
			
			return result;
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
