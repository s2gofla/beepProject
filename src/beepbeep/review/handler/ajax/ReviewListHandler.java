package beepbeep.review.handler.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beepbeep.command.AjaxHandler;
import beepbeep.review.dao.ReviewAjaxDAO;
import beepbeep.member.dto.LoginDTO;
import beepbeep.review.model.HreviewDTO;
import beepbeep.review.model.MreviewDTO;
import beepbeep.review.model.PageBlock;
import net.sf.json.JSONObject;



public class ReviewListHandler implements AjaxHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) {
	
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			System.out.println("ReviewListHandler 접속");
			
			PageBlock paging = new PageBlock();
			ArrayList<HreviewDTO> list = null;
			ArrayList<MreviewDTO> mlist = null;
			HashMap<String, Object> map = new HashMap<>();
			
			ReviewAjaxDAO dao = ReviewAjaxDAO.getInstance();
			
			int h_code = request.getParameter("h_code")== null ? -1 : Integer.parseInt(request.getParameter("h_code")) ;
			int m_code = request.getParameter("m_code")== null ? -1 : Integer.parseInt(request.getParameter("m_code")) ;
			int num = request.getParameter("num")== null ? -1 : Integer.parseInt(request.getParameter("num"));
			String pCurrentPage = request.getParameter("currentPage");
			String searchWord = request.getParameter("searchWord");
			
			int currentPage;
			
			if ( pCurrentPage == null || pCurrentPage.equals("-1") ) {
				currentPage = 1;
			} else {
				currentPage = Integer.parseInt(pCurrentPage);
			}
			
			if (searchWord == null || searchWord.equals("") || searchWord.equals("검색어를 입력하세요")) {
				searchWord = "*";
			} else {
				currentPage = 1;
			}
			
			
			String id = "";
			
			System.out.println(currentPage);
			paging.setCurrentPage(currentPage);
			
			HttpSession session = request.getSession();
			LoginDTO lDto = null;
			lDto = (LoginDTO)session.getAttribute("authUser");
			
			if( lDto != null ) {
				id = lDto.getId();	
			}else {
				id ="*";
			}
	
			if (h_code == -1) {
				mlist = dao.selecMList(m_code, id, paging, num, searchWord);
				paging = dao.getMPage(m_code, paging, searchWord);
				map.put("mlist",mlist);
			}else if(m_code == -1) {
			list = dao.selecHList(h_code, id, paging, num, searchWord);
			paging = dao.getPage(h_code, paging, searchWord);
			map.put("list",list);
		
			}
			
			
			map.put("paging", paging);
			map.put("id",id);		
			
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("map", map); 
		
			PrintWriter pw;
			try {
				pw = response.getWriter();
				pw.println(jsonObj);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}


	}

}
