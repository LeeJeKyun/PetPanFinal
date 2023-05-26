package admin.dto;

import java.util.Date;

public class ReportComment {
	
	private int coreportNo;
	private String reportDetail;
	private Date reportDate;
	private String complete;
	private int userNo;
	private String userId;
	private int boardNo;
	private int commentNo;
	private String content;
	
	public ReportComment() {}

	public ReportComment(int coreportNo, String reportDetail, Date reportDate, String complete, int userNo,
			String userId, int boardNo, int commentNo, String content) {
		super();
		this.coreportNo = coreportNo;
		this.reportDetail = reportDetail;
		this.reportDate = reportDate;
		this.complete = complete;
		this.userNo = userNo;
		this.userId = userId;
		this.boardNo = boardNo;
		this.commentNo = commentNo;
		this.content = content;
	}

	@Override
	public String toString() {
		return "ReportCommet [coreportNo=" + coreportNo + ", reportDetail=" + reportDetail + ", reportDate="
				+ reportDate + ", complete=" + complete + ", userNo=" + userNo + ", userId=" + userId + ", boardNo="
				+ boardNo + ", commentNo=" + commentNo + ", content=" + content + "]";
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

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	

}
