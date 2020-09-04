package beepbeep.member.dto;

public class LoginView {
	private LoginDTO dto;
	
	private int errcode;
	
	public LoginDTO getDto() {
		return dto;
	}
	public void setDto(LoginDTO dto) {
		this.dto = dto;
	}

	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	
	
}
