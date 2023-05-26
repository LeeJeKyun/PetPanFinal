package shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.dto.Member;
import shop.dao.face.ShopDao;
import shop.dto.Basket;
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
	public List<Map<String, Object>> selectBasket(Basket basket) {
		
		List<Map<String, Object>> list = shopDao.selectBasket(basket);
		
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
	
}
