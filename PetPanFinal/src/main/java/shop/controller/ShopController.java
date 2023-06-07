package shop.controller;

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
		
		basket.setUserno((int)session.getAttribute("userno"));
		
		//장바구니 담기
		shopService.insertBasket(basket);
		
		//장바구니 보여주기
		List<Map<String, Object>> list = shopService.selectBasket(basket);
		
		Member member = shopService.memberShop(basket);
		
		model.addAttribute("list", list);
		model.addAttribute("member", member);
		
		//상품 사진
		List<ShopFile> file = shopService.shopfile(basket);
		model.addAttribute("file", file);
		System.out.println("파일 : " + file); 

		//---- 리뷰 ----
		paging = shopService.reviewPaging(curPage);
		review.setObjectno(shop.getObjectno());

		int checkReview = shopService.cntReview(review);
		
		if(checkReview > 0) {
			
			List<Review> reviewList= shopService.reviewList(review);
			
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
		
		System.out.println("리스트 : " + list);
		
		model.addAttribute("list", list);
		model.addAttribute("member", member);	
	}
	
	
	@GetMapping("/buy")
	public void buy(Model model, Basket basket) {
		
	}
	
	@PostMapping("/buy")
	public void buyPost(Model model, Basket basket, HttpSession session) {
		
		basket.setUserno((int)session.getAttribute("userno"));
		
		shopService.insertBasket(basket);
		
		List<Map<String,Object>> list = shopService.selectBasket(basket);
		model.addAttribute("list", list);
		
		Member member = shopService.memberShop(basket);
		
		model.addAttribute("member", member);		
		
	}
	
	
	@GetMapping("/pay")
	public String pay(OrderUser orderUser, OrderThing orderThing, Member member,Basket basket, HttpSession session) {
		
		int userno = (int)session.getAttribute("userno");
		basket.setUserno((int)session.getAttribute("userno"));
		List<Map<String,Object>> list = shopService.selectBasket(basket);
		
		shopService.insertOrder(list);
		shopService.buyDeleteBasket(userno);
		
		return "redirect:/shop/main";
	}
	
	@GetMapping("/delete")
	public String deletePost(Basket basket, HttpSession session, Model model) {
		
		basket.setUserno((int)session.getAttribute("userno"));
		
		shopService.deleteBasket(basket);
		
		List<Map<String, Object>> list = shopService.selectBasket(basket);

		Member member = shopService.memberShop(basket);
		
		model.addAttribute("list", list);
		model.addAttribute("member", member);
		
		return "forward:basket";
	}
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
		
		shopService.writeReview(fileList, review, no, orderUser);
		
		return "redirect:/shop/main";
	}
	
	@PostMapping("report")
	public void report() {
		
	}
	
	
}
