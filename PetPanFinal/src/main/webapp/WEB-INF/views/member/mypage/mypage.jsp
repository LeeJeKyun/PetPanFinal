
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">

/* 회원탈퇴 */
.ur{  
	color: #767678;
	text-decoration: none; 
}


.r {
	text-align: center;
	margin-top: 100px;
}

 .m{ 
	text-align: center;
	border: 1px solid #263959;
	border-radius: 2em;
	width: 500px;
	margin: auto;
 } 

 .my{ 
	text-align: center;
	color: #FF5050;
 } 
 .myp{ 
	text-align: center;
	color: #FF5050;
	margin-top: 100px;
/* 	margin: auto; */
 } 

 .pe { 
	text-align: center;
	border: 1px solid #263959;
	border-radius: 2em;
	width: 500px;
	margin: 0px auto;
 } 
 
 .img {
 	position: absolute;
  	float: left; 
  	margin: 15px -150px;
 }

.petUp{
	margin: 2px 48%;
}

.petUp a{
	color: #FF5050;
 	text-decoration: none; 
}
</style>

<c:import url="./mypage_header.jsp" />


<div class="text">


<div class="my">
<h3>내 정보</h3>
</div>

<div class="m">

<h2 style="color: #263959;">이름 : ${detail.userName }</h2>
<h4 style="color: #52616a;">닉네임 : ${detail.userNick }</h4>
<h4 style="color: #52616a;">아이디 : ${detail.userId }</h4>
<h4 style="color: #52616a;">Phone : ${detail.phone }</h4>

</div> <!-- m -->



<c:choose>
<c:when test="${empty hospital }">

<div class="myp">
<h3>펫 정보</h3>
</div>

<div class="pe">

<c:forEach items="${petDetail }" var="petDetail" >
	<img alt=".." src="<%=request.getContextPath()%>/petfile/${petDetail.storedName}" width="90px" height="90px" class="img">
</c:forEach>

<c:forEach items="${petInfo }" var="petInfo" >
	<h2 style="color: #263959;">이름 : ${petInfo.petName }</h2>
	<h4 style="color: #52616a;">종류 : ${petInfo.type }</h4>
</c:forEach>


</div> <!-- pe -->

<div class="petUp">
<a href="./petUpdate">수정하기</a>
</div>

</c:when>

</c:choose>



<div class="r">

<a href="../unregist/cheak" class="ur">회원탈퇴</a>

</div>

















</div>







