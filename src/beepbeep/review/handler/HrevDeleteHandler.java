package beepbeep.review.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.review.service.HinfoDetailService;
import beepbeep.review.service.HrevDeleteService;
import beepbeep.review.service.HrevUpdateService;

public class HrevDeleteHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("delete 호출!");
		
		int h_review_code = Integer.parseInt(request.getParameter("h_review_code"));
		int h_code = Integer.parseInt(request.getParameter("h_code"));
		
		String h_name = request.getParameter("h_name");
		System.out.println(h_name);
		HrevDeleteService service = HrevDeleteService.getInstance();
		
		int result = service.delete(h_review_code);
		
		if (result == 1) {
			h_name = URLEncoder.encode(h_name);
			response.sendRedirect("../review/hreview_detail.do?h_code="+h_code+"&h_name="+h_name+"&delete=suscess");
		}
		
		return null;
	}

}
