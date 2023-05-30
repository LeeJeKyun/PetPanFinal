package board.dto;

import java.util.Date;

public class Comment {

	private int commentNo;
	private String content;
	private Date writeDate;
	private int boardNo;
	private int userNo;
	private int depth;
	private  int refcommentNo;
	private int dComment;
	
	public Comment() {}

	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", content=" + content + ", writeDate=" + writeDate + ", boardNo="
				+ boardNo + ", userNo=" + userNo + ", depth=" + depth + ", refcommentNo=" + refcommentNo + ", dComment="
				+ dComment + "]";
	}

	public Comment(int commentNo, String content, Date writeDate, int boardNo, int userNo, int depth, int refcommentNo,
			int dComment) {
		super();
		this.commentNo = commentNo;
		this.content = content;
		this.writeDate = writeDate;
		this.boardNo = boardNo;
		this.userNo = userNo;
		this.depth = depth;
		this.refcommentNo = refcommentNo;
		this.dComment = dComment;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
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

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getRefcommentNo() {
		return refcommentNo;
	}

	public void setRefcommentNo(int refcommentNo) {
		this.refcommentNo = refcommentNo;
	}

	public int getdComment() {
		return dComment;
	}

	public void setdComment(int dComment) {
		this.dComment = dComment;
	}
	
	
}
