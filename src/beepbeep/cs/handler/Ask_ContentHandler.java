package beepbeep.cs.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.cs.model.Cs_AskDTO;

import beepbeep.cs.service.Ask_Content;

public class Ask_ContentHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ask_contentHandler");
		int qna_seq = Integer.parseInt(request.getParameter("qna_seq"));
		Ask_Content ask_content = new Ask_Content();
		Cs_AskDTO askViewData = ask_content.selectOne(qna_seq);
		request.setAttribute("askViewData", askViewData);
		
		return "/cs/cs_ask_content";
	}

}
