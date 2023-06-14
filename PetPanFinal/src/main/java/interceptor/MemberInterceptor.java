package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MemberInterceptor implements HandlerInterceptor{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("+ + + 인터셉터 시작 + + + ");
		
		// 세션 객체
		HttpSession session = request.getSession();
		
		if( session.getAttribute("login") == null ) {
			// 1. 비 로그인 상태
			logger.info(" >> 접속 불가 : 비로그인 상태");
			
			// 다른 URL로 리다이렉트
//			response.sendRedirect("../main");
			response.sendRedirect("/member/login/login");
			
			// 컨트롤러 접근 차단
			return false;
			
		}
			
		
		return true;  // 컨트롤러 접근 허용
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("+ + + 인터셉터 종료 + + +");
		
	}
	
}
