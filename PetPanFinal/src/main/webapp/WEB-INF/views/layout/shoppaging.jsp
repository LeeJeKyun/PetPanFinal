<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!--     <link href = "../resources/css/paging.css"  rel = "stylesheet" /> -->
<!-- JavaScript Bundle with Popper -->
    <style>
		
.ul{
	width: 600px;
	list-style: none;
	text-align: center;
	height: 50px;
	margin-top: 50px;
	margin: 0 auto;
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

.seach_div{
	text-align: center;
    margin: 20px;
    margin-left: -125px;
}

#codeIdSubmit{
	background-color: #1a73e8;
	border: #1a73e8;
	width: 50px;
	height: 25px;
}



</style>
<div id = container>

	<div>
		<div class="search_div" style="text-align: center; margin: 20px; margin-left: -125px;">
			<form action="<%=request.getContextPath() %>/shop/main" method="get">
				<input type="text" name="search" value="${search }">
				<input type="submit" id="codeIdSubmit" value="검색" class="btn btn-info"style="cursor: pointer;">
			</form>
		</div>
		<ul class = "ul">
			<!-- 처음으로 이동 -->
			<c:if test="${paging.curPage ne 1 }">
				<li class = "startPage li"><a href = "./main?curPage=1">처음</a></li>
			</c:if>
			
			<!-- 왼쪽으로 한 페이지 이동 -->
			<c:if test="${paging.curPage  gt 1 }">
				<li class = "li"><a href = "./main?curPage=${paging.curPage-1 }">&#60;</a></li>
			</c:if>
			
			<c:forEach var = "i"  begin = "${paging.startPage }" end = "${paging.endPage }">
	<%-- 		<c:forEach var = "i" begin = "1" end = "10"> --%>
				<c:if test="${paging.curPage eq i }">
					<li class = "colorBox li"><a href = "./main?curPage=${i }">${i }</a></li>
				</c:if>
				<c:if test="${paging.curPage ne i }">
					<li class = "li"><a href = "./main?curPage=${i }">${i }</a></li>
				</c:if>
			</c:forEach>
			<!-- 오른쪽 페이지로 한 페이지 이동 -->
			<c:if test="${paging.curPage  lt paging.totalPage}">
				<li class = "li"><a href = "./main?curPage=${paging.curPage+1 }">&#62;</a></li>
			</c:if>
			
			<!-- 마지막 페이지로 이동 -->
			<c:if test="${paging.curPage lt paging.totalPage}">
	
				<li class = "endPage li"><a href = "./main?curPage=${paging.totalPage }">마지막</a></li>
			</c:if>
		</ul>
	</div>
</div>    
    