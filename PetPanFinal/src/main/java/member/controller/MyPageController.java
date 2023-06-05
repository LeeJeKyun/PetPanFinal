package member.controller;

import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import board.service.face.BoardService;
import member.dto.Hospital;
import member.dto.Member;
import member.dto.Pet;
import member.dto.PetFile;
import member.service.face.MemberService;


@Controller
@RequestMapping("/member")
public class MyPageController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private MemberService memberService;
	@Autowired private BoardService boardService;
	
	@GetMapping("/mypage/mypage")
	public void mypage(
			HttpSession session
			, Member member
			, Model model
			, Pet pet
			, PetFile petFile
			) {
		
		
		member.setUserNo((int)session.getAttribute("userno"));
		
		Member detail = memberService.userDetail(member);
		model.addAttribute("detail", detail);
//		logger.info("detail : {}" , detail);
		
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

	@GetMapping("/mypage/myprofile")
	public void myprofile(
			HttpSession session
			, Member member
			, Model model
			) {
		// 유저 정보 조회
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

		// 회원 정보 수정
		memberService.getKakaoApiFromAddress( jibunAddress);
		
		HashMap<String, String> XYMap = memberService.getXYMapfromJson( memberService.getKakaoApiFromAddress( jibunAddress) );
		
		member.setLatitude(XYMap.get("x"));
		member.setLongitude(XYMap.get("y"));
		
		memberService.updateMember(member);
	
		
		return "redirect:./mypage";
	}
	
	@GetMapping("/mypage/petinfo")
	public void petinfo() {

	}
	
	@PostMapping("/mypage/petinfo")
	public String petinfo(			
			HttpSession session
			, Member member
			, @RequestParam("file") MultipartFile petFile
			, Pet pet
			) {
		
		logger.info("userno {}", session.getAttribute("userno"));

		pet.setUserNo((int)session.getAttribute("userno"));
		
		
		
		// 펫 번호
		int petNo = memberService.pet(pet, petFile);
		
		return "redirect:./petinfo";
		
		
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
	
	@GetMapping("/mypage/comment")
	public void comment(
			HttpSession session
			, Member member
			, Model model
			
			) {
		
		member.setUserNo((int)session.getAttribute("userno"));
		
		
		Member detail = memberService.userDetail(member);
		model.addAttribute("detail", detail);
	}
	
	
	@GetMapping("/mypage/hospital")
	public void hospital() {
	}
	
	@PostMapping("/mypage/hospital")
	public String hospitalPost(
			Hospital hospital 
			, HttpSession session
			, Member member
			, @RequestParam(name = "file", required = false)List<MultipartFile> fileList
			, @RequestParam(required = false)List<Integer> no
			) {
		
		hospital.setUserNo((int)session.getAttribute("userno"));
		
		logger.info("hospital: {}", hospital);
		logger.info("fileList  컨: {}", fileList);
		
		int hospitalNo = memberService.insertHospital( hospital );
		hospital.setHospitalNo(hospitalNo);
		boardService.enrollHospital(fileList, no, hospital);
		
		return "redirect:./mypage";
	}
	
	
	
	
	
}
