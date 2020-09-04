package beepbeep.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;
import beepbeep.member.dto.LoginDTO;
import beepbeep.member.dto.LoginView;
import beepbeep.member.dto.MemberDTO;
import beepbeep.member.service.LoginService;
import beepbeep.member.service.MypageService;

public class MypageHandler implements CommandHandler{

	private static final String FORM_VIEW="/member/m7_myPage";
	private MypageService mypageS = new MypageService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("get");
			return processSubmit(request,response);
		}
		else if(request.getMethod().equalsIgnoreCase("POST")) {
			System.out.println("post");
			return processSubmit(request,response);
		}
		else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		LoginDTO dto = (LoginDTO) request.getSession().getAttribute("authUser");	
		
		String id = dto.getId();
		int Numgrade = dto.getMgrade_code();
		
		
		
		try {

			
			int num = mypageS.gradeUp(id,Numgrade);
			Numgrade = num;
					
			
			MemberDTO mdto = mypageS.gradeName(Numgrade);

			System.out.println("핸들러=="+mdto.getGrade());
			
			request.setAttribute("gradeName", mdto.getGradeName());
			request.setAttribute("grade",mdto.getGrade() );
			return FORM_VIEW;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}


}
