package beepbeep.community.dto;

import java.sql.Date;

public class FreeListDTO {
	private int no;
	private String title;
	private String id;
	private Date dates;
	private String sdates;
	private int views;
	//
	private String hashtag;
	private String[] tagArr;
	
	private boolean newImg;
	private String pwd;
	
	
	
	public String getSdates() {
		return sdates;
	}

	public void setSdates(String sdates) {
		this.sdates = sdates;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	public boolean isNewImg() {
		return newImg;
	}


	public void setNewImg(boolean newImg) {
		this.newImg = newImg;
	}


	public String[] getTagArr() {
		return tagArr;
	}


	public void setTagArr(String[] tagArr) {
		this.tagArr = tagArr;
	}
	private String contents; // 게시글 내용
	private int fboard_seq;
	
	// 회원사진
	private String photo;
	private String grade_name;
	
	
	
	public String getGrade_name() {
		return grade_name;
	}


	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public int getNo() {
		return no;
	}
	
	
	public int getFboard_seq() {
		return fboard_seq;
	}
	public void setFboard_seq(int fboard_seq) {
		this.fboard_seq = fboard_seq;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
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
