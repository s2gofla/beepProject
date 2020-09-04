package beepbeep.information.service;
 
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.information.dao.InfoDAO;
import beepbeep.information.dto.ToptipListDTO;
import beepbeep.information.dto.ToptipListView;

public class ToptipListService {
	private static final ToptipListService instance = new ToptipListService();
	public static ToptipListService getInstance(){	
		return instance;
	}
	private static final int COUNT_PER_PAGE=12;

	
	public  ToptipListView selectList(int currentPage, String searchWord) throws SQLException, NamingException{
		
		Connection conn = ConnectionProvider.getConnection();
 
		InfoDAO dao = InfoDAO.getInstance();
		int numberOfPagesBlocks = 10;
		int totalcount = dao.toptiplistCount(conn, searchWord);
		List<ToptipListDTO> list =  null;
		ToptipListView view = null;
		
        int firstRow = 0;
        int endRow = 0;
        if (totalcount > 0) {
           firstRow = (currentPage - 1) * COUNT_PER_PAGE + 1;
           endRow = firstRow + COUNT_PER_PAGE - 1;
           System.out.println("cp"+currentPage);
           System.out.println(firstRow);
           System.out.println(endRow);
           list = dao.toptipList(conn, firstRow, endRow, searchWord);
            
           view = new ToptipListView(totalcount, currentPage, list, COUNT_PER_PAGE, firstRow, endRow);
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
        }
        JdbcUtil.close(conn);
        return view; 
        
 
	}
}
