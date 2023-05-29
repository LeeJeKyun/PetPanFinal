<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class = " f comment">
	<c:forEach items="${commentList }" var="comment" >
	<c:if test="${comment.DEPTH eq 1 }">
		<input type="hidden" id="Cdepth${comment.COMMENTNO }" value="${comment.DEPTH }">
		
		<table style="border: 1px solid #fff;">
			<tr>
				<td>글쓴이 ${comment.USERNAME}</td>
				<td>(<fmt:formatDate value="${comment.WRITE_DATE }" pattern="yyyy.MM.dd"/>)</td>
				<td><button>신고하기</button></td>
			</tr>
			<tr>
				<td colspan="2" ><span style="cursor: pointer;" onclick="showComCom(${comment.COMMENTNO})">${comment.CONTENT }</span></td>
				<td><button>삭제</button></td>
			</tr>
			<tr id="ComCom${comment.COMMENTNO }" class="displayNone">
				<td colspan="2">
				 └> <input type="text" name="content" id="Ccontent${comment.COMMENTNO }" onkeypress="enterkeyPress(event)"> 
					<button id="ComComSubmit" onclick="comcomInput(${userno}, ${comment.BOARDNO }, ${comment.COMMENTNO })" >입력</button>
				 </td>	
			</tr>
		</table>
	</c:if>
	
	<c:if test="${comment.DEPTH eq 2 }">
		<input type="hidden" id="Cdepth${comment.COMMENTNO }" value="${comment.DEPTH }">
		
		<table style="padding-left: 40px; border: 1px solid #ccc;">
			<tr>
				<td>글쓴이 ${comment.USERNAME}</td>
				<td>(<fmt:formatDate value="${comment.WRITE_DATE }" pattern="yyyy.MM.dd"/>)</td>
				<td><button>신고하기</button></td>
			</tr>
			<tr>
				<td colspan="2" ><span style="cursor: pointer;" onclick="showComCom(${comment.COMMENTNO})">${comment.CONTENT }</span></td>
				<td><button>삭제</button></td>
			</tr>
			<tr id="ComCom${comment.COMMENTNO }" class="displayNone">
<!-- 				<td colspan="2"> -->
<%-- 				 └> <input type="text" name="content" id="Ccontent${comment.COMMENTNO }">  --%>
<%-- 					<button id="ComComSubmit" onclick="comcomInput(${userno}, ${comment.BOARDNO }, ${comment.COMMENTNO })">입력</button> --%>
<!-- 				 </td>	 -->
			</tr>
		</table>
	</c:if>
	</c:forEach>
</div>