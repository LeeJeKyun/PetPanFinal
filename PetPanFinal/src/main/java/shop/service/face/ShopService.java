package shop.service.face;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import member.dto.Member;
import shop.dto.Basket;
import shop.dto.OrderUser;
import shop.dto.ReportObject;
import shop.dto.Review;
import shop.dto.ReviewFile;
import shop.dto.Shop;
import shop.dto.ShopFile;
import util.Paging;
import util.ReviewPaging;
import util.ShopPaging;

public interface ShopService {

	/**
	 * 페이징, 검색
	 * @param curPage - 현제페이지
	 * @param search - 검색
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
	 * 상품 사진
	 * @param basket - objectno
	 * @return
	 */
	public List<ShopFile> shopfile(Basket basket);
	
	
	/**
	 * objectno마다 리뷰가 있는지 확인
	 * @param review - objectno
	 * @return 
	 */
	public int cntReview(Review review);
	
	
	/**
	 * objectno마다 리뷰 보여주기
	 * @param review - objectno
	 * @return
	 */
	public List<Map<String, Object>> reviewList(Review review);
	
	
	/**
	 * 리뷰마다 사진 보여주기
	 * @param review - objectno
	 * @return
	 */
	public Map<Integer, List> ReviewfileList(Review review);
	
	
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

	
	/**
	 * 장바구니 담은 유저 정보
	 * @param basket - userno
	 * @return
	 */
	public Member memberShop(Basket basket);
	
	
	/**
	 * 구매내역 DB저장
	 * @param list - 장바구니
	 */
	public void insertOrder(List<Map<String, Object>> list);

	
	/**
	 * 구매 후 장바구니 삭제
	 * @param userno
	 */
	public void buyDeleteBasket(int userno);

	
	/**
	 * 장바구니 삭제
	 * @param basket - userno
	 */
	public void deleteBasket(Basket basket);

	
	/**
	 * 구매내역
	 * @param orderUser - 상품 구매한 회원정보
	 * @return
	 */
	public List<Map<String, Object>> orderList(OrderUser orderUser);

	
	/**
	 * 리뷰 쓰기
	 * @param fileList - 파일
	 * @param review - 뭐였더라
	 * @param no - 파일취소
	 * @param orderUser - 구매확인
	 */
	public void writeReview(List<MultipartFile> fileList, Review review, List<Integer> no, OrderUser orderUser);


	/**
	 * 상품 신고 
	 * @param reportObject - userno object reportdetail
	 */
	public void insertReport(ReportObject reportObject);

}
