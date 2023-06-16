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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import board.dto.Board;
import board.dto.BoardFile;
import board.dto.Comment;
import board.dto.ReportBoard;
import board.dto.ReportComment;
import board.service.face.BoardService;
import member.dto.Member;
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
			@RequestParam(value = "distance", defaultValue = "2") String distance,
			HttpSession session,
			Model model
			
			) {
		
		logger.info("/care/list [GET]");
		
		List<Map<String, Object>> list = null;
		Paging paging = null;
		
		//로그인을 했을 때는 계정의 정보를 가져와서 주변 게시글만 띄워준다. 
		if(session.getAttribute("login") != null && (boolean)session.getAttribute("login") != false) {
			
			int userno = (int)session.getAttribute("userno");
			
			Member loginMember = boardService.getUserInfo(userno);
			paging = boardService.getCarePaging(curPage, search, loginMember, distance);
			
			list = boardService.getCareListFromLogin(paging, loginMember, distance);
			model.addAttribute("login", true);
			
		}
		
		List<Map<String, Object>> noticeList = boardService.getNoticeListToCare();
		
		//확인해보기
//		for(Map<String, Object> m : list) {
//			logger.info("map -> {}", m);
//		}

//		//확인해보기
//		for(Map<String, Object> m : noticeList) {
//			logger.info("map -> {}", m);
//		}
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("distance", distance);
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
//		logger.info("writerMember: {}", writerMember);
		
		List<BoardFile> fileList = boardService.getCareFile(boardNo);
//		logger.info("fileList : {}", fileList);
//		String loginid = null;
		int userNo = 0;
		boolean isRecommended = false;
		
		if(session.getAttribute("login") != null) {
//			loginid = (String)session.getAttribute("loginid");
			userNo = (int)session.getAttribute("userno");
			
			Member loginMember = boardService.getUserInfo(userNo);
//			logger.info("loginMember : {}", loginMember);
			isRecommended = boardService.isRecommended(boardNo, userNo);
//			logger.info("isRecommended : {} ", isRecommended);
			model.addAttribute("loginMember", loginMember);
			double distance = boardService.getDistance(loginMember, writerMember);
//			logger.info("{}", distance);
			model.addAttribute("distance", distance * 1000);
		}
		
		List<Map<String, Object>> commentList = boardService.getCommentList(boardNo, userNo);

		for(Map<String,Object> m : commentList) {
			logger.info("{}", m);
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
//		logger.info("boardNo : {}", boardNo);
//		String loginid = (String)session.getAttribute("loginid");
//		logger.info("loginid : {}", loginid);
//		int userNo = boardService.getUserno(loginid);
//		logger.info("userNo : {}", userNo);
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
			, HttpSession session
			, Model model
			) {
		logger.info("Comment : {}", comment);
		
		int userno = 0;
		if(session.getAttribute("login") != null) {
			userno = (int)session.getAttribute("userno");
		}
		
		boardService.inputComment(comment);
		List<Map<String, Object>> commentList = boardService.getCommentList(comment.getBoardNo(), userno);
		
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
	
	@GetMapping("/reportPopup")
	public void care_report(
			
			int boardNo,
			Model model
			
			) {
		logger.info("boardNo : {}", boardNo);
		model.addAttribute("boardNo", boardNo);
	}
	
	@PostMapping("/reportPopup")
	public String care_reportProc(
			
			ReportBoard reportBoard,
			@RequestParam(name = "writeDetail",defaultValue = "") String writeDetail,
			HttpSession session
			
			) {
		
		logger.info("reportBoard : {}", reportBoard);
		int userno = (int)session.getAttribute("userno");
		logger.info("userno : {}", userno);
		reportBoard.setUserNo(userno);
		boardService.inputCareReport(reportBoard, writeDetail);
		
		return "/board/care/reportComplete";
	}
	
	@GetMapping("/reportComment")
	public void care_reportComment(
			
			int commentNo,
			Model model
			
			) {
		
		logger.info("commentno : {}", commentNo);
		model.addAttribute("commentNo", commentNo);
		
	}
	
	@PostMapping("/reportComment")
	public String care_reportComment(
			
			ReportComment reportComment,
			@RequestParam(name = "writeDetail", defaultValue = "") String writeDetail,
			HttpSession session
			
			) {
		
		logger.info("reportComment : {}", reportComment);
		int userno = (int)session.getAttribute("userno");
		logger.info("userno : {}", userno);
		reportComment.setUserNo(userno);
		boardService.inputCareCommentReport(reportComment, writeDetail);
		return "/board/care/reportComplete";
	}
	
	@GetMapping("/comment/delete")
	public String care_deleteComment(
			
			Comment comment,
			HttpSession session,
			Model model
			
			) {
		
		logger.info("Comment : {}", comment);
		boardService.deleteCareComment(comment);
		
		
		int userno = 0;
		if(session.getAttribute("login") != null) {
			userno = (int)session.getAttribute("userno");
		}
		
		List<Map<String, Object>> commentList = boardService.getCommentList(comment.getBoardNo(), userno);
		
		model.addAttribute("commentList", commentList);
		
		return "/board/care/comment";
	}
	
	@GetMapping("/comRecommend")
	public @ResponseBody int comRecommend(
			
			int commentNo,
			HttpSession session
			
			) {
		
		int userno = (int)session.getAttribute("userno");
		logger.info("{}", commentNo);
		logger.info("userno : {}", userno);
		
		Map<String, Object> map = new HashMap<>();

		map.put("commentNo", commentNo);
		map.put("userNo", userno);
		
		if(!boardService.isRecommendedCom(map)) {
			logger.info("추천");
			boardService.commentRecommend(map);
		} else {
			logger.info("추천취소");
			boardService.deleteComRecommend(map);
		}

		int res = boardService.getComRecommendCount(commentNo);
		
		return res;
	}
	
	@GetMapping("/update")
	public void careUpdate(
			
			Board board,
			Model model
			
			) {
		
		Map<String, Object> boardMap = boardService.getBoardOne(board.getBoardNo());
		List<BoardFile> boardFile = boardService.getBoardFile(board.getBoardNo());
		
		logger.info("boardMap : {}", boardMap);
		logger.info("boardFile : {}", boardFile);
		model.addAttribute("map", boardMap);
		model.addAttribute("boardFile",boardFile);
	}
	
	@PostMapping("/update")
	public String careUpdateProc(
			
			@RequestParam(value="file", required=false)List<MultipartFile> fileList
			, Board board
			, @RequestParam(required = false) List<Integer> no
			, HttpSession session
			
			) {
		
		logger.info("board : {}", board);
		logger.info("fileList : {}", fileList);
		logger.info("no : {}", no);

		boardService.updateBoardCare(board);
		boardService.saveUpdateFile(fileList, board.getBoardNo(), no);
		
		return "redirect:list";
	}
	
	@GetMapping("/fileDelete")
	public @ResponseBody String fileDelete(
			
			BoardFile boardFile
			
			) {
		
		logger.info("boardFile : {}", boardFile);
		
		boolean res = boardService.deleteFileCare(boardFile);
		
		if(res) {
			return "delete Complete!!";
		}
		
		return "delete fail";
	}
	
}
