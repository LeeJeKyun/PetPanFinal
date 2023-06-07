package shop.dto;

import java.util.Date;

public class ReportObject {

	private int reportno;
	private int objectno;
	private int userno;
	private String reportdetail;
	private String complete;
	private Date reportdate;
	
	public ReportObject() {
		// TODO Auto-generated constructor stub
	}

	public ReportObject(int reportno, int objectno, int userno, String reportdetail, String complete, Date reportdate) {
		super();
		this.reportno = reportno;
		this.objectno = objectno;
		this.userno = userno;
		this.reportdetail = reportdetail;
		this.complete = complete;
		this.reportdate = reportdate;
	}

	@Override
	public String toString() {
		return "ReportObject [reportno=" + reportno + ", objectno=" + objectno + ", userno=" + userno
				+ ", reportdetail=" + reportdetail + ", complete=" + complete + ", reportdate=" + reportdate + "]";
	}

	public int getReportno() {
		return reportno;
	}

	public void setReportno(int reportno) {
		this.reportno = reportno;
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

	public String getReportdetail() {
		return reportdetail;
	}

	public void setReportdetail(String reportdetail) {
		this.reportdetail = reportdetail;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public Date getReportdate() {
		return reportdate;
	}

	public void setReportdate(Date reportdate) {
		this.reportdate = reportdate;
	}
	
	
}
