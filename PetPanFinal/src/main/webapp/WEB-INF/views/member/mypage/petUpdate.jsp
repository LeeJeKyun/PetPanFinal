
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">

.ur{
	color: #767678;
	text-decoration: none; 
}


#text {
	text-align: center;
	width: 600px;
	margin: 0 auto;
}

input{ 
   border:none;
   
   border-bottom: 2px solid #FFDAD7;
 }

.select{
    width: 420px;
    margin: 15px auto;
    text-align:center;
}

.select input[type=text]{
   width: 200px;
   height: 35px;


} 


#btn{
   background-color: #FFDAD7;
   color : #FF5050;
   border-radius: 7px;
   border-color: #FFDAD7;
   width: 130px;
   height: 35px;
}

.t{
   background-color: #FFDAD7;
   color : #FF5050;
   border-radius: 7px;
   border-color: #FFDAD7;
   width: 80px;
   height: 35px;
}

</style>

<c:import url="./mypage_header.jsp" />

<div class="text">

<br>

<h2 style="color: #FF5050; text-align: center;">동물 수정</h2>

	
<form action="./petUpdate" method="post" enctype="multipart/form-data">
	


   <div class="select">

	<img alt=".." src="<%=request.getContextPath()%>/petfile/${Detail.storedName}" width="90px" height="90px" class="img">
	
	   <div class="select">
	
	
	이름 : <input type = "text" style="color: #263959;" value="${Info.petName}" name = "petName">
		</div>
		
		   <div class="select">
		
	종류 : <input style="color: #52616a;" name = "type" value="${Info.type}">
		</div>
			<input type="hidden" value="${Info.petNo}" name = "petNo">
	</div>
	
   <div class="select">
	<input type="file" id="file" name="petFile"><br><br>
	  <div id = "input-files"></div>
   </div>

   <div class="select">
      <button id="btn">등록하기</button>
   </div>

</form>







</div>

<c:import url="../../layout/footer.jsp" />







