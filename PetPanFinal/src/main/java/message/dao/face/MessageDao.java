package message.dao.face;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import member.dto.Member;
import message.dto.Message;

public interface MessageDao {

	/**
	 * 메시지를 전달할 userno을 userid를 통해 가져오는 메소드
	 * 
	 * @param receiveuserid
	 * @return
	 */
	public Member selectMemberByUserid(String receiveuserid);

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

	/**
	 * 로그인한 유저가 받은 쪽지리스트를 모두 가져오는 메소드
	 * 
	 * @param userno
	 * @return
	 */
	public List<Map<String, Object>> selectMessageListbyUserno(Message message);

	/**
	 * PK로 메시지를 가져오는 메소드
	 * 
	 * @param message
	 * @return
	 */
	public Message selectMessageByMessageNo(Message message);

	/**
	 * 메시지의 doRead컬럼을 Y로 변경하는 메소드
	 * 
	 * @param message
	 */
	public void updateMessageDoRead(Message message);

	/**
	 * 메시지의 저장상태에 따라 상태를 Y, N으로 변경하는 메소드
	 * 
	 * @param message
	 */
	public void updateMessageSave(Message message);

	/**
	 * 메시지를 delete하는 메소드
	 * 
	 * @param message
	 * @return
	 */
	public int deleteMessageByMessageno(Message message);
	
	/**
	 * 모두에게 메시지를 insert하는 메소드.
	 * 
	 */
	public void insertMessageToAll(@Param(value="list")List<Message> list, @Param(value = "content") String content, @Param(value = "senduserno") int senduserno);

	/**
	 * 30일에 한번 메시지를 삭제하는 메소드.
	 * 
	 * @param nowDate
	 */
	public void deleteMessageThirtyDate();

	/**
	 * 공지를 받아야 하는 멤버의 userno을 받아오는 메소드 
	 * 
	 * @return
	 */
	public List<Message> selectNormalUserList();

	/**
	 * 다음 쪽지의 시퀀스번호를 가져오는 메소 
	 * 
	 * @return
	 */
	public List<Integer> selectNextSeq(@Param(value="seqList")List<Message> seqList);
	
	
}
