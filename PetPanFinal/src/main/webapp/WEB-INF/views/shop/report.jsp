<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고</title>
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">

$(function(){
	$("button").click(function(){
		if( $("#reportdetail").val() != "" ){
			$("form").submit();
			alert("신고가 완료되었습니다.");
		}else{
			alert("사유를 적어주세요.")
		}
	})	
})


</script>
<style type = "text/css">
body{
	padding-top:  15px;
	padding-bottom: 20px;
	padding-left: 30px;
	padding-right: 30px;
}
h4{
	margin: 0;
	width: 160px;
}
h3{
	margin: 0;
	
}
textarea{
	width: 100%;
	height: 430px;
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
	<form action = "./report" method = "post">
		<textarea name = "reportdetail" placeholder = "사유를 입력하세요." id="reportdetail"></textarea>
		
		<!-- 신고할 boardNo -->
		<input type = "hidden" name = "objectno" value = "${objectno }" >
		<button type = "button">신고하기</button>
	</form>
</div>
</body>
</html>