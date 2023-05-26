<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>

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
 <c:import url="../../layout/adminHeader.jsp"/>
<form action="/petpan/admin/shop/write" method="post" id="form" enctype="multipart/form-data">

		<label for="name">상품이름</label>
		<input type="text" id="name" name="name"><br><br>
		<label for="price">가격</label>
		<input type="text" id="price" name="price"><br><br>
		<label for="">수량</label>
		<input type="text" id="remain" name="remain"><br><br>
		<label for="shopcontent">글내용</label>
		<textarea cols="50" rows="30" id="shopcontent" name="shopcontent"></textarea>
		<br>
		<label for ="file">파일</label>
		<input type="file" name="shopFile" id="shopFile"><br><br>
		
<input type="button" id="write" value="작성">
</form>

</body>
</html>