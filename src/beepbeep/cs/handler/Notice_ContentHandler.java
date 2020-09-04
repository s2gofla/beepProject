package beepbeep.cs.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;

import beepbeep.cs.model.Cs_NoticeDTO;
import beepbeep.cs.service.Notice_Content;

public class Notice_ContentHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int notice_seq = Integer.parseInt(request.getParameter("notice_seq"));
		
		Notice_Content notice_content = new Notice_Content();
		
		Cs_NoticeDTO noticeViewData = notice_content.selectOne(notice_seq);
		request.setAttribute("noticeViewData", noticeViewData);
		
		return "/cs/cs_notice_content";
	}

}
