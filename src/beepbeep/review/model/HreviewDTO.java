package beepbeep.review.model;
import java.sql.Date;
import java.util.ArrayList;

public class HreviewDTO {
	
	private int h_code;
	private int h_review_code;
	private String id;
	private String nickname;
	private int score_kind;
	private int score_price;
	private int score_result;
	private int score_satisfaction;
	private double star_score;
	private int review_type;
	//ajax용
	private String ajaxDates;
	
	private Date dates;
	private int likes;
	private String contents;
	private int userlike;
	
	
	private ArrayList<HreivewPicDTO> picture;
	private ArrayList<HreviewTreatDTO> treatment;
	private String certification;
	
	
	
	
	public int getUserlike() {
		return userlike;
	}

	public void setUserlike(int userlikes) {
		this.userlike = userlikes;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getAjaxDates() {
		return ajaxDates;
	}

	public void setAjaxDates(String ajaxDates) {
		this.ajaxDates = ajaxDates;
	}

	public int getH_review_code() {
		return h_review_code;
	}

	public void setH_review_code(int h_review_code) {
		this.h_review_code = h_review_code;
	}

	public double getStar_score() {
		return star_score;
	}

	public void setStar_score(double star_score) {
		this.star_score = star_score;
	}
	
	public int getH_code() {
		return h_code;
	}
	
	public void setH_code(int h_code) {
		this.h_code = h_code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getScore_kind() {
		return score_kind;
	}
	public void setScore_kind(int score_kind) {
		this.score_kind = score_kind;
	}
	public int getScore_price() {
		return score_price;
	}
	public void setScore_price(int score_price) {
		this.score_price = score_price;
	}
	public int getScore_result() {
		return score_result;
	}
	public void setScore_result(int score_result) {
		this.score_result = score_result;
	}
	public int getScore_satisfaction() {
		return score_satisfaction;
	}
	public void setScore_satisfaction(int score_satisfaction) {
		this.score_satisfaction = score_satisfaction;
	}
	public int getReview_type() {
		return review_type;
	}
	public void setReview_type(int review_type) {
		this.review_type = review_type;
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public ArrayList<HreivewPicDTO> getPicture() {
		return picture;
	}
	public void setPicture(ArrayList<HreivewPicDTO> picture) {
		this.picture = picture;
	}
	public ArrayList<HreviewTreatDTO> getTreatment() {
		return treatment;
	}
	public void setTreatment(ArrayList<HreviewTreatDTO> treatment) {
		this.treatment = treatment;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
}
