package shop.service.face;

import java.util.List;
import java.util.Map;

import shop.dto.Basket;
import shop.dto.Shop;
import util.ShopPaging;

public interface ShopService {

	
	public ShopPaging getpaging(int curPage);

	public List<Shop> shoplist(ShopPaging paging);

	public Shop view(Shop shop);

	public void insertBasket(Basket basket);

	public List<Map<String, Object>> selectBasket(Basket basket);



}
