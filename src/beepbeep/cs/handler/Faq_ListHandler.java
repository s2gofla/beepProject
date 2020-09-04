package beepbeep.cs.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.cs.service.FaqListView;
import beepbeep.cs.service.GetFaqListService;

public class Faq_ListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("faq.do요청 핸들러까지옴");
		String pCurrentPage = request.getParameter("page");
		int currentPage = 1;
		if(pCurrentPage != null) {
			currentPage = Integer.parseInt(pCurrentPage);
		}
		GetFaqListService faqListService = GetFaqListService.getInstance();
		FaqListView viewData = faqListService.getFaqList(currentPage);
		request.setAttribute("viewData", viewData);
		return "/cs/cs_faq";
	}

}
