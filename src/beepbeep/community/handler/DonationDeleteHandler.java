package beepbeep.community.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.community.service.DiseaseDeleteService;
import beepbeep.community.service.DonationDeleteService;

public class DonationDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if( request.getMethod().equalsIgnoreCase("GET") ) {
		    return "/community/delete";
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			int d_seq = Integer.parseInt(request.getParameter("d_seq"));
			String pPwd = request.getParameter("passwd");
			DonationDeleteService donationDeleteService = DonationDeleteService.getInstance();
			
			int result = donationDeleteService.donationdelete(d_seq, pPwd);

			if(result==1) {
				String param="?ton=success";
				pageRedirect(request, response, param);
	            
	         }else {
	        	 request.setAttribute("result", result);
	        	 return "/community/delete";
	         }
	         return null;		
		}else {
	         response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	         return null;
	      }

	}

	private void pageRedirect(HttpServletRequest request, HttpServletResponse response, String param) throws IOException {
		String page = "/beepPro/community/donation_list.do"+param;
		response.sendRedirect(page);
	}

}
