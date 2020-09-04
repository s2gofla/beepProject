package beepbeep.review.service;

import java.sql.Connection;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.review.dao.ReviewDAO;
import beepbeep.review.model.MreviewDTO;

public class MrevUpdateService {
	
	
	private static MrevUpdateService service = null;
	
	public static MrevUpdateService getInstance() {
		if(service == null) {
			service = new MrevUpdateService();
		}
		return service;
	}

	public MreviewDTO getMReview(int m_review_code) {
		MreviewDTO dto = null;
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		try(Connection con = ConnectionProvider.getConnection()) {
			
			dto = dao.selectOneMReview(con, m_review_code);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return dto;
	}

	public int update(MreviewDTO dto) {
		ReviewDAO dao = ReviewDAO.getInstance();
		
		Connection con = null;
		try {
			
			con =  ConnectionProvider.getConnection();
			//리뷰 수정 및 치료항목 사진 수정
			con.setAutoCommit(false);
			
			int result =  dao.Mupdate(con,dto);
		
			//dao.updatePic(con,dto);
			
			con.commit();
			return result;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
			JdbcUtil.close(con);
		}
		
		
		return 0;
	}
	
}
