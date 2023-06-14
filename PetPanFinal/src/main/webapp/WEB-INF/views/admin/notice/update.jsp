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
#btnWrite{
	width: 100px;
	height: 35px;
	border:none;
	background-color: #4B89DC;
	border-radius: 10px 10px 10px 10px;
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

#deletebt{
	width: 20px;
	height: 25px;
	display: inline-flex;
    flex-direction: column;
    align-items: center; /* 가로 - 중앙으로 */
    justify-content: flex-start;
    
}
#numbox{
	width: 300px;
}
#file{
	text-align: center;
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
			alert("입력해주세요.")
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
// 	$("#btnWrite").click(function(){
// 		console.log("updateContents() 호출")
// 		updateContents();
// 	})
})

function updateContents(){
	//스마트 에디터에 작성된 내용을 textarea#content에 반영한다.
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
}

$(document).on('click', '.deleteReturn', function() {
	console.log( $(this).attr("data-value") )
    var no = $(this).attr("data-value");
    $("input[name=delete]").eq(no).val(-1);
    $(this).closest('tr').remove();
});
  
</script>
<div id = "container">
	<div id = "center-div">
		<h4 style = "margin-top: 20px; margin-bottom: 20px; margin-left: 30px;">공지 수정</h4>
		<form action = "<%=request.getContextPath() %>/admin/notice/update" method = "post" id = "content-form" enctype="multipart/form-data">
			<select name="boardtypeno" class="form-select">
			  <option value="1" ${notice.boardtypeno == '1' ? 'selected' : ''}>품앗이</option>
			  <option value="2" ${notice.boardtypeno == '2' ? 'selected' : ''}>자유</option>
			  <option value="3" ${notice.boardtypeno == '3' ? 'selected' : ''}>중고거래</option>
			  <option value="4" ${notice.boardtypeno == '4' ? 'selected' : ''}>병원</option>
			</select>
			
			
			
			<input type = "text" name = "noticetitle" id = "title" placeholder = " 제목을 입력하세요" required = "required" value="${notice.noticetitle }"></input>
			<br>
			<textarea id = "content" name = "noticecontent" required = "required">${notice.noticecontent }</textarea>
			<input type ="file" name ="file" id = "file" multiple = "multiple" accept = ".gif, .jpg, .png, .jpeg" style = "display: none"><br>
			<div align="right" style="float: right">
			<br>
			<button type = "button" id ="fileBtn" class="btn btn-info" >첨부파일</button>
			</div>
			<div id = "input-files" align="right"></div>
			<div>
			<table id = "file">
			<tr>
				<th width="300px">파일 이름</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="noticeFile" items="${fileList}" varStatus="length">
				<input type = "hidden" value="${noticeFile.fileno }" name="delete">
			<tr>
				<td><input type = "hidden" value="${length.count -1 }" name="save">${noticeFile.originName }</td>
				<td><button type='button' class="deleteReturn">X</button></td>
			</tr>
			</c:forEach>
			</table>
			<div style="float: right">
			<button type = "button"  id = "btnWrite" class="btn btn-info">작성</button>
				<a href = "./view?noticeNo=${notice.noticeno}" class="btn btn-danger">취소</a>
			</div>
			</div>
			
<!-- 			<input type = "hidden" name = "userNo" value = "${userno}"> -->
			<input type = "hidden" name = "userno" value = "1">
			<input type = "hidden" name = "noticeno" value = "${notice.noticeno}">
			
<!-- 			<button type = "submit"  id = "btnWrite" onclick = "updateContents()">작성</button> -->
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