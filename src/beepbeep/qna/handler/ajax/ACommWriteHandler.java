package beepbeep.qna.handler.ajax;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.qna.dao.QnaAjaxDAO;
import beepbeep.qna.dto.AnswerCommDTO;
import net.sf.json.JSONObject;

public class ACommWriteHandler implements AjaxHandler{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		int ps_seq = Integer.parseInt(request.getParameter("ps_seq"));
		String contents = request.getParameter("answer_comment");
		HashMap<String, Object> map = new HashMap<String, Object>();
		System.out.println(id+","+ps_seq+","+contents);
		QnaAjaxDAO dao = QnaAjaxDAO.getInstance();
		int result = dao.insertAComment(id, contents, ps_seq);
		List<AnswerCommDTO> comments = dao.icASelect(ps_seq);
		
		map.put("result", result);
		map.put("comments", comments);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("map", map);
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
	}

}
