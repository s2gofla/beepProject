package beepbeep.main.handler.ajax;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.main.service.ajax.FAutoCompleteService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FAutoCompleteHandler implements AjaxHandler{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		String autoWord = request.getParameter("autoWord");
		autoWord = autoWord.substring(1);
		FAutoCompleteService fservice = FAutoCompleteService.getInstance();
		
		List<String> list = fservice.autoList(autoWord);
		
		JSONArray arrayObj = new JSONArray();
		JSONObject jsonObj = null;
		if(!list.isEmpty()) {
			for(String str: list) {
				jsonObj = new JSONObject();
				jsonObj.put("data", str);
				arrayObj.add(jsonObj);
			}			
		}
		PrintWriter pw = response.getWriter();
		pw.println(arrayObj);
		pw.flush();
		pw.close();
		
	}

}
