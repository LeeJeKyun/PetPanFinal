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
.xFile{
	cursor: pointer;
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
	background-color:#ffdad7;
	width: 101px;
	height: 30px;
	border-radius: 10px 10px 10px 10px;
	text-align: center;
	padding-top: 9px;
}
#fileBtn{
	
width: 101px;
height: 30px;
font-size: 15px;
background-color: #f5cbcb;
border-radius: 10px 10px 10px 10px / 10px 10px 10px 9px;
border: none;
color: #FF5050;
cursor: pointer;
	
}
#modal {
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.4);
  display: none;
}
.modal-content {
  background-color: #fefefe;
  margin: 15% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 25%;
  height: 17%;
  border-radius: 10px 10px 10px 10px;
}
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}
.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
#submit, #cancel{
	width: 101px;
	height: 30px;
	font-size: 15px;
	background-color: #f5cbcb;
	border-radius: 10px 10px 10px 10px / 10px 10px 10px 9px;
	border: none;
	color: #FF5050;
	cursor: pointer;
}
</style>

<script type="text/javascript">
$(function(){
	
	// 모달창 로직
	const modal = document.getElementById("modal");
	const openModalBtn = document.getElementById("btnWrite");
	const submitModalBtn = document.getElementById("submit");
	const closeModalBtn = document.getElementById("cancel")
	// 모달창 열기
	openModalBtn.addEventListener("click", () => {
	  modal.style.display = "block";
	  document.body.style.overflow = "hidden"; // 스크롤바 제거
	});
	// 모달창 닫기
	closeModalBtn.addEventListener("click", () => {
	  modal.style.display = "none";
	  document.body.style.overflow = "auto"; // 스크롤바 보이기
	});
	submitModalBtn.addEventListener("click", () => {
		$("form").submit();
	})
	
	
	
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
// 		$("form").submit();
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
			//전체 띄운 목록 삭제
			$(".file-line").eq(i).remove();
			//파일리스트 div에 추가하기
			$("#input-files").append("<div class = 'file-line' data-no = "+ i + ">"
													+ "<input type = 'hidden' data-no = " + i +" name = 'no' value = " + i +">"
													+ file[i].name 	
													+ "<span class = 'xFile' data-no = " + i + " style = 'cursor: pointer;'> x </span>" 
													+ "</div>")
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

<%--게시글에 포핟된 파일을 삭제하는 메소드 --%>
function fileDeleteAjax(fileno, e){
	console.log(fileno);
	console.log(e.target);
	
	$.ajax({
		type : "get" 
			, url: "./fileDelete"
			, data : {
				fileno : fileno, 
			}
			, dataType : "html"
			, success : function(data){
				console.log("AJAX 성공")
				console.log(data);
				$(e.target).parent('.existFile').remove();
				
				
			}
			, error : function(){
				console.log("AJAX 실패")
			}
		})
	
}
</script>


<!-- 모달창 -->
<div id="modal">
  <div class="modal-content">
    <h2>정말 게시글을 수정하시겠습니까?</h2><br>
    <div style="text-align: center;">
	    <button id="submit">예</button>
	    <button id="cancel">아니오</button>
    </div>
  </div>
</div>


<div id = "container">
	<div id = "center-div">
		<h2 style = "margin-top: 20px; margin-bottom: 20px; margin-left: 30px;">품앗이 글수정</h2>
		<form action = "<%=request.getContextPath() %>/board/care/update" method = "post" id = "content-form" enctype="multipart/form-data">
			<input type="hidden" name="boardNo" value="${map.BOARDNO }">
			<div style="text-align: right;">
				<a href = "<%=request.getContextPath() %>/board/care/list" id = "cancelBtn">목록으로</a>
			</div>
			<input type = "text" name = "boardTitle" id = "title" placeholder = "제목을 입력하세요" required = "required" value="${map.BOARDTITLE }">	
			
			<textarea id = "content" name = "content" required = "required" >${map.CONTENT }</textarea>
			<input type ="file" name ="file" id = "file" multiple = "multiple" style = "display: none" accept="image/*"><br>
			<button type = "button" id ="fileBtn" >사진 업로드</button>
			<div id = "input-files">
			
				<c:forEach items="${boardFile }" var="file" >
					<div class = 'existFile' data-no =>
						${file.storedName } 	
						<span style = 'cursor: pointer;'onclick="fileDeleteAjax(${file.fileno}, event)"> x </span>
					</div>
				</c:forEach>
			</div>
			
			<input type = "hidden" name = "userNo" value = "${map.USERNO }">
			
			<button type = "button"  id = "btnWrite" >수정</button>
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