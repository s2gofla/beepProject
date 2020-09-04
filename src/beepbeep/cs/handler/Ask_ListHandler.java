package beepbeep.cs.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.cs.service.AskListView;
import beepbeep.cs.service.GetAskListService;

public class Ask_ListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ask.do 요청 핸들러까지옴");
		String pCurrentPage = request.getParameter("page");
		int currentPage = 1;
		if(pCurrentPage != null) {
			currentPage = Integer.parseInt(pCurrentPage);
		}
		GetAskListService askListService = GetAskListService.getInstance();
		AskListView viewData = askListService.getAskList(currentPage);
		request.setAttribute("viewData", viewData);
		return "/cs/cs_ask";
	}
	
} 
