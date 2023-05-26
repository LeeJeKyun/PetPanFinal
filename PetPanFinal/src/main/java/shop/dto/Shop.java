package shop.dto;

public class Shop {

   private int objectno;
   private String name;
   private int price;
   private int remain;
   private String shopcontent;
   private int deleteobj;
   
   public Shop() {
      // TODO Auto-generated constructor stub
   }

   public Shop(int objectno, String name, int price, int remain, String shopcontent, int deleteobj) {
      super();
      this.objectno = objectno;
      this.name = name;
      this.price = price;
      this.remain = remain;
      this.shopcontent = shopcontent;
      this.deleteobj = deleteobj;
   }

   @Override
   public String toString() {
      return "Shop [objectno=" + objectno + ", name=" + name + ", price=" + price + ", remain=" + remain
            + ", shopcontent=" + shopcontent + ", deleteobj=" + deleteobj + "]";
   }

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