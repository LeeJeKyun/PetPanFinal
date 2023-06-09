<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--  <%@ include file="../../layout/adminHeader.jsp" %> --%>

<c:import url="../../layout/adminHeader.jsp"/>

 
 <form action="./search" method="get">
<div >
	<table style="margin-top : 100px; margin-left : 400px;">
		<tr>
			<td><button class ="btn btn-primary" >검색</button></td><td><input class = "form-control" type="text" name = "keyword" placeholder="유저이름검색" ></td>
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
		<td><a href = "./view?userno=${list.userNo}">${list.userName }</a></td>
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