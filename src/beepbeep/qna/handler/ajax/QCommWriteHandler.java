package beepbeep.qna.handler.ajax;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.qna.dao.QnaAjaxDAO;
import beepbeep.qna.dto.QnaCommentDTO;
import net.sf.json.JSONObject;

public class QCommWriteHandler implements AjaxHandler{
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CommWriteHandler 접속");
		String id = request.getParameter("id");
		int pq_seq = Integer.parseInt(request.getParameter("pq_seq"));
		String contents = request.getParameter("question_comment");
		
		//System.out.println(id+","+pq_seq+","+contents);
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		QnaAjaxDAO dao = QnaAjaxDAO.getInstance();
		int result = dao.insertQComment(id, contents, pq_seq);
		List<QnaCommentDTO> comments = dao.icSelect(pq_seq);
		map.put("result", result);
		map.put("comments", comments);
		
		JSONObject jsonObj = new JSONObject();
		/* jsonObj.putAll(JSONObject.fromObject(result)); */
		
		
		jsonObj.put("map", map);
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
		
	}

}
