package beepbeep.member.handler.ajax;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.member.service.ajax.IdCheckService;
import beepbeep.member.service.ajax.NicknameCheckService;
import net.sf.json.JSONObject;

public class NicknameCheckHandler implements AjaxHandler{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String nickname = request.getParameter("nickname");
		
		NicknameCheckService nicknameS = NicknameCheckService.getInstance();
		int result = nicknameS.nicknameCheck(nickname);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
	}

}
