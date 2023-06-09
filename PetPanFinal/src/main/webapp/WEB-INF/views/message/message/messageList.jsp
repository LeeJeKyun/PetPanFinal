<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
    <form action="./messageprocess" method="post" >
	<table style="padding: 15px 15px 0 15px; width: 100%">
		<tr>
			<td style="text-align: center;"><input type="checkbox" onclick="toggleAllCheckbox()"></td>
			<th style="width: 20%">보낸사람</th>
			<th style="width: 40%; text-align: left;" colspan="2">쪽지내용</th>
			<th style="width: 40%">보낸일시</th>
		</tr>
		<c:forEach items="${list }" var="message">
		<tr id="${message.MESSAGENO }"
				<c:if test='${message.DOREAD ne "N" }'>
				style="color: #bbb;"
				</c:if>
				>
			<td style="text-align: center;"><input type="checkbox" name="messageno" value="${message.MESSAGENO }" ></td>
			<td style="text-align: center;">${message.USERNICK }</td>
			<td colspan="2"><a style="cursor: pointer;" onclick="messageView(${message.MESSAGENO})">${fn:substring(message.CONTENT,0,15) }...</a></td>
			<td style="text-align: center;"><small style="color: #aaa;"><fmt:formatDate value="${message.SEND_DATE }" pattern="yyyy-MM-dd HH:mm:ss" /></small> </td>
		</tr>
		</c:forEach>
		<tr>
			<td style="width: 10%; text-align: center;"><button type="button" onclick="changeProcess('d')">삭제</button></td>
			<td style="width: 10%; text-align: center;"><button type="button" onclick="changeProcess('r')">읽음</button></td>
			<td style="width: 20%;"><button type="button" onclick="changeProcess('s')">저장</button></td>
			<td style="width: 20%;"></td>
			<td style="width: 20%;"></td>
		</tr>
	</table>
		<input type="hidden" name="process" value="" >
	</form>