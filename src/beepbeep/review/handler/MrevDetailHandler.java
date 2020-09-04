package beepbeep.review.handler;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beepbeep.command.CommandHandler;
import beepbeep.member.dto.LoginDTO;
import beepbeep.review.model.HinfoDTO;
import beepbeep.review.model.HreviewDTO;
import beepbeep.review.model.MinfoDTO;
import beepbeep.review.model.MreviewDTO;
import beepbeep.review.model.PageBlock;
import beepbeep.review.service.HinfoDetailService;
import beepbeep.review.service.MinfoDetailService;

public class MrevDetailHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("mdetail 핸들러 호출");
		
		ArrayList<MreviewDTO> list = null;
		MinfoDTO dto = null;
		PageBlock paging = new PageBlock();
		
		MinfoDetailService service = MinfoDetailService.getInstance();
		
		int m_code = Integer.parseInt(request.getParameter("m_code"));
		String pCurrentPage = request.getParameter("currentP");
		int currentPage = pCurrentPage == null? 1 : Integer.parseInt(pCurrentPage);
		paging.setCurrentPage(currentPage);
		
		
		HttpSession session = request.getSession(false);
		LoginDTO lDto = null;
		System.out.println(session);
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
			
			dto = service.select(m_code, id); //약 정보 dto
			list = service.selecList(m_code, paging, id); //리뷰 리스트
			paging = service.getPage(m_code, paging);
			
			request.setAttribute("dto", dto);
			request.setAttribute("list", list);
			request.setAttribute("paging", paging);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
		
		
		return "/review/minfo-detail";
	}

		

	
}
