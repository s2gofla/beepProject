package beepbeep.information.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.information.dto.DiseaseDTO;
import beepbeep.information.service.DiseaseService;

public class DiseaseHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int seq = Integer.parseInt(request.getParameter("seq"));
		DiseaseService diseaseservice = DiseaseService.getInstance();
		DiseaseDTO dto = diseaseservice.selectList(seq);
				
		request.setAttribute("dto", dto);
		
		return "/information/disease";
	}

}
