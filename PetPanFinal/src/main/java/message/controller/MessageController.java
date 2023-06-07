package message.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import message.dto.Message;
import message.service.face.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired MessageService messageService;

	@GetMapping("/message/send")
	public void send(
			
			String receiveuserid
			, HttpSession session
			, Model model
			
			) {
		int sendUserNo = (int)session.getAttribute("userno");
		int receiveUserNo = messageService.getReceiveUserNo(receiveuserid);
		
		logger.info("receiver : {}", receiveuserid);
		logger.info("sendNo : {}", sendUserNo);
		
		String sendUserId = messageService.getSendUserId(sendUserNo);
		logger.info("sender : {}", sendUserId);
		model.addAttribute("receiveuserid", receiveuserid);
		model.addAttribute("receiveuserno", receiveUserNo);
		model.addAttribute("senduserid", sendUserId);
		model.addAttribute("senduserno", sendUserNo);
	}
	
	@PostMapping("/message/send")
	public String sendProc(
			
			Message message
			
			) {
		
		logger.info("{}", message);
		boolean res = messageService.sendMessage(message);
		
		if(res) return "redirect:/message/message/complete";
		
		return "redirect:/message/message/fail";
		
	}
	
	@GetMapping("/message/complete")
	public void messageComplete() {}
	
	@GetMapping("/message/list")
	public void messageList(
			
			HttpSession session,
			Model model
			
			) {
		
		int userno = (int)session.getAttribute("userno");
		
		Message message = new Message();
		message.setReceiveUserNo(userno);
		
		List<Map<String, Object>> list = messageService.getMessageList(message);
		
//		logger.info("{}", list);
		
		for(Map<String, Object> m : list) {
			logger.info("{}", m);
		}
		
		model.addAttribute("list", list);
		
	}
	
	@GetMapping("/message/messageList")
	public void messageList_tab(
			
			Message message,
			Model model
			
			) {
		logger.info("message : {}", message);
		
		List<Map<String, Object>> list = messageService.getMessageList(message);
		
//		logger.info("{}", list);
		
		model.addAttribute("list", list);
		
	}
	
	@GetMapping("/message/messageView")
	public void messageView(
			
			Message message,
			Model model
			
			) {
		
		logger.info("message : {}", message);
		
		Message viewMessage = messageService.getMessageView(message);
		
		model.addAttribute("viewMessage", viewMessage);
		
	}
	
}
