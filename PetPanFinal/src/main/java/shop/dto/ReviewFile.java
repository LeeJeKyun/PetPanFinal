package shop.dto;

public class ReviewFile {

	private int fileno;
	private String originname;
	private String storedname;
	private long filesize;
	private int reviewno;
	
	public ReviewFile() {
		// TODO Auto-generated constructor stub
	}

	public ReviewFile(int fileno, String originname, String storedname, long filesize, int reviewno) {
		super();
		this.fileno = fileno;
		this.originname = originname;
		this.storedname = storedname;
		this.filesize = filesize;
		this.reviewno = reviewno;
	}

	@Override
	public String toString() {
		return "ReviewFile [fileno=" + fileno + ", originname=" + originname + ", storedname=" + storedname
				+ ", filesize=" + filesize + ", reviewno=" + reviewno + "]";
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

	public int getReviewno() {
		return reviewno;
	}

	public void setReviewno(int reviewno) {
		this.reviewno = reviewno;
	}

	
		
}
