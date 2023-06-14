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
import board.dto.Notice;
import board.dto.ReportBoard;
import board.dto.ReportComment;
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
		if(null != session.getAttribute("userno")) {
			return "/board/board/write";
		}else {
			return "redirect:/";      
		}
	}
	
	@PostMapping("/board/write")
	public String writePost(
			@RequestParam(value = "file", required = false)List<MultipartFile> fileList //올릴 파일 리스트
			, Board board
			, @RequestParam(required = false) List<Integer> no // 취소된 파일 -1 
			,	HttpSession session
			) {
		
		if(board.getContent() == null || board.getBoardTitle() == null)
			return "redirect:/board/board";
		
		board.setUserNo((int)session.getAttribute("userno"));
		
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
		
		
		paging.setSearch(search);
		
		paging = boardService.getPaging(curPage, category, search);

		//일반 게시판 자유 or 중고거래 게시글 가져오기
		List<Map<String, Object>> list =  boardService.getBoard(paging, category);
		 
		//공지사항 3개 가져오기
		List<Map<String, Object>> listNotice = boardService.getNotice(category);
		
		model.addAttribute("listNotice", listNotice);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("category", category);
	}
	
	@GetMapping("/board/detail")
	public void detail(int boardNo, HttpSession session ,Model model) {
		boolean like = false;
		
		// 게시글 추천 눌렀는지 가져오기
		BoardRecommend boardReco = new BoardRecommend();
		boardReco.setBoardNo(boardNo);
		
		if(null != session.getAttribute("userno")) {
			boardReco.setUserNo((int)session.getAttribute("userno"));
			//boardService.
			like = boardService.isLike(boardReco); 
			boardService.plusHit(boardNo);
		}
		
		// boardno, boardTitle, hit, recommend, writeDate, userName, content, boardTypeNo, writeDate
		Map<String, Object> map =  boardService.getBoardOne(boardNo);
//		boardReco.setUserNo(1);
		
		// boardNo에 맞는 파일 가져오기
		List<BoardFile> list = boardService.getBoardFile(boardNo);
		
		logger.info("like : {}", like);
		model.addAttribute("like", like);
		
		logger.info("boardFile  {}", list);
		model.addAttribute("list", list);
		
		model.addAttribute("map", map);
		logger.info("map {}", map);
	}
	@PostMapping("/board/reportPopup")
	public void reportBoard(Model model, HttpSession session, ReportBoard reportBoard, String writeDetail) {
		int userNo = (int) session.getAttribute("userno");
		
		reportBoard.setUserNo(userNo);
		
		logger.info("reportBoard {}", reportBoard);
		logger.info("writeDetail {}", writeDetail);
		
		boardService.boardReport(reportBoard, writeDetail);
		model.addAttribute("flag", true);
	}
	@GetMapping("/board/reportPopup")
	public void reportPopup(@RequestParam(defaultValue = "false", required = false) boolean flag, 
										int boardNo, Model model) {
		model.addAttribute("boardNo", boardNo);
		model.addAttribute("flag", flag);
	}
	
	@GetMapping("/board/delete/board")
	public String deleteBoard(int boardNo) {
		boardService.deleteBoard(boardNo);
		
		logger.info("게시글 삭제 {}", boardNo);
		return "redirect:/board/board";
	}
	@GetMapping("/board/update")
	public void updateBoardPath(int boardNo, Model model) {
		//게시글 정보 가져오기
		Map<String, Object> map = boardService.getBoardOne(boardNo);
		logger.info("map {} ", map);
		
		List<BoardFile> listFile =  boardService.getBoardFile(boardNo);
		logger.info("listFile {} ", listFile);
		
		model.addAttribute("map", map);
		model.addAttribute("listFile", listFile);
	}
	@PostMapping("/board/update")
	public String updateBoard(@RequestParam(value = "file", required = false)List<MultipartFile> fileList //올릴 파일 리스트
			, Board board
			, @RequestParam(required = false) List<Integer> no // 취소된 파일 -1 
			,	HttpSession session
			) {
		
		boardService.updateBoard(fileList, no, session, board);
		return "redirect:/board/board";
	}
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
	@PostMapping("/board/comment")
	public ModelAndView comment(int boardNo, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		// commentNo, content, writeDate, userNo, depth, refcommentNo, isRecommend
		List<Map<String, Object>> list =  boardService.getComments(boardNo, (int)session.getAttribute("userno"));
		logger.info("comments list : {}",list);
		
		mav.addObject("list", list);
		mav.setViewName("./board/board/detail_comment");
		
		return mav;
	}
	@GetMapping("/board/comment/write")
	public String commentWrite(Comment comment, HttpSession session, Model model) {
		
		comment.setUserNo((int)session.getAttribute("userno")) ;
//		comment.addComment(comment);
		logger.info("comment {}",comment);
		
		String userName = boardService.getUsername(comment.getUserNo());
				
		comment = boardService.addComment(comment);
		model.addAttribute("comment", comment);
		logger.info("삽입한 댓글 {}", comment);
		
		return "jsonView";
	}
	@GetMapping("/board/comment/delete")
	public String deleteComment(int commentNo, Model model) {
		
		//댓글 삭제
		boardService.deleteComment(commentNo);
		
		model.addAttribute("data", true);
		return "jsonView";
	}
	
	@GetMapping("/board/reportComment")
	public void reportCommentPopup(@RequestParam(defaultValue = "false") boolean flag
						, int commentNo, Model model) {
		model.addAttribute("commentNo", commentNo);
		model.addAttribute("flag", flag);
	}
	
	
	@PostMapping("/board/reportComment")
	public void reportComment(ReportComment rc, HttpSession session,
								@RequestParam(required = false) String writeDetail
								,Model model) {
		rc.setUserNo((int)session.getAttribute("userno"));
		
		logger.info(" rc 댓글 신고 {}",rc);
		logger.info("기타 사유 {}", writeDetail);
		boardService.reportComment(rc, writeDetail);
		
		model.addAttribute("data", true);
		model.addAttribute("flag", true);
	}
	@GetMapping("/board/comment/like")
	public String commentLike(int commentNo, HttpSession session, Model model) {
		if(session.getAttribute("userno") == null) {
			return "redirect:/";
		}
		Map<String, Object> map = new HashMap<>();
		int userNo = (int)session.getAttribute("userno");
		
		boolean flag = boardService.commentRecommend(userNo, commentNo);
		
		int count = boardService.countCommentReco(commentNo);
		
//		map.put("flag", flag);
//		map.put("count", count);
//		
//		model.addAttribute("map",map);
		model.addAttribute("flag", flag);
		model.addAttribute("count", count);
		return "jsonView";
	}
}
