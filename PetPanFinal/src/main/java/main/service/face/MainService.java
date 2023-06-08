package main.service.face;

import java.util.List;

import board.dto.Board;


public interface MainService {

	/**
	 * 인기순으로 자유게시판 조회
	 * 
	 * @param board
	 * @return
	 */
	List<Board> selectFree(Board board);

	
	
	
}
