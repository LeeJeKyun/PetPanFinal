package admin.dto;

import java.util.Date;

public class Notice {
	int NoticeNo;
	String NoticeTitle;
	String NoticeContent;
	Date NoticeWriteDate;
	String UserNo;
	int BoardTypeNo;
	
	
	
	
	@Override
	public String toString() {
		return "Notice [NoticeNo=" + NoticeNo + ", NoticeTitle=" + NoticeTitle + ", NoticeContent=" + NoticeContent
				+ ", NoticeWriteDate=" + NoticeWriteDate + ", UserNo=" + UserNo + ", BoardTypeNo=" + BoardTypeNo + "]";
	}

	
	

	public Notice() {
	}




	public Notice(int noticeNo, String noticeTitle, String noticeContent, Date noticeWriteDate, String userNo,
			int boardTypeNo) {
		NoticeNo = noticeNo;
		NoticeTitle = noticeTitle;
		NoticeContent = noticeContent;
		NoticeWriteDate = noticeWriteDate;
		UserNo = userNo;
		BoardTypeNo = boardTypeNo;
	}


	public int getNoticeNo() {
		return NoticeNo;
	}


	public void setNoticeNo(int noticeNo) {
		NoticeNo = noticeNo;
	}


	public String getNoticeTitle() {
		return NoticeTitle;
	}


	public void setNoticeTitle(String noticeTitle) {
		NoticeTitle = noticeTitle;
	}


	public String getNoticeContent() {
		return NoticeContent;
	}


	public void setNoticeContent(String noticeContent) {
		NoticeContent = noticeContent;
	}


	public Date getNoticeWriteDate() {
		return NoticeWriteDate;
	}


	public void setNoticeWriteDate(Date noticeWriteDate) {
		NoticeWriteDate = noticeWriteDate;
	}


	public String getUserNo() {
		return UserNo;
	}


	public void setUserNo(String userNo) {
		UserNo = userNo;
	}


	public int getBoardTypeNo() {
		return BoardTypeNo;
	}


	public void setBoardTypeNo(int boardTypeNo) {
		BoardTypeNo = boardTypeNo;
	}
	
	
	

}
