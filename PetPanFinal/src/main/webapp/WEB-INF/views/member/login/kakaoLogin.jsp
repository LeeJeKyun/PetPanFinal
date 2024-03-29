<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <c:if test="${userId eq null || email eq null }">
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=fecd7b77d17e2393c47ca5ce09dd6b34&redirect_uri=http://localhost:8888/member/kakaoLogin&response_type=code">
			<img src="/resources/member/img/kakao.png">
        </a>
    </c:if>
    
    <c:if test="${userId ne null || email ne null}">
        <h1>로그인 성공입니다</h1>
       <button onclick="location.href='/member/login/mypage'">마이페이지</button>
		<button onclick="location.href='/member/login/logout'">로그아웃</button>
        
        <input type="button" value="로그아웃" onclick="location.href='./logout'">
    </c:if>
</body>
</html>




