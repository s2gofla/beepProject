package beepbeep.member.handler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;



public class ShowChangePwdHandler implements CommandHandler{



	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String id =request.getParameter("id");
			request.setAttribute("id", id);
			
			return "/member/m15_showChangePwd";
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}