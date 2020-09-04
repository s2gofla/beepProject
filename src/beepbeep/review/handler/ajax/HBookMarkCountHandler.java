package beepbeep.review.handler.ajax;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.review.dao.ReviewAjaxDAO;
import net.sf.json.JSONObject;

public class HBookMarkCountHandler implements AjaxHandler{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("hbookmarkcount 접속");
		
		
		int h_code = Integer.parseInt(request.getParameter("h_code"));
		
		
		HashMap<String, Object> map = new HashMap<>();
		
		ReviewAjaxDAO dao = ReviewAjaxDAO.getInstance();
		
		int books = 0;

		books = dao.selectBooks(h_code);
		

		map.put("books",books);
		System.out.println(books+"총수");
		map.put("h_code",h_code);
		System.out.println("출력"+h_code);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("map",map);
		PrintWriter pw = response.getWriter();
		pw.print(jsonObj);
		
	}

}
