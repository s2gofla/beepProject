package beepbeep.main.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.util.ConnectionProvider;

import beepbeep.main.dao.MainDAO;
import beepbeep.review.dao.ReviewDAO;
import beepbeep.review.model.HreviewDTO;
import beepbeep.review.model.PageBlock;

public class TreatSchService {
	private static TreatSchService service = null;
	public static TreatSchService getInstance() {
		if(service == null) {
			service = new TreatSchService();
		}
		return service;
	}

	private TreatSchService() {	}
	
	public ArrayList<HreviewDTO> selecList(String search, PageBlock paging, String id) {
		
		ArrayList<HreviewDTO> list = null;
		MainDAO dao = MainDAO.getInstance();
		
		try (Connection con = ConnectionProvider.getConnection()){
			
			list = dao.selectReview(con, search, paging, id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
public PageBlock getPage(String search, PageBlock paging) {
		
		PageBlock pageBlock = new PageBlock();
		MainDAO dao = MainDAO.getInstance();
		
		try(Connection con = ConnectionProvider.getConnection()) {
			
			pageBlock = dao.selectHRevPage(con, search, paging);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pageBlock;
	
	}

	
}
