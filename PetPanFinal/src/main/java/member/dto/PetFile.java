package member.dto;

public class PetFile {
	
	private int fileNo;
	private String originName;
	private String storedName;
	private int fileSize;
	private int petNo;
	
	public PetFile() {}

	public PetFile(int fileNo, String originName, String storedName, int fileSize, int petNo) {
		super();
		this.fileNo = fileNo;
		this.originName = originName;
		this.storedName = storedName;
		this.fileSize = fileSize;
		this.petNo = petNo;
	}

	@Override
	public String toString() {
		return "PetFile [fileNo=" + fileNo + ", originName=" + originName + ", storedName=" + storedName + ", fileSize="
				+ fileSize + ", petNo=" + petNo + "]";
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

	public int getPetNo() {
		return petNo;
	}

	public void setPetNo(int petNo) {
		this.petNo = petNo;
	}
	
	
}
