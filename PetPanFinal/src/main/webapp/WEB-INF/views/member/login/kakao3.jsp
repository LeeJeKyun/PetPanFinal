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
    <c:if test="${userId eq null}">
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=709641586592b61e7e148fb086efd03f&redirect_uri=http://localhost:8888/login/login&response_type=code">
			<img src="/resources/member/img/kakao.png" style="height:40px; width: 145px;">
        </a>
    </c:if>
    
    <c:if test="${userId ne null}">
        <h1>로그인 성공</h1>
        <input type="button" value="로그아웃" onclick="location.href='./logout'">
    </c:if>
</body>
</html>




