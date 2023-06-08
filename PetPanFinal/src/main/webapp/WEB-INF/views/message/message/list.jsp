
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
.fontWhite{
	color: white;
}
/* ------ 모달 css  */
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
<%-- 모달 버튼 css --%>
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

<%-- 쪽지탭을 선택 시에 AJAX --%>
function selectTab(doRead){
	console.log(this);
	if(doRead == 'Save'){
		console.log('저장됨');
		
		$.ajax({
			type : "get" 
				, url: "./messageList"
				, data : { 
					saveMessage : 'Y',
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
		return;
	} else if(doRead == 'Send'){
		console.log("보낸 쪽지함")
		
		$.ajax({
			type : "get" 
				, url: "./messageList"
				, data : { 
					doread : 'Send',
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
		
		return;
	}
	
		console.log('저장됨 이외의 다른 AJAX 실행')
		
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

//답장보내기
function sendMessage(userid){
	var login = '<%=session.getAttribute("login") %>';
	console.log(login)
	console.log(userid)
	window.open("<%=request.getContextPath() %>/message/message/send?receiveuserid=" + userid , '쪽지', "width=400, height=500, resizable=no");
	$('.messageLayer').hide();
}

//쪽지저장하기
function saveMessage(messageNo, isSaved){
	console.log(messageNo);
	
	console.log(isSaved);
	
	$.ajax({
		type : "get" 
			, url: "./messagesave"
			, data : { 
				messageno : messageNo, 
				issaved : isSaved
			}
			, dataType : "html"
			, success : function(data){
				console.log("AJAX 성공")
				console.log(data);
				
				if(data == 'true'){
					alert('저장이 완료되었습니다.')
				} else{
					alert('저장이 취소되었습니다.')
				}
				
			}
			, error : function(){
				console.log("AJAX 실패")
				
			}
	})
	
}

function deleteMessage(){
	console.log($("#submit").attr("messageno"));
	
	var messageNo = $("#submit").attr("messageno");
	
	$.ajax({
		type : "get" 
			, url: "./messagedelete"
			, data : { 
				messageno : messageNo, 
			}
			, dataType : "html"
			, success : function(data){
				console.log("AJAX 성공")
				console.log(data);
				
				location.reload();
						
			}
			, error : function(){
				console.log("AJAX 실패")
				
			}
	})
}

function toggleAllCheckbox(){
	console.log("체크박스다껐다켰다껏다켰다켰다껐다");
	
	var checkbox = $("[type='checkbox']");
	
	for(var i=1; i<checkbox.length; i++){
		console.log('asdf');
		checkbox[i].click();
	}	
	
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
	<div style="text-align: center; cursor: pointer;" class="tab" onclick="selectTab('Save')"><span>저장된 쪽지</span></div>
	<div style="text-align: center; cursor: pointer;" class="tab" onclick="selectTab('Y')"><span>읽음</span></div>
	<div style="text-align: center; cursor: pointer;" class="tab" onclick="selectTab('N')"><span>안읽음</span></div>
	<div style="text-align: center; cursor: pointer;" class="tab" onclick="selectTab('Send')"><span>보낸 쪽지함</span></div>
</div>


<div style="width: 1200px; margin: 0 auto; border: 2px solid #FFDAD7; height: 800px;
	display: grid; grid-template-columns: 1fr 1fr;">

<!-- 쪽지 목록 칸 -->
<div id="messageList">
	<c:import url="./messageList.jsp" />
</div>

<!-- 쪽지 상세보기 칸 -->
<div id="messageView" style="border-left: 2px solid #FFDAD7;">
	<div style="text-align: center;"><h3>상세보기</h3></div>
</div>


</div>

<!-- 쪽지 삭제 모달창 -->
<div id="modal">
  <div class="modal-content">
    <h2>정말 쪽지를 삭제하시겠습니까?</h2><br>
    <div style="text-align: center;">
	    <button id="submit" onclick="deleteMessage()">예</button>
	    <button id="cancel">아니오</button>
    </div>
  </div>
</div>


<!-- 컨텐츠영역 끝 -->
</div>

<c:import url="../../layout/footer.jsp" />







