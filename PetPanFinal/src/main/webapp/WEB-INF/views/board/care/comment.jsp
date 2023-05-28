<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class = " f comment">
	<c:forEach items="${commentList }" var="comment" >
		<table>
			<tr>
				<td>글쓴이 ${comment.USERNAME}</td>
				<td>(<fmt:formatDate value="${comment.WRITE_DATE }" pattern="yyyy.MM.dd"/>)</td>
				<td><button>신고하기</button></td>
			</tr>
			<tr>
				<td colspan="2">${comment.CONTENT }</td>
				<td><button>삭제</button></td>
			</tr>
		</table>
	</c:forEach>
</div>
<div id = "comment2" class = "small6 cursor">답글달기</div>