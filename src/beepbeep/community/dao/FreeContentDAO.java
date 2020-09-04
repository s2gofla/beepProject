package beepbeep.community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.util.JdbcUtil;

import beepbeep.community.dto.FreeCommentDTO;
import beepbeep.community.dto.FreeListDTO;

public class FreeContentDAO {
	private static FreeContentDAO instance = new FreeContentDAO();

	public static FreeContentDAO getInstance() {
		return instance;
	}

	private FreeContentDAO() {}
	
	
	// 조회수 증가
	public int updateReadcount(Connection conn, int fboard_seq) {
		String sql = "update free_board set views = views +1 where fboard_seq = ?";

		PreparedStatement pstmt = null;		
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fboard_seq);
			result = pstmt.executeUpdate();
		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			try { pstmt.close();} catch (SQLException e) {	e.printStackTrace();	}
			//try {conn.close();  } catch (SQLException e) {	e.printStackTrace();}
		}
		return result;///
	}
	
	
	
	// 게시글 얻어오기
	public FreeListDTO selectOne(Connection conn, int fboard_seq) {
		String sql = "select f.*,photo,grade_name from free_board f join membert m on f.id = m.id join grade g on m.mgrade_code=g.mgrade_code where fboard_seq=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FreeListDTO dto = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,fboard_seq);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new FreeListDTO();
				
				//dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setDates(rs.getDate("dates"));
				dto.setSdates(rs.getString("dates"));
				dto.setViews(rs.getInt("views"));
				dto.setFboard_seq(rs.getInt("fboard_seq"));
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
	
	// 글쓰기
	   public int freeInsert(Connection conn, String id, String title, String hashtag, String contents ) throws SQLException {
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      int result = 0;
		      String sql = "insert into free_board  "
				+ "values(free_seq.nextval, ?, ?, sysdate,0, ?, ?) "; /* free_seq.nextval */
		      try {
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, id);
		         pstmt.setString(2, title);
		         pstmt.setString(3, hashtag);
		         pstmt.setString(4, contents);
		         
		         result = pstmt.executeUpdate();
		         return result;
		      }finally {
		         JdbcUtil.close(pstmt);
		      }
		      
		   }
	
	   public int freeUpdate(Connection conn, String id, String title, String hashtag, String contents, int fboard_seq ) throws SQLException {
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      int result = 0;
		      String sql = "update free_board set title=?, dates=sysdate, contents=?, hashtag=? where fboard_seq=?";
		      try {
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, title);
		         pstmt.setString(2, contents);
		         pstmt.setString(3, hashtag);
		         pstmt.setInt(4, fboard_seq);
		         
		         result = pstmt.executeUpdate();
		         return result;
		      }finally {
		         JdbcUtil.close(pstmt);
		      }
		      
		   }

		public int ComCount(Connection conn, int fboard_seq) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select count(*) from free_comment f join membert m on f.id = m.id where fboard_seq=?";
				
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, fboard_seq);

					rs = pstmt.executeQuery(); // 총 페이지 수 반환
				rs.next();
				return rs.getInt(1);
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}
	   
	public List<FreeCommentDTO> selectcom(Connection conn, int fboard_seq, String id) throws SQLException {
		String sql = "select f.*,photo from free_comment f join membert m on f.id = m.id where fboard_seq=? order by fcoment_seq desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fboard_seq);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				List<FreeCommentDTO> ComList = new ArrayList<FreeCommentDTO>();
				do {
				FreeCommentDTO comdto = new FreeCommentDTO();
				
				comdto.setFcoment_seq(rs.getInt("fcoment_seq"));
				comdto.setPhoto(rs.getString("photo"));
				comdto.setId(rs.getString("id"));
				comdto.setDates(rs.getDate("dates"));
				comdto.setSdates(rs.getString("dates"));
				comdto.setContents(rs.getString("contents"));
				//comdto.setLikes(rs.getInt("likes"));
				int likes = likeCount(conn, comdto.getFcoment_seq());
				comdto.setLikes(likes);
				int id_alikes = answerLike(conn, comdto.getFcoment_seq(), id);
				comdto.setId_alikes(id_alikes);
				
				List<FreeCommentDTO> ccoList = selectccom(conn, comdto.getFcoment_seq());
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
	// 내가 좋아요를 했는지 여부 1또는 0
		private int answerLike(Connection conn, int fcoment_seq, String sid) throws SQLException {
			String sql = "select count(*) from free_likes where fcoment_seq=? and id=? ";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, fcoment_seq);
				pstmt.setString(2, sid);
				rs = pstmt.executeQuery();
				rs.next();
				return rs.getInt(1);
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}

		}
	// 좋아요 수 세기
		private int likeCount(Connection conn, int fcoment_seq) throws SQLException {
			String sql = "select count(*) from free_likes where fcoment_seq = ?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, fcoment_seq);
				rs = pstmt.executeQuery();
				rs.next();
				return rs.getInt(1); 
			}finally {
				JdbcUtil.close(pstmt);
				JdbcUtil.close(rs);
			}
		}	
	
	

	private List<FreeCommentDTO> selectccom(Connection conn, int fcoment_seq) throws SQLException {
		String sql = "select f.*,photo from free_reply f join membert m on f.id = m.id where fcoment_seq=? order by dates desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<FreeCommentDTO> ccoList = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fcoment_seq);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ccoList = new ArrayList<FreeCommentDTO>();
				do {
					FreeCommentDTO comdto = new FreeCommentDTO();
					//comdto.setFcoment_seq(rs.getInt("freply_seq"));
					comdto.setFcoment_seq(rs.getInt("fcoment_seq"));
					comdto.setPhoto(rs.getString("photo"));
					comdto.setId(rs.getString("id"));
					comdto.setDates(rs.getDate("dates"));
					comdto.setSdates(rs.getString("dates"));
					comdto.setContents(rs.getString("contents"));
					comdto.setFreply_seq(rs.getInt("freply_seq"));
					ccoList.add(comdto);
				}while(rs.next());
			}
			return ccoList;
		}finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }

	}
	

	   
	   
}
