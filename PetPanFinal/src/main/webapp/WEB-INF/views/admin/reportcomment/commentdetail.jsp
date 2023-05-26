<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div class="commentinfo">
		  <h3>댓글 정보</h3>
		  <p>댓글 번호: ${comment.commentNo}</p>
		  <p>댓글 내용: ${comment.content}</p>
		  <p>작성 일자: <fmt:formatDate value="${comment.writeDate}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
		</div>

</body>
</html>