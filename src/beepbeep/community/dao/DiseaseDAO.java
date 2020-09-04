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

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.community.dto.DiseaseContentDTO;
import beepbeep.community.dto.DiseaseListDTO;
import beepbeep.community.dto.DonationContentDTO;
import beepbeep.community.dto.FreeListDTO;
import beepbeep.community.service.DiseaseListService;

public class DiseaseDAO {
	private static DiseaseDAO instance = new DiseaseDAO();

	public static DiseaseDAO getInstance() {
		return instance;
	}

	private DiseaseDAO() {}
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public int selectCount(Connection conn, int searchCondition, String searchWord) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from disease_tip ";
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
			case 5:
				sql += " where regexp_like( hashtag, ?, 'i') "; 
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
	
	public List<DiseaseListDTO> selectList(Connection conn, int searchCondition, String searchWord, int firstRow, int endRow) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from ( select rownum no, t.* from( SELECT  d.*, case   when ( sysdate - dates)  between 0 and 1/24   then 'true'  else 'false'  end newImg FROM disease_tip d  ";
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
			case 5:
				sql += " where regexp_like( hashtag, ?, 'i') "; 
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
			List<DiseaseListDTO> diseaseList = new ArrayList<DiseaseListDTO>();
			do {
				DiseaseListDTO dto = new DiseaseListDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				//dto.setTitle(rs.getString("title"));
				
				String title = rs.getString("title");
				title = title.replace("<", "&lt;");
				title = title.replace(">", "&gt;");
				
				if(searchCondition == 1 && !searchWord.equals("*")) {
					title = title.replace(searchWord, String.format("<span class='searchWord'>%s</span>", searchWord));
				}
				//
				dto.setTitle(title);
				
				dto.setDates(rs.getDate("dates"));
				dto.setViews(rs.getInt("views"));
				dto.setDtip_seq(rs.getInt("dtip_seq"));
				dto.setNewImg(Boolean.parseBoolean(rs.getString("newImg")));
				dto.setM_sub_seq(rs.getInt("m_sub_seq"));

				diseaseList.add(dto);
			} while (rs.next());
			return diseaseList;
		}else {
			return Collections.emptyList();
		}
		
	}finally {
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
	}
	
}
	// 조회수 증가
	public int updateReadcount(Connection conn, int dtip_seq) {
		String sql = "update disease_tip set views = views +1 where dtip_seq = ?";

		PreparedStatement pstmt = null;		
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dtip_seq);
			result = pstmt.executeUpdate();
		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			try { pstmt.close();} catch (SQLException e) {	e.printStackTrace();	}
			//try {conn.close();  } catch (SQLException e) {	e.printStackTrace();}
		}
		return result;///
	}

	// 리스트
	public DiseaseListDTO selectOne(Connection conn, int dtip_seq) {
		String sql = "select d.*,photo,grade_name from disease_tip d join membert m on d.id = m.id join grade g on m.mgrade_code=g.mgrade_code where dtip_seq=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DiseaseListDTO dto = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,dtip_seq);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new DiseaseListDTO();
				
				dto.setId(rs.getString("id"));
				dto.setM_sub_seq(rs.getInt("m_sub_seq"));
				dto.setTitle(rs.getString("title"));
				dto.setDates(rs.getDate("dates"));
				dto.setSdates(rs.getString("dates"));
				dto.setViews(rs.getInt("views"));
				dto.setDtip_seq(rs.getInt("dtip_seq"));
				dto.setContents(rs.getString("contents"));
				dto.setHashtag(rs.getString("hashtag"));
				dto.setPhoto(rs.getString("photo"));
				dto.setGrade_name(rs.getString("grade_name"));
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

	// 댓글 개수
	public int ComCount(Connection conn, int dtip_seq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from disease_tip_comment d join membert m on d.id = m.id where dtip_seq=?";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dtip_seq);

				rs = pstmt.executeQuery(); // 총 페이지 수 반환
			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 댓글 가져오기
	public List<DiseaseContentDTO> selectcom(Connection conn, int dtip_seq, String id) throws SQLException {
		String sql = "select d.*,photo from disease_tip_comment d join membert m on d.id = m.id where dtip_seq=? order by seq desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dtip_seq);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				List<DiseaseContentDTO> ComList = new ArrayList<DiseaseContentDTO>();
				do {
					DiseaseContentDTO comdto = new DiseaseContentDTO();
					
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
					
					List<DiseaseContentDTO> ccoList = selectccom(conn, comdto.getSeq());
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

	private List<DiseaseContentDTO> selectccom(Connection conn, int seq) throws SQLException {
		String sql = "select d.*,photo from disease_reply d join membert m on d.id = m.id where seq=? order by dates desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<DiseaseContentDTO> ccoList = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ccoList = new ArrayList<DiseaseContentDTO>();
				do {
					DiseaseContentDTO comdto = new DiseaseContentDTO();
					comdto.setDireply_seq(rs.getInt("direply_seq"));
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
		String sql = "select count(*) from disease_likes where seq=? and id=? ";
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
		String sql = "select count(*) from disease_likes where seq = ?";
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

	// 치팁 글쓰기
	public int diseaseInsert(Connection conn, String id, int m_sub_seq, String title, String hashtag, String contents) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      int result = 0;
	      String sql = "insert into disease_tip  "
			+ "values(disease_seq.nextval, ?, ?,?, sysdate,0, ?, ?) "; /* free_seq.nextval */
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, m_sub_seq);
	         pstmt.setString(2, id);
	         pstmt.setString(3, title);
	         pstmt.setString(4, hashtag);
	         pstmt.setString(5, contents);
	         
	         result = pstmt.executeUpdate();
	         return result;
	      }finally {
	         JdbcUtil.close(pstmt);
	      }
	}

	// 게시글 수정
	public int diseaseUpdate(Connection conn, String id, int m_sub_seq, String title, String hashtag, String contents,
			int dtip_seq) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      int result = 0;
	      String sql = "update disease_tip set m_sub_seq = ?, title=?, dates=sysdate, hashtag=?, contents=? where dtip_seq=?";
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, m_sub_seq);
	         pstmt.setString(2, title);
	         pstmt.setString(3, hashtag);
	         pstmt.setString(4, contents);
	         pstmt.setInt(5, dtip_seq);
	         
	         result = pstmt.executeUpdate();
	         return result;
	      }finally {
	         JdbcUtil.close(pstmt);
	      }
}

	public String selectOpwd(Connection conn, int dtip_seq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DiseaseListDTO dto = null;
		//int result = 0;
		String oPwd = null;
		String sql = "select pwd from disease_tip d join membert m on d.id=m.id where dtip_seq=?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dtip_seq);
			
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

	public int diseaseDelete(Connection conn, int dtip_seq, String pPwd) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			String sql = "delete from disease_tip where dtip_seq=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dtip_seq);
			
			result = pstmt.executeUpdate();
			
			return result;
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int diseaseComDelete(String id, int seq) throws NamingException, SQLException {
		conn =ConnectionProvider.getConnection();

		try {
			String sql = "delete from disease_tip_comment where seq = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);

			int result = pstmt.executeUpdate();
			
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}


public int diseaseCcomDelete(String id, int direply_seq) throws NamingException, SQLException {
	conn =ConnectionProvider.getConnection();

	try {
		String sql = "delete from disease_reply where direply_seq = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, direply_seq);

		int result = pstmt.executeUpdate();
		
		return result;
	}finally {
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
	}
}


}