package beepbeep.cs.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.cs.dao.Cs_AskDAO;

import beepbeep.cs.model.Cs_AskDTO;

public class Ask_Content {
	public Cs_AskDTO selectOne(int qna_seq) {
		Cs_AskDTO dto = null;
		Cs_AskDAO dao = Cs_AskDAO.getInstance();
		
		
		try {
			Connection conn;
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			dto = dao.select(conn, qna_seq);
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
