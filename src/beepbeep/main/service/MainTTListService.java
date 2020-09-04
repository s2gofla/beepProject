package beepbeep.main.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.information.dto.ToptipListDTO;
import beepbeep.main.dao.MainDAO;

public class MainTTListService {
	private static MainTTListService instance = new MainTTListService();
	public static MainTTListService getInstance() {
		return instance;
	}
	private MainTTListService() { }
	public List<ToptipListDTO> ttList() throws NamingException, SQLException {
		Connection conn = ConnectionProvider.getConnection();
		
		MainDAO dao = MainDAO.getInstance();
		List<ToptipListDTO> list = dao.ttSelect(conn);
		
		return list;
	}
	
	
	
}
