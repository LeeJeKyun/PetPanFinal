<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<meta charset="UTF-8">
<title>Insert title here</title>
  <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <style type="text/css">
.boardinfo{
	width: 1000px;
}
</style>
 <c:import url="../../layout/adminHeader.jsp"/>
<body>
<div class="container2">
<div>
	<div>
		  <h3>신고 정보</h3>
		  <p>신고 번호: ${list.objreportNo}</p>
		  <p>신고 내용: ${list.reportdetail}</p>
		  <p>신고 일자: <fmt:formatDate value="${list.reportDate}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
		  <p>처리 여부: ${list.complete}</p>
		</div>
		<hr>
		<form action="<%=request.getContextPath() %>/admin/reportshop/view/delete" method="post">
		<input type = "hidden" name="objreportNo" value="${list.objreportNo}">
		<div class="commentinfo">
		  <h3>신고 상품 정보</h3>
		  <p>상품 번호: ${list.objectNo}</p>
		  <p>상품 이름: <a href="javascript:void(window.open('<%=request.getContextPath() %>/admin/shop/view?objectno=${list.objectNo}', '상품정보','width=1000px, height=1000px'))">${list.name}</a></p>
		</div>
		<hr>
		
		<div>
		  <h3>신고자 정보</h3>
		  <p>신고자 번호: ${list.userNo}</p>
		  <p>신고자 ID: ${getmember.userId}</p>
		  <p>신고자 이름: ${getmember.userName}</p>
		  사유 <textarea rows="3" cols="20" name="reason"></textarea><br>
		  신고자 블랙리스트 추가 <input type="checkbox" id="delete" name="userNo" value="${list.userNo }">
		</div>
		<hr>
		<input type="submit" id="codeIdSubmit" value="선택 게시글 삭제/선택 회원 블랙리스트 추가">
		</form>
	</div>
	</div>
</body>
</html>

	

</body>
</html>