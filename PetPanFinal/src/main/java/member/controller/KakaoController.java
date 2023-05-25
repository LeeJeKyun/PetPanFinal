package member.controller;


import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import member.service.face.KakaoService;



@Controller
@RequestMapping("/login")
public class KakaoController {
	
	private static final Logger logger = LoggerFactory.getLogger(KakaoController.class);
	@Autowired KakaoService kakaoService;
	
	@RequestMapping("/")
	public String index() {
		
		return "/login/login";
	}  
	
	@RequestMapping("/login")
	public String login(@RequestParam("code")String code, HttpSession session) {
		logger.info("/login/login ���Ӽ���");
		
		logger.info("code: {}", code);
		String access_Token = kakaoService.getAccessToken(code);
		logger.info("controller access_token : {}" + access_Token);
		
		HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
	    System.out.println("login Controller : " + userInfo);
	    
	    //    Ŭ���̾�Ʈ�� �̸����� ������ �� ���ǿ� �ش� �̸��ϰ� ��ū ���
	    if (userInfo.get("email") != null) {
	        session.setAttribute("userId", userInfo.get("email"));
	        session.setAttribute("access_Token", access_Token);
	    }
	    
		return "/login/login";
		
	}


//	@RequestMapping("/login")
//	public String KakaoLogin() {
//		logger.info("/login/login ���Ӽ���");
//		
//		return "/login/login";
//	}
}
