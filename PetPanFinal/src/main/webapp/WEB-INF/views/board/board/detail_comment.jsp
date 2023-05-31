<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<script type="text/javascript">
$(function(){
	
})
</script>
<!-- ajax html -->
	 <c:forEach var = "i" items = "${list}">
	 <c:if test="${i.DEPTH == 1}">
	 <div class = "comment-area">
	 		<div class = " f comment">
	 			<div class = "info-space">
	 				<table>
	 					<tr>
	 						<td class = "left-side name-space">${i.USERNAME}
	 							<span class = "date">(<fmt:formatDate value="${i.WRITEDATE }" pattern = "yyyy.MM.dd"/>)</span>
	 							<span class  = "comment1 small6">답글달기</span>
	 						</td>
	 						
	 						<c:if test="${i.USERNO == session.userno }">
	 						<td class = "right-side" data-boardNo = "${i.BOARDNO }">
	 								<span class = "delete-comment">삭제</span></td>
	 						</c:if>
	 								
	 					</tr>
	 				</table>
	 			</div>
	 			<div class = "main-comment">
	 			<br>
	 				${i.CONTENT }
	 			<br>
	 			<br>
	 			</div>
	 		</div>
	 </div>
	 </c:if>
	 <c:if test="${i.DEPTH == 2 }">
	 
	<div class = "comment2-area">
	<hr>
		↳ ${i.USERNAME} <span class = "date">(<fmt:formatDate value="${i.WRITEDATE }" pattern = "yyyy.MM.dd"/>)</span>
						<span class  = "comment2 small6"> 답글달기</span>
			<div class = "main-comment">
			<br>
				${i.CONTENT }
			</div>
	</div>
	 </c:if>
	 <c:if test="${i.DEPTH == 3 }">
	 <div class = "comment3-area" data-commentNo = "">
	 <hr>
	 	↳ ${i.USERNAME} <span class = "date">(<fmt:formatDate value="${i.WRITEDATE }" pattern = "yyyy.MM.dd"/>)</span> 
	 					<span class  = "comment3 small6"> 답글달기</span>
	 		<div class = "main-comment">
	 		<br>
	 			${i.CONTENT }
	 		</div>
	 </div>
	 </c:if>
	 </c:forEach>