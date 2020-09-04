package beepbeep.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beepbeep.admin.service.Admin_HconfirmDelete;

import beepbeep.command.CommandHandler;

public class Admin_Hconfirm_DeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("delete요청");
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		 Admin_HconfirmDelete HconfirmDelete = Admin_HconfirmDelete.getInstance();
		 HconfirmDelete.delete(seq);
		//경로수
		response.sendRedirect("/beepPro/admin/admin_Hconfirm_list.do?delete=success");
		return null;
	}
	
}
