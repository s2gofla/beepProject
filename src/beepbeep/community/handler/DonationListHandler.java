package beepbeep.community.handler;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CategoryDTO;
import beepbeep.command.CategoryService;
import beepbeep.command.CommandHandler;

import beepbeep.community.dto.DonationListView;

import beepbeep.community.service.DonationListService;

public class DonationListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("donationListHandler 요청");
		String pCurrentPage = request.getParameter("page");
		int currentPage = 1;
		if(pCurrentPage != null) {
			currentPage = Integer.parseInt(pCurrentPage);
		}
		DonationListService donationListService = DonationListService.getInstance();
		
		int searchCondition = Integer.parseInt(request.getParameter("searchCondition") == null? "1" : request.getParameter("searchCondition"));
		// 검색어
		String searchWord = request.getParameter("searchWord");

		// 검색어가 아무것도 없으면 모든것 출력?
		if(searchWord == null || searchWord.equals("")) searchWord = "*";
		
		DonationListView view = donationListService.getDonationList(searchCondition,searchWord,currentPage);
		request.setAttribute("view", view);
		request.setAttribute("searchCondition", searchCondition);
		request.setAttribute("searchWord", searchWord);

		CategoryService categoryService = CategoryService.getInstance();
		List<CategoryDTO> categoryList = categoryService.CategoryList();
		request.setAttribute("categoryList", categoryList);
		
		return "/community/donationlist";
	}

}
