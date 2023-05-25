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

	

	
	
}
