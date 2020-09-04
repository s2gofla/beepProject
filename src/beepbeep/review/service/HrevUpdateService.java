package beepbeep.review.service;

import java.sql.Connection;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.review.dao.ReviewDAO;
import beepbeep.review.model.HreviewDTO;

public class HrevUpdateService {

	
	private static HrevUpdateService service = null;
	
	public static HrevUpdateService getInstance() {
		if(service == null) {
			service = new HrevUpdateService();
		}
		return service;
	}

	public HreviewDTO getReview(int h_review_code) {
		
		HreviewDTO dto = null;
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		try(Connection con = ConnectionProvider.getConnection()) {
			
			dto = dao.selectOneReview(con, h_review_code);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return dto;
	}

	public int update(HreviewDTO dto) {
		
		
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		Connection con = null;
		try {
			
			con =  ConnectionProvider.getConnection();
			//리뷰 수정 및 치료항목 사진 수정
			con.setAutoCommit(false);
			
			int result =  dao.update(con,dto);
			dao.updateTreat(con,dto);
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
