package message.service.face;

import message.dto.Message;

public interface MessageService {

	/**
	 * 쪽지를 받는 유저의 번호를 가져오는 메소드
	 * 
	 * @param receiveuserid
	 * @return
	 */
	public int getReceiveUserNo(String receiveuserid);

	/**
	 * 보내는 유저의 닉네임을 가져오는 메소드
	 * 
	 * @param sendUserNo
	 * @return
	 */
	public String getSendUserId(int sendUserNo);

	/**
	 * 쪽지를 보내는 메소드(DB저장)
	 * 
	 * @param message
	 * @return - 쪽지를 보내는데 성공시 true, 실패시 false
	 */
	public boolean sendMessage(Message message);

}
