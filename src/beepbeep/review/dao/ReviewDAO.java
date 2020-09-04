package beepbeep.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.command.CategoryDTO;
import beepbeep.review.model.CategoryModalDTO;
import beepbeep.review.model.HinfoDTO;
import beepbeep.review.model.HreivewPicDTO;
import beepbeep.review.model.HreviewDTO;
import beepbeep.review.model.HreviewTreatDTO;
import beepbeep.review.model.HtimeDTO;
import beepbeep.review.model.MinfoDTO;
import beepbeep.review.model.MpurposeDTO;
import beepbeep.review.model.MreviewDTO;
import beepbeep.review.model.MreviewPicDTO;
import beepbeep.review.model.PageBlock;
import beepbeep.review.model.SpecialDTO;


public class ReviewDAO {
	
	private static ReviewDAO dao = null;
	
	public static ReviewDAO getInstance() {
		
		if (dao == null) {
			dao = new ReviewDAO();
		}
		return dao;
	}
	
	private ReviewDAO () {}

	//특이사항 필터리스트 불러오는 메서드
	public ArrayList<SpecialDTO> selectSpeciality(Connection con) {
		ArrayList<SpecialDTO> list = null;
		SpecialDTO dto = null;
		String sql = "select * from specialty_type";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<SpecialDTO>();
			while(rs.next()) {
				dto = new SpecialDTO();
				dto.setSt_code(rs.getInt("st_code"));
				dto.setSpecialty_name(rs.getString("specialty_name"));
				
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

	//해당 병원의 특이사항 dto저장 메서드
	private ArrayList<SpecialDTO> selectSpeciality(int h_code) {
		Connection con = null;
		ArrayList<SpecialDTO> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SpecialDTO dto = null;
		
		String sql = "select s.st_code, specialty_name\r\n" + 
				"from h_info h join specialty s on h.h_code = s.h_code\r\n" + 
				"    join specialty_type st on s.st_code = st.st_code\r\n" + 
				"where h.h_code=?"; 
				
	try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, h_code);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<SpecialDTO>();
			
			while(rs.next()) {
				dto = new SpecialDTO();
				
				dto.setSt_code(rs.getInt("st_code"));
				dto.setSpecialty_name(rs.getString("specialty_name"));
				
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

	//해당 병원의 진료과목 dto저장 메서드
	public ArrayList<CategoryDTO> selectHinfoSub(int h_code) {
		Connection con = null;
		ArrayList<CategoryDTO> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CategoryDTO dto = null;
		
		String sql = "select  s.m_sub_seq, m_sub_name\r\n" + 
				"from h_info h join h_sub s on h.h_code = s.h_code\r\n" + 
				"    join m_sub_type m on s.m_sub_seq = m.m_sub_seq\r\n" + 
				"where h.h_code=?";
	
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, h_code);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<CategoryDTO>();
			
			while(rs.next()) {
				dto = new CategoryDTO();
				
				dto.setM_sub_seq(rs.getInt("m_sub_seq"));
				dto.setM_sub_name(rs.getString("m_sub_name"));
				
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

	//hinfo 병원 목록 필터+검색을 통한 리스트조회 메서드
	public ArrayList<HinfoDTO> selectHinfo(Connection con, String sub, String special, String searchWord, String id) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HinfoDTO> list = new ArrayList<HinfoDTO>();
		ArrayList<SpecialDTO> specialList = new ArrayList<SpecialDTO>();
		ArrayList<CategoryDTO> subList = new ArrayList<CategoryDTO>();

		int h_code;
		
		try {
			String sql = "select   DISTINCT h.h_code\r\n" + 
					", h_name\r\n" + 
					", h_address\r\n" + 
					",(select sum(score_kind+score_price+score_result+score_satisfaction)/(count(*)*4) from h_review r where h.h_code = r.h_code) star_score"+
					", (select count(*) from h_review r where h.h_code = r.h_code) reviewer"+
					", (select count(*) from h_bookmark b where h.h_code = b.h_code) bookmark"+
					", (select count(*) from h_bookmark bk where h.h_code = bk.h_code and bk.id = ?) isbookmark";
	
		if (sub.equals("*") && special.equals("*")) {
			
			 sql += " from h_info h join h_sub s on h.h_code = s.h_code\r\n"
					+ "where regexp_like(h_name, ?) or regexp_like(h_address, ?)";

		
		}else if (sub.equals("*")) { //진료과목이 없을때
			System.out.println("특이사항");
			sql += 	" from h_info h join specialty s on h.h_code = s.h_code \r\n" + 
					" join specialty_type st on s.st_code = st.st_code \r\n" + 
					" where s.st_code in "+ special + " and (regexp_like(h_name, ?) or regexp_like(h_address, ?))" ;
			
		}else if (special.equals("*")) { //특이사항 없을때
			System.out.println("진료과목");
			sql += 	" from h_info h join h_sub u on u.h_code = h.h_code\r\n" + 
					" join m_sub_type m on m.m_sub_seq = u.m_sub_seq\r\n" + 
					" where m.m_sub_seq in " + sub + " and (regexp_like(h_name, ?) or regexp_like(h_address, ?))" ;
			
		}else if(!sub.equals("*") && !special.equals("*")) {
			System.out.println("진료과목+특이사항");
			sql += 	" from h_info h join specialty s on h.h_code = s.h_code \r\n" + 
					" join specialty_type st on s.st_code = st.st_code \r\n" + 
					" join h_sub u on u.h_code = h.h_code\r\n" + 
					" join m_sub_type m on m.m_sub_seq = u.m_sub_seq\r\n" + 
					" where m.m_sub_seq in " + sub +  " and s.st_code in "+ special + " and (regexp_like(h_name, ?) or regexp_like(h_address, ?))" ;
	
		}
		
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, searchWord);
		pstmt.setString(3, searchWord);
		
		rs = pstmt.executeQuery();
			HinfoDTO dto = null;
			
			while (rs.next()) {
				
				dto = new HinfoDTO();
				
				//병원코드
				h_code = rs.getInt("h_code");	
				dto.setH_code(h_code);
				
				//별점 가져오기
				dto.setStar_score(Math.round(rs.getDouble("star_score")*10)/10.0);
				
				//리뷰총수
				dto.setReviewer(rs.getInt("reviewer"));
				
				//진료과목
				specialList = selectSpeciality(h_code);
				dto.setSpecial(specialList);
				
				//특이사항 
				subList = selectHinfoSub(h_code);
				dto.setSub(subList);
				
				//병원이름, 주소
				dto.setH_name(rs.getString("h_name"));
				dto.setH_address(rs.getString("h_address"));
				
				//북마크 총수
				dto.setBookmark_count(rs.getInt("bookmark"));
				
				//해당 회원의 북마크 여부
				dto.setIsBookMark(rs.getInt("isbookmark"));
				
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

	// 한 병원의 정보를 가져올 메서드
	public HinfoDTO selectHinfo(Connection con, int h_code, String id) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HinfoDTO dto = null;
		ArrayList<SpecialDTO> specialList = new ArrayList<SpecialDTO>();
		ArrayList<CategoryDTO> subList = new ArrayList<CategoryDTO>();
		HtimeDTO hdto = new HtimeDTO();

		String sql = "select h.h_code\r\n" + 
				", h_name\r\n" + 
				", h_address\r\n"
				+ ", h_tel"
				+ ", h_link " + 
				",(select sum(score_kind+score_price+score_result+score_satisfaction)/(count(*)*4) from h_review r where h.h_code = r.h_code) star_score\r\n" + 
				", (select count(*) from h_review r where h.h_code = r.h_code) reviewer\r\n" + 
				", (select count(*) from h_bookmark b where h.h_code = b.h_code) bookmark "+
				", (select count(*) from h_bookmark bk where h.h_code = bk.h_code and bk.id = ?) isbookmark"+
				" from h_info h join h_sub s on h.h_code = s.h_code\r\n" + 
				" where h.h_code = ?";
		
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setInt(2, h_code);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
			dto = new HinfoDTO();


				dto.setH_code(h_code);
				
				//별점 가져오기
				dto.setStar_score(Math.round(rs.getDouble("star_score")*10)/10.0);
				
				//리뷰총수
				dto.setReviewer(rs.getInt("reviewer"));
				
				//북마크 총수
				dto.setBookmark_count(rs.getInt("bookmark"));
				
				//한 아이디의 북마크 여부
				dto.setIsBookMark(rs.getInt("isbookmark"));
			
				//진료과목
				specialList = selectSpeciality(h_code);
				dto.setSpecial(specialList);
				
				//특이사항 
				subList = selectHinfoSub(h_code);
				dto.setSub(subList);
				
				//시간 가져오기
				hdto = selectHtime(h_code);
				dto.setHtime(hdto);
				
				
				//병원이름, 주소
				dto.setH_name(rs.getString("h_name"));
				dto.setH_address(rs.getString("h_address"));
				dto.setH_link(rs.getString("h_link"));
				dto.setH_tel(rs.getString("h_tel"));


			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(con);	
		}
		
		
		
		return dto;
	}
	
	//병원 시간 가져오는 메서드	
	private HtimeDTO selectHtime(int h_code) {
		
		Connection con = null;
		HtimeDTO hdto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select h.h_code, mon, tue, wed, thur, fri, sat, ltime_week, ltime_weekend\r\n" + 
				"from h_info h join h_info_time t on h.h_code = t.h_code\r\n" + 
				"where h.h_code= ?";
		
		
		try {
			con = ConnectionProvider.getConnection();
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, h_code);
			
			rs = pstmt.executeQuery();
			
			
			if (rs.next()) {
				
				hdto = new HtimeDTO();
		
				hdto.setMon(rs.getString("mon"));
				hdto.setTue(rs.getString("tue"));
				hdto.setWed(rs.getString("wed"));
				hdto.setThur(rs.getString("thur"));
				hdto.setFri(rs.getString("fri"));
				hdto.setSat(rs.getString("sat"));
				hdto.setLtime_week(rs.getString("ltime_week"));
				hdto.setLtime_weekend(rs.getString("ltime_weekend"));
			
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(con);	
		}
		return hdto;

	}

	//한 병원의 리뷰 목록가져올 메서드
	public ArrayList<HreviewDTO> selectReview(Connection con, int h_code, PageBlock paging, String id) {
		
	
		HreviewDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<HreviewDTO> list = null;
		ArrayList<HreviewTreatDTO> treatList = null;
		ArrayList<HreivewPicDTO> picList = null;
		paging.setNumberPerPage(4);
		
		int start = (paging.getCurrentPage()-1)*paging.getNumberPerPage()+1;
		int end = paging.getCurrentPage()*paging.getNumberPerPage();
		
		
		
		String sql = "select rownum, t.*\r\n" + 
				"from ( select  h.*, nickname\r\n" + 
				", (select sum(score_kind+score_price+score_result+score_satisfaction)/4 from h_review r where h.h_review_code= r.h_review_code) star_score\r\n" + 
				", (select count(*) from hreview_likes l where l.h_review_code = h.h_review_code and id = ? ) userlike"+
				" from h_review h join memberT m on h.id = m.id\r\n" + 
				"where h_code = ? \r\n" + 
				"order by dates desc) t\r\n" + 
				"where rownum between ? and ?";
		
		try {
			list = new ArrayList<HreviewDTO>();
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setInt(2, h_code);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				dto = new HreviewDTO();
				int h_review_code = rs.getInt("h_review_code");
				
				dto.setH_review_code(h_review_code);
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
				dto.setUserlike(rs.getInt("userlike"));
				
				
				treatList = selectTreat(h_review_code);
				dto.setTreatment(treatList);
				
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
	
	//병원리뷰 쓰기
	public int insert(Connection con, HreviewDTO dto) {
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "insert into h_review (h_review_code, h_code, id, score_kind, score_price, score_result, score_satisfaction, review_type, contents)\r\n" + 
				"values( h_review_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getH_code());
			pstmt.setString(2, dto.getId());
			pstmt.setInt(3, dto.getScore_kind());
			pstmt.setInt(4, dto.getScore_price());
			pstmt.setInt(5, dto.getScore_result());
			pstmt.setInt(6, dto.getScore_satisfaction());
			pstmt.setInt(7, dto.getReview_type());
			pstmt.setString(8, dto.getContents());
			
			result= pstmt.executeUpdate();
			
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		
		return result;
	}
		
	//병원 리뷰 글 삭제
	public int delete(Connection con, int h_review_code) {
		
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = con.prepareStatement(
					"delete from h_review where h_review_code = ?");
			pstmt.setInt(1, h_review_code);
			
			int result = pstmt.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return 0;
	}
	
	//한 id리뷰 글 불러오기
	public HreviewDTO selectOneReview(Connection con, int h_review_code) {
		

		HreviewDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HreviewTreatDTO> treatList = null;
		ArrayList<HreivewPicDTO> picList = null;
		
		
		String sql = "select * from h_review where h_review_code=?";
		
		try {
		
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, h_review_code);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new HreviewDTO();
				
				dto.setScore_kind(rs.getInt("score_kind"));
				dto.setScore_price(rs.getInt("score_price"));
				dto.setScore_result(rs.getInt("score_result"));
				dto.setScore_satisfaction(rs.getInt("score_satisfaction"));
				dto.setReview_type(rs.getInt("review_type"));
				dto.setContents(rs.getString("contents"));
				treatList = selectTreat(h_review_code);
				dto.setTreatment(treatList);
				picList = selectPic(h_review_code);
				dto.setPicture(picList);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	//병원 리뷰 업데이트
	public int update(Connection con, HreviewDTO dto) {
		
		PreparedStatement pstmt = null;
		
		String sql = " update h_review\r\n" + 
				" set score_kind =?, score_price=?, score_result=?, score_satisfaction=?, dates=sysdate, contents=?\r\n" + 
				" where h_review_code = ?";
		int result= 0;
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getScore_kind());
			pstmt.setInt(2, dto.getScore_price());
			pstmt.setInt(3, dto.getScore_result());
			pstmt.setInt(4, dto.getScore_satisfaction());
			pstmt.setString(5, dto.getContents());
			pstmt.setInt(6, dto.getH_review_code());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//업데이트되면 치료항목, 가격까지 업데이트하기
	public void updateTreat(Connection con, HreviewDTO dto) {
		
		PreparedStatement pstmt = null;
		try {
			Iterator<HreviewTreatDTO> ir = dto.getTreatment().iterator();
			while (ir.hasNext()) {
				HreviewTreatDTO tdto = ir.next();
			
				String sql = "update review_treatment set treatment_code = ? where rtreatment_seq = ? ";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, tdto.getTreatment_code());
				pstmt.setInt(2, tdto.getRtreatment_seq());
				
				pstmt.executeUpdate();
				
				
				sql = "update h_review_price set price=? where rtreatment_seq = ? ";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, tdto.getPrice());
				pstmt.setInt(2, tdto.getRtreatment_seq());
				
				pstmt.executeUpdate();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			JdbcUtil.close(pstmt);
		}
		
		
		}
	
	//약 용도 필터 리스트
	public ArrayList<MpurposeDTO> selectMpurposeList(Connection con) {
		
		ArrayList<MpurposeDTO> list = null;
		MpurposeDTO dto = null;
		String sql = "select * from m_purpose";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<MpurposeDTO>();
			
			while(rs.next()) {
				dto = new MpurposeDTO();
				dto.setBpurpose_code(rs.getInt("bpurpose_code"));
				dto.setPurpose_code(rs.getInt("purpose_code"));
				dto.setPurpose_name(rs.getString("purpose_name"));
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
	
	//약 조회 리스트
	public ArrayList<MinfoDTO> selectMinfo(Connection con, String mpurpose, String searchWord, PageBlock paging,  String id, int num) {
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MinfoDTO> list = new ArrayList<MinfoDTO>();
		MpurposeDTO mpurPose = new MpurposeDTO();
		/* PageBlock pageBlock = new PageBlock(); */
		
		
		
		int start = (paging.getCurrentPage()-1)*paging.getNumberPerPage()+1;
		int end = paging.getCurrentPage()*paging.getNumberPerPage();
		
		int m_code;
	
		
		try {
			String sql ="select a.* \r\n" + 
					"from(\r\n" + 
					"select rownum no, t.*\r\n" + 
					"from (\r\n" + 
					"select m.* \r\n" + 
					", (select sum(score_effect+score_price+score_comfort)/(count(*)*3) from m_review r where m.m_code = r.m_code) star_score \r\n" + 
					", (select count(*) from m_review r where m.m_code = r.m_code) reviewer\r\n" + 
					", (select count(*) from m_bookmark b where m.m_code = b.m_code) bookmark \r\n" + 
					", (select count(*) from m_bookmark bk where m.m_code = bk.m_code and bk.id = ?) isbookmark\r\n" + 
					"from m_info m join m_purpose p on m.purpose_code = p.purpose_code " ;
			 		
		if (mpurpose.equals("*")) {
			
			 sql += "where regexp_like(m_name, ?) or regexp_like(m_enterprise, ?)" ;
			 		
		
		}else if (!mpurpose.equals("*")) { //약용도 필터 골랐을때
	         System.out.println("용도 필터");
	         sql += "where (m.purpose_code in "+mpurpose+") and (regexp_like(m_name, ?) or regexp_like(m_enterprise, ?))\r\n" ;

	      }

		if (num == 1) {
			sql += "order by reviewer desc";
		}else if(num ==2) {
			sql += "order by star_score desc";
		}else if(num ==3) {
			sql += "order by star_score";
		}
		
		
		sql += ")t  )a where a.no between ? and ?";

		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, id);
	
		pstmt.setString(2, searchWord);
		pstmt.setString(3, searchWord);
		pstmt.setInt(4, start);
		pstmt.setInt(5, end);
		
		rs = pstmt.executeQuery();
			MinfoDTO dto = null;
			
			while (rs.next()) {
				
				dto = new MinfoDTO();
				
				//약코드
				m_code = rs.getInt("m_code");	
				dto.setM_code(m_code);
				
				//별점 가져오기
				dto.setStar_score(Math.round(rs.getDouble("star_score")*10)/10.0);
				
				//리뷰총수
				dto.setReviewer(rs.getInt("reviewer"));
				
				//북마크 총수
				dto.setBookmark_count(rs.getInt("bookmark"));
				
				//해당 회원의 북마크 여부
				dto.setIsBookMark(rs.getInt("isbookmark"));
				
				//약용도 리스트
				mpurPose = selectMpurpose(m_code);
				dto.setMpurpose(mpurPose);

				
				//약이름, 회사이름
				dto.setM_name(rs.getString("m_name"));
				dto.setM_enterprise(rs.getString("m_enterprise"));
				dto.setM_pic(rs.getString("m_pic"));
				
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
	
	//약 용도 리스트
	private MpurposeDTO selectMpurpose(int m_code) {
		
		Connection con = null;
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MpurposeDTO dto = null;
		
		String sql = "select p.purpose_name, b.bpurpose_code, bpurpose_name, p.purpose_code\r\n" + 
				"from m_info i join m_purpose p on i.purpose_code = p.purpose_code\r\n" + 
				"join m_bpurpose b on p.bpurpose_code = b.bpurpose_code\r\n" + 
				"where m_code = ?";
				
	try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_code);
			rs = pstmt.executeQuery();
			
	
			
			while(rs.next()) {
				dto = new MpurposeDTO();
				
				dto.setBpurpose_code(rs.getInt("bpurpose_code"));
				dto.setPurpose_code(rs.getInt("purpose_code"));
				dto.setPurpose_name(rs.getString("purpose_name"));
				dto.setBpurpose_name(rs.getString("bpurpose_name"));
				
			
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
			
		}
		
		return dto;
		

	}
		
	//페이징 처리
	public PageBlock selectMPage(Connection con, String searchWord, String mpurpose, PageBlock pageBlock) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		String sql = "select count(*) totalRecords, ceil(count(*)/?) totalPages\r\n" + 
				"from m_info";
		
		if (mpurpose.equals("*")) {
			sql += " where regexp_like(m_name, ?) or regexp_like(m_enterprise, ?)";
		}else if(!mpurpose.equals("*")) {
			sql += " where (regexp_like(m_name, ?) or regexp_like(m_enterprise, ?)) and purpose_code in"+mpurpose;
		}
		
		int totalPages =0;
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pageBlock.getNumberPerPage());
			pstmt.setString(2, searchWord);
			pstmt.setString(3, searchWord);
			
			
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

	
	//한 약정보 가져오는 메서드
	public MinfoDTO selectMinfo(Connection con, int m_code, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MinfoDTO dto = null;	
		MpurposeDTO mdto = null;
	
		String sql = "select t.* \r\n" + 
				", (select avg(m_price) from m_review where m_code=?) avg"+
				", (select sum(score_effect+score_price+score_comfort)/(count(*)*3) from m_review r where t.m_code = r.m_code) star_score \r\n" + 
				", (select count(*) from m_review r where t.m_code = r.m_code) reviewer\r\n" + 
				", (select count(*) from m_bookmark b where t.m_code = b.m_code) bookmark\r\n" + 
				", (select count(*) from m_bookmark bk where t.m_code = bk.m_code and bk.id = ?) isbookmark\r\n" + 
				"from m_info t " 
				+ "where m_code = ?";
		
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, m_code);
			pstmt.setString(2, id);
			pstmt.setInt(3, m_code);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
			dto = new MinfoDTO();
			
		

			
				//용도
				mdto = selectMpurpose(m_code);
				dto.setMpurpose(mdto);
				
				dto.setM_code(m_code);

				
				dto.setM_name(rs.getString("m_name"));
				dto.setM_enterprise(rs.getString("m_enterprise"));
				dto.setM_ingredient(rs.getString("m_ingredient"));
				dto.setM_effect(rs.getString("m_effect"));
				dto.setM_dose(rs.getString( "m_dose"));
				dto.setM_pic(rs.getString("m_pic"));
				dto.setM_sideeffect(rs.getString("m_sideeffect"));
				dto.setM_price(rs.getInt("avg"));
				//별점 가져오기
				dto.setStar_score(Math.round(rs.getDouble("star_score")*10)/10.0);
				//리뷰총수
				dto.setReviewer(rs.getInt("reviewer"));
				//북마크 총수
				dto.setBookmark_count(rs.getInt("bookmark"));
				//해당 회원의 북마크 여부
				dto.setIsBookMark(rs.getInt("isbookmark"));
				

				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(con);	
		}
		return dto;

	}
	
	
	//약 리뷰 리스트
	public ArrayList<MreviewDTO> selectMReview(Connection con, int m_code, PageBlock paging, String id) {
		MreviewDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<MreviewDTO> list = null;
	
		ArrayList<MreviewPicDTO> picList = null;
		paging.setNumberPerPage(4);
		
		int start = (paging.getCurrentPage()-1)*paging.getNumberPerPage()+1;
		int end = paging.getCurrentPage()*paging.getNumberPerPage();
		
		
		String sql = "select rownum, t.*\r\n" + 
				"from ( select  h.*, nickname\r\n" + 
				", (select sum(score_effect+score_comfort+score_price)/3 from m_review r where h.mreview_seq= r.mreview_seq) star_score\r\n" + 
				", (select count(*) from mreview_likes l where l.mreview_seq = h.mreview_seq and id = ? ) userlike\r\n" + 
				"from m_review h join memberT m on h.id = m.id\r\n" + 
				"where m_code = ? \r\n" + 
				"order by dates desc)t\r\n" + 
				"where rownum between ? and ?\r\n" ;
		
		try {
			list = new ArrayList<MreviewDTO>();
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setInt(2, m_code);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				dto = new MreviewDTO();
				
				int m_review_code = rs.getInt("mreview_seq");
				
				dto.setM_review_code(m_review_code);
				dto.setId(rs.getString("id"));
				
				dto.setScore_effect(rs.getInt("score_effect"));
				dto.setScore_price(rs.getInt("score_price"));
				dto.setScore_comfort(rs.getInt("score_comfort"));
				dto.setM_price(rs.getInt("m_price"));
				dto.setDates(rs.getDate("dates"));
				dto.setLikes(rs.getInt("likes"));
				dto.setContents(rs.getString("contents"));
				dto.setNickname(rs.getString("nickname"));
				dto.setStar_score(Math.round(rs.getDouble("star_score")*10)/10.0);
				dto.setUserlike(rs.getInt("userlike"));
				

				
				picList = selectMPic(m_review_code);
				dto.setPicture(picList);
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	return list;
	
	}
	//약 리뷰사진 저장
	private ArrayList<MreviewPicDTO> selectMPic(int m_review_code) {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<MreviewPicDTO> list = null;
		MreviewPicDTO dto = null;
		
		try {
			con = ConnectionProvider.getConnection();
			list = new ArrayList<MreviewPicDTO>();
			
			String sql = "select * from m_review_pic where m_review_seq = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_review_code);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				dto = new MreviewPicDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setMreview_seq(rs.getInt("m_review_seq"));
				dto.setPic(rs.getString("pic"));
					
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
	
	//약리뷰작성
	public int Minsert(Connection con, MreviewDTO dto) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql ="insert into m_review (mreview_seq, m_code, id, m_price, score_effect, score_comfort, score_price, contents)\r\n" + 
				"values(m_review_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getM_code());
			pstmt.setString(2, dto.getId());
			pstmt.setInt(3, dto.getM_price());
			pstmt.setInt(4, dto.getScore_effect());
			pstmt.setInt(5, dto.getScore_comfort());
			pstmt.setInt(6, dto.getScore_price());
			pstmt.setString(7, dto.getContents());
			
			result = pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		
		return result;
	}

	public MreviewDTO selectOneMReview(Connection con, int m_review_code) {
		MreviewDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MreviewPicDTO> picList = null;

		String sql = "select * from m_review where mreview_seq=?";
		
		try {
		
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, m_review_code);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new MreviewDTO();
				
				dto.setScore_effect(rs.getInt("score_effect"));
				dto.setScore_price(rs.getInt("score_price"));
				dto.setScore_comfort(rs.getInt("score_comfort"));
				dto.setContents(rs.getString("contents"));
				dto.setM_price(rs.getInt("m_price"));
				picList = selectMPic(m_review_code);
				dto.setPicture(picList);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	//약리뷰 업데이트
	public int Mupdate(Connection con, MreviewDTO dto) {
		PreparedStatement pstmt = null;
		
		String sql = " update m_review\r\n" + 
				" set score_effect =?, score_price=?, score_comfort=?, dates=sysdate, m_price=?, contents=?\r\n" + 
				" where mreview_seq = ?";
		int result= 0;
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getScore_effect());
			pstmt.setInt(2, dto.getScore_price());
			pstmt.setInt(3, dto.getScore_comfort());
			pstmt.setInt(4, dto.getM_price());
			pstmt.setString(5, dto.getContents());
			pstmt.setInt(6, dto.getM_review_code());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int Mdelete(Connection con, int m_review_code) {
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = con.prepareStatement(
					"delete from m_review where mreview_seq = ?");
			pstmt.setInt(1, m_review_code);
			
			int result = pstmt.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return 0;

	}

	public PageBlock selectHRevPage(Connection con, int h_code, PageBlock pageBlock) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "select count(*) totalRecords, ceil(count(*)/?) totalPages\r\n" + 
				"from h_review where h_code = ?";
		
		
		int totalPages =0;
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pageBlock.getNumberPerPage());
			pstmt.setInt(2, h_code);
			
			
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
	
	
	//리뷰쓰기 병원목록 조회-모달
	public ArrayList<HinfoDTO> selectHname(Connection con) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		HinfoDTO dto = null;
		ArrayList<HinfoDTO> list = null;
		
		try {
			
			String sql = "select h_code, h_name from h_info";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<HinfoDTO>();
			
			while (rs.next()) {
				dto = new HinfoDTO();
				
				dto.setH_code(rs.getInt("h_code"));
				dto.setH_name(rs.getString("h_name"));
				
				list.add(dto);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	//그에 해당하는 전체 진료과목 뿌리기-모달
	public List<CategoryModalDTO> selectCategoryList(Connection con, int h_code) {
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select t.treatment_code, t.m_sub_seq, \r\n" + 
				"treatment_name,\r\n" + 
				"m_sub_name ,\r\n" + 
				"h_code\r\n" + 
				"from treatment t join m_sub_type m on t.m_sub_seq = m.m_sub_seq\r\n" + 
				"join h_sub s on m.m_sub_seq = s.m_sub_seq\r\n"; 
	
				sql+= "where h_code = ?\r\n" ;
			
	
		
		try {
			pstmt = con.prepareStatement(sql);
			

				
				pstmt.setInt(1, h_code);
		
			
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<CategoryModalDTO> categoryList = new ArrayList<CategoryModalDTO>();
				
				do {
				
					CategoryModalDTO dto = new CategoryModalDTO();
					dto.setM_sub_name(rs.getString("m_sub_name"));
					dto.setM_sub_seq(rs.getInt("m_sub_seq"));
					dto.setTreatment_code(rs.getInt("treatment_code"));
					dto.setTreatment_name(rs.getString("treatment_name"));
					
					
					
					categoryList.add(dto);
				
				}while(rs.next());
				
				return categoryList;
		
			}else {
				return Collections.emptyList();
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
	}
	
	//받아온 리뷰코드로 사진 insert
	public int insertPic(Connection con, int h_review_code, String pic) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "insert into hreview_pic values (h_reviewPic_seq.nextval, ?, ?)"; 
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, h_review_code);
			pstmt.setString(2, pic);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	//리뷰코드 받아오기
	public int selectHreviewCode(HreviewDTO dto) {
		System.out.println("h_rview받으러 호출됌");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select h_review_code from h_review where id = ? and h_code = ?";
		int h_review_code = 0;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			
			pstmt.setInt(2, dto.getH_code());
			
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
			
				h_review_code = rs.getInt("h_review_code");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return h_review_code;
	}
	
	//모달창에서 받아온 치료항목 insert
	public void insertTreatCode(Connection con, int h_review_code, int treatment_code, int price) {
		
		PreparedStatement pstmt = null;
	
		
		String sql = "insert into review_treatment values (h_treatment_seq.nextval, ?, ?, ?)"; 
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, h_review_code);
			pstmt.setInt(2, treatment_code);
			pstmt.setInt(3, price);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	//모달창에 병원에 해당하는 진료과목 조회
	public List<CategoryDTO> HsubList(Connection con, int h_code) {
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select distinct t.m_sub_seq,\r\n" + 
				"h_code,\r\n" + 
				"m_sub_name\r\n" + 
				"from treatment t join m_sub_type m on t.m_sub_seq = m.m_sub_seq\r\n" + 
				"join h_sub s on m.m_sub_seq = s.m_sub_seq\r\n" ;

			
			sql += "where h_code = ?\r\n" ;

				
		
		try {
			pstmt = con.prepareStatement(sql);
			

				
				pstmt.setInt(1, h_code);
		
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				List<CategoryDTO> categoryList = new ArrayList<CategoryDTO>();
				do {
					CategoryDTO dto = new CategoryDTO();
					dto.setM_sub_name(rs.getString("m_sub_name"));
					dto.setM_sub_seq(rs.getInt("m_sub_seq"));
					
					categoryList.add(dto);
				
				}while(rs.next());
			
				return categoryList;
			
			}else {
				return Collections.emptyList();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
	}
	
	//인증리뷰 테이블 insert
	public void insertCerti(Connection con, int h_review_code, String certification_attach) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "insert into h_review_certification values (h_review_certification_seq.nextval, ?, ?, 0)";
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, h_review_code);
			pstmt.setString(2, certification_attach);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}

	public PageBlock selectMRevPage(Connection con, int m_code, PageBlock pageBlock) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "select count(*) totalRecords, ceil(count(*)/?) totalPages\r\n" + 
				"from m_review where m_code = ?";
		
		
		int totalPages =0;
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pageBlock.getNumberPerPage());
			pstmt.setInt(2, m_code);
			
			
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


