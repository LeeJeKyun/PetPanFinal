package member.controller;

import java.util.HashMap;

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

import member.dto.Member;
import member.dto.Pet;
import member.dto.PetFile;
import member.service.face.MemberService;


@Controller
@RequestMapping("/member")
public class MyPageController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private MemberService memberService;
	
	@GetMapping("/mypage/mypage")
	public void mypage(
			HttpSession session
			, Member member
			, Model model
			) {
		
		
		member.setUserNo((int)session.getAttribute("userno"));
		
//		Member detail = memberService.userDetail(member);
//		model.addAttribute("info", member);
		
		Member detail = memberService.userDetail(member);
		model.addAttribute("detail", detail);
		
		logger.info("detail : {}" , detail);
		
	}

	@GetMapping("/mypage/myprofile")
	public void myprofile(
			HttpSession session
			, Member member
			, Model model
			) {
		member.setUserNo((int)session.getAttribute("userno"));
		
		Member detail = memberService.userDetail(member);
		model.addAttribute("detail", detail);
		
		
	}
	
	@PostMapping("/mypage/myprofile")
	public String myprofileUpdate(
			HttpSession session
			, Member member
			, Model model
			, @RequestParam("address") String jibunAddress
			) {
		
		member.setUserNo((int)session.getAttribute("userno"));
		
		logger.info("member profile : {}", member);

		memberService.getKakaoApiFromAddress( jibunAddress);
		
		HashMap<String, String> XYMap = memberService.getXYMapfromJson( memberService.getKakaoApiFromAddress( jibunAddress) );
		
		member.setLatitude(XYMap.get("x"));
		member.setLongitude(XYMap.get("y"));
		
		memberService.updateMember(member);
	
		
		return "redirect:./mypage";
	}
	
	@GetMapping("/mypage/petinfo")
	public void petinfo(			
			HttpSession session
			, PetFile petFile
			, Pet pet
			, Member member
			) {
		member.setUserNo((int)session.getAttribute("userno"));
		
		memberService.petInfo(pet, petFile);
		

	}
	
	
	
	
	
	@GetMapping("/mypage/content")
	public void content(
			HttpSession session
			, Member member
			, Model model

			) {
		
		member.setUserNo((int)session.getAttribute("userno"));
		
		
		Member detail = memberService.userDetail(member);
		model.addAttribute("detail", detail);
	}
	
	
	
}
