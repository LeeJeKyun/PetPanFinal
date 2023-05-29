package board.dao.face;

import java.util.List;
import java.util.Map;

import board.dto.Board;
import board.dto.BoardFile;
import board.dto.BoardRecommend;
import board.dto.CommentTable;
import board.dto.Notice;
import board.dto.ReportBoard;
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
	public void insertCommentToCareBoard(CommentTable commentTable);

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
	 * @param boardNo 가져올 댓글의 boardNo
	 * @return select한 댓글
	 */
	public List<Map<String, Object>> selectComments(int boardNo);
}
