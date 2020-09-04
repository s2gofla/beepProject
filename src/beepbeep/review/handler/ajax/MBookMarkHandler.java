package beepbeep.review.handler.ajax;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.review.dao.ReviewAjaxDAO;
import net.sf.json.JSONObject;

public class MBookMarkHandler implements AjaxHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("hbookmark 접속");
		
		String book = request.getParameter("book");
		int m_code = Integer.parseInt(request.getParameter("m_code"));
		String id = request.getParameter("id");
		String pnum = request.getParameter("num") == null ? "-1" : request.getParameter("num"); 
		int num = Integer.parseInt(pnum);
		
		System.out.println("mbookmark num값은"+num);
		
		HashMap<String, Object> map = new HashMap<>();
		
		ReviewAjaxDAO dao = ReviewAjaxDAO.getInstance();
		
		int result = 0;
		
		if (book.equals("0")) {
			
			result = dao.MinsertBook(m_code, id);
		}else if(book.equals("-1")){
			result = dao.MdeleteBook(m_code, id);
		}
		
		
		map.put("result", result);
		
		map.put("m_code",m_code);
		
		map.put("num", num);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("map",map);
		PrintWriter pw = response.getWriter();
		pw.print(jsonObj);
		
	}

}
