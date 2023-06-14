<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!--     <link href = "../resources/css/paging.css"  rel = "stylesheet" /> -->
    <style>
.container div{
	width: 600px;
	
}    
.ul{
	width: 320px;
	list-style: none;
	text-align: center;
	height: 50px;
/* 	margin-top: 50px; */
	margin-left: auto;
	margin-right: auto;
}
.li{
	float: left;	
	border: solid black 1px;
	width: 30px;
	height: 30px;
	text-align: center;
}
a{
	text-decoration-line: none;
}
.endPage{
	font-size: 0.8em;
	width: 40px;
	border-radius: 0px 10px 10px 0px  / 0px 10px 10px 0px;
}
.startPage{
	font-size: 0.8em;
	width: 40px;
	border-radius: 10px 0px 0px 10px  / 10px 0px 0px 10px; 
}
.colorBox{
	background-color: #1a73e8;
	color: #FFFFFF;
}
    </style>
<div id = container>
<div>
	<ul class = "ul">
		<!-- 처음으로 이동 -->
		<c:if test="${paging.curPage ne 1 }">
			<li class = "startPage li"><a href = "./list?curPage=1&search=${paging.search}&mammalia=${paging.mammalia}&rodent=${paging.rodent}&reptile=${paging.reptile}&birds=${paging.birds}">처음</a></li>
		</c:if>
		
		<!-- 왼쪽으로 한 페이지 이동 -->
		<c:if test="${paging.curPage  gt 1 }">
			<li class = "li"><a href = "./list?curPage=${paging.curPage-1 }&search=${paging.search}&mammalia=${paging.mammalia}&rodent=${paging.rodent}&reptile=${paging.reptile}&birds=${paging.birds}">&#60;</a></li>
		</c:if>
		
		<c:forEach var = "i"  begin = "${paging.startPage }" end = "${paging.endPage }">
<%-- 		<c:forEach var = "i" begin = "1" end = "10"> --%>
			<c:if test="${paging.curPage eq i }">
				<li class = "colorBox li"><a href = "./list?curPage=${i }&search=${paging.search}&mammalia=${paging.mammalia}&rodent=${paging.rodent}&reptile=${paging.reptile}&birds=${paging.birds}">${i }</a></li>
			</c:if>
			<c:if test="${paging.curPage ne i }">
				<li class = "li"><a href = "./list?curPage=${i }&search=${paging.search}&mammalia=${paging.mammalia}&rodent=${paging.rodent}&reptile=${paging.reptile}&birds=${paging.birds}">${i }</a></li>
			</c:if>
		</c:forEach>
		<!-- 오른쪽 페이지로 한 페이지 이동 -->
		<c:if test="${paging.curPage  lt paging.totalPage}">
			<li class = "li"><a href = "./list?curPage=${paging.curPage+1 }&search=${paging.search}&mammalia=${paging.mammalia}&rodent=${paging.rodent}&reptile=${paging.reptile}&birds=${paging.birds}">&#62;</a></li>
		</c:if>
		
		<!-- 마지막 페이지로 이동 -->
		<c:if test="${paging.curPage lt paging.totalPage}">

			<li class = "endPage li"><a href = "./list?curPage=${paging.totalPage }&search=${paging.search}&mammalia=${paging.mammalia}&rodent=${paging.rodent}&reptile=${paging.reptile}&birds=${paging.birds}">마지막</a></li>
		</c:if>
	</ul>
</div>
</div>    
    