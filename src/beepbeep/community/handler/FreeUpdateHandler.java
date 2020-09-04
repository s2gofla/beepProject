package beepbeep.community.handler;

import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import beepbeep.command.CommandHandler;
import beepbeep.community.dao.FreeContentDAO;
import beepbeep.community.dto.FreeCommentDTO;
import beepbeep.community.dto.FreeListDTO;
import beepbeep.community.service.FreeContentService;
import beepbeep.community.service.FreeUpdateService;
import beepbeep.community.service.FreeWriteService;

public class FreeUpdateHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("freeUpdateHandler 요청");

		
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {
			String pSeq = request.getParameter("fboard_seq");
			int fboard_seq =  Integer.parseInt(pSeq);
			
			FreeListDTO dto= new FreeListDTO();
			
			FreeContentDAO dao = FreeContentDAO.getInstance();
			Connection conn = ConnectionProvider.getConnection();
			dto = dao.selectOne(conn, fboard_seq);
			request.setAttribute("dto", dto);
		    return "/community/freeupdate";

			
		    
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			String id = request.getParameter("id");
			String title = request.getParameter("title"); // 입력된거
			String contents = request.getParameter("contents");
			String hashtag = request.getParameter("hashtag");
			int fboard_seq = Integer.parseInt(request.getParameter("fboard_seq"));
			
			FreeUpdateService freeUpdateService = FreeUpdateService.getInstance();
			// 됐는지 숫자로 받아옴
			int result = freeUpdateService.freeupdate(id, title, hashtag, contents, fboard_seq);
			String viewPage = "/beepPro/community/free_content.do?fboard_seq="+fboard_seq;
			
			if(result==1) {
	            response.sendRedirect(viewPage);
	         }else {
	            response.sendRedirect(viewPage+"?ton=fail");
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
