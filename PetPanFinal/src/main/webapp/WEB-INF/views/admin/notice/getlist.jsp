<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<table class = "table table-hover" style = "width : 800px">
	<tr>
		<th>공지 번호</th>
		<th>제목</th>
		<th>작성일</th>
		<th>작성자</th>
	</tr>
			<c:forEach var="list" items="${list}">
	<tr>
		<td>${list.noticeno}</td>
		<td><a href="./view/?noticeNo=${list.noticeno}">${list.noticetitle}</a></td>
		<td><fmt:formatDate value="${list.noticewritedate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
		<td>${list.userno}</td>
	</tr>
		</c:forEach>

</table>





