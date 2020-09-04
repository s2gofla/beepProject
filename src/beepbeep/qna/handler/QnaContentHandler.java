package beepbeep.qna.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beepbeep.command.CommandHandler;
import beepbeep.member.dto.LoginDTO;
import beepbeep.qna.dto.QnaContentView;
import beepbeep.qna.service.QnaContentService;

public class QnaContentHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnaContentHandler");
		int pq_seq = Integer.parseInt(request.getParameter("pq_seq"));
		
		HttpSession session = request.getSession();
		LoginDTO dto = (LoginDTO) session.getAttribute("authUser");
		String sid= dto==null?"NONE": dto.getId() ;
		QnaContentService qnacontentService = QnaContentService.getInstance();
		QnaContentView view  = qnacontentService.getQnaContent(sid, pq_seq);
		String edit = request.getParameter("edit");
		request.setAttribute("edit", edit);
		request.setAttribute("contentview", view);
		request.setAttribute("reportComment", request.getParameter("reportComment"));
		request.setAttribute("reportPost", request.getParameter("reportPost"));
		return "/QnA/qna_view";
	}
}
