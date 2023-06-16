package main.service.face;

import java.util.List;
import java.util.Map;

import board.dto.Board;
import board.dto.BoardFile;
import member.dto.PetFile;
import message.dto.Message;
import util.HospitalPaging;


public interface MainService {

	/**
	 * 인기순으로 자유게시판 조회
	 * 
	 * @param board
	 * @return
	 */
	List<Board> selectFree(Board board);

	/**
	 * 인기순으로 중고게시판 조회
	 * 
	 * @param board
	 * @return
	 */
	List<Board> selectOld(Board board);
	
//	/** 
//	 * 새로나온 자유게시판 조회
//	 * 
//	 * @param board
//	 * @return
//	 */
//	List<Board> selectNewFree(Board board);
//
//	/**
//	 * 새로나온 중고게시판 조회
//	 * 
//	 * @param board
//	 * @return
//	 */
//	List<Board> selectNewOld(Board board);

	/**
	 * 새로나온 품앗이 조회
	 * 
	 * @param board
	 * @return
	 */
	List<Board> selectPoom(Board board );

	/**
	 * 검색어에 맞는 게시글 
	 * 
	 * @param paging 조건 넣은 객체
	 * @return 조건 넣은 객체 반환
	 */
	HospitalPaging getPaging(HospitalPaging paging);

	/**
	 * 병원 정보 가져오기
	 * 
	 * @param paging
	 * @return
	 */
	List<Map<String, Object>> getHospitalInfo(HospitalPaging paging);

	/**
	 * 메시지 읽음 확인
	 * 
	 * @param message
	 * @return 
	 */
	public int selectDoread(Message message);

	/**
	 * 품앗이 사진 조회
	 * 
	 * @param boardFile
	 * @return
	 */
	List<BoardFile> getPoomPhoto(List<Board> poom);




	
	
	
}
