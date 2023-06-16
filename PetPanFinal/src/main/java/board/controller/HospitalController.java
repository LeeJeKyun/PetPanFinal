package board.controller;

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

import board.dto.Hospital;
import board.dto.HospitalFile;
import board.service.face.BoardService;
import member.dto.Member;
import util.HospitalPaging;
import util.Paging;

@Controller
@RequestMapping("/board/hospital")
public class HospitalController {
private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired BoardService boardService;
	
	@GetMapping("/list")
	public void hospitalListPath(
								@RequestParam(defaultValue = "1") Integer curPage
								, Model model, HttpSession session
									// 검색어, 반경
								, @RequestParam(required = false) String search
								, @RequestParam(required = false, defaultValue = "0") Integer radius
								, @RequestParam(defaultValue = "N") char rodent
								, @RequestParam(defaultValue = "N") char birds
								, @RequestParam(defaultValue = "N") char mammalia
								, @RequestParam(defaultValue = "N") char reptile
								) {
		logger.info("search 검색 {} ", search);
		logger.info("radius 반경 {} ", radius);
		int userNo = -1;
		if(null != session.getAttribute("userno")) {
			userNo = (int)session.getAttribute("userno");
			
		}
		logger.info("rodent {}", rodent);
		logger.info("birds {}", birds);
		logger.info("mammalia {}", mammalia);
		logger.info("retile {}", reptile);
		// 검색한 페이징 객체 가져오기 + userNo, + radius
		HospitalPaging paging = new HospitalPaging();
		paging.setCurPage(curPage);
		paging.setUserNo(userNo);
		paging.setRadius(radius);
		paging.setSearch(search);
		
		paging.setRodent(rodent);
		paging.setBirds(birds);
		paging.setMammalia(mammalia);
		paging.setReptile(reptile);
		
		logger.info("병원 전 paging {}", paging);
		paging = boardService.getHospitalPaging(paging);
		
		logger.info("병원 후 paging {}", paging);
		
		//병원 정보 가져오기 
		List<Map<String, Object>> hospitalList  = boardService.getHospitalInfo(paging);
		logger.info(" hospitalList {} ", hospitalList);
		
		// 로그인한 사용자 좌표 가져오기
		Map<String, String> map = boardService.getUserLoc(session);
		logger.info("user 좌표 {}", map );
		
		model.addAttribute("userLoc", map);
		model.addAttribute("hospitalList", hospitalList);
		logger.info("paging {}", paging);
		model.addAttribute("paging", paging);
	}
//	@GetMapping("/enroll")
//	public String hospitalEnrollPath(HttpSession session) {
//		if(null == session.getAttribute("login")) {
//			return "redirect:./list";
//		}
//		return "./board/hospital/enroll";
//	}
//	@PostMapping("/enroll")
//	public String hospitalEnroll(@RequestParam(required = false)List<MultipartFile> fileList
//									, @RequestParam(required = false)List<Integer> no
//									, Hospital hospital
//									, HttpSession session) {
//		logger.info("open {}",hospital.getOpen());
//		logger.info("close {}",hospital.getClose());
//		
//		hospital.setUserNo((int)session.getAttribute("userno"));
//		logger.info("hospital {}", hospital);
//		logger.info("hospital files {}", fileList);
//		
//		//boardService.enrollHospital(fileList, no, hospital);
//		
//		
//		return "redirect:./list";
//	}
	@GetMapping("/detail")
	public void hospitalDetail(int hospitalNo, Model model, HttpSession session) {
		int userNo = -1;
		if(null != session.getAttribute("userno")) {
			userNo = (int)session.getAttribute("userno");
		}
		Map<String, Object> map = boardService.getHospitalDetail(hospitalNo, userNo);
		
		logger.info("map {}", map);
		
		model.addAttribute("map", map);
	}
	
	@GetMapping("/modifyinfo")
	public void modifyHospitalInfoGet(int hospitalno, HttpSession session, Model model) {
		// 기존의 병원 정보 가져오기
		Map<String, Object> map = boardService.getHospitalInfo(hospitalno, (Integer)session.getAttribute("userno"));
		
		logger.info("병원 정보 {}",map);
		
		model.addAttribute("map", map);
	}
	@PostMapping("/modifyinfo")
	public String modifyHospitalInfo(Hospital hospital
									, MultipartFile file
									, HttpSession session) {
		hospital.setUserNo((int)session.getAttribute("userno"));
		
		boardService.modifyHospitalInfo(hospital, file);
		
		return "redirect:./list";
	}
}
