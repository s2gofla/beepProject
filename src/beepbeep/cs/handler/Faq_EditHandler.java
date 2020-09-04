package beepbeep.cs.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;

import beepbeep.cs.model.Cs_FaqDTO;
import beepbeep.cs.service.FaqWrite;

public class Faq_EditHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return "/cs/cs_faq_edit";
		}else if(request.getMethod().contentEquals("POST")) {
			request.setCharacterEncoding("UTF-8");
			return processEdit(request,response);
		}else {
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
		return null;
	}

	private String processEdit(HttpServletRequest request, HttpServletResponse response) {
		Cs_FaqDTO dto = new Cs_FaqDTO();
		dto.setTitle(request.getParameter("title"));
		dto.setContents(request.getParameter("contents"));
		
		try {
			FaqWrite service = FaqWrite.getInstance();
			int result = service.write(dto);

			if(result ==1 ) {
				response.sendRedirect("/beepPro/cs/cs_faq_list.do");
			} 
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

	}
