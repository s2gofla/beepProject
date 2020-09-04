package beepbeep.information.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.information.dao.SoreSpotCategoryDAO;
import beepbeep.information.dto.SoreSpotCategoryDTO;


public class SoreSpotCategoryService {
	private static SoreSpotCategoryService instance = new SoreSpotCategoryService();
	
	public static SoreSpotCategoryService getInstance() {
		return instance;
	}
	private SoreSpotCategoryService() {}
	
	public List<SoreSpotCategoryDTO> SoreSpotCategoryList(){
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			SoreSpotCategoryDAO sorespotcategorydao = SoreSpotCategoryDAO.getInstance();
			
			List<SoreSpotCategoryDTO> sorespotcategoryList = sorespotcategorydao.selectList(conn);
			
			return sorespotcategoryList;
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return null;
	}
}
