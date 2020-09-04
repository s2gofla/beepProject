package beepbeep.community.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.community.dao.DiseaseDAO;
import beepbeep.community.dao.DonationDAO;
import beepbeep.community.dto.DonationContentDTO;

public class DonationWriteService {
	private static DonationWriteService instance = new DonationWriteService();

	public static DonationWriteService getInstance() {
		return instance;
	}

	private DonationWriteService() {
		
	}

	public int donationwrite(DonationContentDTO dto) throws NamingException, SQLException {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			DonationDAO dao = DonationDAO.getInstance();
			int result = dao.donationInsert(conn, dto);
					
			System.out.println("글쓰기");
			int result2 = dao.insertPic(conn, dto);
			System.out.println("첨부파일");
			return result+result2;
		}finally {
			JdbcUtil.close(conn);
		}
	}



}
