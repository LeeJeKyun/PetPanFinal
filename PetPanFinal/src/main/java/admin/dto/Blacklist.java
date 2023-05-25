package admin.dto;

import java.util.Date;

public class Blacklist {

	int blackno;         
	Date blackdate;         
	String reason;    
	int userno;
	@Override
	public String toString() {
		return "Blacklist [blackno=" + blackno + ", blackdate=" + blackdate + ", reason=" + reason + ", userno="
				+ userno + "]";
	}
	public Blacklist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Blacklist(int blackno, Date blackdate, String reason, int userno) {
		super();
		this.blackno = blackno;
		this.blackdate = blackdate;
		this.reason = reason;
		this.userno = userno;
	}
	public int getBlackno() {
		return blackno;
	}
	public void setBlackno(int blackno) {
		this.blackno = blackno;
	}
	public Date getBlackdate() {
		return blackdate;
	}
	public void setBlackdate(Date blackdate) {
		this.blackdate = blackdate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	
	
	
	
}
