package shop.dto;

public class OrderThing {

	private int buylistno;
	private int quantity;
	private int objectno;
	private int buyno;
	
	public OrderThing() {
		// TODO Auto-generated constructor stub
	}

	public OrderThing(int buylistno, int quantity, int objectno, int buyno) {
		super();
		this.buylistno = buylistno;
		this.quantity = quantity;
		this.objectno = objectno;
		this.buyno = buyno;
	}

	@Override
	public String toString() {
		return "OrderThing [buylistno=" + buylistno + ", quantity=" + quantity + ", objectno=" + objectno + ", buyno="
				+ buyno + "]";
	}

	public int getBuylistno() {
		return buylistno;
	}

	public void setBuylistno(int buylistno) {
		this.buylistno = buylistno;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getObjectno() {
		return objectno;
	}

	public void setObjectno(int objectno) {
		this.objectno = objectno;
	}

	public int getBuyno() {
		return buyno;
	}

	public void setBuyno(int buyno) {
		this.buyno = buyno;
	}
	
}
