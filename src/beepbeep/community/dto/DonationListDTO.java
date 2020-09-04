package beepbeep.community.dto;

import java.sql.Date;

public class DonationListDTO {
	private int no;
	private String title;
	private String id;
	private Date dates;
	private String sdates;
	private int views;
	private String header;
	
	private boolean newImg;
	private String pwd;
	
	private String contents; // 게시글 내용
	private int d_seq;
	
	// 회원사진
	private String photo;
	private String grade_name;
	
	private int m_sub_seq;
	
	
	
	public int getM_sub_seq() {
		return m_sub_seq;
	}
	public void setM_sub_seq(int m_sub_seq) {
		this.m_sub_seq = m_sub_seq;
	}
	public String getSdates() {
		return sdates;
	}
	public void setSdates(String sdates) {
		this.sdates = sdates;
	}
	public boolean isNewImg() {
		return newImg;
	}
	public void setNewImg(boolean newImg) {
		this.newImg = newImg;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getD_seq() {
		return d_seq;
	}
	public void setD_seq(int d_seq) {
		this.d_seq = d_seq;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getGrade_name() {
		return grade_name;
	}
	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDates() {
		return dates;
	}
	public void setDates(Date dates) {
		this.dates = dates;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	
	
}
