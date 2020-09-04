package beepbeep.qna.handler;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beepbeep.command.CategoryDTO;
import beepbeep.command.CategoryService;
import beepbeep.command.CommandHandler;
import beepbeep.member.dto.LoginDTO;
import beepbeep.qna.service.QnaWriteService;

public class QnaWriteHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			CategoryService categoryservice = CategoryService.getInstance();
			List<CategoryDTO> categoryList= categoryservice.CategoryList();
			request.setAttribute("categoryList", categoryList);
			
			return "/QnA/qna_write";
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			/* qnaw-select=5&qna-twrite=ㅁㅇ&qna-cwrite=ㄻㄹ */
			System.out.println("WirteHandler Post");
			int select = Integer.parseInt(request.getParameter("qnaw-select"));
			String title = request.getParameter("qna-twrite");
			String contents = request.getParameter("qna-cwrite");
			HttpSession session = request.getSession();
			LoginDTO dto = (LoginDTO) session.getAttribute("authUser");
			String id = dto.getId();
			
			QnaWriteService qnawriteservice = QnaWriteService.getInstance();
			int result = qnawriteservice.qnaWirte(id, select, title, contents);
			String viewPage="/beepPro/QnA/qna_list.do";;
			if(result==1) {
				response.sendRedirect(viewPage+"?ton=success");
			}else {
				response.sendRedirect(viewPage+"?ton=fail");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
			return "";
			
		} else {
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		
	}

}
