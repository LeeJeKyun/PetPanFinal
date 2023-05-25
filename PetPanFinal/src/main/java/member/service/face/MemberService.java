package member.service.face;



import java.util.HashMap;

import member.dto.Member;


public interface MemberService {

	/**
	 * 
	 * 
	 * @param member
	 * @return
	 */
	public boolean login(Member member);

	
	/**
	 * 회원정보 조회
	 * 
	 * @return
	 */
	public Member selectDetail(String userId);

	/**
	 * 회원가입
	 * 
	 * @param member
	 */
	public void insertJoin(Member member);

	
	/**
	 * 주소를 지정하면 위도 경도 뽑기
	 * 
	 * @param roadFullAddr
	 * @return
	 */
	public String getKakaoApiFromAddress(String roadFullAddr);

	/**
	 * 위도 경도 타입 변환
	 * 
	 * @param jsonString
	 * @return
	 */
	public HashMap<String, String> getXYMapfromJson(String jsonString);


	

	
	
	
	
	
	
	
	
}
