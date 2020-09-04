package beepbeep.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.member.dao.MemberDAO;
import beepbeep.member.dto.LoginDTO;
import beepbeep.member.dto.MemberDTO;

public class ChangeCheckService {
	
	private MemberDAO dao=MemberDAO.getInstance();

	public void changeCheck( String userPwd, String curPwd) throws NamingException {
		
		Connection conn =null;

		
		
		
		try {
			conn = ConnectionProvider.getConnection();
			
	

			
			if(!userPwd.equals(curPwd)) {
				System.out.println("서비스의 저장된값과 입력한 값이 다르다");
				throw new InvaildPasswordException();
			
				
			}	
			
			
			
		}catch(SQLException e) {
			
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
}
