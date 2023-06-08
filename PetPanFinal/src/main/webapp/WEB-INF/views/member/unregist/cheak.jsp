
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
 label{
 	
 }

</style>

<c:import url="../mypage/mypage_header.jsp" />
<script type="text/javascript">
</script>


<div class="text">


<div class="my">
<h3>회원탈퇴</h3>
</div>
<form action="./cheak" method="post">
<div class="m">
<label for="userPw">비밀번호</label>
<input type="text" id="userPw" class="userPw" name="pw"><br><br>
<label for="userPw_check">비밀번호 확인</label>
<input type="text" id="userPw_check" class="userPw_cheak" >
<br><br>
<c:if test="${cheak eq '0' }">
<h5>비밀번호가 일치하지 않습니다.</h5>
</c:if>
<button></button>
</div> <!-- m -->
</form>


<div class="r">

<a href="../unregist/cheak" class="ur">탈퇴취소하기</a>

</div>



</div>







