package test;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ConnectionProvider;

import beepbeep.command.CommandHandler;

public class TestHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("testhandler");
		Connection conn = ConnectionProvider.getConnection();
		TestDAO dao = TestDAO.getInstance();
		Message msg = dao.select(conn);
		
		request.setAttribute("msg", msg);
		return "/main/test";
	}

}
