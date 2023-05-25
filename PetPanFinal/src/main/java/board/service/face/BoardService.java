package board.service.face;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import board.dto.Board;
import board.dto.Notice;
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
//	public List<Map<String ,Object>> getBoard(Paging paging, int category, String search);

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

}
