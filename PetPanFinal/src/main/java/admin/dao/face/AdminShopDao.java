package admin.dao.face;

import java.util.List;

import shop.dto.Shop;
import util.AdminPaging;

public interface AdminShopDao {

	public int selectShopTotalSearch(String search);

	public List<Shop> ShoptselectAll(AdminPaging paging);

	public void updateShop(int deleteNo);

	public void insertShop(Shop shop);

	public int selectNextObj();

}
