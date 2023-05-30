package shop.service.face;

import java.util.List;
import java.util.Map;

import member.dto.Member;
import shop.dto.Basket;
import shop.dto.Shop;
import util.ShopPaging;

public interface ShopService {

	/**
	 * 페이징
	 * @param curPage
	 * @return
	 */
	public ShopPaging getpaging(int curPage);

	/**
	 * 쇼핑 메인페이지 리스트
	 * @param paging 페이징
	 * @return 전체 쇼핑 게시판 리스트
	 */
	public List<Shop> shoplist(ShopPaging paging);

	/**
	 * 쇼핑 상세페이지
	 * @param shop objectno
	 * @return
	 */
	public Shop view(Shop shop);

	
	public void insertBasket(Basket basket);

	public List<Map<String, Object>> selectBasket(Basket basket);

	public List<Basket> newBasket(Basket basket);

	public Member memberShop(Basket basket);

	public void insertOrder(List<Map<String, Object>> list);

	public void buyDeleteBasket(int userno);

	public void deleteBasket(Basket basket);


		
	




}
