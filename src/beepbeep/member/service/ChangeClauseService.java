package beepbeep.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.member.dao.MemberDAO;
import beepbeep.member.dto.ChangeClauseDTO;
import beepbeep.member.dto.ChangeInfoDTO;

public class ChangeClauseService {
	private MemberDAO dao=MemberDAO.getInstance();
	
	public void changeClause(String userId,  String TC1, String TC2) throws NamingException {
Connection conn =null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
		
			ChangeClauseDTO dto = new ChangeClauseDTO(TC1,TC2);
			
			
			dao.updateClause(conn, dto, userId);
			
			conn.commit();
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
