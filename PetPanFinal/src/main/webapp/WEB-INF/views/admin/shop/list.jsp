<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload = function() {
	
	
	document.getElementById('write').onclick = function() {
		document.getElementById('form').submit();
	}
	
	
	document.getElementById('back').onclick = function() {
		history.go(-1);
	}
	
}
</script>

</head>
<body>
<form action="/petpan/admin/shop/write" method="get" id="form">
<input type="button" id="write" value="작성">
</form>
</body>
</html>