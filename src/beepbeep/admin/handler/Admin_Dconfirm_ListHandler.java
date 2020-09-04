package beepbeep.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.admin.service.Admin_Confirm_ListView;
import beepbeep.admin.service.GetAdminConfrimListService;

import beepbeep.command.CommandHandler;

public class Admin_Dconfirm_ListHandler implements CommandHandler{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("list.do요청 admin_ListHandler.process까지옴");
		String pCurrentPage = request.getParameter("page");
		int currentPage =1;
		if(pCurrentPage != null) {
			currentPage = Integer.parseInt(pCurrentPage);
		}
	
		GetAdminConfrimListService admin_confirmListService = GetAdminConfrimListService.getInstance();
	
		Admin_Confirm_ListView viewData = admin_confirmListService.getAdmin_ConfrimList(currentPage);
		
		
			request.setAttribute("viewData", viewData);
		
		
		return "/admin/admin_confirm";
	}

}
