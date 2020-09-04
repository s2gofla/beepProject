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

public class GetAdminReportReplyListService {
	private static GetAdminReportReplyListService instance = new GetAdminReportReplyListService();
	
	public static GetAdminReportReplyListService getInstance() {
		return instance;
	}
	private GetAdminReportReplyListService() {
		
	}
	private static final int REPORT_REPLY_COUNT_PER_PAGE = 10;
	
	//현재페이지 번호
		public Admin_ReportReply_ListView getAdmin_ReportReplyList(int pageNumber){
			Connection conn =null;
			int currentPageNumber = pageNumber;
			
			try {
				conn = ConnectionProvider.getConnection();
				Admin_ReportDAO adin_reportReply_dao = Admin_ReportDAO.getInstance();
				int reportReplyTotalCount = adin_reportReply_dao.selectReplyCount(conn);
				
				List<Admin_ReportDTO> admin_reportReply_List = null;
				int firstRow = 0;
				int endRow = 0;
				if(reportReplyTotalCount > 0) {
					firstRow = (pageNumber-1)* REPORT_REPLY_COUNT_PER_PAGE +1;
					endRow = firstRow + REPORT_REPLY_COUNT_PER_PAGE -1;
					
					admin_reportReply_List = adin_reportReply_dao.showReplyList(conn, firstRow, endRow);
				}else {
					currentPageNumber = 0;
					admin_reportReply_List = Collections.emptyList();
				}
				return new Admin_ReportReply_ListView(reportReplyTotalCount, currentPageNumber, admin_reportReply_List, REPORT_REPLY_COUNT_PER_PAGE, firstRow, endRow);
			} catch (SQLException | NamingException e) {
			throw new ServiceException("목록 구하기 실패: " + e.getMessage(), e);
			}finally {
			JdbcUtil.close(conn);	
			}
			
		}
}
