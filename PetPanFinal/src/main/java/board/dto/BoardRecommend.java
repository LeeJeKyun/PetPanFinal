package board.dto;

public class BoardRecommend {

	private int boardRecomNo;
	private int boardNo;
	private int userNo;
	
	public BoardRecommend() {}

	@Override
	public String toString() {
		return "BoardRecommend [boardRecomNo=" + boardRecomNo + ", boardNo=" + boardNo + ", userNo=" + userNo + "]";
	}

	public BoardRecommend(int boardRecomNo, int boardNo, int userNo) {
		super();
		this.boardRecomNo = boardRecomNo;
		this.boardNo = boardNo;
		this.userNo = userNo;
	}

	public int getBoardRecomNo() {
		return boardRecomNo;
	}

	public void setBoardRecomNo(int boardRecomNo) {
		this.boardRecomNo = boardRecomNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	
}
