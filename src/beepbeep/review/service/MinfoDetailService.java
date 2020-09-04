package beepbeep.review.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.util.ConnectionProvider;

import beepbeep.review.dao.ReviewDAO;
import beepbeep.review.model.MinfoDTO;
import beepbeep.review.model.MreviewDTO;
import beepbeep.review.model.PageBlock;

public class MinfoDetailService {
	
	private static MinfoDetailService service = null;
	
	public static MinfoDetailService getInstance() {
		if(service == null) {
			service = new MinfoDetailService();
		}
		return service;
	}

	public MinfoDTO select(int m_code, String id) {
		
		MinfoDTO dto = null;
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		try(Connection con = ConnectionProvider.getConnection()) {
			
			dto = dao.selectMinfo(con, m_code, id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	public ArrayList<MreviewDTO> selecList(int m_code, PageBlock paging, String id) {
		
		ArrayList<MreviewDTO> list = null;
		ReviewDAO dao = ReviewDAO.getInstance();
		
		try (Connection con = ConnectionProvider.getConnection()){
			
			list = dao.selectMReview(con, m_code, paging, id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	public PageBlock getPage(int m_code, PageBlock paging) {
		
		PageBlock pageBlock = new PageBlock();
		ReviewDAO dao = ReviewDAO.getInstance();
		
		try(Connection con = ConnectionProvider.getConnection()) {
			
			pageBlock = dao.selectMRevPage(con, m_code, paging);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pageBlock;
		
	}
	
	
}
