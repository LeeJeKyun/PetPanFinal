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

	if(${flag}){
		window.close();
	}
	 $("[name='reportdetail']").click(function(){
		 if ($("#other").is(':checked')){
			 console.log("#other clicked")
			 
			 $("textarea").removeAttr("disabled")
		 }else{
			 $("textarea").val("");
			 $("textarea").attr("disabled", "disabled");
		 }
	 })
	$("button").click(function(){
		if( $("[name='reportdetail']").is(':checked') ){
			$("form").submit();
			alert("댓글 신고가 완료되었습니다.")
			//window.close();
		}else{
			alert("사유를 선택해주세요.")
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
<form action = "./reportComment" method = "post">
	<table>
		<tr>
			<td style = "width: 50%; text-align: left;"><h3>신고사유</h3> </td>
		</tr>
		<tr>
			<td><h4><label><input type = "radio"  name = "reportdetail" value = "영리목적/홍보성">영리목적/홍보성</label></h4></td>
			<td><h4><label><input type = "radio"  name = "reportdetail" value = "저작권침해">저작권침해</label></h4></td>
		</tr>
		<tr>
			<td><h4><label><input type = "radio"  name = "reportdetail" value = "음란성/선정성">음란성/선정성</label></h4></td>
			<td><h4><label><input type = "radio"  name = "reportdetail" value = "욕성/인신공격">욕성/인신공격</label></h4></td>
		</tr>
		<tr>
			<td><h4><label><input type = "radio"  name = "reportdetail" value = "개인정보노출">개인정보노출</label></h4></td>
			<td><h4><label><input type = "radio"  name = "reportdetail" value = "같은내용 반복게시">같은내용 반복게시</label></h4></td>
		</tr>
		<tr>
			<td colspan = "2"><h4><label><input type = "radio"  id = "other"  name = "reportdetail" value = "기타">기타</label></h4></td>
			<td></td>
		</tr>
	</table>
	<!-- reportReason이 기타면 writeDetaiil로 처리 -->
	<textarea name = "writeDetail" placeholder = "사유를 입력하세요." disabled></textarea>
	
	<!-- 신고할 boardNo -->
	<!-- userNo은 세션에서 받기 -->
	<input type = "hidden" name = "commentNo" value = "${commentNo }" >
	<button type = "button">신고하기</button>
	</form>
</div>
</body>
</html>