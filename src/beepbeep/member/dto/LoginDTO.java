package beepbeep.member.dto;

public class LoginDTO {

	private String id;
	private String nickname;
	private String password;
	private String phone;
	private String grade;
	private String photo;
	private int mgrade_code;
	private String checkbox;
	
	public LoginDTO(String id, String nickname, String password, String phone, String grade , String photo, int mgrade_code, String checkbox) {
		this.password = password;
		this.id=id;
		this.nickname =nickname;
		this.phone = phone;
		this.grade = grade;
		this.photo=photo;
		this.mgrade_code = mgrade_code;
		this.checkbox = checkbox;
	}

	public LoginDTO() {	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	


	
	
	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	


	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getMgrade_code() {
		return mgrade_code;
	}

	public void setMgrade_code(int mgrade_code) {
		this.mgrade_code = mgrade_code;
	}

	public String getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}

	
	
	
	public  boolean matchPassword(String pwd) {
		return password.equals(pwd);
	}


	
}
