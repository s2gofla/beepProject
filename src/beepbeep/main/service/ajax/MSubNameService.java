package beepbeep.main.service.ajax;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.main.dao.MainAjaxDAO;
import beepbeep.main.dto.MSubnameDTO;

public class MSubNameService {
	private static MSubNameService instance = new MSubNameService();
	public static MSubNameService getInstance() {
		return instance;
	}
	private MSubNameService() { }
	public MSubnameDTO msubNameList(int m_sub_seq) throws NamingException, SQLException {
		Connection conn = ConnectionProvider.getConnection();
		
		MainAjaxDAO dao = MainAjaxDAO.getInstance();
		
		List<String> list = dao.msubNameList(conn, m_sub_seq);
		int cnt = dao.msubNameCount(conn, m_sub_seq);
		MSubnameDTO dto = new MSubnameDTO(list, cnt);
		
		return dto;
	}
	
	
}
