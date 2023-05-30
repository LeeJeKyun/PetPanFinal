package admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import admin.service.face.AdminService;
import member.dto.Member;
import util.AdminPaging;

@RequestMapping("/admin")
@Controller
public class AdminMemberController {
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired AdminService adminService;
	
	
	@GetMapping("/member/list")
	public void viewUserBoard(@RequestParam(defaultValue = "0") int curPage, Model model) {

		AdminPaging paging = new AdminPaging();
		
		paging = adminService.getPage(curPage);
		

		
		List<Member> list = adminService.getMemberBoard(paging);
		
		model.addAttribute("list",list);
		model.addAttribute("paging",paging);
	}
	
	@GetMapping("/member/search")
	public void viewsearchBoard(@RequestParam(defaultValue = "0") int curPage, String keyword, Model model ) {
		
		AdminPaging paging = new AdminPaging();
		
		paging = adminService.getPage(curPage);
		
		
		
		List<Member> list = adminService.getsearchMemberBoard(paging,keyword);
		
		model.addAttribute("list",list);
		model.addAttribute("paging",paging);
		
	}

	
	
}
