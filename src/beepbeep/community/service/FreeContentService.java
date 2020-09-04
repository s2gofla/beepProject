package beepbeep.community.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.community.dao.FreeContentDAO;
import beepbeep.community.dto.FreeCommentDTO;
import beepbeep.community.dto.FreeListDTO;

public class FreeContentService {

	public FreeListDTO selectOne(int fboard_seq) {
		FreeListDTO dto = null;
		
		// 조회수 증가
		FreeContentDAO dao = FreeContentDAO.getInstance();
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); // 자동 커밋 사용X
			dao.updateReadcount(conn, fboard_seq); // 조회수 증가
			dto = dao.selectOne(conn, fboard_seq);
			String tag = dto.getHashtag();
			if(tag!=null) {
				String[] htag = tag.split(" ");
				for (int i = 0; i < htag.length; i++) {
					htag[i] = htag[i].substring(1);
				}
				dto.setTagArr(htag);
			}
			conn.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}
		return dto;
	}

	public List<FreeCommentDTO> selectCom(int fboard_seq, String id) {
		FreeCommentDTO comdto = null;
		
		FreeContentDAO dao = FreeContentDAO.getInstance();
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int ComCount = dao.ComCount(conn, fboard_seq);
			
			List<FreeCommentDTO> ComList = null;
			if(ComCount>0) {
				ComList = dao.selectcom(conn, fboard_seq, id);
			}else {
				ComList = Collections.emptyList();
			}
			
			return ComList;
		}catch (NamingException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					JdbcUtil.close(conn);
				}
				return null;

	}
	




	
}
