package beepbeep.qna.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.qna.dao.QnaDAO;
import beepbeep.qna.dto.QnaCommentDTO;
import beepbeep.qna.dto.QnaContentView;
import beepbeep.qna.dto.QnaListDTO;

public class QnaContentService {
	private static QnaContentService instance = new QnaContentService();
	
	public static QnaContentService getInstance() {
		return instance;
	}
	private QnaContentService() {}
	
	public QnaContentView getQnaContent(String sid, int pq_seq) throws NamingException, SQLException {
		Connection conn = null;
		
		try{
			conn = ConnectionProvider.getConnection();
			QnaDAO qnadao = QnaDAO.getInstance();
			
			QnaListDTO contentDTO = qnadao.selectContent(conn, pq_seq);
			qnadao.countReaded(conn, pq_seq);
			List<QnaCommentDTO> commentList = qnadao.selectComment(conn, pq_seq, sid);
			int id_qlike = qnadao.questionlike(conn, pq_seq, sid);
			QnaContentView view = new QnaContentView();
			view.setId_like(id_qlike);
			view.setCommentList(commentList);
			view.setContentDTO(contentDTO);
			
			return view;
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	
}
