
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

<h2 style="color: #FF5050; text-align: center;">병원 등록</h2>

	
<form action="./petinfo" method="post" enctype="multipart/form-data">
	
   <div class="select">
      <label for="hospitalName">이름</label>
      <input type="text"  id="petName" name="petName">
   </div>


   <div class="select">
      <label for="code">사업자번호</label>
      <input type="text"  id="type" name="type">
   </div>

   <div class="select">
	<input type="file" id="file" name="file"><br><br>
	  <div id = "input-files"></div>
   </div>
   <div class="select">
	   특수동물 여부<br>
	<input type="checkbox" name="mammalia" value="y">
	<label >포유류</label>
	<input type="checkbox" name="reptile" value="y">
	<label>파충류</label>
	<input type="checkbox" name="rodent" value="y">
	<label>설치류</label>
	<input type="checkbox" name="birds" value="y">
	<label>조류</label>
   </div>

   <div class="select">
      <button id="btn">등록하기</button>
   </div>

</form>





















</div>

<c:import url="../../layout/footer.jsp" />







