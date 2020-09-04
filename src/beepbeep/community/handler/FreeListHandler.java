package beepbeep.community.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.community.dto.FreeListView;
import beepbeep.community.dto.SubDTO;
import beepbeep.community.service.FreeListService;
import beepbeep.community.service.SubService;

public class FreeListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("freeListHandler 요청");
		String pCurrentPage = request.getParameter("page"); // ?page 뒤에 숫자
		int currentPage = 1;
		if(pCurrentPage != null) {
			currentPage = Integer.parseInt(pCurrentPage);
		}
		FreeListService freeListService = FreeListService.getInstance();
		
		//FreeListView view = freeListService.getFreeList(currentPage);
		//request.setAttribute("view", view);

		
		// 검색
		// 제목, 작성자, 내용 등등~
		// null 이면 1(제목)
		int searchCondition = Integer.parseInt(request.getParameter("searchCondition") == null? "1" : request.getParameter("searchCondition"));
		// 검색어
		String searchWord = request.getParameter("searchWord");

		// 검색어가 아무것도 없으면 모든것 출력?
		if(searchWord == null || searchWord.equals("")) searchWord = "*";
		
		FreeListView view = freeListService.getSearchList(searchCondition,searchWord, currentPage);
		request.setAttribute("view", view);
		request.setAttribute("searchCondition", searchCondition);
		request.setAttribute("searchWord", searchWord);
		
		
		// sub
		SubService subservice = SubService.getInstance();
		List<SubDTO> subList = subservice.getSubList();
		request.setAttribute("subList", subList);
		
		return "/community/freelist";
		
	}

}
