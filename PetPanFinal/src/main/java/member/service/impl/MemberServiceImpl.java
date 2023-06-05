package member.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import member.dao.face.MemberDao;
import member.dto.Hospital;
import member.dto.Member;
import member.dto.Pet;
import member.dto.PetFile;
import member.service.face.MemberService;


@Service
public class MemberServiceImpl implements MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Autowired private  MemberDao memberDao;
	@Autowired ServletContext context;
	@Autowired private JavaMailSenderImpl mailSender;
	
	private int authNumber; 
	
	
	@Override
	public boolean login(Member member) {
		logger.info("login() 🤣🤣 ");
		
		
		if (memberDao.loginProc(member) > 0) {
			
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public Member selectlogin(Member member) {
		logger.info("member : {}", member);
		Member member2 = memberDao.selectlogin(member);
		
		return member2;
	}

	@Override
	public boolean selcetBlack(Member member) {
		

		if( memberDao.selcetBlack(member) > 0) {
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean selectKakao(Member member) {

		if( memberDao.selectKakao(member) > 0) {
			
			return true;
		}
		
		return false;
	}
	
	
	@Override
	public void insertJoin(Member member) {
		
		memberDao.insertJoin(member);
	}

	
	public void makeRandomNumber() {
		// 난수의 범위 111111 ~ 999999 (6자리 난수)
		Random r = new Random();
		int checkNum = r.nextInt(888888) + 111111;
		System.out.println("인증번호 : " + checkNum);
		authNumber = checkNum;
	}
	
	
	//이메일 보낼 양식! 
	@Override
	public String joinEmail(String email) {
		makeRandomNumber();
		String setFrom = "dabin9872@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력 
		String toMail = email;
		String title = "회원 가입 인증 이메일 입니다."; // 이메일 제목 
		String content = 
				"홈페이지를 방문해주셔서 감사합니다." + 	//html 형식으로 작성 ! 
                "<br><br>" + 
			    "인증 번호는 " + authNumber + "입니다." + 
			    "<br>" + 
			    "해당 인증번호를 인증번호 확인란에 기입하여 주세요."; //이메일 내용 삽입
		mailSend(setFrom, toMail, title, content);
		return Integer.toString(authNumber);
	}
	
	//이메일 전송 메소드
	public void mailSend(String setFrom, String toMail, String title, String content) { 
		
		MimeMessage message = mailSender.createMimeMessage();
//		// true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			// true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
			helper.setText(content,true);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public String getKakaoApiFromAddress(String jibunAddress) {
	    String apiKey = "9ae14f3088db483522887b5408416524";
	    String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json";
	    String jsonString = null;

	    try {
	    	jibunAddress = URLEncoder.encode(jibunAddress, "UTF-8");
	        
	        
	        String addr = apiUrl + "?query=" + jibunAddress;

	        URL url = new URL(addr);
	        URLConnection conn = url.openConnection();
	        conn.setRequestProperty("Authorization", "KakaoAK " + apiKey);

	        BufferedReader rd = null;
	        rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        StringBuffer docJson = new StringBuffer();

	        String line;

	        while ((line=rd.readLine()) != null) {
	            docJson.append(line);
	        }

	        jsonString = docJson.toString();
	        rd.close();

	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (MalformedURLException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    logger.info("jibunAddress{}", jibunAddress);
	    
	    return jsonString;
	    
	    
	    
	}

	@Override
	public HashMap<String, String> getXYMapfromJson(String jsonString) {
	    ObjectMapper mapper = new ObjectMapper();
	    HashMap<String, String> XYMap = new HashMap<String, String>();

	    try {
	        TypeReference<Map<String, Object>> typeRef 
	            = new TypeReference<Map<String, Object>>(){};
	        Map<String, Object> jsonMap = mapper.readValue(jsonString, typeRef);

	        @SuppressWarnings("unchecked")
	        List<Map<String, String>> docList 
	            =  (List<Map<String, String>>) jsonMap.get("documents");	

	        Map<String, String> adList = (Map<String, String>) docList.get(0);
	       
	        XYMap.put("x",adList.get("x"));
	        XYMap.put("y", adList.get("y"));

	        logger.info("adList {} : ", adList);
	        
	    } catch (JsonParseException e) {
	        e.printStackTrace();
	    } catch (JsonMappingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    logger.info("XYMap{}", XYMap);

	    
	    return XYMap;
	}
	
	
	@Override
	public void insertkakaoJoin(Member member, String sosId) {
		
		member.setSuserno(sosId);
		logger.info("impl : {}", sosId);
		logger.info("impl : {}", member);
		
		
		memberDao.kakaoinsert(member);
	}


	@Override
	public Member selectSuser(String sosId) {
		
		return memberDao.selectSuser(sosId);
		
	}
	
	@Override
	public Member userDetail(Member userNo) {

		
		return memberDao.selectDetailMember(userNo);
	}
	
	
	
	@Override
	public void updateMember(Member member) {

		memberDao.updateMember(member);
	}
	
	
	
	@Override
	public int pet(Pet pet, MultipartFile petFile) {

//		int petNo = memberDao.selectPetNo();
//		pet.setPetNo(petNo);
		
		// 파일이 저장될 경로
		String storedPath = context.getRealPath("petfile");
		File storedFolder = new File(storedPath);
		if( !storedFolder.exists() ) {
			storedFolder.mkdir();
		}
		
		// 파일이 저장될 이름
		String originName = petFile.getOriginalFilename();
		
		
		// 저장될 파일 정보 객체
		File dest = null;
		
		String storedName = null;
		
		//저장된 파일이름 중복을 방지하는 로직임
		do {
		
			storedName = UUID.randomUUID().toString().split("-")[0];
			
			dest = new File(storedFolder, storedName);
			
		}while(dest.exists());
		
		
		try {
			petFile.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		pet.setPetNo(memberDao.selectPetNo()); 
		
		
		PetFile file = new PetFile();
		
		file.setPetNo( pet.getPetNo() );
		file.setOriginName(originName);
		file.setStoredName(storedName);
		file.setFileSize(petFile.getSize());
	
		logger.info("pet: {}", pet);
		memberDao.insertPet(pet);
		
		memberDao.insertPetfile(file);
		
		return 0;
	}


	@Override
	public List<Pet> petInfo(Member userNo) {
		
		return memberDao.selectPetInfo(userNo);
	}
	
	@Override
	public List<PetFile> petFile(PetFile petNo) {
		
		return memberDao.selectPetFile(petNo);
	}

	@Override
	public boolean selectHospital(Member member) {

		if( memberDao.selectHospital(member) == 2) {
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public int insertHospital(Hospital hospital) {
		int hospitalNo = 0;
		// 이미 병원 정보가 DB에 있는지 확인
		Integer hn = memberDao.selectIsHospitalNo(hospital.getUserNo());
		if( null == hn || hn == 0) { //없을 때
			memberDao.insertHospital(hospital);
		}else { //있을 때
			hospitalNo = memberDao.selectHospitalNo(hospital.getUserNo());
		}
		return hospitalNo;
	}
	
	
	
	
}
