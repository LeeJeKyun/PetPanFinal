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
import board.dto.CommentTable;
import board.dto.Notice;
import board.dto.ReportBoard;
import board.service.face.BoardService;
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
	public void inputComment(CommentTable commentTable) {
		boardDao.insertCommentToCareBoard(commentTable);
	}
	
	@Override
	public List<Map<String, Object>> getCommentList(int boardno) {
		
		return boardDao.selectCommentByBoardno(boardno);
	}
	
	@Override
	public List<Map<String, Object>> getNoticeListToCare() {
		return boardDao.selectNoticeToCare();
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
		
		logger.info("{}", list);
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
			if(no.get(i) == -1) continue;  // -1 이면 올리지 않는 취소한 파일
			
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

		// 신고 테이블이 있는 게시글인지 검사
		if(boardDao.selectReportBoard(boardNo) > 0) {
			// 있으면 boardTypeNo을 5로 변경
			boardDao.updateBoard(boardNo);
		}else {
			//없으면 게시글 삭제
			//게시글 파일 삭제
			boardDao.deleteBoardFile(boardNo);
			//게시글 삭제
			boardDao.deleteBoard(boardNo);
			
		}
		
		// 없으면 게시글 삭제
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
		}
	}

	@Override
	public boolean isLike(BoardRecommend boardReco) {
		
		if( boardDao.selectIsReco(boardReco) > 0 ) {
			return true;
		}
		return false;
	}
	public Map<String, Object> getCareView(int boardNo) {
		
		Map<String, Object> boardMap = boardDao.selectBoardOne(boardNo);
//		logger.info("boardMap : {}", boardMap);
		
		return boardMap;
	}


	
}
