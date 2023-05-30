package admin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import admin.dao.face.AdminDao;
import admin.dao.face.AdminShopDao;
import admin.dto.Blacklist;
import admin.dto.Notice;
import admin.dto.ReportBoard;
import admin.dto.ReportComment;
import admin.service.face.AdminService;
import board.dto.Board;
import board.dto.CommentTable;
import member.dto.Member;
import shop.dto.Shop;
import shop.dto.ShopFile;
import util.AdminPaging;
import util.Paging;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired AdminDao adminDao;
	@Autowired ServletContext context;
	@Autowired AdminShopDao adminShopDao;

	@Override
	public List<ReportBoard> getReportBoard(AdminPaging paging) {
		
		System.out.println(paging);
		
		List<ReportBoard> list = adminDao.ReportBoardtselectAll(paging);
		
		for(ReportBoard e : list) {
			System.out.println(e);
		}
		
		return list;
	}
	
	@Override
	public AdminPaging getPage(int curPage) {
		int totalpage = adminDao.selectTotal();
		AdminPaging paging = new AdminPaging(totalpage, curPage);

		return paging;
	}

	@Override
	public AdminPaging getPage(int curPage, String search) {
		int totalpage = adminDao.selectTotalSearch(search);
		AdminPaging paging = new AdminPaging(totalpage, curPage);
		paging.setSearch(search);

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
	public List<Blacklist> getBlacklistBoard(AdminPaging paging) {
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
			adminDao.insertBlacklist(blacklist);
			
		}
		
		if(getgetblack!=null) {
			blacklist.setUserno(getgetblack);
			blacklist.setReason(getgetblackres);
			adminDao.insertBlacklist(blacklist);
		}
		
		
		
	}
	@Override
	public List<Member> getMemberBoard(AdminPaging paging) {

		List<Member> list = adminDao.MemberselectAll(paging);
		
		for(Member b : list) {
			System.out.println(b);
		}
		
		return list;
	}
	
	@Override
	public List<Member> getsearchMemberBoard(AdminPaging paging, String keyword) {
			Map<String,Object> map = new HashMap<>();
			
			map.put("paging", paging);
			map.put("keyword", keyword);
	
		
		
			List<Member> list = adminDao.MemberselectByKeyword(map);
		
		for(Member b : list) {
			System.out.println(b);
		}
		
		return list;
	}

	@Override
	public List<ReportComment> getReportComment(AdminPaging paging) {
		List<ReportComment> list = adminDao.ReportCommentselectAll(paging);
		
		for(ReportComment e : list) {
			System.out.println(e);
		}
		
		return list;
	}

	@Override
	public AdminPaging getPageComment(int curPage) {
		
		int totalpage = adminDao.selectTotalComment();
		AdminPaging paging = new AdminPaging(totalpage, curPage);

		return paging;
	}

	@Override
	public void deleteCheckedComment(List<String> delete) {
	    List<String> deleteNoList = delete;
	    int commentno = 0;

	    for (int i = 0; i < deleteNoList.size(); i++) {
	        int deleteNo = Integer.valueOf(deleteNoList.get(i));
	        //update할 BoardNo를 받아오는 메소드
	        commentno = adminDao.selectCommentNo(deleteNo);
	        //boardno의 타입을 5로 바꾸는 메소드
	        adminDao.updateReportComment(commentno);
	        //Report의 complete를 N->Y로 바꾸는 메소드
	        adminDao.updateReportCompleteComment(deleteNo);
	        
	    }
		
	}

	@Override
	public ReportComment getReportViewComment(String coreportNo) {
		int coreportno = Integer.valueOf(coreportNo);
		ReportComment reportComment = adminDao.selectReportInfoComment(coreportNo);
		return reportComment;
	}

	@Override
	public CommentTable getComment(int commentNo) {
		
		CommentTable comment = adminDao.selectCommentDetail(commentNo);
		return comment;
	}

	@Override
	public void changeReportComment(Integer coreportNo, Integer docommentNo, Integer getdoblack, Integer getgetblack,
			String getdoblackres, String getgetblackres) {
		adminDao.updateReportCompleteComment(coreportNo);
		Blacklist blacklist = new Blacklist();
		
		if(docommentNo!=null) {
	        //comment의 내용을 지워서 '관리자에 의해 삭제된 게시글 입니다' 로 업데이트 하는 구문
	        adminDao.updateReportComment(docommentNo);	
		}
		
		if(getdoblack!=null) {
			blacklist.setUserno(getdoblack);
			blacklist.setReason(getdoblackres);
			adminDao.insertBlacklist(blacklist);
			
		}
		
		if(getgetblack!=null) {
			blacklist.setUserno(getgetblack);
			blacklist.setReason(getgetblackres);
			adminDao.insertBlacklist(blacklist);
		}
		
	}

	@Override
	public Member getGetMemberComment(int commentNo) {
		//boardno로 신고 당한 사람의 정보를 꺼내는 메소드
		int userno = adminDao.selectCommentuser(commentNo);
		Member member = adminDao.selectMember(userno);
		return member;
	
	}
	
	@Override
	public List<Notice> getNoticeListByType(int theme) {
		List<Notice> list = adminDao.SelectBytheme(theme);
		return list;
	}

	@Override
	public List<ReportBoard> getSearchReportBoard(AdminPaging paging) {
		
		List<ReportBoard> list = adminDao.ReportBoardSearchselectAll(paging);
		
		for(ReportBoard e : list) {
			System.out.println(e);
		}
		
		return list;
	}

	@Override
	public AdminPaging getShopPage(int curPage, String search) {
		int totalpage = adminShopDao.selectShopTotalSearch(search);
		
		AdminPaging paging = new AdminPaging(totalpage, curPage);
		paging.setSearch(search);

		return paging;
	}

	@Override
	public List<Shop> getSearchShopBoard(AdminPaging paging) {
		
		System.out.println(paging);
		
		List<Shop> list = adminShopDao.ShoptselectAll(paging);
		
		for(Shop e : list) {
			System.out.println(e);
		}
		
		return list;
	}

	@Override
	public void deleteCheckedShop(List<String> delete) {
	    List<String> deleteNoList = delete;
	    
	    for (int i = 0; i < deleteNoList.size(); i++) {
	        int deleteNo = Integer.valueOf(deleteNoList.get(i));
	        adminShopDao.updateShop(deleteNo); 
	    }
		
	}

	@Override
	public int saveShopGetObjectno(Shop shop) {
		int objectno = adminShopDao.selectNextObj();
		System.out.println(objectno);
		shop.setObjectno(objectno);
		adminShopDao.insertShop(shop);

		return objectno;
	}

	@Override
	public void saveShopFiles(List<MultipartFile> fileList, int objectno, List<Integer> no) {
		
		for(int i = 0; i < fileList.size(); i++) {
			if( no!=null && no.get(i) == -1) continue;  // -1 이면 올리지 않는 취소한 파일
			
			if(fileList.get(i).getSize() <= 0)  continue;  // 파일의 크기가 0이면  
			
			// 파일이 저장될 경로
			String storedPath = context.getRealPath("upload");
			logger.info(" storedPath : {}", storedPath);
			
			// upload폴더가 없으면 생성
			File storedFolder = new File(storedPath);
			storedFolder.mkdir();
			
			File dest = null;
			String storedName = null;
			
			do {
				//저장할 파일 이름 생성
				storedName = fileList.get(i).getOriginalFilename(); //원본 파일명
				
				storedName += UUID.randomUUID().toString().split("-")[0]; //
				logger.info("storedName : {}", storedName);

				//실제 저장될 파일 객체
				dest = new File(storedFolder, storedName);
				
			}while(dest.exists());
			
			try {
				// 업로드된 파일을 upload 폴더에 저장
				fileList.get(i).transferTo(dest);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//DB에 저장할 객체
			ShopFile shopfile = new ShopFile();
			
			shopfile.setObjectno(objectno);
			shopfile.setOriginname(fileList.get(i).getOriginalFilename());
			shopfile.setStoredname(storedName);
			shopfile.setFilesize(fileList.get(i).getSize());
			
			
			logger.info("shopfile: {} ",shopfile);
			
			//DB insert
			adminShopDao.insertShopFile(shopfile);
			
		}
		
	}

	@Override
	public Shop getShopDetailByobjectno(Integer objectno) {
		Shop shop = adminShopDao.selectShop(objectno);
		return shop;
	}

	@Override
	public List<ShopFile> getshopFileByobjectno(Integer objectno) {
		
		List<ShopFile> list = adminShopDao.selectShopFile(objectno);
				
			for(ShopFile e : list) {
					System.out.println(e);
			}		
				
				
		return list;
	}

	@Override
	public void changeAndDeleteFile(List<Integer> delete, List<Integer> save) {
		if(save!=null) {
			for(int i=save.size()-1; i>=0 ;i--) {
				int remove = save.get(i);
				delete.remove(remove);
			}
			
				
				
			for(int e : delete) {
				ShopFile deletefile = adminShopDao.selectShopFileByFileno(e);
				String storedPath = context.getRealPath("upload");
				String storedName = "\\";
				storedName += deletefile.getStoredname();
				storedPath += storedName;
				System.out.println(storedName);
				System.out.println(storedPath);
				
				File file = new File(storedPath);
				
				if( file.exists() ){
		    		if(file.delete()){
		    			System.out.println("파일삭제 성공");
		    		}else{
		    			System.out.println("파일삭제 실패");
		    		}
		    	}else{
		    		System.out.println("파일이 존재하지 않습니다.");
		    	}
				adminShopDao.deleteChangeFileOnDb(e);	
			}
				
				
			}
		
		}

	@Override
	public void changeShop(Shop shop, Integer objectno) {
		shop.setObjectno(objectno);
		
		adminShopDao.updateShopDetail(shop);
		
	}


	}
	

