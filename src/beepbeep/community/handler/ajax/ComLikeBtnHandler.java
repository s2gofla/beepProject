package beepbeep.community.handler.ajax;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beepbeep.command.AjaxHandler;
import beepbeep.community.dao.FreeAjaxDAO;
import beepbeep.member.dto.LoginDTO;
import net.sf.json.JSONObject;

public class ComLikeBtnHandler implements AjaxHandler{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ComLikeBtnHandler 접속");
		HttpSession session = request.getSession();
		LoginDTO dto = (LoginDTO) session.getAttribute("authUser");
		String id = dto.getId();
		
		String yn = request.getParameter("like");
		int fcoment_seq = Integer.parseInt(request.getParameter("fcoment_seq"));
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		FreeAjaxDAO dao = FreeAjaxDAO.getInstance();
		int result, likes, record;
		result = dao.updateLikecount(yn, fcoment_seq, id);
		likes = dao.selectLikes(fcoment_seq);
		
		
		// 좋아요 기록
		//record = dao.likesRecord(id, fcoment_seq);
		
		map.put("result", result); // "이름" 값
		map.put("likes", likes);
		//map.put("record", record); //
		map.put("fcoment_seq", fcoment_seq);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("map", map);
		
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
		
		
	}
	

}
