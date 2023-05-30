
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<c:import url="../../layout/header.jsp" />

<style type="text/css" >
#boardselect{
	bottom: 6px;
	left: 30px;
	font-size:15px;
	width: 100px;
	padding-left: 4px;
}
</style>
<script type="text/javascript">
function searchInit(){
// 	console.log("클릭했다")
	$("#search").val('');
	$("#searchBtn").click();
}
function closeLayer( obj ) {
	$(obj).parent().parent().hide();
	$('.messageLayer').css({
		"top": 0,
		"left": 0,
		"position": "fixed"
	})
}
$(function() {
	//JQuery DOM 영역
	
	
})
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
</script>

<br>

<div style="width: 1500px; margin:0 auto;">

<div style="text-align: left; width: 1100px; margin: 0 auto;">
	<span style="font-size: 2em; font-weight: bold;">품앗이</span>
	<select name="boardselect" id="boardselect" onchange="location.href=(this.value);" style="height: 30px; font-size: 16px;">
	<option value="" selected="selected">품앗이</option>
	<option value="">임시보호</option>
	<option value="" >유기동물</option>
</select>
</div>

<br>

<table style="margin: 0 auto; width: 1000px; text-align: center; border-collapse: collapse;">
	<tr style="height: 50px">
		<th>번호</th>
		<th></th>
		<th>제목</th>
		<th>글쓴이</th>
		<th>등록일</th>
		<th>조회수</th>
		<th>추천수</th>
	</tr>
<c:forEach items="${noticeList }" var="notice">
	<tr style="height: 40px; border-top: 2px solid #f5cbcb;">
		<td>${notice.NOTICENO }</td>
		<td style="height: 90px;"><a href="../notice/view?noticeno=${notice.NOTICENO }"><img width="120px" height="90px" alt="공지사항" src="" style="vertical-align: middle;" ></a></td>
		<td><a href="../notice/view?noticeno=${notice.NOTICENO }">${notice.NOTICETITLE }</a></td>
		<td>관리자</td>
		<td><fmt:formatDate value="${notice.NOTICEWRITEDATE }" pattern="yyyy-MM-dd" /></td>
		<td></td>
		<td></td>
	</tr>
</c:forEach>
<c:forEach items="${list }" var="care">
	<tr style="height: 40px; border-top: 2px solid #f5cbcb;">
		<td>${care.BOARDNO }</td>
		<td style="height: 90px;"><a href="../care/view?boardNo=${care.BOARDNO }"><img width="120px" height="90px" alt="아가사진" src="<%=request.getContextPath() %>/upload/${care.STOREDNAME}" style="vertical-align: middle;" ></a></td>
		<td><a href="../care/view?boardNo=${care.BOARDNO }">${care.BOARDTITLE }</a></td>
		<td><span class="message" onclick="message(event, '${care.USERID}')" style="cursor: pointer;">${care.USERID }</span></td>
		<td><fmt:formatDate value="${care.WRITEDATE }" pattern="yyyy-MM-dd" /></td>
		<td>${care.HIT }</td>
		<td>${care.RECOMMEND }</td>
	</tr>
</c:forEach>
</table>

<br>
<div style="width: 1000px; margin:0 auto;">
	<form action="./list" method="get">
	<table>
	<tr>
		<td style="width: 10%">
		<c:if test="${login eq true }">
		<a href="./write">
			<button type="button"
			style="width: 85px; height: 33px; font-size: 17px; font-weight: bold; background-color: #f5cbcb; border-radius: 10px 10px 10px 10px / 10px 10px 10px 9px; border: none; color: #FF5050; cursor: pointer;">
				글쓰기
			</button>
		</a>
		</c:if>
		</td>
		<td style="width: 80%; text-align: right;">
			<input type="text" id="search" name="search" value="${paging.search }" style=" width:290px; height: 24px; border: 1px solid #ccc;">
			
		</td>
		<td style="width: 6%">
			<button type="submit" id="searchBtn"
			style="width: 60px; height:30px; font-size: 14px; font-weight: bold; background-color: #f5cbcb; border-radius: 10px 10px 10px 10px / 10px 10px 10px 9px; border: none; color: #FF5050; cursor: pointer;">
				검색
			</button>
		</td>
		<td>
			<button type="button" onclick="searchInit()"
			style="width: 60px; height:30px; font-size: 14px; font-weight: bold; background-color: #f5cbcb; border-radius: 10px 10px 10px 10px / 10px 10px 10px 9px; border: none; color: #FF5050; cursor: pointer;">
				초기화
			</button>
		</td>
	</tr>
	</table>
	</form>
	<br>
</div>

<div class="messageLayer" style="display: none; background: #FFDAD7; color: #FF5050; border: solid 2px #d0d0d0; width: 143px; height: 33px;	padding: 10px;">
	<div>
		<span onclick="closeLayer(this)" style="cursor:pointer;font-size:1.5em" title="닫기">X</span>
		<span style="cursor:pointer;font-size:1.5em" onclick="sendMessage($('.messageLayer').attr('userid'))">쪽지보내기</span>
	</div>
</div>
<!-- //폼 레이어  -->

<c:import url="./paging.jsp" />

<!-- 컨텐츠영역 끝 -->
</div>

<c:import url="../../layout/footer.jsp" />







