package beepbeep.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.admin.service.Admin_Hconfirm;
import beepbeep.command.CommandHandler;

public class Admin_HconfirmHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("update요청");
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		Admin_Hconfirm hConfirm = Admin_Hconfirm.getInstance();
		hConfirm.confirm(seq);
		response.sendRedirect("/beepPro/admin/admin_Hconfirm_list.do?update=success");
		return null;
	}

}
