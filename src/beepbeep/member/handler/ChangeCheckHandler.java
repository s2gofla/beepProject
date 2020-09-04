package beepbeep.member.handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.member.dto.LoginDTO;
import beepbeep.member.service.ChangeCheckService;
import beepbeep.member.service.ChangeInfoService;
import beepbeep.member.service.InvaildPasswordException;
import beepbeep.member.service.WithdrawalService;


public class ChangeCheckHandler implements CommandHandler{
	private static final String FORM_VIEW = "/member/m11_changePWD";

	private ChangeCheckService changeCheckS = new ChangeCheckService();



	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("GET");
			return processForm(request,response);

		}
		else if(request.getMethod().equalsIgnoreCase("POST")){
			System.out.println("POST");
			return processSubmit(request,response);

		}
		else {
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}

	}



	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		String c = request.getParameter("change");
		request.setAttribute("c",c);

		return FORM_VIEW;
		
	}



	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws NamingException, SQLException, IOException, ServletException {

		LoginDTO dto = (LoginDTO)request.getSession().getAttribute("authUser");

		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);

		String curPwd1 = request.getParameter("curPwd1");
		String c = request.getParameter("change");

	
		
		try {

			
		
			System.out.println(dto.getPassword());
			System.out.println(curPwd1);
			
			if(c.equals("1")) {
				changeCheckS.changeCheck( dto.getPassword(), curPwd1);
				return "/member/m8_infoChange";
			}
			else if(c.equals("2")) {
				changeCheckS.changeCheck( dto.getPassword(), curPwd1);
				return "/member/m9_clauseChange";
			}
			else if(c.equals("3")) {

				changeCheckS.changeCheck( dto.getPassword(), curPwd1);
				response.sendRedirect(request.getContextPath()+"/member/withdrawal.do"); 
				return null;
			}
			else {
				return  "/member/m7_myPage";

			}


		}catch (InvaildPasswordException e) {
			errors.put("badCurPwd", Boolean.TRUE); 
			return FORM_VIEW;
			}
			



		}

	}



