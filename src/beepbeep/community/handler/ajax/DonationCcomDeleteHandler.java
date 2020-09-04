package beepbeep.community.handler.ajax;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beepbeep.command.AjaxHandler;
import beepbeep.community.dao.DonationAjaxDAO;
import beepbeep.member.dto.LoginDTO;
import net.sf.json.JSONObject;

public class DonationCcomDeleteHandler implements AjaxHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("DonationCcomDeleteHandler 접속");
		
		HttpSession session = request.getSession();
		LoginDTO dto = (LoginDTO)session.getAttribute("authUser");
		String id = dto.getId();
		
		int doreply_seq = Integer.parseInt(request.getParameter("doreply_seq"));
		System.out.println(doreply_seq);
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		DonationAjaxDAO dao = DonationAjaxDAO.getInstance();
		
		int result = dao.donationCcomDelete(id, doreply_seq);

		map.put("result", result);
		
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("map", map);
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
		
	}

}
