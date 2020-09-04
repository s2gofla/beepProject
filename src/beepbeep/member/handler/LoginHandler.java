package beepbeep.member.handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beepbeep.command.CommandHandler;
import beepbeep.member.dto.LoginDTO;
import beepbeep.member.dto.LoginView;
import beepbeep.member.dto.MemberDTO;
import beepbeep.member.service.LoginService;

public class LoginHandler implements CommandHandler{

	
	private static final String FORM_VIEW="/member/m6_login";
	private LoginService loginS = new LoginService();
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("get login");
			return processForm(request,response);
		}
		else if(request.getMethod().equalsIgnoreCase("POST")) {
			System.out.println("post login");
			return processSubmit(request,response);
		}
		else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		
	}


	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
		
	}


	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception{
			String id = trim(request.getParameter("id"));
			String password = trim(request.getParameter("password"));
			String checkbox = request.getParameter("checkbox");
			
			
			System.out.println(id);
			System.out.println(password);
			System.out.println(checkbox);

					
	
			
			try {
				
				LoginView view = loginS.login(id, password);
		
				if(view.getErrcode() == 0) {
					noErrorPage(request, response, view);
				}else {
					request.setAttribute("errcode", view.getErrcode());
					return "/member/m6_login";
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		
	}

	private void noErrorPage(HttpServletRequest request, HttpServletResponse response, LoginView view) throws IOException, NamingException, SQLException {
		String checkbox =trim( request.getParameter("checkbox"));
		String id = trim(request.getParameter("id"));
		
		
		request.getSession().setAttribute("authUser", view.getDto());

		
		
		System.out.println("noerr");
		System.out.println(view.getDto());

		
		if(checkbox != null) {
			Cookie cookie = new Cookie("checkbox", id);
			cookie.setMaxAge(60*60*24*31);//숫자당 1초라서 31일간 저장
			response.addCookie(cookie);
		}else {
			Cookie cookie = new Cookie("checkbox", id);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		//로그인 되면 정보를 세션값에 넣고 브라우저가 닫힐 때까지 유지
		HttpSession session = request.getSession();
		session.setAttribute("loginMember", view.getDto());
		
	
		
	
		response.sendRedirect(request.getContextPath()+"/member/loginout.do");			
		
	}


	


	private String trim(String str) {
		return str == null? null : str.trim();
	}

}
