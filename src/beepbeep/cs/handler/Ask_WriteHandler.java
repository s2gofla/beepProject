package beepbeep.cs.handler;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beepbeep.command.CommandHandler;
import beepbeep.cs.model.Cs_AskDTO;
import beepbeep.cs.service.AskWrite;


public class Ask_WriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return "/cs/cs_ask_write";
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		}else {
			System.out.println("error");
		}
		return null;
		
		
	}
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("writehandler");
		String title = request.getParameter("title");
		int qtype_seq = Integer.parseInt(request.getParameter("qtype_seq"));
		String contents = request.getParameter("contents");
		String id = request.getParameter("id");
		
		Cs_AskDTO dto = new Cs_AskDTO();
		dto.setTitle(title);
		dto.setId(id);
		dto.setQtype_seq(qtype_seq);
		dto.setContents(contents);
		AskWrite askWrite = AskWrite.getInstance();
		int result = askWrite.write(dto);
		
		if(result ==1) {
			response.sendRedirect("/beepPro/cs/cs_ask_list.do");

		}
		return null;
	}
	
}
