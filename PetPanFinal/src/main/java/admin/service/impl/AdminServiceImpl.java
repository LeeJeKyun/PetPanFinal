package admin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import admin.dao.face.AdminDao;
import admin.dto.Blacklist;
import admin.dto.ReportBoard;
import admin.service.face.AdminService;
import board.dto.Board;
import member.dto.Member;
import shop.dto.Shop;
import shop.dto.ShopFile;
import util.Paging;

@Service
public class AdminServiceImpl implements AdminService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired AdminDao adminDao;
	@Autowired ServletContext context;

	@Override
	public List<ReportBoard> getReportBoard(Paging paging) {
		
		System.out.println(paging);
		
		List<ReportBoard> list = adminDao.ReportBoardtselectAll(paging);
		
		for(ReportBoard e : list) {
			System.out.println(e);
		}
		
		return list;
	}

	@Override
	public Paging getPage(int curPage) {
		int totalpage = adminDao.selectTotal();
		Paging paging = new Paging(totalpage, curPage);

		return paging;
	}

	@Override
	public ReportBoard getReportView(String boreportNo) {
		int boreportno = Integer.valueOf(boreportNo);
		ReportBoard reportboard = adminDao.selectReportInfo(boreportno);
		return reportboard;
	}

	@Override
	public Member getDoMember(int userNo) {
		Member member = adminDao.selectMember(userNo);
		return member;
	}

	@Override
	public Member getGetMember(int boardNo) {
		//boardno로 신고 당한 사람의 정보를 꺼내는 메소드
		int userno = adminDao.selectBoarduser(boardNo);
		Member member = adminDao.selectMember(userno);
		return member;
	}

	@Override
	public Board getBoard(int boardNo) {
		//boardno로 신고 당한 게시글의 정보를 꺼내는 메소드
		Board board = adminDao.selectBoardDetail(boardNo);
		return board;
	}

	@Override
	public void deleteChecked(List<String> delete) {
	    List<String> deleteNoList = delete;
	    int boardno = 0;

	    for (int i = 0; i < deleteNoList.size(); i++) {
	        int deleteNo = Integer.valueOf(deleteNoList.get(i));
	        //update할 BoardNo를 받아오는 메소드
	        boardno = adminDao.selectBoardNo(deleteNo);
	        //boardno의 타입을 5로 바꾸는 메소드
	        adminDao.updateBoardtype(boardno);
	        //Report의 complete를 N->Y로 바꾸는 메소드
	        adminDao.updateReportComplete(deleteNo);
	        
	    }

		
	}
	
	
	@Override
	public List<Blacklist> getBlacklistBoard(Paging paging) {
		System.out.println(paging);
		
		List<Blacklist> list = adminDao.BlacklistselectAll(paging);
		
		for(Blacklist b : list) {
			System.out.println(b);
		}
		
		return list;
	}
		
	@Override
	public void deleteblacklist(List<String> delete) {
	
		
		int rownum = 0;
		
		
		for (int i = 0; i < delete.size(); i++) {
			int deleteNo = Integer.valueOf(delete.get(i))  ;
		 rownum += adminDao.deleteBlacklist(deleteNo);
		
		}
		logger.info("삭제된 행의 개수" + rownum);
	}
	
	@Override
	public void insertblacklist(Blacklist blacklist) {
		adminDao.insertBlacklist(blacklist);
		
	}
	
	@Override
	public void shopContentInsert(Shop shop, MultipartFile shopFile, ShopFile shopfile) {
		

		int objectno = adminDao.selectObjectNo();
		shop.setObjectno(objectno);
		shopfile.setObjectno(objectno);
		adminDao.insertObjectContent(shop);
		//일반 글 삽입 끝
		
		if( shopFile.getSize() <= 0 ) {
			return;
		}
		
		String storedPath = context.getRealPath("upload");
		logger.info("storedPath + {}", storedPath );
		
		// uploa폴더가 존재하지 않으면 생성한다.
		File storedFolder = new File(storedPath);
		storedFolder.mkdir();
		// 오리지널 네임 객체 만들기 안 헷갈리게
		
		String originName = shopFile.getOriginalFilename();
		
		//저장할 파일 이름 생성하기
		File dest = null;
		String storedName = null;
		do{
			storedName = originName;
		//오리지널이름에 랜덤번호를 더해서 저장번호를 만든다.
		storedName += UUID.randomUUID().toString().split("-")[0];
		
		logger.info("storedName + {}", storedName );
		
		//실제 저장될 파일 객체
		dest = new File(storedFolder,storedName);
		//같은 이름이 있으면 다시 시도하도록 하는 do while문 이다.
		} while( dest.exists() );
		try {
			//업로드된 파일을 upload폴더에 저장하기
			shopFile.transferTo(dest);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		shopfile.setOriginname(originName);
		shopfile.setStoredname(storedName);
		shopfile.setFilesize(shopFile.getSize());
		
		adminDao.insertFile(shopfile);

	}

	@Override
	public void changeReportBoard(Integer boreportNo, Integer doboardNo, Integer getdoblack, Integer getgetblack,
			String getdoblackres, String getgetblackres) {
		
		Blacklist blacklist = new Blacklist();
		// 처리완료를 누르면 boreport의 complete를 N->Y로 바꾸는 메소드
		adminDao.updateReportComplete(boreportNo);
		
		if(doboardNo!=null) {
	        //boardno의 타입을 5로 바꾸는 메소드
	        adminDao.updateBoardtype(doboardNo);	
		}
		
		if(getdoblack!=null) {
			blacklist.setUserno(getdoblack);
			blacklist.setReason(getdoblackres);
			
		}
		
		if(getgetblack!=null) {
			blacklist.setUserno(getgetblack);
			blacklist.setReason(getgetblackres);
		}
		
	}

	


}
