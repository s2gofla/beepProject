package beepbeep.review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.command.CategoryDAO;
import beepbeep.command.CategoryDTO;
import beepbeep.review.dao.ReviewDAO;
import beepbeep.review.model.CategoryModalDTO;
import beepbeep.review.model.HinfoDTO;
import beepbeep.review.model.HreivewPicDTO;
import beepbeep.review.model.HreviewDTO;
import beepbeep.review.model.HreviewTreatDTO;

public class HreviewService {
	
	
	private static HreviewService service = null;
	
	public static HreviewService getInstance() {
		if(service == null) {
			service = new HreviewService();
		}
		return service;
	}

	private HreviewService() {

	}

	public int write(HreviewDTO dto) {
		
		ReviewDAO dao = ReviewDAO.getInstance();
		Connection con = null;
		
		try {
			
			
			con = ConnectionProvider.getConnection();
			
			
			int result = dao.insert(con, dto);
			int h_review_code = dao.selectHreviewCode(dto);
			
			if(dto.getCertification() != null) {
				String certification_attach = dto.getCertification();
				dao.insertCerti(con, h_review_code, certification_attach);
			}
			
			Iterator<HreviewTreatDTO>priceir = dto.getTreatment().iterator();
			while (priceir.hasNext()) {
				HreviewTreatDTO treatDTO = priceir.next();
				int treatment_code = treatDTO.getTreatment_code();
				int price = treatDTO.getPrice();
				dao.insertTreatCode(con, h_review_code, treatment_code, price);
				
			}
			
			
			
			
			if (dto.getPicture() != null) {
				
				Iterator<HreivewPicDTO>ir = dto.getPicture().iterator();
				while (ir.hasNext()) {
					HreivewPicDTO picDTO = ir.next();
					String pic = picDTO.getPic();
					dao.insertPic(con, h_review_code, pic);
					
				}
				
			}
			
			
			
		
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

	public ArrayList<HinfoDTO> selectHname() {
		
		ReviewDAO dao = ReviewDAO.getInstance();
		Connection con = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
			ArrayList<HinfoDTO> list = null;
			
			list =  dao.selectHname(con);
			
			return list;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public List<CategoryModalDTO> selectCategory(int h_code) {
		
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			
			ReviewDAO categorydao = ReviewDAO.getInstance();
			
			List<CategoryModalDTO> categoryList = categorydao.selectCategoryList(con,h_code);
			
			return categoryList;
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(con);
		}
		return null;
	}

	public List<CategoryDTO> CaategryList(int h_code) {
	
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			ReviewDAO categorydao = ReviewDAO.getInstance();
			
			List<CategoryDTO> categorylist = categorydao.HsubList(conn, h_code);
			
			return categorylist;
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return null;
	}
	
	
}
