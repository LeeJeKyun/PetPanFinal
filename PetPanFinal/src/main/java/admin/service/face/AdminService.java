package admin.service.face;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import admin.dto.Blacklist;
import admin.dto.ReportBoard;
import admin.dto.ReportComment;
import board.dto.Board;
import board.dto.Comment;
import board.dto.Notice;
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

	public List<Blacklist> getBlacklistBoard(AdminPaging paging);

	public void deleteblacklist(List<String> delete);

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

	public int saveShopGetObjectno(Shop shop);
	
	public List<Notice> getNoticeListByType(int theme);

	public void saveShopFiles(List<MultipartFile> fileList, int objectno, List<Integer> no);

	public Shop getShopDetailByobjectno(Integer objectno);

	public List<ShopFile> getshopFileByobjectno(Integer objectno);

	public void changeAndDeleteFile(List<Integer> delete, List<Integer> save);

	public void changeShop(Shop shop, Integer objectno);

	public void writeNotice(List<MultipartFile> fileList, Notice notice);

	public void stopShop(Integer objectno);

	public List<HashMap<String, Object>> getBuyerList(AdminPaging adminPaging);

	public AdminPaging getPageBuyer(int curPage, String search);

	public void completeCheckedBuyer(List<String> delete);



	
	

}
