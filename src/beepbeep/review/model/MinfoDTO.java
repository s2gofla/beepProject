package beepbeep.review.model;

public class MinfoDTO {

	private int m_code;
	private String m_name;
	private String m_enterprise;
	private int m_price;
	private String m_ingredient;
	private String m_effect;
	private String m_dose;
	private String m_pic;
	private String m_sideeffect;
	
	private MpurposeDTO mpurpose;
	private int reviewer;
	private double star_score;
	private int bookmark_count;
	private int isBookMark;
	
	
	

	public int getIsBookMark() {
		return isBookMark;
	}
	public void setIsBookMark(int isBookMark) {
		this.isBookMark = isBookMark;
	}
	public MpurposeDTO getMpurpose() {
		return mpurpose;
	}
	public void setMpurpose(MpurposeDTO mpurpose) {
		this.mpurpose = mpurpose;
	}
	public int getReviewer() {
		return reviewer;
	}
	public void setReviewer(int reviewer) {
		this.reviewer = reviewer;
	}
	public double getStar_score() {
		return star_score;
	}
	public void setStar_score(double star_score) {
		this.star_score = star_score;
	}
	public int getBookmark_count() {
		return bookmark_count;
	}
	public void setBookmark_count(int bookmark_count) {
		this.bookmark_count = bookmark_count;
	}
	public int getM_code() {
		return m_code;
	}
	public void setM_code(int m_code) {
		this.m_code = m_code;
	}

	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_enterprise() {
		return m_enterprise;
	}
	public void setM_enterprise(String m_enterprise) {
		this.m_enterprise = m_enterprise;
	}
	public int getM_price() {
		return m_price;
	}
	public void setM_price(int m_price) {
		this.m_price = m_price;
	}
	public String getM_ingredient() {
		return m_ingredient;
	}
	public void setM_ingredient(String m_ingredient) {
		this.m_ingredient = m_ingredient;
	}
	public String getM_effect() {
		return m_effect;
	}
	public void setM_effect(String m_effect) {
		this.m_effect = m_effect;
	}
	public String getM_dose() {
		return m_dose;
	}
	public void setM_dose(String m_dose) {
		this.m_dose = m_dose;
	}
	public String getM_pic() {
		return m_pic;
	}
	public void setM_pic(String m_pic) {
		this.m_pic = m_pic;
	}
	public String getM_sideeffect() {
		return m_sideeffect;
	}
	public void setM_sideeffect(String m_sideeffect) {
		this.m_sideeffect = m_sideeffect;
	}
	
	
	
}
