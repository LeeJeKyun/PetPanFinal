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
	
	/**
	 * boreportNo를 바탕으로 해당 신고의 상세정보를 가져온다.
	 * @param boreportNo
	 * @return
	 */
	public ReportBoard getReportView(String boreportNo);
	
	/**
	 * 신고 테이블의 신고자 번호를 바탕으로 신고자의 정보를 가져온다.
	 * @param userNo
	 * @return
	 */
	public Member getDoMember(int userNo);
	
	/**
	 * 신고 테이블의 번호를 바탕으로 피신고자의 정보를 가져온다.
	 * @param boardNo
	 * @return
	 */
	public Member getGetMember(int boardNo);

	/**
	 * boardNo를 바탕으로 신고 받은 게시글이 어떤 게시글인지 상세정보를 가져온다.
	 * @param boardNo
	 * @return
	 */
	public Board getBoard(int boardNo);
	
	/**
	 * 체크 되어 있는 번호를 delete list로 받아서 체크되어있는 신고를 완료 처리하도록 한다.
	 * @param delete
	 */
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

	/**
	 * boreportNo를 통해서 처리 여부를 바꾸고,
	 * doboardNo로 게시글의 게시 여부를 바꾸고.
	 * 나머지를 바탕으로 유저를 블랙 처리할 것인지를 결정한다.
	 * 
	 * @param boreportNo
	 * @param doboardNo
	 * @param getdoblack
	 * @param getgetblack
	 * @param getdoblackres
	 * @param getgetblackres
	 */
	public void changeReportBoard(Integer boreportNo, Integer doboardNo, Integer getdoblack, Integer getgetblack,
			String getdoblackres, String getgetblackres);

	public List<Member> getMemberBoard(AdminPaging paging);

	public List<Member> getsearchMemberBoard(AdminPaging paging, String keyword);

	/**
	 * 
	 * 받아온 댓글 신고의 paging을 바탕으로 댓글신고의 전체리스트를 반환한다.
	 * @param paging
	 * @return
	 */
	public List<ReportComment> getReportComment(AdminPaging paging);

	/**
	 * 댓글 신고의 페이징을 담아오는 객체
	 * 
	 * @param curPage
	 * @return AdminPaging
	 */
	public AdminPaging getPageComment(int curPage);

	/**
	 * delete에 저장된 값을 바탕으로 체크된 댓글 신고를 처리한다.
	 * @param delete
	 */
	public void deleteCheckedComment(List<String> delete);

	/**
	 * coreportNo를 바탕으로  댓글 신고의 상세 정보를 조회한다.
	 * @param coreportNo
	 * @return ReportComment
	 */
	public ReportComment getReportViewComment(String coreportNo);

	/**
	 * commentNo 를 바탕으로 해당 댓글의 상세정보를 반환한다.
	 * @param commentNo
	 * @return ReportComment
	 */
	public Comment getComment(int commentNo);

	/**
	 * coreportNo를 통해서 N의 처리완료 값을 Y로 바꾸고
	 * 아래 나머지 값들이 들어왔다면 그 값들을 처리한다.
	 * 
	 * @param coreportNo
	 * @param docommentNo
	 * @param getdoblack
	 * @param getgetblack
	 * @param getdoblackres
	 * @param getgetblackres
	 */
	public void changeReportComment(Integer coreportNo, Integer docommentNo, Integer getdoblack, Integer getgetblack,
			String getdoblackres, String getgetblackres);

	/**
	 * commentNo를 바탕으로 그 글의 작성자의 정보를 반환한다.
	 * @param commentNo
	 * @return Member
	 */
	public Member getGetMemberComment(int commentNo);
	
	/**
	 * 현재 총 페이지의 수와 현재 페이지 검색어를 바탕으로 list를 반환한다.
	 * @param paging
	 * @return List<ReportBoard>
	 */
	public List<ReportBoard> getSearchReportBoard(AdminPaging paging);

	/**
	 * 검색어와 현재페이지를 바탕으로 페이징 객체를 반환한다.
	 * 
	 * @param curPage
	 * @param search
	 * @return AdminPaging
	 */
	public AdminPaging getPage(int curPage, String search);

	/**
	 * 검색어와 현재페이지를 기반으로 페이징 객체를 반환한다.
	 * @param curPage
	 * @param search
	 * @return AdminPaging
	 */
	public AdminPaging getShopPage(int curPage, String search);

	/**
	 * 현재 페이지와 총량이 저장되어서 계산된 페이징 객체를 보내서 상점의 리스트를 띄워준다
	 * @param paging
	 * @return List<Shop>
	 */
	public List<Shop> getSearchShopBoard(AdminPaging paging);

	public void deleteCheckedShop(List<String> delete);

	/**
	 * 받아온 상점 객체와 대표사진을 DB로 전달하고 그리고 파일 테이블에 추가할 objectno를 반환한다.
	 * 
	 * @param shop
	 * @param img1
	 * @return int objectno
	 */
	public int saveShopGetObjectno(Shop shop, MultipartFile img1);
	/**
	 * 게시판 번호별 list 
	 * @param theme
	 * @return
	 */
	public List<Notice> getNoticeListByType(int theme);

	/**
	 * 저장되어 있는 fileList와 그 인덱스의 파일들을 저장할 것인지 정하는 no리스트를 보내고, 어디 상점에 추가할지 지정해주는 objectno를 같이 보내
	 * 저장한다.
	 * @param fileList
	 * @param objectno
	 * @param no
	 */
	public void saveShopFiles(List<MultipartFile> fileList, int objectno, List<Integer> no);

	/**
	 * objectno를 바탕으로 해당 objectno에 있는 shop의 정보를 반환한다.
	 * @param objectno
	 * @return Shop
	 */
	public Shop getShopDetailByobjectno(Integer objectno);

	/**
	 * objectno를 바탕으로 그 objectno에 연결된 파일들을 불러온다.
	 * @param objectno
	 * @return List<ShopFile>
	 */
	public List<ShopFile> getshopFileByobjectno(Integer objectno);

	/**
	 * delete로 전체저장되었던 파일을 저장하고
	 * save로 그중 어떤 파일은 남겨야하는 지를 저장한다.
	 * save의 인덱스와 같은 파일들은 남기고 나머지 파일들을 삭제한다.
	 * 
	 * @param delete
	 * @param save
	 */
	public void changeAndDeleteFile(List<Integer> delete, List<Integer> save);

	/**
	 * 새로 받아온 shop을 바탕으로 shop의 내용을 수정할 수 있다. 대표사진은 수정할 수 없다.
	 * @param shop
	 * @param objectno
	 */
	public void changeShop(Shop shop, Integer objectno);
	/**
	 * 
	 * @param fileList
	 * @param notice
	 * @param no
	 * @param session
	 */
	public void writeNotice(List<MultipartFile> fileList, Notice notice, List<Integer> no, HttpSession session);

	/**
	 * view에서 objectno를 바탕으로 지금 판매중이던 상점을 중단 시킨다.
	 * @param objectno
	 */
	public void stopShop(Integer objectno);

	/**
	 * paging 객체를 이용하여 조건에 맞는 구매자의 전체 리스트를 반환한다.
	 * @param adminPaging
	 * @return
	 */
	public List<HashMap<String, Object>> getBuyerList(AdminPaging adminPaging);

	/**
	 * 
	 * 검색어와 현재 페이지를 바탕으로 페이징 객체를 리턴한다.
	 * @param curPage
	 * @param search
	 * @return
	 */
	public AdminPaging getPageBuyer(int curPage, String search);

	/**
	 * 체크되어 돌아온 deletelist = buynolist로 판매자의 배송상태를 변경한다.
	 * @param delete
	 */
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

	/**
	 * 체크 표시로 받아온 resell 리스트를 보내서 판매를 재개한다.
	 * @param resell
	 */
	public void resellCheckedShop(List<String> resell);
	
	/**
	 * 
	 * @param userno
	 */
	public void appointmentAdmin(int userno);

	/**
	 * 
	 * @param userNo
	 * @return
	 */
	public int findBlack(int userNo);



	
	

}
