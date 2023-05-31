package admin.controller;

import java.util.List;

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

import admin.dto.ReportBoard;
import admin.service.face.AdminService;
import shop.dto.Shop;
import shop.dto.ShopFile;
import util.AdminPaging;

@RequestMapping("/admin/shop")
@Controller
public class AdminShopcontroller {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired AdminService adminService;
	
	@GetMapping("/list")
	public void ShopList(@RequestParam(defaultValue = "0") int curPage, 
			Model model, 
			@RequestParam(required=false,defaultValue = "")String search) {
		logger.info("/reportboard [GET}");
		logger.info("curPage = {}", curPage);
		AdminPaging paging = new AdminPaging();

	
		paging = adminService.getShopPage(curPage,search);
		
		logger.info(search);
		
		List<Shop> list;

			list = adminService.getSearchShopBoard(paging);
		
		model.addAttribute("search",search);
		model.addAttribute("list",list);
		model.addAttribute("paging",paging);
		
	}
	@GetMapping("/delete")
	public String Shopdelete(@RequestParam(value="delete",required=false) List<String> delete) {
		
		adminService.deleteCheckedShop(delete);
		
		
		return "redirect:/admin/shop/list";
		
	}
	
	@GetMapping("/write")
	public void addShopObject() {
		
	}
	
	
	@PostMapping("/write")
	public String addShopObjectProc(
			@RequestParam(value = "file", required = false)List<MultipartFile> fileList //올릴 파일 리스트
			, Shop shop
			, @RequestParam(required = false) List<Integer> no // 취소된 파일 -1
			) {
		if(shop.getShopcontent() == null || shop.getName() == null )
			return "redirect:/admin/shop/list";
		
		logger.info("write post");
		
		logger.info("no : {}", no);
		logger.info("fileList : {}", fileList);
		logger.info("board : {}", shop);
		
		int objectno = adminService.saveShopGetObjectno(shop);
		
		adminService.saveShopFiles(fileList, objectno, no);
		

		
		
		return "redirect:/admin/shop/list";
	}
	
	@GetMapping("/view")
	public void viewShopDetail(Integer objectno, Model model) {
		Shop shop = adminService.getShopDetailByobjectno(objectno);
		List<ShopFile> shopFile = adminService.getshopFileByobjectno(objectno);
		
		model.addAttribute("shop", shop);
		model.addAttribute("shopFile", shopFile);
	}
	
	@GetMapping("/change")
	public void changeShopDetail(Integer objectno, Model model) {
		Shop shop = adminService.getShopDetailByobjectno(objectno);
		List<ShopFile> shopFile = adminService.getshopFileByobjectno(objectno);
		
		model.addAttribute("shop", shop);
		model.addAttribute("shopFile", shopFile);
	}
	
	@PostMapping("/change")
	public String postChangeShopDetail(Integer objectno,
			@RequestParam(value = "file", required = false)List<MultipartFile> fileList //올릴 파일 리스트
			, Shop shop
			, @RequestParam(required = false) List<Integer> no // 취소된 파일 -1
			, @RequestParam(required = false) List<Integer> delete
			, @RequestParam(required = false) List<Integer> save
			) {
			
			if(shop.getShopcontent() == null || shop.getName() == null )
			return "redirect:/admin/shop/list";
			
			adminService.changeAndDeleteFile(delete,save);
	
			adminService.changeShop(shop,objectno);
			
			adminService.saveShopFiles(fileList, objectno, no);
			

			
			
			
			return "redirect:/admin/shop/list";
	
			
		}
		
	}
	
	


