<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<c:import url = "../../layout/header.jsp" />


<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f020491197bfb81f37566adfe6725a03"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f020491197bfb81f37566adfe6725a03&libraries=clusterer"></script>
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
</style>
<script type="text/javascript">
//추천 AJAX
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
//댓글 AJAX
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
	var login = '<%=session.getAttribute("login") %>';
	console.log(login)
	if(login != 'true'){
		alert("로그인을 해주세요.")
		$('.messageLayer').hide();
		return;
	}
	console.log(userid)
	window.open("<%=request.getContextPath() %>/message/message/send?receiveuserid=" + userid, '쪽지', "width=400, height=500, resizable=no");
	$('.messageLayer').hide();
}
<%-----------------------------쪽지모달창 끝 -----------------%>
$(function(){
	
	// 모달창 로직
	const modal = document.getElementById("modal");
	const btnDelete = document.getElementById("btnDelete");
	const submitModalBtn = document.getElementById("submit");
	const closeModalBtn = document.getElementById("cancel")
	// 모달창 열기
	btnDelete.addEventListener("click", () => {
	  modal.style.display = "block";
	  document.body.style.overflow = "hidden"; // 스크롤바 제거
	});
	// 모달창 닫기
	closeModalBtn.addEventListener("click", () => {
	  modal.style.display = "none";
	  document.body.style.overflow = "auto"; // 스크롤바 보이기
	});
	submitModalBtn.addEventListener("click", () => {
		location.href="./delete?boardNo=" + ${map.BOARDNO}
	})
	
})

<%-- 게시글 신고 함수 --%>
function report() {
	if(${empty userno}){
		alert("로그인을 해주세요.")
		return
	}
	
	window.open("./reportPopup?boardNo="+${map.BOARDNO}, "신고", "width=400, height=500, resizable=no");
}
function reportComment(commentNo){
	if(${empty userno}){
		alert("로그인을 해주세요.")
		return
	}
	window.open("./reportComment?commentNo="+commentNo, "신고", "width=400, height=500, resizable=no" );
}
</script>

<div id = "fcontainer" >
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
		<a href="./list">
			<button type="button" style="width: 85px; height: 33px; font-size: 17px; font-weight: bold; background-color: #f5cbcb; border-radius: 10px 10px 10px 10px / 10px 10px 10px 9px; border: none; color: #FF5050; cursor: pointer;">
				목록으로
			</button>
		</a>
	<c:if test="${userno eq map.USERNO }">
		<button id="btnDelete" type="button" style="width: 120px; height: 33px; font-size: 17px; font-weight: bold; background-color: #f5cbcb; border-radius: 10px 10px 10px 10px / 10px 10px 10px 9px; border: none; color: #FF5050; cursor: pointer;">
			게시글 삭제
		</button>
	</c:if>
	</div>
	<br>
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
		 <div>작성자와 회원님 사이의 거리 :
		 		<c:if test="${distance lt 1000 }">
		 			<fmt:formatNumber value="${distance }" type="number" pattern="###"/>m
		 		</c:if>
		 		<c:if test="${distance gt 1000 }">
				 	<fmt:formatNumber value="${distance / 1000 }"  pattern=".000"/>km
				</c:if>
		 </div>
	 </div>
		 <div id="map" style="width:797px;height:266px;"></div>
		 <script type="text/javascript">
			 var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
			 var options = { //지도를 생성할 때 필요한 기본 옵션
			 	center: new kakao.maps.LatLng(${writerMember.longitude}, ${writerMember.latitude}), //지도의 중심좌표.
			 	level: 7 //지도의 레벨(확대, 축소 정도)
			 };
			
			 var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
			 
			 var marker = new kakao.maps.Marker({
				 	map: map,
				    position: new kakao.maps.LatLng( ${writerMember.longitude}, ${writerMember.latitude} )
				});
			 
			
			 var myMarker = new kakao.maps.Marker({
				 	map: map,
				    position: new kakao.maps.LatLng( ${loginMember.longitude}, ${loginMember.latitude} )
				});
				
		 </script>
		 <div style="text-align: right;">
		 	<span style="color: #ccc; cursor: pointer;" onclick="report()" >게시글 신고</span>
		 </div>
		<hr>
	 <table>
	 <c:if test="${login eq true }">
	 	<tr>
	 		<td class = "left-side" > 댓글 <input type="text" id="comment" onkeypress="enterkey(event)"></td>
	 		<td><button type="button" id="commentInput" onclick="commentInput('${userno}', '${map.BOARDNO }')">입력</button></td>
	 	</tr>
 	</c:if>
	 </table>
	 
	<div class="messageLayer" style="display: none; background: #FFDAD7; color: #FF5050; width: 143px; height: 33px; padding: 10px;">
		<div>
			<span onclick="closeLayer(this)" style="cursor:pointer;font-size:1.5em" title="닫기">X</span>
			<span style="cursor:pointer;font-size:1.5em" onclick="sendMessage($('.messageLayer').attr('userid'))">쪽지보내기</span>
		</div>
	</div>
	
	<!-- 게시글 삭제 모달창 -->
	<div id="modal">
	  <div class="modal-content">
	    <h2>정말 게시글을 삭제하시겠습니까?</h2><br>
	    <div style="text-align: center;">
		    <button id="submit" style="width: 120px; height: 33px; font-size: 17px; font-weight: bold; background-color: #f5cbcb; border-radius: 10px 10px 10px 10px / 10px 10px 10px 9px; border: none; color: #FF5050; cursor: pointer;">예</button>
		    <button id="cancel" style="width: 120px; height: 33px; font-size: 17px; font-weight: bold; background-color: #f5cbcb; border-radius: 10px 10px 10px 10px / 10px 10px 10px 9px; border: none; color: #FF5050; cursor: pointer;">아니오</button>
	    </div>
	  </div>
	</div>
	 
	 <!-- ajax html -->
	 <div class = "comment-area">
		<c:import url="./comment.jsp"/>
	 </div>
	 <!-- ajax html -->
</div>
<c:import url = "../../layout/footer.jsp" />