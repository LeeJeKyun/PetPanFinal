package member.dto;

public class Pet {

	private int petNo;
	private String petName;
	private String type;
	private int userNo;
	
	public Pet() {}

	public Pet(int petNo, String petName, String type, int userNo) {
		super();
		this.petNo = petNo;
		this.petName = petName;
		this.type = type;
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return "Pet [petNo=" + petNo + ", petName=" + petName + ", type=" + type + ", userNo=" + userNo + "]";
	}

	public int getPetNo() {
		return petNo;
	}

	public void setPetNo(int petNo) {
		this.petNo = petNo;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	
	
	
}
