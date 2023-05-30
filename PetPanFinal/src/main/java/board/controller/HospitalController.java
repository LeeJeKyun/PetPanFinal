package board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import board.service.face.BoardService;
import util.Paging;

@Controller
@RequestMapping("/hospital")
public class HospitalController {
private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired BoardService boardService;
	
	@GetMapping("/list")
	public void hospitalList(Paging paging, Model model) {
		
	}
	@GetMapping("/detail")
	public void hospitalDetail(int hospitalNo, Model model) {
		
		
		
	}
}
