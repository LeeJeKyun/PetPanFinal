package board.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import board.dto.Board;
import board.dto.BoardFile;
import board.dto.Notice;
import board.service.face.BoardService;
import util.Paging;



@Controller
@RequestMapping("/board")
public class BoardController {
private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired BoardService boardService;
	

	@GetMapping("/notice/list")
	public void notice_list(
			
			@RequestParam(value = "curPage", defaultValue = "1") int curPage,
			@RequestParam(value = "search", defaultValue = "") String search,
			Model model
			
			) {
		logger.info("/board/notice [GET]");
		Paging paging = boardService.getNoticePaging(curPage, search);
		
		List<Map<String, Object>> list = boardService.getNoticeList(paging);
		for(Map<String, Object> n : list) {
			logger.info("{}", n);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
	}
	
	@GetMapping("/notice/view")
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
	
	@GetMapping("/care/list")
	public void care(
			
			@RequestParam(value = "curPage", defaultValue = "1") int curPage,
			@RequestParam(value = "search", defaultValue = "") String search,
			Model model
			
			) {
		
		logger.info("/care/list [GET]");
		Paging paging = boardService.getCarePaging(curPage, search);
		
		List<Map<String, Object>> list = boardService.getCareList(paging);
		
		
//		for(Map<String, Object> m : list) {
//			logger.info("{}", m);
//		}
		
		model.addAttribute("paging", paging);
		model.addAttribute("list", list);
		
	}
	
	@GetMapping("/care/write")
	public void care_write() {
		
	}
	
	@PostMapping("/care/write")
	public String care_wrtieProc(
			
			HttpSession session
			, Board board
			, List<MultipartFile> file
			
			) {
		
		String loginid = (String)session.getAttribute("loginid"); 
		
		//나중에 세션에 userno추가되면 메소드 지우기
		board.setUserNo(boardService.getUserno(loginid));
		logger.info("board {}", board);
		logger.info("file {}", file);
		
		boardService.upload(board, file);
		
		return "redirect:/board/care/list";
		
	}
	
	//-----------------------------제균--------------------------------------------

	@GetMapping("/board/write")
	public String write(HttpSession session) {
		
		// 로그인 안돼있을 때 메인으로 redirect
//		if((boolean)session.getAttribute("login")) {
//			return "redirect:/board/board/write";
//		}else {
//			//return "redirect:/main";      
//		}
		return "/board/board/write";
	}
	
	@PostMapping("/board/write")
	public String writePost(
			@RequestParam(value = "file", required = false)List<MultipartFile> fileList //올릴 파일 리스트
			, Board board
			, @RequestParam(required = false) List<Integer> no // 취소된 파일 -1 
			) {
		
		if(board.getContent() == null || board.getBoardTitle() == null)
			return "redirect:/board/board";
		
		logger.info("write post");
		
		logger.info("no : {}", no);
		logger.info("fileList : {}", fileList);
		logger.info("board : {}", board);
		
		int boardNo = boardService.writeBoard(board);  //올라간 게시글의 번호 받기
		boardService.saveFiles(fileList, boardNo, no);
		
		return "redirect:/board/board";
	}
	@GetMapping("/board")
	public void board( @RequestParam(required = false, defaultValue = "1") Integer curPage
			, Model model
			,	@RequestParam(required = true, defaultValue = "2")int category
			, 	@RequestParam(required = false) String search) {
		Paging paging = new Paging();
		
		paging.setSearch(search);
		
//		paging = boardService.getPaging(curPage, category);
		paging = boardService.getPaging(curPage, category, search);

		//일반 게시판 자유 or 중고거래 게시글 가져오기
//		List<Map<String, Object>> list =  boardService.getBoard(paging, category, search);
		List<Map<String, Object>> list =  boardService.getBoard(paging, category);
		 
		//공지사항 3개 가져오기
		List<Map<String, Object>> listNotice = boardService.getNotice(category);
		
		model.addAttribute("listNotice", listNotice);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		
	}
	
	@GetMapping("/board/detail")
	public void detail(int boardNo, Model model) {
		// boardno, boardTitle, hit, recommend, writeDate, userName, content, boardTypeNo, writeDate
		Map<String, Object> map =  boardService.getBoardOne(boardNo);
		
		// boardNo에 맞는 파일 가져오기
		List<BoardFile> list = boardService.getBoardFile(boardNo);
		
		logger.info("boardFile  {}", list);
		logger.info("map {}", map);
		
		model.addAttribute("list", list);
		model.addAttribute("map", map);
	}
	// 게시글 신고 구현중
	@GetMapping("/board/report")
	public String reportBoard(int boardNo, int userNo) {
		
		//이미 신고한 게시글이면 신고한 게시글이다
		
		//아니면 신고 추가
		
		return "redirect:/board/board/detail?boardNo="+ boardNo;
	}
	@GetMapping("/board/delete/board")
	public String deleteBoard(int boardNo) {
		boardService.deleteBoard(boardNo);
		
		logger.info("게시글 삭제 {}", boardNo);
		return "redirect:/board/board";
	}
	//댓글 ajax 구현중
	@GetMapping("/board/comment")
	public void comment() {
		
	}
}
