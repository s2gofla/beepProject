package beepbeep.review.model;

import java.sql.Date;
import java.util.ArrayList;

public class MreviewDTO {

	
	private int m_code;
	private int m_review_code;
	private String id;
	private String nickname;
	private int m_price;
	private int score_effect;
	private int score_comfort;
	private int score_price;
	private String contents;
	private Date dates;
	private int likes;
	private double star_score;
	private String ajaxDates;
	private int userlike;
	private ArrayList<MreviewPicDTO> picture;
	
	
	
	public String getAjaxDates() {
		return ajaxDates;
	}

	public void setAjaxDates(String ajaxDates) {
		this.ajaxDates = ajaxDates;
	}

	public int getUserlike() {
		return userlike;
	}

	public void setUserlike(int userlike) {
		this.userlike = userlike;
	}

	
	
	public double getStar_score() {
		return star_score;
	}

	public void setStar_score(double star_score) {
		this.star_score = star_score;
	}

	public Date getDates() {
		return dates;
	}

	public void setDates(Date dates) {
		this.dates = dates;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}


	public int getM_code() {
		return m_code;
	}

	public void setM_code(int m_code) {
		this.m_code = m_code;
	}

	public int getM_review_code() {
		return m_review_code;
	}

	public void setM_review_code(int m_review_code) {
		this.m_review_code = m_review_code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getM_price() {
		return m_price;
	}

	public void setM_price(int m_price) {
		this.m_price = m_price;
	}

	public int getScore_effect() {
		return score_effect;
	}

	public void setScore_effect(int score_effect) {
		this.score_effect = score_effect;
	}

	public int getScore_comfort() {
		return score_comfort;
	}

	public void setScore_comfort(int score_comfort) {
		this.score_comfort = score_comfort;
	}

	public int getScore_price() {
		return score_price;
	}

	public void setScore_price(int score_price) {
		this.score_price = score_price;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public ArrayList<MreviewPicDTO> getPicture() {
		return picture;
	}

	public void setPicture(ArrayList<MreviewPicDTO> picture) {
		this.picture = picture;
	}
	
	
	
}
