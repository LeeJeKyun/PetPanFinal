package admin.dao.face;

import java.util.List;

import shop.dto.Shop;
import shop.dto.ShopFile;
import util.AdminPaging;

public interface AdminShopDao {

	public int selectShopTotalSearch(String search);

	public List<Shop> ShoptselectAll(AdminPaging paging);

	public void updateShop(int deleteNo);

	public void insertShop(Shop shop);

	public int selectNextObj();

	public void insertShopFile(ShopFile shopfile);
	
	public Shop selectShop(Integer objectno);

	public List<ShopFile> selectShopFile(Integer objectno);

	public ShopFile selectShopFileByFileno(int e);

	public void deleteChangeFileOnDb(int e);

	public void updateShopDetail(Shop shop);

	

}
