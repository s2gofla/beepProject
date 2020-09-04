package beepbeep.qna.handler.ajax;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.member.dto.LoginDTO;
import beepbeep.qna.dao.QnaAjaxDAO;
import net.sf.json.JSONObject;

public class AnswerUpdateHandler implements AjaxHandler{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String contents= request.getParameter("contents");
		String ps_seq = request.getParameter("ps_seq");
		
		QnaAjaxDAO dao = QnaAjaxDAO.getInstance();
		int result = dao.updateAnswer(ps_seq, contents);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
		
	}

}
