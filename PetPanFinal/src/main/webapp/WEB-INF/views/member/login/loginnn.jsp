
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/header.jsp" />

<style type="text/css">



#login {
	text-align: center;

}

</style>

<div id="login">

<form action="./login" method="post">

	<label>아이디 : <input type="text" name="id"></label><br>
	<label>비밀번호 : <input type="password" name="pw"></label><br>

	<button>로그인</button>
	<button>취소</button>  


</form>

<c:import url="../member/naverLogin.jsp" />

</div>

<c:import url="../layout/footer.jsp" />







