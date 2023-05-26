package member.controller;



import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import member.dto.Member;
import member.service.face.KakaoService;
import member.service.face.MemberService;


@Controller
@RequestMapping("/member")
public class MemberController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private KakaoService kakaoService;
	@Autowired private MemberService memberService;
	
	
	@RequestMapping("/naverLogin")
	public void naverLogin() {
				
	}

	
	@GetMapping("/login/login")
	public void login() {
		logger.info("Login");
	}
	
	
	@GetMapping("/kakaoLogin")
	public String kakaoLogin(
			@RequestParam("code")String code
			, HttpSession session
			, Member member
			) {
		
		logger.info("/kakaoLogin ");
		logger.info("code: {}", code);

		String access_Token = kakaoService.getAccessToken(code);
		
		logger.info("Membercontroller access_token : {}" + access_Token);
		HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
		
		member.setSuserno((String)userInfo.get("id"));
		boolean kakao = memberService.selectKakao(member);
		
		
		if( kakao ) {
			
	    	session.setAttribute("login", true);
	        session.setAttribute("userId", userInfo.get("id"));
	        session.setAttribute("access_Token", access_Token);
			
			return "./main";
			
		} else {
			
//			logger.info("Info {}", userInfo);
			logger.info("info {}",userInfo.get("id"));
	        session.setAttribute("userId", userInfo.get("id"));
	        session.setAttribute("access_Token", access_Token);
	        
	        
//			memberService.insertkakaoJoin( member );
			
		}
		
////	클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
//	    if (userInfo.get("email") != null) {
//	    	session.setAttribute("login", true);
//	        session.setAttribute("userId", userInfo.get("id"));
//	        session.setAttribute("access_Token", access_Token);
//	    }

	    return "redirect:./login/socialjoin";

		
			
	}
	
	
	@PostMapping("/login/login")
	public String loginProc( 
			
			Member member
			, HttpSession session
			, HttpServletResponse resp
			, HttpServletRequest req
			
			
			) {
		
		logger.info("/login/login");
		
		logger.info("{}", member);
		
		
		if( memberService.login( member ) ) {
			
			Member member2 = memberService.selectlogin(member);
			boolean black = memberService.selcetBlack(member2);
			
			if( black ) {
				
				logger.info("로그인 실패");
				
				session.invalidate();
				
			} if( !black ){	
			
				session.setAttribute("login", true );
				session.setAttribute("loginid", member2.getUserId() );
				session.setAttribute("userno", member2.getUserNo());
				session.setAttribute("userName", member2.getUserName());
				
				logger.info("session: {} ", member2.getUserNo());
				
				}
			
			return "./main";
		}
		
		
		
		return "./main";
	}
	
	
	@GetMapping("/login/logout")
	public String logout(HttpSession session
			) {
		
		
		
		
		
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
		
		memberService.getKakaoApiFromAddress( jibunAddress);
		
		HashMap<String, String> XYMap = memberService.getXYMapfromJson( memberService.getKakaoApiFromAddress( jibunAddress) );
		
		member.setLatitude(XYMap.get("x"));
		member.setLongitude(XYMap.get("y"));
		
		logger.info("memberlast : {} ", member);

		memberService.insertJoin( member );
		
		
//		kakaoService.getAccessToken(code);
//		member.setSuserno(code);
//		memberService.insertkakaoJoin( member );
		
		
		return "/main";
		
	}
	

	
	@GetMapping("/login/socialjoin")
	public void socialjoin(String sosId, HttpSession session) {
		logger.info("/login/socialjoin");
		sosId = (String) session.getAttribute("userId");
		logger.info("sosid: {}" , sosId);
		
	}
	
	
	
	@PostMapping("/login/socialjoin")
	public String socialjoinProc(
			Member member
			, @RequestParam("address") String jibunAddress
			, HttpSession session
			, String sosId
			) {
		
		sosId = (String) session.getAttribute("userId");
		
		// 세션 카카오아이디만 해제하기
		session.removeAttribute("sosId");
		
		logger.info("sosid: {}" , sosId);
		
		logger.info("/login/socialjoin");
		logger.info("member: {}", member);
		
		
		memberService.getKakaoApiFromAddress( jibunAddress);
		
		HashMap<String, String> XYMap = memberService.getXYMapfromJson( memberService.getKakaoApiFromAddress( jibunAddress) );
		
		member.setLatitude(XYMap.get("x"));
		member.setLongitude(XYMap.get("y"));
		
		logger.info("memberlast : {} ", member);
		
		
		memberService.insertkakaoJoin( member, sosId );
		
		return "/main";
		
	}
	
	
	

	
	
	
	
	
	@GetMapping("/login/mypage")
	public void mypage(
			HttpSession session
			,Model model
			) {
		
		
		model.addAttribute("detail", memberService.selectDetail((String)session.getAttribute("loginId")));
		
	}



	

	
	
}
