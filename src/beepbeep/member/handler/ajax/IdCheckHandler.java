package beepbeep.member.handler.ajax;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.command.CommandHandler;
import beepbeep.member.service.ajax.IdCheckService;
import net.sf.json.JSONObject;

public class IdCheckHandler implements AjaxHandler{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		IdCheckService idservice = IdCheckService.getInstance();
		int result = idservice.idCheck(id);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);

	}

}
