package beepbeep.community.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import beepbeep.command.CategoryDTO;
import beepbeep.command.CategoryService;
import beepbeep.command.CommandHandler;
import beepbeep.community.dao.DiseaseDAO;
import beepbeep.community.dao.FreeContentDAO;
import beepbeep.community.dto.DiseaseListDTO;
import beepbeep.community.dto.FreeListDTO;
import beepbeep.community.service.DiseaseUpdateService;
import beepbeep.community.service.FreeUpdateService;

public class DiseaseUpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("diseaseUpdateHandler 요청");
		
		CategoryService categoryService = CategoryService.getInstance();
		List<CategoryDTO> categoryList = categoryService.CategoryList();
		request.setAttribute("categoryList", categoryList);
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {
			String pSeq = request.getParameter("dtip_seq");
			int dtip_seq =  Integer.parseInt(pSeq);
			
			DiseaseListDTO dto= new DiseaseListDTO();
			
			DiseaseDAO dao = DiseaseDAO.getInstance();
			Connection conn = ConnectionProvider.getConnection();
			dto = dao.selectOne(conn, dtip_seq);
			request.setAttribute("dto", dto);
		    return "/community/diseaseupdate";

			
		    
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			String id = request.getParameter("id");
			int m_sub_seq = Integer.parseInt(request.getParameter("m_sub_seq"));
			String title = request.getParameter("title"); // 입력된거
			String contents = request.getParameter("contents");
			String hashtag = request.getParameter("hashtag");
			int dtip_seq = Integer.parseInt(request.getParameter("dtip_seq"));
			
			DiseaseUpdateService diseaseUpdateService = DiseaseUpdateService.getInstance();
			// 됐는지 숫자로 받아옴
			int result = diseaseUpdateService.diseaseupdate(id, m_sub_seq, title, hashtag, contents, dtip_seq);
			String viewPage = "/beepPro/community/disease_content.do?dtip_seq="+dtip_seq;
			
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
