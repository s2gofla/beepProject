package beepbeep.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beepbeep.admin.service.Admin_HConfirm_ListView;
import beepbeep.admin.service.GetAdminHConfrimListService;
import beepbeep.command.CommandHandler;

public class Admin_Hconfirm_ListHandler implements CommandHandler{
	            
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("list.do요청 admin_ListHandler.process까지옴");
		String pCurrentPage = request.getParameter("page");
		int currentPage =1;
		if(pCurrentPage != null) {
			currentPage = Integer.parseInt(pCurrentPage);
		}
	
		GetAdminHConfrimListService admin_HconfirmListService = GetAdminHConfrimListService.getInstance();
	
		Admin_HConfirm_ListView HviewData = admin_HconfirmListService.getAdmin_HConfrimList(currentPage);
		
			request.setAttribute("HviewData", HviewData);
		
		
		return "/admin/admin_Hconfirm";
	}

}
