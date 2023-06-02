package board.dto;

public class Hospital {

	private int hospitalNo;
	private String hospitalName;
	private String hospitalCode;
	private int userNo;
	private char mammalia;
	private char reptile;
	private char rodent;
	private char birds;
	private String open;
	private String close;
	
	public Hospital() {}

	@Override
	public String toString() {
		return "Hospital [hospitalNo=" + hospitalNo + ", hospitalName=" + hospitalName + ", hospitalCode="
				+ hospitalCode + ", userNo=" + userNo + ", mammalia=" + mammalia + ", reptile=" + reptile + ", rodent="
				+ rodent + ", birds=" + birds + ", open=" + open + ", close=" + close + "]";
	}

	public Hospital(int hospitalNo, String hospitalName, String hospitalCode, int userNo, char mammalia, char reptile,
			char rodent, char birds, String open, String close) {
		super();
		this.hospitalNo = hospitalNo;
		this.hospitalName = hospitalName;
		this.hospitalCode = hospitalCode;
		this.userNo = userNo;
		this.mammalia = mammalia;
		this.reptile = reptile;
		this.rodent = rodent;
		this.birds = birds;
		this.open = open;
		this.close = close;
	}

	public int getHospitalNo() {
		return hospitalNo;
	}

	public void setHospitalNo(int hospitalNo) {
		this.hospitalNo = hospitalNo;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public char getMammalia() {
		return mammalia;
	}

	public void setMammalia(char mammalia) {
		this.mammalia = mammalia;
	}

	public char getReptile() {
		return reptile;
	}

	public void setReptile(char reptile) {
		this.reptile = reptile;
	}

	public char getRodent() {
		return rodent;
	}

	public void setRodent(char rodent) {
		this.rodent = rodent;
	}

	public char getBirds() {
		return birds;
	}

	public void setBirds(char birds) {
		this.birds = birds;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}
	
}
