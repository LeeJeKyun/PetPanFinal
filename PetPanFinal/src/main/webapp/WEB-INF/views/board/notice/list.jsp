
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="../../layout/header.jsp" />

<style type="text/css" >
#boardselect{
	bottom: 6px;
	left: 30px;
	font-size:15px;
	width: 100px;
	padding-left: 4px;
}
</style>

<br>

<div style="width: 1500px; margin:0 auto;">

<div style="text-align: left; width: 1100px; margin: 0 auto;">
	<span style="font-size: 2em; font-weight: bold;">공지사항</span>
</div>

<br>

<table style="margin: 0 auto; width: 1000px; text-align: center; border-collapse: collapse;">
	<tr style="height: 50px">
		<th>번호</th>
		<th>제목</th>
		<th>글쓴이</th>
		<th>등록일</th>
	</tr>
<c:forEach items="${list }" var="notice">
<tr style="height: 40px; border-top: 2px solid #f5cbcb">
	<td>${notice.NOTICENO }</td>
	<td><a href="../notice/view?noticeno=${notice.NOTICENO }">${notice.NOTICETITLE }</a></td>
	<td>${notice.USERNAME }</td>
	<td><fmt:formatDate value="${notice.NOTICEWRITEDATE }" pattern="yyyy-MM-dd" /></td>
</tr>
</c:forEach>
</table>

<br>
<br>

<c:import url="./paging.jsp" />

<!-- 컨텐츠영역 끝 -->
</div>

<c:import url="../../layout/footer.jsp" />







