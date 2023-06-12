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
	 * @return buyno
	 */
	public int buyno();
	
	
	/**
	 * 구매내역 orderuser DB저장
	 * @param orderUser
	 */
	public void insertOrderUser(OrderUser orderUser);

	
	/**
	 * 구매내역 orderthing DB저장
	 * @param orderThing
	 */
	public void insertOrderThing(OrderThing orderThing);

	
	/**
	 * 장바구니 삭제
	 * @param userno - 세션 userno
	 */
	public void buyDeleteBasket(int userno);

	
	/**
	 * 장바구니 삭제할 basketno 가져오기
	 * @param basket - userno, objectno
	 * @return
	 */
	public Basket selectDeleteBasket(Basket basket);

	
	/**
	 * 장바구니 삭제 
	 * @param basketno
	 */
	public void deleteBasket(int basketno);

	
	/**
	 * 리뷰리스트 보여주기
	 * @param review - objectno
	 * @return
	 */
	public List<Map<String, Object>> reviewList(Review review);

	
	/**
	 * 구매내역 보여주기
	 * @param orderUser - userno
	 * @return
	 */
	public List<Map<String, Object>> orderList(OrderUser orderUser);

	
	/**
	 * review PK 뽑기
	 * @return reviewno
	 */
	public int selectNextval();
	
	/**
	 * 리뷰 등록
	 * @param review
	 */
	public void writeReview(Review review);

	
	/**
	 * 리뷰 사진 저장
	 * @param reviewFile
	 */
	public void insertShopFile(ReviewFile reviewFile);

	
	/**
	 * 리뷰쓰면 orderUser테이블 complete 바꾸기
	 * @param review - buyno
	 */
	public void updateC(Review review);
	
	
	/**
	 * 리뷰가 있는지 확인
	 * @param review - objectno
	 * @return
	 */
	public int cntReviewno(Review review);

	
	/**
	 * objectno으로 reviewno불러오기
	 * @param review - objectno
	 * @return - reviewno List
	 */
	public List<Integer> selectReviewNo(Review review);
	
	
	/**
	 * 리뷰마다 파일 가져오기 
	 * @param reviewno
	 * @return reviewfile List
	 */
	public List<ReviewFile> fileList(int reviewno);


	/**
	 * 상품 신고 DB저장
	 * @param reportObject - userno objectno reportdetail
	 */
	public void insertReport(ReportObject reportObject);

	

	




	










}
