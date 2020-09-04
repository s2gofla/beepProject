package beepbeep.cs.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.cs.service.AskDelete;


public class Ask_DeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("delete요청");
		int qna_seq = Integer.parseInt(request.getParameter("qna_seq"));
		
		AskDelete askDelete = AskDelete.getInstance();
		askDelete.delete(qna_seq);
		
		response.sendRedirect("/beepPro/cs/cs_ask_list.do?delete=success");
		return null;
	}

}
