package beepbeep.review.model;

public class HreviewTreatDTO {
	
	private int rtreatment_seq;	
	private int treatment_code;
	private String treatment_name;
	private String m_sub_name;
	private int price;
	
	
	
	
	public int getTreatment_code() {
		return treatment_code;
	}
	public void setTreatment_code(int treatment_code) {
		this.treatment_code = treatment_code;
	}
	public int getRtreatment_seq() {
		return rtreatment_seq;
	}
	public void setRtreatment_seq(int rtreatment_seq) {
		this.rtreatment_seq = rtreatment_seq;
	}
	public String getTreatment_name() {
		return treatment_name;
	}
	public void setTreatment_name(String treatment_name) {
		this.treatment_name = treatment_name;
	}
	public String getM_sub_name() {
		return m_sub_name;
	}
	public void setM_sub_name(String m_sub_name) {
		this.m_sub_name = m_sub_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
