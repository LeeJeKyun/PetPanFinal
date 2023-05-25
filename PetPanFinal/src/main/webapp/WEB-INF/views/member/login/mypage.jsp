
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/header.jsp" />

<h1>마이페이지</h1>
<hr>
아이디 : ${detail.userId }
이메일 : ${detail.email }
휴대폰번호 : ${detail.phone } <br><br>

<a href="./main"><button>메인으로</button></a>

<c:import url="../layout/footer.jsp" />







