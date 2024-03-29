package shop.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import member.dto.Member;
import shop.dto.Basket;
import shop.dto.OrderThing;
import shop.dto.OrderUser;
import shop.dto.ReportObject;
import shop.dto.Review;
import shop.dto.ReviewFile;
import shop.dto.Shop;
import shop.dto.ShopFile;
import shop.service.face.ShopService;
import util.ReviewPaging;
import util.ShopPaging;

@Controller
@RequestMapping("/shop")
public class ShopController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired ShopService shopService;
	
	@GetMapping("/main")
	public void main(Model model, ShopPaging paging
			,@RequestParam(value="curPage", required=false, defaultValue = "1")int curPage
			,@RequestParam(required=false,defaultValue = "")String search) {
		//페이징
		paging = shopService.getpaging(curPage,search);
		
		//상품 전체 조회
		List<Shop> shoplist = shopService.shoplist(paging);

		model.addAttribute("search", search);
		model.addAttribute("paging", paging);
		model.addAttribute("list", shoplist);
		
	}
	
	@GetMapping("/view")
	public void view(Shop shop, Model model, Shop view, HttpSession session, Basket basket, Review review, ReviewPaging paging
			, @RequestParam(value="curPage", required=false, defaultValue = "1")int curPage){

		//shop 상세페이지
		view = shopService.view(shop);
		
		model.addAttribute("view", view);
		
		//비로그인 확인
		Object checkLogin = session.getAttribute("userno");
		
		//로그인상태일때 장바구니 보이게하기
		if(checkLogin != null) {
			
			int userno = (int)checkLogin;
			basket.setUserno(userno);
			
			//장바구니 담기
			shopService.insertBasket(basket);
			
			//장바구니 보여주기
			List<Map<String, Object>> list = shopService.selectBasket(basket);
			
			Member member = shopService.memberShop(basket);
			
			model.addAttribute("list", list);
			model.addAttribute("member", member);
			model.addAttribute("login", true);
			
		}
		
		//상품 사진
		List<ShopFile> file = shopService.shopfile(basket);
		model.addAttribute("file", file);

		//---- 리뷰 ----
		review.setObjectno(shop.getObjectno());

		//리뷰 있는지 확인
		int checkReview = shopService.cntReview(review);
		
		//리뷰가 있을때
		if(checkReview > 0) {
			
			//리뷰 리스트
			List<Map<String, Object>> reviewList= shopService.reviewList(review);
			//리뷰 사진
			Map<Integer, List> FileList = shopService.ReviewfileList(review);
			
			model.addAttribute("reviewList", reviewList);
			model.addAttribute("filelist", FileList);
			
		}
		
	}
	@GetMapping("/basket")
	public void basket() {
		
	}
	@PostMapping("/basket")
	public void basketPost(Basket basket, HttpSession session, Model model) {
		logger.info("/basket [POST]");
		
		basket.setUserno((int)session.getAttribute("userno"));
		
		//장바구니 담기
		shopService.insertBasket(basket);
		
		//장바구니 보여주기
		List<Map<String, Object>> list = shopService.selectBasket(basket);
		
		Member member = shopService.memberShop(basket);
		
		model.addAttribute("list", list);
		model.addAttribute("member", member);	
	}
	
	
	@GetMapping("/buy")
	public void buy(Model model, int userno, Basket basket) {
		
		basket.setUserno(userno);
		
		model.addAttribute("basket", basket);
		//바로 구매 누르면 장바구니 자동 등록
		shopService.insertBasket(basket);
		
		//구매전 장바구니 확인 
		List<Map<String,Object>> list = shopService.selectBasket(basket);
		model.addAttribute("list", list);
		
		Member member = shopService.memberShop(basket);
		
		System.out.println(list);
		System.out.println(member);
		
		model.addAttribute("member", member);
	}
	
	@PostMapping("/buy")
	public void buyPost(Model model, Basket basket, HttpSession session) {
		
		basket.setUserno((int)session.getAttribute("userno"));
		
		//바로 구매 누르면 장바구니 자동 등록
		shopService.insertBasket(basket);
		
		//구매전 장바구니 확인 
		List<Map<String,Object>> list = shopService.selectBasket(basket);
		model.addAttribute("list", list);

		
		Member member = shopService.memberShop(basket);
		
		model.addAttribute("member", member);		
		
		System.out.println(list);
		System.out.println(member);
	}
	
	
	@GetMapping("/pay")
	public String pay(OrderUser orderUser, OrderThing orderThing, Member member,Basket basket, HttpSession session) {
		
		int userno = (int)session.getAttribute("userno");
		basket.setUserno((int)session.getAttribute("userno"));
		
		//구매전 장바구니 확인
		List<Map<String,Object>> list = shopService.selectBasket(basket);
		
		//구매내역
		shopService.insertOrder(list);
		//구매후 장바구니 삭제
		shopService.buyDeleteBasket(userno);
		
		return "redirect:/shop/main";
	}
	
	//shop 상세정보에서 장바구니 삭제
	@GetMapping("/delete")
	public String deletePost(Basket basket, HttpSession session, Model model) {
		
		basket.setUserno((int)session.getAttribute("userno"));
		
		//장바구니 삭제
		shopService.deleteBasket(basket);
		
		//장바구니 삭제 후 장바구니 보여주기
		List<Map<String, Object>> list = shopService.selectBasket(basket);

		Member member = shopService.memberShop(basket);
		
		model.addAttribute("list", list);
		model.addAttribute("member", member);
		
		return "forward:basket";
	}
	
	//장바구니 삭제
	@GetMapping("/deleteBasket")
	public String deleteBasket(Basket basket, HttpSession session, Model model) {

		basket.setUserno((int)session.getAttribute("userno"));
		shopService.deleteBasket(basket);
		
		List<Map<String, Object>> list = shopService.selectBasket(basket);
		
		Member member = shopService.memberShop(basket);
		
		model.addAttribute("list", list);
		model.addAttribute("member", member);
		
		return "forward:headBasket";
	}
	
	@GetMapping("/headBasket")
	public void headBasket(Basket basket, HttpSession session, Model model) {
		basket.setUserno((int)session.getAttribute("userno"));
		
		//장바구니 담기
		shopService.insertBasket(basket);
		
		//장바구니 보여주기
		List<Map<String, Object>> list = shopService.selectBasket(basket);
		
		Member member = shopService.memberShop(basket);
		
		
		model.addAttribute("list", list);
		model.addAttribute("member", member);	
	}
	
	@GetMapping("/orderList")
	public void orderList(OrderUser orderUser, HttpSession session, Model model) {
		
		orderUser.setUserno((int)session.getAttribute("userno"));
		
		//구매내역
		List<Map<String, Object>> list = shopService.orderList(orderUser);

		model.addAttribute("list", list);
		
	}
	
	@GetMapping("/writeReview")
	public void writeReview(int objectno,int buyno, Model model){
		
		model.addAttribute("objectno",objectno);
		model.addAttribute("buyno",buyno);
		
	}
		
	@PostMapping("/writeReview")
	public String writeReviewPost(HttpSession session, Review review
								,@RequestParam(value = "file", required = false)List<MultipartFile> fileList
								,@RequestParam(required = false) List<Integer> no
								,OrderUser orderUser) {
		
		review.setUserno((int)session.getAttribute("userno"));
		
		if(review.getReviewcontent() == null || review.getReviewtitle() == null ) {
			return "redirect:/shop/main";
		}
		
		//리뷰쓰기
		shopService.writeReview(fileList, review, no, orderUser);
		
		return "redirect:/shop/main";
	}
	
	@GetMapping("/report")
	public void report(int objectno, Model model) {
	
		model.addAttribute("objectno", objectno);
		
	}
	
	@PostMapping("/report")
	public void reportPost(ReportObject reportObject, HttpSession session, String reportdetail) {
	
		reportObject.setUserno((int)session.getAttribute("userno"));
		reportObject.setReportdetail(reportdetail);
		
		logger.info("{}", reportObject);
		
		//상품신고
		shopService.insertReport(reportObject);
		
		
	}
	
	
}
