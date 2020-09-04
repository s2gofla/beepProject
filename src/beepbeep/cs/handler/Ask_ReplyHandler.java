package beepbeep.cs.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.cs.model.Cs_AskDTO;
import beepbeep.cs.service.ReplyService;

public class Ask_ReplyHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReplyService reply = new ReplyService();
		List<Cs_AskDTO> askReply = reply.select();
		request.setAttribute("askReply", askReply);
		return "/beepPro/cs/cs_ask_content.do";
	}

}
