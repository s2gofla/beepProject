package beepbeep.review.handler.ajax;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.review.dao.ReviewAjaxDAO;
import beepbeep.review.model.MpurposeDTO;
import net.sf.json.JSONObject;

public class SelectPurposeHandler implements AjaxHandler{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
System.out.println("selectHandler 접속");
		
		
		int bpurpose_code = Integer.parseInt(request.getParameter("bpurpose_code"));
		
		ArrayList<MpurposeDTO>list = null;
		ReviewAjaxDAO dao = ReviewAjaxDAO.getInstance();
		
		try {
			
			list = dao.selectPurpose(bpurpose_code);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObj = new JSONObject();
		response.setCharacterEncoding("UTF-8");

		jsonObj.put("list",list);
		PrintWriter pw = response.getWriter();
		pw.print(jsonObj);
		
	
		
		
	}

}
