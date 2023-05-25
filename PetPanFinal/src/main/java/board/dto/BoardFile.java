package board.dto;

public class BoardFile {
	
	private int fileno;
	private String originName;
	private String storedName;
	private long fileSize;
	private int boardno;
	
	public BoardFile() {}

	public BoardFile(int fileno, String originName, String storedName, long fileSize, int boardno) {
		this.fileno = fileno;
		this.originName = originName;
		this.storedName = storedName;
		this.fileSize = fileSize;
		this.boardno = boardno;
	}

	@Override
	public String toString() {
		return "BoardFile [fileno=" + fileno + ", originName=" + originName + ", storedName=" + storedName
				+ ", fileSize=" + fileSize + ", boardno=" + boardno + "]";
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

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	
	
	
}
