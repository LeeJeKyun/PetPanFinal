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
import admin.dto.ReportComment;
import admin.dto.ReportObject;
import admin.service.face.AdminService;
import member.dto.Member;
import util.AdminPaging;

@Controller
@RequestMapping("/admin/reportshop")
public class AdminReportShopController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired AdminService adminService;
	
	@GetMapping("/list")
	public void reporetShoplist(
			@RequestParam(defaultValue = "1") int curPage, 
			Model model, 
			@RequestParam(required=false,defaultValue = "")String search
			) {
		AdminPaging paging = new AdminPaging();
		
		paging = adminService.getPagereportShop(curPage,search);
		
//		logger.info("paging = {}", paging);
		
		List<ReportObject> list = adminService.getReportShopList(paging);
		
		model.addAttribute("search",search);
		model.addAttribute("list",list);
		model.addAttribute("paging",paging);
		
	}
	
	@GetMapping("/view")
	public void reportView(
			@RequestParam(defaultValue = "0")Integer objreportNo,
			Model model,
			@RequestParam(required=false)Blacklist blacklist,
			ReportObject reportObject,
			Member getmember
			
			) {
		reportObject = adminService.getReportObject(objreportNo);
		getmember = adminService.getShopReportMember(reportObject);
		int getblack = adminService.findBlack(getmember.getUserNo());
	
		model.addAttribute("list",reportObject);
		model.addAttribute("getmember", getmember);
		model.addAttribute("getblack", getblack);
	}
	
	@PostMapping("/view/delete")
	public String deleteAndBlackshop(
			Integer objreportNo,@RequestParam(required=false) Integer objectNo, @RequestParam(required=false) Integer userNo
			, String reason
			){
		
		adminService.changeObjReportAndAddBlack(objreportNo,objectNo,userNo,reason);
		
		return "redirect:/admin/reportshop/list";
		
	}
	
	@GetMapping("/delete")
	public String deleteReportBoard(@RequestParam(value="delete",required=false) List<String> delete) {
		adminService.changeObjReport(delete);
		
		return "redirect:/admin/reportshop/list";
	}

}
