package beepbeep.command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.qna.service.QnaListService;

public class CategoryService {
	private static CategoryService instance = new CategoryService();
	
	public static CategoryService getInstance() {
		return instance;
	}
	private CategoryService() {}
	
	public List<CategoryDTO> CategoryList(){
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			CategoryDAO categorydao = CategoryDAO.getInstance();
			
			List<CategoryDTO> categoryList = categorydao.selectList(conn);
			
			return categoryList;
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return null;
	}
}
