package beepbeep.information.dto;

public class DiseaseListDTO {

	private int seq;
	private String d_name;
	private String definition;
	private String m_sub_name;
	private String symptom;
	
	
	 
	public String getSymptom() {
		return symptom;
	}
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
	public String getM_sub_name() {
		return m_sub_name;
	}
	public void setM_sub_name(String m_sub_name) {
		this.m_sub_name = m_sub_name;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
}
