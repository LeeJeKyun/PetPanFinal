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

import admin.dto.ReportBoard;
import admin.service.face.AdminService;
import board.dto.Board;
import member.dto.Member;
import util.AdminPaging;

@RequestMapping("/admin/reportboard")
@Controller
public class AdminReportBoardController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired AdminService adminService;
	
	@GetMapping("/list")
	public void reportBoard(@RequestParam(defaultValue = "1") int curPage, 
			Model model, 
			@RequestParam(required=false,defaultValue = "")String search) {

//		logger.info("/reportboard [GET}");
//		logger.info("curPage = {}", curPage);
		// 어드민 페이징 객체 생성
		AdminPaging paging = new AdminPaging();

//		logger.info("/reportboard [GET}");
//		logger.info("curPage = {}", curPage);
	
		// 받아온 search 검색값과 현재 페이지 값을 바탕으로 총 페이징을 완성한다.
		paging = adminService.getPage(curPage,search);
//		logger.info(search);
		
//		logger.info("paging = {}", paging);
		List<ReportBoard> list;

		list = adminService.getSearchReportBoard(paging);
		
		
		model.addAttribute("search",search);
		model.addAttribute("list",list);
		model.addAttribute("paging",paging);
		
	}
	
	@GetMapping("/delete")
	public String deleteBoardReport(@RequestParam(value="delete",required=false) List<String> delete) {
//		logger.info("delete = {}", delete);
		adminService.deleteChecked(delete);
		
		
		return "redirect:/admin/reportboard/list";
	}
	
	@GetMapping("/view")
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
		
		// 차후 추가한 메소드 ( 블랙리스트에 사람이 있을 경우에 disable로 바꾸기 위한 변수)
		int doblack = adminService.findBlack(domember.getUserNo());
		int getblack = adminService.findBlack(getmember.getUserNo());
		
		model.addAttribute("list", reportboard);
		model.addAttribute("domember", domember);
		model.addAttribute("getmember", getmember);
		model.addAttribute("doblack", doblack);
		model.addAttribute("getblack", getblack);
		model.addAttribute("board", board);
		
		
	}
	@PostMapping("/view/delete")
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

}
