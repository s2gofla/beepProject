package beepbeep.community.handler.ajax;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beepbeep.command.AjaxHandler;
import beepbeep.community.dao.DiseaseAjaxDAO;
import beepbeep.community.dao.FreeAjaxDAO;
import beepbeep.member.dto.LoginDTO;
import net.sf.json.JSONObject;

public class DiseaseComLikeBtnHandler implements AjaxHandler{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("diseaseComLikeBtnHandler 접속");
		HttpSession session = request.getSession();
		LoginDTO dto = (LoginDTO) session.getAttribute("authUser");
		String id = dto.getId();
		
		String yn = request.getParameter("like");
		int seq = Integer.parseInt(request.getParameter("seq"));
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		DiseaseAjaxDAO dao = DiseaseAjaxDAO.getInstance();
		int result, likes;
		result = dao.updateLikecount(yn, seq, id);
		likes = dao.selectLikes(seq);
		

		
		map.put("result", result); // "이름" 값
		map.put("likes", likes);
		map.put("seq", seq);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("map", map);
		
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
		
		
	}
	

}
