
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<style type="text/css">
#fcontainer{
	width: 800px;
	height: 1000px;
	margin: 0 auto;
}

#fcontainer a{
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

<h3 class="co">내가 쓴 댓글</h3>
<table class = "table-list" style = "width: 100%; border-collapse: collapse">
	<tr class = "th-border items">
		<th>댓글 번호</th>
		<th>댓글 내용</th>
		<th>작성날짜</th>
	</tr>
	
<c:forEach items="${comment }" var="comment" >
	<tr class = "td-style items">
		<td> ${comment.COMMENTNO }</td>
		<c:choose>
		<c:when test="${comment.BOARDTYPENO eq 1  }">
		<td><a href="../../board/care/view?boardNo=${comment.BOARDNO }"> ${comment.CONTENT }</a></td>
		</c:when>
		<c:when test="${content.BOARDTYPENO ne 1  }">
		<td><a href="../../board/board/detail?boardNo=${comment.BOARDNO }"> ${comment.CONTENT }</a></td>
		</c:when>
		</c:choose>
		<td class = "date"><fmt:formatDate value = "${comment.WRITEDATE }" pattern = "yyyy.MM.dd" /></td>
	</tr>

</c:forEach>

</table>

</div>

<c:import url="../../layout/footer.jsp" />







