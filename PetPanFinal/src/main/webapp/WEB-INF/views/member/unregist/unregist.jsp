
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">

/* 회원탈퇴 */
.ur{  
	color: #767678;
	text-decoration: none; 
}


.r {
	text-align: center;
	margin-top: 100px;
}

 .m{ 
	text-align: center;
	border: 1px solid #263959;
	border-radius: 2em;
	width: 500px;
	margin: auto;
 } 

 .my{ 
	text-align: center;
	color: #FF5050;
 } 
 .myp{ 
	text-align: center;
	color: #FF5050;
	margin-top: 100px;
/* 	margin: auto; */
 } 

 .pe { 
	text-align: center;
	border: 1px solid #263959;
	border-radius: 2em;
	width: 500px;
	margin: 0px auto;
 } 
 
 .img {
 	position: absolute;
  	float: left; 
  	margin: 15px -150px;
 }
 label{
 	
 }

</style>

<c:import url="../mypage/mypage_header.jsp" />
<script type="text/javascript">
</script>


<div class="text">


<div class="my">
<h3>회원탈퇴</h3>
</div>
<form action="./unregist" method="post">
<div class="m">
<h4>
1. 사이트를 탈퇴할 경우 저장했던 사진,파일, 게시한 글과 작성한 댓글, 받은 쪽지, 작성한 리뷰와 거래 목록이 모두 사라지게 됩니다.<br><br>
2. 보낸 쪽지의 경우, 받은 쪽에서 사라지진 않습니다. <br>하지만 탈퇴된 회원이 보낸 메세지로 변경되게 됩니다.<br><br>
3. 어떠한 경우에도 탈퇴된 회원의 정보를 복구할 수는 없습니다. <br>신중하게 탈퇴를 해주시기 바랍니다. <br><br>
</h4>
<input type="checkbox" name="delete" value="Y"> 전 본 약관을 자세히 숙지하고 탈퇴에 동의합니다. <br><br>
<button>탈퇴</button>
<br><br>
</div> <!-- m -->
</form>


<div class="r">

<a href="../unregist/cheak" class="ur">탈퇴취소하기</a>

</div>



</div>







