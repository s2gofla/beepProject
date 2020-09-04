package beepbeep.member.service.ajax;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.member.dao.MemberAjaxDAO;

public class FindPwdCheckService {

	private static FindPwdCheckService instance = new FindPwdCheckService();

	public static FindPwdCheckService getInstance() {
		return instance;
	}
	
	private FindPwdCheckService() {}
	
	public String findPwd(String name, String birth, String id) throws NamingException, SQLException {
		
		Connection conn = ConnectionProvider.getConnection();
		MemberAjaxDAO dao = MemberAjaxDAO.getInstance();
	
		String result = dao.findPwd(conn, name,birth, id);
		return result; 
		
	}


}
