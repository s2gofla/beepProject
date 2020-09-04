package beepbeep.information.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;

import beepbeep.information.dao.TopTipContentDAO;
import beepbeep.information.dto.TopTipContentDTO;

public class TopTipContentService {
	private static final TopTipContentService instance = new TopTipContentService();
	public static TopTipContentService getInstance() {
		return instance;
	}
	
	public List<TopTipContentDTO> selectList(int tt_code) throws SQLException, NamingException{
		
		Connection conn = ConnectionProvider.getConnection();
		
		TopTipContentDAO toptipcontentdao = TopTipContentDAO.getInstance();
		List<TopTipContentDTO> toptipcontentList = toptipcontentdao.selectList(conn, tt_code);
		
		return toptipcontentList;
	}
}
