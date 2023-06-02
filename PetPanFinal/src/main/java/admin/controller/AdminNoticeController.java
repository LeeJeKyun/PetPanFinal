package admin.controller;

import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import admin.service.face.AdminService;
import board.dto.Notice;

@RequestMapping("/admin")
@Controller
public class AdminNoticeController {
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired AdminService adminService;
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	/*===========================================================
	 * 공지사항 CRUD
	 *==============================================================
	 */
	
	// 공지 리스트 보여주기
	@GetMapping("/notice/list")
	public void noticelist() {
	}
	
	//공지 리스트 ajax
	
	@GetMapping("/notice/getlist")
	public void noticeGetlist( int theme ,Model model) {
		List<Notice> noticeList = adminService.getNoticeListByType(theme);
	    model.addAttribute("list", noticeList);
	}
//공지 하나 보여주기
	/*============================
	 *  write먼저하기
	 *  ============================
	 */
	@GetMapping("/notice/view")
	public void board(  Model model	,int noticeno, HttpSession session) {
		
//			Notice noticeList = adminService.getOnelist(boardno);
//		    model.addAttribute("list", noticeList);
		
	}
//	
//	
	@GetMapping("/notice/write")
	public void write() {
		
	}
//	
	@PostMapping("/notice/write")
	public String writePost(
			@RequestParam(value = "file", required = false)List<MultipartFile> fileList //올릴 파일 리스트
			, Notice notice
			, HttpSession session
			, @RequestParam(required = false) List<Integer> no // 취소된 파일 -1 
			) {
		 
		
		
		
		if(notice.getNoticecontent() == null || notice.getNoticecontent() == null)
			return "redirect:./list";
		// 

		
		logger.info("notice : {}", notice);
		adminService.writeNotice(fileList, notice, no,session);
		
		logger.info("write post");
		
		logger.info("no : {}", no);
		logger.info("fileList : {}", fileList);
		logger.info("notice : {}", notice);
		
		
		return "redirect:./list";
	}
//	
//	@GetMapping("/board/detail")
//	public void detail(int boardNo, HttpSession session ,Model model) {
//		// boardno, boardTitle, hit, recommend, writeDate, userName, content, boardTypeNo, writeDate
//		Map<String, Object> map =  boardService.getBoardOne(boardNo);
//		
//		// boardNo에 맞는 파일 가져오기
//		List<BoardFile> list = boardService.getBoardFile(boardNo);
//		
//		// 게시글 추천 눌렀는지 가져오기
//		BoardRecommend boardReco = new BoardRecommend();
//		boardReco.setBoardNo(boardNo);
//		//boardReco.setUserNo((int)session.getAttribute("userNo"));
//		boardReco.setUserNo(1);
//		
//		boolean like = boardService.isLike(boardReco); 
//		
//		logger.info("boardFile  {}", list);
//		logger.info("map {}", map);
//		logger.info("like : {}", like);
//		
//		model.addAttribute("like", like);
//		model.addAttribute("list", list);
//		model.addAttribute("map", map);
//	}
	
}
