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

import admin.dto.ReportComment;
import admin.service.face.AdminService;
import board.dto.Comment;
import member.dto.Member;
import util.AdminPaging;

@RequestMapping("/admin/reportcomment")
@Controller
public class AdminReportCommentController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired AdminService adminService;
	
	@GetMapping("/list")
	public void reportComment(@RequestParam(defaultValue = "0") int curPage, Model model) {
//		logger.info("/reportboard [GET}");
//		logger.info("curPage = {}", curPage);
		AdminPaging paging = new AdminPaging();
		
		paging = adminService.getPageComment(curPage);
		
//		logger.info("paging = {}", paging);
		
		List<ReportComment> list = adminService.getReportComment(paging);
		
		model.addAttribute("list",list);
		model.addAttribute("paging",paging);
		
	}
	
	@GetMapping("/delete")
	public String deleteCommentReport(@RequestParam(value="delete",required=false) List<String> delete) {
//		logger.info("delete = {}", delete);
		adminService.deleteCheckedComment(delete);
		
		
		return "redirect:/admin/reportcomment/list";
	}
	
	@GetMapping("/view")
	public void viewReportComment(String coreportNo, Model model, ReportComment reportComment, 
			// 신고자
			Member domember, 
			//피신고자
			Member getmember,
			Comment comment
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
	
	@GetMapping("/commentdetail")
	public void viewRefComment(String commentno, Comment comment, Model model) {
		comment = adminService.getComment(Integer.valueOf(commentno));
		model.addAttribute("comment", comment);
	}
	
	@PostMapping("/view/delete")
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
