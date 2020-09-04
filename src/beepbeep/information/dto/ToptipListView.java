package beepbeep.information.dto;

import java.util.List;
public class ToptipListView {

	 
		private int TotalCount;   // 총 방명록 글 수
		private int currentPageNumber;   // 현재 페이지 번호
		private List<ToptipListDTO> List;

		// 방명록 List
		private int pageTotalCount;  // 총페이지 수
		private int CountPerPage;  // 한 페이지에 출력할 방명록 글 수
		private int firstRow;  // 게시물 시작
		private int endRow;    // 게시물 끝
		private int numberOfpageBlocks = 10;
		private int start, end;
		private boolean prev, next;

		public ToptipListView(int totalCount, int currentPageNumber, java.util.List<ToptipListDTO> list, int countPerPage,
				int firstRow, int endRow) {
			super();
			TotalCount = totalCount;
			this.currentPageNumber = currentPageNumber;
			List = list;
			CountPerPage = countPerPage;
			this.firstRow = firstRow;
			this.endRow = endRow;

			calculatePageTotalCount();
		}

		public int getTotalCount() {
			return TotalCount;
		}
		public void setTotalCount(int totalCount) {
			TotalCount = totalCount;
		}
		public int getCurrentPageNumber() {
			return currentPageNumber;
		}
		public void setCurrentPageNumber(int currentPageNumber) {
			this.currentPageNumber = currentPageNumber;
		}
		public List<ToptipListDTO> getList() {
			return List;
		}
		public void setList(List<ToptipListDTO> list) {
			List = list;
		}
		public int getPageTotalCount() {
			return pageTotalCount;
		}
		public void setPageTotalCount(int pageTotalCount) {
			this.pageTotalCount = pageTotalCount;
		}
		public int getCountPerPage() {
			return CountPerPage;
		}
		public void setCountPerPage(int countPerPage) {
			CountPerPage = countPerPage;
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
		private void calculatePageTotalCount() {
			if (TotalCount == 0) {
				pageTotalCount = 0;
			} else {
				pageTotalCount = TotalCount / CountPerPage;
				if (TotalCount % CountPerPage > 0) {
					pageTotalCount++;
				}
			}
		}
	}