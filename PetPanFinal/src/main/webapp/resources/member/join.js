

//1. 처음 포커스 벗어났을 경우 메시지 띄우기
$(function(){
   
    //아이디에 포커스
   $("#userId").focus()
   
   //아이디를 입력하지 않고 넘어갈 경우(필수 정보입니다 띄우기)
   $("#userId").blur(function(){
      if($("#userId").val()==''){
         $("#userId_msg").html("필수정보입니다")
      }else{
         $("#userId_msg").html("")
      }
   })
   
   //비밀번호를 입력하지 않고 넘어갈 경우(필수 정보입니다 띄우기)
   $("#userPw").blur(function(){
      if($("#userPw").val()==''){
         $("#userPw_msg").html("필수정보입니다")
      }else{
         $("#userPw_msg").html("")
      }
   })
   
   //tab으로 다음칸으로 넘어갔을 경우  -- blur function
   ////비밀번호입력과 비밀번호 재확인입력이 동일하지않을 경우 메시지
   $("#userPw_check").blur(function(){   
      
      if($("#userPw").val() != $("#userPw_check").val()){
         $("#userPwcheck_error").html("비밀번호가 일치하지않습니다")
      }else{
         $("#userPwcheck_error").html("비밀번호가 일치합니다")
      }
   })
   
   //비밀번호 재확인칸을 클릭했을 경우 -- click function
   //비밀번호입력과 비밀번호 재확인입력이 동일하지않을 경우 메시지
   $("#userPw_check").click(function(){   
      
      if($("#userPw").val() != $("#userPw_check").val()){
         $("#userPwcheck_error").html("비밀번호가 일치하지않습니다")
      }else{
         $("#userPwcheck_error").html("비밀번호가 일치합니다")
      }
   })
   
   
   //이름을 입력하지 않고 넘어갈 경우(필수 정보입니다 띄우기)
   $("#userName").blur(function(){
      if($("#userName").val()==''){
         $("#userName_msg").html("필수정보입니다")
      }else{
         $("#userName_msg").html("")
      }
   })
   
})
//----------------------------------------------------------------------------------------

//2. 버튼 클릭했을 경우 아이디,비밀번호,이름,생년월일 정규화가 맞지않으면 메시지 띄우기
$(function(){
   
   //유효성 검사 -- 버튼 눌렀을때 push 알람 띄우기
   $("form").submit(function(){
      
      //validate가 틀렸을경우 리턴값 false
      //validate - 아이디,비밀번호 : 정규화 
      if(!validate()){
         return false
      }
      
      return true;
   })
   
   //아이디 입력란 클릭시 밑에 메시지 없애기
   $("#userId").focus(function(){
      $("#userId_msg").html("")   
   })
   //비밀번호 입력란 클릭시 밑에 메시지 없애기
   $("#userPw").focus(function(){
      $("#userPw_msg").html("")   
   })
   //이름 입력란 클릭시 밑에 메시지 없애기
   $("#userName").focus(function(){
      $("#userName_msg").html("")   
   })
})

//아이디,비밀번호,이름,생년월일 정규화가 틀렸을 경우 버튼푸시 실패
function validate(){

   //가입하기버튼 눌렀을 때 아이디 유효성검사
   if($('#userId').val()==''){
      $('#userId_msg').html("아이디를 입력해주세요")
      return false
   }
   
   //아이디 정규화
   var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
   
   if(!idReg.test($("#userId").val())){
         $("#userId_msg").html("아이디는 6자 이상, 20자 이하의 영문자,숫자만 가능합니다!")
         return false
   }
   
   //가입하기버튼 눌렀을 때 비밀번호 유효성검사
   if($('#userPw').val()==''){
      $('#userPw_msg').html("비밀번호를 입력해주세요")
      return false
   }
   
   //비밀번호 정규화
    var pwReg =/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;
         
   if( !pwReg.test( $("#userPw").val() ) )  {
         $("#userPw_msg").html("비밀번호는 알파벳 대소문자, 숫자, 특수기호 조합으로 8자 이상, 20자 이하로 작성하세요")
         return false
   }
    
     //가입하기버튼 눌렀을 때 이름 유효성검사
   if($('#userName').val()==''){
      $('#userName_msg').html("이름을 입력해주세요")
      return false
   }

     //이름 정규화
     var nameReg = /^[가-힣]{1,10}$/

   if( !nameReg.test( $("#userName").val() ) )  {
      $("#userName_msg").html("이름은 10자이내 한글만 가능합니다!")
      return false
   }
     return true;
  
}

//3. 버튼 클릭했을 경우 비밀번호와 비밀번호 재확인이 일치하지 않으면 메시지 띄우기
$(function(){
   
   //유효성 검사 -- 버튼 눌렀을때 push 알람 띄우기
   $("form").submit(function(){
      
      //validate가 틀렸을경우 리턴값 false
      //compare_check - 비밀번호,비밀번호재확인 일치 
      if(!compare_check()){
         return false
      }
      return true;
   })
  })
//버튼을 클릭했을 경우 비밀번호와 비밀번호재확인이 일치하지않으면 버튼푸시 실패
//비밀번호와 비밀번호 재확인 입력 유효성검사
function compare_check(){
   
   //가입하기버튼 눌렀을 때 비밀번호 확인 유효성 검사
   if($("#userPw").val() != $("#userPw_check").val()){
      $("#userPwcheck_error").html("비밀번호가 일치하지않습니다")
      return false;
   }
   return true
}

