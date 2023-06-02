package shop.dao.face;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import member.dto.Member;
import shop.dto.Basket;
import shop.dto.OrderThing;
import shop.dto.OrderUser;
import shop.dto.Review;
import shop.dto.ReviewFile;
import shop.dto.Shop;
import util.ReviewPaging;
import util.ShopPaging;

public interface ShopDao {

	public int countShop();

	public List<Shop> selectAll(ShopPaging paging);
	
	public Shop selectByObjno(Shop shop);

	public void insertBasket(Basket basket);

	public int checkBasket(Basket basket);
	
	public void updateBasket(Basket basket);
	
	public List<Map<String, Object>> selectBasket(Basket basket);

	public List<Basket> basket(Basket basket);

	public Member memberShop(Basket basket);

	public int buyno();
	
	public void insertOrderUser(OrderUser orderUser);

	public void insertOrderThing(OrderThing orderThing);

	public void buyDeleteBasket(int userno);

	public Basket selectDeleteBasket(Basket basket);

	public void deleteBasket(int basketno);

	public int countReview();

	public List<Map<String, Object>> reviewList(Review review);

	public List<Map<String, Object>> orderList(OrderUser orderUser);

	public void writeReview(Review review);

	public void insertShopFile(ReviewFile reviewFile);

	public int selectNextval();








}
