package admin.service.face;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import admin.dto.Blacklist;
import admin.dto.ReportBoard;
import board.dto.Board;
import member.dto.Member;
import shop.dto.Shop;
import shop.dto.ShopFile;
import util.Paging;

public interface AdminService {

	public List<ReportBoard> getReportBoard(Paging paging);

	public Paging getPage(int curPage);

	public ReportBoard getReportView(String boreportNo);

	public Member getDoMember(int userNo);

	public Member getGetMember(int boardNo);

	public Board getBoard(int boardNo);

	public void deleteChecked(List<String> delete);

	public List<Blacklist> getBlacklistBoard(Paging paging);

	public void deleteblacklist(List<String> delete);

	public void insertblacklist(Blacklist blacklist);

	
	public void shopContentInsert(Shop shop, MultipartFile shopFile, ShopFile shopfile);

	public void changeReportBoard(Integer boreportNo, Integer doboardNo, Integer getdoblack, Integer getgetblack,
			String getdoblackres, String getgetblackres);

	public List<Member> getMemberBoard(Paging paging);

	public List<Member> getsearchMemberBoard(Paging paging, String keyword);

	
	

}
