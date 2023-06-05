package board.dto;

import java.util.Date;

public class ReportComment {

	private int coreportNo;
	private String reportDetail;
	private Date reportDate;
	private char complete;
	private int userNo;
	private int commentNo;
	
	public ReportComment() {}

	public ReportComment(int coreportNo, String reportDetail, Date reportDate, char complete, int userNo,
			int commentNo) {
		this.coreportNo = coreportNo;
		this.reportDetail = reportDetail;
		this.reportDate = reportDate;
		this.complete = complete;
		this.userNo = userNo;
		this.commentNo = commentNo;
	}

	@Override
	public String toString() {
		return "ReportComment [coreportNo=" + coreportNo + ", reportDetail=" + reportDetail + ", reportDate="
				+ reportDate + ", complete=" + complete + ", userNo=" + userNo + ", commentNo=" + commentNo + "]";
	}

	public int getCoreportNo() {
		return coreportNo;
	}

	public void setCoreportNo(int coreportNo) {
		this.coreportNo = coreportNo;
	}

	public String getReportDetail() {
		return reportDetail;
	}

	public void setReportDetail(String reportDetail) {
		this.reportDetail = reportDetail;
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
