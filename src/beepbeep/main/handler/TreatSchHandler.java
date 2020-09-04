package beepbeep.main.handler;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beepbeep.command.CommandHandler;
import beepbeep.main.service.TreatSchService;
import beepbeep.member.dto.LoginDTO;
import beepbeep.review.model.HreviewDTO;
import beepbeep.review.model.PageBlock;
import beepbeep.review.service.HinfoDetailService;

public class TreatSchHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	System.out.println("detail핸들러 호출");
		
		ArrayList<HreviewDTO> list = null;
		PageBlock paging = new PageBlock();
		
		TreatSchService service = TreatSchService.getInstance();
		String search = request.getParameter("search");
		String pCurrentPage = request.getParameter("currentP");
		int currentPage = pCurrentPage == null? 1 : Integer.parseInt(pCurrentPage);
		paging.setCurrentPage(currentPage);
		
		
		HttpSession session = request.getSession(false);
		LoginDTO lDto = null;
		String id = "";
		if (session != null) {
			lDto = (LoginDTO)session.getAttribute("authUser");
			if( lDto != null ) {
				id = lDto.getId();	
			}else {
				id ="*";
			}
		}
		
		try {

			list = service.selecList(search, paging, id);
			paging = service.getPage(search, paging);
			
			request.setAttribute("paging", paging);
			request.setAttribute("list", list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "/main/main_search";
	}

}
