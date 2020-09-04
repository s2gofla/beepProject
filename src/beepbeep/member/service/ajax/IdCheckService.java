package beepbeep.member.service.ajax;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.member.dao.MemberAjaxDAO;

public class IdCheckService {
	private static IdCheckService instance = new IdCheckService();
	
	public static IdCheckService getInstance() {
		return instance;
	}
	
	private IdCheckService() {}

	public int idCheck(String id) throws NamingException, SQLException {
		Connection conn = ConnectionProvider.getConnection();
		
		MemberAjaxDAO dao = MemberAjaxDAO.getInstance();
		
		int result = dao.checkedId(conn, id);
		
		return result;
	}
}
