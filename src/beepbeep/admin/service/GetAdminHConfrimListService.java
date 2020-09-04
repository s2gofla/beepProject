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

public class GetAdminHConfrimListService {
	private static GetAdminHConfrimListService instance = new GetAdminHConfrimListService();
	
	public static GetAdminHConfrimListService getInstance() {
		return instance;
	}
	private GetAdminHConfrimListService() {
		
	}
	private static final int H_CONFRIM_COUNT_PER_PAGE = 10;
	
	//현재페이지 번호
		public Admin_HConfirm_ListView getAdmin_HConfrimList(int pageNumber){
			Connection conn =null;
			int currentPageNumber = pageNumber;
			
			try {
				conn = ConnectionProvider.getConnection();
				Admin_ConfirmDAO adin_Hconfirm_dao = Admin_ConfirmDAO.getInstance();
				int admin_MconfirmTotalCount = adin_Hconfirm_dao.selectH_Count(conn);
				
				List<Admin_ConfirmDTO> admin_Mconfirm_List = null;
				int firstRow = 0;
				int endRow = 0;
				if(admin_MconfirmTotalCount > 0) {
					firstRow = (pageNumber-1)* H_CONFRIM_COUNT_PER_PAGE +1;
					endRow = firstRow + H_CONFRIM_COUNT_PER_PAGE -1;
					
					admin_Mconfirm_List = adin_Hconfirm_dao.showH_List(conn, firstRow, endRow);
				}else {
					currentPageNumber = 0;
					admin_Mconfirm_List = Collections.emptyList();
				}
				return new Admin_HConfirm_ListView (admin_MconfirmTotalCount, currentPageNumber, admin_Mconfirm_List, H_CONFRIM_COUNT_PER_PAGE, firstRow, endRow);
			} catch (SQLException | NamingException e) {
			throw new ServiceException("목록 구하기 실패: " + e.getMessage(), e);
			}finally {
			JdbcUtil.close(conn);	
			}
			
		}
}
