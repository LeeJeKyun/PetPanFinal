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
	width: 100%;
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
</style>
<script type="text/javascript">
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
	
// 	$("#delete-comment").click(function(){
// 		$.ajax({
// 			type: ""
// 			, url: ""
// 			, data :{
				
// 			}
// 			, dateType: "json"
// 			, success: function(data){
// 				console.log("data ", data)
// 			}
// 			, error: function(data{
// 				console.log("ajax error")
// 			})
// 		})
// 	})
	
})
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
		 	<div ">
		 		<img alt="no img" src="<%=request.getContextPath() %>/upload/${i.storedName}" style = "width: 400px; height: 300px;">
		 	</div>
		 </c:forEach>
		 <br>
		 </div>
	 </div>
		 <hr>
		 <div id = "report-area">
<%-- 		<a href = "./report?boardNo=${map.BOARDNO }&userNo=${userNo}"  class = "font-options">게시글 신고</a> --%>
			<a href = "./report?boardNo=${map.BOARDNO }&userNo=1"  class = "font-options">게시글 신고</a>
		</div>
	 		<div id = "write-comment" >댓글 <textarea id = "write"  placeholder = "댓글을 작성하세요."></textarea>
	 		<button type = "button" id = "writeBtn">작성</button>	
	 		</div>
	 		<div id = "refresh" class = "cursor right-side">새로고침</div>
	 <!-- ajax html -->
	 <div class = "comment-area">
	 		<div class = " f comment">
	 			<div class = "info-space">
	 				<table>
	 					<tr>
	 						<td class = "left-side" id = "name-space">글쓴이${userName }
	 							<span id = "date">(2020.12.13)</span>
	 						</td>
	 						
	 						<td class = "right-side" id = "delete-comment">삭제</td>
	 					</tr>
	 				</table>
	 			</div>
	 			<div class = "main-comment">
	 			<br>
	 				댓그르르르
	 			<br>
	 			<br>
	 			</div>
	 		</div>
	 		<div id = "comment2" class = "small6 cursor">답글달기</div>
	 </div>
	 <!-- ajax html -->
</div>
<c:import url = "../../layout/footer.jsp" />