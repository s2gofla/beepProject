package beepbeep.community.dto;

public class SubDTO {
	private String m_sub_seq;
	private String m_sub_name;
	
	public SubDTO(String m_sub_seq, String m_sub_name) {
		super();
		this.m_sub_seq = m_sub_seq;
		this.m_sub_name = m_sub_name;
	}
	


	public SubDTO() {
		super();
	}



	public String getM_sub_seq() {
		return m_sub_seq;
	}
	public void setM_sub_seq(String m_sub_seq) {
		this.m_sub_seq = m_sub_seq;
	}
	public String getM_sub_name() {
		return m_sub_name;
	}
	public void setM_sub_name(String m_sub_name) {
		this.m_sub_name = m_sub_name;
	}
	
	

}