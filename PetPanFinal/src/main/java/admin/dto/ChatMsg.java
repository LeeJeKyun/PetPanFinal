package admin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ChatMsg {
	private int msgno;      
	private int chatroomno;
	private int senderno;         
	private String message;   
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Seoul")
	private Date regdate;
	@Override
	public String toString() {
		return "ChatMsg [msgno=" + msgno + ", chatroomno=" + chatroomno + ", senderno=" + senderno + ", message="
				+ message + ", regdate=" + regdate + "]";
	}
	private ChatMsg(int msgno, int chatroomno, int senderno, String message, Date regdate) {
		super();
		this.msgno = msgno;
		this.chatroomno = chatroomno;
		this.senderno = senderno;
		this.message = message;
		this.regdate = regdate;
	}
	private ChatMsg() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMsgno() {
		return msgno;
	}
	public void setMsgno(int msgno) {
		this.msgno = msgno;
	}
	public int getChatroomno() {
		return chatroomno;
	}
	public void setChatroomno(int chatroomno) {
		this.chatroomno = chatroomno;
	}
	public int getSenderno() {
		return senderno;
	}
	public void setSenderno(int senderno) {
		this.senderno = senderno;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	

}
