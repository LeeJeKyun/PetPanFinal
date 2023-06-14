package member.dto;

public class LoginFail {
	private int failNo;
	private int failCount;
	private int userno;
	public LoginFail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginFail(int failNo, int failCount, int userno) {
		super();
		this.failNo = failNo;
		this.failCount = failCount;
		this.userno = userno;
	}
	@Override
	public String toString() {
		return "LoginFail [failNo=" + failNo + ", failCount=" + failCount + ", userno=" + userno + "]";
	}
	public int getFailNo() {
		return failNo;
	}
	public void setFailNo(int failNo) {
		this.failNo = failNo;
	}
	public int getFailCount() {
		return failCount;
	}
	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	
	

}
