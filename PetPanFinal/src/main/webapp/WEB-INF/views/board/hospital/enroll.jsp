<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<c:import url = "../../layout/header.jsp" />

<style>
*{
	margin: 0;
	padding: 0;
}
#title{
	width: 730px;
	height: 40px;
	margin-top: 10px;
	margin-bottom: 10px;
}
#content{
	width: 730px;
	height: 500px;
}
#center-div{
	width: 740px;
/* 	height: 500px; */
	margin: 0 auto;
	margin-top: 200px;
}
table{
	 border-collapse: collapse;
}
tr:hover{
	background-color: #ccc;
}
.xFile{
	cursor: pointer;
	
}
.file-name{
	width:700px;
	display:  inline-block;
}
#btnWrite{
	width: 730px;
	height: 40px;
	border:none;
	background-color: #ffdad7;
	border-radius: 10px 10px 10px 10px;
	margin-top: 20px;
	cursor: pointer;
}
#input-files{
	margin-top: 10px;
	margin-bottom: 10px;
}
table{
	display: table;
}
#cancelBtn{
	display: inline-block;
	margin-left: 80%;
	background-color:#ffdad7;
	width: 101px;
	height: 30px;
	border-radius: 10px 10px 10px 10px;
	text-align: center;
	padding-top: 9px;
}
#contentBox{
	width: 730px;
	height: 500px;
}
input[type=number]{
	width: 40px;
	border: none;
}
.timepicker{
	margin-bottom: 20px;
}
/* ------ 모달 css  */
#modal{
	position: relative;
	width: 100%;
	height: 100%;
	z-index: 1;
}
#modal .modal_content{
	width: 300px;
	background: #fff;
	border: 2px solid #666;
}
#modal .modal_layer{
	position: fixed;
	z-index: 1; 
}
</style>

<script type="text/javascript">
$(function(){
	//time 속성 24시간으로 변경
	$('.timepicker').timepicker({
            timeFormat: 'HH:mm',
            interval: 30,
            defaultTime: '09:00',
            startTime: '00:00',
            dynamic: false,
            dropdown: true,
            scrollbar: true
    });
	
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

<div id = "container">
	<div id = "center-div">
		<h2 style = "margin-top: 20px; margin-bottom: 20px; margin-left: 30px;">병원 등록</h2>
		<form action = "./enroll" method = "post" id = "content-form" enctype="multipart/form-data">
			<div>
				<a href = "./list" id = "cancelBtn">등록 취소</a>
			</div>
<!-- 			<div id = contentBox> -->
				<label>오픈 시간: <input type = "time" name = "open" class = "timepicker"></label><br>
				<label>닫는 시간: <input type = "time" name = "close" class = "timepicker"></label>
<!-- 			</div> -->
			
			<div>진료가능한 특수 동물 선택</div>
			<label><input type = "checkbox" name = "mammalia" value = "y">포유류</label>
			<label><input type = "checkbox" name = "reptile" value = "y">파충류</label>
			<label><input type = "checkbox" name = "rodent" value = "y">설치류</label>
			<label><input type = "checkbox" name = "birds" value = "y">조류</label>
			<br>
			<input type ="file" name ="file" id = "file" multiple = "multiple" accept = ".gif, .jpg, .png, .jpeg" style = "display: none"><br>
			<button type = "button" id ="fileBtn" >첨부파일</button>
			<!-- <div id = "input-files"></div> -->
			<table id = "input-files"></table>
			
			<button type = "submit"  id = "btnWrite" >등록</button>
			
		</form>
	</div>
</div>
<c:import url = "../../layout/footer.jsp" />