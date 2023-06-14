<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>+ + + PETPAN + + +</title>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- timepicker -->
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>


<style type="text/css">
#header {
	text-align: left;
	line-height: 3em;
	background: #FFDAD7;
	color: #FF5050;
	font-size: larger;
	font-weight: 600;


}

#footer {
	text-align: center;
}

/* .pet { */
/* 	line-height: 3em; */
/* 	background: #FFDAD7; */
/* 		color: #FF5050; */
/* 	font-size: larger; */
/* 	font-weight: 600; */
/* } */

#footer {
	margin-top: 30px;
}

 #header a { 
 	text-decoration: none; 
 	color: #FF5050; 
 	font-weight: 600;
 } 
.pet {
 	font-size: small;
}

.my {
	font-size: lazer;
}

#menu a {
 	text-decoration: none; 
 	color: #FF5050; 
 	font-size: small;
 	font-weight: 600;
}

#menu{
	float: left;
}


ul {
	text-decoration: none;
	color: #FF5060;
    list-style:none;
    margin:0;
    padding:0;
}

li {
    margin: 0px 15px auto;
    border : 0;
    float: left;
}

.clearbox {
	height: 0;
	overflow: hidden;
	line-height: 0;
	clear: both;
}

</style>

</head>
<body>




<div id="header">  <!-- header -->


<p><a href="/" class="pet">PET PAN&nbsp;</a>  <a href="./mypage" class="my"> 마이 페이지 </a></p>

</div>

<div>
<c:choose>
<c:when test="${empty hospital }">
<div id="menu">

<ul class="pan">
	<li>
	<a href="./myprofile" >정보 수정</a>
	<em></em>
	</li>

	<li>
	<a href="./petinfo">동물 등록</a>
	<em></em>
	</li>

	<li>
	<a href="./content">내가 쓴 글</a>
	<em></em>
	</li>

	<li>
	<a href="./comment">내가 쓴 댓글</a>
	<em></em>
	</li>
	<li>
	<a href="./review">내 리뷰</a>
	<em></em>
	</li>
</ul>
</div>
</c:when>

<c:when test="${not empty hospital and hospital }">

<div id="menu">

<ul class="pan">
	<li>
	<a href="./myprofile" >정보 수정</a>
	<em></em>
	</li>

	<li>
	<a href="./hospital">병원 등록</a>
	<em></em>
	</li>

	<li>
	<a href="./content">내가 쓴 글</a>
	<em></em>
	</li>

	<li>
	<a href="./comment">내가 쓴 댓글</a>
	<em></em>
	</li>
</ul>
</div>


</c:when>

</c:choose>
<div class="clearbox"></div>





</div><!-- header -->





