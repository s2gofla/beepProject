package beepbeep.review.handler;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beepbeep.command.CommandHandler;
import beepbeep.member.dto.LoginDTO;
import beepbeep.review.model.HinfoDTO;
import beepbeep.review.model.HreviewDTO;
import beepbeep.review.model.PageBlock;
import beepbeep.review.service.HinfoDetailService;

public class HrevDetailHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("detail핸들러 호출");
		
		ArrayList<HreviewDTO> list = null;
		HinfoDTO dto = null;
		PageBlock paging = new PageBlock();
		
		HinfoDetailService service = HinfoDetailService.getInstance();
		int h_code = Integer.parseInt(request.getParameter("h_code"));
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
			
			dto = service.select(h_code, id);
			list = service.selecList(h_code, paging, id);
			paging = service.getPage(h_code, paging);
			
			request.setAttribute("paging", paging);
			request.setAttribute("dto", dto);
			request.setAttribute("list", list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
		
		
		return "/review/hinfo-detail";
	}

}
