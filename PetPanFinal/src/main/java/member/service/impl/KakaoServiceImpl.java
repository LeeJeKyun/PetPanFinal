package member.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import member.controller.MemberController;
import member.dao.face.KakaoDao;
import member.service.face.KakaoService;

@Service
public class KakaoServiceImpl implements KakaoService{
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired KakaoDao kakaoDao;
	
	@Override
	public String getAccessToken(String code) {

	        String access_Token = "";
	        String refresh_Token = "";
	        String reqURL = "https://kauth.kakao.com/oauth/token";
	        
	        try {
	            URL url = new URL(reqURL);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            
//	          POST 요청을 위해 기본값이 false인 setDoOutput을 true로	            
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);

	            
	            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	            StringBuilder sb = new StringBuilder();
	            sb.append("grant_type=authorization_code");
	            sb.append("&client_id=fecd7b77d17e2393c47ca5ce09dd6b34");
	            sb.append("&redirect_uri=http://localhost:8888/member/kakaoLogin");
	            sb.append("&code=" + code);
	            bw.write(sb.toString());
	            bw.flush();
	            
	            //    결과 코드가 200이라면 성공
	            int responseCode = conn.getResponseCode();
	            logger.info("responseCode : " + responseCode);
	 
//	          	요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = "";
	            String result = "";
	            
	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	            logger.info("response body : " + result);
	            
//	          	Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
//	            JsonParser parser = new JsonParser();
//	            JsonElement element = parser.parse(result);
	            JsonElement element = JsonParser.parseString(result);
	            
	            access_Token = element.getAsJsonObject().get("access_token").getAsString();
	            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
	            
	            logger.info("access_token : " + access_Token);
	            logger.info("refresh_token : " + refresh_Token);
	            
	            br.close();
	            bw.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	        
	        return access_Token;
	    }
	

	   
	   public HashMap<String, Object> getUserInfo (String access_Token) {
		    
//		    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		   HashMap<String, Object> userInfo = new HashMap<>();
		    String reqURL = "https://kapi.kakao.com/v2/user/me";
		    try {
		        URL url = new URL(reqURL);
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("POST");
		        
//		      	요청에 필요한 Header에 포함될 내용
		        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
		        
		        int responseCode = conn.getResponseCode();
		        logger.info("responseCode : " + responseCode);
		        
		        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        
		        String line = "";
		        String result = "";
		        
		        while ((line = br.readLine()) != null) {
		            result += line;
		        }
		        logger.info("response body : " + result);
		        
//	            JsonElement element = JsonParser.parseString(result);
//		        
//		        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
//			    JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
//		        JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
//		        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
//		        String email = kakao_account.getAsJsonObject().get("email").getAsString();
		        
		        Gson gson = new Gson();
	            
		        JsonElement je = gson.fromJson(result, JsonElement.class);
		        logger.info("je :{}", je);
		        
		        String email = null;
		        		
		        try {
				
		        	email =	je.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();

		        } catch (NullPointerException e) {

					email =  "none";
				}
		        	
		        
		        

		        if(email == null) {
		        	
		        	email = "none";
		        
		        }
		        	
		        
		        String id = je.getAsJsonObject().get("id").getAsString();
		        

//		        userInfo.put("nickname", nickname);
		        userInfo.put("email", email);
		        userInfo.put("id", id);
		        
			      
		        
		        
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    
		    return userInfo;
	   }
	   
	   

	   
	   @Override
	public void kakaoLogout(String access_Token) {
		   
		   String reqURL = "https://kapi.kakao.com/v1/user/logout";
		    try {
		        URL url = new URL(reqURL);
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("POST");
		        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
		        
		        int responseCode = conn.getResponseCode();
		        logger.info("responseCode : " + responseCode);
		        
		        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        
		        String result = "";
		        String line = "";
		        
		        while ((line = br.readLine()) != null) {
		            result += line;
		        }
		        logger.info(result);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }   
	}
	   
	   
}
