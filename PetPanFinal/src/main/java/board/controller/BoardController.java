package board.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import board.dto.Board;
import board.dto.BoardFile;
import board.dto.BoardRecommend;
import board.dto.Comment;
import board.dto.CommentTable;
import board.dto.Notice;
import board.dto.ReportBoard;
import board.service.face.BoardService;
import util.Paging;



@Controller
@RequestMapping("/board")
public class BoardController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired BoardService boardService;
	
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
			, HttpSession session) {
		Paging paging = new Paging();
		
		//테스트 session  userNo
		//session.setAttribute("userNo", 1);
		session.getAttribute("userno");
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
		boolean like = false;
		// boardNo에 맞는 파일 가져오기
		List<BoardFile> list = boardService.getBoardFile(boardNo);
		
		// 게시글 추천 눌렀는지 가져오기
		BoardRecommend boardReco = new BoardRecommend();
		boardReco.setBoardNo(boardNo);
		if(null != session.getAttribute("userno")) {
			boardReco.setUserNo((int)session.getAttribute("userno"));
			like = boardService.isLike(boardReco); 
		}
//		boardReco.setUserNo(1);
		
		logger.info("like : {}", like);
		model.addAttribute("like", like);
		
		logger.info("boardFile  {}", list);
		model.addAttribute("list", list);
		
		model.addAttribute("map", map);
		logger.info("map {}", map);
	}
	// 게시글 신고 구현중
	@PostMapping("/board/reportPopup")
	public void reportBoard( HttpSession session, ReportBoard reportBoard, String writeDetail) {
		int userNo = (int) session.getAttribute("userno");
		
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
	public ModelAndView recommend(BoardRecommend boardReco, HttpSession session) {

		ModelAndView mv = new ModelAndView();
		
		boardReco.setUserNo((int) session.getAttribute("userno"));
//		boardReco.setUserNo(1);
		
		logger.info("추천 boardReco : {}",boardReco);

		// 추천했으면 취소 or 추천
		
		mv.addObject("like", boardService.Reco(boardReco));
		mv.addObject("recommend", boardService.getCountReco(boardReco));
		mv.setViewName("jsonView");
		
		return mv;
	}
	//댓글 ajax 구현중
	@GetMapping("/board/comment")
	public String comment(int boardNo, Model model) {
		
		// commentNo, content, writeDate, userNo, depth, refcommentNo
		List<Map<String, Object>> list =  boardService.getComments(boardNo);
		
		model.addAttribute(list);
		
		return "./detail_comment";
	}
	@GetMapping("/board/comment/write")
	public String commentWrite(Comment comment, HttpSession session, Model model) {
		
		comment.setUserNo((int)session.getAttribute("userno")) ;
		//comment.setRefcommentNo(0);
		logger.info("comment {}",comment);
		
		String userName = boardService.getUsername(comment.getUserNo());
				
		//comment = boardService.addComment(comment);
		model.addAttribute("comment", comment);
		logger.info("삽입한 댓글 {}", comment);
		
		return "./board/comment";
	}
	
}
