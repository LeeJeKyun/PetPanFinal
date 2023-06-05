
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="../layout/header.jsp" />

<style>
.title{
	margin-top: 10%;
	margin-left: 5%;
}
.content{
	margin: 5%;
}

.table{
	width: 1100px;
}

.tr_head{
 	font-size: x-large;
 	text-align: center;
}
.tr_body{
	text-align: center;
}
</style>
<h1 class="title">${list[0].BUYERNAME }님의 구매내역</h1>

<div class="content">

<table class="table">
	<thead class="thead">
		<tr class="tr_head">
			<td>상품명</td>
			<td>개수</td>
			<td>가격</td>
			<td>구매날짜</td>
			<td>배송현황</td>
			<td></td>
		</tr>
	</thead>
<c:forEach var="list" items="${list }">
<tbody class="tbody">
	<tr class="tr_body">
		<td>${list.NAME }</td>
		<td>${list.QUANTITY }</td>
		<td>${list.PRICE }</td>
		<td>${list.BUYDATE }</td> 
		<c:set var="del" value="${list.COMPLETE }" />
		<c:choose>
			<c:when  test="${del eq 'y'}">
	   		<td>배송완료</td>
	   		</c:when>
			<c:when  test="${del eq 'n'}">
	   		<td>배송준비중</td>
	   		</c:when>
			<c:when  test="${del eq '1'}">
	   		<td>리뷰작성완료</td>
	   		</c:when>
	    </c:choose>
			<c:set var="COMPLETE" value="${list.COMPLETE }" />
			<c:if test="${COMPLETE eq 'y'}">
		    	<td><a href="./writeReview?objectno=${list.OBJECTNO }&buyno=${list.BUYNO}"><button>리뷰쓰기</button></a></td>
			</c:if>
			<c:if test="${COMPLETE eq '1'}">
				<td><button disabled="disabled">리뷰작성완료</button>
			</c:if>
	</tr>
</tbody>
</c:forEach>

</table>

</div>


<c:import url="../layout/footer.jsp" />







