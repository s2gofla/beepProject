package beepbeep.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.admin.service.Admin_Report_ListView;
import beepbeep.admin.service.GetAdminReportListService;
import beepbeep.command.CommandHandler;

public class Admin_ReportHandler implements CommandHandler{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("list.do요청 admin_ListHandler.process까지옴");
		String pCurrentPage = request.getParameter("page");
		int currentPage =1;
		if(pCurrentPage != null) {
			currentPage = Integer.parseInt(pCurrentPage);
		}
		 	
		GetAdminReportListService admin_ReportListService = GetAdminReportListService.getInstance();
		Admin_Report_ListView viewData = admin_ReportListService.getAdmin_ReportList(currentPage);
		request.setAttribute("viewData", viewData);
		return "/admin/admin_report";
	}

}
