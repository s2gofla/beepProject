package beepbeep.member.service.ajax;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.member.dao.MemberAjaxDAO;

public class FindIdCheckService {
	
	private static FindIdCheckService instance = new FindIdCheckService();
	
	public static FindIdCheckService getInstance() {
		return instance;
	}
	
	private FindIdCheckService() {}

	public String findId(String tel) throws NamingException, SQLException {
		Connection conn = ConnectionProvider.getConnection();
		MemberAjaxDAO dao = MemberAjaxDAO.getInstance();
		
		String result = dao.findId(conn, tel);
		
		return result;
	}
}
