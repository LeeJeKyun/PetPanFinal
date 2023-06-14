
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<c:import url="../../layout/header_plain.jsp" />
<script type="text/javascript">

$(function(){
	   
	   //유효성 검사 -- 버튼 눌렀을때 push 알람 띄우기
	   $("form").submit(function(){
	      
	      //validate가 틀렸을경우 리턴값 false
	      //validate - 아이디,비밀번호 : 정규화 
	      if(validate()){
	         console.log("click") 
	         return false
	      }
	      return true
	   })
	   
	   
	   //아이디 입력란 클릭시 밑에 메시지 없애기
	   $("#userId").focus(function(){
	      $("#userId_msg").html("")   
	   })
	   //비밀번호 입력란 클릭시 밑에 메시지 없애기
	   $("#userPw").focus(function(){
	      $("#userPw_msg").html("")   
	   })
	})

	//아이디,비밀번호 정규화가 틀렸을 경우 버튼푸시 실패
	function validate(){

	   //로그인버튼 눌렀을 때 아이디 유효성검사
	   if($('#userId').val()==''){
	      $('#userId_msg').html("아이디를 입력해주세요")
	      return true;
	   }
	   
	   //로그인버튼 눌렀을 때 비밀번호 유효성검사
	   if($('#userPw').val()==''){
	      $('#userPw_msg').html("비밀번호를 입력해주세요")
	      return true;
	   }
	   return false;
	}
	
	



//     $(document).ready(function() {
    	
//         var message = "[[${msg}]]";
        
//         if (message != "") {
//             alert(message);
//         }
//     })


// 아이디 저장하기 (쿠키) 7일간 저장됨
$(document).ready(function(){
	  
    var key = getCookie("key");
    $("#userId").val(key); 
      
    if($("#userId").val() != ""){
        $("#rememberId").attr("checked", true); 
    }
      
    $("#rememberId").change(function(){ 
        if($("#rememberId").is(":checked")){ 
            setCookie("key", $("#userId").val(), 7); 
        }else{ 
            deleteCookie("key");
        }
    });
      
    
    $("#userId").keyup(function(){ 
        if($("#rememberId").is(":checked")){ 
            setCookie("key", $("#userId").val(), 7); 
        }
    });
});


function setCookie(cookieName, value, exdays){
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
    document.cookie = cookieName + "=" + cookieValue;
}
  
function deleteCookie(cookieName){
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}
  
function getCookie(cookieName) {
    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if(start != -1){
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
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
      <input type="text" class="boxcolor" id="userId" name="userId" placeholder="아이디">
      <span id="userId_msg" class="msg" style="color:red"></span>
   </div>
      
   <div class="select">
      <label for="userPw" ></label>
      <input type="password" class="boxcolor" id="userPw" name="userPw" placeholder="비밀번호">
      <span id="userPw_msg" class="msg" style="color:red">${msg } ${errMsg }</span>
   </div>
   
   
   
   <div class="select">
      <input type="checkbox" name="rememberId" id="rememberId"  >아이디 저장
      <span id="login_error" class="msg" style="color:red"></span>
   </div>
    
        


   <div class="select">
      <button id="btn" >로그인</button>
   </div>
   
   <div class="naver">
   <c:import url="./naverLogin.jsp" />
   </div>
      
   <div class="kakao">
   <c:import url="../login/kakaoLogin.jsp" />
   </div>
  
      
      
   <div class="select">
      <a href="/member/login/id" >아이디 찾기 | </a>
      <a href="/member/login//pw" >비밀번호 찾기 | </a>
      <a href="/member/login/join" >회원가입</a>
   </div>

</form>

</div>








<c:import url="../../layout/footer.jsp" />