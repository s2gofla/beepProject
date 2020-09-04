package beepbeep.review.handler.ajax;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.command.CategoryDTO;
import beepbeep.review.dao.ReviewAjaxDAO;
import beepbeep.review.model.CategoryModalDTO;
import net.sf.json.JSONObject;

public class SelectAllSubHandler implements AjaxHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("selectAllHandler 접속");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		List<CategoryModalDTO> clist = null;
		List<CategoryDTO> subTypeList = null;
		HashMap<String, Object> map = new HashMap<>();
		
		
		int h_code = request.getParameter("h_code")== null ?  -1 :Integer.parseInt(request.getParameter("h_code"));
		
		

		
		ReviewAjaxDAO dao = ReviewAjaxDAO.getInstance();
		
		try {
			clist = dao.selectTreatMent(h_code);
			subTypeList = dao.selectSub(h_code);
			
			map.put("clist",clist);
			map.put("subTypeList", subTypeList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		JSONObject jsonObj = new JSONObject();
		response.setCharacterEncoding("UTF-8");

		jsonObj.put("map",map);
		PrintWriter pw = response.getWriter();
		pw.print(jsonObj);
		
		
	}

}
