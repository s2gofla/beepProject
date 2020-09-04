package beepbeep.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.Collections;
import com.util.JdbcUtil;

public class MainAjaxDAO {
	private static MainAjaxDAO instance = new MainAjaxDAO();
	public static MainAjaxDAO getInstance() {
		return instance;
	}
	private MainAjaxDAO() { }
	
	public List<String> autoFList(Connection conn, String autoWord) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = null;
		String s1 = "";
		String str = "%"+autoWord+"%";
		String sql = "SELECT SYMPTOM_INFO FROM SYMPTOM WHERE SYMPTOM_INFO LIKE  ?  ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<String>();
				do {
					s1 = "#"+rs.getString("SYMPTOM_INFO");
					list.add(s1);
				}while(rs.next());
			}else {
				list = Collections.emptyList();
			}
			return list;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

	}
	public List<String> autoFFList(Connection conn, String autoWord) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = null;
		String s1 = "";
		String str = "%"+autoWord+"%";
		String sql = "SELECT SS_NAME FROM SORE_SPOT WHERE SS_NAME LIKE  ?  ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<String>();
				do {
					s1 = "#"+rs.getString("SS_NAME");
					list.add(s1);
				}while(rs.next());
			}else {
				list = Collections.emptyList();
			}
			return list;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public List<String> msubNameList(Connection conn, int m_sub_seq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = null;
		String sql = "select treatment_name from treatment where m_sub_seq= ? order by treatment_name ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_sub_seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<String>();
				do {
					list.add(rs.getString("treatment_name"));	
				}while(rs.next());
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		
		return list;
	}
	
	public int msubNameCount(Connection conn, int m_sub_seq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from treatment where m_sub_seq= ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_sub_seq);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
}
