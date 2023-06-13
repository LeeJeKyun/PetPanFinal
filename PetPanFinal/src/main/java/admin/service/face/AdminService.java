package admin.service.face;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.WebSocketMessage;

import admin.dto.Blacklist;
import admin.dto.ChatMsg;
import admin.dto.ReportBoard;
import admin.dto.ReportComment;
import admin.dto.ReportObject;
import board.dto.Board;
import board.dto.Comment;
import board.dto.Notice;
import board.dto.NoticeFile;
import member.dto.Member;
import shop.dto.Shop;
import shop.dto.ShopFile;
import util.AdminPaging;

public interface AdminService {

	public List<ReportBoard> getReportBoard(AdminPaging paging);

	public AdminPaging getPage(int curPage);

	public ReportBoard getReportView(String boreportNo);

	public Member getDoMember(int userNo);

	public Member getGetMember(int boardNo);

	public Board getBoard(int boardNo);

	public void deleteChecked(List<String> delete);
	/**
	 * 
	 * @param paging
	 * @return blacklist 목록 가져오기 (username 포함)
	 */
	public List<Map<String, Object>> getBlacklistBoard(AdminPaging paging);
	
	/**
	 * 한꺼번에 blacklist삭제
	 * @param delete
	 */
	
	public void deleteblacklist(List<String> delete);
	/**
	 * 블랙리스트 삽입
	 * @param blacklist
	 */
	public void insertblacklist(Blacklist blacklist);

	
	public void shopContentInsert(Shop shop, MultipartFile shopFile, ShopFile shopfile);

	public void changeReportBoard(Integer boreportNo, Integer doboardNo, Integer getdoblack, Integer getgetblack,
			String getdoblackres, String getgetblackres);

	public List<Member> getMemberBoard(AdminPaging paging);

	public List<Member> getsearchMemberBoard(AdminPaging paging, String keyword);

	public List<ReportComment> getReportComment(AdminPaging paging);

	public AdminPaging getPageComment(int curPage);

	public void deleteCheckedComment(List<String> delete);

	public ReportComment getReportViewComment(String coreportNo);

	public Comment getComment(int commentNo);

	public void changeReportComment(Integer coreportNo, Integer docommentNo, Integer getdoblack, Integer getgetblack,
			String getdoblackres, String getgetblackres);

	public Member getGetMemberComment(int commentNo);

	public List<ReportBoard> getSearchReportBoard(AdminPaging paging);

	public AdminPaging getPage(int curPage, String search);

	public AdminPaging getShopPage(int curPage, String search);

	public List<Shop> getSearchShopBoard(AdminPaging paging);

	public void deleteCheckedShop(List<String> delete);

	public int saveShopGetObjectno(Shop shop, MultipartFile img1);
	/**
	 * 게시판 번호별 list 
	 * @param theme
	 * @return
	 */
	public List<Notice> getNoticeListByType(int theme);

	public void saveShopFiles(List<MultipartFile> fileList, int objectno, List<Integer> no);

	public Shop getShopDetailByobjectno(Integer objectno);

	public List<ShopFile> getshopFileByobjectno(Integer objectno);

	public void changeAndDeleteFile(List<Integer> delete, List<Integer> save);

	public void changeShop(Shop shop, Integer objectno);
	/**
	 * 
	 * @param fileList
	 * @param notice
	 * @param no
	 * @param session
	 */
	public void writeNotice(List<MultipartFile> fileList, Notice notice, List<Integer> no, HttpSession session);

	public void stopShop(Integer objectno);

	public List<HashMap<String, Object>> getBuyerList(AdminPaging adminPaging);

	public AdminPaging getPageBuyer(int curPage, String search);

	public void completeCheckedBuyer(List<String> delete);
	/**
	 * 멤버 업데이트
	 * @param member
	 */
	public void updateMember(Member member);

	public AdminPaging getPagereportShop(int curPage, String search);

	public List<ReportObject> getReportShopList(AdminPaging paging);

	public ReportObject getReportObject(Integer objreportNo);

	public Member getShopReportMember(ReportObject reportObject);

	public void changeObjReportAndAddBlack(Integer objreportNo, Integer objectNo, Integer userNo, String reason);

	public void changeObjReport(List<String> delete);
	/**
	 * 공지 가져오기
	 * @param noticeno
	 * @return
	 */
	public Notice getNotice(int noticeno);

	public List<NoticeFile> getNotiaceFilelist(int noticeno);
	/**
	 * 공지 삭제하기
	 * @param noticeno
	 */
	public void deletenotice(int noticeno);

	public void noticechangeAndDeleteFile(List<Integer> delete, List<Integer> save);

	public void changeNotice(Notice notice);

	public void saveNoticeFiles(List<MultipartFile> fileList, Notice notice, List<Integer> no);
	/**
	 * 
	 * @param curPage
	 * @return
	 */
	public AdminPaging getBlacklistPage(int curPage);
	/**
	 * 
	 * @param curPage
	 * @return
	 */
	public AdminPaging getmemberPage(int curPage);
	/**
	 * 
	 * @param curPage
	 * @param keyword
	 * @return
	 */
	public AdminPaging getSearchMemberPage(int curPage, String keyword);

	public void resellCheckedShop(List<String> resell);
	
	
	



	
	

}
