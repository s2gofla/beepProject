package beepbeep.community.handler.ajax;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beepbeep.command.AjaxHandler;
import beepbeep.community.dao.FreeAjaxDAO;
import beepbeep.community.dto.FreeCommentDTO;
import beepbeep.member.dto.LoginDTO;
import net.sf.json.JSONObject;

public class FreeCcomWirteHandler implements AjaxHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("FreeCcomWirteHandler 접속");
		
		HttpSession session = request.getSession();
		LoginDTO dto = (LoginDTO)session.getAttribute("authUser");
		String id = dto.getId();

		System.out.println(id);
		int fcoment_seq = Integer.parseInt(request.getParameter("fcoment_seq"));
		//System.out.println(fboard_seq);
		String contents = request.getParameter("contents");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		FreeAjaxDAO dao = FreeAjaxDAO.getInstance();

		int result = dao.freeCcomInsert(id, fcoment_seq, contents);
		List<FreeCommentDTO> comments = dao.inscSelect(fcoment_seq, id);
		map.put("result", result);
		map.put("comments", comments);
		map.put("id",id);
		
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("map", map);
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
		
	}

}
