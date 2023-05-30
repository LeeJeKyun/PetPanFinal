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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import member.dto.Member;
import shop.dto.Basket;
import shop.dto.OrderThing;
import shop.dto.OrderUser;
import shop.dto.Shop;
import shop.service.face.ShopService;
import util.ShopPaging;

@Controller
@RequestMapping("/shop")
public class ShopController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired ShopService shopService;
	
	@GetMapping("/main")
	public void main(Model model, ShopPaging paging
			,@RequestParam(value="curPage", required=false, defaultValue = "1")int curPage) {
		//페이징
		paging = shopService.getpaging(curPage);
		
		//상품 전체 조회
		List<Shop> shoplist = shopService.shoplist(paging);
		
		model.addAttribute("paging", paging);
		model.addAttribute("list", shoplist);
	}
	
	@GetMapping("/view")
	public void view(Shop shop, Model model, Shop view, HttpSession session, Basket basket) {

		System.out.println(shop);
		//shop 상세페이지
		view = shopService.view(shop);
		System.out.println("뷰뷰ㅠ뷰뷰뷰뷰ㅠ뷰뷰 : " + view);
		
		model.addAttribute("view", view);
		
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
	@GetMapping("/basket")
	public void basket() {
		
	}
	@PostMapping("/basket")
	public void basketPost(Basket basket, HttpSession session, Model model) {
		logger.info("/basket [POST]");
		
		basket.setUserno((int)session.getAttribute("userno"));
//		basket.setUserno(100);
		
		//장바구니 담기
		System.out.println("insert" + basket);
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
		
		System.out.println("리스트 : " + list);
		
		Member member = shopService.memberShop(basket);
		
		model.addAttribute("member", member);		
		
	}
	
	
	@GetMapping("/pay")
	public String pay(OrderUser orderUser, OrderThing orderThing, Member member,Basket basket, HttpSession session) {
		
		int userno = (int)session.getAttribute("userno");
		basket.setUserno((int)session.getAttribute("userno"));
		List<Map<String,Object>> list = shopService.selectBasket(basket);
		
		System.out.println(list);
		shopService.insertOrder(list);
		shopService.buyDeleteBasket(userno);
		
		return "redirect:/shop/main";
	}
	
	@GetMapping("/delete")
	public String deletePost(Basket basket, HttpSession session, Model model) {
		
		basket.setUserno((int)session.getAttribute("userno"));
		
		shopService.deleteBasket(basket);
		System.out.println("asdfasdfadf" + basket);
		
		List<Map<String, Object>> list = shopService.selectBasket(basket);

		Member member = shopService.memberShop(basket);
		
		System.out.println("리스트 : " + list);
		
		model.addAttribute("list", list);
		model.addAttribute("member", member);
		
		return "forward:basket";
	}
}
