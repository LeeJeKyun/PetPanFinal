package shop.dto;

public class Basket {

	private int basketno;
	private int quantity;
	private int userno;
	private int objectno;
	
	public Basket() {
		// TODO Auto-generated constructor stub
	}

	public Basket(int basketno, int quantity, int userno, int objectno) {
		super();
		this.basketno = basketno;
		this.quantity = quantity;
		this.userno = userno;
		this.objectno = objectno;
	}

	@Override
	public String toString() {
		return "Basket [basketno=" + basketno + ", quantity=" + quantity + ", userno=" + userno + ", objectno="
				+ objectno + "]";
	}

	public int getBasketno() {
		return basketno;
	}

	public void setBasketno(int basketno) {
		this.basketno = basketno;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public int getObjectno() {
		return objectno;
	}

	public void setObjectno(int objectno) {
		this.objectno = objectno;
	}
	
}
