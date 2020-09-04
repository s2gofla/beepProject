package beepbeep.member.handler;

import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.member.dto.LoginDTO;
import beepbeep.member.service.ChangeInfoService;
import beepbeep.member.service.InvaildPasswordException;


public class ChangeInfoHandler implements CommandHandler{

	private static final String FORM_VIEW = "/member/m8_infoChange";
	private ChangeInfoService changeInfoS = new ChangeInfoService();

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

	private String processSubmit(HttpServletRequest request, HttpServletResponse response ) throws NamingException {
		LoginDTO dto = (LoginDTO)request.getSession().getAttribute("authUser");


		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);


		String newPwd1 = request.getParameter("newPwd1");
		String newPwd2 = request.getParameter("newPwd2");
		String newNick = request.getParameter("newNick");
		String newPhone = request.getParameter("newPhone");
		String newPhoto = request.getParameter("newPhoto");

		System.out.println(newPwd1);
		System.out.println(newPwd2);
		System.out.println(newNick);
		System.out.println(newPhone);
		System.out.println(newPhoto);
		System.out.println(dto.getId());

		
		if(newPwd1 == null || newPwd1.isEmpty()) {
			System.out.println("newpwd1");
			errors.put("newPwd1",Boolean.TRUE);

		}
		if(newPwd2 == null || newPwd2.isEmpty()) {
			System.out.println("newpwd2");
			errors.put("newPwd2",Boolean.TRUE);
		}
		if(newPhone == null || newPhone.isEmpty()) {
			System.out.println("newphone");
			errors.put("newPhone",Boolean.TRUE);
		}
		if(newNick == null || newNick.isEmpty()) {
			System.out.println("newnick");
			errors.put("newNick",Boolean.TRUE);
		}

		if(!newPwd1.equals(newPwd2)) {
			System.out.println("newnotmatch");
			errors.put("newNotMatch", Boolean.TRUE);
		}


		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}




		try {
			
			changeInfoS.changeInfo(dto.getId(), newPwd1, newPwd2, newNick, newPhone, newPhoto);
			return "/member/m7_myPage";
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}


}




