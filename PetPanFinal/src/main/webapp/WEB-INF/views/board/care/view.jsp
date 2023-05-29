<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<c:import url = "../../layout/header.jsp" />

<style>
#fcontainer{
	width: 800px;
	margin: 0 auto;
	margin-top: 200px;
 	margin-bottom: 200px; 
}	
#main{
	widht: 800px;
	height: 500px;
	padding: 20px;
}
.font-options{
	color: #c1c1c1;
	font-size: 0.9em;
}
#report-area{
	text-align: right;
}
#info{
	width: 100%;
	height: 50px;
}
#boardNo{
	display: inline-block;
	margin-left: 20px;
	margin-top: 15px;
}
.center{
	text-align: center;
}
.center-small{
	font-size: 0.8em;
	text-align: center;
}
.small6{
	font-size: 0.6em;
}
#content{

}
table{
	width: 800px;
}
.comment{
	width: 800px;
	margin-top: 20px;
	background-color: #ccc;
}
.left-side{
	width: 50%;
	text-align: left;
}
.right-side{
	width: 50%;
	text-align: right;
}
#name-space{
	padding-top: 20px;
	padding-left: 20px;
	 
}
#delete-comment{
	padding-right: 20px;
	padding-top: 20px;
	font-size: 0.8em;
	cursor: pointer;
}
#date{
	font-size: 0.8em;
}
.main-comment{
	padding-left:  20px;
	padding-right: 20px;
	font-size: 0.8em;
}
.cursor{
 	cursor: pointer;
}
#comment2{
	width: 800px;
	text-align:right;
	margin-right: 20px;
	cursor: pointer;
}
.displayNone{
	display: none;
}
#comment{
	width: 347px;
	height: 22px;
	font-size: 17px;
}
</style>
<script type="text/javascript">
function recommendAjax(boardNo){
// 	console.log('boardNo : ' + boardNo)

	$("#recommendBtn1").toggleClass("displayNone");
	$("#recommendBtn2").toggleClass("displayNone");
	$.ajax({
		type : "get" 
			, url: "./recommend"
			, data : { 
				boardNo : boardNo
			}
			, dataType : "html"
			, success : function(data){
				console.log("AJAX 성공")
				console.log(data)
				
				$("#recommendCnt").html(data);
				
				
				
			}
			, error : function(){
				console.log("AJAX 실패")	
			}
	})
	
}
function commentInput(userno, boardno) {
	console.log("commentInput click!" + userno)
	console.log("commentInput click!" + boardno)
	console.log("commentInput comment : " + $("#comment").val())
	
	$.ajax({
	type : "get" 
		, url: "./comment"
		, data : { 
			userno : userno,
			boardno : boardno,
			content : $("#comment").val()
		}
		, dataType : "html"
		, success : function(data){
			console.log("AJAX 성공")
			console.log(data)
			$(".comment-area").html(data)
			$("#comment").val('');
			
		}
		, error : function(){
			console.log("AJAX 실패")	
		}
	})
}

//대댓글 AJAX
function comcomInput(userno, boardno, refcommentno){
	console.log('Info : ' + userno + ', ' + boardno + ', ' + refcommentno);
	console.log($("#Ccontent" + refcommentno).val())
	console.log($("#Cdepth" + refcommentno).val())
	
	$.ajax({
		type : "get" 
			, url: "./comment"
			, data : { 
				userno : userno,
				boardno : boardno,
				refCommentNo : refcommentno, 
				content : $("#Ccontent" + refcommentno).val(), 
				depth: $("#Cdepth" + refcommentno).val()
			}
			, dataType : "html"
			, success : function(data){
				console.log("AJAX 성공")
// 				console.log(data)
				
			}
			, error : function(){
				console.log("AJAX 실패")	
			}
		})
	
}

$(function(){
	
// 	// 댓글 길이가 50 넘어가면 길이 추가
// 	if( $(".main-comment").css('height').split('px')[0] > 50 ){
// 		var height = 60 + Number($(".main-comment").css('height').split('px')[0]);
// 		$(".comment").css('height', height);
// 	}
	// 본문 길이가 450 넘어가면 길이 추가
	if($("#content").css('height').split('px')[0] > 450){
		var height = 100 + Number( $("#content").css('height').split('px')[0])
		$("#main").css('height', height);
	}
	
	$("#refresh").click(function(){
		console.log("#refresh click");
		
		$.ajax({
			type : "get" 
				, url: "./comment"
				, data : { 
					
				}
				, dataType : "html"
				, success : function(data){
					console.log("AJAX 성공")
					
					console.log("data", data);
				}
				, error : function(){
					console.log("AJAX 실패")	
				}
		})
	})
	
})
function enterkey(e) {
	if (e.keyCode == 13) {
		console.log("댓글입력")
		$("#commentInput").click();
	}
}
function showComCom(commentno){
	console.log(commentno)
	$("#ComCom" + commentno).toggleClass("displayNone");
	$("#Ccontent" + commentno).val('');
}

</script>

<div id = "fcontainer">
	<c:if test="${map.BOARDTYPENO == 1}">
		<h1>품앗이</h1>
	</c:if>
	<c:if test="${map.BOARDTYPENO == 2}">
		<h1>자유게시판</h1>
	</c:if>
	<c:if test="${map.BOARDTYPENO == 3}">
		<h1>중고거래</h1>
	</c:if>
	<div id = "report-area">
		<a href = ""  class = "font-options">게시글 신고</a>
	</div>
	<div id = "line-gray" style = "background-color: gray; width: 100%; height:2px;"></div>
	
	<div id = "info">
		<table>
			<tr>
				<td style = "width:10%;" class = "center">번호</td>
				<td style = "width:50%;" class = "center">제목</td>
				<td style = "width:10%;" class = "center">조회수</td>
				<td style = "width:10%;" class = "center">추천수</td>
				<td style = "width:20%;" class = "center">날짜</td>
			</tr>
			<tr>
				<td style = "width:10%;" class = "center-small">${map.BOARDNO }</td>
				<td style = "width:50%;" class = "center-small">${map.BOARDTITLE }</td>
				<td style = "width:10%;" class = "center-small">${map.HIT }</td>
				<td style = "width:10%;" class = "center-small">${map.RECOMMEND}</td>
				<td style = "width:20%;" class = "center-small">${map.WRITEDATE }</td>
			</tr>
		</table>
	</div>
	<hr>
	<div id = "main">
		<div>작성자 : ${map.USERNAME }</div>
		<div id = "title">
			<h3>${map.BOARDTITLE }</h3>
		 </div>
		 <div id = "content_image">
		 	<c:forEach items="${fileList }" var="image" >
		 		<img width="150px" height="150px" onclick='popup("${image.storedName}")'
		 		alt="아가사진" src="<%=request.getContextPath() %>/upload/${image.storedName}">
		 	</c:forEach>
		 </div>
		 <div id = "content">
		 	${map.CONTENT }
		 <br>
		 
		 </div>
	 </div>
	 <div id="recommend">
	 	<div id="recommend_count">
	 		<span>추천수 : </span><span id="recommendCnt">${map.RECOMMEND }</span>
	 	</div>
	 	<c:choose>
			<c:when test="${isRecommended eq false || empty isRecommended}">
		 		<img src="<%=request.getContextPath() %>/resources/img/emptyheart.png" width="22px"
		 			id="recommendBtn1" style="cursor: pointer;" onclick="recommendAjax(${map.BOARDNO})">
		 		<img src="<%=request.getContextPath() %>/resources/img/pilledheart.png" width="22px" class="displayNone"
		 			id="recommendBtn2" style="cursor: pointer;" onclick="recommendAjax(${map.BOARDNO})">
	 		</c:when>
	 		<c:otherwise>
		 		<img src="<%=request.getContextPath() %>/resources/img/emptyheart.png" width="22px" class="displayNone"
		 			id="recommendBtn1" style="cursor: pointer;" onclick="recommendAjax(${map.BOARDNO})">
		 		<img src="<%=request.getContextPath() %>/resources/img/pilledheart.png" width="22px"
		 			id="recommendBtn2" style="cursor: pointer;" onclick="recommendAjax(${map.BOARDNO})">
			 </c:otherwise>
		 </c:choose>
	 </div>
		 <hr>
	 <table>
	 	<tr>
	 		<td class = "left-side" > 댓글 <input type="text" id="comment" onkeypress="enterkey(event)"></td>
	 		<td><button type="button" id="commentInput" onclick="commentInput('${userno}', '${map.BOARDNO }')">입력</button></td>
	 		<td id = "refresh" class = "cursor">새로고침</td>
	 	</tr>
	 </table>
	 <!-- ajax html -->
	 <div class = "comment-area">
		<c:import url="./comment.jsp"/>
	 </div>
	 <!-- ajax html -->
</div>
<c:import url = "../../layout/footer.jsp" />