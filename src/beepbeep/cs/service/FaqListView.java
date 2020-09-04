package beepbeep.cs.service;

import java.util.List;

import beepbeep.cs.model.Cs_FaqDTO;

public class FaqListView {
	private int faqTotalCount;					//총페이지수
	private int currentPageNumber;			//현재페이지번호
	private List<Cs_FaqDTO> faqList;		//FAQ리스트
	private int pageTotalCount;				//전체페이지 수
	private int faqCountPerPage;				//한페이지에 출력할 faq수
	private int firstRow;								//시작
	private int endRow;									//끝
	public FaqListView(int faqTotalCount, int currentPageNumber, List<Cs_FaqDTO> faqList,
			int faqCountPerPage, int firstRow, int endRow) {
		super();
		this.faqTotalCount = faqTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.faqList = faqList;
		this.faqCountPerPage = faqCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		
		calulatePageTotalCount();
	}
	private void calulatePageTotalCount() {
		if(faqTotalCount == 0) {
			pageTotalCount = 0;
		}else {
			pageTotalCount = faqTotalCount / faqCountPerPage;
			if(faqTotalCount % faqCountPerPage > 0) {
				pageTotalCount++;
			}
		}
		
	}
	public int getFaqTotalCount() {
		return faqTotalCount;
	}
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	public List<Cs_FaqDTO> getFaqList() {
		return faqList;
	}
	public int getPageTotalCount() {
		return pageTotalCount;
	}
	public int getFaqCountPerPage() {
		return faqCountPerPage;
	}
	public int getFirstRow() {
		return firstRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public boolean isEmpty() {
		return faqTotalCount == 0;
	}
	

}


