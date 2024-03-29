package message.service.face;

import java.util.Date;
import java.util.List;
import java.util.Map;

import member.dto.Member;
import message.dto.Message;

public interface MessageService {

	/**
	 * 쪽지를 받는 유저의 번호를 가져오는 메소드
	 * 
	 * @param receiveuserid
	 * @return
	 */
	public Member getReceiveUser(String receiveuserid);

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

	/**
	 * 로그인한 유저의 쪽지를 가져오는 메소드
	 * 
	 * @param userno
	 * @return
	 */
	public List<Map<String, Object>> getMessageList(Message message);

	/**
	 * 상세보기 하려는 쪽지를 가져오는 메소드
	 * 
	 * @param message
	 * @return
	 */
	public Message getMessageView(Message message);

	/**
	 * 쪽지를 저장하는 메소드
	 * 
	 * @param message
	 */
	public boolean saveMessage(Message message);

	/**
	 * 쪽지를 삭제하는 메소드
	 * 
	 * @param message
	 * @return
	 */
	public boolean deleteMessage(Message message);

	/**
	 * 메시지를 여러번 삭제하는 메소드
	 * 
	 * @param messagenoList
	 */
	public void deleteMessageByList(List<Integer> messagenoList);

	/**
	 * 여러 메시지를 한번에 읽음처리하는 메소드
	 * 
	 * @param messagenoList
	 */
	public void readMessageByList(List<Integer> messagenoList);

	/**
	 * 여러 메시지를 한번에 저장처리하는 메소드
	 * 
	 * @param messagenoList
	 */
	public void saveMessageByList(List<Integer> messagenoList);

	/**
	 * 모든 유저에게 쪽지를 보내는 메소드.
	 * 
	 */
	public void sendMessageToAll(String content, int senduserno);

	/**
	 * 30일이 지난 쪽지를 삭제하는 메소드.
	 * 
	 */
	public void deleteMessageRoutin();

}
