package beepbeep.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beepbeep.command.CommandHandler;

public class SignupHandler implements CommandHandler{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String ctype = request.getParameter("ctype")==null?"0":request.getParameter("ctype");
		String mcode = request.getParameter("mcode")==null?"0":request.getParameter("mcode");
		String gcode = request.getParameter("gcode")==null?"0":request.getParameter("gcode");
		
		if(ctype.equals("0")) {
			
			return "/member/m1_type";
		}else if(!ctype.equals("0") && mcode.equals("0")) {
			return "/member/m2_clause";
		}else if(ctype.equals("1") && !mcode.equals("0")) {
			return "/member/m3_2_GPC";
		}else if(ctype.equals("2") && !mcode.equals("0")) {
			return "/member/m3_DNC";
		}

		return "/member/m1_type";
	}

}
