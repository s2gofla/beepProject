package beepbeep.cs.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.cs.dao.Cs_NoticeDAO;
import beepbeep.cs.model.Cs_NoticeDTO;

public class NoticeWrite {
	private static NoticeWrite instance = new NoticeWrite();
	public static NoticeWrite getInstance() {
		return instance;
	}
	private NoticeWrite() {
		
	}
	
	public int write(Cs_NoticeDTO dto) {
		Connection conn = null;
		int result = 0;
		try {
			conn = ConnectionProvider.getConnection();
			Cs_NoticeDAO dao = Cs_NoticeDAO.getInstance();
			result = dao.insert(conn, dto);
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

