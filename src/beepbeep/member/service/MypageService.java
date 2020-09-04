package beepbeep.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.security.auth.message.callback.SecretKeyCallback.Request;
import javax.websocket.Session;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.member.dao.MemberDAO;
import beepbeep.member.dto.LoginDTO;
import beepbeep.member.dto.MemberDTO;

public class MypageService {
	private MemberDAO dao = MemberDAO.getInstance();
	
	public int gradeUp(String id,int Numgrade) throws NamingException,SQLException {
		Connection conn=ConnectionProvider.getConnection();
		LoginDTO dto = new LoginDTO();
		
		try{ 

			int result = dao.gradeUp(conn, id);
			if(result ==1) {
				Numgrade = Numgrade+1;
			}
			


		}catch(SQLException e) {
			System.out.println("마이페이지서비스 오류");
			JdbcUtil.rollback(conn);

		}finally {
			JdbcUtil.close(conn); 
		}
		return Numgrade;
	}

	
	
	public MemberDTO gradeName(int numgrade) throws NamingException, SQLException {
		Connection conn=ConnectionProvider.getConnection();
		
		try{ 

			MemberDTO dto = dao.gradeName(conn, numgrade);

			return dto;
		}finally {
			JdbcUtil.close(conn); 
		}
	}
	
}
