
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

.select{
    width: 420px;
    margin: 15px auto;

}




.select input {
   width: 200px;
   height: 35px;
   border:none;
   border-bottom: 2px solid #FFDAD7;
   margin: 0px 20px;
   
} 


#btn{
   background-color: #FFDAD7;
   color : #FF5050;
   border-radius: 7px;
   border-color: #FFDAD7;
   width: 430px;
   height: 35px;
   margin-top: 50px;
}

.check {
	margin: 28px;
}

 .m{ 
	text-align: center;
	border: 1px solid #FF5050;
	border-radius: 2em;
	width: 529px;
	margin: auto;
	height: 330px;
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
 
 #pw{
 	margin-top: 75px;
 }
 
 h5{
 	margin: 0;
 }

</style>


<script type="text/javascript">

function compare_check(){
	   
	   //탈퇴버튼 눌렀을 때 비밀번호 확인 유효성 검사
	   if($("#userPw").val() != $("#userPw_check").val()){
// 	      $("#userPwcheck_error").html("비밀번호가 일치하지않습니다")
	      return false;
	   }
	   return true
	}

</script>




<c:import url="../mypage/mypage_header.jsp" />
<script type="text/javascript">
</script>


<div class="text">


<div class="my">
<h3>회원탈퇴</h3>
</div>

<form action="./cheak" method="post">

<div class="m">


   <div class="select">
   		<div id="pw">
      <label for="userPw" class="check">비밀번호</label>
      <input type="password" id="userPw" name="pw" >
      <span id="userPw_msg" class="msg"></span>
      	</div>
   </div>
   
   <div class="select">
      <label for="userPw_check" >비밀번호 재확인</label>
      <input class="form" type="password" id="userPw_check" name="userPw_check" >
   </div>


	<c:if test="${cheak eq '0' }">
		<h5>비밀번호가 일치하지 않습니다.</h5>
	</c:if>

	<div class="select">
		<button id="btn">탈퇴하기</button>
	</div>


</div> <!-- m -->
</form>


<div class="r">

<a href="../unregist/cheak" class="ur">탈퇴취소하기</a>

</div>



</div>







