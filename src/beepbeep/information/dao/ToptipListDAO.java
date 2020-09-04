package beepbeep.information.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JdbcUtil;

import beepbeep.information.dto.ToptipListDTO;

public class ToptipListDAO {
	private static final ToptipListDAO instance = new ToptipListDAO();
	public static ToptipListDAO getInstance(){	
		return instance;
	}
	
	public List<ToptipListDTO> selectList(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		String sql = "select toptip_board.tt_code, pic, title, dates, views " +
						"from toptip_board join toptip_attach on(toptip_board.tt_code = toptip_attach.tt_code) " +
						"where pic like '%t.j%' " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				List<ToptipListDTO> toptiplistList = new ArrayList<ToptipListDTO>();
				do {
					ToptipListDTO dto = new ToptipListDTO();
					
					dto.setTt_code(rs.getInt("tt_code"));
					dto.setPic(rs.getString("pic"));
					dto.setTitle(rs.getString("title"));
					dto.setDates(rs.getString("dates"));
					dto.setViews(rs.getInt("views"));
					
					toptiplistList.add(dto);
				}while(rs.next());
				return toptiplistList;
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
	}
	
}
