package board.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import board.dao.face.BoardDao;
import board.dto.Board;
import board.dto.BoardFile;
import board.dto.BoardRecommend;
import board.dto.Comment;
import board.dto.Hospital;
import board.dto.HospitalFile;
import board.dto.Notice;
import board.dto.ReportBoard;
import board.dto.ReportComment;
import board.service.face.BoardService;
import member.dto.Member;
import util.Paging;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired BoardDao boardDao;
	@Autowired ServletContext context;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Paging getNoticePaging(int curPage, String search) {
		
		Paging paging = null;
		paging = new Paging(boardDao.selectNoticeCntAll(search), curPage, 10, 5);
		paging.setSearch(search);
		
//		logger.info("{}",paging);
		
		return paging;
	}

	@Override
	public List<Map<String, Object>> getNoticeList(Paging paging) {
		
		return boardDao.selectNoticeAll(paging);
	}

	@Override
	public Notice getNoticeView(int noticeno) {
		return boardDao.selectNoticeByNoticeno(noticeno);
	}

	@Override
	public Paging getCarePaging(int curPage, String search) {
		
		Paging paging = null;
		paging = new Paging(boardDao.selectCareCntAll(search), curPage, 10, 10);
		paging.setSearch(search);
		
		return paging;
	}

	@Override
	public List<Map<String, Object>> getCareList(Paging paging) {
		return boardDao.selectCareAll(paging);
	}
	
	@Override
	public int getUserno(String loginid) {
			
		return boardDao.selectUsernoByUserid(loginid);
	}
	
	
	@Override
	public void upload(Board board, List<MultipartFile> file) {
		
		int boardno = boardDao.selectBoardSeqNext();
		board.setBoardNo(boardno);
		boardDao.insertCareBoard(board);
		
		for(int i=0; i<file.size(); i++) {
			MultipartFile nowfile = file.get(i);
			
			if(nowfile.getSize() <= 0) {
				return;
			}
			
			String storedPath = context.getRealPath("upload");
			
			logger.info(storedPath);
			
			File storedFolder = new File(storedPath);
			storedFolder.mkdir();
			
			File dest = null;
			String storedName = null;
			
			do {
				storedName = nowfile.getOriginalFilename();
				storedName += UUID.randomUUID().toString().split("-")[0];
				
				dest = new File(storedFolder, storedName);
				
			} while( dest.exists() );
			
			try {
				nowfile.transferTo(dest);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Map<String, Object> map  = new HashMap<>();
			
			map.put("boardNo", boardno);
			map.put("originName", nowfile.getOriginalFilename());
			map.put("storedName", storedName);
			map.put("fileSize", nowfile.getSize());
			
			
			int a = boardDao.insertBoardFile(map);
			
			logger.info("upload 메소드 ---{}번째---", i+1);
			logger.info("{}", a);
			
		}
		
	}
	
	@Override
	public List<BoardFile> getCareFile(int boardNo) {
		
		return boardDao.selectCareBoardFile(boardNo);
	}
	
	@Override
	public void recommendBoardCare(int boardNo, int userNo) {
		boolean isRecommended = false;
		Map<String, Integer> map = new HashMap<>();
		map.put("boardNo", boardNo);
		map.put("userNo", userNo);
		
		if( boardDao.selectRecommendCntByBoardNoUserNo(map) > 0) {
//			logger.info("추천내역이 있다~!");
			boardDao.deleteRecommendToCare(map);
//			logger.info("추천내역을 지웠따!");
		} else {
//			logger.info("추천내역이 없다~!");
			boardDao.insertRecommendToCare(map);
//			logger.info("추천내역을 올렸따!");
		}
	}
	
	@Override
	public int getRecommendCnt(int boardNo) {
		
		
		return boardDao.selectRecommendCnt(boardNo);
	}
	
	@Override
	public boolean isRecommended(int boardNo, int userNo) {
		
		Map<String, Integer> map = new HashMap<>();
		
		map.put("boardNo", boardNo);
		map.put("userNo", userNo);
		
		if( boardDao.selectRecommendCntByBoardNoUserNo(map) > 0) {
			logger.info("추천이있다.");
			return true;
		}
		
		return false;
	}
	
	@Override
	public void inputComment(Comment comment) {
		int refcommentno = comment.getRefCommentNo();
		comment.setDepth(comment.getDepth()+1);
		
		logger.info("{}", comment);
		
		if(refcommentno == 0 ) {
			comment.setCommentNo(boardDao.selectMaxCommentNo());
			comment.setOrganization(comment.getCommentNo());
			comment.setSortNo(1);
		} else {	//depth가 2이상일때의 처리
			//commentno은 시퀀스에서 가져오기
			comment.setCommentNo(boardDao.selectMaxCommentNo());
			
			//organ은 refcommentno의 organ
			comment.setOrganization(boardDao.selectRefOrgan(refcommentno));
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("organization", comment.getOrganization());
			map.put("refcommentno", comment.getRefCommentNo());
			
			//sortno을 1씩 올리기
			boardDao.updateAfterSortnoUpper(map);
			comment.setSortNo(boardDao.selectSortNoByRef(refcommentno)+1);
		}
		
		logger.info("{}", comment);
		
		boardDao.insertCommentToCareBoard(comment);
	}
	
	@Override
	public List<Map<String, Object>> getCommentList(int boardno) {
		
		return boardDao.selectCommentByBoardno(boardno);
	}
	
	@Override
	public List<Map<String, Object>> getNoticeListToCare() {
		return boardDao.selectNoticeToCare();
	}
	
	@Override
	public void deleteBoardByBoardObj(Board board) {
		int res  = boardDao.deleteByUpdateBoardType(board);
		
		logger.info("{}", res);
	}
	
	@Override
	public Member getMemberByBoard(Map<String, Object> map) {
		return boardDao.getMemberByBoardMap(map);
	}

	
	//-------------------------------제균----------------------------------

	
	@Override
	public Paging getPaging(Integer curPage, int category, String search) {
		Paging paging = null;
		
		if(search == null) search = "";
		
		
		if(category == 2) {
			paging = new Paging(boardDao.selectFreeBoardCnt(search), curPage);
		}else if(category == 3) {
			paging = new Paging(boardDao.selectMarketBoardCnt(search), curPage);
		}
		paging.setSearch(search);
		logger.info("{}", paging);
		
		
		return paging;
	}

	@Override
	public List<Map<String, Object>> getBoard(Paging paging, int category) {
		logger.info("service getBoard : {}", category);
		Map<String, Object> map = new HashMap<>();
		
		if(paging.getSearch() == null) paging.setSearch("");
		
//		map.put("startNo", paging.getStartNo());
//		map.put("endNo", paging.getEndNo());
		
		
		List<Map<String, Object>> list = null;
		if(category == 3) { // 중고거래 게시판
			list = boardDao.selectMarketList(paging);
		}else if(category == 2) { //자유 게시판
			list = boardDao.selectFreeList(paging);
		}
		
		logger.info(" list {}", list);
		return list;
	}

	@Override
	public List<Map<String, Object>> getNotice(int category) {
		logger.info("{}", category);
		logger.info("{}", boardDao.selectNoticeCnt(category));
		
		Map<String, Integer> map = new HashMap<>();
		map.put("category", category);
		map.put("max", boardDao.selectNoticeCnt(category));
		
		logger.info("{}", boardDao.selectNotice(map));
		
		return boardDao.selectNotice(map);
	}

	@Override
	public int writeBoard(Board board) {

		//다음 boardNo 가져오기
		int boardNo = boardDao.selectBoardNext();
		board.setBoardNo(boardNo);
		
		//board 객체 저장
		boardDao.insertBoard(board);
		
		return boardNo;
	}

	@Override
	public void saveFiles(List<MultipartFile> fileList, int boardNo, List<Integer> no) {
		for(int i = 0; i < fileList.size(); i++) {
			
			if(null == no || no.get(i) == -1) continue;  // -1 이면 올리지 않는 취소한 파일
			
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
			Map<String, Object> map  = new HashMap<>();
			
			map.put("boardNo", boardNo);
			map.put("originName", fileList.get(i).getOriginalFilename());
			map.put("storedName", storedName);
			map.put("fileSize", fileList.get(i).getSize());
			
			logger.info("map: {} ",map);
			
			//DB insert
			boardDao.insertBoardFile(map);
		}
	}
	// boardno, boardTitle, hit, recommend, writeDate, userName, content, boardTypeNo, userNo, writeDate
	@Override
	public Map<String, Object> getBoardOne(int boardNo) {
		return boardDao.selectBoardOne(boardNo);
	}

	@Override
	public List<BoardFile> getBoardFile(int boardNo) {
		return boardDao.selectFiles(boardNo);
	}

	@Override
	public void deleteBoard(int boardNo) {

		// 게시글 타입 5번으로 변경
		boardDao.updateBoard(boardNo);
	}

	@Override
	public void boardReport(ReportBoard reportBoard, String writeDetail) {
		//동일 유저가 동일 게시글 신고했는지 확인
		if( boardDao.selectIsReport(reportBoard) > 0) {
			//있다 처리 안함
		}else {
			// 없다 신고 처리
			if("기타".equals(reportBoard.getReportDetail())) {
				reportBoard.setReportDetail(writeDetail);
			}
			boardDao.insertReport(reportBoard);
			logger.info(" 최종 신고 {}", reportBoard);
		}
	}

	@Override
	public boolean isLike(BoardRecommend boardReco) {
		
		if( boardDao.checkReco(boardReco) > 0 ) {
			return true;
		}
		return false;
	}
	public Map<String, Object> getCareView(int boardNo) {
		
		Map<String, Object> boardMap = boardDao.selectBoardOne(boardNo);
//		logger.info("boardMap : {}", boardMap);
		
		return boardMap;
	}

	@Override
	public boolean Reco(BoardRecommend boardReco) {

		boolean flag = false;
		
		if( boardDao.checkReco(boardReco)  > 0) { 
			boardDao.deleteReco(boardReco); // 추천 취소
			
		}else { 
			boardDao.insertBoardReco(boardReco); //추천하기
			flag = true;
		}
		return flag;
	}

	@Override
	public int getCountReco(BoardRecommend boardReco) {

		return boardDao.selectCntReco(boardReco);
	}

	@Override
	public List<Map<String, Object>> getComments(int boardNo) {

		Map<String, Integer> map = new HashMap<>();
		map.put("boardNo", boardNo);
		
		//가장 큰 cnt가져오기 , 가장 최근 댓글
//		Integer cnt = boardDao.selectMaxCommentCnt(boardNo);
//		
//		map.put("max", cnt);
//		map.put("min", 1);
		return boardDao.selectComments(map);
	}

	@Override
	public Comment addComment(Comment comment) {
		int commentNo;
		//commentNo이 0일 때 일반 댓글
		if(comment.getCommentNo() == 0) {
			//dept 1
			//organaization은 자신의 commentNo
			commentNo = boardDao.selectMaxCommentNo();
			comment.setCommentNo( commentNo );
			comment.setOrganization( commentNo );
			comment.setDepth(1);
			
			//  depth가 1인 댓글은 sortNo이 1 ;   가장 마지막 sortNo 가져와서 +1 하고 넣기
			comment.setSortNo(1);
			logger.info("comment {}" , comment);
			
			// 댓글 삽입
			boardDao.insertComment(comment);
			
		}else {
		// 댓글을 달 commentNo의 depth와 sortNo, organization 가져오기
		Map<String, Integer> map = boardDao.selectDepthAndSortNo( comment.getCommentNo() );
		
		logger.info("댓글 map {}", map);
		logger.info("댓글 sortno {}", map.get("SORTNO"));
		logger.info("댓글 orga {}", map.get("ORGANIZATION"));
		logger.info("댓글 depth {}", map.get("DEPTH"));
		
		int sortno = Integer.parseInt(String.valueOf(map.get("SORTNO")));
		int depth = Integer.parseInt(String.valueOf(map.get("DEPTH")));
		int organization = Integer.parseInt(String.valueOf(map.get("ORGANIZATION")));
		
		comment.setSortNo(sortno + 1);
		comment.setDepth(depth +1 );
		comment.setOrganization(organization );
		
		//organization 중에서 map.get(sortNo)보다 큰 것들 다 +1
		boardDao.updatePlueSortNo(map);
		
		// 다음 commentNo 가져오기
		commentNo = boardDao.selectMaxCommentNo();
		comment.setCommentNo(commentNo); 
		
		//댓글 삽입
		logger.info(" 대댓 삽입 comment  : {}",comment);
		
		boardDao.insertComment(comment);
		
		}
		
		//삽입한 댓글 가져오기
		comment = boardDao.selectCommentByCommentNo(commentNo);
		
		return comment;
	}

	@Override
	public String getUsername(int userNo) {
		
		return boardDao.selectUserNameByUserNo(userNo);
	}

	@Override
	public void deleteComment(int commentNo) {

		//댓글의 내용을 작성자가 삭제한 댓글이라고 바꾼다.
		boardDao.updateComment(commentNo);
	}

	@Override
	public void reportComment(ReportComment rc, String writeDetail) {
		if("기타".equals(rc.getReportdetail()) ) {
			rc.setReportdetail(writeDetail);
		}
		boardDao.insertCommentNo(rc);
	}

	@Override
	public void enrollHospital(List<MultipartFile> fileList, List<Integer> no, member.dto.Hospital hospital) {

		//회원가입할 때 입력했던 병원번호 no 가져오기
		int hospitalNo = boardDao.selectHospitalNo(hospital.getUserNo());
		logger.info("병원 정보 NO {}", hospitalNo);
		logger.info("no no 병원파일{}", no);
		logger.info("fileList 병원파일{}", fileList);
		for(int i = 0; i < no.size(); i++) {
			if(null != no.get(i) && no.get(i) != -1) {
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
				Map<String, Object> mapFile  = new HashMap<>();
				
				mapFile.put("storedName", storedName);
				mapFile.put("originName", fileList.get(i).getOriginalFilename());
				mapFile.put("fileSize", fileList.get(i).getSize());
				mapFile.put("hospitalNo", hospitalNo);
				
				//병원 사진 저장
				boardDao.insertHospitalFile(mapFile);
				
				logger.info("mapFile 병원 {}", mapFile);
			}
		}
	}

	@Override
	public Member getUserInfo(int userNo) {
		return boardDao.selectUserInfo(userNo);
	}


	
}
