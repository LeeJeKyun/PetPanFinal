<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
	<table style="padding: 15px 15px 0 15px; width: 100%">
		<tr>
			<th style="width: 20%">보낸사람</th>
			<th style="width: 40%; text-align: left;">쪽지내용</th>
			<th style="width: 40%">보낸일시</th>
		</tr>
		<c:forEach items="${list }" var="message">
		<c:if test='${message.DOREAD eq "N" }'>
		<tr id="${message.MESSAGENO }">
			<td style="text-align: center;">${message.USERID }</td>
			<td><a style="cursor: pointer;" onclick="messageView(${message.MESSAGENO})">${fn:substring(message.CONTENT,0,15) }...</a></td>
			<td style="text-align: center;"><small style="color: #aaa;"><fmt:formatDate value="${message.SEND_DATE }" pattern="yyyy-MM-dd HH:mm:ss" /></small> </td>
		</tr>
		</c:if>
		<c:if test='${message.DOREAD ne "N" }'>
		<tr id="${message.MESSAGENO }" style="color: #bbb;">
			<td style="text-align: center;">${message.USERID }</td>
			<td><a style="cursor: pointer;" onclick="messageView(${message.MESSAGENO})">${fn:substring(message.CONTENT,0,15) }...</a></td>
			<td style="text-align: center;"><small style="color: #aaa;"><fmt:formatDate value="${message.SEND_DATE }" pattern="yyyy-MM-dd HH:mm:ss" /></small> </td>
		</tr>
		</c:if>
		</c:forEach>
	</table>