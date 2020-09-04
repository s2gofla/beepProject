package beepbeep.cs.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.cs.dao.Cs_AskDAO;
import beepbeep.cs.model.Cs_AskDTO;

public class GetAskListService {
	private static GetAskListService instance = new GetAskListService();
	
	public static GetAskListService getInstance() {
		return instance;
	}
	private GetAskListService() {
		
	}
	
	private static final int ASK_COUNT_PER_PAGE = 10;
	
	public AskListView getAskList(int pageNumber) {
		Connection conn =null;
		int currentPageNumber = pageNumber;
		
		try {
			conn = ConnectionProvider.getConnection();
			Cs_AskDAO askDao = Cs_AskDAO.getInstance();
			int askTotalCount = askDao.selectCount(conn);
			List<Cs_AskDTO> askList = null;
			int firstRow = 0;
			int endRow = 0;
			if(askTotalCount > 0) {
				firstRow = (pageNumber -1) * ASK_COUNT_PER_PAGE+1;
				endRow = firstRow + ASK_COUNT_PER_PAGE -1;
				askList = askDao.showList(conn, firstRow, endRow);
			}else {
				currentPageNumber = 0;
				askList = Collections.emptyList();
			}
			return new AskListView(askTotalCount, currentPageNumber, askList, ASK_COUNT_PER_PAGE, firstRow, endRow);
		} catch (SQLException  | NamingException e) {
			throw new ServiceException("목록 구하기 실패:  " + e.getMessage(), e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
}
