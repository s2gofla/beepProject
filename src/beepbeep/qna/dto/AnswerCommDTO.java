package beepbeep.qna.dto;

public class AnswerCommDTO {
	private int psa_seq;
	private int ps_seq;
	private String id;
	private String dates;
	private String contents;
	private String nickname;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getPsa_seq() {
		return psa_seq;
	}
	public void setPsa_seq(int psa_seq) {
		this.psa_seq = psa_seq;
	}
	public int getPs_seq() {
		return ps_seq;
	}
	public void setPs_seq(int ps_seq) {
		this.ps_seq = ps_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
}
