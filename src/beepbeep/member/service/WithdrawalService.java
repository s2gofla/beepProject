package beepbeep.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.member.dao.MemberDAO;


public class WithdrawalService {
	private MemberDAO dao=MemberDAO.getInstance();
	
	public void withdrawal(String userId) throws NamingException {
		Connection conn =null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			System.out.println(userId);
			
			dao.deleteMember(conn, userId);
			
			
			conn.commit();
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
