package beepbeep.qna.dto;

import java.util.Date;

public class QnaListDTO {
	private int pq_seq;

	private String m_sub_name;
	private int m_sub_seq;
	private String id;
	private String nickname;
	private String q_title;
	private int likes;
	private int views;
	private String dates;
	private String contents;
	private String imageurl;
	private int answerCnt;
	
	
	public int getAnswerCnt() {
		return answerCnt;
	}
	public void setAnswerCnt(int answerCnt) {
		this.answerCnt = answerCnt;
	}
	public int getM_sub_seq() {
		return m_sub_seq;
	}
	public void setM_sub_seq(int m_sub_seq) {
		this.m_sub_seq = m_sub_seq;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getPq_seq() {
		return pq_seq;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public void setPq_seq(int pq_seq) {
		this.pq_seq = pq_seq;
	}
	public String getM_sub_name() {
		return m_sub_name;
	}
	public void setM_sub_name(String m_sub_name) {
		this.m_sub_name = m_sub_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQ_title() {
		return q_title;
	}
	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	
	
}
