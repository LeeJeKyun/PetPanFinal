package shop.dto;

public class ShopFile {

	private int fileno;
	private String originname;
	private String storedname;
	private long filesize;
	private int objectno;
	
	public ShopFile() {
		// TODO Auto-generated constructor stub
	}

	public ShopFile(int fileno, String originname, String storedname, long filesize, int objectno) {
		super();
		this.fileno = fileno;
		this.originname = originname;
		this.storedname = storedname;
		this.filesize = filesize;
		this.objectno = objectno;
	}

	@Override
	public String toString() {
		return "ShopFile [fileno=" + fileno + ", originname=" + originname + ", storedname=" + storedname
				+ ", filesize=" + filesize + ", objectno=" + objectno + "]";
	}

	public int getFileno() {
		return fileno;
	}

	public void setFileno(int fileno) {
		this.fileno = fileno;
	}

	public String getOriginname() {
		return originname;
	}

	public void setOriginname(String originname) {
		this.originname = originname;
	}

	public String getStoredname() {
		return storedname;
	}

	public void setStoredname(String storedname) {
		this.storedname = storedname;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public int getObjectno() {
		return objectno;
	}

	public void setObjectno(int objectno) {
		this.objectno = objectno;
	}
	
}
