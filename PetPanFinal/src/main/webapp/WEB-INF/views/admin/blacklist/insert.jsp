<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--  <%@ include file="../../layout/adminHeader.jsp" %> --%>

<c:import url="../../layout/adminHeader.jsp"/>

 

<div class="container2">
	<div>
	<h1>블랙유저 등록</h1>
	<hr>
		<form action="./insert" method="post">
		<table class = "table table-striped" style="width:800px">
	
			<tr>
				<th>유저 번호</th>
				<td><input type="text" name = "userno" value="${userno}"></td>
			</tr>
			
			<tr>
				<th>정지 사유</th>
				<td><input type="text" name = "reason"></td>
			
			</tr>
		</table>
		
		
			<button type="submit" class = "btn btn-primary">삽입하기</button>
		
		
	</form>


	</div>	
</div>
</body>
</html>