package beepbeep.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.member.dao.MemberDAO;
import beepbeep.member.dto.MemberDTO;
import beepbeep.member.service.ajax.IdCheckService;

public class SignUpService {
	private static SignUpService instance = new SignUpService();
	
	public static SignUpService getInstance() {
		return instance;
	}
	
	private SignUpService() {}


	
	
	//의사
	public int signUp1(MemberDTO dto) throws NamingException, SQLException {
		
		Connection conn = ConnectionProvider.getConnection();
		
		
		try {
			
			MemberDAO dao = MemberDAO.getInstance();
			
			
			int result = dao.insertMember1(conn, dto);			
			int result2 = dao.insertCertification(conn,dto);
			
			return result+ result2;
			
		}finally {
			JdbcUtil.close(conn);			
		}
	}
	
	
	//일반
	public int signUp2(MemberDTO dto) throws NamingException, SQLException {
		
		Connection conn = ConnectionProvider.getConnection();
		
		
		try {
			
			MemberDAO dao = MemberDAO.getInstance();
			
			
			int result = dao.insertMember2(conn, dto);			
			
			return result;
			
		}finally {
			JdbcUtil.close(conn);			
		}
	}

}
