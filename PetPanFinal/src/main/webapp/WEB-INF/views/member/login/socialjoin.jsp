
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../../layout/header_plain.jsp" />

<script src="/resources/member/join.js" type="text/javascript" ></script>

<link href="/resources/member/join.css" rel="stylesheet">

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>  <!-- 다음 postcode -->



<script type="text/javascript">
window.onload = function() {
	btnPostcode.onclick = function() {
	}
}
</script>

<script type="text/javascript">

$(function() {
	
	$("#btnPostcode").click(function() {
		
		new daum.Postcode({
			oncomplete: function(data) {
			
			
			
			// 우편번호 적용하기
			$("#zipCode").val( data.zonecode )
			 //$("#input[name~='postcode']").val(data.zonecode)
			 
			
			if( data.userSelectedType == 'J' ) {
				// 사용자가 지번 주소를 선택
			
				$("#address").val( data.jibunAddress )
				
			} else if( data.userSelectedType == 'R') {
				// 사용자가 도로명 주소를 선택
				
				$("#address").val( data.roadAddress )
				
			}
			
			$("#address_detail").trigger("focus")
			
			
			}
		
		
		}).open();
		
		
		
	})
	
})

</script>





<div id="join">

<form action="./socialjoin" method="post">

   
   <div class="select">
      <label for="userName">이름</label>
      <input type="text"  id="userName" name="userName">
      <span id="userName_msg" class="msg"></span>
   </div>
   
   <div class="select">
      <label for="userId" >아이디</label><br><button>중복체크</button>
      <input type="text" id="userId" name="userId"  placeholder="6자 이상, 16자 이하의 영문자,숫자만 가능">
      <span id="userid_msg" class="msg"></span>
   </div>
      
   <div class="select">
      <label for="userPw">비밀번호</label>
      <input type="password" id="userPw" name="userPw" placeholder="알파벳 대소문자, 숫자, 특수기호 조합으로 6자 이상, 16자 이하로 작성">
      <span id="userpw_msg" class="msg"></span>
   </div>
   
   <div class="select">
      <label for="userPw_check">비밀번호 재확인</label>
      <input type="password" id="userPw_check" name="userPw_check" >
      <span id="userPwcheck_error" class="msg"></span>
   </div>
   
   <div class="select">
      <label for="userNick">닉네임</label>
      <input type="text" id="userNick" name="userNick" >
      <span id="userNick_msg" class="msg"></span>
   </div>

   <div class="select">
      <label for="email">이메일</label>
      <input type="text" id="email" name="email" >
      <span id="email_msg" class="msg"></span>
   </div>
   

   <div class="select">
      <label for="address">주소</label>   <button id="btnPostcode" type="button">우편번호 찾기</button>
      <input type="text" id="zipCode" name="zipCode" >	
      <input type="text"  id="address" name="address" >	
      <input type="text" id="detailaddress" name="detailaddress" >	
      <span id="address_msg" class="msg"></span>
   </div>
   
   <div class="select">
      <label for="phone">휴대전화</label>
      <input type="text"  id="phone" name="phone" pattern="[0-9]+" placeholder="ex)01012345678">
      <span id="phone_msg" class="msg"></span>
   </div>
   
   
   <div class="select">
      <label><input type="radio" name="positionNo" value="1"> 일반 </label>
      <label><input type="radio" name="positionNo" value="2"> 병원관계자 </label>
   </div>
   
   
   
   
   <div class="select">
      <button id="btn">가입하기</button>
   </div>
   

</form>






</div>  <!-- join -->




<c:import url="../../layout/footer_plain.jsp" />





