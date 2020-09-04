package beepbeep.community.handler;

import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.community.service.FreeWriteService;

public class FreeWriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("freeWriteHandler 요청");
		if( request.getMethod().equalsIgnoreCase("GET") ) {
		    return "/community/freewrite";
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			String id = request.getParameter("id");
			String title = request.getParameter("writetitle"); // 입력된거
			String contents = request.getParameter("writecontent");
			
			String hashtag = request.getParameter("writehashtag");
			
			FreeWriteService freeWriteService = FreeWriteService.getInstance();
			// 됐는지 숫자로 받아옴
			int result = freeWriteService.freewrite(id, title, hashtag, contents);
			String viewPage = "/beepPro/community/free_list.do";
			if(result==1) {
	            response.sendRedirect(viewPage+"?on=success");
	         }else {
	            response.sendRedirect(viewPage+"?on=fail");
	         }
	         RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
	         dispatcher.forward(request, response);
	         return "";
			
		}else {
	         response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	         return null;
	      }
	}



}
