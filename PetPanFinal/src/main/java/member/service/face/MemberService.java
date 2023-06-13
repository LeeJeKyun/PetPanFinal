package member.service.face;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import board.dto.Board;
import board.dto.Comment;
import member.dto.Hospital;
import member.dto.Member;
import member.dto.Pet;
import member.dto.PetFile;
import shop.dto.Review;


public interface MemberService {

	/**
	 * 로그인 성공 여부
	 * 
	 * @param member
	 * @return
	 */
	public boolean login(Member member);

	/**
	 * 회원가입 새로운 회원 정보를 등록한다.
	 * 
	 * @param member
	 */
	public void insertJoin(Member member);

	/**
	 * 주소를 지정하면 위도 경도를 반환해 준다.
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
	 * @param petFileList - 펫 번호를 가져올 List<PetFile> 객체
	 * @return - 펫 번호가 입력되어 있는 List<PetFile> 객체 
	 */
	public List<PetFile> petFile(List<PetFile> petFileList);

	/**
	 * 병원 관계자인지 일반 회원인지!!
	 * 
	 * @param member
	 */
	public boolean selectHospital(Member member);

	/**
	 * 병원 정보 입력하기
	 * 이미 있으면 있는 hospital 정보 객체 반환
	 * @param hospital
	 */
	public int insertHospital(Hospital hospital);

	/**
	 * 
	 * 
	 * @param email
	 * @return
	 */
	public String joinEmail(String email);

	/**
	 * 마이페이지 내가쓴 리뷰
	 * @return 
	 */
	public List<Review> myreview(int userno);

	/**
	 * 아이디 찾기
	 * 
	 * @param member
	 * @return
	 */
	public Member searchId(Member member);

	/**
	 * 비밀번호 찾기
	 * 
	 * @param member
	 * @return
	 */
	public Member searchPw(Member member);

	/**
	 * 비밀번호 찾고 변경
	 * 
	 * @param member
	 */
	public void updatePw(Member member);

	/**
	 * 아이디 중복검사
	 * 
	 * @param member
	 * @return
	 */
	public int idDu(Member member);

	/**
	 * 닉네임 중복검사
	 * 
	 * @param member
	 * @return
	 */
	public int nickDu(Member member);

	/**
	 * 내가 쓴 게시글 조회
	 * 
	 * @param attribute
	 * @return
	 */
	public List<Board> myContent(int userno);

	/**
	 * 내가 쓴 댓글 조회
	 * 
	 * @param attribute
	 * @return
	 */
	public List<Map<String, Object>> myComment(int attribute);

	/**
	 * 비밀번호 찾기 할 때 이메일 전송
	 * 
	 * @param email
	 * @return
	 */
	public String pwEmail(String email);

	/**
	 * 
	 * 
	 * @param petFile
	 * @return
	 */
	public List<PetFile> selectPetprofile(PetFile petFile);

	/**
	 * 펫 정보 가져오기
	 * 
	 * @param pet
	 * @return 
	 */
	public Pet selectPetByPetNo(Pet pet);

	/**
	 * 펫 사진 가져오기
	 * 
	 * @param pet
	 * @return
	 */
	public PetFile selectPetFileByPet(Pet pet);

	/**
	 * 펫 사진 수정하기
	 * 
	 * @param pet
	 * @param petFile
	 * @return
	 */
	public int petUpdate(Pet pet, MultipartFile petFile);

	/**
	 * userid로 Member의 정보를 불러오는(userno) 메소드
	 * 
	 * @param member
	 * @return
	 */
	public Member getMemberInfoByUserid(Member member);

	/**
	 * 펫 사진 삭제
	 * 
	 * @param petFile
	 */
	public void deletePetPhoto(PetFile petFile);

	/**
	 * 펫 정보 삭제
	 * 
	 * @param pet
	 */
	public void deletePetInfo(Pet pet);
	
	/**
	 * userno로 불러오기
	 * @param userno
	 * @return 
	 */
	
	public int selectByUserno(int userno);





	








	

	
	
	
	
	
	
	
	
}
