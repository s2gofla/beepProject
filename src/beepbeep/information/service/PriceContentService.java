package beepbeep.information.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.information.dao.InfoDAO;
import beepbeep.information.dto.PriceContentDTO;

public class PriceContentService {
	private static PriceContentService instance = new PriceContentService();
	public static PriceContentService getInstance() {
		return instance;
	}
	
	public List<PriceContentDTO> pricecontentlist(int pinfo_code){
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			InfoDAO pricecontentdao = InfoDAO.getInstance();
			List<PriceContentDTO> pricecontentlist = pricecontentdao.priceContent(conn, pinfo_code);
			return pricecontentlist;
		} catch(NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return null;
	}
}
