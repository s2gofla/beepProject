package beepbeep.review.handler.ajax;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beepbeep.command.AjaxHandler;
import beepbeep.command.CategoryDTO;

import beepbeep.review.dao.ReviewAjaxDAO;
import beepbeep.member.dto.LoginDTO;
import beepbeep.review.model.HinfoDTO;
import beepbeep.review.model.SpecialDTO;
import net.sf.json.JSONObject;


public class HinfoListHandler implements AjaxHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("hinfoListHandler 접속");
		
		ArrayList<HinfoDTO> list = null; //병원 정보 가져오기(이름,주소,별점,북마크 총수)
		ReviewAjaxDAO dao = ReviewAjaxDAO.getInstance();
		
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
		int num = Integer.parseInt(request.getParameter("type"));
		

		
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
				
				list = dao.select(num,searchWord,sub,special,id);

							
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			JSONObject jsonObj = new JSONObject();
			/* jsonObj.put("view", view); */
			jsonObj.put("list", list);
			PrintWriter pw = response.getWriter();
			pw.println(jsonObj);
		
	}

}
