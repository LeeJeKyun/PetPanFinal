
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<c:import url="./mypage_header.jsp" />

<script type="text/javascript">
$(function() {
	
	//time 속성 24시간으로 변경
	$('.timepicker').timepicker({
            timeFormat: 'HH:mm',
            interval: 30,
            dynamic: false,
            dropdown: true,
            scrollbar: true
    });
	
	$(".select .yn").on("click", function() {
		if( $(this).is(":checked") ) {
			console.log("cc", $(this))
			
			$(this).parents(".select").find("div").eq(1).show()
		} else {
			$(this).parents(".select").find("div").eq(1).hide()
		}
	})
	
	// 파일 삭제, -1로 value 변경
	$(document).on("click", ".xFile", function(){ 
		console.log("xFile clicked")
		
		console.log( $(this).attr("data-no") )
		var no = $(this).attr("data-no");
		
		console.log( $(".file-line")[no] )
		
		$("input[name=no]").get(no).value = -1;
		
		$(".file-line").eq(no).hide();
		
	})
	// 파일 선택
	$("#file").on('change', function(){
		
		console.log( "clicked");
		console.log(document.getElementById("file").files)
		console.log( document.getElementById("file").files.length )
		
		var file  = document.getElementById("file").files;
		
		
		for(var i = 0; i < file.length; i++){
			console.log( file[i].name );
			$(".file-line").eq(i).remove();
			$("#input-files").append("<tr class = 'file-line' data-no = " + i + ">"
										+ "<td><input type = 'hidden' data-no = " + i +" name = 'no' value = " + i +"></td"
										+ "<td><span class = 'file-name'>" + file[i].name + "</span></td>" 
										+ "<td class = 'xFile' data-no = " + i + " > x</td>" 
										+ "</td>")
			console.log(i)
			console.log(file[i].lastModified)
		}
	})
	// ---------------
	$("#fileBtn").click(function(){
		$("#file").click();
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

#input-files{
	margin-top: 10px;
	margin-bottom: 10px;
	text-align: left; 
	
}

table{
	 border-collapse: collapse;
	 width: 200px;
	 margin: 0 auto;
}
tr:hover{
	background-color: #ccc;
}
.xFile{
	cursor: pointer;
	
}
.file-name{
	width:300px;
	display:  inline-block;
}
</style>

<div class="text">

<br>

<h2 style="color: #FF5050; text-align: center;">병원 등록</h2>

	
<form action="./hospital" method="post" enctype ="multipart/form-data">
	
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
      
       <input type="time" name="open" class = "timepicker">  ~ 
       <input type="time" name="close" class = "timepicker">
<!--       <label for="code">영업시간</label>					 -->
   </div><!-- time -->
	
<!-- <label style="color: #FF5050; font-size: small;">오전 : </label> -->

   <div class="select">
   <div>
	   <label>특수동물 여부<input type="checkbox" class="yn"> </label>
   </div><!-- select -->
	   
	<div>
		<label >포유류
		<input type="checkbox" name="mammalia" value="y"></label>
		<label>파충류
		<input type="checkbox" name="reptile" value="y"></label>
		<label>설치류
		<input type="checkbox" name="rodent" value="y"></label>
		<label>조류
		<input type="checkbox" name="birds" value="y"></label>
	</div>
	
	<!-- 병원 사진 추가 -->
	<!-- 병원 사진 1장만 -->
	<input type ="file" name ="file" id = "file"  accept = ".gif, .jpg, .png, .jpeg" style = "display: none"><br>
	<button type = "button" id ="fileBtn" >첨부파일</button>
	<table id = "input-files"></table>
	
   </div><!-- select -->
	
   <div class="select">
      <button type = "submit" id="btn">등록하기</button>
   </div><!-- select -->

</form>

</div>





















<c:import url="../../layout/footer.jsp" />







