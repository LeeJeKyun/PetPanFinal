package board.dto;

public class NoticeFile {
	
	private int fileno;
	private String originName;
	private String storedName;
	private long fileSize;
	private int noticeno;
	@Override
	public String toString() {
		return "NoticeFile [fileno=" + fileno + ", originName=" + originName + ", storedName=" + storedName
				+ ", fileSize=" + fileSize + ", noticeno=" + noticeno + "]";
	}
	
	
	public NoticeFile() {
	}


	public NoticeFile(int fileno, String originName, String storedName, long fileSize, int noticeno) {
		super();
		this.fileno = fileno;
		this.originName = originName;
		this.storedName = storedName;
		this.fileSize = fileSize;
		this.noticeno = noticeno;
	}


	public int getFileno() {
		return fileno;
	}
	public void setFileno(int fileno) {
		this.fileno = fileno;
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
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public int getNoticeno() {
		return noticeno;
	}
	public void setNoticeno(int noticeno) {
		this.noticeno = noticeno;
	}
	
	
	
}

