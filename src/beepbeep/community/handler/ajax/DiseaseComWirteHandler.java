package beepbeep.community.handler.ajax;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beepbeep.command.AjaxHandler;
import beepbeep.community.dao.DiseaseAjaxDAO;
import beepbeep.community.dao.FreeAjaxDAO;
import beepbeep.community.dto.DiseaseContentDTO;
import beepbeep.community.dto.FreeCommentDTO;
import beepbeep.member.dto.LoginDTO;
import net.sf.json.JSONObject;

public class DiseaseComWirteHandler implements AjaxHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("DiseaseComWirteHandler 접속");
		
		HttpSession session = request.getSession();
		LoginDTO dto = (LoginDTO)session.getAttribute("authUser");
		String id = dto.getId();

		System.out.println(id);
		int dtip_seq = Integer.parseInt(request.getParameter("dtip_seq"));
		//System.out.println(fboard_seq);
		String contents = request.getParameter("contents");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		DiseaseAjaxDAO dao = DiseaseAjaxDAO.getInstance();

		int result = dao.diseaseComInsert(id, dtip_seq, contents);
		List<DiseaseContentDTO> comments = dao.insSelect( dtip_seq);
		map.put("result", result);
		map.put("comments", comments);
		
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("map", map);
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
		
	}

}
