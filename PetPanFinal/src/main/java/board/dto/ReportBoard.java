package board.dto;

import java.util.Date;

public class ReportBoard {

	private int boardReportNo;
	private String reportDetail;
	private Date reportDate;
	private char complete;
	private int userNo;
	private int boardNo;
	
	public ReportBoard() {}

	@Override
	public String toString() {
		return "ReportBoard [boardReportNo=" + boardReportNo + ", reportDetail=" + reportDetail + ", reportDate="
				+ reportDate + ", complete=" + complete + ", userNo=" + userNo + ", boardNo=" + boardNo + "]";
	}

	public ReportBoard(int boardReportNo, String reportDetail, Date reportDate, char complete, int userNo,
			int boardNo) {
		super();
		this.boardReportNo = boardReportNo;
		this.reportDetail = reportDetail;
		this.reportDate = reportDate;
		this.complete = complete;
		this.userNo = userNo;
		this.boardNo = boardNo;
	}

	public int getBoardReportNo() {
		return boardReportNo;
	}

	public void setBoardReportNo(int boardReportNo) {
		this.boardReportNo = boardReportNo;
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

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
}
