package beepbeep.community.handler;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CategoryDTO;
import beepbeep.command.CategoryService;
import beepbeep.command.CommandHandler;
import beepbeep.community.service.DiseaseWriteService;

public class DiseaseWriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("diseaseWriteHandler 요청");
		
		CategoryService categoryService = CategoryService.getInstance();
		List<CategoryDTO> categoryList = categoryService.CategoryList();
		request.setAttribute("categoryList", categoryList);
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {			
		    return "/community/diseasewrite";
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			String id = request.getParameter("id");
			int m_sub_seq = Integer.parseInt(request.getParameter("m_sub_seq")); /// disease에서 추가
			String title = request.getParameter("writetitle"); // 입력된거
			String contents = request.getParameter("writecontent");
			
			String hashtag = request.getParameter("writehashtag");
			
			DiseaseWriteService diseaseWriteService = DiseaseWriteService.getInstance();
			// 됐는지 숫자로 받아옴
			int result = diseaseWriteService.diseasewrite(id, m_sub_seq, title, hashtag, contents);
			String viewPage = "/beepPro/community/disease_list.do";
			if(result==1) {
	            response.sendRedirect(viewPage+"?on=success");
	         }else {
	            response.sendRedirect(viewPage+"?on=fail");
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
