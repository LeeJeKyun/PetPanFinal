package board.dto;

import java.util.Date;

public class ReportComment {

	private int coreportNo;
	private String reportdetail;
	private Date reportDate;
	private char complete;
	private int userNo;
	private int commentNo;
	
	public ReportComment() {}

	@Override
	public String toString() {
		return "ReportComment [coreportNo=" + coreportNo + ", reportdetail=" + reportdetail + ", reportDate="
				+ reportDate + ", complete=" + complete + ", userNo=" + userNo + ", commentNo=" + commentNo + "]";
	}

	public ReportComment(int coreportNo, String reportdetail, Date reportDate, char complete, int userNo,
			int commentNo) {
		super();
		this.coreportNo = coreportNo;
		this.reportdetail = reportdetail;
		this.reportDate = reportDate;
		this.complete = complete;
		this.userNo = userNo;
		this.commentNo = commentNo;
	}

	public int getCoreportNo() {
		return coreportNo;
	}

	public void setCoreportNo(int coreportNo) {
		this.coreportNo = coreportNo;
	}

	public String getReportdetail() {
		return reportdetail;
	}

	public void setReportdetail(String reportdetail) {
		this.reportdetail = reportdetail;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public char getComplete() {
		return complete;
	}

	public void setComplete(char complete) {
		this.complete = complete;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
}
