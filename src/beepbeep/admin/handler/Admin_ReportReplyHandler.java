package beepbeep.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.admin.service.Admin_ReportReply_ListView;
import beepbeep.admin.service.Admin_Report_ListView;
import beepbeep.admin.service.GetAdminReportListService;
import beepbeep.admin.service.GetAdminReportReplyListService;
import beepbeep.command.CommandHandler;

public class Admin_ReportReplyHandler implements CommandHandler{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("reply list.do요청 admin_ListHandler.process까지옴");
		String pCurrentPage = request.getParameter("page");
		int currentPage =1;
		if(pCurrentPage != null) {
			currentPage = Integer.parseInt(pCurrentPage);
		}
		 	
		GetAdminReportReplyListService admin_ReportReplyListService = GetAdminReportReplyListService.getInstance();
		Admin_ReportReply_ListView replyviewData = admin_ReportReplyListService.getAdmin_ReportReplyList(currentPage);
		request.setAttribute("replyviewData", replyviewData);
		return "/admin/admin_ReportReply";
	}

}
