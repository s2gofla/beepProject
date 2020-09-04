package beepbeep.qna.dto;

import java.util.List;

public class QnaListView {
	private int qnaTotalCount;   // 총 방명록 글 수
	private int currentPageNumber;   // 현재 페이지 번호
	private List<QnaListDTO> qnaList;

	// 방명록 List
	private int pageTotalCount;  // 총페이지 수
	private int qnaCountPerPage;  // 한 페이지에 출력할 방명록 글 수
	private int firstRow;  // 게시물 시작
	private int endRow;    // 게시물 끝
	private int numberOfpageBlocks = 10;
	private int start, end;
	private boolean prev, next;

	public QnaListView(int qnaTotalCount, int currentPageNumber, List<QnaListDTO> qnaList,
			int qnaCountPerPage, int firstRow, int endRow) {
		this.qnaTotalCount = qnaTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.qnaList = qnaList;
		this.qnaCountPerPage = qnaCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		
		calculatePageTotalCount();
	}
	public int getNumberOfpageBlocks() {
		return numberOfpageBlocks;
	}
	public void setNumberOfpageBlocks(int numberOfpageBlocks) {
		this.numberOfpageBlocks = numberOfpageBlocks;
	}
	public int getStart() {
		return start;
	}


	public void setStart(int start) {
		this.start = start;
	}


	public int getEnd() {
		return end;
	}


	public void setEnd(int end) {
		this.end = end;
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

	public int getQnaTotalCount() {
		return qnaTotalCount;
	}


	public void setQnaTotalCount(int qnaTotalCount) {
		this.qnaTotalCount = qnaTotalCount;
	}


	public int getCurrentPageNumber() {
		return currentPageNumber;
	}


	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}


	public List<QnaListDTO> getQnaList() {
		return qnaList;
	}


	public void setQnaList(List<QnaListDTO> qnaList) {
		this.qnaList = qnaList;
	}


	public int getPageTotalCount() {
		return pageTotalCount;
	}


	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}


	public int getQnaCountPerPage() {
		return qnaCountPerPage;
	}


	public void setQnaCountPerPage(int qnaCountPerPage) {
		this.qnaCountPerPage = qnaCountPerPage;
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


	private void calculatePageTotalCount() {
		if (qnaTotalCount == 0) {
			pageTotalCount = 0;
		} else {
			pageTotalCount = qnaTotalCount / qnaCountPerPage;
			if (qnaTotalCount % qnaCountPerPage > 0) {
				pageTotalCount++;
			}
		}
	}
}