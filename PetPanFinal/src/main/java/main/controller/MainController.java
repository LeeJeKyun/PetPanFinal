package main.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import member.dto.Member;
import member.dto.Pet;
import member.dto.PetFile;
import member.service.face.MemberService;


@Controller
public class MainController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private MemberService memberService;
	

	@GetMapping("/")
	public String main(
			HttpSession session
			, Member member
			, Model model
			, PetFile petFile
			) {
		logger.info("로그인 성공 메인");
		
		if( session.getAttribute("login") != null) {
		
		member.setUserNo((int)session.getAttribute("userno"));
		
		
		Member detail = memberService.userDetail(member);
		model.addAttribute("detail", detail);
		logger.info("detail {}", detail);
		
		// userno로 펫 정보 조회하기
		List<Pet> petInfo = memberService.petInfo(member);
		model.addAttribute("petInfo", petInfo);
		logger.info("petInfo : {}", petInfo);
		
		// 펫이 여러마리
		for (Pet p : petInfo) {
			petFile.setPetNo(p.getPetNo());
			
		
		logger.info("petFile: {}", petFile);
		
		// petno로 펫 사진 불러오기
		List<PetFile> petDetail = memberService.petFile(petFile);
		model.addAttribute("petDetail", petDetail);
		logger.info("petDetail : {}" , petDetail);
		
		}
		
	}
		return "/main";

	}	
	
	
	
	
	
	
	
	
	
	
	
}
