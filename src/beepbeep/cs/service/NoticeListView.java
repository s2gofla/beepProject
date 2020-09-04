package beepbeep.cs.service;

import java.util.List;

import beepbeep.cs.model.Cs_NoticeDTO;

public class NoticeListView {
	private int noticeTotalCount;						//총 공지사항 글수
	private int currentPageNumber;					//현재 페이지 번호
	private List<Cs_NoticeDTO> noticeList;	//공지사항List
	private int pageTotalCount;						//총 페이지수
	private int noticeCountPerPage;				//한페이지에 출력할 공지사항수
	private int firstRow;										//시작
	private int endRow;											//끝

	//생성자	

	public NoticeListView(int noticeTotalCount, int currentPageNumber, List<Cs_NoticeDTO> noticeList,
			int noticeCountPerPage, int firstRow, int endRow) {
		super();
		this.noticeTotalCount = noticeTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.noticeList = noticeList;
		this.noticeCountPerPage = noticeCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		//총페이지수 계산하는함수
		calculatePageTotalCount();
	}

	private void calculatePageTotalCount() {
		if(noticeTotalCount == 0) {
			pageTotalCount = 0;
		}else {
			pageTotalCount = noticeTotalCount / noticeCountPerPage;
			if(noticeTotalCount % noticeCountPerPage > 0) {
				pageTotalCount++;
			}
		}
		
	}

	public int getNoticeTotalCount() {
		return noticeTotalCount;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public List<Cs_NoticeDTO> getNoticeList() {
		return noticeList;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public int getNoticeCountPerPage() {
		return noticeCountPerPage;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public int getEndRow() {
		return endRow;
	}
	public boolean isEmpty() {
		return noticeTotalCount == 0;
	}
}



