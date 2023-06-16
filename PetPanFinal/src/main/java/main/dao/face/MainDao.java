package main.dao.face;

import java.util.List;
import java.util.Map;

import board.dto.Board;
import board.dto.BoardFile;
import message.dto.Message;
import util.HospitalPaging;

public interface MainDao {

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
	 * 
	 * 
	 * @param paging
	 * @return
	 */
	int selectHospitalAllCnt(HospitalPaging paging);

	/**
	 * 조건에 맞는 병원을 조회
	 * 
	 * @param paging 조회할 조건
	 * @return 조회된 병원
	 */
	List<Map<String, Object>> selectHospitalAll(HospitalPaging paging);

	/**
	 * 읽음 확인
	 * 
	 * @param message
	 */
	public int selectDoread(Message message);

	
	/**
	 * 새로나온 품앗이 조회
	 * 
	 * @param board
	 * @return
	 */
	List<Board> selectPoom(Board board);

	/**
	 * 
	 * @param boardno
	 * @return
	 */
	 public BoardFile selectPoomPhoto(int boardno);
	
	



	



	
	
	
	
	
	
	
	
	

}
