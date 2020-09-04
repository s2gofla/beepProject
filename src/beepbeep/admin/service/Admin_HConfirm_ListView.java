package beepbeep.admin.service;

import java.util.List;

import beepbeep.admin.model.Admin_ConfirmDTO;

public class Admin_HConfirm_ListView {
	private int admin_MconfirmTotalCount;						//총 공지사항 글수
	private int currentPageNumber;					//현재 페이지 번호
	private List<Admin_ConfirmDTO> admin_Hconfirm_List;	//공지사항List
	private int pageTotalCount;						//총 페이지수
	private int admin_MconfirmCountPerPage;				//한페이지에 출력할 공지사항수
	private int firstRow;										//시작
	private int endRow;
	
	
	public Admin_HConfirm_ListView(int admin_MconfirmTotalCount, int currentPageNumber,
			List<Admin_ConfirmDTO> admin_Hconfirm_List, int admin_MconfirmCountPerPage, int firstRow,
			int endRow) {                       
		super();
		this.admin_MconfirmTotalCount = admin_MconfirmTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.admin_Hconfirm_List = admin_Hconfirm_List;
		this.admin_MconfirmCountPerPage = admin_MconfirmCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		calculatePageTotalCount();
	}
	private void calculatePageTotalCount() {
		if(admin_MconfirmTotalCount == 0) {
			pageTotalCount = 0;
		}else {
			pageTotalCount = admin_MconfirmTotalCount / admin_MconfirmCountPerPage;
			if(admin_MconfirmTotalCount % admin_MconfirmCountPerPage > 0) {
				pageTotalCount++;
			}
		}
		
	}
	
	
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public int getAdmin_MconfirmTotalCount() {
		return admin_MconfirmTotalCount;
	}
	
	public List<Admin_ConfirmDTO> getAdmin_Hconfirm_List() {
		return admin_Hconfirm_List;
	}
	public int getAdmin_MconfirmCountPerPage() {
		return admin_MconfirmCountPerPage;
	}
	public int getPageTotalCount() {
		return pageTotalCount;
	}
	
	public int getFirstRow() {
		return firstRow;
	}
	public int getEndRow() {
		return endRow;
	}
	
	
}
