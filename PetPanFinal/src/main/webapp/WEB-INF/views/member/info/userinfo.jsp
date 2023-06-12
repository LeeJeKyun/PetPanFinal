<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보</title>
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>


<body>

<div style="height: 50px; text-align: center;">
	<h2>${member.userNick }</h2>
</div>

<div style="display: grid; grid-template-columns : 1fr 1fr; grid-gap: 2vw;">

	<div style="text-align: center;">
		<h3>작성한 게시글</h3>
		<table style="width:100%">
			<c:forEach items="${contentList }"  var="content">
				<tr style="text-align: center;">
					<td>
						<c:if test="${content.boardTypeNo eq 1 }">
						<span style="color: #FF5050; cursor: pointer;" onclick="window.open('<%=request.getContextPath() %>/board/care/view?boardNo=${content.boardNo}')"
						>${fn:substring(content.boardTitle,0,8) }...
						</span>
						</c:if>
						<c:if test="${content.boardTypeNo ne 1 }">
						<span style="color: #FF5050; cursor: pointer;" onclick="window.open('<%=request.getContextPath() %>/board/board/detail?boardNo=${content.boardNo}')"
						>${fn:substring(content.boardTitle,0,8) }...
						</span>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div style="text-align: center;">
		<h3>작성한 댓글</h3>
		<table style="width:100%">
			<c:forEach items="${commentList }"  var="comment">
				<tr style="text-align: center;">
					<td>
						<span  style="color: #FF5050; cursor: pointer;" onclick="window.open('<%=request.getContextPath() %>/board/board/detail?boardNo=${comment.BOARDNO }')">
							${fn:substring(comment.CONTENT,0,8) }...
						</span>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</div>

</body>
</html>