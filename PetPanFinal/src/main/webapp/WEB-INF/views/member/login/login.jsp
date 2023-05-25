
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../../layout/header_plain.jsp" />
<script type="text/javascript">

function login(){

   if($("#login_form").$("#userId").value()==""){
      $("#userid_msg").html("아이디를 잘못 입력했습니다")
      $("#userid").focus()
//       return
   }else if($("#login_form").$("#userPw").value()==""){
      $("#userpw_msg").html("비밀번호를 잘못 입력했습니다")
      $("#userpw").focus()
//       return
   }
   $("#login_form").submit()
//    return true
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
 
.select input[type=text]{
   width: 100%;
   height: 35px;

} 
.select input[type=password]{
   width: 100%;
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

.naver {
	text-align: center;
}

.kakao {
	text-align: center;
}

input:focus {
	outline: 1px solid #FF5050;
}

</style>

</head>
<body>

<div id="login">

<form action="./login" method="post">

   <h3 style="text-align:center; font-size:30px; color:#FF5060;">로그인 화면</h3><br>
   
   
   
   <div class="select">
   
      <label for="userId" ></label>
      <input type="text" class="boxcolor" id="userId" name="userId"  placeholder="아이디">
      <span id="userid_msg" class="msg" style="color:red"></span>
   </div>
      
   <div class="select">
      <label for="userPw" ></label>
      <input type="password" class="boxcolor" id="userPw" name="userPw" placeholder="비밀번호">
      <span id="userpw_msg" class="msg" style="color:red"></span>
   
   </div>
   
   <div class="select">
      <input type="checkbox">아이디 저장
      <span id="login_error" class="msg" style="color:red"></span>
   </div>
         
   <div class="select">
      <button id="btn" onclick="login()">로그인</button>
   </div>
   
   <div class="naver">
   <c:import url="../login/naverLogin.jsp" />
   </div>
      
   <div class="kakao">
   <c:import url="../login/loginkakao.jsp" />
   </div>
  
      
      
   <div class="select">
      <a href="/member/id.jsp" >아이디 찾기 | </a>
      <a href="/member/pj/pw.jsp" >비밀번호 찾기 | </a>
      <a href="/member/login/join" >회원가입</a>
   </div>

</form>

</div>








<c:import url="../../layout/footer.jsp" />