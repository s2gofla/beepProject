package beepbeep.cs.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.cs.service.NoticeDelete;

public class Notice_DeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("delete요청");
		int notice_seq = Integer.parseInt(request.getParameter("notice_seq"));
		
		NoticeDelete noticeDelete = NoticeDelete.getInstance();
		noticeDelete.delete(notice_seq);
		
		response.sendRedirect("/beepPro/cs/cs_notice_list.do?delete=success");
		return null;
	}

}
