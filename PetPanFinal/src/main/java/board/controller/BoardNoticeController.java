package board.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.dto.Notice;
import board.service.face.BoardService;
import util.Paging;

@Controller
@RequestMapping("/board/notice")
public class BoardNoticeController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired BoardService boardService;
	
	@GetMapping("/list")
	public void notice_list(
			
			@RequestParam(value = "curPage", defaultValue = "1") int curPage,
			@RequestParam(value = "search", defaultValue = "") String search,
			Model model
			
			) {
//		logger.info("/board/notice [GET]");
		Paging paging = boardService.getNoticePaging(curPage, search);
		
		List<Map<String, Object>> list = boardService.getNoticeList(paging);
		for(Map<String, Object> n : list) {
			logger.info("{}", n);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
	}
	
	@GetMapping("/view")
	public void notice_view(
			
			int noticeno,
			Model model
			
			) {
		
		logger.info("{}", noticeno);
		Notice notice = boardService.getNoticeView(noticeno);
		
		logger.info("notice : {}" ,notice);
		//상세조회한 공지사항 jsp에 전달
		model.addAttribute("notice", notice);
		
	}
	
}
