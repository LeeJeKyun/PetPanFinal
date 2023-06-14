package admin.controller;

import java.util.HashMap;
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

import admin.service.face.AdminService;
import util.AdminPaging;

@Controller
@RequestMapping("/admin/buyer")
public class AdminBuyerController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired AdminService adminService;
	
	@GetMapping("/list")
	public void getBuyerList(Model model,
			@RequestParam(defaultValue = "1") int curPage,
			@RequestParam(required=false,defaultValue = "")String search
			){
		AdminPaging adminPaging = new AdminPaging();
		
		adminPaging = adminService.getPageBuyer(curPage,search);
		List<HashMap<String,Object>> list = adminService.getBuyerList(adminPaging);
	
		
		model.addAttribute("list", list);
		model.addAttribute("paging", adminPaging);
		
	}
	
	@GetMapping("/complete")
	public String completeBuyerList(@RequestParam(value="delete",required=false) List<String> delete) {
		adminService.completeCheckedBuyer(delete);
		
		
		return "redirect:/admin/buyer/list";
	}
}
