package beepbeep.member.handler.ajax;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.member.service.ajax.FindPwdCheckService;
import net.sf.json.JSONObject;

public class FindPwdCheckHandler implements AjaxHandler{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String id= request.getParameter("id");
		
		FindPwdCheckService findPwdService = FindPwdCheckService.getInstance();
		
		String result = findPwdService.findPwd(name, birth, id);  
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
		
	}

}
