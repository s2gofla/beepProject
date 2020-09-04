package beepbeep.main.handler.ajax;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.AjaxHandler;
import beepbeep.main.dto.MSubnameDTO;
import beepbeep.main.service.ajax.MSubNameService;
import net.sf.json.JSONObject;

public class MSubNameHandler implements AjaxHandler{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		int m_sub_seq = Integer.parseInt(request.getParameter("m_sub_seq"));
		String m_sub_name = request.getParameter("m_sub_name");
		//System.out.println(m_sub_seq);
		MSubNameService msnservice = MSubNameService.getInstance();
		
		MSubnameDTO dto = msnservice.msubNameList(m_sub_seq);
		dto.setM_sub_name(m_sub_name);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("dto", dto);
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
		
	}

}
