package beepbeep.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.admin.service.Admin_ConfirmDelete;
import beepbeep.admin.service.Admin_HconfirmDelete;
import beepbeep.admin.service.Admin_ReportDelete;
import beepbeep.command.CommandHandler;

public class Admin_confirm_DeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("delete요청");
		int c_seq = Integer.parseInt(request.getParameter("c_seq"));
		
		 Admin_ConfirmDelete confirmDelete = Admin_ConfirmDelete.getInstance();
		 confirmDelete.delete(c_seq);
		//경로수
		response.sendRedirect("/beepPro/admin/admin_confirm_list.do?delete=success");
		return null;
	}
	
}
