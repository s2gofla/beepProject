package beepbeep.cs.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import org.apache.jasper.tagplugins.jstl.core.Catch;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.cs.dao.Cs_FaqDAO;
import beepbeep.cs.model.Cs_FaqDTO;

//싱글톤
public class GetFaqListService {
	private static GetFaqListService instance = new GetFaqListService();
	
	public static GetFaqListService getInstance() {
		return instance;
	}
	private GetFaqListService() {
		
	}
	
	private static final int FAQ_COUNT_PER_PAGE=10;
	
	public FaqListView getFaqList(int pageNumber) {
		Connection conn = null;
		int currentPageNumber = pageNumber;
		
		try {
			conn = ConnectionProvider.getConnection();
			Cs_FaqDAO faqDao = Cs_FaqDAO.getInstance();
			int faqTotalCount = faqDao.selectCount(conn);
			
			List<Cs_FaqDTO> faqList = null;
			int firstRow = 0;
			int endRow = 0;
			if(faqTotalCount > 0) {
				firstRow = (pageNumber-1) * FAQ_COUNT_PER_PAGE+1;
				endRow = firstRow + FAQ_COUNT_PER_PAGE -1;
				
				faqList = faqDao.showList(conn, firstRow, endRow);
			}else {
				currentPageNumber = 0;
				faqList = Collections.emptyList();
			}
			return new  FaqListView(faqTotalCount, currentPageNumber, faqList, FAQ_COUNT_PER_PAGE, firstRow, endRow);
		} catch(SQLException | NamingException e) {
			throw new ServiceException("목록 구하기 실패:  " + e.getMessage(), e);
		}finally {
		JdbcUtil.close(conn);	
		}

	}
		
	}

