<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<c:import url = "../../layout/header.jsp" />


<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
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
			userNo : userno,
			boardNo : boardno,
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
function comcomInput(userno, boardno, refcommentno, organization){
	console.log('Info : ' + userno + ', ' + boardno + ', ' + refcommentno);
	console.log('organization : ' + organization)
	console.log($("#Ccontent" + refcommentno).val())
	console.log($("#Cdepth" + refcommentno).val())
	
	$.ajax({
		type : "get" 
			, url: "./comment"
			, data : { 
				userNo : userno,
				boardNo : boardno,
				refCommentNo : refcommentno,
				organization : organization,
				content : $("#Ccontent" + refcommentno).val(), 
				depth: $("#Cdepth" + refcommentno).val()
			}
			, dataType : "html"
			, success : function(data){
				console.log("AJAX 성공")
				console.log(data)
				$(".comment-area").html(data)
				
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
   
	
// 	$("#refresh").click(function(){
// 		console.log("#refresh click");
		
// 		$.ajax({
// 			type : "get" 
// 				, url: "./comment"
// 				, data : { 
					
// 				}
// 				, dataType : "html"
// 				, success : function(data){
// 					console.log("AJAX 성공")
					
// 					console.log("data", data);
// 				}
// 				, error : function(){
// 					console.log("AJAX 실패")	
// 				}
// 		})
// 	})
	
})
<%-- 댓글 입력시 해당 댓글에 대한 대댓글 입력창 띄우기 --%>
function showComCom(commentno){
	console.log(commentno)
	$("#ComCom" + commentno).toggleClass("displayNone");
	$("#Ccontent" + commentno).focus();
	$("#Ccontent" + commentno).val('');
}

<%-- 댓글 입력창에서 엔터키 입력시 실행 함수 --%>
function enterkey(e) {
	if (e.keyCode == 13) {
		console.log("댓글입력")
		$("#commentInput").click();
	}
}
<%-- 대댓글 입력창에서 엔터키입력시 실행 함수 --%>
function enterkeyPress(e, commentno) {
	if (e.keyCode == 13) {
		console.log("1뎁스 대댓글입력")
		$("#ComComSubmit"+commentno).click();
	}
}

<%----------------------------쪽지보내기 모달창 띄우는 JS ---------- --%>
function closeLayer( obj ) {
	$(obj).parent().parent().hide();
	$('.messageLayer').css({
		"top": 0,
		"left": 0,
		"position": "fixed"
	})
}
function message(e, userid){

// 	console.log(e);
// 	console.log(userid);
	
	var sWidth = window.innerWidth;
	var sHeight = window.innerHeight;

	var oWidth = $('.messageLayer').width();
	var oHeight = $('.messageLayer').height();

	// 레이어가 나타날 위치를 셋팅한다.
	var divLeft = e.clientX + 10;
	var divTop = e.clientY + 5;

	// 레이어가 화면 크기를 벗어나면 위치를 바꾸어 배치한다.
	if( divLeft + oWidth > sWidth ) divLeft -= oWidth;
	if( divTop + oHeight > sHeight ) divTop -= oHeight;

	// 레이어 위치를 바꾸었더니 상단기준점(0,0) 밖으로 벗어난다면 상단기준점(0,0)에 배치하자.
	if( divLeft < 0 ) divLeft = 0;
	if( divTop < 0 ) divTop = 0;

	$('.messageLayer').css({
		"top": divTop,
		"left": divLeft,
		"position": "fixed"
	}).attr({"userid" : userid});
	$('.messageLayer').show();
}

function sendMessage(userid){
	console.log(userid)
	location.href='<%=request.getContextPath() %>/message/message/send?userid=' + userid;
}
<%-----------------------------쪽지모달창 끝 -----------------%>

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
		<div>작성자 : <span class="message" onclick="message(event, '${map.USERID}')" style="cursor: pointer;">${map.USERID }</span></div>
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
	 	<c:if test="${login eq true }">
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
		 </c:if>
	 </div>
		 <hr>
	 <table>
	 	<tr>
	 		<td class = "left-side" > 댓글 <input type="text" id="comment" onkeypress="enterkey(event)"></td>
	 		<td><button type="button" id="commentInput" onclick="commentInput('${userno}', '${map.BOARDNO }')">입력</button></td>
	 		<td id = "refresh" class = "cursor">새로고침</td>
	 	</tr>
	 </table>
	 
	<div class="messageLayer" style="display: none; background: #FFDAD7; color: #FF5050; width: 143px; height: 33px; padding: 10px;">
		<div>
			<span onclick="closeLayer(this)" style="cursor:pointer;font-size:1.5em" title="닫기">X</span>
			<span style="cursor:pointer;font-size:1.5em" onclick="sendMessage($('.messageLayer').attr('userid'))">쪽지보내기</span>
		</div>
	</div>
	 
	 <!-- ajax html -->
	 <div class = "comment-area">
		<c:import url="./comment.jsp"/>
	 </div>
	 <!-- ajax html -->
</div>
<c:import url = "../../layout/footer.jsp" />