package message.dao.face;

import message.dto.Message;

public interface MessageDao {

	/**
	 * 메시지를 전달할 userno을 userid를 통해 가져오는 메소드
	 * 
	 * @param receiveuserid
	 * @return
	 */
	public int selectUsernoByUserid(String receiveuserid);

	/**
	 * 메시지를 전달하는 userid를 userno을 통해 가져오는 메소드
	 * 
	 * @param sendUserNo
	 * @return
	 */
	public String selectUseridByUserno(int sendUserNo);

	/**
	 * 메시지를 DB에 저장하는 메소드
	 * 
	 * @param message
	 * @return 성공시 1, 실패시 0 반환
	 */
	public int insertMessage(Message message);
	
}
