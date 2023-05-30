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
/* 	height: 500px; */
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
}
.comment3{
	width: 780px;
	text-align: right;
	cursor: pointer;
}
#write{
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
#delete-area{
	text-align: right;
	margin-bottom: 10px;
	margin-right: 20px;
}
.like{
	width: 30px;
	height: 30px;
	cursor: pointer;
}
.comment2-area{
	width: 780px;
	padding-left: 20px;
}
.comment3-area{
	width: 760px;
	padding-left: 40px;
}
#refresh{
	cursor: pointer;
}
</style>
<script type="text/javascript">
$(function(){
	// 본문 길이가 450 넘어가면 길이 추가
	if($("#content").css('height').split('px')[0] > 450){
		var height = 100 + Number( $("#content").css('height').split('px')[0])
		$("#main").css('height', height);
	};
	
	// 게시글 신고 버튼 클릭시
	$("#reportBtn").click(function(){
		var userno = '<%=session.getAttribute("userno")%>';
        if(userno=="null"){ 
        	alert("로그인을 해주세요.")
			return;
		}
		console.log("#reportBtn click")
		window.open("./reportPopup?boardNo="+${map.BOARDNO }, "신고", "width=400, height=500, resizable=no");
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
			type: "get"
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
	//  일반 댓글 작성
	$("#writeBtn").click(function(){
		var userno = '<%=session.getAttribute("userno")%>';

        if(userno=="null"){ 
        	alert("로그인을 해주세요.")
			return;
		}
        if(  "" == $("#write").val() ){
        	alert("입력을 해주세요");
        	return;
        }
		$.ajax({
			type: "get"
			, url: "./comment/write"
			, data: {
				"boardNo": ${map.BOARDNO }
				, "depth": "1"
				, "content": $("#write").val()
			}
			, dataType : "html"
			, success: function(data){
						$("#write").val("");
						console.log(data)
						console.log(data.content)
						console.log(data.userNo);
						
						updateComment(data);
			}
			, error: function(){
				console.log("댓글 ajax 실패");
			}
		})
	});
	
})
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
function updateComment(c){
	
	var html = "<div class = 'comment-area'>";
		html += "	<div class = 'f comment'>"; 
		html += "		<div class = 'info-space'>"; 
		html += "			<table>" 
		html += "			<tr>" 
		html += "				<td class = 'left-side name-space'>글쓴이"   
		html += "					<span class = 'date'>(2020.12.13)" + ${c.writeDate } + "</span>"				 
		html += "					<span class  = 'comment1 small6'>답글달기</span>"				 
		html += "				</td>"				 
		html += "				<td class = 'right-side' data-boardNo = " + ${c.boardNo} +">"				 
		html += "						<span class = 'delete-comment'>삭제</span></td>"				 
		html += "			</tr>	"			 
		html += "		</table>"				 
		html += "	</div>"				 
		html += "	<div class = 'main-comment'>"				 
		html += "			<br>"				 
		html += 			 + c.content				 
		html += "			<br>"				 
		html += "			<br>"				 
		html += "			</div>"
		html += "		</div>"				 
		html += "</div>"				 
		
	$("#commentsBox").append(html);
}
</script>
<div id = "fcontainer">
	<c:if test="${map.BOARDTYPENO == 2}">
		<h1><a href = "../board">자유게시판</a></h1>
	</c:if>
	<c:if test="${map.BOARDTYPENO == 3}">
		<h1><a href = "../board?category=3">중고거래</a></h1>
	</c:if>
	
	<!-- 게시글의 userno과 session의 userno이 같으면 -->
<%-- 	<c:if test="${map.USERNO == userno }"> --%>
		<div id = "delete-area">
			<a href = "./delete/board?boardNo=${map.BOARDNO }"  id = "delete-board">게시글 삭제</a>
		</div>
<%-- 	</c:if> --%>
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
		<div>작성자 :  ${map.USERNAME }</div>
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
		 
		 
	 </div>
		 <hr>
		 <div id = "report-area">
			<a href = "#"  id = "reportBtn" class = "font-options">게시글 신고</a>
		</div>
	 		<div id = "write-comment" >댓글 <textarea id = "write"  placeholder = "댓글을 작성하세요."></textarea>
	 		<button type = "button" id = "writeBtn">작성</button>	
	 		</div>
	 		<div class = "right-side"><span id = "refresh">새로고침</span></div>
	 <!-- ajax html -->
	 <!-- 댓글 -->
	 <div id = "commentsBox"></div>
		<!-- 	 <!-- ajax html --> 
</div>
<c:import url = "../../layout/footer.jsp" />