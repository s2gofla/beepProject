package beepbeep.cs.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.cs.dao.Cs_FaqDAO;
import beepbeep.cs.model.Cs_FaqDTO;



public class FaqEdit {



	public int write( Cs_FaqDTO dto ) {
		Cs_FaqDAO dao = Cs_FaqDAO.getInstance();		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();	
			int insertedCount = dao.insert(conn, dto); 
			if (insertedCount == 0) {
				//throw new RuntimeException("fail to insert article");
			}
			return insertedCount;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} catch (NamingException e) {  
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

}
