package beepbeep.review.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beepbeep.command.CommandHandler;
import beepbeep.member.dto.LoginDTO;
import beepbeep.review.model.MinfoDTO;
import beepbeep.review.model.MpurposeDTO;
import beepbeep.review.model.PageBlock;
import beepbeep.review.service.MinfoService;


public class MreviewHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
System.out.println("Mlist핸들러 호출");
		
		ArrayList<MinfoDTO> list = null; //약 정보 가져오기
		ArrayList<MpurposeDTO> mpurList = null; // 필터  약 용도 목록 가져오기
		PageBlock paging = new PageBlock(); //페이징 처리코딩
	
		
		MinfoService service = MinfoService.getInstance();
		
		HttpSession session = request.getSession(false);
		LoginDTO dto = null;
		System.out.println(session);
		String id = "";
		if (session != null) {
			
			dto = (LoginDTO)session.getAttribute("authUser");
			
			if( dto != null ) {
				id = dto.getId();	
			}else {
				id ="*";
			}
		}
		
		
		String pCurrentPage = request.getParameter("currentP");
		
		int currentPage = pCurrentPage == null? 1 : Integer.parseInt(pCurrentPage);
		System.out.println(currentPage);
		paging.setCurrentPage(currentPage);
		
		String pnum = request.getParameter("num") ;

		
		String searchWord = request.getParameter("searchWord");
		String mpurpose = request.getParameter("mpurpose");
		
		int num = 0;
		
		if (pnum == null || pnum.equals("")) {
			num = 1;
		}else {
			num = Integer.parseInt(pnum); 
		}
		
		if (searchWord == null || searchWord.equals("") || searchWord.equals("검색어를 입력하세요")) {
			searchWord = "*";
		}
		if (mpurpose== null || mpurpose.equals("")) {
			mpurpose="*";
			
		}

		
			try {
				
				list = service.select(searchWord,mpurpose, paging, id, num);		
				paging = service.getPage(searchWord,mpurpose ,paging);
				mpurList= service.purposeList();
				
				
				request.setAttribute("paging", paging);
				request.setAttribute("list", list);
				request.setAttribute("mpurList", mpurList);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
		
		return "/review/minfo";
	}

	
	
	
}
