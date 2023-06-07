<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../../layout/header_plain.jsp" />


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


   
<form action="./id" method="post">

   <div class="select">


<c:choose>
	<c:when test="${empty searchId}">
		<p class="mb-4">조회결과가 없습니다.</p>
		<a href='./id' style="text-align: center; font-size: 20px; color: #FF5060;">돌아가기</a>
	</c:when>
	
	<c:otherwise>
		<label for="userName">회원님의 아이디는</label>
		<h4 style="color: #52616a;">아이디 : ${searchId.userId }</h4>
	<div>
   <a href='./pw' style="text-align:center; font-size:20px; color:#FF5060;">비밀번호 찾기</a><br>
   <a href='./login' style="text-align:center; font-size:20px; color:#FF5060;">로그인</a><br>
   </div>
	</c:otherwise>
	
	</c:choose>
</div>
      

    
   


</form>

</div>






<c:import url="../../layout/footer_plain.jsp" />