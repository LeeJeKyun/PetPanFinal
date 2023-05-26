package member.service.face;

import java.util.HashMap;

public interface KakaoService {


	/**
	 * ���� 
	 * 
	 * @param code
	 * @return 
	 */
	public String getAccessToken(String code);

	/**
	 * �
	 * 
	 * @param attribute
	 */
	public void kakaoLogout(String access_Token);

	/**
	 * access 
	 * 
	 * @param access_Token
	 * @return
	 */
	public HashMap<String, Object> getUserInfo(String access_Token);


}
