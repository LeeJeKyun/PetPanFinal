package member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.dao.face.UnregistDao;
import member.dto.Member;
import member.service.face.UnregistService;

@Service
public class UnregistServiceImpl implements UnregistService{
	
	@Autowired UnregistDao unregistDao;
	
	@Override
	public int checkmember(Member member, int userno, String pw) {
		
		member.setUserNo(userno);
		member.setUserPw(pw);
		
		int cheak = unregistDao.selectRegMember(member);

		return cheak;
	}

	@Override
	public void unregistAll(int userno) {
		// 저장된 펫정보의 제거
		
		
	}

}
