package beepbeep.admin.service;

import java.util.List;

import beepbeep.admin.model.Admin_ConfirmDTO;

public class Admin_Confirm_ListView {
	private int admin_confirmTotalCount;						//총 공지사항 글수
	private int currentPageNumber;					//현재 페이지 번호
	private List<Admin_ConfirmDTO> admin_confirm_List;	//공지사항List
	private int pageTotalCount;						//총 페이지수
	private int admin_confirmCountPerPage;				//한페이지에 출력할 공지사항수
	private int firstRow;										//시작
	private int endRow;
	
	
	public Admin_Confirm_ListView(int admin_confirmTotalCount, int currentPageNumber,
			List<Admin_ConfirmDTO> admin_confirm_List, int admin_confirmCountPerPage, int firstRow,
			int endRow) {
		super();
		this.admin_confirmTotalCount = admin_confirmTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.admin_confirm_List = admin_confirm_List;
		this.admin_confirmCountPerPage = admin_confirmCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		calculatePageTotalCount();
	}
	private void calculatePageTotalCount() {
		if(admin_confirmTotalCount == 0) {
			pageTotalCount = 0;
		}else {
			pageTotalCount = admin_confirmTotalCount / admin_confirmCountPerPage;
			if(admin_confirmTotalCount % admin_confirmCountPerPage > 0) {
				pageTotalCount++;
			}
		}
		
	}
	
	public int getAdmin_confirmTotalCount() {
		return admin_confirmTotalCount;
	}
	public int getAdmin_confirmCountPerPage() {
		return admin_confirmCountPerPage;
	}
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	public List<Admin_ConfirmDTO> getAdmin_confirm_List() {
		return admin_confirm_List;
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
