package admin.dao.face;

import java.util.HashMap;
import java.util.List;

import admin.dto.ReportObject;
import board.dto.Notice;
import member.dto.Member;

import shop.dto.OrderThing;
import shop.dto.Shop;
import shop.dto.ShopFile;
import util.AdminPaging;

public interface AdminShopDao {

	/**
	 * 검색어를 바탕으로 띄워줘야하는 total양을 보내준다
	 * @param search
	 * @return int total
	 */
	public int selectShopTotalSearch(String search);

	public List<Shop> ShoptselectAll(AdminPaging paging);

	public void updateShop(int deleteNo);

	/**
	 * 
	 * 저장된 shop객체를 통해서 새로운 상점을 추가한다.
	 * @param shop
	 */
	public void insertShop(Shop shop);

	/**
	 * 지금 상점 글이 저장될 objectno를 미리 불러온다.
	 * @return int objectno
	 */
	public int selectNextObj();

	/**
	 * 저장된 파일이 들어있는 shopfile을 받아서 추가한다. nolist가 끝날때 까지 반복한다.
	 * @param shopfile
	 */
	public void insertShopFile(ShopFile shopfile);
	
	/**
	 * objectno를 바탕으로 상세 상품정보를 반환한다.
	 * @param objectno
	 * @return Shop
	 */
	public Shop selectShop(Integer objectno);

	/**
	 * objectno로 저장되어 있는 파일의 목록을 반환한다.
	 * @param objectno
	 * @return List<ShopFile>
	 */
	public List<ShopFile> selectShopFile(Integer objectno);

	public ShopFile selectShopFileByFileno(int e);

	/**
	 * 해당 인덱스에 저장된 파일들을 그냥 삭제를 진행한다. 실제 파일도 삭제한다.
	 * @param e
	 */
	public void deleteChangeFileOnDb(int e);

	/**
	 * 받아온 shop객체를 통해서 shop의 내부 내용을 바꾼다.
	 * @param shop
	 */
	public void updateShopDetail(Shop shop);

	/**
	 * objectno로 판매를 중단한다.
	 * @param objectno
	 */
	public void changeShopDeleteobj(Integer objectno);

	/**
	 * paging 객체를 이용해 조건에 맞는 전체 구매자 리스트를 반환한다.
	 * @param adminPaging
	 * @return List<HashMap<String, Object>>
	 */
	public List<HashMap<String, Object>> selectAllBuyer(AdminPaging adminPaging);
	
	/**
	 * 검색어에 맞는 전체 리스트의 수를 반환한다.
	 * @param search
	 * @return int total
	 */
	public int selectTotalSearchBuyer(String search);

	/**
	 * buyno에 어떤 물품을 얼마나 구매했는지를 반환한다.
	 * @param buyno
	 * @return OrderThing 
	 */
	public OrderThing selectObjectno(int buyno);

	/**
	 * 구매한 buyno와 OrderThing을 이용해서 실제 상품의 갯수를 줄인다.
	 * @param orderThing
	 */
	public void updateObjectReamin(OrderThing orderThing);
	
	/**
	 * 동적 쿼리를 이용해서 deleteNoMaplist = objectnolist안에 있는 상품들을 판매 중단시킨다.
	 * @param deleteNoMaplist
	 */
	public void updateShopDeleteObj(List<HashMap> deleteNoMaplist);

	/**
	 * 
	 * 동적 쿼리를 이용해서 deleteNoMaplist = buynolist이기에 배송중을 배송완료로 변경한다.
	 * @param deleteNoMaplist
	 */
	public void updateOrderUserComplete(List<HashMap> deleteNoMaplist);

	public int countTotalReportShop(String search);

	public List<ReportObject> ReportShopselectAll(AdminPaging paging);

	public ReportObject selectReportObject(Integer objreportNo);

	public Member selectReportMember(ReportObject reportObject);

	public void updateReportobject(Integer objreportNo);

	public void updateReportObjectComplete(List<HashMap> objreportNolist);

	public void updateReportObjectCompleteResell(List<HashMap> objreportNolist);

	/**
	 * resellNoMaplist로 받아온 objectno로 상품의 판매를 재개하도록한다.
	 * @param resellNoMaplist
	 */
	public void updateShopResellObj(List<HashMap> resellNoMaplist);

	public int selectObjectReamin(OrderThing orderThing);




}
