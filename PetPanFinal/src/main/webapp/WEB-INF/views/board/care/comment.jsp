<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class = " f comment">
	<c:forEach items="${commentList }" var="comment" >
		<input type="hidden" id="Cdepth${comment.COMMENTNO }" value="${comment.DEPTH }">
		
		<table style="border: 1px solid #fff; margin-left: ${comment.DEPTH * 17}px">
			<tr>
				<td>글쓴이 ${comment.USERID}</td>
				<td>(<fmt:formatDate value="${comment.WRITE_DATE }" pattern="yyyy.MM.dd"/>)</td>
				<td><span style="color: #555; cursor: pointer;" onclick="reportComment(${comment.COMMENTNO})">신고</span></td>
			</tr>
			<tr>
				<td colspan="2"><span style="cursor: pointer;" onclick="showComCom(${comment.COMMENTNO})">${comment.CONTENT }</span></td>
				<td><span style="color: #555; cursor: pointer;">삭제</span></td>
			</tr>
			<tr id="ComCom${comment.COMMENTNO }" class="displayNone">
				<td colspan="2">
				 └> <input type="text" name="content" id="Ccontent${comment.COMMENTNO }" onkeypress="enterkeyPress(event, ${comment.COMMENTNO})"> 
					<button id="ComComSubmit${comment.COMMENTNO }" onclick="comcomInput(${userno}, ${comment.BOARDNO }, ${comment.COMMENTNO }, ${comment.ORGANIZATION })" >입력</button>
				 </td>
			</tr>
		</table>
	</c:forEach>
</div>