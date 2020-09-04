package beepbeep.community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import beepbeep.community.dto.DiseaseContentDTO;
import beepbeep.community.dto.DonationContentDTO;

public class DonationAjaxDAO {
	private static DonationAjaxDAO instance = new DonationAjaxDAO();

	public static DonationAjaxDAO getInstance() {
		return instance;
	}

	private DonationAjaxDAO() {}
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public int updateLikecount(String yn, int seq, String id) throws NamingException, SQLException {
		conn = ConnectionProvider.getConnection();
		String sql = "";
		if(yn.equals("y")) {
			sql = "insert into donation_likes values(dolike_seq.nextval,?,?)";
		}else {
			sql = "delete from donation_likes where seq=? and id=? ";
		}
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, seq);
		pstmt.setString(2, id);
		int result = pstmt.executeUpdate();
		
		JdbcUtil.close(pstmt);
		JdbcUtil.close(conn);
		
		return result;
	}

	public int selectLikes(int seq) throws NamingException, SQLException {
		conn = ConnectionProvider.getConnection();
		String sql = "select count(*) from donation_likes where seq = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, seq);
		
		 rs = pstmt.executeQuery();
		 int result = 0;
		 if(rs.next()) {
			 result = rs.getInt(1);
		 }
		 JdbcUtil.close(pstmt);
		 JdbcUtil.close(conn);
		return result;
	}

	public int donationComInsert(String id, int d_seq, String contents) throws NamingException, SQLException {
		   conn = ConnectionProvider.getConnection();
		   pstmt = conn.prepareStatement("insert into donation_comment values (donation_com_seq.nextval, ?, ?, sysdate, 0, ?, 0, ?) ");
		   
		   		 pstmt.setString(1, id);
		   		 pstmt.setInt(2, d_seq);
		         pstmt.setInt(3, d_seq);
		         pstmt.setString(4, contents);
		         
		         int result = pstmt.executeUpdate();
		         
		     	JdbcUtil.close(pstmt);
				JdbcUtil.close(conn);
		         return result;
		      
		   }

	public List<DonationContentDTO> insSelect(int d_seq) throws NamingException, SQLException {
		conn = ConnectionProvider.getConnection();
		String sql = "select d.*,photo from donation_comment d join membert m on d.id = m.id where d_seq=? order by seq desc";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, d_seq);
		rs = pstmt.executeQuery();
		List<DonationContentDTO> list = null;
		
		if(rs.next()) {
			list = new ArrayList<DonationContentDTO>();
			do {
				DonationContentDTO dto = new DonationContentDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setPhoto(rs.getString("photo"));
				dto.setId(rs.getString("id"));
				//dto.setDates(rs.getDate("dates"));
				dto.setSdates(rs.getString("dates"));
				dto.setContents(rs.getString("contents"));
				dto.setLikes(rs.getInt("likes"));
				
				list.add(dto);
			}while(rs.next());
		}
		
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(conn);
		
		return list;
	}

	public int donationCcomInsert(String id, int seq, String contents) throws NamingException, SQLException {
		   conn = ConnectionProvider.getConnection();
		   pstmt = conn.prepareStatement("insert into donation_reply values (donation_ccoseq.nextval, ?, ?, sysdate, ?) ");
		   
		         pstmt.setInt(1, seq);
		         pstmt.setString(2, id);
		         pstmt.setString(3, contents);
		         
		         int result = pstmt.executeUpdate();
		         
		     	JdbcUtil.close(pstmt);
				JdbcUtil.close(conn);
		         return result;
	}

	public List<DonationContentDTO> inscSelect(int seq) throws NamingException, SQLException {
		conn = ConnectionProvider.getConnection();
		String sql = "select d.*,photo from donation_reply d join membert m on d.id = m.id where seq=? order by dates desc";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, seq);
		rs = pstmt.executeQuery();
		List<DonationContentDTO> list = null;
		if(rs.next()) {
			list = new ArrayList<DonationContentDTO>();
			do {
				DonationContentDTO dto = new DonationContentDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setPhoto(rs.getString("photo"));
				dto.setId(rs.getString("id"));
				//dto.setDates(rs.getDate("dates"));
				dto.setSdates(rs.getString("dates"));;
				dto.setContents(rs.getString("contents"));
				dto.setDoreply_seq(rs.getInt("doreply_seq"));

				System.out.println(dto.getContents());
				list.add(dto);
			}while(rs.next());
		}
		
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(conn);
		
		return list;
	}

	public int donationComDelete(String id, int seq) throws NamingException, SQLException {
		conn =ConnectionProvider.getConnection();

		try {
			String sql = "delete from donation_comment where seq = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);

			int result = pstmt.executeUpdate();
			
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int donationCcomDelete(String id, int doreply_seq) throws NamingException, SQLException {
		conn =ConnectionProvider.getConnection();

		try {
			String sql = "delete from donation_reply where doreply_seq = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, doreply_seq);

			int result = pstmt.executeUpdate();
			
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
