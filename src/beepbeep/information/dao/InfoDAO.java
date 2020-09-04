package beepbeep.information.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.util.JdbcUtil;

import beepbeep.information.dto.DiseaseDTO;
import beepbeep.information.dto.DiseaseListCategoryDTO;
import beepbeep.information.dto.DiseaseListDTO;
import beepbeep.information.dto.PriceContentDTO;
import beepbeep.information.dto.PriceListDTO;
import beepbeep.information.dto.TopTipContentDTO;
import beepbeep.information.dto.ToptipCategoryDTO;
import beepbeep.information.dto.ToptipListDTO;

public class InfoDAO {
	private static final InfoDAO instance = new InfoDAO();
	public static InfoDAO getInstance(){	
		return instance;
	}
	private InfoDAO() {}
	
	/* 질환 리스트 */
	public List<DiseaseListDTO> selectList(Connection conn, int firstRow, int endRow, String searchWord) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql =              " select *   " + 
				"from (select rownum no, a.*    " + 
				"         from(      " + 
				"             select seq, d_name, symptom_info, definition , m_sub_name " + 
				"             from disease_board db  left join disease di on di.d_seq = db.d_seq  " + 
				"                                    left join dis_by_sym dbs on di.d_seq=dbs.d_seq  " + 
				"                                    left join symptom sym on dbs.sym_code = sym.sym_code  "+
				"									 join m_sub_type mst on di.m_sub_seq = mst.m_sub_seq ";

		
		if(!searchWord.equals("*")) {
			sql += "             where regexp_like (d_name, ?) or regexp_like(definition, ?) or regexp_like(sym.symptom_info,?) " ;			
		}
		
		sql += 				"             order by seq   " + 
				"            ) a )   " + 
				"where no between ? and ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			if(searchWord.equals("*")) {
				pstmt.setInt(1, firstRow);
				pstmt.setInt(2, endRow);
			}else {
				pstmt.setString(1, searchWord);
				pstmt.setString(2, searchWord);				
				pstmt.setString(3, searchWord);				
				pstmt.setInt(4, firstRow);
				pstmt.setInt(5, endRow);
			}
			System.out.println(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				List<DiseaseListDTO> list = new ArrayList<DiseaseListDTO>();
				do {
					DiseaseListDTO dto = new DiseaseListDTO();
					dto.setSeq(rs.getInt("seq"));
					dto.setD_name(rs.getString("d_name"));
					dto.setDefinition(rs.getString("definition"));
					dto.setM_sub_name(rs.getString("m_sub_name"));
					System.out.println(dto.getD_name());
					list.add(dto);
				}while(rs.next());
				return list;
			}
		} finally {		
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
	}
	
	
	/* 가격 리스트 */
	public List<PriceListDTO> priceList(Connection conn, int firstRow, int endRow, String searchWord, int m_sub_seq) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql =  
				" select * " +
				" from (select rownum no, a.* " +
				" from(      " + 
				" select pinfo_code, pinfo_treatment " +
				" from price_info ";
		if(searchWord.equals("*") && m_sub_seq!=0){
			sql  += " where m_sub_seq = ? ";
		}else if(!searchWord.equals("*") && m_sub_seq!=0 ) {
			sql += " where regexp_like(pinfo_treatment, ?) and m_sub_seq = ? ";
		}else if(!searchWord.equals("*") && m_sub_seq==0){
			sql += " where regexp_like (pinfo_treatment, ?)  ";
		}
		
		sql += " order by pinfo_code  " +
				"            ) a )   " + 
				" where no between ? and ? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			if(searchWord.equals("*") && m_sub_seq!=0){
				pstmt.setInt(1, m_sub_seq);
				pstmt.setInt(2, firstRow);
				pstmt.setInt(3, endRow);
			}else if(!searchWord.equals("*") && m_sub_seq!=0 ) {
				pstmt.setString(1, searchWord);
				pstmt.setInt(2, m_sub_seq);
				pstmt.setInt(3, firstRow);
				pstmt.setInt(4, endRow);
			}else if(!searchWord.equals("*") && m_sub_seq==0){
				pstmt.setString(1, searchWord);
				pstmt.setInt(2, firstRow);
				pstmt.setInt(3, endRow);
			}else {
				pstmt.setInt(1, firstRow);
				pstmt.setInt(2, endRow);
			}
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				List<PriceListDTO> list = new ArrayList<PriceListDTO>();
				do {
					PriceListDTO dto = new PriceListDTO();					
					dto.setPinfo_code(rs.getInt("pinfo_code"));
					dto.setPinfo_treatment(rs.getString("pinfo_treatment"));			
					list.add(dto);
				}while(rs.next());
				return list;
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
	}
	
	
	/* 꿀팁 리스트 */
	public List<ToptipListDTO> toptipList(Connection conn, int firstRow, int endRow, String searchWord) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		String sql = " select * " +
						" from (select rownum no, a.* " +
						" from ( " +
						" select toptip_board.tt_code, pic, title, dates, views " +
						" from toptip_board join toptip_attach on(toptip_board.tt_code = toptip_attach.tt_code) " +
						" where pic like '%t.j%'  "  ;
						 
		
		if(!searchWord.equals("*")) {
			sql += " where regexp_like (title, ?) " ;			
		}
		sql += 		" order by dates desc  " + 
						"            ) a )   " + 
						"where no between ? and ? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			if(searchWord.equals("*")) {
				pstmt.setInt(1, firstRow);
				pstmt.setInt(2, endRow);
			}else {
				pstmt.setString(1, searchWord);				
				pstmt.setInt(2, firstRow);
				pstmt.setInt(3, endRow);
			}
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				List<ToptipListDTO> list = new ArrayList<ToptipListDTO>();
				do {
					ToptipListDTO dto = new ToptipListDTO();			
					dto.setTt_code(rs.getInt("tt_code"));
					dto.setPic(rs.getString("pic"));
					dto.setTitle(rs.getString("title"));
					dto.setDates(rs.getString("dates"));
					dto.setViews(rs.getInt("views"));	
					list.add(dto);
				}while(rs.next());
				return list;
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
	}
	
	
	
	
	
	
	/* 질환 상세정보 */
	public DiseaseDTO diseaseContent (Connection conn, int seq) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select d_name, definition, cause, symptom, diagnosis_treatment, prevention  " +
				"from disease_board join disease on(disease_board.d_seq=disease.d_seq)   " + 
				"where seq = ? " ;
 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			DiseaseDTO dto = null;
			if(rs.next()) {				
					dto = new DiseaseDTO();
					
					dto.setD_name(rs.getString("d_name"));
					dto.setDefinition(rs.getString("definition"));
					dto.setCause(rs.getString("cause"));
					dto.setSymptom(rs.getString("symptom"));
					dto.setDiagnosis_treatment(rs.getString("diagnosis_treatment"));
					dto.setPrevention(rs.getString("prevention"));
				
			}
			return dto;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	/* 가격 상세정보 */
	public List<PriceContentDTO> priceContent(Connection conn, int pinfo_code) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select min_price, avg_price, max_price " + 
						"from price_info " +
						"where pinfo_code = ?" ;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pinfo_code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<PriceContentDTO> pricecontentlist = new ArrayList<PriceContentDTO>();
				do {
					PriceContentDTO dto = new PriceContentDTO();
					dto.setMin_price(rs.getInt("min_price"));
					dto.setAvg_price(rs.getInt("avg_price"));
					dto.setMax_price(rs.getInt("max_price"));
					
					pricecontentlist.add(dto);
				} while(rs.next());
				return pricecontentlist;
			}else {
				return Collections.emptyList();
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	/* 꿀팁 상세 정보 */
	public List<TopTipContentDTO> toptipContent(Connection conn, int tt_code) throws SQLException{
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
	
	
	/* 질환 검색 */
	public int diseaselistCount(Connection conn, String searchWord) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(searchWord.equals("*")) {
			sql  = "select count(*) from Disease_board ";
		}else {
			sql = "select count(*)   " + 
					"             from disease_board db  left join disease di on di.d_seq = db.d_seq   " + 
					"                                    left join dis_by_sym dbs on di.d_seq=dbs.d_seq   " + 
					"                                    left join symptom sym on dbs.sym_code = sym.sym_code   " + 
					"             where regexp_like (d_name, ?) or regexp_like(definition, ?)  or regexp_like(sym.symptom_info, ?) " + 
					"             order by seq ";
		}
		try {
			pstmt = conn.prepareStatement(sql);
			if(!searchWord.equals("*")) {
				pstmt.setString(1, searchWord);
				pstmt.setString(2, searchWord);
				pstmt.setString(3, searchWord);
			}
			rs = pstmt.executeQuery() ;
			rs.next();
			
			return rs.getInt(1);
			
		} finally  {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
 		}
	}
	
	/* 가격 검색 */
	public int pricelistCount(Connection conn, String searchWord, int m_sub_seq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(searchWord.equals("*") && m_sub_seq==0) {
			sql  = "select count(*) from price_info  ";
		}else if(searchWord.equals("*") && m_sub_seq!=0){
			sql  = "select count(*) from price_info where m_sub_seq = ? ";
		}else if(!searchWord.equals("*") && m_sub_seq!=0 ) {
			sql = "select count(*) from price_info where regexp_like(pinfo_treatment, ?) and m_sub_seq = ? ";
		}else if(!searchWord.equals("*") && m_sub_seq==0){
			sql = "select count(*)  from price_info  where regexp_like (pinfo_treatment, ?)  ";
		}
		sql += "   order by pinfo_code " ;
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			if(searchWord.equals("*") && m_sub_seq!=0){
				pstmt.setInt(1, m_sub_seq);
			}else if(!searchWord.equals("*") && m_sub_seq!=0 ) {
				pstmt.setString(1, searchWord);
				pstmt.setInt(2, m_sub_seq);
			}else if(!searchWord.equals("*") && m_sub_seq==0){
				pstmt.setString(1, searchWord);
			}
			rs = pstmt.executeQuery() ;
			rs.next();
			
			return rs.getInt(1);
			
		} finally  {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	/* 꿀팁 검색 */
	public int toptiplistCount(Connection conn, String searchWord) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(searchWord.equals("*")) {
			sql  = "select count(*) from toptip_board ";
		}else {
			sql = " select count(*)   " + 
					"  from toptip_board join toptip_attach on(toptip_board.tt_code = toptip_attach.tt_code)  " + 
					"  where pic like '%t.j%'  and regexp_like (title, ?)  " + 
					"  order by dates desc ";
		}
		try {
			pstmt = conn.prepareStatement(sql);
			if(!searchWord.equals("*")) {
				pstmt.setString(1, searchWord);
			}
			rs = pstmt.executeQuery() ;
			rs.next();
			
			return rs.getInt(1);
			
		} finally  {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
 		}
	}
	
	
	
	
	
	
	
	/* 질환리스트 카테고리 */
	public List<DiseaseListCategoryDTO> diseaselistCategory(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from sore_spot";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<DiseaseListCategoryDTO> diseasecategoryList = new ArrayList<DiseaseListCategoryDTO>();
				do {
					DiseaseListCategoryDTO dto = new DiseaseListCategoryDTO();
					dto.setSs_code(rs.getInt("ss_code"));
					dto.setSs_name(rs.getString("ss_name"));
					
					diseasecategoryList.add(dto);
				}while(rs.next());
				return diseasecategoryList;
			}else {
				return Collections.emptyList();
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	/* 꿀팁 카테고리 */
	public List<ToptipCategoryDTO> toptipCategory(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from toptip_type";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<ToptipCategoryDTO> toptipcategoryList = new ArrayList<ToptipCategoryDTO>();
				do {
					ToptipCategoryDTO dto = new ToptipCategoryDTO();
					dto.setTt_type_code(rs.getInt("tt_type_code"));
					dto.setTt_type_name(rs.getString("tt_type_name"));
					
					toptipcategoryList.add(dto);
				}while(rs.next());
				return toptipcategoryList;
			}else {
				return Collections.emptyList();
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	

	
	
}