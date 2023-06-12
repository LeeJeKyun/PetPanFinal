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
	background-color: #ccc;
	padding: 8px 3px 13px 20px;
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
.node {
    position: absolute;
    background-image: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/sign-info-64.png);
    cursor: pointer;
    width: 64px;
    height: 64px;
}

.tooltip {
    background-color: #fff;
    position: absolute;
    border: 2px solid #333;
    font-size: 25px;
    font-weight: bold;
    padding: 3px 5px 0;
    left: 65px;
    top: 14px;
    border-radius: 5px;
    white-space: nowrap;
    display: none;
}

.tracker {
    position: absolute;
    margin: -35px 0 0 -30px;
    display: none;
    cursor: pointer;
    z-index: 3;
}

.icon {
    position: absolute;
    left: 6px;
    top: 9px;
    width: 48px;
    height: 48px;
    background-image: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/sign-info-48.png);
}

.balloon {
    position: absolute;
    width: 60px;
    height: 60px;
    background-image: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/balloon.png);
    -ms-transform-origin: 50% 34px;
    -webkit-transform-origin: 50% 34px;
    transform-origin: 50% 34px;
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

<%-- 닉네임 클릭시 생기는 창을 종료하는 함수 --%>
function closeLayer( obj ) {
	$(obj).parent().parent().hide();
	$('.messageLayer').css({
		"top": 0,
		"left": 0,
		"position": "fixed"
	})
}

<%-- 닉네임 클릭 시 창을 띄우는 함수 --%>
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
	}).attr({
			"userid" : userid
		});
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
	window.open("<%=request.getContextPath() %>/message/message/send?receiveuserid=" + userid , '쪽지', "width=400, height=500, resizable=no");
	$('.messageLayer').hide();
}
<%-----------------------------쪽지모달창 끝 -----------------%>
$(function(){
	
	<c:if test="${userno eq map.USERNO }">
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
	</c:if>
	
	$(window).on("scroll", function() {
		$(".messageLayer").hide();
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

<%-- 댓글 신고 함수 --%>
function reportComment(commentNo){
	if(${empty userno}){
		alert("로그인을 해주세요.")
		return
	}
	window.open("./reportComment?commentNo="+commentNo, "신고", "width=400, height=500, resizable=no" );
}

<%-- 댓글 삭제 함수 --%>
function deleteComment(commentNo){
	console.log("click & " + commentNo)
	
		$.ajax({
		type : "get" 
			, url: "./comment/delete"
			, data : { 
				commentNo : commentNo, 
				boardNo : ${map.BOARDNO}
			}
			, dataType : "html"
			, success : function(data){
// 				console.log("AJAX 성공")
// 				console.log(data)
				$(".comment-area").html(data)
				
			}
			, error : function(){
				console.log("AJAX 실패")	
			}
		})
		
}

<%-- 댓글추천 AJAX --%>
function comRecommend(commentNo, e){
	console.log("click & " + commentNo);
	$("#comemptyHeart" + commentNo).toggleClass('displayNone')
	$("#comfillHeart" + commentNo).toggleClass('displayNone')
		
	$.ajax({
		type : "get" 
			, url: "./comRecommend"
			, data : { 
				commentNo : commentNo, 
			}
			, dataType : "html"
			, success : function(data){
// 				console.log("AJAX 성공")
				console.log(data)
// 				console.log(e.target);
// 				console.log($("#comrec" + commentNo));
				$("#comrec" + commentNo).html(data);
			}
			, error : function(){
				console.log("AJAX 실패")
			}
		})
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
		<div>작성자 : <span class="message" onclick="message(event, '${map.USERID}')" style="cursor: pointer;">${map.USERNICK }</span></div>
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
		 <div style="padding-left: 16px;">
		 	<c:if test="${login eq true }">
			 	<c:choose>
					<c:when test="${isRecommended eq false || empty isRecommended}">
				 		<img src="<%=request.getContextPath() %>/resources/img/emptyheart.png" width="30px" height="30px"
				 			id="recommendBtn1" style="cursor: pointer;" onclick="recommendAjax(${map.BOARDNO})">
				 		<img src="<%=request.getContextPath() %>/resources/img/pilledheart.png" width="30px" height="30px" class="displayNone"
				 			id="recommendBtn2" style="cursor: pointer;" onclick="recommendAjax(${map.BOARDNO})">
			 		</c:when>
			 		<c:otherwise>
				 		<img src="<%=request.getContextPath() %>/resources/img/emptyheart.png" width="30px" height="30px" class="displayNone"
				 			id="recommendBtn1" style="cursor: pointer;" onclick="recommendAjax(${map.BOARDNO})">
				 		<img src="<%=request.getContextPath() %>/resources/img/pilledheart.png" width="30px" height="30px"
				 			id="recommendBtn2" style="cursor: pointer;" onclick="recommendAjax(${map.BOARDNO})">
					 </c:otherwise>
				 </c:choose>
			 </c:if>
		</div>
	 	<div id="recommend_count">
	 		<span>추천수 : </span><span id="recommendCnt">${map.RECOMMEND }</span>
	 	</div>
	 	<br>
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
		 
		 function TooltipMarker(position, tooltipText) {
			    this.position = position;
			    var node = this.node = document.createElement('div');
			    node.className = 'node';

			    var tooltip = document.createElement('div');
			    tooltip.className = 'tooltip',

			    tooltip.appendChild(document.createTextNode(tooltipText));
			    node.appendChild(tooltip);
			    
			    // 툴팁 엘리먼트에 마우스 인터렉션에 따라 보임/숨김 기능을 하도록 이벤트를 등록합니다.
			    node.onmouseover = function() {
			        tooltip.style.display = 'block';
			    };
			    node.onmouseout = function() {
			        tooltip.style.display = 'none';
			    };
		}
		 
		TooltipMarker.prototype = new kakao.maps.AbstractOverlay;
		
		TooltipMarker.prototype.onAdd = function() {
			    var panel = this.getPanels().overlayLayer;
			    panel.appendChild(this.node);
		};
		
		TooltipMarker.prototype.onRemove = function() {
		    this.node.parentNode.removeChild(this.node);
		};
		
		TooltipMarker.prototype.draw = function() {
		    // 화면 좌표와 지도의 좌표를 매핑시켜주는 projection객체
		    var projection = this.getProjection();
		    
		    // overlayLayer는 지도와 함께 움직이는 layer이므로
		    // 지도 내부의 위치를 반영해주는 pointFromCoords를 사용합니다.
		    var point = projection.pointFromCoords(this.position);

		    // 내부 엘리먼트의 크기를 얻어서
		    var width = this.node.offsetWidth;
		    var height = this.node.offsetHeight;

		    // 해당 위치의 정중앙에 위치하도록 top, left를 지정합니다.
		    this.node.style.left = (point.x - width/2) + "px";
		    this.node.style.top = (point.y - height/2) + "px";
		};
		
		TooltipMarker.prototype.getPosition = function() {
		    return this.position;
		};
		
		function MarkerTracker(map, target) {
		    // 클리핑을 위한 outcode
		    var OUTCODE = {
		        INSIDE: 0, // 0b0000
		        TOP: 8, //0b1000
		        RIGHT: 2, // 0b0010
		        BOTTOM: 4, // 0b0100
		        LEFT: 1 // 0b0001
		    };
		    
		    // viewport 영역을 구하기 위한 buffer값
		    // target의 크기가 60x60 이므로 
		    // 여기서는 지도 bounds에서 상하좌우 30px의 여분을 가진 bounds를 구하기 위해 사용합니다.
		    var BOUNDS_BUFFER = 30;
		    
		    // 클리핑 알고리즘으로 tracker의 좌표를 구하기 위한 buffer값
		    // 지도 bounds를 기준으로 상하좌우 buffer값 만큼 축소한 내부 사각형을 구하게 됩니다.
		    // 그리고 그 사각형으로 target위치와 지도 중심 사이의 선을 클리핑 합니다.
		    // 여기서는 tracker의 크기를 고려하여 40px로 잡습니다.
		    var CLIP_BUFFER = 40;

		    // trakcer 엘리먼트
		    var tracker = document.createElement('div');
		    tracker.className = 'tracker';

		    // 내부 아이콘
		    var icon = document.createElement('div');
		    icon.className = 'icon';

		    // 외부에 있는 target의 위치에 따라 회전하는 말풍선 모양의 엘리먼트
		    var balloon = document.createElement('div');
		    balloon.className = 'balloon';

		    tracker.appendChild(balloon);
		    tracker.appendChild(icon);

		    map.getNode().appendChild(tracker);

		    // traker를 클릭하면 target의 위치를 지도 중심으로 지정합니다.
		    tracker.onclick = function() {
		        map.setCenter(target.getPosition());
		        setVisible(false);
		    };

		    // target의 위치를 추적하는 함수
		    function tracking() {
		        var proj = map.getProjection();
		        
		        // 지도의 영역을 구합니다.
		        var bounds = map.getBounds();
		        
		        // 지도의 영역을 기준으로 확장된 영역을 구합니다.
		        var extBounds = extendBounds(bounds, proj);

		        // target이 확장된 영역에 속하는지 판단하고
		        if (extBounds.contain(target.getPosition())) {
		            // 속하면 tracker를 숨깁니다.
		            setVisible(false);
		        } else {
		            // target이 영역 밖에 있으면 계산을 시작합니다.
		            

		            // 지도 bounds를 기준으로 클리핑할 top, right, bottom, left를 재계산합니다.
		            //
		            //  +-------------------------+
		            //  | Map Bounds              |
		            //  |   +-----------------+   |
		            //  |   | Clipping Rect   |   |
		            //  |   |                 |   |
		            //  |   |        *       (A)  |     A
		            //  |   |                 |   |
		            //  |   |                 |   |
		            //  |   +----(B)---------(C)  |
		            //  |                         |
		            //  +-------------------------+
		            //
		            //        B
		            //
		            //                                       C
		            // * 은 지도의 중심,
		            // A, B, C가 TooltipMarker의 위치,
		            // (A), (B), (C)는 각 TooltipMarker에 대응하는 tracker입니다.
		            // 지도 중심과 각 TooltipMarker를 연결하는 선분이 있다고 가정할 때,
		            // 그 선분과 Clipping Rect와 만나는 지점의 좌표를 구해서
		            // tracker의 위치(top, left)값을 지정해주려고 합니다.
		            // tracker 자체의 크기가 있기 때문에 원래 지도 영역보다 안쪽의 가상 영역을 그려
		            // 클리핑된 지점을 tracker의 위치로 사용합니다.
		            // 실제 tracker의 position은 화면 좌표가 될 것이므로 
		            // 계산을 위해 좌표 변환 메소드를 사용하여 모두 화면 좌표로 변환시킵니다.
		            
		            // TooltipMarker의 위치
		            var pos = proj.containerPointFromCoords(target.getPosition());
		            
		            // 지도 중심의 위치
		            var center = proj.containerPointFromCoords(map.getCenter());

		            // 현재 보이는 지도의 영역의 남서쪽 화면 좌표
		            var sw = proj.containerPointFromCoords(bounds.getSouthWest());
		            
		            // 현재 보이는 지도의 영역의 북동쪽 화면 좌표
		            var ne = proj.containerPointFromCoords(bounds.getNorthEast());
		            
		            // 클리핑할 가상의 내부 영역을 만듭니다.
		            var top = ne.y + CLIP_BUFFER;
		            var right = ne.x - CLIP_BUFFER;
		            var bottom = sw.y - CLIP_BUFFER;
		            var left = sw.x + CLIP_BUFFER;

		            // 계산된 모든 좌표를 클리핑 로직에 넣어 좌표를 얻습니다.
		            var clipPosition = getClipPosition(top, right, bottom, left, center, pos);
		            
		            // 클리핑된 좌표를 tracker의 위치로 사용합니다.
		            tracker.style.top = clipPosition.y + 'px';
		            tracker.style.left = clipPosition.x + 'px';

		            // 말풍선의 회전각을 얻습니다.
		            var angle = getAngle(center, pos);
		            
		            // 회전각을 CSS transform을 사용하여 지정합니다.
		            // 브라우저 종류에따라 표현되지 않을 수도 있습니다.
		            // https://caniuse.com/#feat=transforms2d
		            balloon.style.cssText +=
		                '-ms-transform: rotate(' + angle + 'deg);' +
		                '-webkit-transform: rotate(' + angle + 'deg);' +
		                'transform: rotate(' + angle + 'deg);';

		            // target이 영역 밖에 있을 경우 tracker를 노출합니다.
		            setVisible(true);
		        }
		    }

		    // 상하좌우로 BOUNDS_BUFFER(30px)만큼 bounds를 확장 하는 함수
		    //
		    //  +-----------------------------+
		    //  |              ^              |
		    //  |              |              |
		    //  |     +-----------------+     |
		    //  |     |                 |     |
		    //  |     |                 |     |
		    //  |  <- |    Map Bounds   | ->  |
		    //  |     |                 |     |
		    //  |     |                 |     |
		    //  |     +-----------------+     |
		    //  |              |              |
		    //  |              v              |
		    //  +-----------------------------+
		    //  
		    // 여기서는 TooltipMaker가 완전히 안보이게 되는 시점의 영역을 구하기 위해서 사용됩니다.
		    // TooltipMarker는 60x60 의 크기를 가지고 있기 때문에 
		    // 지도에서 완전히 사라지려면 지도 영역을 상하좌우 30px만큼 더 드래그해야 합니다.
		    // 이 함수는 현재 보이는 지도 bounds에서 상하좌우 30px만큼 확장한 bounds를 리턴합니다.
		    // 이 확장된 영역은 TooltipMarker가 화면에서 보이는지를 판단하는 영역으로 사용됩니다.
		    function extendBounds(bounds, proj) {
		        // 주어진 bounds는 지도 좌표 정보로 표현되어 있습니다.
		        // 이것을 BOUNDS_BUFFER 픽셀 만큼 확장하기 위해서는
		        // 픽셀 단위인 화면 좌표로 변환해야 합니다.
		        var sw = proj.pointFromCoords(bounds.getSouthWest());
		        var ne = proj.pointFromCoords(bounds.getNorthEast());

		        // 확장을 위해 각 좌표에 BOUNDS_BUFFER가 가진 수치만큼 더하거나 빼줍니다.
		        sw.x -= BOUNDS_BUFFER;
		        sw.y += BOUNDS_BUFFER;

		        ne.x += BOUNDS_BUFFER;
		        ne.y -= BOUNDS_BUFFER;

		        // 그리고나서 다시 지도 좌표로 변환한 extBounds를 리턴합니다.
		        // extBounds는 기존의 bounds에서 상하좌우 30px만큼 확장된 영역 객체입니다.  
		        return new kakao.maps.LatLngBounds(
		                        proj.coordsFromPoint(sw),proj.coordsFromPoint(ne));
		        
		    }


		    // Cohen–Sutherland clipping algorithm
		    // 자세한 내용은 아래 위키에서...
		    // https://en.wikipedia.org/wiki/Cohen%E2%80%93Sutherland_algorithm
		    function getClipPosition(top, right, bottom, left, inner, outer) {
		        function calcOutcode(x, y) {
		            var outcode = OUTCODE.INSIDE;

		            if (x < left) {
		                outcode |= OUTCODE.LEFT;
		            } else if (x > right) {
		                outcode |= OUTCODE.RIGHT;
		            }

		            if (y < top) {
		                outcode |= OUTCODE.TOP;
		            } else if (y > bottom) {
		                outcode |= OUTCODE.BOTTOM;
		            }

		            return outcode;
		        }

		        var ix = inner.x;
		        var iy = inner.y;
		        var ox = outer.x;
		        var oy = outer.y;

		        var code = calcOutcode(ox, oy);

		        while(true) {
		            if (!code) {
		                break;
		            }

		            if (code & OUTCODE.TOP) {
		                ox = ox + (ix - ox) / (iy - oy) * (top - oy);
		                oy = top;
		            } else if (code & OUTCODE.RIGHT) {
		                oy = oy + (iy - oy) / (ix - ox) * (right - ox);        
		                ox = right;
		            } else if (code & OUTCODE.BOTTOM) {
		                ox = ox + (ix - ox) / (iy - oy) * (bottom - oy);
		                oy = bottom;
		            } else if (code & OUTCODE.LEFT) {
		                oy = oy + (iy - oy) / (ix - ox) * (left - ox);     
		                ox = left;
		            }

		            code = calcOutcode(ox, oy);
		        }

		        return {x: ox, y: oy};
		    }

		    // 말풍선의 회전각을 구하기 위한 함수
		    // 말풍선의 anchor가 TooltipMarker가 있는 방향을 바라보도록 회전시킬 각을 구합니다.
		    function getAngle(center, target) {
		        var dx = target.x - center.x;
		        var dy = center.y - target.y ;
		        var deg = Math.atan2( dy , dx ) * 180 / Math.PI; 

		        return ((-deg + 360) % 360 | 0) + 90;
		    }
		    
		    // tracker의 보임/숨김을 지정하는 함수
		    function setVisible(visible) {
		        tracker.style.display = visible ? 'block' : 'none';
		    }
		    
		    // Map 객체의 'zoom_start' 이벤트 핸들러
		    function hideTracker() {
		        setVisible(false);
		    }
		    
		    // target의 추적을 실행합니다.
		    this.run = function() {
		        kakao.maps.event.addListener(map, 'zoom_start', hideTracker);
		        kakao.maps.event.addListener(map, 'zoom_changed', tracking);
		        kakao.maps.event.addListener(map, 'center_changed', tracking);
		        tracking();
		    };
		    
		    // target의 추적을 중지합니다.
		    this.stop = function() {
		        kakao.maps.event.removeListener(map, 'zoom_start', hideTracker);
		        kakao.maps.event.removeListener(map, 'zoom_changed', tracking);
		        kakao.maps.event.removeListener(map, 'center_changed', tracking);
		        setVisible(false);
		    };
		}
		
		
		
		var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		var options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(${writerMember.latitude}, ${writerMember.longitude}), //지도의 중심좌표.
			level: 7 //지도의 레벨(확대, 축소 정도)
		};
		
		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
		
		var writerMember = new kakao.maps.LatLng( ${writerMember.latitude}, ${writerMember.longitude} )
		var loginMember = new kakao.maps.LatLng( ${loginMember.latitude}, ${loginMember.longitude} )
		
		var marker1 = new TooltipMarker(writerMember, '작성자위치')
		var marker2 = new TooltipMarker(loginMember, '우리집')
		
		marker1.setMap(map);
		marker2.setMap(map);
		
		var markerTracker1 = new MarkerTracker(map, marker1);
		var markerTracker2 = new MarkerTracker(map, marker2);
		
		markerTracker1.run();
		markerTracker2.run();
		
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
	 
	<!-- 쪽지보내기 모달창 -->
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