package board.controller;

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

import board.dto.Hospital;
import board.dto.HospitalFile;
import board.service.face.BoardService;
import member.dto.Member;
import util.Paging;

@Controller
@RequestMapping("/board/hospital")
public class HospitalController {
private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired BoardService boardService;
	
	@GetMapping("/list")
	public void hospitalListPath(Paging paging, Model model, HttpSession session) {
		if(null != session.getAttribute("userno") ) {
			int userNo = (int)session.getAttribute("userno");
	
			Member user = new Member();
			
			user = boardService.getUserInfo(userNo);
			
			logger.info("user {}", user);
			
			model.addAttribute("user", user);
		}
	}
	@GetMapping("/enroll")
	public String hospitalEnrollPath(HttpSession session) {
		if(null == session.getAttribute("login")) {
			return "redirect:./list";
		}
		return "./board/hospital/enroll";
	}
	@PostMapping("/enroll")
	public String hospitalEnroll(@RequestParam(required = false)List<MultipartFile> fileList
									, @RequestParam(required = false)List<Integer> no
									, Hospital hospital
									, HttpSession session) {
		logger.info("open {}",hospital.getOpen());
		logger.info("close {}",hospital.getClose());
		
		hospital.setUserNo((int)session.getAttribute("userno"));
		logger.info("hospital {}", hospital);
		logger.info("hospital files {}", fileList);
		
		//boardService.enrollHospital(fileList, no, hospital);
		
		
		return "redirect:./list";
	}
	@GetMapping("/detail")
	public void hospitalDetail(int hospitalNo, Model model) {
		
	}
}
