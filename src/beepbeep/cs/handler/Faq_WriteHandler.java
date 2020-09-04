package beepbeep.cs.handler;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.cs.model.Cs_FaqDTO;
import beepbeep.cs.service.FaqEdit;
import beepbeep.cs.service.FaqWrite;

public class Faq_WriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return "/cs/cs_faq_write";
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
		String contents = request.getParameter("contents");
		
		Cs_FaqDTO dto = new Cs_FaqDTO();
		dto.setTitle(title);
		dto.setContents(contents);
		FaqEdit noticeEdit = new FaqEdit();
		int result = noticeEdit.write(dto);
		if(result ==1) {
			response.sendRedirect("/beepPro/cs/cs_faq_list.do");

		}
		return null;
	}
	
}
