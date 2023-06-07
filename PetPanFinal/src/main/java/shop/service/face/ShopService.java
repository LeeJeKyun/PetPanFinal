package shop.service.face;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import member.dto.Member;
import shop.dto.Basket;
import shop.dto.OrderUser;
import shop.dto.Review;
import shop.dto.ReviewFile;
import shop.dto.Shop;
import shop.dto.ShopFile;
import util.Paging;
import util.ReviewPaging;
import util.ShopPaging;

public interface ShopService {

	/**
	 * 페이징
	 * @param curPage
	 * @param search 
	 * @return
	 */
	public ShopPaging getpaging(int curPage, String search);

	/**
	 * 쇼핑 메인페이지 리스트
	 * @param paging 페이징
	 * @return 전체 쇼핑 게시판 리스트
	 */
	public List<Shop> shoplist(ShopPaging paging);

	/**
	 * 쇼핑 상세페이지
	 * @param shop - objectno
	 * @return
	 */
	public Shop view(Shop shop);

	/**
	 * 장바구니 담기
	 * @param basket - userno, objectno
	 */
	public void insertBasket(Basket basket);

	/**
	 * 장바구니 보여주기
	 * @param basket - userno, objectno
	 * @return
	 */
	public List<Map<String, Object>> selectBasket(Basket basket);

	public List<Basket> newBasket(Basket basket);

	public Member memberShop(Basket basket);

	public void insertOrder(List<Map<String, Object>> list);

	public void buyDeleteBasket(int userno);

	public void deleteBasket(Basket basket);

	public ReviewPaging reviewPaging(int curPage);

	public List<Review> reviewList(Review review);

	public List<Map<String, Object>> orderList(OrderUser orderUser);

	public void writeReview(List<MultipartFile> fileList, Review review, List<Integer> no, OrderUser orderUser);

	public List<ReviewFile> ReviewfileList(Review review);

	public int cntReview(Review review);

	public List<ShopFile> shopfile(Basket basket);

	


	



		
	




}
