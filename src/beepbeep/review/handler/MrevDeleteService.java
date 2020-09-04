package beepbeep.review.handler;

import java.sql.Connection;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.review.dao.ReviewDAO;


public class MrevDeleteService {
	
	private static MrevDeleteService service = null;
	
	public static MrevDeleteService getInstance() {
		if(service == null) {
			service = new MrevDeleteService();
		}
		return service;
	}

	private MrevDeleteService() {

	}

	public int delete(int m_review_code) {
		
		Connection con = null;
		int result = 0;
		
		try {
			
			con = ConnectionProvider.getConnection();
			ReviewDAO dao = ReviewDAO.getInstance();
			
			result = dao.Mdelete(con,m_review_code);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			JdbcUtil.close(con);
		}
		
		return result;
	}
}
