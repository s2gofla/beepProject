package beepbeep.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;



import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.command.CategoryDTO;
import beepbeep.qna.dao.QnaAjaxDAO;
import beepbeep.review.model.CategoryModalDTO;
import beepbeep.review.model.HinfoDTO;
import beepbeep.review.model.HreivewPicDTO;
import beepbeep.review.model.HreviewDTO;
import beepbeep.review.model.HreviewTreatDTO;
import beepbeep.review.model.MpurposeDTO;
import beepbeep.review.model.MreviewDTO;
import beepbeep.review.model.MreviewPicDTO;
import beepbeep.review.model.PageBlock;
import beepbeep.review.model.SpecialDTO;

public class ReviewAjaxDAO {

private static ReviewAjaxDAO instance = new ReviewAjaxDAO();
	
	public static ReviewAjaxDAO getInstance() {
		return instance;
	}
	private ReviewAjaxDAO() {}
	
	Connection con ;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//병원북마크 insert
	public int insertBook(int h_code, String id) throws NamingException, SQLException {
		
		con = ConnectionProvider.getConnection();
		String sql = "insert into h_bookmark values(h_bookmark_seq.nextval, ?, ?)";
		
		
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setInt(2, h_code);
		
		int result = pstmt.executeUpdate();
		
		JdbcUtil.close(pstmt);
		JdbcUtil.close(con);
		
		return result;
	}
	
	//병원북마크 수 가져오기
	public int selectBooks(int h_code) throws NamingException, SQLException {
		
		con = ConnectionProvider.getConnection();
		String sql = "select count(*) from h_bookmark\r\n" + 
				"where h_code = ?";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, h_code);
		rs = pstmt.executeQuery();
		
		int result = 0;
		if (rs.next()) {
			
			result = rs.getInt(1);
		}
		
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		
		return result;
	}
	
	//병원북마크 지우기
	public int deleteBook(int h_code, String id) throws NamingException, SQLException {

		con = ConnectionProvider.getConnection();
		String sql = "delete h_bookmark where h_code=? and id=? ";
		
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, h_code);
		pstmt.setString(2, id);
		
		int result = pstmt.executeUpdate();
		
		JdbcUtil.close(pstmt);
		JdbcUtil.close(con);
		
		return result;
		
		
	}
	
	//약 북마크 insert
	public int MinsertBook(int m_code, String id) throws SQLException, NamingException {
		con = ConnectionProvider.getConnection();
		String sql = "insert into m_bookmark values(m_bookmark_seq.nextval, ?, ?)";
		
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, m_code);
		pstmt.setString(2, id);
		
		int result = pstmt.executeUpdate();
		
		JdbcUtil.close(pstmt);
		JdbcUtil.close(con);
		
		return result;
	}
	
	//약 북마크 delete
	public int MdeleteBook(int m_code, String id) throws SQLException, NamingException {
		
		con = ConnectionProvider.getConnection();
		String sql = "delete m_bookmark where m_code=? and id=? ";
		
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, m_code);
		pstmt.setString(2, id);
		
		int result = pstmt.executeUpdate();
		
		JdbcUtil.close(pstmt);
		JdbcUtil.close(con);
		
		return result;
		
		
	}

	//약 북마크 총 수 가져오기
	public int selectMBooks(int m_code) throws NamingException, SQLException {
		con = ConnectionProvider.getConnection();
		String sql = "select count(*) from m_bookmark\r\n" + 
				"where m_code = ?";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, m_code);
		rs = pstmt.executeQuery();
		
		int result = 0;
		if (rs.next()) {
			
			result = rs.getInt(1);
		}
		
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		
		return result;
	}
	
	//병원 리스트 셀렉
	public ArrayList<HinfoDTO> select(int num, String searchWord, String sub, String special, String id) throws NamingException, SQLException {
		Connection scon = null;
		scon = ConnectionProvider.getConnection();
		PreparedStatement spstmt = null;
		ResultSet srs = null;
		
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
		
		ArrayList<HinfoDTO> list = new ArrayList<HinfoDTO>();
		ArrayList<SpecialDTO> specialList = new ArrayList<SpecialDTO>();
		ArrayList<CategoryDTO> subList = new ArrayList<CategoryDTO>();
		
		
		int h_code;
		
		try {
			String sql = "select distinct h.h_code\r\n" + 
					", h_name\r\n" + 
					", h_address\r\n" + 
					", nvl((select sum(score_kind+score_price+score_result+score_satisfaction)/(count(*)*4) from h_review r where h.h_code = r.h_code),0) star_score" + 
					", (select count(*) from h_review r where h.h_code = r.h_code) reviewer\r\n" + 
					", (select count(*) from h_bookmark b where h.h_code = b.h_code) bookmark\r\n" + 
					", (select count(*) from h_bookmark bk where h.h_code = bk.h_code and bk.id = ?) isbookmark" + 
					", (select count(*) from h_review rc where h.h_code = rc.h_code and review_type = 1) certi";
	
		if (sub.equals("*") && special.equals("*")) {
			System.out.println("이쪽으로 출력되야함");
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
		
		if (num==0) {
			sql += " order by reviewer desc";
		}else if(num==1) {
			sql += " order by certi desc";
		}else if(num==2) {
			sql += " order by star_score desc";
		}
		
		System.out.println(sql);
		
		spstmt = scon.prepareStatement(sql);
		spstmt.setString(1, id);
		spstmt.setString(2, searchWord);
		spstmt.setString(3, searchWord);
		
		srs = spstmt.executeQuery();
			HinfoDTO dto = null;
			
			while (srs.next()) {
				
				dto = new HinfoDTO();
				
				//병원코드
				h_code = srs.getInt("h_code");	
				dto.setH_code(h_code);
				System.out.println(dto.getH_code());
				//병원이름, 주소
				dto.setH_name(srs.getString("h_name"));
				System.out.println(dto.getH_name());
				
				//진료과목
				specialList = selectSpeciality(h_code);
				dto.setSpecial(specialList);
				
				
				//특이사항 
				subList = selectHinfoSub(h_code);
				dto.setSub(subList);
				
				dto.setH_address(srs.getString("h_address"));
				
				//북마크 총수
				dto.setBookmark_count(srs.getInt("bookmark"));
				//해당 회원의 북마크 여부
				dto.setIsBookMark(srs.getInt("isbookmark"));
				
				//별점 가져오기
				dto.setStar_score(Math.round(srs.getDouble("star_score")*10)/10.0);
				
				//리뷰총수
				dto.setReviewer(srs.getInt("reviewer"));
				list.add(dto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		JdbcUtil.close(srs);
		JdbcUtil.close(spstmt);
		JdbcUtil.close(scon);	
		}
		
		
		
		return list;
	}
	
	//병원 진료과목 
	private ArrayList<CategoryDTO> selectHinfoSub(int h_code) {
		ArrayList<CategoryDTO> list = null;
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
	
	//병원 특이사항
	private ArrayList<SpecialDTO> selectSpeciality(int h_code) {
		ArrayList<SpecialDTO> list = null;
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
	
	//병원리뷰 정렬
	public ArrayList<HreviewDTO> selecHList(int h_code, String id, PageBlock paging, int num, String searchWord) {
		
		
		HreviewDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<HreviewDTO> list = null;
		ArrayList<HreviewTreatDTO> treatList = null;
		ArrayList<HreivewPicDTO> picList = null;
		paging.setNumberPerPage(4);
		
		int start = (paging.getCurrentPage()-1)*paging.getNumberPerPage()+1;
		int end = paging.getCurrentPage()*paging.getNumberPerPage();
		System.out.println("start값:"+start +"end값:"+end);
		
		
		String sql ="select a.* from ("+
				
				"select rownum no, t.*\r\n" + 
				"from ( select  h.*, nickname\r\n" + 
				", (select sum(score_kind+score_price+score_result+score_satisfaction)/4 from h_review r where h.h_review_code= r.h_review_code) star_score\r\n" + 
				", (select count(*) from hreview_likes l where l.h_review_code = h.h_review_code and id = ? ) userlike"+
				" from h_review h join memberT m on h.id = m.id\r\n" + 
				" where h_code = ? and regexp_like(contents, ?)" ; 
		switch (num) {
		case 1:
			sql += " order by dates desc) t ";
			break;
		case 2:
			sql += " order by likes desc) t";
			break;
		case 3: 
			sql += " order by star_score desc) t";
			break;
		case 4:
			sql += " order by star_score) t" ;
			break;
		}		
		
		sql +=	" ) a where a.no between ? and ?";
		
		System.out.println(sql);
		try {
			con = ConnectionProvider.getConnection();
			
			
			list = new ArrayList<HreviewDTO>();
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setInt(2, h_code);
			pstmt.setString(3, searchWord);
			pstmt.setInt(4, start);
			pstmt.setInt(5, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				dto = new HreviewDTO();
				int h_review_code = rs.getInt("h_review_code");
				treatList = selectTreat(h_review_code);
				dto.setTreatment(treatList);
				
				picList = selectPic(h_review_code);
				dto.setPicture(picList);
				
				
				dto.setH_review_code(h_review_code);
				dto.setId(rs.getString("id"));
				  
				dto.setScore_kind(rs.getInt("score_kind"));
				dto.setScore_price(rs.getInt("score_price"));
				dto.setScore_result(rs.getInt("score_result"));
				dto.setScore_satisfaction(rs.getInt("score_satisfaction"));
				  
				dto.setReview_type(rs.getInt("review_type"));
				dto.setAjaxDates(rs.getString("dates"));
				
				
				dto.setLikes(rs.getInt("likes"));
				dto.setContents(rs.getString("contents"));
				dto.setNickname(rs.getString("nickname"));
				dto.setStar_score(Math.round(rs.getDouble("star_score")*10)/10.0);
				
			
				list.add(dto);
			}
			
		} catch(IllegalArgumentException e) {
			 e.printStackTrace();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
			
		}
		
	return list;
		
		
	}
	
	//병원리뷰 사진
	private ArrayList<HreivewPicDTO> selectPic(int h_review_code) {
		
		Connection scon = null;
		PreparedStatement spstmt = null;
		ResultSet srs = null;
		
		ArrayList<HreivewPicDTO> list = null;
		HreivewPicDTO dto = null;
		
		try {
			scon = ConnectionProvider.getConnection();
			list = new ArrayList<HreivewPicDTO>();
			
			String sql = "select * from hreview_pic where h_review_code = ?";
			
			spstmt = scon.prepareStatement(sql);
			spstmt.setInt(1, h_review_code);
			
			srs = spstmt.executeQuery();
			
			while(srs.next()) {
				
				dto = new HreivewPicDTO();
				
				dto.setH_review_code(srs.getInt("h_review_code"));
				dto.setHreview_seq(srs.getInt("hreview_seq"));
				dto.setPic(srs.getString("picture"));
					
				list.add(dto);
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			JdbcUtil.close(srs);
			JdbcUtil.close(spstmt);
			JdbcUtil.close(scon);
		}
		
	
		return list;
		
		
	}
	
	//그 약의 치료항목가져오기
	private ArrayList<HreviewTreatDTO> selectTreat(int h_review_code) {
		
		Connection scon = null;
		PreparedStatement spstmt = null;
		ResultSet srs = null;
		
		ArrayList<HreviewTreatDTO> list = null;
		HreviewTreatDTO dto = null;
		
		try {
			scon = ConnectionProvider.getConnection();
			list = new ArrayList<HreviewTreatDTO>();
			
			String sql = "select * from htreatment where h_review_code=?";
			
			spstmt = con.prepareStatement(sql);
			spstmt.setInt(1, h_review_code);
			
			srs = spstmt.executeQuery();
			
			while(srs.next()) {
				
				dto = new HreviewTreatDTO();
				
				dto.setTreatment_code(srs.getInt("treatment_code"));
				dto.setM_sub_name(srs.getString("m_sub_name"));
				dto.setTreatment_name(srs.getString("treatment_name"));
				dto.setPrice(srs.getInt("price"));
				dto.setRtreatment_seq(srs.getInt("rtreatment_seq"));
				
				list.add(dto);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			JdbcUtil.close(srs);
			JdbcUtil.close(spstmt);
			JdbcUtil.close(scon);
		}
		return list;
		
	}
	
	//병원 리뷰 페이징
	public PageBlock getPage(int h_code, PageBlock pageBlock, String searchWord) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "select count(*) totalRecords, ceil(count(*)/?) totalPages\r\n" + 
				"from h_review where h_code = ? and regexp_like(contents, ?)";
		
		
		int totalPages =0;
		
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pageBlock.getNumberPerPage());
			pstmt.setInt(2, h_code);
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
	
	// 약 용도 전체항목가져오기
	public ArrayList<CategoryModalDTO> selectCategory(int m_sub_seq) {
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryModalDTO> list = null;
		CategoryModalDTO dto = null;
		
		try {
			
			list = new ArrayList<CategoryModalDTO>();
			con = ConnectionProvider.getConnection();
			
			String sql = "select  t.m_sub_seq, \r\n" + 
					"t.treatment_code,\r\n" + 
					"treatment_name,\r\n" + 
					"m_sub_name \r\n" + 
					"from treatment t join m_sub_type m on t.m_sub_seq = m.m_sub_seq\r\n" + 
					"where t.m_sub_seq = ?";
			
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, m_sub_seq);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				dto = new CategoryModalDTO();
				
				dto.setTreatment_code(rs.getInt("treatment_code"));
				dto.setTreatment_name(rs.getString("treatment_name"));
				
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
	
	//치료항목가져오기 -모달
	public List<CategoryModalDTO> selectTreatMent(int h_code) {
	
		
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
			Connection con = ConnectionProvider.getConnection();
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
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
		
		
		
	}
	
	//진료과목 가져오기 - 모달
	public List<CategoryDTO> selectSub(int h_code) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select distinct t.m_sub_seq,\r\n" + 
				"h_code,\r\n" + 
				"m_sub_name\r\n" + 
				"from treatment t join m_sub_type m on t.m_sub_seq = m.m_sub_seq\r\n" + 
				"join h_sub s on m.m_sub_seq = s.m_sub_seq\r\n" ;

			
			sql += "where h_code = ?\r\n" ;

				
		
		try {
			Connection con = ConnectionProvider.getConnection();
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
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
		
	}
	
	//약 용도 가져오기
	public ArrayList<MpurposeDTO> selectPurpose(int bpurpose_code) {
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MpurposeDTO> list = null;
		MpurposeDTO dto = null;
		
		try {
			
			list = new ArrayList<MpurposeDTO>();
			con = ConnectionProvider.getConnection();
			
			String sql = "select * from m_purpose where bpurpose_code= ?";
			
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bpurpose_code);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				dto = new MpurposeDTO();
				
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
	
	//좋아요수 insert
	public int insertUserLike(Connection con, int h_review_code, String id) throws NamingException, SQLException {
		
		con = ConnectionProvider.getConnection();
		String sql = "insert into hreview_likes values(hreview_likes_seq.nextval, ?, ?)";
		
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, h_review_code);
		pstmt.setString(2, id);
		
		int result = pstmt.executeUpdate();
		
		JdbcUtil.close(pstmt);
		JdbcUtil.close(con);
		
		return result;
	}
	
	//좋아요 수 삭제
	public int deleteUserLike(Connection con, int h_review_code, String id) throws NamingException, SQLException {
		
		con = ConnectionProvider.getConnection();
		String sql = "delete hreview_likes where h_review_code=? and id=? ";
		
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, h_review_code);
		pstmt.setString(2, id);
		
		int result = pstmt.executeUpdate();
	
		JdbcUtil.close(pstmt);
		JdbcUtil.close(con);
		
		return result;
	}
	
	//좋아요한 수 가져오기
	public int selectLikes(Connection con, int h_review_code) throws NamingException, SQLException {
		
		con = ConnectionProvider.getConnection();
		String sql = "select count(*) from hreview_likes where h_review_code=?";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, h_review_code);
		rs = pstmt.executeQuery();
		
		int result = 0;
		if (rs.next()) {
			
			result = rs.getInt(1);
		}
		
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(con);
		return result;
	}

	//좋아요 수 업데이트
	public void updateLike(Connection con, int h_review_code, String book) {
	
		
		try {
			
			con = ConnectionProvider.getConnection();
			
			String sql = "update h_review set likes = likes";
			
			if (book.equals("0")) {
				sql  += " + 1 ";
			}else {
				sql += " - 1 ";
			}
			
			sql += " where h_review_code = ?";
			
			System.out.println(sql);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, h_review_code);
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
		
	}
	
	
	//약리뷰가져오기
	public ArrayList<MreviewDTO> selecMList(int m_code, String id, PageBlock paging, int num, String searchWord) {
		
		MreviewDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<MreviewDTO> list = null;
		ArrayList<MreviewPicDTO> picList = null;
		paging.setNumberPerPage(4);
		
		int start = (paging.getCurrentPage()-1)*paging.getNumberPerPage()+1;
		int end = paging.getCurrentPage()*paging.getNumberPerPage();
		System.out.println("start값:"+start +"end값:"+end);
		
		
		String sql ="select a.* from ("+
				
				"select rownum no, t.*\r\n" + 
				"from ( select  h.*, nickname\r\n" + 
				", (select sum(score_effect+score_comfort+score_price)/3 from m_review r where h.mreview_seq= r.mreview_seq) star_score\r\n" + 
				", (select count(*) from mreview_likes l where l.mreview_seq = h.mreview_seq and id = ? ) userlike"+
				" from m_review h join memberT m on h.id = m.id\r\n" + 
				" where m_code = ? and regexp_like(contents, ?)" ; 
		switch (num) {
		case 1:
			sql += " order by dates desc) t ";
			break;
		case 2:
			sql += " order by likes desc) t";
			break;
		case 3: 
			sql += " order by star_score desc) t";
			break;
		case 4:
			sql += " order by star_score) t" ;
			break;
		}		
		
		sql +=	" ) a where a.no between ? and ?";
		
		System.out.println(sql);
		try {
			con = ConnectionProvider.getConnection();
			
			
			list = new ArrayList<MreviewDTO>();
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setInt(2, m_code);
			pstmt.setString(3, searchWord);
			pstmt.setInt(4, start);
			pstmt.setInt(5, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				dto = new MreviewDTO();
				int m_review_code = rs.getInt("mreview_seq");

				
				picList = selectMPic(m_review_code);
				dto.setPicture(picList);
				
				
				dto.setM_review_code(m_review_code);
				dto.setId(rs.getString("id"));
				  
				dto.setScore_effect(rs.getInt("score_effect"));
				dto.setScore_price(rs.getInt("score_price"));
				dto.setScore_comfort(rs.getInt("score_comfort"));
				dto.setM_price(rs.getInt("m_price"));
				dto.setAjaxDates(rs.getString("dates"));
								
				dto.setLikes(rs.getInt("likes"));
				dto.setContents(rs.getString("contents"));
				dto.setNickname(rs.getString("nickname"));
				dto.setStar_score(Math.round(rs.getDouble("star_score")*10)/10.0);
				
			
				list.add(dto);
			}
			
		} catch(IllegalArgumentException e) {
			 e.printStackTrace();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
			
		}
		
	return list;
	}
	
	private ArrayList<MreviewPicDTO> selectMPic(int m_review_code) {
		Connection scon = null;
		PreparedStatement spstmt = null;
		ResultSet srs = null;
		
		ArrayList<MreviewPicDTO> list = null;
		MreviewPicDTO dto = null;
		
		try {
			scon = ConnectionProvider.getConnection();
			list = new ArrayList<MreviewPicDTO>();
			
			String sql = "select * from  m_review_pic where m_review_seq = ?";
			
			spstmt = scon.prepareStatement(sql);
			spstmt.setInt(1, m_review_code);
			
			srs = spstmt.executeQuery();
			
			while(srs.next()) {
				
				dto = new MreviewPicDTO();
				
				dto.setSeq(srs.getInt("seq"));
				dto.setMreview_seq(srs.getInt("m_review_seq"));
				dto.setPic(srs.getString("pic"));
					
				list.add(dto);
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			JdbcUtil.close(srs);
			JdbcUtil.close(spstmt);
			JdbcUtil.close(scon);
		}
		
	
		return list;
		
	}

//약리뷰 전체페이지 가져오기
	public PageBlock getMPage(int m_code, PageBlock pageBlock, String searchWord) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "select count(*) totalRecords, ceil(count(*)/?) totalPages\r\n" + 
				"from m_review where m_code = ? and regexp_like(contents, ?)";
		
		
		int totalPages =0;
		
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pageBlock.getNumberPerPage());
			pstmt.setInt(2, m_code);
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

	//약 좋아요
	public int insertMUserLike(Connection con, int mreview_seq, String id) throws NamingException, SQLException {
		
		con = ConnectionProvider.getConnection();
		String sql = "insert into mreview_likes values(mreview_likes_seq.nextval, ?, ?)";
		
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, mreview_seq);
		pstmt.setString(2, id);
		
		int result = pstmt.executeUpdate();
		
		JdbcUtil.close(pstmt);
		JdbcUtil.close(con);
		
		return result;
		
	}
	
	public int deleteMUserLike(Connection con, int mreview_seq, String id) throws NamingException, SQLException {
		con = ConnectionProvider.getConnection();
		String sql = "delete mreview_likes where mreview_seq=? and id=? ";
		
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, mreview_seq);
		pstmt.setString(2, id);
		
		int result = pstmt.executeUpdate();
	
		JdbcUtil.close(pstmt);
		JdbcUtil.close(con);
		
		return result;
	}
	
	public int selectMLikes(Connection con, int mreview_seq) throws NamingException, SQLException {
		con = ConnectionProvider.getConnection();
		String sql = "select count(*) from mreview_likes where mreview_seq=?";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, mreview_seq);
		rs = pstmt.executeQuery();
		
		int result = 0;
		if (rs.next()) {
			
			result = rs.getInt(1);
		}
		
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(con);
		
		return result;
	}
	
	public void MupdateLike(Connection con, int mreview_seq, String book) {
		try {
			
			con = ConnectionProvider.getConnection();
			
			String sql = "update m_review set likes = likes";
			
			if (book.equals("0")) {
				sql  += " + 1 ";
			}else {
				sql += " - 1 ";
			}
			
			sql += " where mreview_seq = ?";
			
			System.out.println(sql);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mreview_seq);
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con);
			JdbcUtil.close(pstmt);
		}
		
	}

	
	
}
