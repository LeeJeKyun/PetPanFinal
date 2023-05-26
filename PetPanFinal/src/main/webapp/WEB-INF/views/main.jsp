
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="./layout/header.jsp" />

<style type="text/css">
.main {
	float: right;
}


</style>




<div class="main">

<!-- userId eq null || email eq null -->

<c:choose>
<c:when test="${login eq null  }">
<%-- <h2>세션 상태: ${not empty login}</h2> --%>
<button onclick="location.href='/member/login/join'">회원가입</button>
<button onclick="location.href='/member/login/login'">로그인</button>
</c:when>

<c:when test="${login eq true  }">
<%-- <h2>세션 상태: ${login eq true }</h2> --%>
<h2>안녕하세요, ${userName}님</h2>
<button onclick="location.href='/member/login/mypage'">마이페이지</button>
<button onclick="location.href='/member/login/logout'">로그아웃</button>
</c:when>

</c:choose>












</div>

<c:import url="./layout/footer.jsp" />







