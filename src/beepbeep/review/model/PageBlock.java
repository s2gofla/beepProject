package beepbeep.review.model;

public class PageBlock {

	private int currentPage = 1;
	private int numberPerPage = 8;
	private int numberOfPageBlocks = 3;
	private int start, end;
	private boolean prev, next;
	
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getNumberPerPage() {
		return numberPerPage;
	}
	public void setNumberPerPage(int numberPerPage) {
		this.numberPerPage = numberPerPage;
	}
	public int getNumberOfPageBlocks() {
		return numberOfPageBlocks;
	}
	public void setNumberOfPageBlocks(int numberOfPageBlocks) {
		this.numberOfPageBlocks = numberOfPageBlocks;
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
	
	
	
	
}
