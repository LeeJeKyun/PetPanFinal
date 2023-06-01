package board.dto;

public class HospitalFile {
	private int fileNo;
	private String originName;
	private String storedName;
	private int fileSize;
	private int hospitalNo;
	
	public HospitalFile() {}

	@Override
	public String toString() {
		return "HospitalFile [fileNo=" + fileNo + ", originName=" + originName + ", storedName=" + storedName
				+ ", fileSize=" + fileSize + ", hospitalNo=" + hospitalNo + "]";
	}

	public HospitalFile(int fileNo, String originName, String storedName, int fileSize, int hospitalNo) {
		super();
		this.fileNo = fileNo;
		this.originName = originName;
		this.storedName = storedName;
		this.fileSize = fileSize;
		this.hospitalNo = hospitalNo;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getStoredName() {
		return storedName;
	}

	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public int getHospitalNo() {
		return hospitalNo;
	}

	public void setHospitalNo(int hospitalNo) {
		this.hospitalNo = hospitalNo;
	}
	
	
}
