package beepbeep.admin.service;

import java.util.List;

import beepbeep.admin.model.Admin_ReportDTO;

public class Admin_Report_ListView {
	private int admin_rportTotalCount;						//총 공지사항 글수
	private int currentPageNumber;					//현재 페이지 번호
	private List<Admin_ReportDTO> admin_report_List;	//공지사항List
	private int pageTotalCount;						//총 페이지수
	private int admin_reportCountPerPage;				//한페이지에 출력할 공지사항수
	private int firstRow;										//시작
	private int endRow;
	
	
	public Admin_Report_ListView(int admin_rportTotalCount, int currentPageNumber,
			List<Admin_ReportDTO> admin_report_List, int admin_reportCountPerPage, int firstRow,
			int endRow) {
		super();
		this.admin_rportTotalCount = admin_rportTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.admin_report_List = admin_report_List;
		
		this.admin_reportCountPerPage = admin_reportCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		calculatePageTotalCount();
	}
	private void calculatePageTotalCount() {
		if(admin_rportTotalCount == 0) {
			pageTotalCount = 0;
		}else {
			pageTotalCount = admin_rportTotalCount / admin_reportCountPerPage;
			if(admin_rportTotalCount % admin_reportCountPerPage > 0) {
				pageTotalCount++;
			}
		}
		
	}
	

	
	public int getAdmin_rportTotalCount() {
		return admin_rportTotalCount;
	}
	public int getAdmin_reportCountPerPage() {
		return admin_reportCountPerPage;
	}
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	
	public List<Admin_ReportDTO> getAdmin_report_List() {
		return admin_report_List;
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
