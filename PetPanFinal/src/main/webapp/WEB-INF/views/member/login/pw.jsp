<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../../layout/header_plain.jsp" />

<script>


$(function(){
   
   $("#userId").focus(function(){
      $("#userId_msg").html("")   
   })
   $("#userName").focus(function(){
      $("#userName_msg").html("")   
   })
   $("#email").focus(function(){
      $("#email_msg").html("")   
   })
   
   $("#id_check").on("click", function(){
      
      var userName = $("#userId").val();
      var userName = $("#userName").val();
      var phone = $("#email").val();
      
      if( userName == '' ) {
         $('#userName_msg').html("이름을 입력해주세요")
         return
      }

      if( userId == '' ) {
         $('#userId_msg').html("아이디를 입력해주세요")
         return
      }
      
      if( phone == '' ) {
         $('#email_msg').html("전화번호을 입력해주세요")
         return
      }
      
      
      console.log(userName)
      console.log(phone)
      
   })
})


// 이메일 인증

$(function(){
	
	$('#mail-Check-Btn').click(function() {
		const email = $('#emailF').val() + $("#middle").text() + $('#email2').val(); // 이메일 주소값 얻어오기!
		console.log('완성된 이메일 : ' + email); // 이메일 오는지 확인
		const checkInput = $('.mail-check') // 인증번호 입력하는곳 
		
		alert('인증번호가 전송되었습니다.')

		// 이메일을 적지 않았을 때 ajax를 막아야한다!!
		if($('#emailF').val()==''){
			$('#mail-check-warn').html('이메일 인증 해주세요!')
			$('#mail-check-warn').css('color','red')
			return;
		}

		
		
		$.ajax({
			type : 'get',
			url : '/member/pwMailCheck?email='+email, // GET방식이라 Url 뒤에 email을 붙일수있다.
			success : function (data) {	// data는 serviceImpl의 joinEmail에서 보내주는 authNumber
				console.log("data : " +  data);
				
			if(data){
			
				checkInput.attr('disabled',false);
				
			  }
			}
		
		  ,error : function() {
	            console.log("실패!")
	         }
	      }); // end ajax
	   }); // end send email


	   var code = ""; // 인증번호 저장을 위한 코드
	   var isCertification = false;
	   
	   
	   
	//인증번호 비교 
	// blur -> focus가 벗어나는 경우 발생
	$('.mail-check').blur(function () {
		const inputCode = $(this).val();
		const $resultMsg = $('#mail-check-warn');
		
		
	    //사용자가 입력한 인증번호를 비교하는 AJAX
	      $.ajax({
	         type : 'POST',
	         data: {inputCode: inputCode},
	         url : '/member/mailCheck',
	         success : function (data) {
	            console.log("data : " +  data);

	    if(data){
	    	isCertification = true;
			
	    	$resultMsg.html('인증번호가 일치합니다.');
			$resultMsg.css('color','green');
			$('#mail-Check-Btn').attr('disabled',true);
			$('#email').attr('readonly',true);
			$('#email2').attr('readonly',true);
			$('#email2').attr('onFocus', 'this.initialSelect = this.selectedIndex');
	         $('#email2').attr('onChange', 'this.selectedIndex = this.initialSelect');
		}else{
			sCertification = false;
			$resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!.');
			$resultMsg.css('color','red');
		}
	         }
	         
	         ,error : function() {
	             console.log("실패!")
	          }
	      }); // end ajax
	   });

});

</script>


<style type="text/css">

#search_pw{
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

.noline{
    text-decoration-line: none;
}


input:focus {
	outline: 1px solid #FF5050;
}

#eamil2 {
	width: 150px;
	
}

#id {
	position: absolute;
	margin-left: 158px;
}

#pw {
	position: absolute;
	margin-left: 327px;
}

.clearbox {
	height: 40px;
	overflow: hidden;
	line-height: 0;
	clear: both;
}

</style>

</head>
<body>

<div id="search_pw">


	<div id="id">
   <a href='./id' style="text-align:center; font-size:17px; color:#FF5060;">아이디 찾기</a><br>
   </div>
   
	<div id="pw">
   <a href='./pw' style="text-align:center; font-size:17px; color:#FF5060;">비밀번호 찾기</a><br>
   </div>


<div class="clearbox"></div>
   
<form action="./pw" method="post">

   <div class="select">
      <label for="userId" >아이디</label>
      <input type="text" class="boxcolor" id="userId" name="userId">
   </div>

   <div class="select">
      <label for="userName" >이름</label>
      <input type="text" class="boxcolor" id="userName" name="userName">
   </div>
      
    
    	<div class="select" style="margin:10px 90px;">
			<label for="email" >이메일</label>
			
		<div class="input-group">
		
			<input type="text" class="form-control" name="emailF" id="emailF" placeholder="이메일" style="width:150px"> 
				<span id="middle">@</span>
				<input type="text" id="email2" list="email3" style="width:150px">
				<datalist class="form-control" id="email3">
					<option value="naver.com">naver.com</option>
					<option value="daum.net">daum.net</option>
					<option value="gmail.com">gmail.com</option>
					<option>직접입력</option>
				</datalist>
		
				<button type="button"id="mail-Check-Btn" class="input-group-addon">본인인증</button>
			<div class="btn">
				<input class="mail-check"
					placeholder="인증번호를 입력해주세요!" disabled="disabled" maxlength="6">
			</div><!-- btn -->
			<span id="mail-check-warn"></span>
		</div>
	</div>
    
    
    
   <div class="select">
      <button id="btn" >비밀번호 찾기</button>
   </div>
   


</form>

</div>






<c:import url="../../layout/footer_plain.jsp" />