<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--  <%@ include file="../../layout/adminHeader.jsp" %> --%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style type="text/css">
  #search {
    margin-left: 400px;
  }
</style>

<c:import url="../../layout/adminHeader.jsp"/>

<div class="container2">
  <table class="table table-striped" style="width:1300px">
    <tr>
      <th>유저번호</th>
      <td>${member.userNo}</td>
    </tr>
    <tr>
      <th>유저이름</th>
      <td>${member.userName}</td>
    </tr>
    <tr>
      <th>유저닉네임</th>
      <td>${member.userNick}</td>
    </tr>
    <tr>
      <th>유저아이디</th>
      <td>${member.userId}</td>
    </tr>
    <tr>
      <th>유저비밀번호</th>
      <td>${member.userPw}</td>
    </tr>
    <tr>
      <th>유저이메일</th>
      <td>${member.email}</td>
    </tr>
    <tr>
      <th>유저주소</th>
      <td>${member.address}</td>
    </tr>
    <tr>
      <th>유저핸드폰번호</th>
      <td>${member.phone}</td>
    </tr>
    <tr id="userPositionRow">
      <th>유저 포지션</th>
      <td id="userPosition">${member.positionNo == 1 ? "일반회원" : member.positionNo == 2 ? "병원관계자" : "관리자"}</td>
    </tr>
  </table>
  <a href="./update?userno=${member.userNo}"><button class="btn btn-primary">정보 수정</button></a>
  <a href="./list"><button class="btn btn-primary">멤버 리스트</button></a>
	    <button class="btn btn-danger" id="goblack">블랙</button>
	    <button class="btn btn-warning" id="deleteblack" >블랙됨</button>

  <c:if test="${member.positionNo ne 3}">
    <a href="./appadmin?userno=${member.userNo}"><button class="btn btn-info" id="adminBtn">관리자 승격</button></a>
  </c:if>
</div>

<script>
$(document).ready(function() {
	if (${isBlack > 0}){
		$('#goblack').hide();
		$('#deleteblack').show();
	}else{
		$('#goblack').show();
		$('#deleteblack').hide();
	}
	
	
	 $('#goblack').click(function(e) {
		    e.preventDefault();
		    var url = '../blacklist/insert?userno=${member.userNo}';
		    var popupWidth = 600; // 팝업 창 너비
		    var popupHeight = 400; // 팝업 창 높이
		    var left = (window.innerWidth - popupWidth) / 3 + window.screenX;
		    var top = (window.innerHeight - popupHeight) / 3 + window.screenY;
		    var popupWindow = window.open(url, '블랙 등록', 'width=' + popupWidth + ',height=' + popupHeight + ',left=' + left + ',top=' + top);

		    if (popupWindow) {
		      popupWindow.focus();

		      popupWindow.onunload = function() {
		        // 팝업 창이 닫혔을 때 부모 창을 새로고침
		        window.location.reload();
		      };
		    }
		  });

	  $('#adminBtn').click(function(e) {
	    e.preventDefault();

	    // AJAX를 사용하여 관리자 승격 요청 보내기
	    $.ajax({
	      url: $(this).parent().attr('href'),
	      type: 'GET',
	      success: function(response) {
	        alert('관리자 승격이 완료되었습니다.');

	        // 유저 포지션 업데이트
	        var positionTd = $('#userPosition');
	        positionTd.text("관리자");

	        // 블랙 버튼과 관리자 승격 버튼 숨기기
	        $('#goblack').hide();
	        $('#adminBtn').hide();
	        $('#deleteblack').hide();
	      },
	      error: function() {
	        alert('관리자 승격에 실패했습니다. 다시 시도해주세요.');
	      }
	    });
	  });
	  


	  // 확인이 true일 경우에만 AJAX 요청을 실행합니다.
	  $('#deleteblack').click(function(e) {
		  e.preventDefault();

		  // 확인(confirm) 창을 표시하고 사용자의 선택 결과를 받습니다.
		  var confirmation = confirm('정말로 블랙을 해제하시겠습니까?');

		  // 확인이 true일 경우에만 AJAX 요청을 실행합니다.
		  if (confirmation) {
		    // AJAX를 통해 "./deleteone"으로 요청을 보냅니다.
		    $.ajax({
		      url: '../blacklist/deleteOne?userno=${member.userNo}',
		      type: 'GET',
		      success: function(response) {
		        // 삭제 성공 시 알림을 표시합니다.
		        alert('Userno: ${member.userNo} UserName: ${member.userName} 삭제되었습니다.');
		        $('#deleteblack').hide();
		        $('#goblack').show();
		      },
		      error: function() {
		        // 에러 처리 로직을 추가합니다.
		      }
		    });
		  }
		});
	  
	  
	  
	  
	});



</script>
</body>
</html>
