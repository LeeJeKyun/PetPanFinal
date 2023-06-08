package member.service.face;

import member.dto.Member;

public interface UnregistService {

	public int checkmember(Member member, int userno, String pw);

	public void unregistAll(int userno);

}
