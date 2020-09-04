package beepbeep.admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.admin.dao.Admin_ConfirmDAO;
import beepbeep.admin.model.Admin_ConfirmDTO;

public class GetAdminConfrimListService {
	private static GetAdminConfrimListService instance = new GetAdminConfrimListService();
	
	public static GetAdminConfrimListService getInstance() {
		return instance;
	}
	private GetAdminConfrimListService() {
		
	}
	private static final int D_CONFRIM_COUNT_PER_PAGE = 10;
	
	//현재페이지 번호
		public Admin_Confirm_ListView getAdmin_ConfrimList(int pageNumber){
			Connection conn =null;
			int currentPageNumber = pageNumber;
			
			try {
				conn = ConnectionProvider.getConnection();
				Admin_ConfirmDAO adin_confirm_dao = Admin_ConfirmDAO.getInstance();
				int admin_confirmTotalCount = adin_confirm_dao.selectCount(conn);
				
				List<Admin_ConfirmDTO> admin_confirm_List = null;
				int firstRow = 0;
				int endRow = 0;
				if(admin_confirmTotalCount > 0) {
					firstRow = (pageNumber-1)* D_CONFRIM_COUNT_PER_PAGE +1;
					endRow = firstRow + D_CONFRIM_COUNT_PER_PAGE -1;
					
					admin_confirm_List = adin_confirm_dao.showD_List(conn, firstRow, endRow);
				}else {
					currentPageNumber = 0;
					admin_confirm_List = Collections.emptyList();
				}
				return new Admin_Confirm_ListView (admin_confirmTotalCount, currentPageNumber, admin_confirm_List, D_CONFRIM_COUNT_PER_PAGE, firstRow, endRow);
			} catch (SQLException | NamingException e) {
			throw new ServiceException("목록 구하기 실패: " + e.getMessage(), e);
			}finally {
			JdbcUtil.close(conn);	
			}
			
		}
}
