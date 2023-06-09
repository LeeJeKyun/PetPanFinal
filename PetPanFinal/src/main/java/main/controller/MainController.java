package main.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.dto.Board;
import main.service.face.MainService;
import member.dto.Member;
import member.dto.Pet;
import member.dto.PetFile;
import member.service.face.MemberService;
import util.HospitalPaging;


@Controller
public class MainController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private MemberService memberService;
	@Autowired MainService mainService;

	@GetMapping("/")
	public String main(
			HttpSession session
			, Member member
			, Model model
			, PetFile petFile
			, Board board
			,@RequestParam(defaultValue = "1") Integer curPage
				// 검색어, 반경
			, @RequestParam(required = false) String search
			, @RequestParam(required = false, defaultValue = "0") Integer radius
			, @RequestParam(defaultValue = "N") char rodent
			, @RequestParam(defaultValue = "N") char birds
			, @RequestParam(defaultValue = "N") char mammalia
			, @RequestParam(defaultValue = "N") char reptile
			) {
		logger.info("로그인 성공 메인");
		
		if( session.getAttribute("login") != null) {
		
		member.setUserNo((int)session.getAttribute("userno"));
		
		Member detail = memberService.userDetail(member);
		model.addAttribute("detail", detail);
		
		// userno로 펫 정보 조회하기
		List<Pet> petInfo = memberService.petInfo(member);
		model.addAttribute("petInfo", petInfo);
		
		// 펫이 여러마리
		for (Pet p : petInfo) {
			petFile.setPetNo(p.getPetNo());
		
		// petno로 펫 사진 불러오기
		List<PetFile> petDetail = memberService.selectPetprofile(petFile);
		model.addAttribute("petDetail", petDetail);
		
		}
		
	}
	
		
		// 자유게시판 인기순 조회
		List<Board> free = mainService.selectFree(board);
		
		// 중고게시판 인기순 조회
		List<Board> old = mainService.selectOld(board);
		
		
		List<Board> newFree = mainService.selectNewFree(board);
		List<Board> newOld = mainService.selectNewOld(board);
		List<Board> poom = mainService.selectPoom(board);
		
		
		
		
		model.addAttribute("free", free);
		model.addAttribute("old", old);
		model.addAttribute("newFree", newFree);
		model.addAttribute("newOld", newOld);
		model.addAttribute("poom", poom);
		
		
		
		
		
		//병원 검색
		HospitalPaging paging = new HospitalPaging();
		paging.setCurPage(curPage);
		paging.setSearch(search);
		
		paging.setRodent(rodent);
		paging.setBirds(birds);
		paging.setMammalia(mammalia);
		paging.setReptile(reptile);
		
		paging = mainService.getPaging(paging);
		
		// 병원 정보 가져오기
		List<Map<String, Object>> hospitalList  = mainService.getHospitalInfo(paging);
		logger.info(" hospitalList {} ", hospitalList);
		
		logger.info("병원 정보 {}", hospitalList);
		
		model.addAttribute("hospitalList", hospitalList);
		model.addAttribute("paging", paging);
		
		
		
		return "/main";
		
		
		
	}	
	
	
	
	
	
	
	
}
