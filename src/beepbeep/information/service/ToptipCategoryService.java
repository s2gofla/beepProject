package beepbeep.information.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.information.dao.InfoDAO;
import beepbeep.information.dto.ToptipCategoryDTO;


public class ToptipCategoryService {
	private static ToptipCategoryService instance = new ToptipCategoryService();
	public static ToptipCategoryService getInstance() {
		return instance;
	}
	private ToptipCategoryService() {}
	
	public List<ToptipCategoryDTO> toptipCategoryList(){
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			InfoDAO dao = InfoDAO.getInstance();
			
			List<ToptipCategoryDTO> toptipcategoryList = dao.toptipCategory(conn);
			
			return toptipcategoryList;
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return null;
	}
}
