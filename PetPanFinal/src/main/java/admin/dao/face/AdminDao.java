package admin.dao.face;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

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

public interface AdminDao {

	public List<ReportBoard> ReportBoardtselectAll(AdminPaging paging);

	public int selectTotal();
	
	public int selectTotalComment();

	public ReportBoard selectReportInfo(int boreportno);

	public Member selectMember(int userNo);

	public int selectBoarduser(int boardNo);

	public Board selectBoardDetail(int boardNo);

	// 개발 도중 사용을 멈춘 메소드
//	public void DeleteReport(int deleteNo);

	public List<Blacklist> BlacklistselectAll(AdminPaging paging);
	
	public int selectObjectNo();

	public void insertObjectContent(Shop shop);

	public void insertFile(ShopFile shopfile);

	public int selectBoardNo(int deleteNo);

	public void updateBoardtype(int boardno);

	public void updateReportComplete(int deleteNo);

	public int deleteBlacklist(int deleteNo);

	public void insertBlacklist(Blacklist blacklist);

	public List<Member> MemberselectAll(Paging paging);

	public List<Member> MemberselectByKeyword(Map<String, Object> map);

	public List<ReportComment> ReportCommentselectAll(AdminPaging paging);

	public int selectCommentNo(int deleteNo);

	public void updateReportComment(int commentno);

	public void updateReportCompleteComment(int deleteNo);

	public ReportComment selectReportInfoComment(String coreportNo);

	public CommentTable selectCommentDetail(int commentNo);

	public int selectCommentuser(int commentNo);


}
