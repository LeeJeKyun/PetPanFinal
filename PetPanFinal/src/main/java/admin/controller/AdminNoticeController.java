package admin.controller;

import java.util.List;

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

import admin.service.face.AdminService;
import board.dto.Notice;
import board.dto.NoticeFile;
import shop.dto.Shop;

@RequestMapping("/admin")
@Controller
public class AdminNoticeController {
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired AdminService adminService;
	
	
	
	
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
	@GetMapping("/notice/view")
	public void board(  Model model	,@RequestParam(value = "noticeNo")int noticeno, HttpSession session) {
		
			Notice notice = adminService.getNotice(noticeno);
			List<NoticeFile> fileList = adminService.getNotiaceFilelist(noticeno);
		    model.addAttribute("notice", notice);
		    model.addAttribute("fileList", fileList);
		
	}
//	
//	
	@GetMapping("/notice/write")
	public void write() {
		
	}
//	
	@PostMapping("/notice/write")
	public String writePost(
			@RequestParam(value = "file", required = false)List<MultipartFile> fileList //올릴 파일 리스트
			, Notice notice
			, HttpSession session
			, @RequestParam(required = false) List<Integer> no // 취소된 파일 -1 
			) {
		 
		
		
		
		if(notice.getNoticecontent() == null || notice.getNoticecontent() == null)
			return "redirect:./list";
		// 

		
		logger.info("notice : {}", notice);
		adminService.writeNotice(fileList, notice, no,session);
		
		logger.info("write post");
		
		logger.info("no : {}", no);
		logger.info("fileList : {}", fileList);
		logger.info("notice : {}", notice);
		
		
		return "redirect:./list";
	}
	
	@GetMapping("/notice/delete")
	public String delete(@RequestParam(value = "noticeNo")int noticeno) {
		
		
		adminService.deletenotice(noticeno);
		
		return "redirect:./list";
		
	}
	
	
	@GetMapping("/notice/update")
	public void update(Model model	,@RequestParam(value = "noticeNo")int noticeno, HttpSession session) {
		
		Notice notice = adminService.getNotice(noticeno);
		List<NoticeFile> fileList = adminService.getNotiaceFilelist(noticeno);
	    model.addAttribute("notice", notice);
	    model.addAttribute("fileList", fileList);
		
	}
	
	@PostMapping("/notice/update")
	public String updatepost(Integer noticeNo,
			@RequestParam(value = "file", required = false)List<MultipartFile> fileList //올릴 파일 리스트
			, Notice notice
			, @RequestParam(required = false) List<Integer> no // 취소된 파일 -1
			, @RequestParam(required = false) List<Integer> delete
			, @RequestParam(required = false) List<Integer> save ) {
		
		
		System.out.println(notice);
		
		if(notice.getNoticecontent() == null || notice.getNoticetitle() == null )
			return "redirect:/admin/shop/list";
			
			adminService.noticechangeAndDeleteFile(delete,save);
	
			adminService.changeNotice(notice);
			
			adminService.saveNoticeFiles(fileList, notice, no);
		
		
		return "redirect:./view?noticeNo=" + Integer.toString(notice.getNoticeno());
	}
	
	
	
}
