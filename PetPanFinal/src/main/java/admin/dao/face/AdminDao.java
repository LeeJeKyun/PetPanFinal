package admin.dao.face;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.dto.Blacklist;
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

public interface AdminDao {

	public List<ReportBoard> ReportBoardtselectAll(AdminPaging paging);
	
	/**
	 * 받아온 검색어를 바탕으로 총 페이지 수를 반환한다.
	 * 
	 * @param search
	 * @return AdminPaging의 totalpage값
	 */
	public int selectTotalSearch(String search);
	
	/**
	 * 댓글 신고의 총 페이지를 받아온다.
	 * @return
	 */
	public int selectTotalComment();
	
	/**
	 * boreportno를 바탕으로 상세정보를 반환한다.
	 * @param boreportno
	 * @return ReportBoard
	 */
	public ReportBoard selectReportInfo(int boreportno);
	
	/**
	 * userNo로 그 유저의 상세 정보를 가져오는 메소드
	 * @param userNo
	 * @return
	 */
	public Member selectMember(int userNo);

	/**
	 * boardNo를 바탕으로 작성자의 정보를 가져오는 메소드
	 * @param boardNo
	 * @return
	 */
	public int selectBoarduser(int boardNo);

	/**
	 * boardNo를 바탕으로 신고 받은 게시글의 상세정보를 꺼내온다.
	 * @param boardNo
	 * @return
	 */
	public Board selectBoardDetail(int boardNo);

	// 개발 도중 사용을 멈춘 메소드
//	public void DeleteReport(int deleteNo);

	public List<Map<String, Object>> BlacklistselectAll(AdminPaging paging);
	
	public int selectObjectNo();

	public void insertObjectContent(Shop shop);

	public void insertFile(ShopFile shopfile);
	/**
	 * delete로 받아온 번호를 바탕으로 해당하는 번호에 연결되어 있는 boardno를 반환한다.
	 *
	 * 
	 * @param deleteNo
	 * @return boardno
	 */
	public int selectBoardNo(int deleteNo);

	/**
	 * boardno가 있을 경우에 해당 boardno의 게시판의 게시판 타입을 보이지 않는 5번으로 바꾼다.
	 * @param boardno
	 */
	public void updateBoardtype(int boardno);
	
	/**
	 * 받아온 report의 번호를 바탕으로 N을 Y로 바꾸도록 한다.
	 * @param deleteNo
	 */
	public void updateReportComplete(int deleteNo);

	public int deleteBlacklist(int deleteNo);

	/**
	 * blacklist에 정보가 들어 있을 경우 해당하는 번호의 유저를 블랙리스트에 추가한다.
	 * @param blacklist
	 */
	public void insertBlacklist(Blacklist blacklist);

	public List<Member> MemberselectAll(AdminPaging paging);

	public List<Member> MemberselectByKeyword(Map<String, Object> map);

	/**
	 * 
	 * 페이징을 통해 댓글 총 목록을 반환한다.
	 * @param paging
	 * @return List<ReportComment>
	 */
	public List<ReportComment> ReportCommentselectAll(AdminPaging paging);

	/**
	 * deleteNo = comreportno를 바탕으로 연결된 commentno를 반환한다.
	 * @param deleteNo
	 * @return commentno
	 */
	public int selectCommentNo(int deleteNo);

	/**
	 * commentno를 바탕으로 해당 댓글을 관리자에 의해 삭제된 댓글 입니다로 내용을 바꾸어준다.
	 * @param commentno
	 */
	public void updateReportComment(int commentno);

	/**
	 * deleteNo = comreportno를 바탕으로 해당 댓글을 처리완료 Y로 바꾼다.
	 * @param deleteNo
	 */
	public void updateReportCompleteComment(int deleteNo);

	/**
	 * coreportNo를 바탕으로 상세정보를 select해서 반환한다.
	 * @param coreportNo
	 * @return ReportComment
	 */
	public ReportComment selectReportInfoComment(String coreportNo);

	public Comment selectCommentDetail(int commentNo);

	/**
	 * 
	 * commentNo를 바탕으로 댓글을 작성한 유저의 정보를 꺼내온다.
	 * @param commentNo
	 * @return
	 */
	public int selectCommentuser(int commentNo);

	/**
	 * 검색을 바탕으로 보여줘야 할 신고 게시글을 전부 반환 한다.
	 * @param paging
	 * @return List<ReportBoard>
	 */
	public List<ReportBoard> ReportBoardSearchselectAll(AdminPaging paging);

	public int selectTotal();

	public List<Notice> SelectBytheme(int theme);

	public void insertNotice(Notice notice);

	public void insertNoticeFile(NoticeFile noticeFile);
	
	/**
	 * 
	 * 저장한 deleteno로 N이 였던 처리결과를 Y로 바꾼다.
	 * @param map
	 */
	public void updateBoardtypeAndReportComplete(HashMap<String, Integer> map);
	
	/**
	 * 저장한 boardmap을 통해서 동적쿼리를 발동시켜 체크되어 있는 컬럼에 연결된 모든 게시글의 게시판번호를 5번으로 바꾸어 화면에 드러나지 않도록 한다.
	 * 
	 * @param boardnoMaplist
	 */
	public void updateBoardtypelist(List<HashMap> boardnoMaplist);

	public void updateReportCompletelist(List<HashMap> deleteNoMaplist);

	/**
	 * 저장한 commentno의 Map list를 통해 동적쿼리로 comment컬럼을 수정한다.
	 * "관리자에 의해 삭제된 댓글입니다." 로 출력할 수 있도록 한다. 사용자는 바꾸지 않는다.
	 * @param commentnoMaplist
	 */
	public void updateCommentTypelist(List<HashMap> commentnoMaplist);

	/**
	 * deleteNo = commentNo를 바탕으로 신고처리를 N에서 Y로 한번에 동적쿼리로 바꾼다.
	 * @param deleteNoMaplist
	 */
	public void updateReportCompleteCommentlist(List<HashMap> Maplist);

	public int updateMember(Member member);

	public Notice selectBynoticeno(int noticeno);

	public List<NoticeFile> selectnoticeFile(int noticeno);

	public void deletenoticeFile(int noticeno);

	public void deletenotice(int noticeno);

	public void updatenoticeDetail(Notice notice);

	public void deleteFileByFileno(int e);

	public NoticeFile selectnoticeFileByFileno(int e);

	public int selectAllBlacklist();

	public int selectAllMember();

	public int selectSearchMember(String keyword);

	public void updateMemberToAdmin(int userno);


	


}
