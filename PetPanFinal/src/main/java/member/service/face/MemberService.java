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

//	/**
//	 * 
//	 * 
//	 * @param member
//	 * @return
//	 */
//	public boolean kakao(String code );
	
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

	
	public Member selectlogin(Member member);


	/**
	 * 블랙리스트 회원 조회하기
	 * 
	 * @param member
	 * @return
	 */
	public boolean selcetBlack(Member member);

	/**
	 * 카카오 토큰 넣기
	 * 
	 * @param sns
	 */
	public void insertkakaoJoin(Member member, String sosId);

	/**
	 * 유저테이블에 소셜 토큰 값 있는지 조회
	 * 
	 * @param member
	 * @return
	 */
	public boolean selectKakao(Member member);

//	/**
//	 * 카카오 회원 가입하기
//	 * 
//	 * @param member
//	 */
//	public void updatekakaoJoin(Member member);




	

	
	
	
	
	
	
	
	
}
