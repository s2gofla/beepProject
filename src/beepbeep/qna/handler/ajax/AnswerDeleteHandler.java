package beepbeep.qna.handler.ajax;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beepbeep.command.AjaxHandler;
import beepbeep.qna.dao.QnaAjaxDAO;
import net.sf.json.JSONObject;

public class AnswerDeleteHandler implements AjaxHandler{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int ps_seq = Integer.parseInt(request.getParameter("ps_seq"));
		
		QnaAjaxDAO dao = QnaAjaxDAO.getInstance();
		
		 int result = dao.answerDelete( ps_seq);
		 
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
		
	}
	

}
