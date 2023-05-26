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

 
<form action="./search" method="get">
<div >
	<table id = "search">
		<tr>
			<td>검색</td><td><input type="text" name = "keyword"></td>
			<td></td>
		</tr>
	</table>
</div>
</form>

<div class="container2">
	<div>
		<table class = "table table-striped" style="width:1300px">
				<tr>
		<th>유저번호</th>
		<th>유저이름</th>
		<th>유저아이디</th>
		<th>유저비밀번호</th>
		<th>유저이메일</th>
		<th>유저주소</th>
		<th>유저핸드폰번호</th>
		<th>유저 포지션</th>
		
		
		

		</tr>
		<c:forEach var="list" items="${list}">

		<tr>
		<td>${list.userNo }</td>
		<td>${list.userName }</td>
		<td>${list.userId}</td>
		<td>${list.userPw}</td>
		<td>${list.email}</td>
		<td>${list.address}</td>
		<td>${list.phone}</td>
		<td>${list.positionNo}</td>
		</tr>
		</c:forEach>
		</table>
		
	</div>	
	<div class = "" style="margin-left: 170px;">	
	<c:import url="../../layout/paging.jsp"/>
	</div>
</div>
</body>
</html>