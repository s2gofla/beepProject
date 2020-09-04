package beepbeep.admin.model;

import java.io.Serializable;
import java.util.Date;

public class Admin_ConfirmDTO implements Serializable{
	private int c_seq;
	private int h_code;
	private String id;
	private String h_name;
	private int license_number;
	private String license_pic;
	private int certification;
	
	private int seq;
	private String certification_attach;
	private int approval;
	
	private int h_review_code;
	private String title;
	private int score_kind;
	private int score_price;
	private int score_result;
	private int score_Satisfaction;
	private int review_type;
	private Date dates;
	private int likes;
	private String contents;
	
	
	
	
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getCertification_attach() {
		return certification_attach;
	}
	public void setCertification_attach(String certification_attach) {
		this.certification_attach = certification_attach;
	}
	public int getApproval() {
		return approval;
	}
	public void setApproval(int approval) {
		this.approval = approval;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getC_seq() {
		return c_seq;
	}
	public void setC_seq(int c_seq) {
		this.c_seq = c_seq;
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
	public String getH_name() {
		return h_name;
	}
	public void setH_name(String h_name) {
		this.h_name = h_name;
	}
	public int getLicense_number() {
		return license_number;
	}
	public void setLicense_number(int license_number) {
		this.license_number = license_number;
	}
	public String getLicense_pic() {
		return license_pic;
	}
	public void setLicense_pic(String license_pic) {
		this.license_pic = license_pic;
	}
	public int getCertification() {
		return certification;
	}
	public void setCertification(int certification) {
		this.certification = certification;
	}
	
	
	
	public int getScore_price() {
		return score_price;
	}
	public void setScore_price(int score_price) {
		this.score_price = score_price;
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
	
	public void setH_review_code(int h_review_code) {
		this.h_review_code = h_review_code;
	}
	public int getH_review_code() {
		return h_review_code;
	}
	public int getScore_kind() {
		return score_kind;
	}
	public void setScore_kind(int score_kind) {
		this.score_kind = score_kind;
	}
	public int getScore_result() {
		return score_result;
	}
	public void setScore_result(int score_result) {
		this.score_result = score_result;
	}
	public int getScore_Satisfaction() {
		return score_Satisfaction;
	}
	public void setScore_Satisfaction(int score_Satisfaction) {
		this.score_Satisfaction = score_Satisfaction;
	}
	public int getReview_type() {
		return review_type;
	}
	public void setReview_type(int review_type) {
		this.review_type = review_type;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	

	public Admin_ConfirmDTO(int c_seq, int h_code, String id, String h_name, int license_number, String license_pic,
			int certification,    int score_price,Date dates, int likes, String contents, int h_review_code, int score_kind, int score_result,String title,
			int score_Satisfaction, int review_type,int seq, String certification_attach, int approval) {
		super();
		this.c_seq = c_seq;
		this.h_code = h_code;
		this.id = id;
		this.h_name = h_name;
		this.license_number = license_number;
		this.license_pic = license_pic;
		this.certification = certification;
		this.score_price = score_price;
		this.dates = dates;
		this.likes = likes;
		this.contents = contents;
		this.h_review_code = h_review_code;
		this.score_kind = score_kind;
		this.score_result = score_result;
		this.score_Satisfaction = score_Satisfaction;
		this.review_type = review_type;
		this.title=title;
		this.seq =seq;
		this.approval =approval;
		this.certification_attach = certification_attach;
	}
	public Admin_ConfirmDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}