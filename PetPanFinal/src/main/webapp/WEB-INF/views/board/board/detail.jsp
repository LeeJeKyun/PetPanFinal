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
	position: relative;
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
table{
	width: 800px;
}
.comment{
	width: 800px;
	margin-top: 20px;
/*  	background-color: #ccc;  */
	padding-bottom: 20px;
}
.left-side{
	width: 50%;
	text-align: left;
}
.right-side{
	width: 100%;
	text-align: right;
}
.name-space{
	padding-top: 20px;
	padding-left: 20px;
	 
}
.delete-comment{
	padding-right: 20px;
	padding-top: 20px;
	font-size: 0.8em;
	cursor: pointer;
}
.update-comment{
	padding-right: 20px;
	padding-top: 20px;
	font-size: 0.8em;
	cursor: pointer;
}
.date{
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
.comment2{
	width: 800px;
	text-align:right;
	margin-right: 20px;
	cursor: pointer;
		font-size: 0.6em;
	
}
.comment3{
	width: 780px;
	text-align: right;
	cursor: pointer;
	font-size: 0.6em;
	
}
.write{
	width: 99%;
	resize: vertical;
}
#delete-board{
	display: inline-block;
	color: black;
	background-color: #ffdad7;
	width:100px;
	height: 34px;
	border-radius: 10px 10px 10px 10px;
	text-align: center;
	padding-top: 10px;
}
#update-board{
	display: inline-block;
	color: black;
	background-color: #ffdad7;
	width:100px;
	height: 34px;
	border-radius: 10px 10px 10px 10px;
	text-align: center;
	padding-top: 10px;
}
#set-area{
	text-align: right;
	margin-bottom: 10px;
	margin-right: 20px;
}
.like{
	width: 30px;
	height: 30px;
	cursor: pointer;
}
#like-area{
	position: absolute;
	bottom: 0;
}
.comment2-area{
	width: 720px;
	padding-left: 80px;
/* 	background-color: #ff50602e; */
	padding-bottom: 20px;
}
.comment3-area{
	width: 680px;
	padding-left: 120px;
	padding-bottom: 20px;
}
#refresh{
	cursor: pointer;
}
#writeCommentStyle{
	font-size: 0.8em;
	text-decoration: underline;
	cursor: pointer;
}
.report-comment{
	cursor: pointer;
	font-size: 0.6em;
	text-decoration: underline; 
	color: black;
}
.comment-heart{
	cursor: pointer;
}
</style>
<script type="text/javascript">
var reportBoard;
var reportcommentW;
window.onunload = function(){
	reportBoard.close();
	reportcommentW.close();	
}
window.onload = function(){
	$("#refresh").trigger("click");
}
$(function(){
	// 본문 길이가 450 넘어가면 길이 추가
	if($("#content").css('height').split('px')[0] > 450){
		var height = 120 + Number( $("#content").css('height').split('px')[0])
		$("#main").css('height', height);
	};
	
	// 게시글 신고 버튼 클릭시
	$("#reportBtn").click(function(){
		var userno = '<%=session.getAttribute("userno")%>';
		var confirmV;
        if(userno=="null"){ 
        	alert("로그인을 해주세요.")
			return;
		}
        confirmV = confirm("이 게시글을 신고하시겠습니까?")
        if(!confirmV){
        	return;
        }
		console.log("#reportBtn click")
		reportBoard = window.open("./reportPopup?boardNo="+${map.BOARDNO }, "신고", "width=400, height=500, resizable=no");
	});
	
	$("#like-area").on("click", ".like", function(){
		console.log("heart clicked");
		var userno = '<%=session.getAttribute("userno")%>';

        if(userno=="null"){ 
        	alert("로그인을 해주세요.")
			return;
		}
		$.ajax({
			type : "get" 
				, url: "./recommend"
				, data : { 
					"boardNo": ${map.BOARDNO }
				}
				, dataType : "json"
				, success : function(data){
					console.log("AJAX 성공")
					
					console.log("data1", data.like);
					console.log("data2", data.recommend)
					
					likeChange(data.like, data.recommend);
					
				}
				, error : function(){
					console.log("AJAX 실패")	
				}
		})
	});
	
	
	// 새로고침
	$("#refresh").click(function(){
		console.log("refresh clicked");
		
		$.ajax({
			type: "post"
			, url: "./comment"
			, data: {
				"boardNo": ${map.BOARDNO }
			}
			, dataType : "html"
			, success: function(res){
				$("#commentsBox").html(res);
			}
			, error: function(){
				console.log("댓글 ajax 실패");
			}
		})
	});
})
//댓글 신고
function reportComment(commentNo){
	console.log("reportComment clicked")
	
	var userno = '<%=session.getAttribute("userno")%>';
    if(userno=="null"){ 
    	alert("로그인을 해주세요.")
		return;
	}
	reportCommentW = window.open("./reportComment?commentNo=" + commentNo, "신고", "width=400, height=500, resizable=no");
}
//   댓글 작성
function writeComment(commentNo){
	var userno = '<%=session.getAttribute("userno")%>';
	
	console.log(commentNo)
	console.log("textarea val" + $("textarea[data-commentNo='" + commentNo +"']").val() );
	
	var comment = $("textarea[data-commentNo='" + commentNo +"']").val();
	
    if(userno=="null"){ 
    	alert("로그인을 해주세요.")
		return;
	}
    if("" == $("textarea[data-commentNo='" + commentNo +"']").val() ){
    	alert("입력을 해주세요");
    	return;
    }
    console.log("boardNo " + ${map.BOARDNO })
    console.log("commentNo " + commentNo)
    console.log("content " + $("textarea[data-commentNo='" + commentNo +"']").val())
    
	$.ajax({
		type: "get"
		, url: "./comment/write"
		, data: {
			"boardNo": ${map.BOARDNO }
			, "commentNo": commentNo 
			, "content": $("textarea[data-commentNo='" + commentNo +"']").val()
		}
		, dataType : "json"
		, success: function(data){
					$("textarea[data-commentNo='" + commentNo +"']").val()
					console.log(data)
					console.log(data.content)
					console.log(data.userNo);
					
					$("#writeArea").remove();
					
					$("#refresh").trigger("click");
		}
		, error: function(){
			console.log("댓글 ajax 실패");
		}
	})
}
//댓글 좋아요
function commentLike(commentNo, status){
    	
   	console.log("comment heart clicked");
   	console.log("comment heart " + commentNo);
   	
   	console.log($(".comment-heart[data-index='" + status + "']"))

   	var $div = $(".comment-like-area[data-index='" + status +"']")
   	console.log($div)
   	var userno = '<%=session.getAttribute("userno")%>';

    if(userno=="null"){ 
      	alert("로그인을 해주세요.")
		return;
	}
       
    $.ajax({
    	type : "get"
    	, url : "./comment/like"
    	, data :{
    		commentNo: commentNo
    	}
    	, dataType : "json"
    	, success : function(data){
    		console.log("댓글 좋아요 ajax 성공")
    		console.log(data)
    		console.log(data.flag)
    		console.log(data.count)
    		
    		commentLikeChange(data, $div, status, commentNo)
    	}
    	, error : function(){
    		console.log("댓글 좋아요 ajax 실패")
    	}
    })	
}
//댓글 좋아요 추가/취소
function commentLikeChange(data, $div, c, commentNo){
	console.log($div)
	console.log("댓글 좋아요 추가/취소")
	var html;
	if(data.flag){
		html = "<div style = 'display: inline-block'>";
		html +=			"<img class = 'comment-heart'" ;
		html +=			"onclick = 'commentLike(" + commentNo +"," + c +")'"; 
		html +=			"alt='좋아요'" ;
		html +=			"src='<%=request.getContextPath()%>/resources/img/fillheart.png'>";
		html +=	"</div>";
		
	}else{
		html = "<div style = 'display: inline-block'>";
		html +=			"<img class = 'comment-heart'" ;
		html +=			"onclick = 'commentLike(" + commentNo +"," + c +")'"; 
		html +=			"alt='좋아요'" ;
		html +=			"src='<%=request.getContextPath()%>/resources/img/emptyheart.png'>";
		html +=	"</div>";
		
	}
	html += "<div style = 'display: inline-block'><span>" + data.count + "</span></div>"
	$div.html(html)
}
//댓글 삭제
function deleteComment(commentNo){
	
	$.ajax({
		type: "get"
		, url: "./comment/delete"
		, data :{
			commentNo: commentNo
		}
		,	success : function(){
	
			console.log("ajax 댓글 삭제 성공")
			$(".main-comment[data-commentNo='" + commentNo +"']").html("작성자가 삭제한 댓글입니다.");	
			
		}
		,	error : function(){
			console.log("댓글 삭제 실패")	
		}
	})
}

function likeChange(like, count){
	console.log("likeChange() 호출됨");

	if(like){
		var html = "";
		html += "<div style = 'padding-left: 16px;'>";
		html += "  <img alt='좋아요'  class = 'like' src='<%=request.getContextPath() %>/resources/img/fillheart.png'>"
		html += "</div>"
		html += "<div>추천수 : " + count + "</div>";
		
		console.log(html);			 
		$("#like-area").html(html);
	}else{
		var html = "";
		html += "<div style = 'padding-left: 16px;'>";
		html += "  <img alt='좋아요'  class = 'like' src='<%=request.getContextPath() %>/resources/img/emptyheart.png'>"
		html += "</div>"
		html += "<div>추천수 : " + count + "</div>";

	 	console.log(html);
		$("#like-area").html(html);
	}
	
};
//댓글 쓰기창 띄우기
function commentShow(commentNo) {
	console.log(commentNo)
	
	if( document.getElementById("writeArea") ){
		$("#writeArea").remove();
	}else{
		var html = ""
		
		html +=	"<div id = writeArea>"
		html += "	댓글 <textarea class = 'write' placeholder = '댓글을 작성하세요.' data-commentNo='" + commentNo + "'/>"
		html +=	"	<button type = 'button' onclick = 'writeComment("+ commentNo +")' id = 'writeBtn'>작성</button>"
		html +=	"</div>"
			
		if( commentNo != 0){
		console.log( $("div[data-commentNo='"+ commentNo +"']") )
			
		$("div[data-commentNo='"+ commentNo +"']").next().html(html)
		}else{
			$(".write-comment").html(html)
		}
	}
}


</script>
<div id = "fcontainer">
	<c:if test="${map.BOARDTYPENO == 2}">
		<h1><a href = "../board">자유게시판</a></h1>
	</c:if>
	<c:if test="${map.BOARDTYPENO == 3}">
		<h1><a href = "../board?category=3">중고거래</a></h1>
	</c:if>
	
		<div id = "set-area" style ="displya: inline-block">
			<a href = "<%= request.getContextPath()%>/board/board">목록으로</a>
		<!-- 게시글의 userno과 session의 userno이 같으면 -->
		<c:if test="${map.USERNO == userno }">
			<a href = "./delete/board?boardNo=${map.BOARDNO }"  id = "delete-board">게시글 삭제</a>
			<a href = "./update?boardNo=${map.BOARDNO }" id = "update-board">게시글 수정</a>
		</c:if>
		</div>
		
	<div id = "line-gray" style = "background-color: gray; width: 100%; height:2px;"></div>
	
	<div id = "info">
		<table>
			<tr>
				<th style = "width:10%;" class = "center">번호</th>
				<th style = "width:50%;" class = "center">제목</th>
				<th style = "width:10%;" class = "center">조회수</th>
				<th style = "width:10%;" class = "center">추천수</th>
				<th style = "width:20%;" class = "center">날짜</th>
			</tr>
			<tr>
				<td style = "width:10%;" class = "center-small">${map.BOARDNO }</td>
				<td style = "width:50%;" class = "center-small">${map.BOARDTITLE }</td>
				<td style = "width:10%;" class = "center-small">${map.HIT }</td>
				<td style = "width:10%;" class = "center-small">${map.RECOMMEND}</td>
				<td style = "width:20%;" class = "center-small"><fmt:formatDate value="${map.WRITEDATE }" pattern = "yyyy.MM.dd"/> </td>
			</tr>
		</table>
	</div>
	<hr>
	<div id = "main">
		<div>작성자 :  ${map.USERNICK }</div>
		<div id = "title">
			<h3>${map.BOARDTITLE }</h3>
		 </div>
		 <div id = "content">
			${map.CONTENT }
		 <br>
		 <c:forEach var = "i" items = "${list }">
		 	<div>
		 		<img alt="no img" src="<%=request.getContextPath() %>/upload/${i.storedName}" style = "width: 400px; height: 300px;">
		 	</div>
		 </c:forEach>
		 <br>
		 
		 </div>
		 <div id = "like-area"> 
			 <c:if test="${like }">
			 	<div style = "padding-left: 16px;"><img alt="like"  class = "like" src="<%=request.getContextPath() %>/resources/img/fillheart.png" ></div>
				 <div>추천수 : ${map.RECOMMEND}</div>
			 </c:if>
			 
			 <c:if test="${!like }">
			 	<div style = "padding-left: 16px;"><img alt="like"  class = "like" src="<%=request.getContextPath() %>/resources/img/emptyheart.png"></div>
			 	<div>추천수 : ${map.RECOMMEND}</div>
			 </c:if>
			 </div>
		 
	 </div>
		 <hr>
		 <div id = "report-area">
			<a href = "#"  id = "reportBtn" class = "font-options">게시글 신고</a>
		</div>
			<span id = "writeCommentStyle" onclick = "commentShow(0)">댓글쓰기</span>
	 		<div class = "write-comment">	
	 		</div>
	 		
	 		<div class = "right-side"><span id = "refresh">새로고침</span></div>
	 <!-- ajax html -->
	 <!-- 댓글 -->
	 <div id = "commentsBox"></div>
		<!-- 	 <!-- ajax html --> 
</div>
<c:import url = "../../layout/footer.jsp" />