package member.service.face;

import java.util.HashMap;

public interface KakaoService {


	/**
	 * ���� �ڵ� 
	 * 
	 * @param code
	 * @return 
	 */
	public String getAccessToken(String code);

	/**
	 * �α׾ƿ�
	 * 
	 * @param attribute
	 */
	public void kakaoLogout(String access_Token);

	/**
	 * access Token���� �г��� ,�̸��� ��������
	 * 
	 * @param access_Token
	 * @return
	 */
	public HashMap<String, Object> getUserInfo(String access_Token);


}
