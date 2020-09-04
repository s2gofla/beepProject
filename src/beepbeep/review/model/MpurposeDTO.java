package beepbeep.review.model;

public class MpurposeDTO {

	private int purpose_code;
	private int bpurpose_code;
	private String purpose_name;
	private String bpurpose_name;
	 
	
	public String getBpurpose_name() {
		return bpurpose_name;
	}
	public void setBpurpose_name(String bpurpose_name) {
		this.bpurpose_name = bpurpose_name;
	}
	public int getPurpose_code() {
		return purpose_code;
	}
	public void setPurpose_code(int purpose_code) {
		this.purpose_code = purpose_code;
	}
	public int getBpurpose_code() {
		return bpurpose_code;
	}
	public void setBpurpose_code(int bpurpose_code) {
		this.bpurpose_code = bpurpose_code;
	}
	public String getPurpose_name() {
		return purpose_name;
	}
	public void setPurpose_name(String purpose_name) {
		this.purpose_name = purpose_name;
	}
	
	
	
}
