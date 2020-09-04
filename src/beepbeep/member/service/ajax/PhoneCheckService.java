package beepbeep.member.service.ajax;


import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.member.dao.MemberAjaxDAO;


public class PhoneCheckService {
	private static PhoneCheckService instance = new PhoneCheckService();
	
	public static PhoneCheckService getInstance() {
		return instance;
	}
	
	private PhoneCheckService() {}

	public int phoneCheck(String tel) throws NamingException, SQLException {
		Connection conn = ConnectionProvider.getConnection();
		MemberAjaxDAO dao = MemberAjaxDAO.getInstance();
		
		int result = dao.checkedPhone(conn, tel);
		
		return result;
		
	}
}
