package beepbeep.admin.service;

import java.util.List;

import beepbeep.admin.model.Admin_ReportDTO;

public class Admin_ReportReply_ListView {
	private int admin_rportReplyTotalCount;						//총 공지사항 글수
	private int currentPageNumber;					//현재 페이지 번호
	private List<Admin_ReportDTO> admin_reportReply_List;	//공지사항List
	private int pageTotalCount;						//총 페이지수
	private int admin_reportReplyCountPerPage;				//한페이지에 출력할 공지사항수
	private int firstRow;										//시작
	private int endRow;
	
	
	public Admin_ReportReply_ListView(int admin_rportReplyTotalCount, int currentPageNumber,
			List<Admin_ReportDTO> admin_reportReply_List, int admin_reportReplyCountPerPage, int firstRow,
			int endRow) {
		super();
		this.admin_rportReplyTotalCount = admin_rportReplyTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.admin_reportReply_List = admin_reportReply_List;
		this.admin_reportReplyCountPerPage = admin_reportReplyCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		calculatePageTotalCount();
	}
	private void calculatePageTotalCount() {
		if(admin_rportReplyTotalCount == 0) {
			pageTotalCount = 0;
		}else {
			pageTotalCount = admin_rportReplyTotalCount / admin_reportReplyCountPerPage;
			if(admin_rportReplyTotalCount % admin_reportReplyCountPerPage > 0) {
				pageTotalCount++;
			}
		}
		
	}
	

	
	
	public int getAdmin_rportReplyTotalCount() {
		return admin_rportReplyTotalCount;
	}
	public List<Admin_ReportDTO> getAdmin_reportReply_List() {
		return admin_reportReply_List;
	}
	public int getAdmin_reportReplyCountPerPage() {
		return admin_reportReplyCountPerPage;
	}
	public int getCurrentPageNumber() {
		return currentPageNumber;
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
