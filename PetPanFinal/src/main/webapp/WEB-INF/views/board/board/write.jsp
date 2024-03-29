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
	margin-left: 60%;
	background-color:#ffdad7;
	width: 101px;
	height: 30px;
	border-radius: 10px 10px 10px 10px;
	text-align: center;
	padding-top: 9px;
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
	
	//작성 버튼 동작
	$("#btnWrite").click(function(){
		console.log("btnWrite click");
		
		console.log($("#smart_editor2"))
		
// 		if($("#title").val() == '') {
// 			alert("required")
			
// 			return
// 		}
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
		
		if(!$("form")[0].checkValidity()) {
			alert("입력해주세요.")
			return
		}
		if($(content.value).text() == '') {
			alert("required c")
			return
		}
		var confirmV;
		confirmV = confirm("게시글을 작성하시겠습니까?");
		if(confirmV){
			$("form").submit();
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
//			$("#input-files").append("<div class = 'file-line' data-no = "+ i + ">"
//													+ "<input type = 'hidden' data-no = " + i +" name = 'no' value = " + i +">"
//													+ file[i].name 
//													+ "<span class = 'xFile' data-no = " + i + " style = 'cursor: pointer;'> x </span>" 
//													+ "</div>")
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
// 	$("#btnWrite").click(function(){
// 		console.log("updateContents() 호출")
// 		updateContents();
// 	})
})

function updateContents(){
	//스마트 에디터에 작성된 내용을 textarea#content에 반영한다.
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
}
</script>

<div id = "container">
	<div id = "center-div">
		<h2 style = "margin-top: 20px; margin-bottom: 20px; margin-left: 30px;">글 작성중</h2>
		<form action = "./write" method = "post" id = "content-form" enctype="multipart/form-data">
			<div>
				<label>자유게시판<input type = "radio" name = boardTypeNo value = "2"></label>
				<label>중고거래<input type = "radio" name = "boardTypeNo" value = "3"></label>
				<a href = "../board" id = "cancelBtn">글쓰기 취소</a>
			</div>
			<input type = "text" name = "boardTitle" id = "title" placeholder = "제목을 입력하세요" required = "required">	
			
			<textarea id = "content" name = "content" required = "required"></textarea>
			<input type ="file" name ="file" id = "file" multiple = "multiple" accept = ".gif, .jpg, .png, .jpeg" style = "display: none"><br>
			<button type = "button" id ="fileBtn" >첨부파일</button>
			<!-- <div id = "input-files"></div> -->
			<table id = "input-files"></table>
			
			<button type = "button"  id = "btnWrite" >작성</button>
		</form>
	</div>
</div>

<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", //에디터가 적용될 <textarea>의 id 속성값
	sSkinURI: "<%=request.getContextPath() %>/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
});

</script>
<c:import url = "../../layout/footer.jsp" />