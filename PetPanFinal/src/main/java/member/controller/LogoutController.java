package member.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import member.service.face.KakaoService;

@Controller
@RequestMapping("/login")
public class LogoutController {

	private static final Logger logger = LoggerFactory.getLogger(KakaoController.class);
	@Autowired KakaoService kakaoService;
	
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		kakaoService.kakaoLogout((String)session.getAttribute("access_Token"));
	    session.removeAttribute("access_Token");
	    session.removeAttribute("userId");
	    return "/login/login";
	}
}
