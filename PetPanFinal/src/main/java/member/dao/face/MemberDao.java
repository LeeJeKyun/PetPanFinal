package member.dao.face;

import member.dto.Member;

public interface MemberDao {

	/**
	 * 로그인
	 * 
	 * @param member
	 */
	public int loginProc(Member member);

	/**
	 * 회원 정보 조회
	 * 
	 * @param id
	 * @return
	 */
	public Member selectDetailMember(String userId);

	/**
	 * 회원가입
	 * 
	 * @param member
	 */
	public void insertJoin(Member member);

	/**
	 * 
	 * 
	 * @param member
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
	 * 
	 * 
	 * @param sns
	 */
	public void kakaoinsert(Member member);

	/**
	 * 카카오 회원 조회하기
	 * 
	 * @param member
	 */
	public int selectKakao(Member member);

	/**
//	 * 카카오 회원 정보 수정(회원가입)
//	 * 
//	 * @param member
//	 */
//	public void upadatekakaoJoin(Member member);

//	/**
//	 * 
//	 * @param code
//	 * @return
//	 */
//	public int loginProc(String code);

	

	
	
}
