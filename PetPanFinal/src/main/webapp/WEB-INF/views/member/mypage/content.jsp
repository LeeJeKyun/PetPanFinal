
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<style type="text/css">

.ur{
	color: #767678;
	text-decoration: none; 
}

.content{

	color: #263959;
}

#fcontainer{
	width: 800px;
	height: 1000px;
	margin: 0 auto;
}

a{
 	text-decoration: none; 
 	color: #FF5050; 
 	font-size: small;
 	font-weight: 600;
}
.ur{
	color: #767678;
	text-decoration: none; 
}

.th-border{
	border-top: solid #FFDAD7 3px ; 
	border-bottom:solid #FFDAD7 3px ;
	height: 30px;
}
 .td-style{ 
 	text-align: center; 
 	border-bottom: solid #FFDAD7 2px;
 	height: 50px;
 } 
 .date{
 	font-size: 0.7em;
 }

.co {
	color: #FF5050;
}


</style>

<c:import url="./mypage_header.jsp" />

<div id = "fcontainer">

<h3 class="co">내가 쓴 게시글</h3>

<table class = "table-list" style = "width: 100%; border-collapse: collapse">
	<tr class = "th-border items">
		<th>글 번호</th>
		<th>글 제목	</th>
		<th>작성날짜</th>
	</tr>
	
	

	
<c:forEach items="${content }" var="content" >
	<tr class = "td-style items">
		<td> ${content.boardNo }</td>
		<c:choose>
		<c:when test="${content.boardTypeNo eq 1  }">
		<td><a href="../../board/care/view?boardNo=${content.boardNo }"> ${content.boardTitle }</a></td>
		</c:when>
		<c:when test="${content.boardTypeNo ne 1  }">
		<td><a href="../../board/board/detail?boardNo=${content.boardNo }"> ${content.boardTitle }</a></td>
		</c:when>
		</c:choose>
		<td class = "date"><fmt:formatDate value = "${content.writeDate }" pattern = "yyyy.MM.dd" /></td>
	</tr>
</c:forEach>


</table>

	 		



<%-- <c:forEach items="${content }" var="content" > --%>
<!-- <div> -->
<%-- 	<h2 style="color: #263959;">글 번호 : ${content.boardNo }</h2> --%>
<%-- 	<h4 style="color: #52616a;">글 제목 : ${content.boardTitle }</h4> --%>
<%-- 	<h4 style="color: #52616a;">작성날짜 : ${content.writeDate }</h4> --%>
<!-- </div> -->
<%-- </c:forEach> --%>












</div>
<c:import url="../../layout/footer.jsp" />







