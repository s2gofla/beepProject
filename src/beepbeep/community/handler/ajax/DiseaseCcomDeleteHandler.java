package beepbeep.community.handler.ajax;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beepbeep.command.AjaxHandler;
import beepbeep.community.dao.DiseaseDAO;
import beepbeep.community.dao.FreeAjaxDAO;
import beepbeep.member.dto.LoginDTO;
import net.sf.json.JSONObject;

public class DiseaseCcomDeleteHandler implements AjaxHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("DiseaseCcomDeleteHandler 접속");
		
		HttpSession session = request.getSession();
		LoginDTO dto = (LoginDTO)session.getAttribute("authUser");
		String id = dto.getId();
		
		int direply_seq = Integer.parseInt(request.getParameter("direply_seq"));
		System.out.println(direply_seq);
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		DiseaseDAO dao = DiseaseDAO.getInstance();
		
		int result = dao.diseaseCcomDelete(id, direply_seq);

		map.put("result", result);
		
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("map", map);
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
		
	}

}
