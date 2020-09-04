package beepbeep.review.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import beepbeep.command.CategoryDTO;
import beepbeep.command.CategoryService;
import beepbeep.command.CommandHandler;
import beepbeep.member.dto.LoginDTO;
import beepbeep.review.model.HinfoDTO;
import beepbeep.review.model.SpecialDTO;
import beepbeep.review.service.HinfoService;

public class HreviewHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		
		
		System.out.println("list핸들러 호출");
		
		ArrayList<HinfoDTO> list = null; //병원 정보 가져오기(이름,주소,별점,북마크 총수)
		// ArrayList<HinfoSubDTO> hinfoSubList = null; // 병원 정보 진료과목 가져오기
		
		List<CategoryDTO> subTypeList = null; // 필터 진료과목 목록 가져오기
		ArrayList<SpecialDTO> specialList = null; // 필터 특이사항 목록 가져오기
		
	
		
		HinfoService service = HinfoService.getInstance();
		CategoryService categoryService = CategoryService.getInstance();
		
		
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
			
			
		System.out.println(id);
		
		String searchWord = request.getParameter("searchWord");
		String sub = request.getParameter("sub");
		String special = request.getParameter("special");
		
		
		
		if (searchWord == null || searchWord.equals("") || searchWord.equals("검색어를 입력하세요")) {
			searchWord = "*";
		}
		if (sub == null || sub.equals("")) {
			sub="*";
			
		}
		if (special == null || special.equals("")) {
			special = "*";
		}
		
		
		
			try {
				
				list = service.select(searchWord,sub,special,id);
				
				
				subTypeList = categoryService.CategoryList();
				specialList= service.specialList();
		
				request.setAttribute("list", list);
				request.setAttribute("subTypeList", subTypeList);
				request.setAttribute("specialList", specialList);
				//request.setAttribute("hinfoSubList", hinfoSubList);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
		
				
		return "/review/hinfo";
		
		
	}

}
