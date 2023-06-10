package board.service.face;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import board.dto.Board;
import board.dto.BoardFile;
import board.dto.BoardRecommend;
import board.dto.Comment;
import board.dto.HospitalFile;
import board.dto.Notice;
import board.dto.NoticeFile;
import board.dto.ReportBoard;
import board.dto.ReportComment;
import member.dto.Hospital;
import member.dto.Member;
import util.HospitalPaging;
import util.Paging;

public interface BoardService {
	

	/**
	 * 현재 페이지를 기반으로 Notice의 Paging객체를 반환하는 메소드
	 * 
	 * @param curPage
	 * @return
	 */
	public Paging getNoticePaging(int curPage, String search);

	/**
	 * 페이징객체를 기반으로 공지사항 List를 반환하는 메소드
	 * 
	 * @param paging
	 * @return
	 */
	public List<Map<String, Object>> getNoticeList(Paging paging);

	/**
	 * noticeno을 기준으로 공지사항을 상세보기 하는 메소드
	 * 
	 * @param noticeno
	 * @return
	 */
	public Notice getNoticeView(int noticeno);

	/**
	 * 현재 페이지를 기반으로 Care의 Paging객체를 반환하는 메소드
	 * 
	 * @param curPage
	 * @param search
	 * @return
	 */
	public Paging getCarePaging(int curPage, String search);

	/**
	 * 페이징객체를 기반으로 care게시판의 list를 반환하는 메소드
	 * 
	 * @param paging
	 * @return
	 */
	public List<Map<String, Object>> getCareList(Paging paging);
	
	/**
	 * 품앗이 상세보기 페이지의 보드 정보 가져오기
	 * 
	 * @param boardNo
	 * @return
	 */
	public Map<String, Object> getCareView(int boardNo);
	
	/**
	 * 품앗이 상세보기 페이지의 boardFile정보 가져오기
	 * 
	 * @param boardNo
	 * @return
	 */
	public List<BoardFile> getCareFile(int boardNo);

	/**
	 * 게시글에 추천하는 메소드
	 * 내부적으로 추천을 했던 데이터가 있는 경우 추천하지않고
	 * 추천을 하지 않았을 경우 추천된다.
	 * 
	 * @param boardNo
	 * @param userNo
	 */
	public void recommendBoardCare(int boardNo, int userNo);

	/**
	 * 추천수를 가져오는 메소드
	 * 
	 * @param boardNo
	 */
	public int getRecommendCnt(int boardNo);

	/**
	 * 현재 로그인한 유저가 추천한 적이 있는 경우 true
	 * 
	 * @param boardNo
	 * @param loginid
	 * @return
	 */
	public boolean isRecommended(int boardNo, int userNo);
	
	/**
	 * 댓글을 DB에 입력해주는 메소드
	 * 
	 * @param commentTable
	 */
	public void inputComment(Comment comment);

	/**
	 * 댓글 리스트를 모두 가져오는 메소드
	 * 
	 * @param boardno
	 * @return
	 */
	public List<Map<String, Object>> getCommentList(int boardno);

	/**
	 * 품앗이 게시판에 띄울 공지사항을 가져오는 메소드
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getNoticeListToCare();
	
	/**
	 * 게시글을 지우는 메소드
	 * 
	 * @param board
	 */
	public void deleteBoardByBoardObj(Board board);

	/**
	 * 게시글을 올린 사람의 위,경도를 가져오는 메소드
	 * 
	 * @param map
	 * @return
	 */
	public Member getMemberByBoard(Map<String, Object> map);
	
	/**
	 * 두 멤버의 거리를 계산하여 반환하는 메소드
	 * 
	 * @param loginMember
	 * @param writerMember
	 */
	public double getDistance(Member loginMember, Member writerMember);

	/**
	 * 로그인한 유저 주변의 게시글만 가져오는 메소드
	 * 
	 * @param paging
	 * @param loginMember
	 * @param distance
	 * @return
	 */
	public List<Map<String, Object>> getCareListFromLogin(Paging paging, Member loginMember, String distance);
	
	/**
	 * 신고를 신고테이블에 올리는 메소드
	 * 
	 * @param reportBoard
	 */
	public void inputCareReport(ReportBoard reportBoard, String writeDetail);
	
	/**
	 * 공지사항 파일을 가져오는 메소드
	 * 
	 * @param noticeno
	 * @return
	 */
	public List<NoticeFile> getNoticeFileList(int noticeno);
	
	/**
	 * 댓글을 신고하는 메소드
	 * 
	 * @param reportComment
	 * @param writeDetail
	 */
	public void inputCareCommentReport(ReportComment reportComment, String writeDetail);

	/**
	 * 품앗이 게시글의 댓글을 삭제하는 메소드
	 * 
	 * @param comment
	 */
	public void deleteCareComment(Comment comment);

	
	//---------------------------제균------------------------------------

	/**
	 * 카테고리에 맞는 게시글 개수 가져와서 페이징 설정하기
	 * @return 설정된 페이징
	 */ 
	public Paging getPaging(Integer curPage, int category, String search);

	/**
	 * 설정된 페이징으로 게시글 가져오기
	 * @param paging 설정된 페이징 객체
	 * @return 반환된 게시글
	 */
	public List<Map<String ,Object>> getBoard(Paging paging, int category);

	/**
	 * 카테고리 타입에 맞는 공지사항 게시글 3개 가져오기
	 * @return 공지사항 게시글 
	 */
	public List<Map<String, Object>> getNotice(int category);

	/**
	 * 게시글 올리기
	 * @param board 게시글 올릴 board 객체
	 * @return DB에 올라간 boardno
	 */
	public int writeBoard(Board board);

	/**
	 * 파일 리스트 올리기
	 * 
	 * @param fileList 저장할 파일 리스트
	 * @param boardNo 게시글의 번호
	 * @param no 취소된 파일 -1, 은 저장하지 않음
	 */
	public void saveFiles(List<MultipartFile> fileList, int boardNo, List<Integer> no);

	/**
	 * 세션에 있는 loginid를 이용하여 유저번호를 가져오는 메소드
	 * 
	 * @param loginid
	 * @return
	 */
	public int getUserno(String loginid);

	/**
	 * 품앗이 게시글을 저장하는 메소드
	 * 
	 * @param board
	 * @param file
	 */
	public void upload(Board board, List<MultipartFile> file);

	/**
	 * 상세보기 페이지의 보드 정보 가져오기
	 * boardno, boardTitle, hit, recommend, writeDate, userName, content, boardTypeNo
	 * 
	 * @param boardNo 가져올 보드 번호
	 * @return boardno, boardTitle, hit, recommend, writeDate, userName, content, boardTypeNo 
	 */
	public Map<String, Object> getBoardOne(int boardNo);

	/**
	 * boardNo에 맞는 파일들 가져오기
	 * @param boardNo 가져올 파일의 boardNo
	 * @return boardNo 게시글의 파일들
	 */
	public List<BoardFile> getBoardFile(int boardNo);

	/**
	 * 게시글 삭제
	 * @param boardNo 삭제할 게시글 boardNo
	 */
	public void deleteBoard(int boardNo);

	/**
	 * 게시글 신고
	 * @param writeDetail  기타일때 사유
	 * @param 신고할 내용과  boardNo userNo 객체
	 */
	public void boardReport(ReportBoard reportBoard, String writeDetail);

	/**
	 * 게시글 추천 했는지 가져오기
	 * @param boardReco 게시글 번호와 유저 번호 객체
	 * @return 추천했으면 true 아니면 false
	 */
	public boolean isLike(BoardRecommend boardReco);



	/**
	 * 게시글 추천하기
	 * 추천했으면 취소 안했으면 하기
	 * 
	 * @param boardReco 추천할 정보 객체
	 * @return view에서 추천했는지 안했는지 확인 true면 추천했음 false면 안했음
	 */
	public boolean Reco(BoardRecommend boardReco);

	/**
	 * 추천개수 
	 * @param boardReco
	 * @return 추천 개수
	 */
	public int getCountReco(BoardRecommend boardReco);

	/**
	 * 댓글 가져오기
	 * 
	 * @param boardNo 가져올 댓글의 boardNo
	 * @return boardNo에 맞는 댓글
	 */
	public List<Map<String, Object>> getComments(int boardNo);

	/**
	 *  댓글 작성
	 *  commentNo과 content, boardNo, userNo이 담긴 comment 객체
	 * @param comment DB에 저장할  댓글 객체
	 * @return 삽입한 댓글 가져오기
	 */
	public Comment addComment(Comment comment);

	/**
	 * userNo으로 userName 가져오기
	 * @param userNo 가져올 userName의 userNo
	 * @return userName
	 */
	public String getUsername(int userNo);

	/**
	 * 댓글 삭제
	 * @param commentNo 삭제할 댓글 번호
	 */
	public void deleteComment(int commentNo);

	/**
	 * 댓글 신고
	 * @param 신고할 정보 객체
	 */
	public void reportComment(ReportComment rc, String writeDetail);

	/**
	 * 병원 등록 
	 * @param fileList 병원등록 사진
	 * @param no 사진파일들 취소 여부
	 * @param hospital 등록할 병원 정보 객체
	 */
	public void enrollHospital(List<MultipartFile> fileList, List<Integer> no, Hospital hospital);

	/**
	 * userNo으로 user 정보 조회
	 * @param userNo 조회할 userNo
	 * @return userNo의 user 정보
	 */
	public Member getUserInfo(int userNo);

	/**
	 * 검색 조건을 넣은 paging 객체 
	 * @param paging 조건을 넣은 페이징 객체
	 * @return 조건을 넣은 페이징 객체 반환
	 */
	public HospitalPaging getHospitalPaging(HospitalPaging paging);
	/**
	 * 병원 정보를 가져온다
	 */
	public List<Map<String, Object>> getHospitalInfo(HospitalPaging paging);

	/**
	 * 병원의 상세보기 정보 가져오기
	 * userNo이 -1이면 비로그인 상태 거리 안뜸
	 * @param hospitalNo 상세보기할 병원의 hospitalNo
	 * @param userNo 
	 * @return 병원의 상세 정보
	 */
	public Map<String, Object> getHospitalDetail(int hospitalNo, int userNo);

	/**
	 * boardNo으로 병원 정보 가져오기
	 * @param boardNo
	 * @param userNo 
	 * @return 병원 정보
	 */
	public Map<String, Object> getHospitalInfo(int boardNo, Integer userNo);

	/**
	 * 병원 정보 수정
	 * @param hospital 수정할 병원 정보 객체
	 * @param file 수정할 파일
	 */
	public void modifyHospitalInfo(board.dto.Hospital hospital, MultipartFile file);


}
