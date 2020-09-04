package beepbeep.member.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beepbeep.command.CommandHandler;
import beepbeep.member.dto.LoginDTO;
import beepbeep.member.service.ChangeCheckService;
import beepbeep.member.service.InvaildPasswordException;
import beepbeep.member.service.WithdrawalService;

public class WithdrawalHandler implements CommandHandler{
	private static final String FORM_VIEW = "/member/m11_changePWD";
	

	private WithdrawalService withdrawalS = new WithdrawalService();


	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		
		LoginDTO dto = (LoginDTO)request.getSession().getAttribute("authUser");

		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);



		
		
		
		try {
	
		
				withdrawalS.withdrawal(dto.getId());
				
				
				HttpSession session = request.getSession(false);
				

				if(session != null) {
					session.invalidate();
				}
				
				
				
				return "/member/m16_withdrawalFinish";



		}catch (InvaildPasswordException e) {
			errors.put("idontknow", Boolean.TRUE); 
			return FORM_VIEW;
			}		

		
	}
	
}
