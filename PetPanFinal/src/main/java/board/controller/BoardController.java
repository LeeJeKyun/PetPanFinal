package board.controller;

import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
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
import org.springframework.web.multipart.MultipartFile;

import board.dto.Board;
import board.dto.BoardFile;
import board.dto.BoardRecommend;
import board.dto.Notice;
import board.dto.ReportBoard;
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
//		logger.info("/board/notice [GET]");
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
		
		//확인해보기
//		for(Map<String, Object> m : list) {
//			logger.info("map -> {}", m);
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
	
	@GetMapping("/care/view")
	public void care_view(
			
			int boardNo
			, Model model
			, HttpSession session
			) {
//		logger.info("boardNo {}", boardNo);
		Map<String, Object> map = boardService.getCareView(boardNo);
		logger.info("map : {}", map);
		
		List<BoardFile> fileList = boardService.getCareFile(boardNo);
		logger.info("fileList : {}", fileList);
		String loginid = null;
		int userNo = 0;
		boolean isRecommended = false;
		
		if(session.getAttribute("login") != null) {
			loginid = (String)session.getAttribute("loginid");
			userNo = boardService.getUserno(loginid);
			
			isRecommended = boardService.isRecommended(boardNo, userNo);
		}
		
		model.addAttribute("map", map);
		model.addAttribute("fileList", fileList);
		model.addAttribute("idRecommended", isRecommended);
		
	}
	
//	@GetMapping("/care/recommend")
//	public @ResponseBody int care_view_recommend (
//				int boardNo
//				, HttpSession session
//			) {
//		
////		logger.info("boardNo : {}", boardNo);
//		String loginid = (String)session.getAttribute("loginid");
////		logger.info("loginid : {}", loginid);
//		int userNo = boardService.getUserno(loginid);
////		logger.info("userNo : {}", userNo);
//		
//		boardService.recommendBoardCare(boardNo, userNo);
//		int recommendCnt = boardService.getRecommendCnt(boardNo);
//		
//		logger.info("recommendCnt : {}", recommendCnt);
//		
//		return recommendCnt;
//		
//	}
	
	@GetMapping("/care/recommend")
	public void care_view_recommend (
			int boardNo
			, HttpSession session
			, Model model
			) {
		
//		logger.info("boardNo : {}", boardNo);
		String loginid = (String)session.getAttribute("loginid");
//		logger.info("loginid : {}", loginid);
		int userNo = boardService.getUserno(loginid);
//		logger.info("userNo : {}", userNo);
		
		boardService.recommendBoardCare(boardNo, userNo);
		int recommendCnt = boardService.getRecommendCnt(boardNo);
		
		logger.info("recommendCnt : {}", recommendCnt);
		
		model.addAttribute("recommendCnt", recommendCnt);
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
			, 	@RequestParam(required = false) String search
			//테스트용 세션
			, HttpSession session) {
		Paging paging = new Paging();
		
		//테스트 session  userNo
		session.setAttribute("userNo", 1);
		
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
	public void detail(int boardNo, HttpSession session ,Model model) {
		// boardno, boardTitle, hit, recommend, writeDate, userName, content, boardTypeNo, writeDate
		Map<String, Object> map =  boardService.getBoardOne(boardNo);
		
		// boardNo에 맞는 파일 가져오기
		List<BoardFile> list = boardService.getBoardFile(boardNo);
		
		// 게시글 추천 눌렀는지 가져오기
		BoardRecommend boardReco = new BoardRecommend();
		boardReco.setBoardNo(boardNo);
		//boardReco.setUserNo((int)session.getAttribute("userNo"));
		boardReco.setUserNo(1);
		
		boolean like = boardService.isLike(boardReco); 
		
		logger.info("boardFile  {}", list);
		logger.info("map {}", map);
		logger.info("like : {}", like);
		
		model.addAttribute("like", like);
		model.addAttribute("list", list);
		model.addAttribute("map", map);
	}
	// 게시글 신고 구현중
	@PostMapping("/board/reportPopup")
	public void reportBoard( HttpSession session, ReportBoard reportBoard, String writeDetail) {
		int userNo = (int) session.getAttribute("userNo");
		
		reportBoard.setUserNo(userNo);
		
		logger.info("reportBoard {}", reportBoard);
		logger.info("writeDetail {}", writeDetail);
		boardService.boardReport(reportBoard, writeDetail);  
			
		
		//return "redirect:/board/board/detail?boardNo="+ reportBoard.getBoardNo();
	}
	@GetMapping("/board/reportPopup")
	public void reportPopup(int boardNo, Model model) {
		model.addAttribute("boardNo", boardNo);
	}
	@GetMapping("/board/delete/board")
	public String deleteBoard(int boardNo) {
		boardService.deleteBoard(boardNo);
		
		logger.info("게시글 삭제 {}", boardNo);
		return "redirect:/board/board";
	}
	// detail 페이지 추천 ajax 구현중
	@GetMapping("/board/recommend")
	public String recommend(boolean like, BoardRecommend boardReco, Model model) {
//		model.addAttribute({
//			"like"
//		})
		
		return "jsonView";
	}
	//댓글 ajax 구현중
	@GetMapping("/board/comment")
	public void comment() {
	}
}
