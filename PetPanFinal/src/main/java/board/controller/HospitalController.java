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

import board.dto.HospitalFile;
import board.service.face.BoardService;
import util.Paging;

@Controller
@RequestMapping("/board/hospital")
public class HospitalController {
private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired BoardService boardService;
	
	@GetMapping("/list")
	public void hospitalListPath(Paging paging, Model model) {
		
	}
	@GetMapping("/enroll")
	public void hospitalEnrollPath(HttpSession session) {
		
	}
	@PostMapping("/enroll")
	public String hospitalEnroll(@RequestParam(required = false)List<HospitalFile> fileList
									, @RequestParam(required = false)List<Integer> no
									, @RequestParam(required = false) List<Integer> spe
									, String open, String close) {
		logger.info("open {}",open);
		logger.info("close {}",close);
		
		
		return "redirect:./list";
	}
	@GetMapping("/detail")
	public void hospitalDetail(int hospitalNo, Model model) {
		
	}
}
