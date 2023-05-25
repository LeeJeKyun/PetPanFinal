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

import shop.dto.Basket;
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
	
	@PostMapping("/basket")
	public void basketPost(Basket basket, HttpSession session, Model model) {
		logger.info("/basket [POST]");
		
//		basket.setUserno((int)session.getAttribute("userno"));
		basket.setUserno(122);
		
		//장바구니 담기
		shopService.insertBasket(basket);
		
		//장바구니 보여주기
		List<Map<String, Object>> list = shopService.selectBasket(basket);
		
		model.addAttribute("list", list.get(list.size()-1));
		
	}
	
	@GetMapping("/buy")
	public void buy() {
		
	}
	
	@PostMapping("/buy")
	public void buyPost(Basket basket) {
	}
}
