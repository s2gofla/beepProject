package beepbeep.review.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.util.ConnectionProvider;

import beepbeep.review.dao.ReviewDAO;
import beepbeep.review.model.HinfoDTO;

import beepbeep.review.model.SpecialDTO;


public class HinfoService {
	
	private static HinfoService service = null;
	
	public static HinfoService getInstance() {
		if(service == null) {
			service = new HinfoService();
		}
		return service;
	}

	private HinfoService() {

	}


	public ArrayList<SpecialDTO> specialList() {
	
		ArrayList<SpecialDTO> list = null;
		ReviewDAO dao = ReviewDAO.getInstance();
		
		try(Connection con = ConnectionProvider.getConnection()) {
			
			list = dao.selectSpeciality(con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
		
	}

	public ArrayList<HinfoDTO> select(String searchWord, String sub, String special, String id) {
		
		if (!sub.equals("*")) {
			
			String[] sub2 = sub.split(","); //sub2[0] 1 sub2[1] 2 sub3 [2] 3
			sub="(";
			for (int i = 0; i < sub2.length; i++) {
				sub += i== sub2.length-1 ? Integer.parseInt(sub2[i]) : Integer.parseInt(sub2[i])+",";
				
				
			}
			sub+=")";
			
		}
		
		if(!special.equals("*")) {
			
			String [] special2 = special.split(",");
			special = "(";
			for (int i = 0; i < special2.length; i++) {
				special += i== special2.length-1 ? Integer.parseInt(special2[i]) : Integer.parseInt(special2[i])+",";
			}
			special+=")";
		}
		
		
		ArrayList<HinfoDTO> list = null;
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		try(Connection con = ConnectionProvider.getConnection()) {
			
			list = dao.selectHinfo(con, sub, special, searchWord,id);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}


	
	
}
