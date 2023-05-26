package shop.dto;

public class Shop {

<<<<<<< HEAD
	private int objectno;
	private String name;
	private int price;
	private int remain;
	private String shopcontent;
	private int deleteobj;
	
	public Shop() {
		// TODO Auto-generated constructor stub
	}
=======
   private int objectno;
   private String name;
   private int price;
   private int remain;
   private String shopcontent;
   private int deleteobj;
   
   public Shop() {
      // TODO Auto-generated constructor stub
   }
>>>>>>> refs/remotes/origin/master

<<<<<<< HEAD
	public Shop(int objectno, String name, int price, int remain, String shopcontent, int deleteobj) {
		super();
		this.objectno = objectno;
		this.name = name;
		this.price = price;
		this.remain = remain;
		this.shopcontent = shopcontent;
		this.deleteobj = deleteobj;
	}
=======
   public Shop(int objectno, String name, int price, int remain, String shopcontent, int deleteobj) {
      super();
      this.objectno = objectno;
      this.name = name;
      this.price = price;
      this.remain = remain;
      this.shopcontent = shopcontent;
      this.deleteobj = deleteobj;
   }
>>>>>>> refs/remotes/origin/master

<<<<<<< HEAD
	@Override
	public String toString() {
		return "Shop [objectno=" + objectno + ", name=" + name + ", price=" + price + ", remain=" + remain
				+ ", shopcontent=" + shopcontent + ", deleteobj=" + deleteobj + "]";
	}
=======
   @Override
   public String toString() {
      return "Shop [objectno=" + objectno + ", name=" + name + ", price=" + price + ", remain=" + remain
            + ", shopcontent=" + shopcontent + ", deleteobj=" + deleteobj + "]";
   }
>>>>>>> refs/remotes/origin/master

   public int getObjectno() {
      return objectno;
   }

   public void setObjectno(int objectno) {
      this.objectno = objectno;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public int getRemain() {
      return remain;
   }

   public void setRemain(int remain) {
      this.remain = remain;
   }

   public String getShopcontent() {
      return shopcontent;
   }

<<<<<<< HEAD
	public void setShopcontent(String shopcontent) {
		this.shopcontent = shopcontent;
	}

	public int getDeleteobj() {
		return deleteobj;
	}

	public void setDeleteobj(int deleteobj) {
		this.deleteobj = deleteobj;
	}

	
}
=======
   public void setShopcontent(String shopcontent) {
      this.shopcontent = shopcontent;
   }

   public int getDeleteobj() {
      return deleteobj;
   }

   public void setDeleteobj(int deleteobj) {
      this.deleteobj = deleteobj;
   }

   
}
>>>>>>> refs/remotes/origin/master
