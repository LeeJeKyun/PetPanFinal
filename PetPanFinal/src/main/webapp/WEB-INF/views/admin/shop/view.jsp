<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://code.jquery.com/jquery-2.2.4.js"></script>
<c:import url="../../layout/adminHeader.jsp"/>
<body>

<div class="container2" style="margin-left: 500px">
<div>
	<div>
		  <h3>신고 정보</h3>
		  <p>상품 번호: ${shop.objectno}</p>
		  <p>상품 이름: ${shop.name}</p>
		  <p>대표 사진 : 
			<img alt=".." src="<%=request.getContextPath()%>/upload/${shop.img1}" width="800px" height="auto" class="img">
		  <p>상품 내용: ${shop.shopcontent}</p>
		  <p><c:forEach items="${shopFile }" var="shopFile">
			<img alt=".." src="<%=request.getContextPath()%>/upload/${shopFile.storedname}" width="800px" height="auto"  class="img">
			<br>
			</c:forEach></p>
		  <p>현재 가격: <fmt:formatNumber value="${shop.price}" pattern="#,###원" /></p>
		  <p>남은 수량: <fmt:formatNumber value="${shop.remain}" pattern="#,###개" /></p>
		  <p>판매 여부: 
		  	<c:if test="${shop.deleteobj eq 1 }">판매중</c:if>
			<c:if test="${shop.deleteobj ne 1 }">판매중단</c:if>
			</p>
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
