<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
  <script type="text/javascript" src = "<%=request.getContextPath() %>/resources/se2/js/service/HuskyEZCreator.js"></script>
  
  
  <c:import url="../../layout/adminHeader.jsp"/>

<style>
*{
	margin: 0;
	padding: 0;
}
#title{
	width: 790px;
	height: 40px;
	margin-top: 10px;
	margin-bottom: 10px;
}
#content{
	width: 790px;
	height: 500px;
}
#center-div{
	width: 800px;
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
	width:790px;
	display:  inline-block;
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

#deletebt{
	width: 20px;
	height: 25px;
	display: inline-flex;
    flex-direction: column;
    align-items: center; /* 가로 - 중앙으로 */
    justify-content: flex-start;
    
}
</style>

<script type="text/javascript">
$(function(){
	
	//작성 버튼 동작
	$("#btnWrite").click(function(){
		console.log("btnWrite click");
		
		console.log($("#smart_editor2"))
		
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
		
		if(!$("form")[0].checkValidity()) {
			alert("제목을입력해주세요.")
			return
		}
		if($("noticecontent").val() == '' || $("noticecontent").val() == '<p><br></p>') {
			alert("내용을입력해주세요.")
			return
		}
		$("form").submit();
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
										+ "<td class = 'xFile' data-no = " + i + " ><button type='button' id='deletebt' class='btn btn-dark btn-sm'>X</button></td>" 
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

function updateContents(){
	//스마트 에디터에 작성된 내용을 textarea#content에 반영한다.
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
}
</script>
<div id = "container">
	<div id = "center-div">
		<h2 style = "margin-top: 20px; margin-bottom: 20px; margin-left: 30px;">공지사항 등록</h2>
		<form action = "./write" method = "post" id = "content-form" enctype="multipart/form-data">
			<select name = "boardtypeno" class="form-select">
			  <option  value="1">품앗이</option>
			  <option value="2">자유</option>
			  <option value="3">중고거래</option>
			  <option value="4">병원</option>
			</select>
			
			
			
			<br>
			<input type = "text" name = "noticetitle" id = "title" placeholder = " 제목을 입력하세요" required = "required">
			<br>
			<textarea id = "content" name = "noticecontent" required = "required"></textarea>
			<input type ="file" name ="file" id = "file" multiple = "multiple" style = "display: none" accept = ".gif, .jpg, .png, .jpeg"><br>
			<div align="right">
			<br>
			<button type = "button" id ="fileBtn" class="btn btn-info" >첨부파일</button>
			</div>
			<div id = "input-files" align="right" ></div>
			
			<div align="right">
			<button  type="button" id = "btnWrite" class="btn btn-primary">작성</button>
			<a href = "./list" class="btn btn-danger">공지 등록 취소</a>
			</div>
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

</body>
</html>