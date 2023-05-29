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
	public void view(Shop shop, Model model, Shop view) {

		//shop 상세페이지
		view = shopService.view(shop);
		
		model.addAttribute("view", view);
	}
	@GetMapping("/basket")
	public void basket() {
		
	}
	@PostMapping("/basket")
	public void basketPost(Basket basket, HttpSession session, Model model) {
		logger.info("/basket [POST]");
		
//		basket.setUserno((int)session.getAttribute("userno"));
		basket.setUserno(100);
		
		//장바구니 담기
		shopService.insertBasket(basket);
		
		//장바구니 보여주기
		List<Map<String, Object>> list = shopService.selectBasket(basket);
		
		
		model.addAttribute("list", list);
		
	}
	
	
	@GetMapping("/buy")
	public void buy(Model model, Basket basket) {

	}
	
	@PostMapping("/buy")
	public void buyPost(Model model, Basket basket, HttpSession session) {
		basket.setUserno((int)session.getAttribute("userno"));
//		basket.setUserno(100);
		System.out.println(basket);
		
//		List<Basket> list = shopService.newBasket(basket);
		List<Map<String,Object>> list = shopService.selectBasket(basket);
		model.addAttribute("list", list);
		System.out.println("장바구니 리스트 : " + list);
		
		Member member = shopService.memberShop(basket);
		
		System.out.println("멤버 : " + member);
		model.addAttribute("member", member);		

		
	}
	@GetMapping("/pay")
	public String pay(OrderUser orderUser, OrderThing orderThing, Member member,Basket basket, HttpSession session) {
		
		basket.setUserno((int)session.getAttribute("userno"));
		List<Map<String,Object>> list = shopService.selectBasket(basket);
		
		shopService.insertOrder(list);
		
		return "redirect:/shop/main";
	}
}
