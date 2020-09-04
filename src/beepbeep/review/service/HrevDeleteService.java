package beepbeep.review.service;

import java.sql.Connection;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.review.dao.ReviewDAO;

public class HrevDeleteService {

private static HrevDeleteService service = null;
	
	public static HrevDeleteService getInstance() {
		if(service == null) {
			service = new HrevDeleteService();
		}
		return service;
	}

	private HrevDeleteService() {

	}

	public int delete(int h_review_code) {
		
		Connection con = null;
		int result = 0;
		
		try {
			
			con = ConnectionProvider.getConnection();
			ReviewDAO dao = ReviewDAO.getInstance();
			
			result = dao.delete(con,h_review_code);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			JdbcUtil.close(con);
		}
		
		return result;
	}
	
}
