package message.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import message.dao.face.MessageDao;
import message.dto.Message;
import message.service.face.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	@Autowired MessageDao messageDao;
	
	@Override
	public int getReceiveUserNo(String receiveuserid) {
		return messageDao.selectUsernoByUserid(receiveuserid);
	}

	@Override
	public String getSendUserId(int sendUserNo) {
		return messageDao.selectUseridByUserno(sendUserNo);
	}

	@Override
	public boolean sendMessage(Message message) {
		
		int res = messageDao.insertMessage(message);
		
		logger.info("{}", res);
		
		if(res > 0) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<Map<String, Object>> getMessageList(Message message) {
		
		return messageDao.selectMessageListbyUserno(message);
		
	}

	@Override
	public Message getMessageView(Message message) {
		
		Message viewMessage = messageDao.selectMessageByMessageNo(message); 
		
		messageDao.updateMessageDoRead(message);
		
		return viewMessage;
	}
	
	
	
}
