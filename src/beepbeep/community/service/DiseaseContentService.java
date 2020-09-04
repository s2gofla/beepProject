package beepbeep.community.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.community.dao.DiseaseDAO;
import beepbeep.community.dto.DiseaseContentDTO;
import beepbeep.community.dto.DiseaseListDTO;

public class DiseaseContentService {

	public DiseaseListDTO selectOne(int dtip_seq) {
		DiseaseListDTO dto = null;
		
		DiseaseDAO dao = DiseaseDAO.getInstance();
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			dao.updateReadcount(conn, dtip_seq); // 조회수 증가
			dto = dao.selectOne(conn, dtip_seq);
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

	public static List<DiseaseContentDTO> selectCom(int dtip_seq, String id) {
		DiseaseContentDTO comdto = null;
		
		DiseaseDAO dao = DiseaseDAO.getInstance();
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			int Comcount = dao.ComCount(conn, dtip_seq);
			
			List<DiseaseContentDTO> ComList = null;
			if(Comcount>0) {
				ComList = dao.selectcom(conn, dtip_seq, id);
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
