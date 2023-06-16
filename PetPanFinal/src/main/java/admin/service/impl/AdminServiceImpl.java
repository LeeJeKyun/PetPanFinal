package admin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import admin.dao.face.AdminDao;
import admin.dao.face.AdminShopDao;
import admin.dto.Blacklist;
import admin.dto.ReportBoard;
import admin.dto.ReportComment;
import admin.dto.ReportObject;
import admin.service.face.AdminService;
import board.dto.Board;
import board.dto.Comment;
import board.dto.Notice;
import board.dto.NoticeFile;
import member.dao.face.MemberDao;
import member.dto.Member;
import shop.dto.OrderThing;
import shop.dto.Shop;
import shop.dto.ShopFile;
import util.AdminPaging;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired AdminDao adminDao;
	@Autowired ServletContext context;
	@Autowired AdminShopDao adminShopDao;
	@Autowired MemberDao memberDao;


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
		if (delete == null) {
			return;
		}
		
	    List<String> deleteNoList = delete;
	    int boardno = 0;
        List<HashMap> boardnoMaplist = new ArrayList<HashMap>();
        List<HashMap> deleteNoMaplist = new ArrayList<HashMap>();

	    for (int i = 0; i < deleteNoList.size(); i++) {
	        int deleteNo = Integer.valueOf(deleteNoList.get(i));
	        //update할 BoardNo를 받아오는 메소드
	        boardno = adminDao.selectBoardNo(deleteNo);
	        
	        //boardno의 타입을 5로 바꾸는 메소드
//	        adminDao.updateBoardtype(boardno);
	        //Report의 complete를 N->Y로 바꾸는 메소드
//	        adminDao.updateReportComplete(deleteNo);
	        
	        // 동적쿼리를 적용하기 위해서 MAP에 저장하는 과정
	        HashMap<String, Integer> boardnoMap = new HashMap<String, Integer>();
	        HashMap<String, Integer> deleteNoMap = new HashMap<String, Integer>();
	        // 받아온 boardno를 Map에 저장하게 하는 코드
	        boardnoMap.put("boardno", boardno);
	        // deleteNo로 받아온 것을 boreportno를 Map에 저장하게 하는 코드
	        deleteNoMap.put("boreportno", deleteNo);
	        
//	        System.out.println(boardnoMap);
//	        System.out.println(deleteNoMap);
	        // MAP 값을 리스트에 저장한다.
	        boardnoMaplist.add(i, boardnoMap);
	        deleteNoMaplist.add(i, deleteNoMap);
	    }
	    
//	    System.out.println(boardnoMaplist);
//	    System.out.println(deleteNoMaplist);
	    
//		for(HashMap e : boardnoMaplist) {
//			System.out.println(e);
//		}
//		for(HashMap e : deleteNoMaplist) {
//			System.out.println(e);
//		}
	    //저장된 리스트들을 한번에 작동시킨다.
	    adminDao.updateBoardtypelist(boardnoMaplist);
	    adminDao.updateReportCompletelist(deleteNoMaplist);
		
	}
	
	
	@Override
	public List<Map<String, Object>> getBlacklistBoard(AdminPaging paging) {
		System.out.println(paging);
		
		List<Map<String, Object>> list = adminDao.BlacklistselectAll(paging);
		
		for(Map<String, Object> m : list) {
			
			System.out.println(m);
		}
		
		return list;
	}
		
	@Override
	public void deleteblacklist(List<String> delete) {
		
		List<String> deleteNoList = delete;
	    List<HashMap> BlacklistNoMaplist = new ArrayList<HashMap>();
	    
	    for (int i = 0; i < deleteNoList.size(); i++) {
	        int deleteNo = Integer.valueOf(deleteNoList.get(i));
	        HashMap<String, Integer> deleteNoMap = new HashMap<String, Integer>();
	        //체크로 받아온 objectNo를 맵에 저장한다.
	        deleteNoMap.put("blackno", deleteNo);
	        //map에 저장된 K/V쌍을 objectNoMaplist에 저장한다.
	        BlacklistNoMaplist.add(i, deleteNoMap);
	    }
	    adminDao.deleteBlacklist(BlacklistNoMaplist);
		
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
		
//		for(ReportComment e : list) {
//			System.out.println(e);
//		}
		
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
		if (delete == null) {
			return;
		}
	    List<String> deleteNoList = delete;
	    int commentno = 0;
	    
        List<HashMap> commentnoMaplist = new ArrayList<HashMap>();
        List<HashMap> deleteNoMaplist = new ArrayList<HashMap>();

	    for (int i = 0; i < deleteNoList.size(); i++) {
	        int deleteNo = Integer.valueOf(deleteNoList.get(i));
	        //update할 BoardNo를 받아오는 메소드
	        commentno = adminDao.selectCommentNo(deleteNo);
	        //Comment를 수정하는 메소드
//	        adminDao.updateReportComment(commentno);
	        //Report의 complete를 N->Y로 바꾸는 메소드
//	        adminDao.updateReportCompleteComment(deleteNo);
	        
	        HashMap<String, Integer> commentNoMap = new HashMap<String, Integer>();
	        HashMap<String, Integer> deleteNoMap = new HashMap<String, Integer>();
	        
	        // 받아온 commentno를 Map에 저장하게 하는 코드
	        commentNoMap.put("commentNo", commentno);
	        // deleteNo로 받아온 것을 coreportno를 Map에 저장하게 하는 코드
	        deleteNoMap.put("coreportNo", deleteNo);
	        
//	        System.out.println(boardnoMap);
//	        System.out.println(deleteNoMap);
	        // MAP 값을 리스트에 저장한다.
	        commentnoMaplist.add(i, commentNoMap);
	        deleteNoMaplist.add(i, deleteNoMap);
	        
	    }
	    
	    adminDao.updateCommentTypelist(commentnoMaplist);
	    adminDao.updateReportCompleteCommentlist(deleteNoMaplist);
		
	}

	@Override
	public ReportComment getReportViewComment(String coreportNo) {
		int coreportno = Integer.valueOf(coreportNo);
		ReportComment reportComment = adminDao.selectReportInfoComment(coreportNo);
		return reportComment;
	}

	@Override
	public Comment getComment(int commentNo) {
		
		Comment comment = adminDao.selectCommentDetail(commentNo);
		return comment;
	}

	@Override
	public void changeReportComment(Integer coreportNo, Integer docommentNo, Integer getdoblack, Integer getgetblack,
			String getdoblackres, String getgetblackres) {
		adminDao.updateReportCompleteComment(coreportNo);
		Blacklist blacklist = new Blacklist();
		
		if(docommentNo!=null) {
	        //comment의 내용을 지워서 '관리자에 의해 삭제된 댓글 입니다' 로 업데이트 하는 구문
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
		
//		for(ReportBoard e : list) {
////			System.out.println(e);
//		}
		
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
		
//		for(Shop e : list) {
//			System.out.println(e);
//		}
		
		return list;
	}

	@Override
	public void deleteCheckedShop(List<String> delete) {
		if (delete == null) {
			return;
		}
		
	    List<String> deleteNoList = delete;
	    List<HashMap> objectNoMaplist = new ArrayList<HashMap>();
	    
	    for (int i = 0; i < deleteNoList.size(); i++) {
	        int deleteNo = Integer.valueOf(deleteNoList.get(i));
	        HashMap<String, Integer> deleteNoMap = new HashMap<String, Integer>();
	        //체크로 받아온 objectNo를 맵에 저장한다.
	        deleteNoMap.put("objectNo", deleteNo);
	        //map에 저장된 K/V쌍을 objectNoMaplist에 저장한다.
	        objectNoMaplist.add(i, deleteNoMap);
	    }
	    adminShopDao.updateShopDeleteObj(objectNoMaplist);
		
	}

	@Override
	public int saveShopGetObjectno(Shop shop, MultipartFile img1) {
		
		if(img1.getSize() > 0) {

		String storedPath = context.getRealPath("upload");
		logger.info(" storedPath : {}", storedPath);
		
		// upload폴더가 없으면 생성
		File storedFolder = new File(storedPath);
		storedFolder.mkdir();
		
		File dest = null;
		String storedName = null;
		
		do {
			//저장할 파일 이름 생성
			storedName = img1.getOriginalFilename(); //원본 파일명
			
			storedName += UUID.randomUUID().toString().split("-")[0]; //
			logger.info("storedName : {}", storedName);

			//실제 저장될 파일 객체
			dest = new File(storedFolder, storedName);
			
			shop.setImg1(storedName);
		}while(dest.exists());
		
		try {
			// 업로드된 파일을 upload 폴더에 저장
			img1.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//DB에 저장할 객체
		
		}
		logger.info("shop = {}", shop);
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
//			logger.info(" storedPath : {}", storedPath);
			
			// upload폴더가 없으면 생성
			File storedFolder = new File(storedPath);
			storedFolder.mkdir();
			
			File dest = null;
			String storedName = null;
			
			do {
				//저장할 파일 이름 생성
				storedName = fileList.get(i).getOriginalFilename(); //원본 파일명
				
				storedName += UUID.randomUUID().toString().split("-")[0]; //
//				logger.info("storedName : {}", storedName);

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
			
			
//			logger.info("shopfile: {} ",shopfile);
			
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
				
//			for(ShopFile e : list) {
//					System.out.println(e);
//			}		
				
				
		return list;
	}

	@Override
	public void changeAndDeleteFile(List<Integer> delete, List<Integer> save) {
		
		
		if(save!=null) {
			for(int i=save.size()-1; i>=0 ;i--) {
				// save로부터 어떤 파일을 남길 것인지 를 계산한다.
				int remove = save.get(i);
				delete.remove(remove);
				System.out.println(remove);
			}
		}	
				
				
			for(int e : delete) {
				//남은 파일들은 save 인덱스가 지워져서 넘어오지 않은 녀석들이기 때문에 삭제를 진행한다.
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
			
		
		


	@Override
	public void changeShop(Shop shop, Integer objectno) {
		shop.setObjectno(objectno);
		
		adminShopDao.updateShopDetail(shop);
		
	}
	
	@Override
	public void writeNotice(List<MultipartFile> fileList, Notice notice, List<Integer> no, HttpSession session) {
		

		notice.setUserno((int)session.getAttribute("userno"));
		logger.info("공지 : {}", notice);
		adminDao.insertNotice(notice);
		
		if(fileList == null) return; 
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
			NoticeFile noticeFile  = new NoticeFile();
			
			noticeFile.setNoticeno(notice.getNoticeno());
			noticeFile.setOriginName(fileList.get(i).getOriginalFilename());
			noticeFile.setStoredName(storedName);
			noticeFile.setFileSize(fileList.get(i).getSize());
			
			
			logger.info("shopfile: {} ",noticeFile);
			
			//DB insert
			adminDao.insertNoticeFile(noticeFile);
			
		}
	
		}
		
		
		
		
		

	@Override
	public void stopShop(Integer objectno) {
		adminShopDao.changeShopDeleteobj(objectno);
		
	}

	@Override
	public List<HashMap<String, Object>> getBuyerList(AdminPaging adminPaging) {
		
		
		
		List<HashMap<String, Object>> list = adminShopDao.selectAllBuyer(adminPaging);
		
//		for(HashMap<String, Object> e : list) {
//			System.out.println(e);
//		}
		
		return list;
	}

	@Override
	public AdminPaging getPageBuyer(int curPage, String search) {
		int totalpage = adminShopDao.selectTotalSearchBuyer(search);
		AdminPaging paging = new AdminPaging(totalpage, curPage);
		paging.setSearch(search);

		return paging;
	}

	@Override
	public void completeCheckedBuyer(List<String> delete) {
		
		if (delete == null) {
			return;
		}
		
	    List<String> completeNoList = delete;
	    OrderThing orderThing = new OrderThing();
	    
        List<HashMap> deleteNoMaplist = new ArrayList<HashMap>();

	    for (int i = 0; i < completeNoList.size(); i++) {
	        int completno = Integer.valueOf(completeNoList.get(i));
	        //update할 buyno를 받아오는 메소드
	        orderThing = adminShopDao.selectObjectno(completno);
	        adminShopDao.updateObjectReamin(orderThing);
	        int remain = adminShopDao.selectObjectReamin(orderThing);
	        if(remain<=0) {
	        	adminShopDao.updateShop(orderThing.getObjectno());
	        }
	        HashMap<String, Integer> deleteNoMap = new HashMap<String, Integer>();
	        deleteNoMap.put("buyNo", completno);
	        
//	        System.out.println(boardnoMap);
//	        System.out.println(deleteNoMap);
	        // MAP 값을 리스트에 저장한다.
	       
	        deleteNoMaplist.add(i, deleteNoMap);
	    }
	    
//	    System.out.println(deleteNoMaplist);
//		for(HashMap e : boardnoMaplist) {
//			System.out.println(e);
//		}
//		for(HashMap e : deleteNoMaplist) {
//			System.out.println(e);
//		}
	    //저장된 리스트들을 한번에 작동시킨다.
	    
	    adminShopDao.updateOrderUserComplete(deleteNoMaplist);
		
		
		
	}
	
	
	@Override
	public void updateMember(Member member) {
		
		int res =  adminDao.updateMember(member);
		
		logger.info("{}",res);
		
	}

	@Override
	public AdminPaging getPagereportShop(int curPage, String search) {
		int totalcount = adminShopDao.countTotalReportShop(search);
		AdminPaging paging = new AdminPaging(totalcount, curPage);
		paging.setCurPage(curPage);
		paging.setSearch(search);
		
		return paging;
	}

	@Override
	public List<ReportObject> getReportShopList(AdminPaging paging) {
		
		List<ReportObject> list = adminShopDao.ReportShopselectAll(paging);
		
		for(ReportObject e : list) {
			System.out.println(e);
		}
		
		return list;
	}

	@Override
	public ReportObject getReportObject(Integer objreportNo) {
		ReportObject reportObject = adminShopDao.selectReportObject(objreportNo);
				
				
		return reportObject;
	}

	@Override
	public Member getShopReportMember(ReportObject reportObject) {
		Member member = adminShopDao.selectReportMember(reportObject);
		return member;
	}

	@Override
	public void changeObjReportAndAddBlack(Integer objreportNo, Integer objectNo, Integer userNo, String reason) {
		adminShopDao.updateReportobject(objreportNo);
		
		if(objectNo!=null) {
			adminShopDao.changeShopDeleteobj(objectNo);
		}
		if(userNo!=null) {
			Blacklist blacklist = new Blacklist();
			blacklist.setUserno(userNo);
			blacklist.setReason(reason);
			
			adminDao.insertBlacklist(blacklist);
		}
		
	}

	@Override
	public void changeObjReport(List<String> delete) {
		
		if (delete == null) {
			return;
		}
		
		List<HashMap> objreportNolist = new ArrayList<HashMap>();
		
		  for (int i = 0; i < delete.size(); i++) {
		       Integer deleteno = Integer.valueOf(delete.get(i));
		       HashMap<String, Integer> deleteNoMap = new HashMap<String, Integer>();
		       deleteNoMap.put("objreportNo", deleteno);
		       
		       objreportNolist.add(deleteNoMap);
		       
		    }
		  
		  adminShopDao.updateReportObjectComplete(objreportNolist);
		
	}
	  

	
	@Override
	public Notice getNotice(int noticeno) {
		Notice notice = adminDao.selectBynoticeno(noticeno);
		
		return notice;
	}
	
	
	@Override
	public List<NoticeFile> getNotiaceFilelist(int noticeno) {
		List<NoticeFile> list = adminDao.selectnoticeFile(noticeno);
		
		for(NoticeFile e : list) {
				logger.info("{}",e);
		}	
		
		return list;
	}
	
	@Override
	public void deletenotice(int noticeno) {
		adminDao.deletenoticeFile(noticeno);
		adminDao.deletenotice(noticeno);
		
	}
	
	
	@Override
	public void changeNotice(Notice notice) {
		// TODO Auto-generated method stub
		
		adminDao.updatenoticeDetail(notice);
		
	}
	@Override
	public void noticechangeAndDeleteFile(List<Integer> delete, List<Integer> save) {
		// TODO Auto-generated method stub
		
		if(save!=null) {
		for(int i=save.size()-1; i>=0 ;i--) {
			int remove = save.get(i);
			delete.remove(remove);
			System.out.println(remove);
		}
	}	
			
	if(delete != null) {
		for(int e : delete) {
			NoticeFile deletefile = adminDao.selectnoticeFileByFileno(e);
			String storedPath = context.getRealPath("upload");
			String storedName = "\\";
			storedName += deletefile.getStoredName();
			System.out.println(storedName);
			System.out.println(storedPath);
			
			File file = new File(storedPath, storedName);
			
			
    		if(file.delete()){
    			System.out.println("파일삭제 성공");
    		}else{
    			System.out.println("파일삭제 실패");
    		}

			adminDao.deleteFileByFileno(e);	
		}
	}
		
	}
	@Override
	public void saveNoticeFiles(List<MultipartFile> fileList, Notice notice, List<Integer> no) {
		
		if(fileList == null) return; 
		
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
			NoticeFile noticeFile  = new NoticeFile();
			
			noticeFile.setNoticeno(notice.getNoticeno());
			noticeFile.setOriginName(fileList.get(i).getOriginalFilename());
			noticeFile.setStoredName(storedName);
			noticeFile.setFileSize(fileList.get(i).getSize());
			
			
			logger.info("noticefile: {} ",noticeFile);
			
			//DB insert
			adminDao.insertNoticeFile(noticeFile);
			
		}
		
	}
	@Override
	public AdminPaging getBlacklistPage(int curPage) {
		
		int totalpage = adminDao.selectAllBlacklist();
		AdminPaging paging = new AdminPaging(totalpage, curPage);

		return paging;
	}
	
	@Override
	public AdminPaging getmemberPage(int curPage) {
		int totalpage = adminDao.selectAllMember();
		AdminPaging paging = new AdminPaging(totalpage, curPage);

		return paging;
	}
	
	@Override
	public AdminPaging getSearchMemberPage(int curPage,String keyword) {
		int totalpage = adminDao.selectSearchMember(keyword);
		AdminPaging paging = new AdminPaging(totalpage, curPage);

		return paging;
	}
	
	
	
	

	@Override
	public void resellCheckedShop(List<String> resell) {
		if (resell == null) {
			return;
		}
		
	    List<String> resellNoList = resell;
	    List<HashMap> resellNoMaplist = new ArrayList<HashMap>();
	    
	    for (int i = 0; i < resellNoList.size(); i++) {
	        int resellNo = Integer.valueOf(resellNoList.get(i));
	        HashMap<String, Integer> resellNoMap = new HashMap<String, Integer>();
	        resellNoMap.put("objectNo", resellNo );
	        resellNoMaplist.add(i, resellNoMap);
	    }
	    
	    adminShopDao.updateShopResellObj(resellNoMaplist);
		
		
	}
	
	@Override
	public void appointmentAdmin(int userno) {
		adminDao.updateMemberToAdmin(userno);
		
	}

	@Override
	public int findBlack(int userNo) {
		int cheak = adminDao.selectCountBlackByuserNo(userNo);
		return cheak;
	}
	
	@Override
	public int isBlack(int userno) {
		return adminDao.selectCntByuserno(userno);
	}
	
	@Override
	public void deleteblacklistOne(int userno) {
		adminDao.deleteBlacklistOne(userno);
		
	}
	
}


	




