
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>  <!-- 다음 postcode -->



<c:import url="./mypage_header.jsp" />



<style type="text/css">

.ur{
	color: #767678;
	text-decoration: none; 
}


#text {
	text-align: center;
	width: 600px;
	margin: 0 auto;
}

input{ 
   border:none;
   
   border-bottom: 2px solid #FFDAD7;
 }

.select{
    width: 420px;
    margin: 15px auto;

}

.select input[type=text]{
   width: 420px;
   height: 35px;
   color: #b7415f;

} 
.select input[type=password]{
   width: 420px;
   height: 35px;
} 

#btn{
   background-color: #FFDAD7;
   color : #FF5050;
   border-radius: 7px;
   border-color: #FFDAD7;
   width: 430px;
   height: 35px;
}

.id {
	color: #b7415f;
}

</style>


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










<div class="text">

<br>

<h2 style="color: #FF5050; text-align: center;">정보수정</h2>

<form action="./myprofile" method="post">
	
   <div class="select">
      <label for="userId">아이디 &emsp;</label>
      <label class="id" > ${detail.userId}</label>
   </div>

   <div class="select">
      <label for="userPw">비밀번호</label>
      <input type="password" id="userPw" name="userPw" placeholder="알파벳 대소문자, 숫자, 특수기호 조합으로 6자 이상, 16자 이하로 작성">
      <span id="userPw_msg" class="msg"></span>
   </div>
   
   <div class="select">
      <label for="userPw_check">비밀번호 재확인</label>
      <input type="password" id="userPw_check" name="userPw_check" >
      <span id="userPwcheck_error" class="msg"></span>
   </div>
   
   <div class="select">
      <label for="userNick">닉네임</label>
      <input type="text" id="userNick" name="userNick" value="${detail.userNick}" required>
      <span id="userNick_msg" class="msg"></span>
   </div>

   <div class="select">
      <label for="email">이메일</label>
      <input type="text" id="email" name="email" value="${detail.email}" required>
      <span id="email_msg" class="msg"></span>
   </div>
   

   <div class="select">
      <label for="address">주소</label>   <button id="btnPostcode" type="button">우편번호 찾기</button>
      <input type="text" id="zipCode" name="zipCode" value="${detail.zipCode}" required>	
      <input type="text"  id="address" name="address" value="${detail.address}" required>	
      <input type="text" id="detailaddress" name="detailaddress" value="${detail.detailaddress}" required >	
      <span id="address_msg" class="msg"></span>
   </div>
   
   <div class="select">
      <label for="phone">휴대전화</label>
      <input type="text"  id="phone" name="phone" pattern="[0-9]+" value="${detail.phone}" required>
      <span id="phone_msg" class="msg"></span>
   </div>




   <div class="select">
      <button id="btn">수정하기</button>
   </div>


</form>



















</div>

<c:import url="../../layout/footer.jsp" />







