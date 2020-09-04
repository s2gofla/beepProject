package beepbeep.community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.community.dto.FreeCommentDTO;
import oracle.net.aso.a;

public class FreeAjaxDAO {
	
	private static FreeAjaxDAO instance = new FreeAjaxDAO();

	public static FreeAjaxDAO getInstance() {
		return instance;
	}

	private FreeAjaxDAO() {}
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	// 댓글쓰기
	
	   public int freeComInsert( String id, int fboard_seq, String contents ) throws SQLException, NamingException {
		   conn = ConnectionProvider.getConnection();
		   pstmt = conn.prepareStatement("insert into free_comment values(free_com_seq.nextval,?,?,0,sysdate,?,0,?) ");
		   
		         pstmt.setString(1, id);
		         pstmt.setInt(2, fboard_seq);
		         pstmt.setInt(3, fboard_seq);
		         pstmt.setString(4, contents);
		         
		         int result = pstmt.executeUpdate();
		         
		     	JdbcUtil.close(pstmt);
				JdbcUtil.close(conn);
		         return result;
		      
		   }
	   
	   // 좋아요 추가/삭제
		public int updateLikecount(String yn, int fcoment_seq, String id) throws NamingException, SQLException {
			conn = ConnectionProvider.getConnection();
			String sql = "";
			if(yn.equals("y")) {
				sql = "insert into free_likes values(flike_seq.nextval,?,?)";
			}else {
				sql = "delete from free_likes where fcoment_seq=? and id=? ";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fcoment_seq);
			pstmt.setString(2, id);
			int result = pstmt.executeUpdate();
			
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
			
			return result;
		}

		// 좋아요 개수
		public int selectLikes(int fcoment_seq) throws NamingException, SQLException {
			conn = ConnectionProvider.getConnection();
			String sql = "select count(*) from free_likes where fcoment_seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fcoment_seq);
			
			 rs = pstmt.executeQuery();
			 int result = 0;
			 if(rs.next()) {
				 result = rs.getInt(1);
			 }
			 JdbcUtil.close(pstmt);
			 JdbcUtil.close(conn);
			return result;
		}


		// 댓글 불러오기
		public List<FreeCommentDTO> insSelect( int fboard_seq, String id) throws NamingException, SQLException {
			conn = ConnectionProvider.getConnection();
			String sql = "select f.*,photo from free_comment f join membert m on f.id = m.id where fboard_seq=? order by fcoment_seq desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fboard_seq);
			rs = pstmt.executeQuery();
			List<FreeCommentDTO> list = null;
			if(rs.next()) {
				list = new ArrayList<FreeCommentDTO>();
				do {
					FreeCommentDTO dto = new FreeCommentDTO();
					dto.setFcoment_seq(rs.getInt("fcoment_seq"));
					dto.setPhoto(rs.getString("photo"));
					dto.setId(rs.getString("id"));
					//dto.setDates(rs.getDate("dates"));
					dto.setDate_ajax(rs.getString("dates"));
					dto.setContents(rs.getString("contents"));
					dto.setLikes(rs.getInt("likes"));
					
					int likes = likeCount(conn, dto.getFcoment_seq());
					dto.setLikes(likes);
					
					int id_alikes = answerLike(conn, dto.getFcoment_seq(), id);
					dto.setId_alikes(id_alikes);

					
					//List<AnswerCommDTO> cocoList = cocomentSelect(conn, dto.getPs_seq()); // ???
					//dto.setCocoList(cocoList);
					
					//System.out.println(dto.getContents());
					list.add(dto);
				}while(rs.next());
			}
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
			
			return list;
		}
		

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

		// 대댓글 작성
		public int freeCcomInsert(String id, int fcoment_seq, String contents) throws SQLException, NamingException {
			   conn = ConnectionProvider.getConnection();
			   pstmt = conn.prepareStatement("insert into free_reply values (free_ccoseq.nextval, ?, ?, sysdate, ?) ");
			   
			         pstmt.setInt(1, fcoment_seq);
			         pstmt.setString(2, id);
			         pstmt.setString(3, contents);
			         
			         int result = pstmt.executeUpdate();
			         
			     	JdbcUtil.close(pstmt);
					JdbcUtil.close(conn);
			         return result;
		}
		
		// 대댓글 불러오기
		public List<FreeCommentDTO> inscSelect(int fcoment_seq, String id) throws NamingException, SQLException {
			conn = ConnectionProvider.getConnection();
			String sql = "select f.*,photo from free_reply f join membert m on f.id = m.id where fcoment_seq=? order by dates desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fcoment_seq);
			rs = pstmt.executeQuery();
			List<FreeCommentDTO> list = null;
			if(rs.next()) {
				list = new ArrayList<FreeCommentDTO>();
				do {
					FreeCommentDTO dto = new FreeCommentDTO();
					dto.setFcoment_seq(rs.getInt("fcoment_seq"));
					dto.setPhoto(rs.getString("photo"));
					dto.setId(rs.getString("id"));
					//dto.setDates(rs.getDate("dates"));
					dto.setSdates(rs.getString("dates"));;
					dto.setContents(rs.getString("contents"));
					dto.setFreply_seq(rs.getInt("freply_seq"));

					int id_alikes = answerLike(conn, dto.getFcoment_seq(), id);
					dto.setId_alikes(id_alikes);
					
					System.out.println(id_alikes);
					list.add(dto);
				}while(rs.next());
			}
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
			
			return list;
		}

		public int freeComDelete(String id, int fcoment_seq) throws NamingException, SQLException {
			conn =ConnectionProvider.getConnection();

			try {
				String sql = "delete from free_comment where fcoment_seq = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, fcoment_seq);

				int result = pstmt.executeUpdate();
				
				return result;
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}

		public int freeCcomDelete(String id, int freply_seq) throws NamingException, SQLException {
			conn =ConnectionProvider.getConnection();

			try {
				String sql = "delete from free_reply where freply_seq = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, freply_seq);

				int result = pstmt.executeUpdate();
				
				return result;
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}

}
