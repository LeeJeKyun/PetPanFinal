package main.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.dto.Board;
import board.dto.BoardFile;
import main.dao.face.MainDao;
import main.service.face.MainService;
import member.dto.PetFile;
import member.service.face.MemberService;
import message.dto.Message;
import util.HospitalPaging;

@Service
public class MainServiceImpl implements MainService {

	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Autowired MainDao mainDao;

	@Override
	public List<Board> selectFree(Board board) {

		
		
		return mainDao.selectFree(board);
	}
	
	@Override
	public List<Board> selectOld(Board board) {
		return mainDao.selectOld(board);
	}
	
	
//	@Override
//	public List<Board> selectNewFree(Board board) {
//		return mainDao.selectNewFree(board);
//	}
//	
//	@Override
//	public List<Board> selectNewOld(Board board) {
//		return mainDao.selectNewOld(board);
//	}
	

	
	
	@Override
	public HospitalPaging getPaging(HospitalPaging paging) {

		HospitalPaging hP = null;
		if(paging.getUserNo() == -1) {
			//로그인을 하지 않은 유저
			paging.setRadius(0);
		}
		if(null == paging.getSearch()) paging.setSearch("");
		
			//전체 검색
			hP = new HospitalPaging(mainDao.selectHospitalAllCnt(paging), paging.getCurPage(), 9, 5);
			
		hP.setSearch(paging.getSearch());
		hP.setUserNo(paging.getUserNo());
		hP.setRadius(paging.getRadius());
		
		//종 선택
		hP.setRodent(paging.getRodent());
		hP.setBirds(paging.getBirds());
		hP.setMammalia(paging.getMammalia());
		hP.setReptile(paging.getReptile());
		
		return hP;
	}
	
	
	@Override
	public List<Map<String, Object>> getHospitalInfo(HospitalPaging paging) {

		List<Map<String, Object>> list = null;
		
			list = mainDao.selectHospitalAll(paging); 

			return list;
	}
	
	
	
	@Override
	public int selectDoread(Message message) {

		
		return mainDao.selectDoread(message);
		
	}
	
	
	@Override
	public List<Board> selectPoom(Board board) {
		return mainDao.selectPoom(board);
	}
	
	
	@Override
	public List<BoardFile> getPoomPhoto(List<Board> poom) {
		
		List<BoardFile> returnList = new ArrayList<BoardFile>();
		
		for(int i=0; i<poom.size(); i++) {
			BoardFile boardFile = mainDao.selectPoomPhoto(poom.get(i).getBoardNo());
			
			
			returnList.add(boardFile);
		}
		
		return returnList;
	}
	
	
	
	
}
