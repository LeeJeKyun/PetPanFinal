package member.service.impl;

import java.io.BufferedReader;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import member.dao.face.MemberDao;
import member.dto.Member;
import member.dto.Pet;
import member.dto.PetFile;
import member.service.face.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

	
	@Autowired private  MemberDao memberDao;

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
	public Member userDetail(Member userNo) {

		
		return memberDao.selectDetailMember(userNo);
	}
	
	
	
	@Override
	public void updateMember(Member member) {

		memberDao.updateMember(member);
	}
	
	
	@Override
	public void petInfo(Pet pet, PetFile petFile) {

		
		
		
	}
	
	
	


	
	@Override
	public Member selectSuser(String sosId) {
		
		return memberDao.selectSuser(sosId);
		
	}
	
	

	


	
	
	
	
	
	
	
}
