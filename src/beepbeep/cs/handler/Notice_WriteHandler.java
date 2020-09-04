package beepbeep.cs.handler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.cs.model.Cs_NoticeDTO;
import beepbeep.cs.service.NoticeWrite;

public class Notice_WriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return "/cs/cs_write";
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			System.out.println("post로 요청");
			return processSubmit(request, response);
		}else {
			System.out.println("error");
		}
		return null;
		
		
	}
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		Cs_NoticeDTO dto = new Cs_NoticeDTO();
		System.out.println("writehandler");
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		int views = Integer.parseInt(request.getParameter("views"));
		
		dto.setId(id);
		dto.setTitle(title);
		dto.setContents(contents);
		dto.setViews(views);
		NoticeWrite noticeWrite = NoticeWrite.getInstance();
		int result = noticeWrite.write(dto);
		if(result ==1) {
			response.sendRedirect("/beepPro/cs/cs_notice_list.do");
		}
		
		
		return null;
	}
	
}
