package member.dao.face;


import java.util.List;
import java.util.Map;

import board.dto.Board;
import board.dto.Comment;
import member.dto.Hospital;
import member.dto.Member;
import member.dto.Pet;
import member.dto.PetFile;
import shop.dto.Review;

public interface MemberDao {

	/**
	 * 로그인
	 * 
	 * @param member
	 */
	public int loginProc(Member member);

	/**
	 * 회원가입
	 * 
	 * @param member
	 */
	public void insertJoin(Member member);

	/**
	 * 로그인
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
	public int selcetBlack(Member member);

	/**
	 * 관계자인지 아닌지
	 * 
	 * @param member
	 * @return
	 */
	public int selectMgr(Member member);

	/**
	 * 
	 * 
	 * @param member
	 */
	public void kakaoinsert(Member member);

	/**
	 * 카카오 회원 조회하기
	 * 
	 * @param member
	 */
	public int selectKakao(Member member);

	/**
	 * userNo로 회원 상세조회
	 * 
	 * @param userNo
	 * @return
	 */
	public Member selectDetailMember(Member userNo);

	/**
	 * 회원 정보 수정
	 * 
	 * @param member
	 */
	public void updateMember(Member member);

	/**
	 * 소셜 유저 넘버 조회
	 * 
	 * @param sosId
	 * @return
	 */
	public Member selectSuser(String sosId);


	/**
	 * petNo 가져오기
	 * 
	 * @return
	 */
	public int selectPetNo();

	/**
	 * pet 저장
	 * 
	 * @param pet
	 */
	public void insertPet(Pet pet);

	/**
	 * 펫 사진 삽입
	 * 
	 * @param petFile
	 */
	public void insertPetfile(PetFile petFile);

	/**
	 * userNo로 펫 정보 조회
	 * 
	 * @param userNo
	 * @return
	 */
	public List<Pet> selectPetInfo(Member userNo);
	
	/**
	 * petNo로 사진 조회
	 * 
	 * @param petFileList 
	 * @return
	 */
	public PetFile selectPetFile(int petNo);

	/**
	 * 포지션넘버 조회해서 병원관계자인지 일반회원인지,,
	 * 
	 * @param member
	 * @return
	 */
	public int selectHospital(Member member);

	/**
	 * 병원정보 입력하기
	 * 
	 * @param hospital
	 */
	public void insertHospital(Hospital hospital);

	/**
	 * 
	 * 
	 * @param userno
	 * @return
	 */
	public List<Review> myreview(int userno);

	/**
	 * 아이디 찾기 (이름, 휴대폰번호 같을 경우)
	 * 
	 * @param member
	 * @return
	 */
	public Member searchId(Member member);

	/**
	 * 비밀번호 찾기(아이디, 이름,이메일 같을 경우)
	 * 
	 * @param member
	 * @return
	 */
	public Member searchPw(Member member);

	/**
	 * 비밀번호 변경
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
	 * @param userno
	 * @return
	 */
	public List<Board> myContent(int userno);

	/**
	 * 내가 쓴 댓글 조회
	 * 
	 * @param userno
	 * @return
	 */
	public List<Map<String, Object>> myComment(int userno);


	/**
	 * 병원 테이블에 같은 userNo이 있는지 확인
	 * @param hospitalNo 확인할 병원 번호
	 * @return 없으면 0
	 */
	public Integer selectIsHospitalNo(int hospitalNo);

	/**
	 * 
	 * @param userNo
	 * @return
	 */
	public Integer selectHospitalNo(int userNo);

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
	 * 펫 수정 전 기존 파일 삭제
	 * 
	 * @param pet
	 */
	public void deletePetfile(Pet pet);
	
	
	/**
	 * 펫 사진 수정
	 * 
	 * @param pet
	 */
	public void updatePet(Pet pet);


	/**
	 * UserId로 멤버의 정보를 가져오는 메소드
	 * 
	 * @param member
	 * @return
	 */
	public Member selectUserNoMemberByUserId(Member member);

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
	 * 
	 * @param userno
	 * @return userno 수
	 */
	
	public int selectByUserno(int userno);








	

	
	
}
