package beepbeep.review.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.util.ConnectionProvider;

import beepbeep.review.dao.ReviewDAO;
import beepbeep.review.model.HinfoDTO;
import beepbeep.review.model.HreviewDTO;
import beepbeep.review.model.PageBlock;


public class HinfoDetailService {
	
	private static HinfoDetailService service = null;
	
	public static HinfoDetailService getInstance() {
		if(service == null) {
			service = new HinfoDetailService();
		}
		return service;
	}

	private HinfoDetailService() {

	}




	public HinfoDTO select(int h_code, String id) {
		
		
		HinfoDTO dto = null;
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		try(Connection con = ConnectionProvider.getConnection()) {
			
			dto = dao.selectHinfo(con, h_code, id);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	public ArrayList<HreviewDTO> selecList(int h_code, PageBlock paging, String id) {
		
		ArrayList<HreviewDTO> list = null;
		ReviewDAO dao = ReviewDAO.getInstance();
		
		try (Connection con = ConnectionProvider.getConnection()){
			
			list = dao.selectReview(con, h_code, paging, id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	public PageBlock getPage(int h_code, PageBlock paging) {
		
		PageBlock pageBlock = new PageBlock();
		ReviewDAO dao = ReviewDAO.getInstance();
		
		try(Connection con = ConnectionProvider.getConnection()) {
			
			pageBlock = dao.selectHRevPage(con, h_code, paging);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pageBlock;
	
	}

	
	
}
