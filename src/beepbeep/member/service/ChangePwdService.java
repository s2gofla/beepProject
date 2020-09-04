package beepbeep.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.member.dao.MemberDAO;
import beepbeep.member.dto.ChangeInfoDTO;
import beepbeep.member.dto.MemberDTO;

public class ChangePwdService {
	
	private MemberDAO dao=MemberDAO.getInstance();
	
	public void changePwd(String id, String newPwd1, String newPwd2) throws NamingException {
		Connection conn =null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			

		

			
			dao.updatePwd(conn, id, newPwd1);
			
			conn.commit();
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
