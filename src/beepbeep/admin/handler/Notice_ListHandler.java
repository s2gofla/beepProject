package beepbeep.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.cs.service.GetNoticeListService;
import beepbeep.cs.service.NoticeListView;

public class Notice_ListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("list.do요청 Notice_ListHandler.process까지옴");
		String pCurrentPage = request.getParameter("page");
		int currentPage =1;
		if(pCurrentPage != null) {
			currentPage = Integer.parseInt(pCurrentPage);
		}
		
		GetNoticeListService noticeListService = GetNoticeListService.getInstance();
		NoticeListView viewData = noticeListService.getNoticeList(currentPage);
		request.setAttribute("viewData", viewData);
		return "/admin/admin_notice";
	}

	

}
