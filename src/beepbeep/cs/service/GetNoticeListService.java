package beepbeep.cs.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.cs.dao.Cs_NoticeDAO;
import beepbeep.cs.model.Cs_NoticeDTO;

//싱글톤
public class GetNoticeListService {
	private static GetNoticeListService instance = new GetNoticeListService();
	
	public static GetNoticeListService getInstance() {
		return instance;
	}
	private GetNoticeListService() {
		
	}
	//상수선언
	private static final int NOTICE_COUNT_PER_PAGE = 10;
	
	//현재페이지 번호
	public NoticeListView getNoticeList(int pageNumber){
		Connection conn =null;
		int currentPageNumber = pageNumber;
		
		try {
			conn = ConnectionProvider.getConnection();
			Cs_NoticeDAO noticeDao = Cs_NoticeDAO.getInstance();
			int noticeTotalCount = noticeDao.selectCount(conn);
			
			List<Cs_NoticeDTO> noticeList = null;
			int firstRow = 0;
			int endRow = 0;
			if(noticeTotalCount > 0) {
				firstRow = (pageNumber-1)* NOTICE_COUNT_PER_PAGE +1;
				endRow = firstRow + NOTICE_COUNT_PER_PAGE -1;
				
				noticeList = noticeDao.showList(conn, firstRow, endRow);
			}else {
				currentPageNumber = 0;
				noticeList = Collections.emptyList();
			}
			return new NoticeListView(noticeTotalCount, currentPageNumber, noticeList, NOTICE_COUNT_PER_PAGE, firstRow, endRow);
		} catch (SQLException | NamingException e) {
		throw new ServiceException("목록 구하기 실패: " + e.getMessage(), e);
		}finally {
		JdbcUtil.close(conn);	
		}
		
	}
}
