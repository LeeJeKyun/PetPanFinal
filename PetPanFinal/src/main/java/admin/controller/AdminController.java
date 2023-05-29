package admin.controller;

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

import admin.dto.Blacklist;
import admin.dto.Notice;
import admin.dto.ReportBoard;
import admin.dto.ReportComment;
import admin.service.face.AdminService;
import board.dto.Board;
import board.dto.BoardFile;
import board.dto.BoardRecommend;
import board.dto.CommentTable;
import member.dto.Member;
import shop.dto.Shop;
import shop.dto.ShopFile;
import util.AdminPaging;
import util.Paging;

@RequestMapping("/admin")
@Controller
public class AdminController {
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired AdminService adminService;
	
	@GetMapping("/")
	public String goMain1() {
		
		return "redirect:/admin/main/main";
	}
	
	@GetMapping("/main")
	public String goMain2() {
		
		return "redirect:/admin/main/main";
	}
	
	@GetMapping("/main/main")
	public void goMain3() {
		
	}

	//blacklist 수정
	@GetMapping("/blacklist/board")
	public void viewblackBoard(@RequestParam(defaultValue = "0") int curPage, Model model) {
//		logger.info("/reportboard [GET}");
//		logger.info("curPage = {}", curPage);
		AdminPaging paging = new AdminPaging();
		
		paging = adminService.getPage(curPage);
		
//		logger.info("paging = {}", paging);
		
		List<Blacklist> list = adminService.getBlacklistBoard(paging);
		
		model.addAttribute("list",list);
		model.addAttribute("paging",paging);
	}

	

	@GetMapping("/blacklist/delete")
	public String blacklistdelete(@RequestParam(value="delete",required=false) List<String> delete) {
		
//		logger.info("delete = {}", delete);
		adminService.deleteblacklist(delete);
		
		
		return "redirect:/admin/blacklist/board";
		
	}
	
	@GetMapping("/blacklist/insert")
	public void blacklistinsert() {		
		
	}
	
	
	@PostMapping("/blacklist/insert")
	public void blacklistdeleteproc(Blacklist blacklist) {
		
		
		logger.info("blacklist = {}", blacklist);
		adminService.insertblacklist(blacklist);

	}
	
	
	@GetMapping("/member/board")
	public void viewUserBoard(@RequestParam(defaultValue = "0") int curPage, Model model) {

		AdminPaging paging = new AdminPaging();
		
		paging = adminService.getPage(curPage);
		

		
		List<Member> list = adminService.getMemberBoard(paging);
		
		model.addAttribute("list",list);
		model.addAttribute("paging",paging);
	}
	
	@GetMapping("/member/search")
	public void viewsearchBoard(@RequestParam(defaultValue = "0") int curPage, String keyword, Model model ) {
		
		AdminPaging paging = new AdminPaging();
		
		paging = adminService.getPage(curPage);
		
		
		
		List<Member> list = adminService.getsearchMemberBoard(paging,keyword);
		
		model.addAttribute("list",list);
		model.addAttribute("paging",paging);
		
	}

	
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
	public void board(  Model model	,int boardno, HttpSession session) {
		
//			Notice noticeList = adminService.getOnelist(boardno);
//		    model.addAttribute("list", noticeList);
		
	}
//	
//	
	@GetMapping("/notice/write")
	public void write(HttpSession session) {
		
	}
//	
	@PostMapping("/notice/write")
	public String writePost(
			@RequestParam(value = "file", required = false)List<MultipartFile> fileList //올릴 파일 리스트
			, Notice notice
			, @RequestParam(required = false) List<Integer> no // 취소된 파일 -1 
			) {
		
		if(notice.getNoticeContent() == null || notice.getNoticeTitle() == null)
			return "redirect:./list";
		
		logger.info("write post");
		
		logger.info("no : {}", no);
		logger.info("fileList : {}", fileList);
		logger.info("board : {}", notice);
		
//		int boardNo = adminService.writeBoard(notice);  //올라간 게시글의 번호 받기
//		adminService.saveFiles(fileList, boardNo, no);
		
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
