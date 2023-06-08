package member.controller;

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

import board.service.face.BoardService;
import member.dto.Member;
import member.service.face.MemberService;
import member.service.face.UnregistService;

@Controller
@RequestMapping("/member/unregist")
public class UnregistController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private MemberService memberService;
	@Autowired private BoardService boardService;
	@Autowired private UnregistService unregistService;
	
	@GetMapping("/cheak")
	public void cheak(@RequestParam(required = false) String cheak, Model model) {
		
		model.addAttribute("cheak", cheak);
		
	}
	
	@PostMapping("/cheak")
	public String cheakreg(HttpSession session, Member member, String pw, Model model) {
		
		int userno = (int) session.getAttribute("userno");
		
		int cheak = unregistService.checkmember(member,userno,pw);
		
		if(cheak==0) {
			model.addAttribute("cheak",cheak);
			return "redirect:cheak";
		}
		return "redirect:unregist";
	}
	
	@GetMapping("/unregist")
	public void unregist() {
		
	}
	
	@PostMapping("/unregist")
	public String unregistdo(@RequestParam(required = false, defaultValue = "N")String delete,HttpSession session) {
		
		int userno = (int) session.getAttribute("userno");
		if(delete.equals("N")) {
			return "redirect:/member/mypage/mypage";
		}
		
		unregistService.unregistAll(userno);
		
		return "redirect:thanks";
	
	}
	
	@GetMapping("/thanks")
	public void thanks() {
		
	}
}
