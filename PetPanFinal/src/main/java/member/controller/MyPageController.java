package member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import board.dto.Board;
import board.service.face.BoardService;
import member.dto.Hospital;
import member.dto.Member;
import member.dto.Pet;
import member.dto.PetFile;
import member.service.face.MemberService;
import shop.dto.Review;


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
			, PetFile petF
			) {
		
		// 세션에 담긴 userno 가져오기
		member.setUserNo((int)session.getAttribute("userno"));
		
		// userno로 유저 정보 조회하고 모델에 담기
		Member detail = memberService.userDetail(member);
		model.addAttribute("detail", detail);
		
		// userno로 펫 정보 조회하기
		List<Pet> petInfo = memberService.petInfo(member);
		model.addAttribute("petInfo", petInfo);
		logger.info("petInfo : {}", petInfo);
		
		List<PetFile> petFileList = new ArrayList<>();
		
		// 펫이 여러마리일 경우 리스트로 펫 정보 보여주기
		for (Pet p : petInfo) {
			PetFile petFile = new PetFile();
			petFile.setPetNo(p.getPetNo());
			logger.info("petFile: {}", petFile);
			petFileList.add(petFile);

			session.setAttribute("petNo", petFile.getPetNo());
		}
		
		// petno로 펫 사진 불러오기
		List<PetFile> petDetail = memberService.petFile(petFileList);
		model.addAttribute("petInfo", petInfo);
		model.addAttribute("petDetail", petDetail);
		
		
		logger.info("petDetail : {}" , petDetail);
		
//		memberService.deletePetPhoto(petF);
//		memberService.deletePetInfo(pet);

		
		
	}
	
	@GetMapping("/mypage/delete")
	public void delete(
			Pet pet
			, PetFile petFile) {
	}
	
	@PostMapping("/mypage/delete")
	public String deleteProc(
			Pet pet
			, PetFile petFile
			, HttpSession session) {
		
		
		memberService.deletePetPhoto(petFile);
		memberService.deletePetInfo(pet);
		
		return "redirect:/member/mypage/mypage";
	}
	
	

	@GetMapping("/mypage/petUpdate")
	public void petUpdate(
			HttpSession session
			, Member member
			, Model model
			, Pet pet
			
			) {
		
		// 세션에 담긴 userno 가져오기
//		member.setUserNo((int)session.getAttribute("userno"));
		
		
//		pet정보 가져오기
		
		Pet p = memberService.selectPetByPetNo(pet);
		
//		pet 파일 가져오기
		
		PetFile pf = memberService.selectPetFileByPet(pet);
		
		model.addAttribute("Info", p);
		model.addAttribute("Detail", pf);
		
		
	
		
	}
	
	
	
	@PostMapping("/mypage/petUpdate")
	public String petUpdatePost(
			HttpSession session
			, Pet pet
			, MultipartFile petFile
			) {
		
		logger.info("petfile---------------------{}",petFile);
		logger.info("pet---------------------{}",pet);
		
		
		memberService.petUpdate(pet, petFile);

		
		
		
		
		return "redirect:./mypage";
		
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
			, Model model

			) {
		
		List<Board> content = memberService.myContent((int)session.getAttribute("userno"));
		
		
		logger.info("내가 쓴 게시글 : {}", content);
		model.addAttribute("content", content);
		
	}
	
	
	
	@GetMapping("/mypage/comment")
	public void comment(
			HttpSession session
			, Member member
			, Model model
			
			) {
		
		int userno = (int)session.getAttribute("userno");
		
//		List<Comment> comment = memberService.myComment((int)session.getAttribute("userno"));	
		List<Map<String, Object>> comment = memberService.myComment(userno);	
		
//		List<Map<String, Object>> list = fileUploadService.getPackList();

		model.addAttribute("comment", comment);
		
		logger.info("내가 쓴 댓글 : {}", comment);

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
	
	@GetMapping("/mypage/review")
	public void review(Model model,HttpSession session) {
		
		List<Review> list = memberService.myreview((int)session.getAttribute("userno"));
		
		logger.info("review: {}", list);
		model.addAttribute("list", list);
		
	}
	


	
	
	
}
