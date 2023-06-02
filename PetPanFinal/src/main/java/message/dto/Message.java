package message.dto;

import java.util.Date;

public class Message {

	private int messageno;
	private String content;
	private Date sendDate;
	private String doread;
	private String saveMessage;
	private int receiveUserNo;
	private int sendUserNo;
	
	public Message() {}

	public Message(int messageno, String content, Date sendDate, String doread, String saveMessage, int receiveUserNo,
			int sendUserNo) {
		this.messageno = messageno;
		this.content = content;
		this.sendDate = sendDate;
		this.doread = doread;
		this.saveMessage = saveMessage;
		this.receiveUserNo = receiveUserNo;
		this.sendUserNo = sendUserNo;
	}

	@Override
	public String toString() {
		return "Message [messageno=" + messageno + ", content=" + content + ", sendDate=" + sendDate + ", doread="
				+ doread + ", saveMessage=" + saveMessage + ", receiveUserNo=" + receiveUserNo + ", sendUserNo="
				+ sendUserNo + "]";
	}

	public int getMessageno() {
		return messageno;
	}

	public void setMessageno(int messageno) {
		this.messageno = messageno;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getDoread() {
		return doread;
	}

	public void setDoread(String doread) {
		this.doread = doread;
	}

	public String getSaveMessage() {
		return saveMessage;
	}

	public void setSaveMessage(String saveMessage) {
		this.saveMessage = saveMessage;
	}

	public int getReceiveUserNo() {
		return receiveUserNo;
	}

	public void setReceiveUserNo(int receiveUserNo) {
		this.receiveUserNo = receiveUserNo;
	}

	public int getSendUserNo() {
		return sendUserNo;
	}

	public void setSendUserNo(int sendUserNo) {
		this.sendUserNo = sendUserNo;
	}
	
	
	
}
