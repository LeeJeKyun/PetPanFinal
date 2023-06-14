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
	private String userName;
	private int deleteobj;
	
	public ReportObject() {}

	public ReportObject(int objreportNo, int objectNo, String name, String reportdetail, Date reportDate,
			String complete, int userNo, String userName, int deleteobj) {
		super();
		this.objreportNo = objreportNo;
		this.objectNo = objectNo;
		this.name = name;
		this.reportdetail = reportdetail;
		this.reportDate = reportDate;
		this.complete = complete;
		this.userNo = userNo;
		this.userName = userName;
		this.deleteobj = deleteobj;
	}

	@Override
	public String toString() {
		return "ReportObject [objreportNo=" + objreportNo + ", objectNo=" + objectNo + ", name=" + name
				+ ", reportdetail=" + reportdetail + ", reportDate=" + reportDate + ", complete=" + complete
				+ ", userNo=" + userNo + ", userName=" + userName + ", deleteobj=" + deleteobj + "]";
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getDeleteobj() {
		return deleteobj;
	}

	public void setDeleteobj(int deleteobj) {
		this.deleteobj = deleteobj;
	}
	
}
