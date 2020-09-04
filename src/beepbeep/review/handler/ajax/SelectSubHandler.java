package beepbeep.review.handler.ajax;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.review.dao.ReviewAjaxDAO;
import beepbeep.review.model.CategoryModalDTO;
import net.sf.json.JSONObject;

public class SelectSubHandler implements AjaxHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("selectHandler 접속");
		
		int m_sub_seq = Integer.parseInt(request.getParameter("m_sub_seq"));
		
		ArrayList<CategoryModalDTO>list = null;
		ReviewAjaxDAO dao = ReviewAjaxDAO.getInstance();
		
		try {
			
			list = dao.selectCategory(m_sub_seq);
			
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
