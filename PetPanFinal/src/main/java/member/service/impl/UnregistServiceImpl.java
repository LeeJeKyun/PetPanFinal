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
		
		unregistDao.deleteReportBoardByUserNo(userno);
		unregistDao.deleteReportBoardByBoardNo(userno);
		unregistDao.deleteReportCommentByUserNo(userno);
		unregistDao.deleteReportCommentByCommentNo1(userno);
		unregistDao.deleteReportCommentByCommentNo2(userno);
		unregistDao.deleteReportObjectByUserNo(userno);
		unregistDao.deleteReviewFileByUserNo(userno);
		unregistDao.deleteReviewByUserNo(userno);
		unregistDao.deletePetFileByUserNo(userno);
		unregistDao.deletePetByUserNo(userno);
		unregistDao.deleteHospitalFileByUserNo(userno);
		unregistDao.deleteHospitalByUserNo(userno);
		unregistDao.deleteBlackListByUserNo(userno);
		unregistDao.deleteBasketByUserNo(userno);
		unregistDao.deleteBoardFileByUserNo(userno);
		unregistDao.updateCommentTableByUserNo(userno);
		unregistDao.deleteCommentRecommendByUserNo(userno);
		unregistDao.deleteCommentRecommendBoardByUserNo(userno);
		unregistDao.deleteBoardRecommendByUserNo(userno);
		unregistDao.updateCommentRecommendByUserNo(userno);
		unregistDao.updateBoardRecommendByUserNo(userno);
		unregistDao.deleteMessageByUserNo(userno);
		unregistDao.updateMessageByUserNo(userno);
		unregistDao.updateOrderByUserNo(userno);
		unregistDao.deleteCommentTableByuserNo(userno);
		unregistDao.deleteBoardByUserNo(userno);
		unregistDao.deleteUser(userno);
			
		
	}

}
