package beepbeep.member.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.sun.org.apache.regexp.internal.RESyntaxException;
import com.util.JdbcUtil;

import beepbeep.member.dto.ChangeClauseDTO;
import beepbeep.member.dto.ChangeInfoDTO;
import beepbeep.member.dto.LoginDTO;
import beepbeep.member.dto.MemberDTO;
import beepbeep.member.dto.MypageDTO;
import beepbeep.member.service.SignUpService;
import sun.security.action.GetLongAction;

public class MemberDAO {

	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	private MemberDAO() {}	

	public LoginDTO selectById(Connection conn, String signID)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt=conn.prepareStatement("select * from membert join grade on membert.mgrade_code=grade.mgrade_code where id=? ");
			pstmt.setString(1,signID);
			rs=pstmt.executeQuery();
			LoginDTO dto = new LoginDTO();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("pwd"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPhone(rs.getString("phone"));
				dto.setPhoto(rs.getString("photo"));
				dto.setGrade(rs.getString("grade_name"));
				dto.setMgrade_code(rs.getInt("mgrade_code"));

				
				
			}
			return dto;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}


	}

	//의사
	public int insertMember1(Connection conn, MemberDTO dto) throws SQLException {
		PreparedStatement pstmt = null;

		String sql = "insert into membert values(?,?,?,3,?,?,?,?,?,?,?,?,sysdate,sysdate,?)";
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setInt(2, dto.getGrade());
			pstmt.setInt(3, dto.getMcode());
			pstmt.setString(4, dto.getpassword());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getPhone());
			pstmt.setString(7, dto.getNickname());
			pstmt.setString(8, dto.getName());
			pstmt.setDate(9, dto.getBirth());
			pstmt.setString(10, dto.getSex());
			pstmt.setString(11, dto.getPhoto());
			pstmt.setInt(12, dto.getGcode());

			int result1 = pstmt.executeUpdate();
			return result1;
		} finally {
			JdbcUtil.close(pstmt);			
		}

	}
	
	public int insertCertification(Connection conn, MemberDTO dto) throws SQLException {
		PreparedStatement pstmt = null;

		String sql = "insert into d_certification values(certification_seq.nextval,?,?,?,0)";
		try {
			pstmt = conn.prepareStatement(sql);
			
			System.out.println(dto.getLicenseNo());
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getLicenseNo());
			
			pstmt.setString(3, dto.getLicenseFile());
	

			int result2 = pstmt.executeUpdate();
			return result2;
		} finally {
			JdbcUtil.close(pstmt);			
		}

	}
	
	
	//일반
	public int insertMember2(Connection conn, MemberDTO dto) throws SQLException {
		PreparedStatement pstmt = null;

		String sql = "insert into membert values(?,?,?,3,?,?,?,?,?,?,?,?,sysdate,sysdate,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setInt(2, dto.getGrade());
			pstmt.setInt(3, dto.getMcode());
			pstmt.setString(4, dto.getpassword());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getPhone());
			pstmt.setString(7, dto.getNickname());
			pstmt.setString(8, dto.getName());
			pstmt.setDate(9, dto.getBirth());
			pstmt.setString(10, dto.getSex());
			pstmt.setString(11, dto.getPhoto());
			pstmt.setInt(12, dto.getGcode());

			int result3 = pstmt.executeUpdate();
			return result3;
		} finally {
			JdbcUtil.close(pstmt);			
		}

	}
	

	

	public int updateInfo(Connection conn, ChangeInfoDTO dto, String userId) throws SQLException {


		String sql = "update membert set pwd=?, phone=?, nickname=?, photo=? where id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getPhone());
			pstmt.setString(3, dto.getNickname());
			pstmt.setString(4, dto.getPhoto());
			pstmt.setString(5, userId);



			int result4 = pstmt.executeUpdate();
			return result4;
		} finally {
			JdbcUtil.close(pstmt);			
		}

	}

	public int updateClause(Connection conn, ChangeClauseDTO dto, String userId) throws SQLException {
		
		PreparedStatement pstmt = null;

		try {
			String sql = "update membert set marketing_seq=?, location_seq=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTc1());
			pstmt.setString(2, dto.getTc2());
			pstmt.setString(3, userId);
			System.out.println(dto.getTc1());
			System.out.println(dto.getTc2());



			int result5 = pstmt.executeUpdate();
			return result5;
		} finally {
			JdbcUtil.close(pstmt);			
		}

	}



	public int updatePwd(Connection conn,String id, String newPwd1) throws SQLException {
		
		String sql = "update membert set pwd=? where id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPwd1);
			pstmt.setString(2, id);


			int result6 = pstmt.executeUpdate();
			return result6;
		} finally {
			JdbcUtil.close(pstmt);			
		}

	}

	public int deleteMember(Connection conn, String id) throws SQLException {

		PreparedStatement pstmt= null;
		try {
			String sql = "delete from membert where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			
			System.out.println(id);
			

			int result7 = pstmt.executeUpdate();
			return result7;
		
		} finally {
			JdbcUtil.close(pstmt);		

		}
	
		
	}

	
    
	
	
	public int gradeUp(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt1= null;
		PreparedStatement pstmt2= null;
		PreparedStatement pstmt3= null;
		PreparedStatement pstmt4= null;
		PreparedStatement pstmt5= null;
		PreparedStatement pstmt6= null;
		PreparedStatement pstmt7= null;
		PreparedStatement pstmt8= null;
		
		ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;
        ResultSet rs6 = null;
       
		
        int grade=0;
        int result8=0;
        
		try {
			
			String sql1 = "select count(*) from disease_tip where id=?";
			String sql2 = "select count(*) from donation_board where id=?";
			String sql3 = "select count(*) from free_board where id=?";
			String sql4 = "select count(*) from h_review where id=?";
			String sql5 = "select count(*) from m_review where id=?";
			String sql7 = "select mgrade_code from membert where id=?";			
			
			
			
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1,id);
			rs1 = pstmt1.executeQuery();
			rs1.next();
			int disease_tip_C= rs1.getInt(1);
			
			
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1,id);
			rs2=pstmt2.executeQuery();
			rs2.next();
			int donation_board_C= rs2.getInt(1);
			
			pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setString(1,id);
			rs3=pstmt3.executeQuery();
			rs3.next();
			int free_board_C= rs3.getInt(1);
			
			pstmt4 = conn.prepareStatement(sql4);
			pstmt4.setString(1,id);
			rs4=pstmt4.executeQuery();
			rs4.next();
			int h_review_C= rs4.getInt(1);

			
			pstmt5 = conn.prepareStatement(sql5);
			pstmt5.setString(1,id);
			rs5=pstmt5.executeQuery();
			rs5.next();
			int m_review_C= rs5.getInt(1);

	
			pstmt7 = conn.prepareStatement(sql7);
			pstmt7.setString(1, id);
			rs6=pstmt7.executeQuery();
			rs6.next();
			grade = rs6.getInt("mgrade_code");


			int total_C = disease_tip_C + donation_board_C + free_board_C + h_review_C + m_review_C;
			System.out.println("Dao 게시글Tot=="+total_C);
			
			
			String sql6 = "update membert set mgrade_code = mgrade_code+ 1 where id=?";
			

			
			switch (total_C/10) {


			case 1:

				if(grade ==1 || grade==5) {
				pstmt6 = conn.prepareStatement(sql6);
				pstmt6.setString(1,id);
				result8 = pstmt6.executeUpdate();
				conn.commit();	
				}

				break;
				
				
			case 2:
				
				if(grade==2 || grade==6) {
				pstmt6 = conn.prepareStatement(sql6);
				pstmt6.setString(1,id);
				result8 = pstmt6.executeUpdate();
				conn.commit();
				}
				
				break;
				
			case 3:
				
				if(grade==3 || grade==7) {
				pstmt6 = conn.prepareStatement(sql6);
				pstmt6.setString(1,id);
				result8 = pstmt6.executeUpdate();
				conn.commit();
				}
				
				break;
				
				
			default:
	
				break;
			}

		
		} finally {
			JdbcUtil.close(pstmt1);
			JdbcUtil.close(pstmt2);
			JdbcUtil.close(pstmt3);
			JdbcUtil.close(pstmt4);
			JdbcUtil.close(pstmt5);
			JdbcUtil.close(pstmt6);
			JdbcUtil.close(rs1);
			JdbcUtil.close(rs2);
			JdbcUtil.close(rs3);
			JdbcUtil.close(rs4);
			JdbcUtil.close(rs5);
		}
				
		return result8;
	}

	public MemberDTO gradeName(Connection conn, int numgrade) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt=conn.prepareStatement("select * from membert join grade on membert.mgrade_code=grade.mgrade_code where membert.mgrade_code=? ");
			pstmt.setInt(1,numgrade);
			rs=pstmt.executeQuery();
			
			MemberDTO dto= new MemberDTO();
			if(rs.next()) {
			
				dto.setGradeName(rs.getString("grade_name"));
				dto.setGrade(rs.getInt("mgrade_code"));
										
				
			}
			return dto;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	

}
