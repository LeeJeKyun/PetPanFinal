<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<table class = "table table-hover">
	<tr>
		<th>공지 번호</th>
		<th>제목</th>
		<th>작성일</th>
		<th>작성자</th>
	</tr>
			<c:forEach var="list" items="${list}">
	<tr>
		<td>${list.noticeNo}</td>
		<td><a href="./view/?noticeNo=${noticeNo }">${list.noticeTitle}</a></td>
		<td><fmt:formatDate value="${list.noticeWriteDate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
		<td>${list.userNo}</td>
	</tr>
		</c:forEach>

</table>





