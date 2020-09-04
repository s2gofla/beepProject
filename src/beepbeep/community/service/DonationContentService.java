package beepbeep.community.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.community.dao.DonationDAO;
import beepbeep.community.dto.DonationContentDTO;
import beepbeep.community.dto.DonationListDTO;

public class DonationContentService {

	public DonationListDTO selectOne(int d_seq) {
		DonationListDTO dto = null;
		
		DonationDAO dao = DonationDAO.getInstance();
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			dao.updateReadcount(conn, d_seq); // 조회수 증가
			dto = dao.selectOne(conn, d_seq);

			conn.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}
		return dto;
		
	}

	public static List<DonationContentDTO> selectCom(int d_seq, String id) {
		DonationContentDTO comdto = null;
		
		DonationDAO dao = DonationDAO.getInstance();
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			int Comcount = dao.ComCount(conn, d_seq);
			
			List<DonationContentDTO> ComList = null;
			if(Comcount>0) {
				ComList = dao.selectcom(conn, d_seq, id);
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
