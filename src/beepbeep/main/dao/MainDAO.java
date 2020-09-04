package beepbeep.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.information.dto.ToptipListDTO;
import beepbeep.review.model.HreivewPicDTO;
import beepbeep.review.model.HreviewDTO;
import beepbeep.review.model.HreviewTreatDTO;
import beepbeep.review.model.PageBlock;

public class MainDAO {
	private static MainDAO instance = new MainDAO();
	public static MainDAO getInstance() {
		return instance;
	}
	private MainDAO() { }
	public List<ToptipListDTO> ttSelect(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ToptipListDTO> list = null;
		String sql ="select *        " + 
				"from (select rownum no, a.*        " + 
				"        from (        " + 
				"            select toptip_board.tt_code, pic, title, dates, views        " + 
				"            from toptip_board join toptip_attach on(toptip_board.tt_code = toptip_attach.tt_code)        " + 
				"            where pic like '%t.j%'       " + 
				"            order by dates desc)a       " + 
				"            )       " + 
				"where no<=3      ";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<ToptipListDTO>();
			while(rs.next()) {
				ToptipListDTO dto = new ToptipListDTO();
				dto.setTt_code(rs.getInt("tt_code"));
				dto.setPic(rs.getString("pic"));
				dto.setTitle(rs.getString("title"));
				dto.setDates(rs.getString("dates"));
				//System.out.println(dto.getTitle());
				list.add(dto);
			}
			return list;
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	//한 병원의 리뷰 목록가져올 메서드
	public ArrayList<HreviewDTO> selectReview(Connection con, String search, PageBlock paging, String id) {
		
		
		HreviewDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<HreviewDTO> list = null;
		ArrayList<HreviewTreatDTO> treatList = null;
		ArrayList<HreivewPicDTO> picList = null;
		paging.setNumberPerPage(4);
		
		int start = (paging.getCurrentPage()-1)*paging.getNumberPerPage()+1;
		int end = paging.getCurrentPage()*paging.getNumberPerPage();
		
		
		
		String sql = "select rownum, t.*  " + 
				"from ( select  h.*,tt.*,m.nickname , (select sum(score_kind + score_price +score_result +  score_satisfaction)/4 from h_review r where h.h_review_code= r.h_review_code) star_score     " + 
				"        from h_review h join memberT m on h.id = m.id  \r\n" + 
				"                        join review_treatment rt on (h.h_review_code = rt.h_review_code)\r\n" + 
				"                        join treatment tt on(rt.treatment_code = tt.treatment_code)\r\n" + 
				"				where regexp_like(treatment_name, ?)       \r\n" + 
				"				order by dates desc) t    "
				+ "where rownum between ? and ?  ";
		
		try {
			list = new ArrayList<HreviewDTO>();
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, search);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				dto = new HreviewDTO();
				int h_review_code = rs.getInt("h_review_code");
				
				dto.setH_review_code(rs.getInt("h_review_code"));
				dto.setId(rs.getString("id"));
				
				dto.setScore_kind(rs.getInt("score_kind"));
				dto.setScore_price(rs.getInt("score_price"));
				dto.setScore_result(rs.getInt("score_result"));
				dto.setScore_satisfaction(rs.getInt("score_satisfaction"));
			
				dto.setReview_type(rs.getInt("review_type"));
				dto.setDates(rs.getDate("dates"));
				dto.setLikes(rs.getInt("likes"));
				dto.setContents(rs.getString("contents"));
				dto.setNickname(rs.getString("nickname"));
				dto.setStar_score(Math.round(rs.getDouble("star_score")*10)/10.0);
				
				picList = selectPic(h_review_code);
				dto.setPicture(picList);
				
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	return list;
	}
	
	//해당병원 해당회원이 쓴  사진리스트 가져오기
	private ArrayList<HreivewPicDTO> selectPic(int h_review_code) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<HreivewPicDTO> list = null;
		HreivewPicDTO dto = null;
		
		try {
			con = ConnectionProvider.getConnection();
			list = new ArrayList<HreivewPicDTO>();
			
			String sql = "select * from hreview_pic where h_review_code = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, h_review_code);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				dto = new HreivewPicDTO();
				
				dto.setH_review_code(rs.getInt("h_review_code"));
				dto.setHreview_seq(rs.getInt("hreview_seq"));
				dto.setPic(rs.getString("picture"));
					
				list.add(dto);
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
	
		return list;
	}

	//해당병원 해당회원이 쓴 치료항목 리스트 가져오기
	private ArrayList<HreviewTreatDTO> selectTreat(int h_review_code) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<HreviewTreatDTO> list = null;
		HreviewTreatDTO dto = null;
		
		try {
			con = ConnectionProvider.getConnection();
			list = new ArrayList<HreviewTreatDTO>();
			
			String sql = "select * from htreatment where h_review_code=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, h_review_code);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				dto = new HreviewTreatDTO();
				
				dto.setTreatment_code(rs.getInt("treatment_code"));
				dto.setM_sub_name(rs.getString("m_sub_name"));
				dto.setTreatment_name(rs.getString("treatment_name"));
				dto.setPrice(rs.getInt("price"));
				dto.setRtreatment_seq(rs.getInt("rtreatment_seq"));
				
				list.add(dto);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return list;
	}
	
	public PageBlock selectHRevPage(Connection con, String search, PageBlock pageBlock) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "select count(*) totalRecords , ceil(count(*)/?) totalPages\r\n" + 
				"from h_review hr join review_treatment rt on (hr.h_review_code = rt.h_review_code)\r\n" + 
				"                 join treatment tt on(rt.treatment_code = tt.treatment_code)\r\n" + 
				"where regexp_like(treatment_name, ?)";
		
		
		int totalPages =0;
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pageBlock.getNumberPerPage());
			pstmt.setString(2, search);
			
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				totalPages = rs.getInt("totalPages");
			}
			
		} catch (Exception e) {
			System.out.println("페이징객체 오류");
			e.printStackTrace();
		} finally {
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
			
		}
		
		int pageBlockStart = 1, pageBlockEnd = 3;
		pageBlockStart = (pageBlock.getCurrentPage() - 1)/pageBlock.getNumberOfPageBlocks() *pageBlock.getNumberOfPageBlocks()+1;
		pageBlockEnd = pageBlockStart + pageBlock.getNumberOfPageBlocks()-1;
		
		if (pageBlockEnd > totalPages) {
			pageBlockEnd = totalPages;
		}
		
		pageBlock.setStart(pageBlockStart);
		pageBlock.setEnd(pageBlockEnd);
		
		
		pageBlock.setPrev(pageBlockStart!=1 ? true : false);
		
		pageBlock.setNext(pageBlockEnd!=totalPages? true : false);
		
		return pageBlock;
		
	}
	
	
	
}
