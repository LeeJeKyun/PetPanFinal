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

import admin.dto.Blacklist;
import admin.service.face.AdminService;
import util.AdminPaging;

@RequestMapping("/admin")
@Controller
public class AdminBlacklistController {
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired AdminService adminService;
	
	@GetMapping("/")
	public String goMain1() {
		
		return "redirect:/admin/main/main";
		
	}
	
	@GetMapping("/main")
	public String goMain2() {
		
		return "redirect:/admin/main/main";
	}
	
	@GetMapping("/main/main")
	public void goMain3() {
		
	} 

	//blacklist 수정
	@GetMapping("/blacklist/list")
	public void viewblackBoard(@RequestParam(defaultValue = "0") int curPage, Model model) {
//		logger.info("/reportboard [GET}");
//		logger.info("curPage = {}", curPage);
		AdminPaging paging = new AdminPaging();
		
		paging = adminService.getPage(curPage);
		
//		logger.info("paging = {}", paging);
		
		List<Blacklist> list = adminService.getBlacklistBoard(paging);
		
		model.addAttribute("list",list);
		model.addAttribute("paging",paging);
	}

	

	@GetMapping("/blacklist/delete")
	public String blacklistdelete(@RequestParam(value="delete",required=false) List<String> delete) {
		
//		logger.info("delete = {}", delete);
		adminService.deleteblacklist(delete);
		
		
		return "redirect:/admin/blacklist/board";
		
	}
	
	@GetMapping("/blacklist/insert")
	public void blacklistinsert() {		
		
	}
	
	
	@PostMapping("/blacklist/insert")
	public void blacklistdeleteproc(Blacklist blacklist) {
		
		
		logger.info("blacklist = {}", blacklist);
		adminService.insertblacklist(blacklist);

	}
	
	
	
	
}
