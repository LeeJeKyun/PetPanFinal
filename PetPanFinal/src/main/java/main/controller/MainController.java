package main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@GetMapping("/")
	public String main() {
//		logger.info("메인");
		
		return "/main";
	}
	
	@GetMapping("/portone")
	public void test() {
		
	}
	

	
}
