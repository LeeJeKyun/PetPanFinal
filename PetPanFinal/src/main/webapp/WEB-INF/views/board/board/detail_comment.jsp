<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<script type="text/javascript">
$(function(){
	if( ${i.DEPTH == 1} ){
		console.log("depth 1");
		
		depth1()
	}else if( ${i.DEPTH == 2} ){
		console.log("depth 2");
		
	}else if( ${i.DEPTH == 3} ){
		console.log("depth 3");
		
	}
	
})
function depth1(){
	
}
function depth2(){
	
}
function depth3(){
	
}
</script>

<!-- ajax html -->
<%-- 	 <c:forEach var = "i" items = "commentList"> --%>
<%-- 	 <c:if test="${i.DEPTH == 1}"> --%>
	 <div class = "comment-area">
	 		<div class = " f comment">
	 			<div class = "info-space">
	 				<table>
	 					<tr>
	 						<td class = "left-side name-space">글쓴이${i.USERNAME}
	 							<span class = "date">(2020.12.13)${i.WRITEDATE }</span>
	 							<span class  = "comment1 small6">답글달기</span>
	 						</td>
	 						
<%-- 	 						<c:if test="${i.USERNO == session.userNo }"> --%>
	 						<td class = "right-side" data-boardNo = "${i.BOARDNO }">
	 								<span class = "delete-comment">삭제</span></td>
<%-- 	 						</c:if> --%>
	 								
	 					</tr>
	 				</table>
	 			</div>
	 			<div class = "main-comment">
	 			<br>
	 				댓그르르르
	 			<br>
	 			<br>
	 			</div>
	 		</div>
<!-- 	 		<div class = "comment1 small6">답글달기</div> -->
	 </div>
<%-- 	 </c:if> --%>
<%-- 	 <c:if test="${i.DEPTH == 2 }"> --%>
	 
	<div class = "comment2-area">
	<hr>
		↳ 글쓴이2 <span class = "date">(2020.12.12)</span>
						<span class  = "comment2 small6"> 답글달기</span>
			<div class = "main-comment">
			<br>
				대ㅐ댇댇글
				d<br>
				d<br>
				d<br>
				dd<br>
			</div>
<!-- 		<div class = "comment2 small6 ">답글달기</div> -->
	</div>
	 <div class = "comment3-area" data-commentNo = "">
	 <hr>
	 	↳ 글쓴이3 <span class = "date">(2020.12.12)</span> 
	 					<span class  = "comment3 small6"> 답글달기</span>
	 		<div class = "main-comment">
	 		<br>
	 			대대대대대대ㅐㄷ대대댓글
	 		</div>
<!-- 	 	<div class = "comment3 small6 ">답글달기</div> -->
	 </div>
<%-- 	 </c:if> --%>
<%-- 	 <c:if test="${i.DEPTH == 3 }"> --%>
	 
<%-- 	 </c:if> --%>
<%-- 	 </c:forEach> --%>
	 <!-- ajax html -->

