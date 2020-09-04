package beepbeep.review.handler.ajax;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.review.dao.ReviewAjaxDAO;
import net.sf.json.JSONObject;

public class MBookMarkCountHandler implements AjaxHandler{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("hbookmarkcount 접속");
		
		
		int m_code = Integer.parseInt(request.getParameter("m_code"));
		String pnum = request.getParameter("num") == null ? "-1" : request.getParameter("num"); 
		int num = Integer.parseInt(pnum);
		
		System.out.println("mcount num값"+num);
		HashMap<String, Object> map = new HashMap<>();
		
		ReviewAjaxDAO dao = ReviewAjaxDAO.getInstance();
		
		int books = 0;

		books = dao.selectMBooks(m_code);
		

		map.put("books",books);
		map.put("m_code",m_code);
		map.put("num",num);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("map",map);
		PrintWriter pw = response.getWriter();
		pw.print(jsonObj);
		
	}

}
