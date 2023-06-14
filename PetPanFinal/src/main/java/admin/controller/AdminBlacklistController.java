package admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import admin.dto.Blacklist;
import admin.service.face.AdminService;
import member.dto.Member;
import member.service.face.MemberService;
import util.AdminPaging;

@RequestMapping("/admin")
@Controller
public class AdminBlacklistController {
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired AdminService adminService;
	@Autowired MemberService memberService;
	
	//메인페이지 매핑
	@GetMapping("/")
	public String goMain1() {
		
		return "redirect:/admin/main";
		
	}
	//메인페이지 매핑2
	@GetMapping("/main")
	public void goMain2() {
	
	}


	//blacklist 수정
	@GetMapping("/blacklist/list")
	public void viewblackBoard(@RequestParam(defaultValue = "0") int curPage, Model model) {
		AdminPaging paging = new AdminPaging();
		
		paging = adminService.getBlacklistPage(curPage);
		
		List<Map<String,Object>> list = adminService.getBlacklistBoard(paging);
		
	
		
		logger.info("paging = {}", list);
		
		model.addAttribute("list",list);
		model.addAttribute("paging",paging);
	}

	
	//블랙리스트 삭제 (한번에)
	@GetMapping("/blacklist/delete")
	public String blacklistdelete(@RequestParam(value="delete",required=false) List<String> delete) {
		
		adminService.deleteblacklist(delete);
		
		
		return "redirect:./list";
		
	}

	
	//블랙리스트 insert
	@GetMapping("/blacklist/insert")
	public void blacklistinsert(@RequestParam(required=false)int userno,Model model) {
		
		
		if(userno != 0) {
		model.addAttribute("userno",userno);
		}
	}
	
	//블랙리스트 insert
	@PostMapping("/blacklist/insert")
	public String blacklistdeleteproc(Blacklist blacklist) {
		
		if (blacklist.getUserno() !=0) {
		logger.info("blacklist = {}", blacklist);
		adminService.insertblacklist(blacklist);
		}
		return "redirect:./list";

	}
	@GetMapping("/blacklist/deleteOne")
	@ResponseBody
	public void blacklistdeleteOne(int userno,Model model) {
		
		adminService.deleteblacklistOne(userno);
		
	}
	
	
	
}
