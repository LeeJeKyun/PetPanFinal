package admin.controller;

import java.util.List;

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
import admin.dto.ReportBoard;
import admin.dto.ReportComment;
import admin.service.face.AdminService;
import board.dto.Board;
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
	
	@GetMapping("/reportboard/list")
	public void reportBoard(@RequestParam(defaultValue = "0") int curPage, Model model) {
<<<<<<< HEAD
		logger.info("/reportboard [GET}");
		logger.info("curPage = {}", curPage);
		AdminPaging paging = new AdminPaging();
=======
//		logger.info("/reportboard [GET}");
//		logger.info("curPage = {}", curPage);
		Paging paging = new Paging();
>>>>>>> refs/remotes/origin/master
		
		paging = adminService.getPage(curPage);
		
//		logger.info("paging = {}", paging);
		
		List<ReportBoard> list = adminService.getReportBoard(paging);
		
		model.addAttribute("list",list);
		model.addAttribute("paging",paging);
		
	}
	
	@GetMapping("/reportboard/delete")
	public String deleteBoardReport(@RequestParam(value="delete",required=false) List<String> delete) {
//		logger.info("delete = {}", delete);
		adminService.deleteChecked(delete);
		
		
		return "redirect:/admin/reportboard/list";
	}
	
	@GetMapping("/reportboard/view")
	public void viewReportBoard(String boreportNo, Model model, ReportBoard reportboard, 
			// 신고자
			Member domember, 
			//피신고자
			Member getmember,
			Board board
			) {
//		logger.info("/viewreportboard [GET}");
		
		reportboard = adminService.getReportView(boreportNo);
		//신고자 정보
		domember = adminService.getDoMember(reportboard.getUserNo());
		//피신고자 정보
		getmember = adminService.getGetMember(reportboard.getBoardNo());
		//신고한 게시글 상세정보
		board = adminService.getBoard(reportboard.getBoardNo());
		
		model.addAttribute("list", reportboard);
		model.addAttribute("domember", domember);
		model.addAttribute("getmember", getmember);
		model.addAttribute("board", board);
		
		
	}
	@PostMapping("/reportboard/view/delete")
	public String deleteViewReportBoard(
			Integer boreportNo, 
			@RequestParam(required=false) Integer doboardNo,
			@RequestParam(required=false) Integer getdoblack,
			@RequestParam(required=false) Integer getgetblack,
			@RequestParam(required=false) String getgetblackres,
			@RequestParam(required=false) String getdoblackres
			) {
//		logger.info("/viewreportboard [GET]");
//		logger.info("{}",boreportNo);
//		logger.info("{}",doboardNo);
//		logger.info("{}",getdoblack);
//		logger.info("{}",getdoblackres);
//		logger.info("{}",getgetblack);
//		logger.info("{}",getgetblackres);
		//전달받은 내용들을 바탕으로 blacklist를 추가하고 reportboard의 처리여부를 바꾸고, 보드타입도 바꾸는 메소드
		adminService.changeReportBoard(boreportNo,doboardNo,getdoblack,getgetblack,getdoblackres,getgetblackres);
		
		return "redirect:/admin/reportboard/list";
	}
	



	//blacklist 수정
	@GetMapping("/blacklist/list")
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
		
		
		return "redirect:/admin/blacklist/list";
		
	}
	
	@GetMapping("/shop/list")
	public void ShopList() {
		
	}
	
	@GetMapping("/shop/write")
	public void addShopObject() {
		
	}
	@PostMapping("/shop/write")
	public String addShopObjectProc(
			Shop shop, 
			MultipartFile shopFile,
			ShopFile shopfile
			) {
		logger.info("/shop/write [post]");
		
		logger.info("Shop = {}", shop);
		logger.info("shopFile = {}", shopFile);
		adminService.shopContentInsert(shop,shopFile,shopfile);
		
		
		return "redirect:/admin/shop/list";
	}
	
	//
	@GetMapping("/blacklist/insert")
	public void blacklistdelete(@RequestParam(value="delete",required=false) Blacklist blacklist) {
		
		
		logger.info("blacklist = {}", blacklist);
		adminService.insertblacklist(blacklist);
		
		
		
	}
	
	public void test11() {
		logger.info("123");
	}
	
	@GetMapping("/reportcomment/list")
	public void reportComment(@RequestParam(defaultValue = "0") int curPage, Model model) {
		logger.info("/reportboard [GET}");
		logger.info("curPage = {}", curPage);
		AdminPaging paging = new AdminPaging();
		
		paging = adminService.getPageComment(curPage);
		
//		logger.info("paging = {}", paging);
		
		List<ReportComment> list = adminService.getReportComment(paging);
		
		model.addAttribute("list",list);
		model.addAttribute("paging",paging);
		
	}
	
	@GetMapping("/reportcomment/delete")
	public String deleteCommentReport(@RequestParam(value="delete",required=false) List<String> delete) {
//		logger.info("delete = {}", delete);
		adminService.deleteCheckedComment(delete);
		
		
		return "redirect:/admin/reportcomment/list";
	}
	
	@GetMapping("/reportcomment/view")
	public void viewReportComment(String coreportNo, Model model, ReportComment reportComment, 
			// 신고자
			Member domember, 
			//피신고자
			Member getmember,
			CommentTable comment
			) {
		
		reportComment = adminService.getReportViewComment(coreportNo);
		//신고자 정보
		domember = adminService.getDoMember(reportComment.getUserNo());
		//피신고자 정보
		getmember = adminService.getGetMemberComment(reportComment.getCommentNo());
		//신고한 게시글 상세정보
		comment = adminService.getComment(reportComment.getCommentNo());
		
		model.addAttribute("list", reportComment);
		model.addAttribute("domember", domember);
		model.addAttribute("getmember", getmember);
		model.addAttribute("comment", comment);
		
	}
	
	@GetMapping("/reportcomment/commentdetail")
	public void viewRefComment(String commentno, CommentTable comment, Model model) {
		comment = adminService.getComment(Integer.valueOf(commentno));
		model.addAttribute("comment", comment);
	}
	
	@PostMapping("/reportcomment/view/delete")
	public String deleteViewReportComment(
			Integer coreportNo, 
			@RequestParam(required=false) Integer docommentNo,
			@RequestParam(required=false) Integer getdoblack,
			@RequestParam(required=false) Integer getgetblack,
			@RequestParam(required=false) String getgetblackres,
			@RequestParam(required=false) String getdoblackres
			) {

		adminService.changeReportComment(coreportNo,docommentNo,getdoblack,getgetblack,getdoblackres,getgetblackres);
		
		return "redirect:/admin/reportcomment/list";
	}
	
}
