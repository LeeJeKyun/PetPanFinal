package member.controller;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import member.dto.Member;
import member.service.face.MemberService;


@Controller
@RequestMapping("/member")
public class MemberController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private MemberService memberService;
	
	
	
	@RequestMapping("/naverLogin")
	public void naverLogin() {
				
	}

	@RequestMapping("/kakaoLogin")
	public void kakaoLogin() {
		
	}
	
	@GetMapping("/login/login")
	public void login() {
		logger.info("Login");
	}
	
	@PostMapping("/login/login")
	public String loginProc( 
			
			Member member
			, HttpSession session
			) {
		
//		logger.info("/login/login");
		
//		logger.info("{}", member);
		
		
		if( memberService.login( member ) ) {
			
			session.setAttribute("login", true );
			session.setAttribute("loginid", member.getUserId() );
			session.setAttribute("userno", member.getUserNo());
			session.setAttribute("userName", member.getUserName());
			
//			logger.info("session: {} ", member.getUserNo());
			
			return "./main";
		}
		
		
		return "./main";
	}
	
	
	@GetMapping("/login/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
		
	}

	
	@GetMapping("/login/join")
	public void join() {
		logger.info("/login/join");
		
	}
	
	@PostMapping("/login/join")
	public String joinProc(
			 Member member
//			String zipCode
			, @RequestParam("address") String jibunAddress
			) {
		
		logger.info("/login/join");
		logger.info("member: {}", member);
		logger.info("지번: {}", jibunAddress);
//		logger.info("zipCode: {}", zipCode);
		
		memberService.getKakaoApiFromAddress( jibunAddress);
		
		HashMap<String, String> XYMap = memberService.getXYMapfromJson( memberService.getKakaoApiFromAddress( jibunAddress) );
		
		member.setLatitude(XYMap.get("x"));
		member.setLongitude(XYMap.get("y"));
		
		logger.info("memberlast : {} ", member);

		memberService.insertJoin( member );
		
		
		
		
		
		return "/main";
	}
	
	
	@GetMapping("/login/mypage")
	public void mypage(
			HttpSession session
			,Model model
			) {
		
		
		model.addAttribute("detail", memberService.selectDetail((String)session.getAttribute("loginId")));
		
	}


	
//	@GetMapping("/test")
//	public void test(
//			String jibunAddress
//			) {
//		
//		memberService.getKakaoApiFromAddress( jibunAddress);
//		
//		
//		memberService.getXYMapfromJson( memberService.getKakaoApiFromAddress( jibunAddress) );
//		
//	}
	
	
	
	
	
	
	
	
	

	
	
	

	
	
}
