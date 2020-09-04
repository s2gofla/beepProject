package beepbeep.review.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.review.service.HrevDeleteService;

public class MreDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("delete 호출");
		
		int m_review_code = Integer.parseInt(request.getParameter("m_review_code"));
		int m_code = Integer.parseInt(request.getParameter("m_code"));
		MrevDeleteService service = MrevDeleteService.getInstance();
		
		int result = service.delete(m_review_code);
		
		if (result == 1) {
			
			response.sendRedirect("../review/mreview_detail.do?m_code="+m_code+"&delete=suscess");
		}
		
		return null;
	}

}
