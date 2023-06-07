package member.controller;


import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import member.dto.Member;
import member.service.face.MemberService;
import member.service.face.socialService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private socialService socialService;
	@Autowired private MemberService memberService;
	

	
	
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
		

		String access_Token = socialService.getAccessToken(code);
		
//		logger.info("Membercontroller access_token : {}" + access_Token);
		HashMap<String, Object> userInfo = socialService.getUserInfo(access_Token);
		
		member.setSuserno((String)userInfo.get("id"));

		sosId = (String)userInfo.get("id");
		Member sMember = memberService.selectSuser(sosId);
		
		logger.info("sMember: {}", sMember);
		
		boolean kakao = memberService.selectKakao(member);
		
		
		// 소셜no가 있으면 메인화면으로 이동
		if( kakao ) {
			
	    	session.setAttribute("login", true);
	        session.setAttribute("userId", userInfo.get("id"));
	        session.setAttribute("access_Token", access_Token);
	        session.setAttribute("userno", sMember.getUserNo());
			
			return "./main";
			
			
		// 소셜 no가 없으면 회원가입 창으로 이동
		} else {
			
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
	
	
	// 네이버
	
	@RequestMapping("/naverLogin")
	public ModelAndView naverLogin(
			HttpSession session ) {
		
		
		Map<String, Object> map = socialService.getStateApiUrl();
		
	    String apiURL = (String) map.get("apiURL");
	    String state = (String) map.get("state");
	    logger.debug("apiURL : {}",apiURL);
	    logger.info("state : {}", state);

	    // 세션 또는 별도의 저장 공간에 상태 토큰을 저장
	    session.setAttribute("state", state);
		
		
	    // 외부 URL로 retrun
	    return new ModelAndView("redirect:" + apiURL);
		
	}
	
	
	
	@GetMapping("/login/navercallback")
	public String naverCallback(
			
			HttpServletRequest request, HttpSession session, Model model
			, Member member, String naverId) {
		logger.debug("/login/callback [GET]");

		String code = request.getParameter("code");
		String state = (String)session.getAttribute("state");

//		session.invalidate();

		String apiURL = socialService.getApiURL(code, state);
		JsonObject Token = socialService.getTokenNaver(apiURL);
		
		HashMap<String, Object> naverInfo = socialService.getNaverInfo(Token);
		
		logger.info("Naver에서 읽어온 User정보 : {}", naverInfo);
		
		member.setSuserno((String)naverInfo.get("id"));
		naverId = (String)naverInfo.get("id");
		Member sMember = memberService.selectSuser(naverId);
		
		boolean social = memberService.selectKakao(member);


		
		// 소셜no가 있으면 메인화면으로 이동
		if( social ) {
			
	    	session.setAttribute("login", true);
			session.setAttribute("naverId", naverId);

	        session.setAttribute("userno", sMember.getUserNo());
			
			return "./main";
			
			
		// 소셜 no가 없으면 회원가입 창으로 이동
		} else {
			
			session.setAttribute("naverId", naverId);

			
		}
		
	    return "redirect:./socialjoin";

		
		
//		if ( social ) {
//			logger.debug("회원 가입내역 없음");
//			
//			session.setAttribute("naverId", naverId);
//			
//			
//		    return "redirect:./socialjoin";
//			
//		} else {
//			
//			logger.debug("회원 가입내역 존재");
//			session.setAttribute("login", true); 
//			session.setAttribute("naverId", naverId);
//			
//			return "./main";
//		}
		
		
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
				
				// 관리자 로그인
				
			}
			
//			logger.info("hospital : {}", hospital);
			Member detail = memberService.userDetail(member2);
			model.addAttribute("info", member);

		}
		
		return "redirect:/";
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
	
	
	//아이디 중복 검사
	   @RequestMapping("/join/idDu")
	   @ResponseBody
	   public String userIdDu(Member member) {
	      
	      logger.info("아이디 중복검사");
	      
	      int res = memberService.idDu(member);
	      
	      logger.info("res {} " , res);
	      
	      //result가 1이면 DB에 아이디 존재
	      if(res != 0) {
	         return "fail";   //중복 아이디 존재
	      }else {
	         return "success";   //중복 아이디 없음
	      }
	   }

	   //아이디 중복 검사
	   @RequestMapping("/join/nickDu")
	   @ResponseBody
	   public String userNickDu(Member member) {
		   
		   logger.info("아이디 중복검사");
		   
		   int res = memberService.nickDu(member);
		   
		   logger.info("res {} " , res);
		   
		   //result가 1이면 DB에 닉네임 존재
		   if(res != 0) {
			   return "fail";   //중복 닉네임 존재
		   }else {
			   return "success";   //중복 닉네임 없음
		   }
	   }
	
	
	@GetMapping("/mailCheck")
	@ResponseBody
	public String joinm( String email ) {
		logger.info("/login/joinm");
		logger.info("이메일 인증 : {}" + email);
		
		return memberService.joinEmail(email);
	}
	
	
	@PostMapping("/login/join")
	public String joinProc(
			 Member member
			, @RequestParam("address") String jibunAddress
			) {
		
		logger.info("/login/join");
		
		// 지번 주소
		memberService.getKakaoApiFromAddress( jibunAddress);
		
		// 지번 주소로 위도 경도를 반환해준다.
		HashMap<String, String> XYMap = memberService.getXYMapfromJson( memberService.getKakaoApiFromAddress( jibunAddress) );
		
		// x는 경도 y는 위도
		member.setLongitude(XYMap.get("x"));
		member.setLatitude(XYMap.get("y"));
		
		memberService.insertJoin( member );
		
		return "/main";
		
	}
	

	
	@GetMapping("/login/socialjoin")
	public void socialjoin(String sosId, HttpSession session, String naverId) {
		logger.info("/login/socialjoin");
		
		sosId = (String) session.getAttribute("userId");
		naverId = (String) session.getAttribute("naverId");
		
		System.out.println(sosId);
		System.out.println(naverId);
	}
	
	
	@PostMapping("/login/socialjoin")
	public String socialjoinProc(
			Member member
			, @RequestParam("address") String jibunAddress
			, HttpSession session
			, String sosId, String naverId
			) {
		
		// 카카오토큰중 id를 sosId로 저장
		sosId = (String) session.getAttribute("userId");
		naverId = (String) session.getAttribute("naverId");
		
		System.out.println(sosId);
		System.out.println(naverId);
		
		
		// 세션 카카오아이디만 해제하기
		session.removeAttribute("sosId");
		session.removeAttribute("naverId");
		
		logger.info("/login/socialjoin");
		
		
		memberService.getKakaoApiFromAddress( jibunAddress);
		
		HashMap<String, String> XYMap = memberService.getXYMapfromJson( memberService.getKakaoApiFromAddress( jibunAddress) );
		
		member.setLongitude(XYMap.get("x"));
		member.setLatitude(XYMap.get("y"));
		
		if(sosId!=null) {
		memberService.insertkakaoJoin( member, sosId );
		}else if (naverId!=null) {
		memberService.insertkakaoJoin( member, naverId );
		}
		
		
		return "/main";
		
	}
	
	
	
	@GetMapping("/login/id")
	public void id() {
		logger.info("아이디 찾기");
	}
	
	@PostMapping("/login/id")
	public String idResult(
			Member member
			, Model model

			) {
		logger.info("아이디 찾기");
		
		Member searchId = memberService.searchId(member);
		
		logger.info("searchId :{}", searchId);
		
		
		model.addAttribute("searchId", searchId);

		
		return "./member/login/id_result";
	}

	@GetMapping("/login/pw")
	public void pw( Member member, HttpSession session) {
		logger.info("비밀번호 찾기");

	}

	@PostMapping("/login/pw")
	public String pwResult(
			Member member
			, Model model
			, HttpSession session
			) {
		logger.info("비밀번호 찾기");
		
		Member searchPw = memberService.searchPw(member);
		session.setAttribute("userNo", searchPw.getUserNo());
		
		model.addAttribute("searchPw", searchPw);

		return "./member/login/pw_result";

	}
	
	@GetMapping("/login/pw_result")
	public void pwupdate(

			) {
		logger.info("비밀번호 변경");
	}
	
	@PostMapping("/login/pw_result")
	public String pwUpdate(
			Member member
			, HttpSession session
			) {
		logger.info("비밀번호 변경");
		member.setUserNo((int)session.getAttribute("userNo"));

//		member = (int) session.getAttribute("userNo");
		logger.info("userNo : {}", member);
		
		memberService.updatePw( member);
		
		return "redirect:./login";

	}
	
	
}
