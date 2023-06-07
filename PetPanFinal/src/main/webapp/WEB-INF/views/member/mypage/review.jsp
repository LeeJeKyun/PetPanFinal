
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="./mypage_header.jsp" />


<style type="text/css">
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

.co {
	color: #FF5050;
}

.img {
	width: 250px;
	height: 50px;
}

</style>

<div id = "fcontainer">

<h3 class="co">내 리뷰</h3>

<table class = "table-list" style = "width: 100%; border-collapse: collapse">
	
		<tr class = "th-border items">
		<th>상품 보기</th>
		<th>리뷰 내용</th>
		<th>별점</th>
	</tr>

<c:forEach items="${list }" var="list" >
	<tr class = "td-style items">
	<td><a href="../../shop/view?boardNo=${list.reviewno }">${list.objectno }</a></td>
	<td>${list.reviewcontent }</td>
<%-- 	<td>${list.reviewtitle }</td> --%>
	<td>
		<c:set var="star" value="${list.reviewstar }" />
		<c:if test="${star eq '1'}">
		    <img src="./../../resources/img/1star.jpg" class="img">
		</c:if>
		<c:if test="${star eq '2'}">
		    <img src="./../../resources/img/2star.jpg" class="img">
		</c:if>
		<c:if test="${star eq '3'}">
		    <img src="./../../resources/img/3star.jpg" class="img">
		</c:if>
		<c:if test="${star eq '4'}">
		    <img src="./../../resources/img/4star.jpg" class="img">
		</c:if>
		<c:if test="${star eq '5'}">
		    <img src="./../../resources/img/5star.jpg" class="img">
		</c:if>
	</td>
</tr>

</c:forEach>

</table>

</div><!-- fcontainer -->

<c:import url="../../layout/footer.jsp" />







