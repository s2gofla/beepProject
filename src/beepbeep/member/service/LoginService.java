package beepbeep.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.member.dao.MemberDAO;
import beepbeep.member.dto.LoginDTO;
import beepbeep.member.dto.LoginView;
import beepbeep.member.dto.MemberDTO;

public class LoginService {
	private MemberDAO dao = MemberDAO.getInstance();
	

	
	public LoginView login(String id, String password) throws SQLException, NamingException {
		
		 Connection conn =ConnectionProvider.getConnection();

		LoginView view = new LoginView();
		LoginDTO dto = dao.selectById(conn, id);
		
		System.out.println(dto);
		if(dto.getId() == null) {
			// 없는 아이디
			view.setErrcode(1);
			return view;
		}else{
			view.setDto(dto);
			if(!dto.matchPassword(password)) {
				// 비밀번호 오류
				view.setErrcode(2);
			}
			return view;
		}

			
				
	}


}
