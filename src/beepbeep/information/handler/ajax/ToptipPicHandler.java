package beepbeep.information.handler.ajax;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.information.dto.TTPicDTO;
import beepbeep.information.service.ajax.ToptipService;
import net.sf.json.JSONObject;

public class ToptipPicHandler implements AjaxHandler {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int tt_code = Integer.parseInt(request.getParameter("tt_code"));
		System.out.println(tt_code);
		ToptipService ttservice = ToptipService.getInstance();
		TTPicDTO dto = ttservice.toptipPic(tt_code);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("dto", dto);
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
	}

}
