package beepbeep.member.service.ajax;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.member.dao.MemberAjaxDAO;

public class NicknameCheckService {

private static NicknameCheckService instance = new NicknameCheckService();
	
	public static NicknameCheckService getInstance() {
		return instance;
	}
	
	private NicknameCheckService() {}

	public int nicknameCheck(String nickname) throws NamingException, SQLException {
		Connection conn = ConnectionProvider.getConnection();
		
		MemberAjaxDAO dao = MemberAjaxDAO.getInstance();
		
		int result = dao.checkedNickname(conn, nickname);
		
		return result;
	}
}
