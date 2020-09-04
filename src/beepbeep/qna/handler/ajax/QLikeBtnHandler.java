package beepbeep.qna.handler.ajax;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.member.dto.LoginDTO;
import beepbeep.qna.dao.QnaAjaxDAO;
import net.sf.json.JSONObject;

public class QLikeBtnHandler implements AjaxHandler{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("qlikehandler");
		String yn = request.getParameter("like");
		int pq_seq = Integer.parseInt(request.getParameter("pq_seq"));
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("authUser") ;
		
		String sid = dto==null? "NONE" : dto.getId();
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		QnaAjaxDAO dao = QnaAjaxDAO.getInstance();
		int result , likes ;
		// 좋아요 추가
		result = dao.qlikeBtn(yn, pq_seq, sid);
		// 좋아요 개수
		likes = dao.selectQLike(pq_seq);
		
		map.put("result", result);
		map.put("likes", likes);
		map.put("ps_seq", pq_seq);
		System.out.println("json ajax");
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("map", map);
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
	}

}
