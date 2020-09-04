package beepbeep.member.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.member.dto.LoginDTO;
import beepbeep.member.service.ChangeClauseService;
import beepbeep.member.service.ChangeInfoService;

public class ChangeClauseHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/member/m9_clauseChange";
	private ChangeClauseService changeClauseS = new ChangeClauseService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);

		}
		else if(request.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(request,response);

		}
		else {
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		LoginDTO dto = (LoginDTO)request.getSession().getAttribute("authUser");


		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);


		String TC1 = request.getParameter("m_clause_TC1");
		String TC2 = request.getParameter("m_clause_TC2");
	

		System.out.println(TC1);
		System.out.println(TC2);
		
		System.out.println(dto.getId());

		
		if(TC1 == null ) {
			System.out.println("TC1");
			errors.put("TC1",Boolean.TRUE);
		}
		if(TC2 == null ) {
			System.out.println("TC2");
			errors.put("TC2",Boolean.TRUE);
		}


		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}




		try {
			
			changeClauseS.changeClause(dto.getId(), TC1,TC2);
			return "/member/m7_myPage";
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
