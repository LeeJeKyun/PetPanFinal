package board.dto;

import java.util.Date;

public class CommentTable {
	
	private int commentno;
	private String content;
	private Date writeDate;
	private int boardno;
	private int userno;
	private int depth;
	private int refCommentNo;
	
	public CommentTable() {}

	public CommentTable(int commentNo, String content, Date writeDate, int boardno, int userno, int depth,
			int refCommentNo) {
		super();
		this.commentno = commentNo;
		this.content = content;
		this.writeDate = writeDate;
		this.boardno = boardno;
		this.userno = userno;
		this.depth = depth;
		this.refCommentNo = refCommentNo;
	}

	@Override
	public String toString() {
		return "CommentTable [commentNo=" + commentno + ", content=" + content + ", writeDate=" + writeDate
				+ ", boardno=" + boardno + ", userno=" + userno + ", depth=" + depth + ", refCommentNo=" + refCommentNo
				+ "]";
	}

	public int getCommentNo() {
		return commentno;
	}

	public void setCommentNo(int commentNo) {
		this.commentno = commentNo;
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

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getRefCommentNo() {
		return refCommentNo;
	}

	public void setRefCommentNo(int refCommentNo) {
		this.refCommentNo = refCommentNo;
	}
	
	

}
