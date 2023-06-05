package shop.dto;

public class Review {

	private int reviewno;
	private String reviewtitle;
	private String reviewcontent;
	private int reviewstar;
	private String image1;
	private int objectno;
	private int userno;
	private int buyno;
	
	public Review() {
		// TODO Auto-generated constructor stub
	}

	public Review(int reviewno, String reviewtitle, String reviewcontent, int reviewstar, String image1, int objectno,
			int userno, int buyno) {
		super();
		this.reviewno = reviewno;
		this.reviewtitle = reviewtitle;
		this.reviewcontent = reviewcontent;
		this.reviewstar = reviewstar;
		this.image1 = image1;
		this.objectno = objectno;
		this.userno = userno;
		this.buyno = buyno;
	}

	@Override
	public String toString() {
		return "Review [reviewno=" + reviewno + ", reviewtitle=" + reviewtitle + ", reviewcontent=" + reviewcontent
				+ ", reviewstar=" + reviewstar + ", image1=" + image1 + ", objectno=" + objectno + ", userno=" + userno
				+ ", buyno=" + buyno + "]";
	}

	public int getReviewno() {
		return reviewno;
	}

	public void setReviewno(int reviewno) {
		this.reviewno = reviewno;
	}

	public String getReviewtitle() {
		return reviewtitle;
	}

	public void setReviewtitle(String reviewtitle) {
		this.reviewtitle = reviewtitle;
	}

	public String getReviewcontent() {
		return reviewcontent;
	}

	public void setReviewcontent(String reviewcontent) {
		this.reviewcontent = reviewcontent;
	}

	public int getReviewstar() {
		return reviewstar;
	}

	public void setReviewstar(int reviewstar) {
		this.reviewstar = reviewstar;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public int getObjectno() {
		return objectno;
	}

	public void setObjectno(int objectno) {
		this.objectno = objectno;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public int getBuyno() {
		return buyno;
	}

	public void setBuyno(int buyno) {
		this.buyno = buyno;
	}
	
	
}
