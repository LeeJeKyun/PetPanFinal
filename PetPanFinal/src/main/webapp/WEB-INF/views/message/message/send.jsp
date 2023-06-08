<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지</title> 
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">

$(function(){

})


</script>
<style type = "text/css">
body{
	padding-top:  15px;
	padding-bottom: 20px;
	padding-left: 30px;
	padding-right: 30px;
}
table{
	width:100%;
}
h4{
	margin: 0;
	width: 160px;
}
h3{
	margin: 0;
	
}
td{
	padding-bottom: 20px;
}
textarea{
	width: 100%;
	height: 150px;
	resize: none;
}
button{
	width: 100%;
	height: 40px;
	border: none;
	background-color: #ff6b6b;
	cursor: pointer;
	color: white;
}
</style>

<body>

<div>
<form action = "./send" method = "post">
	<table>
		<tr>
			<td style = "width: 50%; text-align: left;"><h3>쪽지보내기</h3> </td>
		</tr>
		<tr>
			<td><label for="usernick" >받는사람 <input type = "text"  name = "usernick" id="usernick" value = "${receiveMember.userNick }" readonly="readonly"></label></td>
		</tr>
	</table>
	<!-- reportReason이 기타면 writeDetaiil로 처리 -->
	<textarea name = "content" placeholder = "메시지를 입력하세요."></textarea>
	
	<!-- 신고할 boardNo -->
	<!-- 	userNo은 세션에서 받기 -->
<%-- 	<input type = "hidden"  name = "receiveuserid" id="receiveuserid" value = "${receiveuserid }" readonly="readonly"> --%>
	<input type = "hidden" name = "receiveUserNo" value = "${receiveMember.userNo }" >
	<input type = "hidden" name = "sendUserNo" value = "${senduserno }" >
	<button type = "submit">쪽지보내기</button>
	</form>
</div>
</body>
</html>