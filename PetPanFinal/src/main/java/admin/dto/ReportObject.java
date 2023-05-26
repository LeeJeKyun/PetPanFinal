package admin.dto;

import java.util.Date;

public class ReportObject {
	private int objreportNo;
	private int objectNo;
	private String name;
	private String reportdetail;
	private Date reportDate;
	private String complete;
	private int userNo;
	public ReportObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReportObject(int objreportNo, int objectNo, String name, String reportdetail, Date reportDate,
			String complete, int userNo) {
		super();
		this.objreportNo = objreportNo;
		this.objectNo = objectNo;
		this.name = name;
		this.reportdetail = reportdetail;
		this.reportDate = reportDate;
		this.complete = complete;
		this.userNo = userNo;
	}
	@Override
	public String toString() {
		return "ReportObject [objreportNo=" + objreportNo + ", objectNo=" + objectNo + ", name=" + name
				+ ", reportdetail=" + reportdetail + ", reportDate=" + reportDate + ", complete=" + complete
				+ ", userNo=" + userNo + "]";
	}
	public int getObjreportNo() {
		return objreportNo;
	}
	public void setObjreportNo(int objreportNo) {
		this.objreportNo = objreportNo;
	}
	public int getObjectNo() {
		return objectNo;
	}
	public void setObjectNo(int objectNo) {
		this.objectNo = objectNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

}
