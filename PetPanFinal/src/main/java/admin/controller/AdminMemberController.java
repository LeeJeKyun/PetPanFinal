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
	
	//멤버 리스트 가져오기
	@GetMapping("/member/list")
	public void viewUserBoard(@RequestParam(defaultValue = "0") int curPage, Model model, String search) {

		AdminPaging paging = new AdminPaging();
		
		paging = adminService.getSearchMemberPage(curPage,search);
		
		logger.info("{}",paging);
		
		List<Member> list = adminService.getsearchMemberBoard(paging,search);
		
		paging.setSearch(search);
		
		model.addAttribute("list",list);
		model.addAttribute("paging",paging);
	}
	

	//멤버 개인 보여주기
	@GetMapping("/member/view")
	public void viewMember(int userno, Model model ) {
		
		
		Member member = new Member();
		member.setUserNo(userno);
		
		member = memberService.userDetail(member);
		System.out.println(member);
		int isBlack = 0;
		isBlack = adminService.isBlack(userno);
		System.out.println("isBlack : " + isBlack);
		
		model.addAttribute("member",member);
		model.addAttribute("isBlack",isBlack);
		
	}
	//멤버가 블랙인가
	@GetMapping("/member/isblack")
	public void isblack(int userno, Model model ) {
		

		int isBlack = 0;
		isBlack = adminService.isBlack(userno);
		model.addAttribute("checkBlack",isBlack);
		
	}
	//멤버 업데이트 get
	@GetMapping("/member/update")
	public void updateMember(int userno, Model model ) {
		
		
		Member member = new Member();
		member.setUserNo(userno);
		
		member = memberService.userDetail(member);
		
		
		model.addAttribute("member",member);
		
	}
	//멤버 업데이트 실행
	@PostMapping("/member/update")
	public String updateMemberPost(Member member, @RequestParam("address") String jibunAddress) {
		
		
		memberService.getKakaoApiFromAddress(jibunAddress);
		HashMap<String, String> XYMap = memberService.getXYMapfromJson( memberService.getKakaoApiFromAddress( jibunAddress) );
		
		
		member.setLatitude(XYMap.get("x"));
		member.setLongitude(XYMap.get("y"));
		
		logger.info("memberup : {}" , member);
		
		adminService.updateMember(member);
		
		
		return "redirect:./list" ;
		
	}
	
	@GetMapping("/member/appadmin")
	public String updateAdmin(int userno ) {
		
		adminService.appointmentAdmin(userno);
		
		return "redirect:./view?userno=" + userno;
	}

	
	
}
