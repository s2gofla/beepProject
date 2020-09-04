package beepbeep.information.handler.ajax;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.information.dto.PriceContentDTO;
import beepbeep.information.service.ajax.PriceInfoService;
import net.sf.json.JSONObject;

public class PriceInfoHandler implements AjaxHandler{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pinfo_code = Integer.parseInt(request.getParameter("pinfo_code"));
		
		PriceInfoService priceservice = PriceInfoService.getInstance();
		PriceContentDTO dto = priceservice.selectPrice(pinfo_code);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("dto", dto);
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
	}

}
