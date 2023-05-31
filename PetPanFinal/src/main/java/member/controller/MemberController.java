package member.controller;


import java.util.HashMap;

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
import member.dto.Pet;
import member.dto.PetFile;
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
			, String sosId
			) {
		
//		logger.info("/kakaoLogin ");
//		logger.info("code: {}", code);

		String access_Token = kakaoService.getAccessToken(code);
		
//		logger.info("Membercontroller access_token : {}" + access_Token);
		HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
		
		member.setSuserno((String)userInfo.get("id"));

		sosId = (String)userInfo.get("id");
		Member sMember = memberService.selectSuser(sosId);
		
		logger.info("sMember: {}", sMember);
		
		boolean kakao = memberService.selectKakao(member);
		
		
		if( kakao ) {
			
	    	session.setAttribute("login", true);
	        session.setAttribute("userId", userInfo.get("id"));
	        session.setAttribute("access_Token", access_Token);
	        session.setAttribute("userno", sMember.getUserNo());
			
			return "./main";
			
		} else {
			
//			logger.info("Info {}", userInfo);
			logger.info("info {}",userInfo.get("id"));
	        session.setAttribute("userId", userInfo.get("id"));
	        session.setAttribute("access_Token", access_Token);
			
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
			, Model model
			
			) {
		
		logger.info("/login/login");
		
		if( memberService.login( member ) ) {
			
			Member member2 = memberService.selectlogin(member);
			boolean black = memberService.selcetBlack(member2);
			boolean hospital = memberService.selectHospital(member2);

			
			// 블랙리스트 로그인 실패
			if( black ) {
				
				logger.info("로그인 실패");
				session.invalidate();
				
			}
			
			// 로그인 성공
			if( !black ){
			
				// 일반회원 로그인 성공
				session.setAttribute("login", true );
				session.setAttribute("userno", member2.getUserNo());

				// 병원 관계자 로그인
				if( hospital ) {
					session.setAttribute("hospital", true);
				}
			}
			
			logger.info("hospital : {}", hospital);
			Member detail = memberService.userDetail(member2);
			model.addAttribute("info", member);

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
		
		memberService.getKakaoApiFromAddress( jibunAddress);
		
		HashMap<String, String> XYMap = memberService.getXYMapfromJson( memberService.getKakaoApiFromAddress( jibunAddress) );
		
		member.setLatitude(XYMap.get("x"));
		member.setLongitude(XYMap.get("y"));
		
		memberService.insertJoin( member );
		
		
		return "/main";
		
	}
	

	
	@GetMapping("/login/socialjoin")
	public void socialjoin(String sosId, HttpSession session) {
		logger.info("/login/socialjoin");
		sosId = (String) session.getAttribute("userId");
		
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
		
		logger.info("/login/socialjoin");
		
		
		memberService.getKakaoApiFromAddress( jibunAddress);
		
		HashMap<String, String> XYMap = memberService.getXYMapfromJson( memberService.getKakaoApiFromAddress( jibunAddress) );
		
		member.setLatitude(XYMap.get("x"));
		member.setLongitude(XYMap.get("y"));
		
		
		memberService.insertkakaoJoin( member, sosId );
		
		return "/main";
		
	}
	
	
	
	
	
	


	
	
}
