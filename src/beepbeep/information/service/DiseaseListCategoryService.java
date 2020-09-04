package beepbeep.information.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.information.dao.InfoDAO;
import beepbeep.information.dto.DiseaseListCategoryDTO;
 

public class DiseaseListCategoryService {
	private static DiseaseListCategoryService instance = new DiseaseListCategoryService();
	
	public static DiseaseListCategoryService getInstance() {
		return instance;
	}
	private DiseaseListCategoryService() {}
	
	public List<DiseaseListCategoryDTO> diseasecategoryList(){
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			InfoDAO dao = InfoDAO.getInstance();
			
			List<DiseaseListCategoryDTO> list = dao.diseaselistCategory(conn);
			
			return list;
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return null;
	}
}
