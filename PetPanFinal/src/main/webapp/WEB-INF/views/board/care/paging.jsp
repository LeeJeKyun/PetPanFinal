<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!--     <link href = "../resources/css/paging.css"  rel = "stylesheet" /> -->
    <style>
.container div{
	width: 600px;
	 
}    
.ul{
	width: 474px;
	list-style: none;
	text-align: center;
	height: 34px;
	margin: 0 auto
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
	background-color: #e3aeae;
	color: #FFFFFF;
}
    </style>
<div id = container>
<div>
	<ul class = "ul">
		<!-- 처음으로 이동 -->
		<c:if test="${paging.curPage ne 1 }">
		<a href = "<%=request.getContextPath() %>/board/care/list?curPage=1&search=${paging.search}&distance=${distance}"><li class = "startPage li">처음</li></a>
		</c:if>
		
		<!-- 왼쪽으로 한 페이지 이동 -->
		<c:if test="${paging.curPage  gt 1 }">
			<a href = "<%=request.getContextPath() %>/board/care/list?curPage=${paging.curPage-1 }&search=${paging.search}&distance=${distance}"><li class = "li">&#60;</li></a>
		</c:if>
		
		<c:forEach var = "i"  begin = "${paging.startPage }" end = "${paging.endPage }">
<%-- 		<c:forEach var = "i" begin = "1" end = "10"> --%>
			<c:if test="${paging.curPage eq i }">
				<a href = "<%=request.getContextPath() %>/board/care/list?curPage=${i }&search=${paging.search}&distance=${distance}"><li class = "colorBox li">${i }</li></a>
			</c:if>
			<c:if test="${paging.curPage ne i }">
				<a href = "<%=request.getContextPath() %>/board/care/list?curPage=${i }&search=${paging.search}&distance=${distance}"><li class = "li">${i }</li></a>
			</c:if>
		</c:forEach>
		<!-- 오른쪽 페이지로 한 페이지 이동 -->
		<c:if test="${paging.curPage  lt paging.totalPage}">
			<a href = "<%=request.getContextPath() %>/board/care/list?curPage=${paging.curPage+1 }&search=${paging.search}&distance=${distance}"><li class = "li">&#62;</li></a>
		</c:if>
		
		<!-- 마지막 페이지로 이동 -->
		<c:if test="${paging.curPage lt paging.totalPage}">

			<a href = "<%=request.getContextPath() %>/board/care/list?curPage=${paging.totalPage }&search=${paging.search}&distance=${distance}"><li class = "endPage li">마지막</li></a>
		</c:if>
	</ul>
</div>
</div>    
    