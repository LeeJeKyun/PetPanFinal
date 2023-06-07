<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../../layout/header_plain.jsp" />

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script>

$(function(){
   
   //이름 입력란 클릭시 밑에 메시지 없애기
   $("#userName").focus(function(){
      $("#userName_msg").html("")   
   })
   //전화번호 입력란 클릭시 밑에 메시지 없애기
   $("#phone").focus(function(){
      $("#phone_msg").html("")   
   })
   
   $("#id_check").on("click", function(){
      
      var userName = $("#userName").val();
      var phone = $("#phone").val();
      
      if( userName == '' ) {
         $('#userName_msg').html("이름을 입력해주세요")
         return
      }
      
      if( phone == '' ) {
         $('#phone_msg').html("전화번호을 입력해주세요")
         return
      }
      
      
      console.log(userName)
      console.log(phone)
      
   })
})
 
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


	<div>
   <a href='./id' style="text-align:center; font-size:20px; color:#FF5060;">아이디 찾기</a><br>
   <a href='./pw' style="text-align:center; font-size:20px; color:#FF5060;">비밀번호 찾기</a><br>
   </div>
   
<form action="./id" method="post">

   <div class="select">
   	
      <label for="userName" >이름</label>
      <input type="text" class="boxcolor" id="userName" name="userName">
      <div id="clear"></div>
      <span id="userName_msg" class="msg" style="color:red"></span>
   </div>
      
   <div class="select">
      <label for="phone">전화번호</label>
      <input type="text" class="boxcolor" id="phone" name="phone">
      <div id="clear"></div>
      <span id="phone_msg" class="msg" style="color:red"></span>
      
   </div>

    
   <div class="select">
      <button id="id_check" >아이디 찾기</button>
   </div>
   


</form>

</div>






<c:import url="../../layout/footer_plain.jsp" />