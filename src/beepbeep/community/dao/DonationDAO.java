package beepbeep.community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.util.JdbcUtil;

import beepbeep.community.dto.DiseaseListDTO;
import beepbeep.community.dto.DonationContentDTO;
import beepbeep.community.dto.DonationListDTO;

public class DonationDAO {
	private static DonationDAO instance = new DonationDAO();

	public static DonationDAO getInstance() {
		return instance;
	}

	private DonationDAO() {}
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public int selectCount(Connection conn, int searchCondition, String searchWord) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from donation_board ";
			// select count(*) from free_board d where regexp_like(title, '다이어트')
			switch (searchCondition) {
			case 1:
				sql += " where regexp_like( title, ?, 'i') "; // i 대소문자 다 가능
				break;
			case 2:
				sql += " where regexp_like( id, ?, 'i') "; 
				break;
			case 3:
				sql += " where regexp_like( contents, ?, 'i') "; 
				break;
			case 4:
				sql += " where regexp_like( title, ?, 'i')		or 		regexp_like( contents, ?, 'i') "; 
				break;
			}
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchWord); // 한 페이지에 게시글 수
				// 검색어
				if(searchCondition == 4) pstmt.setString(2, searchWord); // 위에 case 4 는 제목이나 내용으로 검색해서 ?가 2개이므로 한 개 더 추가

				rs = pstmt.executeQuery(); // 총 페이지 수 반환
			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<DonationListDTO> selectList(Connection conn, int searchCondition, String searchWord, int firstRow, int endRow) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		String sql = "select * from ( select rownum no, t.* from( SELECT  d.*, case   when ( sysdate - dates)  between 0 and 1/24   then 'true'  else 'false'  end newImg FROM donation_board d ";
		switch (searchCondition) {
		case 1:
			sql += " where regexp_like( title, ?, 'i') "; // i 대소문자 다 가능
			break;
		case 2:
			sql += " where regexp_like( id, ?, 'i') "; 
			break;
		case 3:
			sql += " where regexp_like( contents, ?, 'i') "; 
			break;
		case 4:
			sql += " where regexp_like( title, ?, 'i')		or 		regexp_like( contents, ?, 'i') "; 
			break;
		}
		sql += " order by dates )t )a where no between ? and ? order by no desc ";
		
		pstmt = conn.prepareStatement(sql);

		if( searchCondition == 4) {
			pstmt.setString(1, searchWord);
			pstmt.setString(2, searchWord);
			pstmt.setInt(3, firstRow);
			pstmt.setInt(4,endRow);
		}else {
			pstmt.setString(1, searchWord);
			pstmt.setInt(2, firstRow);
			pstmt.setInt(3,endRow);
		}
		
		rs = pstmt.executeQuery();
		if(rs.next()) {
			List<DonationListDTO> donationList = new ArrayList<DonationListDTO>();
			do {
				DonationListDTO dto = new DonationListDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				String title = rs.getString("title");
				title = title.replace("<", "&lt;");
				title = title.replace(">", "&gt;");
				
				if(searchCondition == 1 && !searchWord.equals("*")) {
					title = title.replace(searchWord, String.format("<span class='searchWord'>%s</span>", searchWord));
				}
				dto.setTitle(title);
				dto.setDates(rs.getDate("dates"));
				dto.setViews(rs.getInt("views"));
				dto.setHeader(rs.getString("header"));
				dto.setD_seq(rs.getInt("d_seq"));
				dto.setNewImg(Boolean.parseBoolean(rs.getString("newImg")));
				
				donationList.add(dto);
			} while (rs.next());
			return donationList;
		}else {
			return Collections.emptyList();
		}
		
	}finally {
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
	}
	
}

	public int updateReadcount(Connection conn, int d_seq) {
		String sql = "update donation_board set views = views +1 where d_seq = ?";

		PreparedStatement pstmt = null;		
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, d_seq);
			result = pstmt.executeUpdate();
		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			try { pstmt.close();} catch (SQLException e) {	e.printStackTrace();	}
			//try {conn.close();  } catch (SQLException e) {	e.printStackTrace();}
		}
		return result;///
	}

	// 기부/나눔 게시판 리스트
	public DonationListDTO selectOne(Connection conn, int d_seq) {
		String sql = "select d.*,photo,grade_name from donation_board d join membert m on d.id = m.id join grade g on m.mgrade_code=g.mgrade_code where d_seq=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DonationListDTO dto = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,d_seq);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new DonationListDTO();
				
				dto.setId(rs.getString("id"));
				dto.setHeader(rs.getString("header"));
				dto.setTitle(rs.getString("title"));
				dto.setDates(rs.getDate("dates"));
				dto.setSdates(rs.getString("dates"));
				dto.setViews(rs.getInt("views"));
				dto.setD_seq(rs.getInt("d_seq"));
				dto.setContents(rs.getString("contents"));
				dto.setPhoto(rs.getString("photo"));
				dto.setGrade_name(rs.getString("grade_name"));
				dto.setM_sub_seq(rs.getInt("m_sub_seq"));
			}
			
		}catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			try { pstmt.close();} catch (SQLException e) {	e.printStackTrace();	}
			try {rs.close();    } catch (SQLException e) {	e.printStackTrace();}
			//try {conn.close();  } catch (SQLException e) {	e.printStackTrace();}
		}
		return dto;
	}

	public int ComCount(Connection conn, int d_seq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from donation_comment d join membert m on d.id = m.id where d_seq=?";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, d_seq);

				rs = pstmt.executeQuery(); // 총 페이지 수 반환
			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 댓글 가져오기
	public List<DonationContentDTO> selectcom(Connection conn, int d_seq, String id) throws SQLException {
		String sql = "select d.*,photo from donation_comment d join membert m on d.id = m.id where d_seq=? order by seq desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, d_seq);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				List<DonationContentDTO> ComList = new ArrayList<DonationContentDTO>();
				do {
					DonationContentDTO comdto = new DonationContentDTO();
					
					comdto.setSeq(rs.getInt("seq"));
					comdto.setPhoto(rs.getString("photo"));
					comdto.setId(rs.getString("id"));
					comdto.setDates(rs.getDate("dates"));
					comdto.setSdates(rs.getString("dates"));
					comdto.setContents(rs.getString("contents"));
					
					
					int likes = likeCount(conn, comdto.getSeq());
					comdto.setLikes(likes);
					int id_alikes = answerLike(conn, comdto.getSeq(), id);
					comdto.setId_alikes(id_alikes);
					
					List<DonationContentDTO> ccoList = selectccom(conn, comdto.getSeq());
					comdto.setCcoList(ccoList);
					
					ComList.add(comdto);
				}while(rs.next());
				return ComList;
			}else {
				return Collections.emptyList();
			}
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private List<DonationContentDTO> selectccom(Connection conn, int seq) throws SQLException {
		String sql = "select d.*,photo from donation_reply d join membert m on d.id = m.id where seq=? order by dates desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<DonationContentDTO> ccoList = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ccoList = new ArrayList<DonationContentDTO>();
				do {
					DonationContentDTO comdto = new DonationContentDTO();
					comdto.setDoreply_seq(rs.getInt("doreply_seq"));
					comdto.setSeq(rs.getInt("seq"));
					comdto.setPhoto(rs.getString("photo"));
					comdto.setId(rs.getString("id"));
					comdto.setDates(rs.getDate("dates"));
					comdto.setSdates(rs.getString("dates"));
					comdto.setContents(rs.getString("contents"));
					ccoList.add(comdto);
				}while(rs.next());
			}
			return ccoList;
		}finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	}

	private int answerLike(Connection conn, int seq, String id) throws SQLException {
		String sql = "select count(*) from donation_likes where seq=? and id=? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

	}

	private int likeCount(Connection conn, int seq) throws SQLException {
		String sql = "select count(*) from donation_likes where seq = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1); 
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}

	public String selectOpwd(Connection conn, int d_seq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DonationListDTO dto = null;
		//int result = 0;
		String oPwd = null;
		String sql = "select pwd from donation_board d join membert m on d.id=m.id where d_seq=?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, d_seq);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				oPwd = rs.getString("pwd");
			}

		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return oPwd;
	}

	public int donationDelete(Connection conn, int d_seq, String pPwd) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			String sql = "delete from donation_board where d_seq=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, d_seq);
			
			result = pstmt.executeUpdate();
			
			return result;
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int donationInsert(Connection conn, String id, String title, String header, String contents) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      int result = 0;
	      String sql = "insert into donation_board  "
			+ "values(donation_seq.nextval, ?, ?,?, sysdate,0, ?) "; /* free_seq.nextval */
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, header);
	         pstmt.setString(2, id);
	         pstmt.setString(3, title);

	         pstmt.setString(4, contents);
	         
	         result = pstmt.executeUpdate();
	         return result;
	      }finally {
	         JdbcUtil.close(pstmt);
	      }
	}

	public int donationInsert(Connection conn, DonationContentDTO dto) throws SQLException {
	      PreparedStatement pstmt1 = null;
	      ResultSet rs1 = null;
	      int result = 0;

	      String sql1 = "insert into donation_board (D_SEQ,HEADER,ID,TITLE,DATES,VIEWS,CONTENTS)  "
			+ "values(donation_seq.nextval, ?, ?,?, sysdate,0, ?) "; 
	      try {
	         pstmt1 = conn.prepareStatement(sql1);
	         pstmt1.setString(1, dto.getHeader());
	         pstmt1.setString(2, dto.getId());
	         pstmt1.setString(3, dto.getTitle());

	         pstmt1.setString(4, dto.getContents());
	         
	         
	         result = pstmt1.executeUpdate();
	        
	         return result;
	      }finally {
	         JdbcUtil.close(pstmt1);
	      }
	}

	public int insertPic(Connection conn, DonationContentDTO dto) throws SQLException {
	      PreparedStatement pstmt2 = null;

	      String sql2 = "insert into donation_pic values (dopic_seq.nextval, donation_seq.currval, ?)";
	      try {
	         pstmt2 = conn.prepareStatement(sql2);
	         
	         

	         pstmt2.setString(1, dto.getPic());

	         int result2 = pstmt2.executeUpdate();
	         return result2;
	      } finally {
	         JdbcUtil.close(pstmt2);         
	      }

	}
	
}//
