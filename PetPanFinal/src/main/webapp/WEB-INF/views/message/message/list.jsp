
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="../../layout/header.jsp" />

<style type="text/css" >
.tab{
	background-color: #f5cbcb;
	border-radius: 10px 10px 10px 10px / 10px 10px 10px 9px;
}
.selected{
	color: white;
}
</style>

<script type="text/javascript">

<%-- 쪽지탭을 선택 시에 AJAX --%>
function selectTab(doRead){
	console.log(this);
	
		$.ajax({
		type : "get" 
			, url: "./messageList"
			, data : { 
				doread : doRead,
				receiveUserNo : ${userno}
				
			}
			, dataType : "html"
			, success : function(data){
				console.log("AJAX 성공")
				console.log(data)
				$("#messageList").html(data);
				
			}
			, error : function(){
				console.log("AJAX 실패")	
			}
	})
	
}

<%-- 쪽지 내용을 누를 시에 AJAX(상세보기) --%>
function messageView(messageNo){
	console.log(messageNo);
	$('#'+messageNo+'').css('color', '#bbb');
	
	$.ajax({
		type : "get" 
			, url: "./messageView"
			, data : { 
				messageno : messageNo
			}
			, dataType : "html"
			, success : function(data){
				console.log("AJAX 성공")
				console.log(data)
				
				$("#messageView").html(data);
				
			}
			, error : function(){
				console.log("AJAX 실패")	
			}
	})
	
}

</script>

<br>

<div style="width: 1500px; margin:0 auto; margin-top:200px;">

<div style="text-align: left; width: 1100px; margin: 0 auto;">
	<span style="font-size: 2em; font-weight: bold;">내 쪽지함</span>
</div>

<br>

<div style="width: 1200px; margin:0 auto; padding-bottom: 10px; display: grid; grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr 1fr 1fr 1fr 1fr ">
	<div style="text-align: center; cursor: pointer;" class="tab" onclick="selectTab()"><span>전체쪽지</span></div>
	<div style="text-align: center; cursor: pointer;" class="tab" onclick="selectTab('Y')"><span>읽음</span></div>
	<div style="text-align: center; cursor: pointer;" class="tab" onclick="selectTab('N')"><span>안읽음</span></div>
</div>


<div style="width: 1200px; margin: 0 auto; border: 2px solid #FFDAD7; height: 800px;
	display: grid; grid-template-columns: 1fr 1fr;">

<!-- 쪽지 목록 칸 -->
<div id="messageList">
	<c:import url="./messageList.jsp" />
</div>

<!-- 쪽지 상세보기 칸 -->
<div id="messageView" style="border-left: 2px solid #FFDAD7; text-align: center;">
	<h3>상세보기</h3>
</div>


</div>
<!-- 컨텐츠영역 끝 -->
</div>

<c:import url="../../layout/footer.jsp" />







