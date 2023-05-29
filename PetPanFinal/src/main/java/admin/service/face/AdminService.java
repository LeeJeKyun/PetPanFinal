package admin.service.face;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import admin.dto.Blacklist;
import admin.dto.ReportBoard;
import admin.dto.ReportComment;
import board.dto.Board;
import board.dto.CommentTable;
import member.dto.Member;
import shop.dto.Shop;
import shop.dto.ShopFile;
import util.AdminPaging;
import util.Paging;

public interface AdminService {

	public List<ReportBoard> getReportBoard(AdminPaging paging);

	public AdminPaging getPage(int curPage, String search);

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

	public CommentTable getComment(int commentNo);

	public void changeReportComment(Integer coreportNo, Integer docommentNo, Integer getdoblack, Integer getgetblack,
			String getdoblackres, String getgetblackres);

	public Member getGetMemberComment(int commentNo);

	public List<ReportBoard> getSearchReportBoard(AdminPaging paging);

	public AdminPaging getPage(int curPage);

	public AdminPaging getShopPage(int curPage, String search);

	public List<Shop> getSearchShopBoard(AdminPaging paging);

	public void deleteCheckedShop(List<String> delete);

	public int saveShopGetObjectno(Shop shop);



	
	

}
