package beepbeep.review.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.util.ConnectionProvider;

import beepbeep.review.dao.ReviewDAO;
import beepbeep.review.model.MinfoDTO;
import beepbeep.review.model.MpurposeDTO;
import beepbeep.review.model.PageBlock;
import beepbeep.review.model.SpecialDTO;

public class MinfoService {

	private static MinfoService service = null;
	
	public static MinfoService getInstance() {
		if(service == null) {
			service = new MinfoService();
		}
		return service;
	}

	private MinfoService() {

	}

	public ArrayList<MinfoDTO> select(String searchWord, String mpurpose, PageBlock paging, String id, int num) {
		System.out.println("list목록 호출");
		if (!mpurpose.equals("*")) {
			String[] mpurpose2 = mpurpose.split(",");
			mpurpose = "(";
			for (int i = 0; i < mpurpose2.length; i++) {
				mpurpose += i == mpurpose2.length-1 ? Integer.parseInt(mpurpose2[i]) : Integer.parseInt(mpurpose2[i])+",";
			
			}
			mpurpose += ")";
		}
		

		
		ArrayList<MinfoDTO> list = null;
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		try(Connection con = ConnectionProvider.getConnection()) {
			
			list = dao.selectMinfo(con, mpurpose, searchWord, paging, id, num);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<MpurposeDTO> purposeList() {
		
		ArrayList<MpurposeDTO> list = null;
		ReviewDAO dao = ReviewDAO.getInstance();
		
		try(Connection con = ConnectionProvider.getConnection()) {
			
			list = dao.selectMpurposeList(con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	
	}

	public PageBlock getPage(String searchWord, String mpurpose, PageBlock paging) {
		
		PageBlock pageBlock = new PageBlock();
		ReviewDAO dao = ReviewDAO.getInstance();
		
		if (!mpurpose.equals("*")) {
			String[] mpurpose2 = mpurpose.split(",");
			mpurpose = "(";
			for (int i = 0; i < mpurpose2.length; i++) {
				mpurpose += i == mpurpose2.length-1 ? Integer.parseInt(mpurpose2[i]) : Integer.parseInt(mpurpose2[i])+",";
			
			}
			mpurpose += ")";
		}
		
		try(Connection con = ConnectionProvider.getConnection()) {
			
			pageBlock = dao.selectMPage(con, searchWord, mpurpose, paging);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pageBlock;
	}
	
}
