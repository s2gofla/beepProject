package beepbeep.qna.dto;

import java.util.List;

public class QnaCommentDTO {
	private int ps_seq;
	private String id;
	private int likes;
	private String dates;
	private String contents;
	private String nickname;
	private String mgrade_name;
	private String imageurl;
	private int likes_yn;
	private List<AnswerCommDTO> cocoList;
		
	public List<AnswerCommDTO> getCocoList() {
		return cocoList;
	}
	public void setCocoList(List<AnswerCommDTO> cocoList) {
		this.cocoList = cocoList;
	}
	public int getLikes_yn() {
		return likes_yn;
	}
	public void setLikes_yn(int likes_yn) {
		this.likes_yn = likes_yn;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getMgrade_name() {
		return mgrade_name;
	}
	public void setMgrade_name(String mgrade_name) {
		this.mgrade_name = mgrade_name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getPs_seq() {
		return ps_seq;
	}
	public void setPs_seq(int ps_seq) {
		this.ps_seq = ps_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
}
