package beepbeep.admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.admin.dao.Admin_ReportDAO;
import beepbeep.admin.model.Admin_ReportDTO;

public class GetAdminReportListService {
	private static GetAdminReportListService instance = new GetAdminReportListService();
	
	public static GetAdminReportListService getInstance() {
		return instance;
	}
	private GetAdminReportListService() {
		
	}
	private static final int REPORT_COUNT_PER_PAGE = 10;
	
	//현재페이지 번호
		public Admin_Report_ListView getAdmin_ReportList(int pageNumber){
			Connection conn =null;
			int currentPageNumber = pageNumber;
			
			try {
				conn = ConnectionProvider.getConnection();
				Admin_ReportDAO adin_report_dao = Admin_ReportDAO.getInstance();
				int reportTotalCount = adin_report_dao.selectCount(conn);
				
				List<Admin_ReportDTO> admin_report_List = null;
				int firstRow = 0;
				int endRow = 0;
				if(reportTotalCount > 0) {
					firstRow = (pageNumber-1)* REPORT_COUNT_PER_PAGE +1;
					endRow = firstRow + REPORT_COUNT_PER_PAGE -1;
					
					admin_report_List = adin_report_dao.showList(conn, firstRow, endRow);
				}else {
					currentPageNumber = 0;
					admin_report_List = Collections.emptyList();
				}
				return new Admin_Report_ListView(reportTotalCount, currentPageNumber, admin_report_List, REPORT_COUNT_PER_PAGE, firstRow, endRow);
			} catch (SQLException | NamingException e) {
			throw new ServiceException("목록 구하기 실패: " + e.getMessage(), e);
			}finally {
			JdbcUtil.close(conn);	
			}
			
		}
}
