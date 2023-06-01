
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="./mypage_header.jsp" />

<script type="text/javascript">
$(function() {
	$(".select .yn").on("click", function() {
		if( $(this).is(":checked") ) {
			console.log("cc", $(this))
			
			$(this).parents(".select").find("div").eq(1).show()
		} else {
			$(this).parents(".select").find("div").eq(1).hide()
		}
	})
	
})




</script>

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
.time{
    width: 420px;
    margin: 15px auto;
    text-align:center;
}

.time input[type=text]{
   width: 80px;
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

.select > div:nth-child(2) {
	display: none;
}

</style>








<div class="text">

<br>

<h2 style="color: #FF5050; text-align: center;">병원 등록</h2>

	
<form action="./hospital" method="post">
	
   <div class="select">
      <label for="hospitalName">이름</label>
      <input type="text"  id="hospitalName" name="hospitalName">
   </div><!-- select -->


   <div class="select">
      <label for="hospitalCode">사업자번호</label>
      <input type="text"  id="hospitalCode" name="hospitalCode">
   </div><!-- select -->

   <div class="time">
      <label for="code">영업시간</label>
      
       <input type="text"  id="open" name="open" >  <label style="color: #FF5050; font-size: small;">~ </label>
       <input type="text"  id="close" name="close">
   </div><!-- time -->
	



<!-- <label style="color: #FF5050; font-size: small;">오전 : </label> -->


   <div class="select">
   <div>
	   특수동물 여부<label><input type="checkbox" class="yn"> </label>
   </div><!-- select -->
	   
	<div>
		<label >포유류</label>
		<input type="checkbox" name="mammalia" value="y">
		<label>파충류</label>
		<input type="checkbox" name="reptile" value="y">
		<label>설치류</label>
		<input type="checkbox" name="rodent" value="y">
		<label>조류</label>
		<input type="checkbox" name="birds" value="y">
	</div>
	
	
   </div><!-- select -->

   <div class="select">
      <button id="btn">등록하기</button>
   </div><!-- select -->

</form>

</div>





















<c:import url="../../layout/footer.jsp" />







