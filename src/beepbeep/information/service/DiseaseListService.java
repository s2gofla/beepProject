package beepbeep.information.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.information.dao.InfoDAO;
import beepbeep.information.dto.DiseaseListDTO;
import beepbeep.information.dto.DiseaseListView;

public class DiseaseListService {
	private static final DiseaseListService instance = new DiseaseListService();
	public static DiseaseListService getInstance(){	
		return instance;
	}
	private static final int COUNT_PER_PAGE=10;
	
	public DiseaseListView selectList(int currentPage, String searchWord) throws SQLException, NamingException{
		
		Connection conn = ConnectionProvider.getConnection();
		
		InfoDAO dao = InfoDAO.getInstance();
		int numberOfPagesBlocks = 10;
		int totalcount =0;
		totalcount = dao.diseaselistCount(conn, searchWord);
		System.out.println("ttcode"+totalcount);
		List<DiseaseListDTO> list =  null;
		DiseaseListView view = null;
		
        int firstRow = 0;
        int endRow = 0;
        if (totalcount > 0) {
           firstRow = (currentPage - 1) * COUNT_PER_PAGE + 1;
           endRow = firstRow + COUNT_PER_PAGE - 1;
           System.out.println(firstRow);
           System.out.println(endRow);
           list = dao.selectList(conn, firstRow, endRow, searchWord);
            
           view = new DiseaseListView(totalcount, currentPage, list, COUNT_PER_PAGE, firstRow, endRow);
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
