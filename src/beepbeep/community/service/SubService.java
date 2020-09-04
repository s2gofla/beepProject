package beepbeep.community.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.community.dao.SubDAO;
import beepbeep.community.dto.FreeListView;
import beepbeep.community.dto.SubDTO;

public class SubService {
	private static SubService instance = new SubService();

	public static SubService getInstance() {
		return instance;
	}

	private SubService() {}

	
	public List<SubDTO> getSubList() throws NamingException, SQLException { 
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			SubDAO subdao = SubDAO.getInstance();
			
			List<SubDTO> subList = subdao.selectList(conn);
			return subList;
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
