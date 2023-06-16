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
th{
	width: 20%;
}
</style>
 <c:import url="../../layout/adminHeader.jsp"/>
<body>
<div class="container2">
<div>
	<div>
		  <h6>신고 정보</h6>
		  <table class = "table table-striped" style="width:1300px"> 
		  <tr><th>신고 번호</th> <td>${list.objreportNo}</td></tr>
		  <tr><th>신고 내용</th> <td>${list.reportdetail}</td></tr>
		  <tr><th>신고 일자</th> <td> <fmt:formatDate value="${list.reportDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td></tr>
		  <tr><th>처리 여부</th> <td>${list.complete}</td></tr>
		  </table>
		</div>
		<hr>
		<form action="<%=request.getContextPath() %>/admin/reportshop/view/delete" method="post">
		<input type = "hidden" name="objreportNo" value="${list.objreportNo}">
		<div class="commentinfo">
		  <h3>신고 상품 정보</h3>
		  <table class = "table table-striped" style="width:1300px"> 
		  <tr><th>상품 번호</th> <td> ${list.objectNo}</td></tr>
		  <tr><th>상품 이름</th> <td> <a href="javascript:void(window.open('<%=request.getContextPath() %>/admin/shop/view?objectno=${list.objectNo}', '상품정보','width=1000px, height=1000px'))">${list.name}</a></td></tr>
		  <tr><th>상품 판매 중단</th> <td>
		  <c:if test="${list.deleteobj eq 1 }">
		  <input type="checkbox" id="" name="objectNo" value="${list.objectNo }">
		  </c:if>
		  <c:if test="${list.deleteobj ne 1 }">
		  <input type="checkbox" id="" disabled="disabled">
		  </c:if>
		  </td></tr>
		  </table>
		</div>
		<hr>
		
		<div>
		  <h3>신고자 정보</h3>
		  <table class = "table table-striped" style="width:1300px"> 
		  <tr><th>신고자 번호</th> <td> ${list.userNo}</td></tr>
		  <tr><th>신고자 ID</th> <td> ${getmember.userId}</td></tr>
		  <tr><th>신고자 이름</th> <td> ${getmember.userName}</td></tr>
		  <tr><th>사유</th> <td>
		  <c:if test="${getblack > 0 }">
		  <textarea rows="3" cols="20" class="form-control" disabled="disabled">이미 블랙리스트에 추가된 사용자입니다.</textarea>
		  </c:if>
		  <c:if test="${getblack == 0 }">
		  <textarea rows="3" cols="20" name="getgetblackres" class="form-control"></textarea>
		  </c:if>
		  </td></tr>
		  <tr><th>피신고자 블랙리스트 추가</th> <td>
		  <c:if test="${getblack > 0 }">
		  <input type="checkbox" disabled="disabled">
		  </c:if>
		  <c:if test="${getblack == 0 }">
		  <input type="checkbox" id="delete" name="getgetblack" value="${list.userNo }">
		  </c:if>
		  </table>
		</div>
		<hr>
		<input type="submit" id="codeIdSubmit" value="선택 게시글 삭제/선택 회원 블랙리스트 추가" class="btn btn-danger">
		</form>
	</div>
	</div>
</body>
</html>

	

</body>
</html>