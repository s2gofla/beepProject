package beepbeep.community.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.community.dao.DiseaseDAO;
import beepbeep.community.dto.DiseaseListDTO;
import beepbeep.community.dto.DiseaseListView;

public class DiseaseListService {
	private static DiseaseListService instance = new DiseaseListService();

	public static DiseaseListService getInstance() {
		return instance;
	}

	private DiseaseListService() {}

	private static final int COUNT_PER_PAGE = 15;
	
	public DiseaseListView getDiseaseList(int searchCondition, String searchWord,int currentPage) {
		Connection conn = null;
		int currentPageNumber = currentPage;
		try {
			conn = ConnectionProvider.getConnection();
			// dao
			DiseaseDAO diseaseListDAO = DiseaseDAO.getInstance();
			
			int messageTotalCount = diseaseListDAO.selectCount(conn, searchCondition, searchWord);
			
			List<DiseaseListDTO> DiseaseList = null;
			int firstRow = 0;
			int endRow = 0;
			// firstRow endRow
			if (messageTotalCount > 0) { // 방명록이 있으면 페이징 처리
				endRow = messageTotalCount-(currentPage - 1) * COUNT_PER_PAGE; // 11
				firstRow = endRow - (COUNT_PER_PAGE - 1);
				//	dao.select
				DiseaseList = diseaseListDAO.selectList(conn, searchCondition, searchWord, firstRow, endRow); // 메세지 목록들
			} else {
				currentPageNumber = 0;
				DiseaseList = Collections.emptyList(); // 비어있는 리스트로 유지
			}
			int pageBlockStart = 1; 
			int pageBlockEnd=10; 
			
			return new DiseaseListView(messageTotalCount, currentPageNumber, DiseaseList, COUNT_PER_PAGE, firstRow, endRow, pageBlockStart, pageBlockEnd);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return null;
	}
	
	
	
	
}
