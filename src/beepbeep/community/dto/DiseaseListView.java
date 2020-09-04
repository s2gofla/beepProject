package beepbeep.community.dto;

import java.util.List;

public class DiseaseListView {
	private int messageTotalCount; // 총 방명록 글 수
	private int currentPageNumber; // 현재 페이지 번호
	private List<DiseaseListDTO> messageList; // 방명록 List
	private int pageTotalCount; // 홈페이지 수
	private int messageCountPerPage; // 한 페이지에 출력할 방명록 글 수
	private int firstRow; // 시작
	private int endRow; // 끝
	
	private int numberOfPageBlocks;
	private boolean prev, next;
	
	private int pageBlockStart;
	private int pageBlockEnd;
	
	
	
	public int getNumberOfPageBlocks() {
		return numberOfPageBlocks;
	}

	public void setNumberOfPageBlocks(int numberOfPageBlocks) {
		this.numberOfPageBlocks = numberOfPageBlocks;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getPageBlockStart() {
		return pageBlockStart;
	}

	public void setPageBlockStart(int pageBlockStart) {
		this.pageBlockStart = pageBlockStart;
	}

	public int getPageBlockEnd() {
		return pageBlockEnd;
	}

	public void setPageBlockEnd(int pageBlockEnd) {
		this.pageBlockEnd = pageBlockEnd;
	}

	public DiseaseListView(int messageTotalCount, int currentPageNumber, List<DiseaseListDTO> messageList,
			int messageCountPerPage, int firstRow, int endRow, int pageBlockStart,int pageBlockEnd) {
		super();
		this.messageTotalCount = messageTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.messageList = messageList;
		this.messageCountPerPage = messageCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;

		pageBlockStart = 1; 
		pageBlockEnd=10; 
		int numberOfPageBlocks = 10;
		if (messageTotalCount == 0) { // 총 게시글이 0 이면 페이지도 0
			pageTotalCount = 0;
		} else {
			pageTotalCount = messageTotalCount / messageCountPerPage; // 페이지 수 = 총 글 수/한 페이지 출력할 글 수
			if (messageTotalCount % messageCountPerPage > 0) { // 총 글 수/한 페이지 출력할 글 수 (나머지가 0 이상이면 페이지 수 +1)
				pageTotalCount++;
			}
		}
		
		pageBlockStart = (currentPageNumber - 1)/numberOfPageBlocks*numberOfPageBlocks+1; // 1 	/ 11 
		pageBlockEnd = pageBlockStart + numberOfPageBlocks-1; // start + 9 => 			  // 10 / 20 / 30 / 40
		if( pageBlockEnd >  pageTotalCount ) pageBlockEnd = pageTotalCount; // 마지막 번호에 총 페이지 수 대입

		setPageBlockStart(pageBlockStart);
		setPageBlockEnd(pageBlockEnd);

		// 총 페이지수를 계산하는 메서드
		calculatePageTotalCount();
	}

	private void calculatePageTotalCount() {
		if (messageTotalCount == 0) {
			pageTotalCount = 0;
		} else {
			pageTotalCount = messageTotalCount / messageCountPerPage;
			if (messageTotalCount % messageCountPerPage > 0) {
				pageTotalCount++;
			}
		}
		
		setPrev(pageBlockStart != 1? true : false);
		setNext(pageBlockEnd != pageTotalCount ? true : false);
		
	}

	public int getMessageTotalCount() {
		return messageTotalCount;
	}

	public void setMessageTotalCount(int messageTotalCount) {
		this.messageTotalCount = messageTotalCount;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public List<DiseaseListDTO> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<DiseaseListDTO> messageList) {
		this.messageList = messageList;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	public int getMessageCountPerPage() {
		return messageCountPerPage;
	}

	public void setMessageCountPerPage(int messageCountPerPage) {
		this.messageCountPerPage = messageCountPerPage;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	
	
}
