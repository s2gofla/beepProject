package beepbeep.information.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JdbcUtil;

import beepbeep.information.dto.TopTipContentDTO;

public class TopTipContentDAO {
	private static final TopTipContentDAO instance = new TopTipContentDAO();
	public static TopTipContentDAO getInstance() {
		return instance;
	}
	
	public List<TopTipContentDTO> selectList(Connection conn, int tt_code) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select toptip_board.tt_code, pic " + 
				"from toptip_board join toptip_attach on(toptip_board.tt_code = toptip_attach.tt_code) " + 
				"where not pic like '%t.j%' and toptip_board.tt_code = ? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tt_code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				List<TopTipContentDTO> toptipcontentList = new ArrayList<TopTipContentDTO>();
				
					TopTipContentDTO toptipcontentdto = new TopTipContentDTO();
				
					toptipcontentdto.setTt_code(rs.getInt("tt_code"));
					toptipcontentdto.setPic(rs.getString("pic"));
					
					toptipcontentList.add(toptipcontentdto);
				
				return toptipcontentList;
			}
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
	}
	
}
