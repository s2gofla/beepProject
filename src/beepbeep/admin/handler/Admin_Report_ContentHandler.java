package beepbeep.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.admin.model.Admin_ReportDTO;
import beepbeep.admin.service.Admin_Report_Content;
import beepbeep.command.CommandHandler;

public class Admin_Report_ContentHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int report_seq = Integer.parseInt(request.getParameter("report_seq"));
		Admin_Report_Content admin_report_content = new Admin_Report_Content();
		Admin_ReportDTO reportViewData = admin_report_content.selectOne(report_seq);
		request.setAttribute("reportViewData", reportViewData);
		return "/admin/admin_report_content";
		                             
	}

}
