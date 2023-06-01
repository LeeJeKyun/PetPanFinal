package member.service.face;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import member.dto.Member;
import member.dto.Pet;
import member.dto.PetFile;


public interface MemberService {

	/**
	 * 
	 * 
	 * @param member
	 * @return
	 */
	public boolean login(Member member);

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

	/**
	 * 
	 * 
	 * @param member
	 * @return
	 */
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

	/**
	 * 소셜 회원 세션에 넣어주기 위해 조회
	 * 
	 * @param sosId
	 */
	public Member selectSuser(String sosId);

	/**
	 * userNo로 조회하여 회원정보 상세보기
	 * 
	 * @param userNo
	 * @return
	 */
	public Member userDetail(Member userNo);

	/**
	 * 회원 정보 수정
	 * 
	 * @param member
	 */
	public void updateMember(Member member);

	/**
	 * 펫No 가져오기
	 * 
	 * @param pet
	 * @param petFile 
	 * @return
	 */
	public int pet(Pet pet, MultipartFile petFile);
	
	/**
	 * 펫 정보 가져오기
	 * 
	 * @param pet
	 * @return
	 */
	public List<Pet> petInfo(Member userNo);
	
	/**
	 * 펫 사진 정보 가져오기
	 * 
	 * @param petFile
	 * @return
	 */
	public List<PetFile> petFile(PetFile petNo);

	/**
	 * 병원 관계자인지,,
	 * 
	 * @param member
	 */
	public boolean selectHospital(Member member);







	








	

	
	
	
	
	
	
	
	
}
