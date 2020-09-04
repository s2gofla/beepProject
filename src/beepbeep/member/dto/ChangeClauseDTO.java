package beepbeep.member.dto;

public class ChangeClauseDTO {
	private String tc1;
	private String tc2;
	
	
	public ChangeClauseDTO() {}


	public ChangeClauseDTO(String tc1, String tc2) {
		super();
		this.tc1 = tc1;
		this.tc2 = tc2;
	}


	public String getTc1() {
		return tc1;
	}

	public void setTc1(String tc1) {
		this.tc1 = tc1;
	}

	public String getTc2() {
		return tc2;
	}

	public void setTc2(String tc2) {
		this.tc2 = tc2;
	}
		
}
