package beepbeep.qna.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.qna.dao.QnaDAO;
import beepbeep.qna.dto.QnaListDTO;
import beepbeep.qna.dto.QnaListView;

public class QnaListService {
	private static QnaListService instance = new QnaListService();
	
	public static QnaListService getInstance() {
		return instance;
	}
	private QnaListService() {}
	
	private static final int QNA_COUNT_PER_PAGE = 5;
	
	public QnaListView getQnaList(int pageNumber) {
		Connection conn = null;
		int currentPageNumber = pageNumber;
		int numberOfPageBlocks = 10;
		try {
			conn = ConnectionProvider.getConnection();
			
			QnaDAO qnadao = QnaDAO.getInstance();
			
			int qnaTotalCount = qnadao.selectCount(conn);
			QnaListView view = null;
			List<QnaListDTO> qnaList = null;
			int firstRow = 0;
			int endRow = 0;
			if (qnaTotalCount > 0) {
				firstRow = (pageNumber - 1) * QNA_COUNT_PER_PAGE + 1;
				endRow = firstRow + QNA_COUNT_PER_PAGE - 1;
				
				qnaList = qnadao.selectList(conn, firstRow, endRow);
				
				view = new QnaListView(qnaTotalCount, currentPageNumber, qnaList, QNA_COUNT_PER_PAGE, firstRow, endRow);
				 int pageBlockStart = 1;
				 int pageBlockEnd=10;
				 pageBlockStart = (currentPageNumber-1)/numberOfPageBlocks*numberOfPageBlocks+1;
				 pageBlockEnd = pageBlockStart + numberOfPageBlocks-1;
				 if(pageBlockEnd > view.getPageTotalCount()) pageBlockEnd = view.getPageTotalCount();
				 
				 view.setStart(pageBlockStart);
				 view.setEnd(pageBlockEnd);
				 
				 view.setPrev(pageBlockStart != 1? true: false);
				 view.setNext(pageBlockEnd != view.getPageTotalCount()?true:false);
				 
			} else {
				currentPageNumber = 0;
				qnaList = Collections.emptyList();
			}
			return view; 
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return null;
	}

}
