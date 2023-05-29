package member.dto;

public class Member {
   private int userNo;
   private String userName;
   private String userId;
   private String userPw;
   private String email;
   private String address;
   private String detailaddress;
   private String zipCode;
   private String phone;
   private String idno;
   private String latitude;
   private String longitude;
   private int positionNo;
   private String suserno;
   private String userNick;
   
  public Member() {}

public Member(int userNo, String userName, String userId, String userPw, String email, String address,
		String detailaddress, String zipCode, String phone, String idno, String latitude, String longitude,
		int positionNo, String suserno, String userNick) {
	super();
	this.userNo = userNo;
	this.userName = userName;
	this.userId = userId;
	this.userPw = userPw;
	this.email = email;
	this.address = address;
	this.detailaddress = detailaddress;
	this.zipCode = zipCode;
	this.phone = phone;
	this.idno = idno;
	this.latitude = latitude;
	this.longitude = longitude;
	this.positionNo = positionNo;
	this.suserno = suserno;
	this.userNick = userNick;
}

@Override
public String toString() {
	return "Member [userNo=" + userNo + ", userName=" + userName + ", userId=" + userId + ", userPw=" + userPw
			+ ", email=" + email + ", address=" + address + ", detailaddress=" + detailaddress + ", zipCode=" + zipCode
			+ ", phone=" + phone + ", idno=" + idno + ", latitude=" + latitude + ", longitude=" + longitude
			+ ", positionNo=" + positionNo + ", suserno=" + suserno + ", userNick=" + userNick + "]";
}

public int getUserNo() {
	return userNo;
}

public void setUserNo(int userNo) {
	this.userNo = userNo;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

public String getUserPw() {
	return userPw;
}

public void setUserPw(String userPw) {
	this.userPw = userPw;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getDetailaddress() {
	return detailaddress;
}

public void setDetailaddress(String detailaddress) {
	this.detailaddress = detailaddress;
}

public String getZipCode() {
	return zipCode;
}

public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getIdno() {
	return idno;
}

public void setIdno(String idno) {
	this.idno = idno;
}

public String getLatitude() {
	return latitude;
}

public void setLatitude(String latitude) {
	this.latitude = latitude;
}

public String getLongitude() {
	return longitude;
}

public void setLongitude(String longitude) {
	this.longitude = longitude;
}

public int getPositionNo() {
	return positionNo;
}

public void setPositionNo(int positionNo) {
	this.positionNo = positionNo;
}

public String getSuserno() {
	return suserno;
}

public void setSuserno(String suserno) {
	this.suserno = suserno;
}

public String getUserNick() {
	return userNick;
}

public void setUserNick(String userNick) {
	this.userNick = userNick;
}

  
  


  
  
}
