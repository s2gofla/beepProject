package beepbeep.qna.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beepbeep.command.CategoryDTO;
import beepbeep.command.CategoryService;
import beepbeep.command.CommandHandler;
import beepbeep.member.dto.LoginDTO;
import beepbeep.qna.dto.QnaContentView;
import beepbeep.qna.dto.QnaListDTO;
import beepbeep.qna.service.QnaContentService;
import beepbeep.qna.service.QnaWriteService;
import beepbeep.qna.service.QuestionEditServie;

public class QuestionEditHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			CategoryService categoryservice = CategoryService.getInstance();
			List<CategoryDTO> categoryList= categoryservice.CategoryList();
			request.setAttribute("categoryList", categoryList);
			
			int pq_seq = Integer.parseInt(request.getParameter("pq_seq"));
			QuestionEditServie qeservice = QuestionEditServie.getInstance();
			QnaListDTO dto = qeservice.selectContent(pq_seq);
			
			request.setAttribute("contentDTO", dto);
			request.setAttribute("pq_seq", pq_seq);
			
			return "/QnA/qna_write";
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			/* qnaw-select=5&qna-twrite=ㅁㅇ&qna-cwrite=ㄻㄹ */
			System.out.println("EditHandler Post");
			String title = request.getParameter("qna-twrite");
			String contents = request.getParameter("qna-cwrite");
			int pq_seq = Integer.parseInt(request.getParameter("pq_seq"));
			
			QuestionEditServie qeservice = QuestionEditServie.getInstance();
			int result = qeservice.qnaUpdate(pq_seq, title, contents);
			
			String url="/beepPro/QnA/qna_view.do?pq_seq="+pq_seq;
			if(result==1) {
				String param = "&edit=success";
				redirectPage(url, param, response);
			}else {
				String param = "&edit=fail";
				redirectPage(url, param, response);
			}
			return null;
			
		} else {
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		
	}
	public void redirectPage(String url, String param, HttpServletResponse response) throws IOException {
		String viewPage = url+param;
		response.sendRedirect(viewPage);
	}

}
