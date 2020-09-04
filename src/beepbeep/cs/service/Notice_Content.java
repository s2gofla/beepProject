package beepbeep.cs.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.cs.dao.Cs_NoticeDAO;
import beepbeep.cs.model.Cs_NoticeDTO;

public class Notice_Content {
	public Cs_NoticeDTO selectOne(int notice_seq) {
		Cs_NoticeDTO dto = null;
		Cs_NoticeDAO dao = Cs_NoticeDAO.getInstance();
		
		
		try (Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);
			dao.updateReadcount(conn, notice_seq);
			dto = dao.selectOne(conn, notice_seq);
			conn.commit();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return dto;
	}
}
