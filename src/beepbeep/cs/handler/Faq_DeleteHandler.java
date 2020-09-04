package beepbeep.cs.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.cs.service.AskDelete;
import beepbeep.cs.service.FaqDelete;


public class Faq_DeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("delete요청");
		int faq_list_seq = Integer.parseInt(request.getParameter("faq_list_seq"));
	
		FaqDelete faqDelete = FaqDelete.getInstance();
		faqDelete.delete(faq_list_seq);
		
		response.sendRedirect("/beepPro/cs/cs_faq_list.do?delete=success");
		return null;
	}

}
