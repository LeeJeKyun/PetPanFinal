package admin.dao.face;

import java.util.HashMap;
import java.util.List;

import admin.dto.ReportObject;
import member.dto.Member;

import shop.dto.OrderThing;
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

	public void changeShopDeleteobj(Integer objectno);

	public List<HashMap<String, Object>> selectAllBuyer(AdminPaging adminPaging);
	
	public int selectTotalSearchBuyer(String search);

	public OrderThing selectObjectno(int buyno);

	public void updateObjectReamin(OrderThing orderThing);

	public void updateShopDeleteObj(List<HashMap> deleteNoMaplist);

	public void updateOrderUserComplete(List<HashMap> deleteNoMaplist);

	public int countTotalReportShop(String search);

	public List<ReportObject> ReportShopselectAll(AdminPaging paging);

	public ReportObject selectReportObject(Integer objreportNo);

	public Member selectReportMember(ReportObject reportObject);

	public void updateReportobject(Integer objreportNo);

	public void updateReportObjectComplete(List<HashMap> objreportNolist);



}
