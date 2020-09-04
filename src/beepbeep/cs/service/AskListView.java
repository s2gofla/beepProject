package beepbeep.cs.service;

import java.util.List;

import beepbeep.cs.model.Cs_AskDTO;

public class AskListView {
	private int askTotalCount;						//총페이지수
	private int currentPageNumber;				//현재페이지번호
	private List<Cs_AskDTO> askList;			//질문내역 리스트
	private int pageTotalCount;					//전체페이지수
	private int askCountPerPage;					//페이지한장당 나올게시글수
	private int firstRow;									//첫
	private int endRow;										//끝
	
	
	public int getAskTotalCount() {
		return askTotalCount;
	}
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	public List<Cs_AskDTO> getAskList() {
		return askList;
	}
	public int getPageTotalCount() {
		return pageTotalCount;
	}
	public int getAskCountPerPage() {
		return askCountPerPage;
	}
	public int getFirstRow() {
		return firstRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public boolean isEmpty() {
		return askTotalCount ==0;
	}
	
	public AskListView(int aksTotalCount, int currentPageNumber, List<Cs_AskDTO> askList, 
			int askCountPerPage, int firstRow, int endRow) {
		super();
		this.askTotalCount = aksTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.askList = askList;
		this.askCountPerPage = askCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		calulatePageTotalCount();
	}
	private void calulatePageTotalCount() {
	if(askTotalCount ==0) {
		pageTotalCount = 0;
	}else {
		pageTotalCount = askTotalCount / askCountPerPage;
		if(askTotalCount % askCountPerPage > 0) {
			pageTotalCount++;
		}
	}
		
	}
	
	
}
