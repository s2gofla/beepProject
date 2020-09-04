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

public class DiseaseAjaxDAO {
	private static DiseaseAjaxDAO instance = new DiseaseAjaxDAO();

	public static DiseaseAjaxDAO getInstance() {
		return instance;
	}

	private DiseaseAjaxDAO() {}
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public int updateLikecount(String yn, int seq, String id) throws NamingException, SQLException {
		conn = ConnectionProvider.getConnection();
		String sql = "";
		if(yn.equals("y")) {
			sql = "insert into disease_likes values(dilike_seq.nextval,?,?)";
		}else {
			sql = "delete from disease_likes where seq=? and id=? ";
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
		String sql = "select count(*) from disease_likes where seq = ?";
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

	public int diseaseComInsert(String id, int dtip_seq, String contents) throws NamingException, SQLException {
		   conn = ConnectionProvider.getConnection();
		   pstmt = conn.prepareStatement("insert into disease_tip_comment values (disease_com_seq.nextval, ?, ?, 0, sysdate, ?, 0, ?) ");
		   
		   		 pstmt.setInt(1, dtip_seq);
		         pstmt.setString(2, id);
		         pstmt.setInt(3, dtip_seq);
		         pstmt.setString(4, contents);
		         
		         int result = pstmt.executeUpdate();
		         
		     	JdbcUtil.close(pstmt);
				JdbcUtil.close(conn);
		         return result;
		      
		   }

	public List<DiseaseContentDTO> insSelect(int dtip_seq) throws SQLException, NamingException {
		conn = ConnectionProvider.getConnection();
		String sql = "select d.*,photo from disease_tip_comment d join membert m on d.id = m.id where dtip_seq=? order by seq desc";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, dtip_seq);
		rs = pstmt.executeQuery();
		List<DiseaseContentDTO> list = null;
		if(rs.next()) {
			list = new ArrayList<DiseaseContentDTO>();//???
			do {
				DiseaseContentDTO dto = new DiseaseContentDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setPhoto(rs.getString("photo"));
				dto.setId(rs.getString("id"));
				//dto.setDates(rs.getDate("dates"));
				dto.setSdates(rs.getString("dates"));
				dto.setContents(rs.getString("contents"));
				dto.setLikes(rs.getInt("likes"));

				
				
				
				//List<AnswerCommDTO> cocoList = cocomentSelect(conn, dto.getPs_seq()); // ???
				//dto.setCocoList(cocoList);
				
				//System.out.println(dto.getContents());
				list.add(dto);
			}while(rs.next());
		}
		
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(conn);
		
		return list;
	}

	public int diseaseCcomInsert(String id, int seq, String contents) throws NamingException, SQLException {
		   conn = ConnectionProvider.getConnection();
		   pstmt = conn.prepareStatement("insert into disease_reply values (disease_ccoseq.nextval, ?, ?, sysdate, ?) ");
		   
		         pstmt.setInt(1, seq);
		         pstmt.setString(2, id);
		         pstmt.setString(3, contents);
		         
		         int result = pstmt.executeUpdate();
		         
		     	JdbcUtil.close(pstmt);
				JdbcUtil.close(conn);
		         return result;
	}

	public List<DiseaseContentDTO> inscSelect(int seq) throws SQLException, NamingException {
		conn = ConnectionProvider.getConnection();
		String sql = "select d.*,photo from disease_reply d join membert m on d.id = m.id where seq=? order by dates desc";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, seq);
		rs = pstmt.executeQuery();
		List<DiseaseContentDTO> list = null;
		if(rs.next()) {
			list = new ArrayList<DiseaseContentDTO>();
			do {
				DiseaseContentDTO dto = new DiseaseContentDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setPhoto(rs.getString("photo"));
				dto.setId(rs.getString("id"));
				//dto.setDates(rs.getDate("dates"));
				dto.setSdates(rs.getString("dates"));;
				dto.setContents(rs.getString("contents"));
				dto.setDireply_seq(rs.getInt("direply_seq"));

				System.out.println(dto.getContents());
				list.add(dto);
			}while(rs.next());
		}
		
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(conn);
		
		return list;
	}
}
