package shop.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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

import member.dto.Member;
import shop.dao.face.ShopDao;
import shop.dto.Basket;
import shop.dto.OrderThing;
import shop.dto.OrderUser;
import shop.dto.ReportObject;
import shop.dto.Review;
import shop.dto.ReviewFile;
import shop.dto.Shop;
import shop.dto.ShopFile;
import shop.service.face.ShopService;
import util.ReviewPaging;
import util.ShopPaging;

@Service
public class ShopServiceImpl implements ShopService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired ServletContext context;
	@Autowired ShopDao shopDao;
	
	
	@Override
	public ShopPaging getpaging(int curPage, String search) {
		
		//게시글 총 갯수
		int total = shopDao.countShop(search);
		
		ShopPaging paging = new ShopPaging(total, curPage);
		paging.setSearch(search);
		
		return paging;
	}
	
	@Override
	public List<Shop> shoplist(ShopPaging paging) {

		//페이징 된 쇼핑 메인페이지 리스트
		List<Shop> list = shopDao.selectAll(paging);
		
		return list;
	}
	
	@Override
	public Shop view(Shop shop) {

		//쇼핑 상세페이지
		Shop view = shopDao.selectByObjno(shop);
		
		return view;
	}
	
	public List<ShopFile> shopfile(Basket basket){
		
		//상품 사진
		List<ShopFile> list = shopDao.shopfile(basket);
		
		return list;
	}
	
	@Override
	public void insertBasket(Basket basket) {
		
		if(basket.getQuantity() <= 0) {
			return;
			//물건 개수가 없으면 리턴
		} else {
			
			//기존 장바구니 확인
			int cnt = shopDao.checkBasket(basket);
			
			if(cnt <= 0) {
				//기존 장바구니 없으면 insert
				shopDao.insertBasket(basket);
			}else {
				//기존 장바구니 없으면 update
				shopDao.updateBasket(basket);
			}
		}
	}
	
	@Override
	public List<Map<String,Object>> selectBasket(Basket basket) {
		
		//장바구니 보여주기
		List<Map<String,Object>> list = shopDao.selectBasket(basket);
		
		return list;
	}
	
	
	
	@Override
	public Member memberShop(Basket basket) {
		
		//장바구니 담은 유저 정보
		return shopDao.memberShop(basket);
	}
	
	@Override
	public void insertOrder(List<Map<String, Object>> list) {
		//구매내역
		
		OrderUser orderUser = new OrderUser();
		OrderThing orderThing = new OrderThing();
		
		
		for(int i=0; i<list.size(); i++) {
			
			int buyno = shopDao.buyno();
			
//			int userno =  (int)list.get(i).get("USERNO");
			int userno = ((BigDecimal)list.get(i).get("USERNO")).intValue();
			int objectno = ((BigDecimal)list.get(i).get("OBJECTNO")).intValue();
			int quantity = ((BigDecimal)list.get(i).get("QUANTITY")).intValue();
			
			Basket basket = new Basket(); 
			
			basket.setUserno(userno);
			Member member = shopDao.memberShop(basket);
			
			String buyername =  member.getUserName();
			String buyeradd = member.getAddress();
			String buydetailaddress = member.getDetailaddress();
			String buyerzip = member.getZipCode();
			String buyerphone = member.getPhone();
			
			orderUser.setBuyno(buyno);
			orderUser.setBuyeradd(buyeradd);
			orderUser.setBuydetailaddress(buydetailaddress);
			orderUser.setBuyername(buyername);
			orderUser.setBuyerphone(buyerphone);
			orderUser.setBuyerzip(buyerzip);
			orderUser.setUserno(userno);
			
			orderThing.setBuyno(buyno);
			orderThing.setQuantity(quantity);
			orderThing.setObjectno(objectno);
			
			//DB저장
			shopDao.insertOrderUser(orderUser);
			shopDao.insertOrderThing(orderThing);
			
		}
		
	}
	
	@Override
	public void buyDeleteBasket(int userno) {
		
		//장바구니 삭제
		shopDao.buyDeleteBasket(userno);
		
	}
	
	@Override
	public void deleteBasket(Basket basket) {
	
		//basketno 가져오기
		Basket selectBasket = shopDao.selectDeleteBasket(basket);
		
		int basketno = selectBasket.getBasketno();
		
		//장바구니 삭제
		shopDao.deleteBasket(basketno);
	}
	
	@Override
	public List<Map<String, Object>> reviewList(Review review) {
		
		List<Map<String, Object>> list = shopDao.reviewList(review);
			
		return list;
	}

	@Override
	public List<Map<String, Object>> orderList(OrderUser orderUser) {
		
		List<Map<String, Object>> list = shopDao.orderList(orderUser);
		
		return list;
	}
	
	@Override
	public void writeReview(List<MultipartFile> fileList, Review review, List<Integer> no, OrderUser orderUser) {
		
		int reviewno = shopDao.selectNextval();
		review.setReviewno(reviewno);
		review.setBuyno(orderUser.getBuyno());
		
		
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
			ReviewFile reviewFile = new ReviewFile();
			reviewFile.setReviewno(reviewno);
			reviewFile.setOriginname(fileList.get(i).getOriginalFilename());
			reviewFile.setStoredname(storedName);
			reviewFile.setFilesize(fileList.get(i).getSize());
			
			if(i==0) {//사진마다 저장하면 안되니까 1번만저장
				review.setImage1(storedName);
				
				//리뷰 DB저장
				shopDao.writeReview(review);
				
			}
			
			//리뷰파일 DB저장
			shopDao.insertShopFile(reviewFile);
			
		}
		//리뷰쓰면 orderUser테이블 complete 바꾸기
		shopDao.updateC(review);
		
	}
	@Override
	public int cntReview(Review review) {
		
		//리뷰가 있는지 확인
		int cnt = shopDao.cntReviewno(review);
		
		return cnt;
	}
	
	@Override
	public Map<Integer, List> ReviewfileList(Review review) {
		
		//objectno로 revieno불러오기
		List<Integer> reviewno = shopDao.selectReviewNo(review);
		
		//이거 개빡침
		//리뷰 하나당 사진이 여러개라서 요래 복잡함
		//어케했노
		List<ReviewFile> list = new ArrayList<ReviewFile>();
		Map<Integer,List> listMap = new HashMap<Integer, List>();
		
		for(int i=0; i<reviewno.size();i++ ) {
			
			//reviewno마다 파일 가져오기
			list = shopDao.fileList(reviewno.get(i));
			
			listMap.put(i, list);
			
		}
		
		return listMap;
		
	}

	@Override
	public void insertReport(ReportObject reportObject) {
		
		//상품신고
		shopDao.insertReport(reportObject);
		
	}

	
}
