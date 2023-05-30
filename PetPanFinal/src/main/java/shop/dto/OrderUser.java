package shop.dto;

import java.util.Date;

public class OrderUser {

	private int buyno;			
	private String buyername;	
	private String buyeradd;	
	private String buydetailaddress;	
	private String buyerzip;	
	private String buyerphone;	
	private String complete;	
	private int userno;			
	private Date buydate;		
	
	public OrderUser() {
		// TODO Auto-generated constructor stub
	}

	public OrderUser(int buyno, String buyername, String buyeradd, String buydetailaddress, String buyerzip,
			String buyerphone, String complete, int userno, Date buydate) {
		super();
		this.buyno = buyno;
		this.buyername = buyername;
		this.buyeradd = buyeradd;
		this.buydetailaddress = buydetailaddress;
		this.buyerzip = buyerzip;
		this.buyerphone = buyerphone;
		this.complete = complete;
		this.userno = userno;
		this.buydate = buydate;
	}

	@Override
	public String toString() {
		return "OrderUser [buyno=" + buyno + ", buyername=" + buyername + ", buyeradd=" + buyeradd
				+ ", buydetailaddress=" + buydetailaddress + ", buyerzip=" + buyerzip + ", buyerphone=" + buyerphone
				+ ", complete=" + complete + ", userno=" + userno + ", buydate=" + buydate + "]";
	}

	public int getBuyno() {
		return buyno;
	}

	public void setBuyno(int buyno) {
		this.buyno = buyno;
	}

	public String getBuyername() {
		return buyername;
	}

	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}

	public String getBuyeradd() {
		return buyeradd;
	}

	public void setBuyeradd(String buyeradd) {
		this.buyeradd = buyeradd;
	}

	public String getBuydetailaddress() {
		return buydetailaddress;
	}

	public void setBuydetailaddress(String buydetailaddress) {
		this.buydetailaddress = buydetailaddress;
	}

	public String getBuyerzip() {
		return buyerzip;
	}

	public void setBuyerzip(String buyerzip) {
		this.buyerzip = buyerzip;
	}

	public String getBuyerphone() {
		return buyerphone;
	}

	public void setBuyerphone(String buyerphone) {
		this.buyerphone = buyerphone;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public Date getBuydate() {
		return buydate;
	}

	public void setBuydate(Date buydate) {
		this.buydate = buydate;
	}

	
}
