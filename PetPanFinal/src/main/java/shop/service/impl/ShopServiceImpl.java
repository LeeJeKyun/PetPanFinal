package shop.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.dto.Member;
import shop.dao.face.ShopDao;
import shop.dto.Basket;
import shop.dto.OrderThing;
import shop.dto.OrderUser;
import shop.dto.Review;
import shop.dto.Shop;
import shop.service.face.ShopService;
import util.ReviewPaging;
import util.ShopPaging;

@Service
public class ShopServiceImpl implements ShopService{

	@Autowired ShopDao shopDao;
	
	
	@Override
	public ShopPaging getpaging(int curPage) {
		
		int total = shopDao.countShop();
		
		ShopPaging paging = new ShopPaging(total, curPage);
		
		return paging;
	}
	
	@Override
	public List<Shop> shoplist(ShopPaging paging) {

		List<Shop> list = shopDao.selectAll(paging);
		
		return list;
	}
	
	@Override
	public Shop view(Shop shop) {

			Shop view = shopDao.selectByObjno(shop);
			
			return view;
			
		
		
	}
	
	@Override
	public void insertBasket(Basket basket) {
		
		if(basket.getQuantity() <= 0) {
			return;
		} else {
			
			int cnt = shopDao.checkBasket(basket);
			
			if(cnt <= 0) {
				shopDao.insertBasket(basket);
			}else {
				shopDao.updateBasket(basket);
			}
		}
	}
	
	@Override
	public List<Map<String,Object>> selectBasket(Basket basket) {
		
		List<Map<String,Object>> list = shopDao.selectBasket(basket);
		
		return list;
	}
	
	@Override
	public List<Basket> newBasket(Basket basket) {
		
		List<Basket> list = shopDao.basket(basket);
		
		return list; 
	}
	
	@Override
	public Member memberShop(Basket basket) {
		
		return shopDao.memberShop(basket);
	}
	
	@Override
	public void insertOrder(List<Map<String, Object>> list) {
		
		OrderUser orderUser = new OrderUser();
		OrderThing orderThing = new OrderThing();
		
		
		for(int i=0; i<list.size(); i++) {
			
			int buyno = shopDao.buyno();
			
//			int userno =  (int)list.get(i).get("USERNO");
			int userno = ((BigDecimal)list.get(i).get("USERNO")).intValue();
			int objectno = ((BigDecimal)list.get(i).get("OBJECTNO")).intValue();
			int quantity = ((BigDecimal)list.get(i).get("QUANTITY")).intValue();
			
			Basket basket = new Basket(); 
			
			basket.setUserno(userno);
			Member member = shopDao.memberShop(basket);
			
			System.out.println("이거임 " + member);

			String buyername =  member.getUserName();
			String buyeradd = member.getAddress();
			String buydetailaddress = member.getDetailaddress();
			String buyerzip = member.getZipCode();
			String buyerphone = member.getPhone();
			
			orderUser.setBuyno(buyno);
			orderUser.setBuyeradd(buyeradd);
			orderUser.setBuydetailaddress(buydetailaddress);
			orderUser.setBuyername(buyername);
			orderUser.setBuyerphone(buyerphone);
			orderUser.setBuyerzip(buyerzip);
			orderUser.setUserno(userno);
			
			orderThing.setBuyno(buyno);
			orderThing.setQuantity(quantity);
			orderThing.setObjectno(objectno);
			
			System.out.println(orderUser);
			System.out.println(orderThing);
			
			shopDao.insertOrderUser(orderUser);
			shopDao.insertOrderThing(orderThing);
			
		}
		
	}
	
	@Override
	public void buyDeleteBasket(int userno) {
		
		shopDao.buyDeleteBasket(userno);
		
	}
	
	@Override
	public void deleteBasket(Basket basket) {
	
		Basket selectBasket = shopDao.selectDeleteBasket(basket);
		
		System.out.println("asdfasdfasdfasdf" +selectBasket);
		
		int basketno = selectBasket.getBasketno();
		
		shopDao.deleteBasket(basketno);
	}
	
	@Override
	public ReviewPaging reviewPaging(int curPage) {
		
		int total = shopDao.countReview();
		
		ReviewPaging paging = new ReviewPaging(total, curPage);
		
		return paging;
	}

	@Override
	public List<Map<String, Object>> reviewList(Review review) {
		
		List<Map<String,Object>> list = shopDao.reviewList(review);
			
		return list;
	}

	@Override
	public List<Map<String, Object>> orderList(OrderUser orderUser) {
		
		List<Map<String, Object>> list = shopDao.orderList(orderUser);
		
		return list;
	}
	@Override
	public void writeReview(Review review) {
		
		shopDao.writeReview(review);
		
	}
	
	
}
