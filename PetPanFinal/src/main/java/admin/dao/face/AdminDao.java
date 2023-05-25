package admin.dao.face;

import java.util.List;

import org.springframework.ui.Model;

import admin.dto.Blacklist;
import admin.dto.ReportBoard;
import board.dto.Board;
import member.dto.Member;
import shop.dto.Shop;
import shop.dto.ShopFile;
import util.Paging;

public interface AdminDao {

	public List<ReportBoard> ReportBoardtselectAll(Paging paging);

	public int selectTotal();

	public ReportBoard selectReportInfo(int boreportno);

	public Member selectMember(int userNo);

	public int selectBoarduser(int boardNo);

	public Board selectBoardDetail(int boardNo);

	// 개발 도중 사용을 멈춘 메소드
//	public void DeleteReport(int deleteNo);

	public List<Blacklist> BlacklistselectAll(Paging paging);
	
	public int selectObjectNo();

	public void insertObjectContent(Shop shop);

	public void insertFile(ShopFile shopfile);

	public int selectBoardNo(int deleteNo);

	public void updateBoardtype(int boardno);

	public void updateReportComplete(int deleteNo);

	public int deleteBlacklist(int deleteNo);

	public void insertBlacklist(Blacklist blacklist);


}
