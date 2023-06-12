<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<style>
.comment-like-area{
	display: inline-block;
}
.comment-heart{
	width: 20px;
	height: 20px;
}
</style>
<!-- ajax html -->
	 <c:forEach var = "i" items = "${list}">
	 <c:if test="${i.DEPTH == 1}">
	 <div class = "comment-area" data-commentNo ="${i.COMMENTNO}" >
	 		<div class = " f comment">
	 			<div class = "info-space">
	 				<table>
	 					<tr>
	 						<td class = "left-side name-space">${i.USERNAME}
	 							<span class = "date">(<fmt:formatDate value="${i.WRITEDATE }" pattern = "yyyy.MM.dd"/>)</span>
	 							<c:if test="${not empty userno }">
	 								<span onclick="commentShow(${i.COMMENTNO})" class = "comment3">답글달기</span>
	 							</c:if>
	 							<!-- 댓글 좋아요 -->
	 							<div class = "comment-like-area">
								 	<div><img class = "comment-heart" data-commentNo = "${i.COMMENTNO }" alt="좋아요" src="<%=request.getContextPath()%>/resources/img/emptyheart.png"></div>
							<%-- 	<div><img alt="좋아요" class = "comment-heart" data-commentNo = "${i.COMMENTNO }" src="<%=request.getContextPath()%>/resources/img/fillheart.png"></div> --%>
								 	<div></div>
							 	</div>
	 						</td>
	 						
	 						<c:if test="${i.USERNO == userno }">
	 						<td class = "right-side" >
	 								<span class = "delete-comment" onclick = "deleteComment(${i.COMMENTNO })">삭제</span></td>
	 						</c:if>
	 						
	 						<!-- 신고 -->
	 						<c:if test="${i.USERNO != userno }">
	 						<td class = "right-side" >
	 							<span class = "report-comment" onclick = "reportComment(${i.COMMENTNO })">신고하기</span>
	 							
	 						</td>
	 						</c:if>
	 						<!-- 신고 -->
	 					</tr>
	 				</table>
	 			</div>
	 			<div class = "main-comment" data-commentNo = "${i.COMMENTNO }">
	 			<br>
	 				${i.CONTENT }
	 			</div>
	 		</div>
	 </div>
	 <div class = "writeBox"></div>
	 </c:if>
	 <c:if test="${i.DEPTH == 2 }">
	 
	<div class = "comment2-area" data-commentNo = "${i.COMMENTNO }">
	<hr>
	<c:if test="${i.USERNO == userno }">
	<div class = "right-side" >
	 	<span class = "delete-comment" onclick = "deleteComment(${i.COMMENTNO })">삭제</span>
	</div>
	</c:if>
	<!-- 신고 -->
	 <c:if test="${i.USERNO != userno }">
	 <div class = "right-side" >
	 	<span class = "report-comment" onclick = "reportComment(${i.COMMENTNO })">신고하기</span>
	 </div>
	 </c:if>
	 <!-- 신고 -->
		↳ ${i.USERNAME} <span class = "date">(<fmt:formatDate value="${i.WRITEDATE }" pattern = "yyyy.MM.dd"/>)</span>
						<c:if test="${not empty userno }">
							<span class  = "comment3" onclick="commentShow(${i.COMMENTNO})"> 답글달기</span>
						</c:if>
						
			<div class = "main-comment" data-commentNo = "${i.COMMENTNO }">
			<br>
				${i.CONTENT }
			</div>
	</div>
	 <div class = "writeBox"></div>
	 </c:if>
	 <c:if test="${i.DEPTH == 3 }">
	 <div class = "comment3-area" data-commentNo = "${i.COMMENTNO }">
	 <hr>
	 <c:if test="${i.USERNO == userno }">
	 <div class = "right-side" >
	 	<span class = "delete-comment" onclick = "deleteComment(${i.COMMENTNO })">삭제</span>
	 </div>
	 </c:if>
	 <!-- 신고 -->
	 <c:if test="${i.USERNO != userno }">
	 <div class = "right-side" >
	 	<span class = "report-comment" onclick = "reportComment(${i.COMMENTNO })">신고하기</span>
	 </div>
	 </c:if>
	 <!-- 신고 -->
	 	↳ ${i.USERNAME} <span class = "date">(<fmt:formatDate value="${i.WRITEDATE }" pattern = "yyyy.MM.dd"/>)</span> 
	 		<div class = "main-comment" data-commentNo = "${i.COMMENTNO }">
	 		<br>
	 			${i.CONTENT }
	 		</div>
	 </div>
	 
	 </c:if>
	 </c:forEach>