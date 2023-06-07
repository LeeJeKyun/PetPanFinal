<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../../layout/header_plain.jsp" />



<script type="text/javascript">

//1. 처음 포커스 벗어났을 경우 메시지 띄우기
$(function(){

   //비밀번호를 입력하지 않고 넘어갈 경우(필수 정보입니다 띄우기)
   $("#userPw").blur(function(){
      if($("#userPw").val()==''){
         $("#userPw_msg").html("필수정보입니다")
      }else{
         $("#userPw_msg").html("")
      }
   })
   
   //tab으로 다음칸으로 넘어갔을 경우  -- blur function
   ////비밀번호입력과 비밀번호 재확인입력이 동일하지않을 경우 메시지
   $("#userPw_check").blur(function(){   
      
      if($("#userPw").val() != $("#userPw_check").val()){
         $("#userPwcheck_error").html("비밀번호가 일치하지않습니다")
      }else{
         $("#userPwcheck_error").html("비밀번호가 일치합니다")
      }
   })
   
   //비밀번호 재확인칸을 클릭했을 경우 -- click function
   //비밀번호입력과 비밀번호 재확인입력이 동일하지않을 경우 메시지
   $("#userPw_check").click(function(){   
      
      if($("#userPw").val() != $("#userPw_check").val()){
         $("#userPwcheck_error").html("비밀번호가 일치하지않습니다")
      }else{
         $("#userPwcheck_error").html("비밀번호가 일치합니다")
      }
   })
   
   
})
//----------------------------------------------------------------------------------------

//2. 버튼 클릭했을 경우비밀번호 정규화가 맞지않으면 메시지 띄우기
$(function(){
   
   //유효성 검사 -- 버튼 눌렀을때 push 알람 띄우기
   $("form").submit(function(){
      
      //validate가 틀렸을경우 리턴값 false
      //validate - 아이디,비밀번호 : 정규화 
      if(!validate()){
         return false
      }
      
      return true;
   })
   

   //비밀번호 입력란 클릭시 밑에 메시지 없애기
   $("#userPw").focus(function(){
      $("#userPw_msg").html("")   
   })

})

//비밀번호 정규화가 틀렸을 경우 버튼푸시 실패
function validate(){

   //가입하기버튼 눌렀을 때 비밀번호 유효성검사
   if($('#userPw').val()==''){
      $('#userPw_msg').html("비밀번호를 입력해주세요")
      return false
   }
   
   //비밀번호 정규화
    var pwReg =/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;
         
   if( !pwReg.test( $("#userPw").val() ) )  {
         $("#userPw_msg").html("비밀번호는 알파벳 대소문자, 숫자, 특수기호 조합으로 8자 이상, 20자 이하로 작성하세요")
         return false
   }
    
     return true;
  
}

//3. 버튼 클릭했을 경우 비밀번호와 비밀번호 재확인이 일치하지 않으면 메시지 띄우기
$(function(){
   
   //유효성 검사 -- 버튼 눌렀을때 push 알람 띄우기
   $("form").submit(function(){
      
      //validate가 틀렸을경우 리턴값 false
      //compare_check - 비밀번호,비밀번호재확인 일치 
      if(!compare_check()){
         return false
      }
      return true;
   })
  })
//버튼을 클릭했을 경우 비밀번호와 비밀번호재확인이 일치하지않으면 버튼푸시 실패
//비밀번호와 비밀번호 재확인 입력 유효성검사
function compare_check(){
   
   //가입하기버튼 눌렀을 때 비밀번호 확인 유효성 검사
   if($("#userPw").val() != $("#userPw_check").val()){
      $("#userPwcheck_error").html("비밀번호가 일치하지않습니다")
      return false;
   }
   return true
}



</script>



<style type="text/css">

#login{
	width: 600px;
	margin: 0 auto;
}


input{
   border:none;
   
   border-bottom: 2px solid #FFDAD7;
}

.select{
    width: 300px;
    margin: 35px auto;
    text-align: center;
}

.select span{
    width: 300px;
    margin: 35px auto;
    text-align: center;
    font-size: small;
}
 
.select input[type=text]{
   width: 50%;
   height: 35px;

} 

#btn{
   background-color: #FFDAD7;
   color : #FF5050;
   border-radius: 7px;
   border-color: #FFDAD7;
   width: 300px;
   height: 35px;
   
}

.noline{
    text-decoration-line: none;
}

#clear {
	clear: both;
}


input:focus {
	outline: 1px solid #FF5050;
}

</style>

</head>
<body>

<div id="search_id">


   
<form action="./pw_result" method="post">

   <div class="select">


<c:choose>
	<c:when test="${empty searchPw}">
		<p class="mb-4">조회결과가 없습니다.</p>
		<a href='./pw' style="text-align: center; font-size: 20px; color: #FF5060;">돌아가기</a>
	</c:when>
	
	<c:otherwise>
	   <div class="select">
      <label for="userPw">비밀번호</label>
      <input type="password" id="userPw" name="userPw" placeholder="알파벳 대소문자, 숫자, 특수기호 조합으로 6자 이상, 16자 이하로 작성">
      <span id="userPw_msg" class="msg"></span>
   </div>
   
   <div class="select">
      <label for="userPw_check">비밀번호 재확인</label>
      <input type="password" id="userPw_check" name="userPw_check" >
      <span id="userPwcheck_error" class="msg"></span>
   </div>
	
   <div class="select">
      <button id="btn">비밀번호 변경</button>
   </div>
	
<!-- 	<div> -->
<!--    <a href='./login' style="text-align:center; font-size:20px; color:#FF5060;">비밀번호 변경</a><br> -->
<!--    </div> -->
	</c:otherwise>
	
	</c:choose>
</div>
      

    
   


</form>

</div>






<c:import url="../../layout/footer_plain.jsp" />