package beepbeep.member.dto;

import java.sql.Date;

public class MemberDTO {

	
	private String photo;
	private String name;
	private Date birth;
	private String sex;
	private String phone;
	private String email;
	private String id;
	private String password;


	private String nickname;
	private int grade;
	

	private int mcode; 
	private int gcode; 
	private int ctype;
	
	private String licenseNo; 
	private String licenseFile;
	
	private String gradeName;

	
	public MemberDTO() { }
	// 일반회원 DTO
	public MemberDTO(String photo, String name, Date birth, String sex, String phone, String email, String id, String password,
			String nickname, int mcode, int gcode, int ctype) {
		this.photo = photo;
		this.name = name;
		this.birth = birth;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.id = id;
		this.password = password;
		this.nickname = nickname;

		this.mcode = mcode;
		this.gcode = gcode;
		this.ctype = ctype;
	}
	
	// 의사 회원 DTO
	public MemberDTO(String photo, String name, Date birth, String sex, String phone, String email, String id, String password,
			String nickname,  int mcode, int gcode, int ctype, String licenseNo, String licenseFile) {
		this.photo = photo;
		this.name = name;
		this.birth = birth;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.id = id;
		this.password = password;
		this.nickname = nickname;

		this.mcode = mcode;
		this.gcode = gcode;
		this.ctype = ctype;
		this.licenseNo = licenseNo;
		this.licenseFile = licenseFile;
	}

	
	
	public String getGradeName() {
		return gradeName;
	}
	public String setGradeName(String gradeName) {
		return this.gradeName = gradeName;
	}

	
	
	
	
	public int getGrade() {
		return grade;
	}

	public int setGrade(int grade) {
		return this.grade = grade;
	}
	
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getLicenseFile() {
		return licenseFile;
	}
	public void setLicenseFile(String licenseFile) {
		this.licenseFile = licenseFile;
	}

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getMcode() {
		return mcode;
	}
	public void setMcode(int mcode) {
		this.mcode = mcode;
	}
	public int getGcode() {
		return gcode;
	}
	public void setGcode(int gcode) {
		this.gcode = gcode;
	}
	public int getCtype() {
		return ctype;
	}
	public void setCtype(int ctype) {
		this.ctype = ctype;
	} 
	

	
	
}
