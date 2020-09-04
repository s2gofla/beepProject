package beepbeep.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.admin.model.Admin_ConfirmDTO;
import beepbeep.admin.service.Admin_Hconfirm_Content;
import beepbeep.command.CommandHandler;
;

public class Admin_Hconfirm_ContentHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int seq = Integer.parseInt(request.getParameter("seq"));
		Admin_Hconfirm_Content admin_hconfirm_content = new Admin_Hconfirm_Content();
		Admin_ConfirmDTO hconfirmViewData = admin_hconfirm_content.selectOne(seq);
		request.setAttribute("hconfirmViewData", hconfirmViewData);
		request.setAttribute("seq", seq);
		return "/admin/admin_hconfirm_content";
	}

}
