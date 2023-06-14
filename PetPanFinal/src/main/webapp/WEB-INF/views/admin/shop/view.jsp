<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://code.jquery.com/jquery-2.2.4.js"></script>
<style type="text/css">
th{
	width: 20%;
}
</style>
<c:import url="../../layout/adminHeader.jsp"/>
<body>


<div class="container2" style="margin-left: 500px">
<div>
	<div>

		  <table class = "table table-striped" style="width:1300px"> 
		  <tr><th>상품 번호</th> <td> ${shop.objectno}</td></tr>
		  <tr><th>상품 이름</th> <td> ${shop.name}</td></tr>
		  <tr><th>대표 사진</th> <td> 
			<img alt=".." src="<%=request.getContextPath()%>/upload/${shop.img1}" width="800px" height="auto" class="img">
		  <tr><th>상품 내용</th> <td> ${shop.shopcontent}</td></tr>
		  <tr><th>상품 사진</th><td>
		  <c:forEach items="${shopFile }" var="shopFile">
		   <img alt=".." src="<%=request.getContextPath()%>/upload/${shopFile.storedname}" width="800px" height="auto"  class="img">
			<br>
			</c:forEach>
			</td>
			</tr>
		  <tr><th>현재 가격</th> <td> <fmt:formatNumber value="${shop.price}" pattern="#,###원" /></td></tr>
		  <tr><th>남은 수량</th> <td> <fmt:formatNumber value="${shop.remain}" pattern="#,###개" /></td></tr>
		  <tr><th>판매 여부</th> <td> 
		  	<c:if test="${shop.deleteobj eq 1 }">판매중</c:if>
			<c:if test="${shop.deleteobj ne 1 }">판매중단</c:if>
		</td></tr>
		</table>
		</div>
		<hr>
	</div>
	<div align="right">
  		<button onclick="location.href='<%=request.getContextPath() %>/admin/shop/change?objectno=${shop.objectno }'" class="btn btn-info">수정</button>
  		<button onclick="location.href='<%=request.getContextPath() %>/admin/shop/view/delete?objectno=${shop.objectno }'" class="btn btn-danger">삭제</button>
	</div>
</div>
		


</body>
</html>
