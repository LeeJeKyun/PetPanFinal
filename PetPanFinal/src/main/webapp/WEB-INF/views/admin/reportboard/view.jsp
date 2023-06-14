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
	<h1>신고 정보</h1>
	<table class = "table table-striped" style="width:1300px"> 
		  <tr><th>신고 번호</th> <td>${list.boreportNo}</td></tr>
		  <tr><th>신고 내용</th> <td>${list.reportDetail}</td></tr>
		  <tr><th>신고 일자</th> <td><fmt:formatDate value="${list.reportDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td></tr>
		  <tr><th>처리 여부</th> <td>${list.complete}</td></tr>
	</table>
	</div>
	
		<hr>
		<form action="<%=request.getContextPath() %>/admin/reportboard/view/delete" method="post">
		<input type = "hidden" name="boreportNo" value="${list.boreportNo}">
		<div class="boardinfo">
		  <h6>신고글 정보</h6>
		  <table class = "table table-striped" style="width:1300px"> 
		  <tr><th>게시물 번호</th> <td>${board.boardNo}</td></tr>
		  <tr><th>게시물 제목</th> <td>${board.boardTitle}</td></tr>
		  <tr><th>게시물 내용</th> <td>${board.content}</td></tr>
		  <tr><th>작성 일자</th> <td><fmt:formatDate value="${board.writeDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td></tr>
		  <tr><th>게시물 유형 번호</th> <td>${board.boardTypeNo}</td></tr>
		  <tr><th>신고게시글 삭제</th> <td> 
		  <c:if test="${board.boardTypeNo ne 5 }">
		  <input type="checkbox" id="delete" name="doboardNo" value="${board.boardNo }">
		  </c:if>
		  <c:if test="${board.boardTypeNo eq 5 }">
		  <input type="checkbox" disabled="disabled">
		  </c:if>
		  </td></tr>
		  </table>
		</div>
		<hr>
		
		<div>
		  <h3>신고자 정보</h3>
		  <table class = "table table-striped" style="width:1300px"> 
		  <tr><th>신고자 번호</th> <td> ${domember.userNo}</td></tr>
		  <tr><th>신고자 ID</th> <td> ${domember.userId}</td></tr>
		  <tr><th>신고자 이름</th> <td> ${domember.userName}</td></tr>
		  <tr><th>사유</th> <td>
		  <c:if test="${doblack > 0 }">
		  <textarea rows="3" cols="20" class="form-control" disabled="disabled">이미 블랙리스트에 추가된 사용자입니다.</textarea>
		  </c:if>
		  <c:if test="${doblack == 0 }">
		  <textarea rows="3" cols="20" name="getdoblackres" class="form-control"></textarea>
		  </c:if>
		  </td></tr>
		  <tr><th>신고자 블랙리스트 추가</th><td> 
		  <c:if test="${doblack > 0 }">
		  <input type="checkbox" disabled="disabled">
		  </c:if>
		  <c:if test="${doblack == 0 }">
		  <input type="checkbox" id="delete" name="getdoblack" value="${domember.userNo }">
		  </c:if>
		  </td></tr>
		  </table>
		</div>
		<hr>
		
		<div>
		  <h3>피신고자 정보</h3>
		  <table class = "table table-striped" style="width:1300px"> 
		  <tr><th>피신고자 번호</th> <td> ${getmember.userNo}</td></tr>
		  <tr><th>피신고자 ID</th> <td> ${getmember.userId}</td></tr>
		  <tr><th>피신고자 이름</th> <td> ${getmember.userName}</td></tr>
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
		  <input type="checkbox" id="delete" name="getgetblack" value="${getmember.userNo }">
		  </c:if>
		  </td></tr>
		  </table>
		</div>
		<hr>
		<input type="submit" id="codeIdSubmit" value="선택 게시글 삭제/선택 회원 블랙리스트 추가" class="btn btn-success">
		</form>
	</div>
	</div>
</body>
</html>

	

</body>
</html>