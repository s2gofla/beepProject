package beepbeep.community.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.community.dao.DonationDAO;

public class DonationDeleteService {
	private static DonationDeleteService instance = new DonationDeleteService();

	public static DonationDeleteService getInstance() {
		return instance;
	}

	private DonationDeleteService() {
		
	}

	public int donationdelete(int d_seq, String pPwd) throws NamingException, SQLException {
		Connection conn = null;
		int result=0;
		try {
			conn = ConnectionProvider.getConnection();
			
			DonationDAO dao = DonationDAO.getInstance();
			String oPwd = dao.selectOpwd(conn, d_seq);
			if(oPwd.equals(pPwd)) {
				result = dao.donationDelete(conn, d_seq, pPwd);
				return result;
			}
		}finally {
			JdbcUtil.close(conn);
		}
		return result;
	}
}
