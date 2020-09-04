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

import beepbeep.community.dto.FreeListDTO;
import beepbeep.community.dto.SubDTO;
import beepbeep.community.handler.FreeDeleteHandler;

public class FreeListDAO {
	private static FreeListDAO instance = new FreeListDAO();

	public static FreeListDAO getInstance() {
		return instance;
	}

	private FreeListDAO() {}
	
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from free_board");
			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	public List<FreeListDTO> selectList(Connection conn, int firstRow, int endRow) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		String sql = "select * from ( select rownum no1, t.* from( SELECT  d.* FROM free_board d order by dates )t )a "
				+ " where no1 between ? and ? order by no1 desc";
		// select t.* from( SELECT rownum no, d.* FROM free_board d order by no desc )t  where t.no between ? and ?
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,firstRow);
		pstmt.setInt(2,endRow);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			List<FreeListDTO> freeList = new ArrayList<FreeListDTO>();
			do {
				FreeListDTO dto = new FreeListDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setDates(rs.getDate("dates"));
				dto.setSdates(rs.getString("dates"));
				dto.setViews(rs.getInt("views"));
				dto.setFboard_seq(rs.getInt("fboard_seq"));

				freeList.add(dto);
			} while (rs.next());
			return freeList;
		}else {
			return Collections.emptyList();
		}
		
	}finally {
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
	}
	
}//

	public int searchCount(Connection conn, int searchCondition, String searchWord) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from free_board ";
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
	
	public List<FreeListDTO> searchList(Connection conn, int searchCondition, String searchWord, int firstRow,
			int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// select * from ( select t.* from( SELECT rownum no, d.* FROM free_board d where regexp_like(title, '다이어트') order by no desc )t )a where a.no between 1 and 13
			String sql = "select * from ( select rownum no, t.* from( SELECT  d.* , case   when ( sysdate - dates)  between 0 and 1/24   then 'true'  else 'false'  end newImg FROM free_board d  ";
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
			

			// 
			
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
				List<FreeListDTO> searchList = new ArrayList<FreeListDTO>();
				do {
					FreeListDTO dto = new FreeListDTO();
					
					
					dto.setNo(rs.getInt("no"));
					dto.setId(rs.getString("id"));
					//dto.setTitle(rs.getString("title"));
					
					//
					String title = rs.getString("title");
					title = title.replace("<", "&lt;");
					title = title.replace(">", "&gt;");
					//
					if(searchCondition == 1 && !searchWord.equals("*")) {
						title = title.replace(searchWord, String.format("<span class='searchWord'>%s</span>", searchWord));
					}
					//
					dto.setTitle(title);
					
					dto.setDates(rs.getDate("dates"));
					dto.setViews(rs.getInt("views"));
					dto.setFboard_seq(rs.getInt("fboard_seq"));
					dto.setNewImg(Boolean.parseBoolean(rs.getString("newImg")));
					searchList.add(dto);
				}while (rs.next());
				return searchList;
			}else {
				return Collections.emptyList();
			}
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
			}

	
	public int freeDelete(Connection conn, int fboard_seq, String pwd) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			String sql = "delete from free_board where fboard_seq=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fboard_seq);
			
			result = pstmt.executeUpdate();
			
			return result;
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public String selectOpwd(Connection conn, int fboard_seq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FreeListDTO dto = null;
		//int result = 0;
		String oPwd = null;
		String sql = "select pwd from free_board f join membert m on f.id=m.id where fboard_seq=?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fboard_seq);
			
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


}
