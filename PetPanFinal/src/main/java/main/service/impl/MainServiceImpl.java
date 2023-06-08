package main.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.dto.Board;
import main.dao.face.MainDao;
import main.service.face.MainService;
import member.service.face.MemberService;

@Service
public class MainServiceImpl implements MainService {

	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Autowired MainDao mainDao;

	@Override
	public List<Board> selectFree(Board board) {

		
		
		return mainDao.selectFree(board);
	}
	
	
	
	
}
