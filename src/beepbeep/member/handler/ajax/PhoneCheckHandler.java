package beepbeep.member.handler.ajax;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.command.CommandHandler;
import beepbeep.member.dao.MemberAjaxDAO;
import beepbeep.member.service.ajax.PhoneCheckService;
import net.sf.json.JSONObject;

public class PhoneCheckHandler implements AjaxHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tel = request.getParameter("tel");
		
		PhoneCheckService checkservice = PhoneCheckService.getInstance();
		
		int result = checkservice.phoneCheck(tel);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
	}

}
