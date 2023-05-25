package admin.dto;

import java.util.Date;

public class ReportBoard {

	private int boreportNo;
	private String reportDetail;
	private Date reportDate;
	private String complete;
	private int userNo;
	private String userId;
	private int boardNo;
	private String boardTitle;
	public ReportBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReportBoard(int boreportNo, String reportDetail, Date reportDate, String complete, int userNo, String userId,
			int boardNo, String boardTitle) {
		super();
		this.boreportNo = boreportNo;
		this.reportDetail = reportDetail;
		this.reportDate = reportDate;
		this.complete = complete;
		this.userNo = userNo;
		this.userId = userId;
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
	}
	@Override
	public String toString() {
		return "ReportBoard [boreportNo=" + boreportNo + ", reportDetail=" + reportDetail + ", reportDate=" + reportDate
				+ ", complete=" + complete + ", userNo=" + userNo + ", userId=" + userId + ", boardNo=" + boardNo
				+ ", boardTitle=" + boardTitle + "]";
	}
	public int getBoreportNo() {
		return boreportNo;
	}
	public void setBoreportNo(int boreportNo) {
		this.boreportNo = boreportNo;
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
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
}
