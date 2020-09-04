package beepbeep.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.member.dao.MemberDAO;
import beepbeep.member.dto.ChangeInfoDTO;
import beepbeep.member.dto.MemberDTO;

public class ChangeInfoService {
	
	private MemberDAO dao=MemberDAO.getInstance();
	
	public void changeInfo(String userId,  String newPwd1, String newPwd2, String newNick, String newPhone, String newPhoto) throws NamingException {
		Connection conn =null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			

			ChangeInfoDTO dto = new ChangeInfoDTO(newPwd1, newNick, newPhone, newPhoto);

			
			dao.updateInfo(conn, dto, userId);
			conn.commit();
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
