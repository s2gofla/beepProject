package beepbeep.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.admin.service.Admin_ReportDelete;
import beepbeep.command.CommandHandler;
import beepbeep.cs.service.NoticeDelete;

public class Admin_Report_DeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("delete요청");
		int report_seq = Integer.parseInt(request.getParameter("report_seq"));
		
		Admin_ReportDelete reportDelete = Admin_ReportDelete.getInstance();
		reportDelete.delete(report_seq);
		//경로수
		response.sendRedirect("/beepPro/cs/list.do?delete=success");
		return null;
	}

}
