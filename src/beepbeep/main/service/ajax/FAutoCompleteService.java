package beepbeep.main.service.ajax;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.main.dao.MainAjaxDAO;

public class FAutoCompleteService {
	
	private static FAutoCompleteService instance = new FAutoCompleteService();
	public static FAutoCompleteService getInstance(){
		return instance;
	}
	private FAutoCompleteService() { }
	public List<String> autoList(String autoWord) throws NamingException, SQLException {
		List<String> list1 = null;
		List<String> list2 = null;
		try(Connection conn = ConnectionProvider.getConnection();){
			MainAjaxDAO dao = MainAjaxDAO.getInstance();
			
			list1 = dao.autoFList(conn, autoWord);
			
			list2 = dao.autoFFList(conn, autoWord);
			if(!list2.isEmpty()) {
				list1.addAll(list2);				
			}
			
		}
		
		return list1;
	}
	
	
	
}
