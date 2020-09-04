package beepbeep.main.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.information.dto.ToptipListDTO;
import beepbeep.main.service.MainTTListService;

public class MainHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MainHandler 접속");
		MainTTListService mttservice = MainTTListService.getInstance();
		List<ToptipListDTO> list = mttservice.ttList();
		
		request.setAttribute("list", list);
		return "/main/main";
	}

}
