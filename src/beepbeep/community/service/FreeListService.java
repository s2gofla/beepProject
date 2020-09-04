package beepbeep.community.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.community.dao.FreeListDAO;

import beepbeep.community.dto.FreeListDTO;
import beepbeep.community.dto.FreeListView;

public class FreeListService {
	private static FreeListService instance = new FreeListService();

	public static FreeListService getInstance() {
		return instance;
	}

	private FreeListService() {
	}

	private static final int COUNT_PER_PAGE = 15;

	

	
	public FreeListView getFreeList(int currentPage) {
		Connection conn = null;
		int currentPageNumber = currentPage;
		try {
			conn = ConnectionProvider.getConnection();
			// dao
			FreeListDAO freeListDAO = FreeListDAO.getInstance();

			int messageTotalCount = freeListDAO.selectCount(conn);

			List<FreeListDTO> FreeList = null;
			int firstRow = 0;
			int endRow = 0;
			// firstRow endRow
			if (messageTotalCount > 0) { // 글이 있으면 페이징 처리
				// 2
				endRow = messageTotalCount - (currentPage - 1) * COUNT_PER_PAGE;
				firstRow = endRow - (COUNT_PER_PAGE - 1);
				// dao.select
				FreeList = freeListDAO.selectList(conn, firstRow, endRow); // 메세지 목록들

			} else {
				currentPageNumber = 0;
				FreeList = Collections.emptyList(); // 비어있는 리스트로 유지
			}
			int pageBlockStart = 1; 
			int pageBlockEnd=10; 

			
			return new FreeListView(messageTotalCount, currentPageNumber, FreeList, COUNT_PER_PAGE, firstRow, endRow, pageBlockStart, pageBlockEnd);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return null;
	}

	// 검색 조건과, 검색어
	public FreeListView getSearchList(int searchCondition, String searchWord, int currentPage) {
		Connection conn = null;
		int currentPageNumber = currentPage;

		try {
			conn = ConnectionProvider.getConnection();

			// dao
			FreeListDAO freeListDAO = FreeListDAO.getInstance();
			int messageTotalCount = freeListDAO.searchCount(conn, searchCondition, searchWord);

			List<FreeListDTO> SearchList = null;
			int firstRow = 0;
			int endRow = 0;
			// firstRow endRow
			if (messageTotalCount > 0) { // 글이 있으면 페이징 처리
				// 2
				endRow = messageTotalCount - (currentPage - 1) * COUNT_PER_PAGE;
				firstRow = endRow - (COUNT_PER_PAGE - 1);
				// dao.select
				SearchList = freeListDAO.searchList(conn, searchCondition, searchWord, firstRow, endRow); // 메세지 목록들
			} else {
				currentPageNumber = 0;
				SearchList = Collections.emptyList(); // 비어있는 리스트로 유지
			}
			//
			int pageBlockStart = 1; 
			int pageBlockEnd=10; 

			return new FreeListView(messageTotalCount, currentPageNumber, SearchList, COUNT_PER_PAGE, firstRow, endRow, pageBlockStart, pageBlockEnd);
			// 매개변수에 포함해야 전달됨
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return null;

	}

	//

}
