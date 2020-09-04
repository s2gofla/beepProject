package beepbeep.review.handler.ajax;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.review.dao.ReviewAjaxDAO;
import net.sf.json.JSONObject;

public class HBookMarkHandler implements AjaxHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("hbookmark 접속");
		
		String book = request.getParameter("book");
		int h_code = Integer.parseInt(request.getParameter("h_code"));
		String id = request.getParameter("id");
		
		
		HashMap<String, Object> map = new HashMap<>();
		
		ReviewAjaxDAO dao = ReviewAjaxDAO.getInstance();
		
		int result = 0;
		
		if (book.equals("0")) {
			
			result = dao.insertBook(h_code, id);
		}else if(book.equals("-1")){
			result = dao.deleteBook(h_code, id);
		}
		// books = dao.selectBooks(h_code);
		
		map.put("result", result);
		//map.put("books",books);
		map.put("h_code",h_code);
		System.out.println(h_code + "표시 h_code");
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("map",map);
		PrintWriter pw = response.getWriter();
		pw.print(jsonObj);
		
	}

}
