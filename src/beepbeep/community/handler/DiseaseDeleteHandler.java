package beepbeep.community.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.community.service.DiseaseDeleteService;
import beepbeep.community.service.FreeDeleteService;

public class DiseaseDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if( request.getMethod().equalsIgnoreCase("GET") ) {
		    return "/community/delete";
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			int dtip_seq = Integer.parseInt(request.getParameter("dtip_seq"));
			String pPwd = request.getParameter("passwd");
			DiseaseDeleteService diseaseDeleteService = DiseaseDeleteService.getInstance();
			
			int result = diseaseDeleteService.diseasedelete(dtip_seq, pPwd);

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
		String page = "/beepPro/community/disease_list.do"+param;
		response.sendRedirect(page);
	}

}
