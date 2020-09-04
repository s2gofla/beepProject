package beepbeep.review.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.review.dao.ReviewDAO;
import beepbeep.review.model.MreviewDTO;

public class MreviewService {

	private static MreviewService service = null;
	
	public static MreviewService getInstance() {
		if(service == null) {
			service = new MreviewService();
		}
		return service;
	}

	public int write(MreviewDTO dto) {
		ReviewDAO dao = ReviewDAO.getInstance();
		Connection con = null;
		
		try {
			
			
			con = ConnectionProvider.getConnection();
			int result = dao.Minsert(con, dto);
			
			return result;
			
			
		} catch (SQLException e) {
			
			JdbcUtil.rollback(con);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(con);
			throw e;
		} catch (NamingException e) {  
			JdbcUtil.rollback(con);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con);
		}
		
		
		return 0;
	}
	
	
}
