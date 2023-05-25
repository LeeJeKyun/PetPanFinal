package board.dto;

import java.util.Date;

public class Board {

private int boardNo;
private String boardTitle;
private String content;
private Date writeDate;
private int hit;
private int userNo;
private int boardTypeNo;

public Board() {}

@Override
public String toString() {
return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", content=" + content + ", writeDate="
+ writeDate + ", hit=" + hit + ", userNo=" + userNo + ", boardTypeNo=" + boardTypeNo + "]";
}

public Board(int boardNo, String boardTitle, String content, Date writeDate, int hit, int userNo, int boardTypeNo) {
super();
this.boardNo = boardNo;
this.boardTitle = boardTitle;
this.content = content;
this.writeDate = writeDate;
this.hit = hit;
this.userNo = userNo;
this.boardTypeNo = boardTypeNo;
}

public int getBoardNo() {
return boardNo;
}

public void setBoardNo(int boardNo) {
this.boardNo = boardNo;
}

public String getBoardTitle() {
return boardTitle;
}

public void setBoardTitle(String boardTitle) {
this.boardTitle = boardTitle;
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

public int getHit() {
return hit;
}

public void setHit(int hit) {
this.hit = hit;
}

public int getUserNo() {
return userNo;
}

public void setUserNo(int userNo) {
this.userNo = userNo;
}

public int getBoardTypeNo() {
return boardTypeNo;
}

public void setBoardTypeNo(int boardTypeNo) {
this.boardTypeNo = boardTypeNo;
}


}


