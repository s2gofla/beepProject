package beepbeep.cs.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.cs.dao.Cs_NoticeDAO;
import beepbeep.cs.model.Cs_NoticeDTO;

public class NoticeDelete {
	private static NoticeDelete instance = new NoticeDelete();
	public static NoticeDelete getInstance() {
		return instance;
	}
	private NoticeDelete() {
		
	}
	
	public int delete(int notice_seq) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			Cs_NoticeDAO dao = Cs_NoticeDAO.getInstance();
			
			result = dao.delete(conn, notice_seq);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
			return result;
		}
		
	
		
		
	}
	
}
