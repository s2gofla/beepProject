package beepbeep.qna.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.member.dto.LoginDTO;
import beepbeep.qna.service.QuesDeleteService;

public class QuestionDeleteHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pq_seq = Integer.parseInt(request.getParameter("pq_seq"));
		String pw = request.getParameter("password");
		LoginDTO login = (LoginDTO) request.getSession().getAttribute("authUser");
		String sspw = login.getPassword();
		
		if(pw.equals(sspw)) {
			QuesDeleteService qdservice = QuesDeleteService.getInstance();
			int result = qdservice.quesDelete(pq_seq);
			// 수정 필요
			String url = "/beepPro/QnA/qna_list.do?delete_yn=y";
			response.sendRedirect(url);
			return null;
		}else {
			String url = "/beepPro/QnA/qna_view.do?pq_seq"+pq_seq;
			response.sendRedirect(url);
			return null;
		}
		
	}

}
