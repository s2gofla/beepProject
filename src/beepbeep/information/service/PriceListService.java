package beepbeep.information.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.information.dao.InfoDAO;
import beepbeep.information.dto.PriceListDTO;
import beepbeep.information.dto.PriceListView;

public class PriceListService {
	private static final PriceListService instance = new PriceListService();
	public static PriceListService getInstance(){	
		return instance;
	}
	private static final int COUNT_PER_PAGE=10;

	
	public PriceListView selectList(int currentPage, String searchWord, int m_sub_seq) throws SQLException, NamingException{
		
		Connection conn = ConnectionProvider.getConnection();
		
		InfoDAO dao = InfoDAO.getInstance();
		int numberOfPagesBlocks = 10;
		int totalcount = dao.pricelistCount(conn, searchWord, m_sub_seq);
		
		List<PriceListDTO> list = null;
		PriceListView view = null;

        int firstRow = 0;
        int endRow = 0;
        if (totalcount > 0) {
           firstRow = (currentPage - 1) * COUNT_PER_PAGE + 1;
           endRow = firstRow + COUNT_PER_PAGE - 1;
           System.out.println("cp"+currentPage);
           System.out.println(firstRow);
           System.out.println(endRow);
           list = dao.priceList(conn, firstRow, endRow, searchWord, m_sub_seq);
           
           view = new PriceListView(totalcount, currentPage, list, COUNT_PER_PAGE, firstRow, endRow);
           int pageBlockStart = 1;
           int pageBlockEnd=10;
           pageBlockStart = (currentPage-1)/numberOfPagesBlocks*numberOfPagesBlocks+1;
           pageBlockEnd = pageBlockStart + numberOfPagesBlocks-1;
           if(pageBlockEnd > view.getPageTotalCount()) 
           	pageBlockEnd = view.getPageTotalCount();
           
           view.setStart(pageBlockStart);
           view.setEnd(pageBlockEnd);
           
           view.setPrev(pageBlockStart != 1? true: false);
           view.setNext(pageBlockEnd != view.getPageTotalCount()?true:false);
           
       } else {
       	currentPage = 0;
          list = Collections.emptyList();
          view = new PriceListView();
       }
       JdbcUtil.close(conn);
       return view; 
       

	}
}
