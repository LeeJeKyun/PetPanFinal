package board.dto;

import java.util.Date;

public class Notice {

	private int noticeno;
	private String noticetitle;
	private String noticecontent;
	private Date noticewritedate;
	private int userno;
	private int boardtypeno;
	
	public Notice() {}

	public Notice(int noticeno, String noticetitle, String noticecontent, Date noticewritedate, int userno,
			int boardtypeno) {
		super();
		this.noticeno = noticeno;
		this.noticetitle = noticetitle;
		this.noticecontent = noticecontent;
		this.noticewritedate = noticewritedate;
		this.userno = userno;
		this.boardtypeno = boardtypeno;
	}

	@Override
	public String toString() {
		return "Notice [noticeno=" + noticeno + ", noticetitle=" + noticetitle + ", noticecontent=" + noticecontent
				+ ", noticewritedate=" + noticewritedate + ", userno=" + userno + ", boardtypeno=" + boardtypeno + "]";
	}

	public int getNoticeno() {
		return noticeno;
	}

	public void setNoticeno(int noticeno) {
		this.noticeno = noticeno;
	}

	public String getNoticetitle() {
		return noticetitle;
	}

	public void setNoticetitle(String noticetitle) {
		this.noticetitle = noticetitle;
	}

	public String getNoticecontent() {
		return noticecontent;
	}

	public void setNoticecontent(String noticecontent) {
		this.noticecontent = noticecontent;
	}

	public Date getNoticewritedate() {
		return noticewritedate;
	}

	public void setNoticewritedate(Date noticewritedate) {
		this.noticewritedate = noticewritedate;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public int getBoardtypeno() {
		return boardtypeno;
	}

	public void setBoardtypeno(int boardtypeno) {
		this.boardtypeno = boardtypeno;
	}
	
	
	
}
