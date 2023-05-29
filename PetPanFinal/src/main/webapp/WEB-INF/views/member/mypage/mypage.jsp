
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">

.ur{
	color: #767678;
	text-decoration: none; 
	
}

.r {
	text-align: center;

}

 .m{ 
	text-align: center;
	border: 1px solid #263959;
	border-radius: 2em;
	width: 500px;
	margin: 100px auto;
 } 
 .pe { 
	text-align: center;
	border: 1px solid #263959;
	border-radius: 2em;
	width: 500px;
	margin: 0px auto;
 } 

</style>

<c:import url="./mypage_header.jsp" />

<div class="text">

<br><br>

<div class="m">

<h2 style="color: #263959;">이름 : ${detail.userName }</h2>
<h4 style="color: #52616a;">닉네임 : ${detail.userNick }</h4>
<h4 style="color: #52616a;">아이디 : ${detail.userId }</h4>
<h4 style="color: #52616a;">Phone : ${detail.phone }</h4>
</div> <!-- my -->


<div class="pe">

<h2 style="color: #263959;">이름 : </h2>
<h4 style="color: #52616a;">종류 : </h4>



</div>




<div class="r">

<a href="./unregister" class="ur">회원탈퇴</a>

</div>










</div>







