<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!--     <link href = "../resources/css/paging.css"  rel = "stylesheet" /> -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>
    <style>
.paging div{
	
	width: 600px;
	
}    
.pagination{
/* 	width: 600px; */
/* /* 	list-style: none; */ */
/* 	text-align: center; */
 	height: 50px; 
	margin-top: 50px;
 	margin-left: 100px; 
}
/* .page-item{ */
/* 	float: left;	 */
/* 	border: solid black 1px; */
/* 	width: 30px; */
/* 	height: 30px; */
/* 	text-align: center; */
/* } */
 a{ 
 	text-decoration-line: none;
 	color :#5f61e6;
	
 }
/* .endPage{ */
/* 	font-size: 0.8em; */
/* 	width: 40px; */
/* 	border-radius: 0px 10px 10px 0px  / 0px 10px 10px 0px; */
/* } */
/* .startPage{ */
/* 	font-size: 0.8em; */
/* 	width: 40px; */
/* 	border-radius: 10px 0px 0px 10px  / 10px 0px 0px 10px;  */
/* } */
 .colorBox{ 
 	background-color: #1a73e8; 
	color: #FFFFFF; 
 } 
    </style>
<div id = paging>
<div>
	<ul class = "pagination pagination-lg">
		<!-- 처음으로 이동 -->
		<c:if test="${paging.curPage ne 1 }">
			<li class = "startPage page-item "><a class="page-link" href = "./list?curPage=1&search=${paging.search}">처음</a></li>
		</c:if>
		
		<!-- 왼쪽으로 한 페이지 이동 -->
		<c:if test="${paging.curPage  gt 1 }">
			<li class = "page-item"><a class="page-link" href = "./list?curPage=${paging.curPage-1 }&search=${paging.search}">&#60;</a></li>
		</c:if>
		
		<c:forEach var = "i"  begin = "${paging.startPage }" end = "${paging.endPage }">
<%-- 		<c:forEach var = "i" begin = "1" end = "10"> --%>
			<c:if test="${paging.curPage eq i }">
				<li class = "page-item active"><a class="page-link" href = "./list?curPage=${i }&search=${paging.search}">${i }</a></li>
			</c:if>
			<c:if test="${paging.curPage ne i }">
				<li class = "page-item"><a class="page-link" href = "./list?curPage=${i }&search=${paging.search}">${i }</a></li>
			</c:if>
		</c:forEach>
		<!-- 오른쪽 페이지로 한 페이지 이동 -->
		<c:if test="${paging.curPage  lt paging.totalPage}">
			<li class = "page-item"><a class="page-link" href = "./list?curPage=${paging.curPage+1 }&search=${paging.search}">&#62;</a></li>
		</c:if>
		
		<!-- 마지막 페이지로 이동 -->
		<c:if test="${paging.curPage lt paging.totalPage}">

			<li class = "endPage page-item"><a class="page-link" href = "./list?curPage=${paging.totalPage }&search=${paging.search}">마지막</a></li>
		</c:if>
	</ul>
</div>
</div>    
    