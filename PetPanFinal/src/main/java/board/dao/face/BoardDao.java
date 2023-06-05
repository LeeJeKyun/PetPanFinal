package board.dao.face;

import java.util.List;
import java.util.Map;

import board.dto.Board;
import board.dto.BoardFile;
import board.dto.BoardRecommend;
import board.dto.Comment;
import board.dto.Notice;
import board.dto.ReportBoard;
import board.dto.ReportComment;
import member.dto.Member;
import util.HospitalPaging;
import util.Paging;

public interface BoardDao {
	
	/**
	 * 게시글 검색어로 검색한 DB의 행 갯수를 반환하는 메소드
	 * 
	 * @param search
	 * @return
	 */
	public int selectNoticeCntAll(String search);

	/**
	 * paging객체를 기반으로 notice행을 반환하는 메소드
	 * 
	 * @param paging
	 * @return
	 */
	public List<Map<String, Object>> selectNoticeAll(Paging paging);

	/**
	 * noticeno을 기준으로 notice를 상세조회하는 메소드
	 * 
	 * @param noticeno
	 * @return
	 */
	public Notice selectNoticeByNoticeno(int noticeno);

	/**
	 * 검색어로 검색한 DB의 행 갯수를 반환하는 메소드
	 * 
	 * @param search
	 * @return
	 */
	public int selectCareCntAll(String search);

	/**
	 * paging객체를 기반으로 care행(boardtypeno=1)을 반환하는 메소드
	 * 
	 * @param paging
	 * @return
	 */
	public List<Map<String, Object>> selectCareAll(Paging paging);

	/**
	 * loginid를 기준으로 userno을 가져오는 메소드
	 * 
	 * @return
	 */
	public int selectUsernoByUserid(String loginid);
	

	/**
	 * 게시글을 DB에 추가하는 메소드
	 * 
	 * @param board
	 */
	public void insertCareBoard(Board board);

	/**
	 * 다음 게시글의 시퀀스를 가져오는 메소드
	 * 
	 * @return
	 */
	public int selectBoardSeqNext();
	
	/**
	 * boardNo을 기준으로 해당하는 boardFile의 리스트를 가져오는 메소드
	 * 
	 * @param boardNo
	 * @return
	 */
	public List<BoardFile> selectCareBoardFile(int boardNo);
	
	/**
	 * 게시글 추천 내역이 있는지 가져오기 있을경우 1, 없을경우 0
	 * 
	 * @return
	 */
	public int selectRecommendCntByBoardNoUserNo(Map<String, Integer> map);
	
	/**
	 * 게시글의 추천내역 올리기
	 * 
	 * @param map
	 */
	public void insertRecommendToCare(Map<String, Integer> map);
	
	/**
	 * 게시글의 추천내역 삭제하기
	 * 
	 * @param map
	 */
	public void deleteRecommendToCare(Map<String, Integer> map);
	
	/**
	 * 해당 게시글의 추천갯수 가져오기
	 * 
	 * @param boardNo
	 * @return
	 */
	public int selectRecommendCnt(int boardNo);
	
	/**
	 * 품앗이 게시글의 댓글을 insert하는 메소드
	 * 
	 * @param commentTable
	 */
	public void insertCommentToCareBoard(Comment comment);
	
	/**
	 * 해당 게시글(boardno)의 댓글을 모두 가져오는 메소드 
	 * 
	 * @param boardno
	 * @return
	 */
	public List<Map<String, Object>> selectCommentByBoardno(int boardno);
	
	/**
	 * 품앗이 게시판에 띄울 최신공지사항을 가져오는 메소드
	 * 
	 * @return
	 */
	public List<Map<String, Object>> selectNoticeToCare();
	
	/**
	 * 참조댓글의 sortno을 가져오는 메소드
	 * 
	 * @param refCommentNo
	 * @return
	 */
	public int selectSortNoByRef(int refCommentNo);
	
	/**
	 * refcommentno을 이용해서 해당 댓글보다 뒷순번인 댓글의 sortno을 1씩 더하는 메소드
	 * 
	 * @param refCommentNo
	 */
	public void updateAfterSortnoUpper(Map<String, Object> map);
	
	/**
	 * refcommentno을 이용하여 organ값을 가져오는 메소드
	 * 
	 * @param refcommentno
	 * @return
	 */
	public int selectRefOrgan(int refcommentno);

	/**
	 * boardtypeno을 5로 바꿈으로써 게시글을 지우는 시늉을 하는 메소드
	 * 
	 * @param board
	 */
	public int deleteByUpdateBoardType(Board board);
	
	/**
	 * map에 담긴 userno을 통해서 member의 정보를 가져오는 메소드
	 * 
	 * @param map
	 * @return
	 */
	public Member getMemberByBoardMap(Map<String, Object> map);
	
	
	//--------------------------제균--------------------------------


	/**
	 * 자유게시판 게시글 개수 가져오기 
	 * @param 검색어
	 * @return 자유게시판의 게시글 수
	 */
	public int selectFreeBoardCnt(String search);
	/**
	 * 중고 거래 게시글 개수 가져오기 
	 * 
	 * @param 검색어
	 * @return 중고거래 게시글 수
	 */
	public int selectMarketBoardCnt(String search);

	/**
	 * 중고거래 페이징으로 범위에 맞는 게시글 가져오기
	 * 
	 * @param 중고거래로 검색할 paging객체
	 * @return 중고거래 카테고리 게시글
	 */
	public List<Map<String, Object>> selectMarketList(Paging paging);
	/**
	 * 자유 게시판 페이징 범위에 맞는 게시글 가져오기
	 * 
	 * @param 자유 게시판 검색할 paging객체
	 * @return 자유 게시판 카테고리 게시글
	 */
	public List<Map<String, Object>> selectFreeList(Paging paging);
	
	/**
	 * 공지사항 3개 select 
	 * 설정된 페이징
	 * @param 현재 공지사항의 가장 최신 번호
	 * @return select된 공지사항
	 */
	public List<Map<String, Object>> selectNotice(Map map);
	/**
	 * 게시글 타입에 맞는 공지사항의 가장 최신 번호 가져오기
	 * @param 게시판 타입
	 * @return 공지사항의 가장 최신번호
	 */
	public int selectNoticeCnt(int category);

	/**
	 * 다음에 저장될 boardNo 가져오기
	 * 
	 * @return 다음 boardNo  seq_board.nextVal
	 */
	public int selectBoardNext();

	/**
	 * board 객체 저장하기
	 * @param board DB에 저장할 board 객체
	 */
	public void insertBoard(Board board);

	/**
	 *  boardFile 테이블에 file 저장
	 * @param map 저장할 file map
	 */
	public int insertBoardFile(Map<String, Object> map);

	/**
	 * 상세보기 게시글의 정보 가져오기
	 * @param boardNo 상세보기할 boardno
	 * @return 상세보기할 게시글의 정보
	 */
	public Map<String, Object> selectBoardOne(int boardNo);

	/**
	 * boardNo에 맞는 파일들 select
	 * 
	 * @param boardNo 가져올 파일들의 boardNo
	 * @return   boardNo에 맞는 파일들 
	 */
	public List<BoardFile> selectFiles(int boardNo);

	/**
	 * boardNo 게시글 삭제
	 * boardtypeno을 5로 변경
	 * @param boardNo 삭제할 게시글의 boardNo
	 */
	public void updateBoard(int boardNo);

	/**
	 * 신고테이블에 boardNo이 있는지 검사
	 * 
	 * @param boardNo select할 boardNo
	 * @return 있으면 true 없으면 false
	 */
	public int selectReportBoard(int boardNo);

	/**
	 * 게시글 파일 삭제
	 * @param boardNo 삭제할 게시글 파일  
	 */
	public void deleteBoardFile(int boardNo);

	/**
	 * 게시글 삭제 
	 * @param boardNo 삭제할 게시글 
	 */
	public void deleteBoard(int boardNo);

	/**
	 * 신고 테이블에 동일 boardNo, 동일 userNo이 있는지 확인
	 * @param reportBoard boardNo과 userNo이 있는 객체
	 * @return 없으면 0
	 */
	public int selectIsReport(ReportBoard reportBoard);

	/**
	 * 신고처리
	 * @param reportBoard 신고할 객체
	 */
	public void insertReport(ReportBoard reportBoard);

	/**
	 * boardrecommend , 유저가 게시글을 추천했나?
	 * @param boardReco 조회할 userNo 과 boardNo
	 * @return 추천했으면 1 안했으면 0
	 */
	public int selectIsReco(BoardRecommend boardReco);

	/**
	 * 추천하기 insert 하기
	 * @param boardReco 추천할 게시글과 유저정보
	 */
	public void insertBoardReco(BoardRecommend boardReco);

	/**
	 * 추천했는지 체크
	 * @param boardReco
	 * @return 추천했으면 1안했으면 0
	 */
	public int checkReco(BoardRecommend boardReco);

	/**
	 * 추천취소 delete
	 * 
	 * @param boardReco 추천 취소할 게시글 번호와 유저 번호
	 */
	public void deleteReco(BoardRecommend boardReco);

	/**
	 * 추천 개수 가져오기
	 * @param boardReco 추천 개수 가져올 게시글 번호
	 * @return 추천 개수
	 */
	public int selectCntReco(BoardRecommend boardReco);

	/**
	 * select 댓글 
	 * @param map 가져올 댓글의 boardNo
	 * @return select한 댓글
	 */
	public List<Map<String, Object>> selectComments(Map<String, Integer> map);

	/**
	 * 댓글을 달 commetNo의  sortNo 과 depth가져오기
	 * @return 댓글을 달 댓글의 sortNo, depth
	 */
	public Map<String, Integer> selectDepthAndSortNo(int commentNo);

	/**
	 *  댓글 삽입 
	 * @param comment 삽입할 댓글 객체
	 */
	public void insertComment(Comment comment);

	/**
	 * 다음 commentNo 가져오기
	 * @return 다음 commentNo
	 */
	public int selectMaxCommentNo();

	/**
	 * commentNo으로 삽입한 댓글 가져오기
	 * @param commentNo 조회할 commentNo
	 * @return 조회한 댓글 
	 */
	public Comment selectCommentByCommentNo(int commentNo);

	/**
	 * userNo으로 userName 조회
	 * @param userNo 조회할 userName의 userNo
	 * @return userName
	 */
	public String selectUserNameByUserNo(int userNo);



	/**
	 * 가장 마지막 sortNo 가져오기
	 * @return 가장 마지막 sortNo
	 */
	public int selectMaxSortNo(int organization);

	/**
	 * 	organization 중에서 map.get(sortNo)보다 큰 것들 다 +1
	 * @param map - organization, sortNo
	 */
	public void updatePlueSortNo(Map<String, Integer> map);

	/**
	 * commenttable에서 가장 최근 게시글 가져오기
	 */
	public int selectMaxCommentCnt(int boardNo);

	/**
	 * 작성자가 삭제할 댓글의 내용을 작성자가 삭제한 댓글이라고 바꾼다.
	 * @param commentNo - update할 댓글의 commentNo
	 */
	public void updateComment(int commentNo);

	/**
	 * 댓글 신고 신고 테이블에 insert
	 * @param 댓글 신고할 정보 객체
	 */
	public void insertCommentNo(ReportComment rc);

	/**
	 * 마이페이지에서 입력했던 병원 번호 가져오기
	 * @param userNo 가져올 회원 번호
	 * @return 입력했던 병원번호, hospitalNo
	 */
	public int selectHospitalNo(int userNo);

	/**
	 * 병원 사진 저장
	 * @param mapFile 병원 사진 파일 객체
	 */
	public void insertHospitalFile(Map<String, Object> mapFile);

	/**
	 * userNo 으로 member 테이블 조회 
	 * @param userNo 조회할 userNo
	 * @return user 정보
	 */
	public Member selectUserInfo(int userNo);

	/**
	 * 병원 정보에 이미 등록한 사진 파일이 있는지 확인
	 * @param hospitalNo 파일을 select 할 hospitalNo
	 * @return 없으면 0
	 */
	public int selectIsHospitalFile(int hospitalNo);

	/**
	 * 이미 등록한 사진 파일을 delete
	 * @param hospitalNo delete 할 hospitalNo
	 */
	public void deleteHospitalFile(int hospitalNo);

	/**
	 * 병원 정보를 조회
	 * @return 모든 병원 정보
	 */
	public List<Map<String, Object>> selectHospitalInfo();

	/**
	 * 병원 파일 조회
	 * @return 병원의 파일 정보
	 */
	public List<Map<String, Object>> selectHospitalFileInfo(List<Map<String, Object>> hospitalList);

	/**
	 * 반경이 0 일때 전체 병원 수 조회
	 * @param paging 병원 수를 조회하기 위한 조건
	 * @return 병원 수 
	 */
	public int selectHospitalAllCnt(HospitalPaging paging);

	/**
	 * 반경을 포함한 병원을 조회
	 * @param paging usreNo, search, radius 가 있는 paging 객체
	 * @return 반경을 포함한 조회, 병원 수
	 */
	public int selectHospitalCnt(HospitalPaging paging);

	/**
	 * 조건에 맞는 병원 조회 (반경 미포함)
	 * @param paging 조회할 조건
	 * @return 조회한 병원 
	 */
	public List<Map<String, Object>> selectHospitalAll(HospitalPaging paging);

	/**
	 * 조건에 맞는 병원 조회 (반경 포함)
	 * @param paging 조회할 조건
	 * @return 조회한 병원
	 */
	public List<Map<String, Object>> selectHospital(HospitalPaging paging);

	/**
	 * 병원의 정보 조회
	 * @param hospitalNo 상세보기할 병원의  HospitalNo
	 * @return 병원의 정보
	 */
	public Map<String, Object> selectHospitalDetail(int hospitalNo);

	/**
	 * 거리를 포함한 병원 정보를 조회
	 * @param map hospitalNo, userNo
	 * @return 로그인한 userNo의 주소와 병원까지의 주소 거리를 포함한 반환
	 */
	public Map<String, Object> selectHospitalDetailUserNo(Map<String, Integer> map);

	/**
	 * 병원의 위도, 경도 조회
	 * @param hospitalNo hospitalNo
	 * @return 병원 위치
	 */
	public Map<String, String> selectHospitalLoc(int hospitalNo);

	/**
	 * 유저의 위치 가져오기
	 * @param userNo 조회할 위치의 userNo
	 * @return 유저의 위치 위도, 경도
	 */
	public Map<String, String> selectUserLoc(int userNo);




	
}
