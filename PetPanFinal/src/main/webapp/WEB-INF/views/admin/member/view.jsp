<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--  <%@ include file="../../layout/adminHeader.jsp" %> --%>


<style type="text/css">
	#search{
		margin-left: 400px;
	}



</style>

<c:import url="../../layout/adminHeader.jsp"/>

 

<div class="container2">
	
		<table class = "table table-striped" style="width:1300px">
		<tr><th>유저번호</th> <td>${member.userNo }</td></tr>
		<tr> <th>유저이름</th><td>${member.userName }</td></tr>
		<tr> <th>유저닉네임</th><td>${member.userNick }</td></tr>
		<tr><th>유저아이디</th> <td>${member.userId}</td></tr>
		<tr><th>유저비밀번호</th><td>${member.userPw}</td></tr>
		<tr><th>유저이메일</th><td>${member.email}</td>	</tr>
		<tr><th>유저주소</th><td>${member.address}</td></tr>
		<tr><th>유저핸드폰번호</th><td>${member.phone}</td></tr>
		<tr><th>유저 포지션</th><td>${member.positionNo}</td></tr>
		</table>
		<a href = "./update?userno=${member.userNo}"><button class = "btn btn-primary">정보 수정</button></a>
		<a href = "./list"><button class = "btn btn-primary">멤버 리스트</button></a>
		
</div>
</body>
</html>