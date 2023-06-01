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
import member.dto.Member;
import member.service.face.MemberService;
import util.AdminPaging;

@RequestMapping("/admin")
@Controller
public class AdminMemberController {
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired AdminService adminService;
	@Autowired MemberService memberService;
	
	
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
	
	@GetMapping("/member/view")
	public void viewMember(int userno, Model model ) {
		
		
		Member member = new Member();
		member.setUserNo(userno);
		
		member = memberService.userDetail(member);
		
		
		model.addAttribute("member",member);
		
	}
	
	@GetMapping("/member/update")
	public void updateMember(int userno, Model model ) {
		
		
		Member member = new Member();
		member.setUserNo(userno);
		
		member = memberService.userDetail(member);
		
		
		model.addAttribute("member",member);
		
	}
	
	@PostMapping("/member/update")
	public String updateMemberPost(Member member, @RequestParam("address") String jibunAddress) {
		
		
		memberService.getKakaoApiFromAddress( jibunAddress);
		HashMap<String, String> XYMap = memberService.getXYMapfromJson( memberService.getKakaoApiFromAddress( jibunAddress) );
		
		
		member.setLatitude(XYMap.get("x"));
		member.setLongitude(XYMap.get("y"));
		
		logger.info("memberup : {}" , member);
		
		adminService.updateMember(member);
		
		
		return "redirect:./list" ;
		
	}

	
	
}
