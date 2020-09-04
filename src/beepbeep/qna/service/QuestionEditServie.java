package beepbeep.qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.qna.dao.QnaDAO;
import beepbeep.qna.dto.QnaListDTO;

public class QuestionEditServie {

	private static QuestionEditServie instance = new QuestionEditServie();
	
	public static QuestionEditServie getInstance() {
		return instance;
	}
	private QuestionEditServie() { }
	public QnaListDTO selectContent(int pq_seq) throws SQLException, NamingException {
		Connection conn = null;
		
		try{
			conn = ConnectionProvider.getConnection();
			QnaDAO qnadao = QnaDAO.getInstance();
			
			QnaListDTO contentDTO = qnadao.selectContent(conn, pq_seq);

					
			
			return contentDTO;
		}finally {
			JdbcUtil.close(conn);
		}

	}
	public int qnaUpdate(int pq_seq, String title, String contents) throws NamingException, SQLException {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			QnaDAO dao = QnaDAO.getInstance();
			int result = dao.updateQuestion(conn, pq_seq, title, contents);
			return result;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	
	
}
