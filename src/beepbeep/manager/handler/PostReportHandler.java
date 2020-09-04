package beepbeep.manager.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.manager.service.PostReportService;
import beepbeep.member.dto.LoginDTO;

public class PostReportHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getHeader("REFERER");
		url = url.substring(url.indexOf("localhost")+9);
		//System.out.println(url);
		int result = -1 ;
		int report_type = Integer.parseInt(request.getParameter("report_type"));
		int all_board_seq = Integer.parseInt(request.getParameter("all_board_seq"));
		int board_seq = Integer.parseInt(request.getParameter("board_seq"));
		String contents = request.getParameter("contents");
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("authUser");
		String id = dto.getId();
		PostReportService prservice = PostReportService.getInstance();
		result = prservice.postReportRegister(report_type, all_board_seq, board_seq, contents, id);
		redirectPost(url, response, result);
		
		return null;
	}
	private void redirectPost(String url, HttpServletResponse response, int result) throws IOException {
		url += "&reportPost="+result;
		response.sendRedirect(url);
	}
}
