package shop.dao.face;

import java.util.List;
import java.util.Map;

import shop.dto.Basket;
import shop.dto.Shop;
import util.ShopPaging;

public interface ShopDao {

	public int countShop();

	public List<Shop> selectAll(ShopPaging paging);

	public Shop selectByObjno(Shop shop);

	public void insertBasket(Basket basket);

	public int checkBasket(Basket basket);
	
	public void updateBasket(Basket basket);
	
	public List<Map<String, Object>> selectBasket(Basket basket);




}
