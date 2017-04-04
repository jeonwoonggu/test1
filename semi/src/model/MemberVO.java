package model;

public class MemberVO {
	private String member_Id;
	private String password;
	private String name;
	private String residentNumber;
	private String gender;
	private String nickName;
	private String tel;
	private String address;
	public MemberVO() {
		super();
	}
	
	public MemberVO(String member_Id, String password, String name, String nickName) {
		super();
		this.member_Id = member_Id;
		this.password = password;
		this.name = name;
		this.nickName = nickName;
	}

	public MemberVO(String member_Id, String password, String name, String residentNumber, String gender,
			String nickName, String tel, String address) {
		super();
		this.member_Id = member_Id;
		this.password = password;
		this.name = name;
		this.residentNumber = residentNumber;
		this.gender = gender;
		this.nickName = nickName;
		this.tel = tel;
		this.address = address;
	}
	public String getMember_Id() {
		return member_Id;
	}
	public void setMember_Id(String member_Id) {
		this.member_Id = member_Id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResidentNumber() {
		return residentNumber;
	}
	public void setResidentNumber(String residentNumber) {
		this.residentNumber = residentNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "MemberVO [member_Id=" + member_Id + ", password=" + password + ", name=" + name + ", residentNumber="
				+ residentNumber + ", gender=" + gender + ", nickName=" + nickName + ", tel=" + tel + ", address="
				+ address + "]";
	}
	
}
