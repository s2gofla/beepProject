package beepbeep.manager.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.manager.service.CommentReportService;
import beepbeep.member.dto.LoginDTO;

public class CommentReportHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getHeader("REFERER");
		url = url.substring(url.indexOf("localhost")+9);
		int report_type = Integer.parseInt(request.getParameter("report_type"));
		int all_board_seq = Integer.parseInt(request.getParameter("all_board_seq"));
		int comment_seq = Integer.parseInt(request.getParameter("comment_seq"));
		String contents = request.getParameter("contents");
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("authUser");
		String id = dto.getId();
		int result = -1;
		CommentReportService crservice = CommentReportService.getInstance();
		result = crservice.commentReportRegister(report_type, all_board_seq, comment_seq, contents, id);
		redirectComment(url, response, result);
		return null;
	}
	private void redirectComment(String url, HttpServletResponse response, int result) throws IOException {
		url += "&reportComment="+result;
		response.sendRedirect(url);
	}
}
