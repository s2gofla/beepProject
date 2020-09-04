package beepbeep.information.service.ajax;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.information.dao.InfoAjaxDAO;
import beepbeep.information.dto.TTPicDTO;

public class ToptipService {
	private static final ToptipService instance = new ToptipService();
	public static ToptipService getInstance() {
		return instance;
	}
	private ToptipService() {}
	
	public TTPicDTO  toptipPic(int tt_code) throws NamingException, SQLException {
		
		Connection conn = ConnectionProvider.getConnection();
		try {
			InfoAjaxDAO dao = InfoAjaxDAO.getInstance();
			
			List<String> list = dao.selectPic(conn, tt_code);
			String title = dao.selectTTtitle(conn, tt_code);
			TTPicDTO dto = new TTPicDTO(list, title);
			return dto;
			
		}finally {
			JdbcUtil.close(conn);
		}
		
		
		

	}
	
}
