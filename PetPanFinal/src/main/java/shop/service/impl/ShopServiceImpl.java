package shop.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.dto.Member;
import shop.dao.face.ShopDao;
import shop.dto.Basket;
import shop.dto.OrderUser;
import shop.dto.Shop;
import shop.service.face.ShopService;
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
		
		int cnt = shopDao.checkBasket(basket);
		
		if(cnt <= 0) {
			shopDao.insertBasket(basket);
		}else {
			shopDao.updateBasket(basket);
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
		
		for(int i=0; i<list.size(); i++) {
			
//			int userno =  (int)list.get(i).get("USERNO");
			int userno = ((BigDecimal)list.get(i).get("USERNO")).intValue();
			
			Basket basket = new Basket(); 
			basket.setUserno(userno);
			shopDao.memberShop(basket);
			
			String buyername =  shopDao.memberShop(basket).getUserName();
			String buyeradd = shopDao.memberShop(basket).getAddress();
			String buydetailaddress = shopDao.memberShop(basket).getDetailaddress();
			String buyerzip = shopDao.memberShop(basket).getZipCode();
			String buyerphone = shopDao.memberShop(basket).getPhone();
			
			orderUser.setBuyeradd(buyeradd);
			orderUser.setBuydetailaddress(buydetailaddress);
			orderUser.setBuyername(buyername);
			orderUser.setBuyerphone(buyerphone);
			orderUser.setBuyerzip(buyerzip);
			orderUser.setUserno(userno);
			
			shopDao.inserOrderUser(orderUser);
		}
		
		
		
	}
	
}
