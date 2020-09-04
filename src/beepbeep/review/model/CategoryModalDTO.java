package beepbeep.review.model;

public class CategoryModalDTO {
	private int m_sub_seq;
	private String m_sub_name;
	private int treatment_code;
	private String treatment_name;
	
	
	
	public int getTreatment_code() {
		return treatment_code;
	}
	public void setTreatment_code(int treatment_code) {
		this.treatment_code = treatment_code;
	}
	public String getTreatment_name() {
		return treatment_name;
	}
	public void setTreatment_name(String treatment_name) {
		this.treatment_name = treatment_name;
	}
	public int getM_sub_seq() {
		return m_sub_seq;
	}
	public void setM_sub_seq(int m_sub_seq) {
		this.m_sub_seq = m_sub_seq;
	}
	public String getM_sub_name() {
		return m_sub_name;
	}
	public void setM_sub_name(String m_sub_name) {
		this.m_sub_name = m_sub_name;
	}
	
	
}
