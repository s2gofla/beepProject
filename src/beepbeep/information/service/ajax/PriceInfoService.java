package beepbeep.information.service.ajax;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.information.dao.InfoAjaxDAO;
import beepbeep.information.dto.PriceContentDTO;
import beepbeep.member.service.ajax.IdCheckService;

public class PriceInfoService {
	private static PriceInfoService instance = new PriceInfoService();
	
	public static PriceInfoService getInstance() {
		return instance;
	}
	
	private PriceInfoService() {}

	public PriceContentDTO selectPrice(int pinfo_code) throws NamingException, SQLException {
		Connection conn = ConnectionProvider.getConnection();
		InfoAjaxDAO dao = InfoAjaxDAO.getInstance();
		
		PriceContentDTO dto = dao.selectPrice(conn, pinfo_code);
		
		return dto;
		
	}
}
