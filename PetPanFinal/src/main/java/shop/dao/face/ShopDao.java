package shop.dao.face;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import member.dto.Member;
import shop.dto.Basket;
import shop.dto.OrderThing;
import shop.dto.OrderUser;
import shop.dto.ReportObject;
import shop.dto.Review;
import shop.dto.ReviewFile;
import shop.dto.Shop;
import shop.dto.ShopFile;
import util.ReviewPaging;
import util.ShopPaging;

public interface ShopDao {

	/**
	 * 게시글 총 개수
	 * @param search - 검색
	 * @return
	 */
	public int countShop(String search);

	
	/**
	 * 페이징 된 쇼핑 메인페이지
	 * @param paging - 페이징
	 * @return
	 */
	public List<Shop> selectAll(ShopPaging paging);
	
	
	/**
	 * objectno마다 하나의 게시글
	 * @param shop - objectno
	 * @return
	 */
	public Shop selectByObjno(Shop shop);

	
	/**
	 * 상품 사진
	 * @param basket - objectno
	 * @return
	 */
	public List<ShopFile> shopfile(Basket basket);
	
	
	/**
	 * 기존의 장바구니에 담겨있는지 확인 
	 * @param basket - userno objectno
	 * @return count(*) - int
	 */
	public int checkBasket(Basket basket);

	
	/**
	 * 장바구니 담기
	 * @param basket - quantity userno objectno
	 */
	public void insertBasket(Basket basket);
	
	
	/**
	 * 장바구니 업데이트
	 * @param basket - userno objectno
	 */
	public void updateBasket(Basket basket);
	
	
	/**
	 * 장바구니 보여주기
	 * @param basket
	 * @return - userno
	 */
	public List<Map<String, Object>> selectBasket(Basket basket);

	
	/**
	 * 장바구니 담은 유저 정보
	 * @param basket - userno
	 * @return
	 */
	public Member memberShop(Basket basket);

	
	/**
	 * OrderUserNo PK 뽑기
	 * @return
	 */
	public int buyno();
	
	
	/**
	 * 
	 * @param orderUser
	 */
	public void insertOrderUser(OrderUser orderUser);

	public void insertOrderThing(OrderThing orderThing);

	public void buyDeleteBasket(int userno);

	public Basket selectDeleteBasket(Basket basket);

	public void deleteBasket(int basketno);

	public List<Map<String, Object>> reviewList(Review review);

	public List<Map<String, Object>> orderList(OrderUser orderUser);

	public void writeReview(Review review);

	public void insertShopFile(ReviewFile reviewFile);

	public int selectNextval();

	public List<ReviewFile> fileList(int reviewno);

	public List<Integer> selectReviewNo(Review review);

	public int cntReviewno(Review review);

	public void updateC(Review review);

	

	/**
	 * 상품 신고 db저장
	 * @param reportObject - userno objectno reportdetail
	 */
	public void insertReport(ReportObject reportObject);

	

	




	










}
