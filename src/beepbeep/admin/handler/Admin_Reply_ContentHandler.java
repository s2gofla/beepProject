package beepbeep.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.admin.model.Admin_ConfirmDTO;
import beepbeep.admin.model.Admin_ReportDTO;
import beepbeep.admin.service.Admin_Hconfirm_Content;
import beepbeep.admin.service.Admin_ReportReply_Content;
import beepbeep.admin.service.Admin_Report_Content;
import beepbeep.command.CommandHandler;
;

public class Admin_Reply_ContentHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int report_seq = Integer.parseInt(request.getParameter("report_seq"));
		Admin_ReportReply_Content admin_reportReply_content = new Admin_ReportReply_Content();
		Admin_ReportDTO reportRelyViewData = admin_reportReply_content.selectOne(report_seq);
		request.setAttribute("reportRelyViewData", reportRelyViewData);
		return "/admin/admin_reportReply_content";
								
	}

}
