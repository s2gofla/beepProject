package beepbeep.qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.qna.dao.QnaDAO;

public class QuesDeleteService {
	private static final QuesDeleteService instance = new QuesDeleteService();
	public static QuesDeleteService getInstance() {
		return instance;
	}
	private QuesDeleteService() {}
	public int quesDelete(int pq_seq) throws NamingException, SQLException {
		Connection conn = ConnectionProvider.getConnection();
		QnaDAO dao = QnaDAO.getInstance();
		
		int result = dao.questionDelete(conn, pq_seq);
		return result;
	}
	
	
	
}
