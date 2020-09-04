package beepbeep.member.dto;

public class ChangeInfoDTO {
	
	private String pwd;
	private String nickname;
	private String phone;
	private String photo;
	


	public ChangeInfoDTO() {}


	public ChangeInfoDTO(String newPwd1, String newNick, String newPhone, String newPhoto) {

		this.pwd = newPwd1;
		this.nickname = newNick;
		this.phone = newPhone;
		this.photo = newPhoto;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	
}
