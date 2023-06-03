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
import board.dto.Comment;
import board.service.face.BoardService;
import member.dto.Member;
import member.service.face.MemberService;
import util.Paging;

@Controller
@RequestMapping("/board/care")
public class BoardCareController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired BoardService boardService;
	
	@GetMapping("/list")
	public void care(
			
			@RequestParam(value = "curPage", defaultValue = "1") int curPage,
			@RequestParam(value = "search", defaultValue = "") String search,
			Model model
			
			) {
		
		logger.info("/care/list [GET]");
		Paging paging = boardService.getCarePaging(curPage, search);
		
		List<Map<String, Object>> list = boardService.getCareList(paging);
		List<Map<String, Object>> noticeList = boardService.getNoticeListToCare();
		
		//확인해보기
		for(Map<String, Object> m : list) {
			logger.info("map -> {}", m);
		}

//		//확인해보기
//		for(Map<String, Object> m : noticeList) {
//			logger.info("map -> {}", m);
//		}
		
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("paging", paging);
		model.addAttribute("list", list);
		
	}
	
	@GetMapping("/write")
	public void care_write() {
		
	}
	
	@PostMapping("/write")
	public String care_wrtieProc(
			
			HttpSession session
			, Board board
			, List<MultipartFile> file
			
			) {
		
//		String loginid = (String)session.getAttribute("loginid"); 
		
		//나중에 세션에 userno추가되면 메소드 지우기
		board.setUserNo((int)session.getAttribute("userno"));
		logger.info("board {}", board); 
		logger.info("file {}", file);
		
		boardService.upload(board, file);
		
		return "redirect:/board/care/list";
		
	}
	
	@GetMapping("/view")
	public void care_view(
			
			int boardNo
			, Model model
			, HttpSession session
			
			) {
//		logger.info("boardNo {}", boardNo);
		Map<String, Object> map = boardService.getCareView(boardNo);
		logger.info("map : {}", map);
		
		Member writerMember = boardService.getMemberByBoard(map);
		logger.info("writerMember: {}", writerMember);
		
		List<BoardFile> fileList = boardService.getCareFile(boardNo);
		logger.info("fileList : {}", fileList);
//		String loginid = null;
		int userNo = 0;
		boolean isRecommended = false;
		List<Map<String, Object>> commentList = boardService.getCommentList(boardNo);
		
		for(Map<String,Object> m : commentList) {
			logger.info("{}", m);
		}
		
		if(session.getAttribute("login") != null) {
//			loginid = (String)session.getAttribute("loginid");
			userNo = (int)session.getAttribute("userno");
			
			Member loginMember = boardService.getUserInfo(userNo);
			logger.info("loginMember : {}", loginMember);
			isRecommended = boardService.isRecommended(boardNo, userNo);
//			logger.info("isRecommended : {} ", isRecommended);
			model.addAttribute("loginMember", loginMember);
			double distance = boardService.getDistance(loginMember, writerMember);
			logger.info("{}", distance);
			model.addAttribute("distance", distance * 1000);
		}
		
		model.addAttribute("map", map);
		model.addAttribute("fileList", fileList);
		model.addAttribute("isRecommended", isRecommended);
		model.addAttribute("commentList", commentList);
		model.addAttribute("writerMember", writerMember);
		
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
	
	@GetMapping("/recommend")
	public void care_view_recommend (
			int boardNo
			, HttpSession session
			, Model model
			) {
		
//		logger.info("boardNo : {}", boardNo);
//		String loginid = (String)session.getAttribute("loginid");
//		logger.info("loginid : {}", loginid);
		int userNo = (int)session.getAttribute("userno");
//		logger.info("userNo : {}", userNo);
		
		boardService.recommendBoardCare(boardNo, userNo);
		int recommendCnt = boardService.getRecommendCnt(boardNo);
		
		logger.info("recommendCnt : {}", recommendCnt);
		
		model.addAttribute("recommendCnt", recommendCnt);
	}
	
	@GetMapping("/comment")
	public void care_view_comment(
			Comment comment
			, Model model
			) {
		logger.info("Comment : {}", comment);
		
		boardService.inputComment(comment);
		List<Map<String, Object>> commentList = boardService.getCommentList(comment.getBoardNo());
		
		for(Map<String, Object> m : commentList) {
			logger.info("map : {}", m);
		}
		
		model.addAttribute("commentList", commentList);
		
	}
	
	@GetMapping("/delete")
	public String care_delete(
			
			Board board
			
			) {
		logger.info("{}", board);
		boardService.deleteBoardByBoardObj(board);
		return "redirect: /board/care/list";
	}
	
}
