package beepbeep.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beepbeep.admin.service.Admin_Dconfirm;
import beepbeep.command.CommandHandler;

public class Admin_DconfirmHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("update요청");
		int c_seq = Integer.parseInt(request.getParameter("c_seq"));
		 Admin_Dconfirm dConfirm = Admin_Dconfirm.getInstance();
		 dConfirm.confirm(c_seq);
		response.sendRedirect("/beepPro/admin/admin_confirm_list.do?update=success");
		return null;
	}

}
