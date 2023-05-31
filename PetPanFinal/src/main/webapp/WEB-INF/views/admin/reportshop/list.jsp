<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>

 <c:import url="../../layout/adminHeader.jsp"/>
 <body>
<div class="container2">
<div align="center">
	<form action="/petpan/admin/reportboard/delete" method="get">
		<table class = "table table-striped" style="width:800px">
		<tr>
		<th>신고번호</th>
		<th>신고자번호</th>
		<th>신고받은 ID</th>
		<th>신고받은 글</th>
		<th>신고내용</th>
		<th>신고일자</th>
		<th>처리여부</th>
		<th>삭제</th>
		
		</tr>
		<c:forEach var="list" items="${list}">
		<tr>
		<td>${list.boreportNo }</td>
		<td>${list.userNo }</td>
		<td>${list.userId }</td>
		<td><a href ="<%=request.getContextPath() %>/admin/reportboard/view/?boreportNo=${list.boreportNo}">${list.boardTitle }</a></td>
		<td>${list.reportDetail }</td>
		<td><fmt:formatDate value="${list.reportDate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
		<td>${list.complete }</td>
		<td><input type="checkbox" id="delete" name="delete" class="delete" value="${list.boreportNo }"></td>
		</tr>
		</c:forEach>
		</table>
		<div align="right" id="searchdelete" class="searchdelete">
			<input type="checkbox" id="checkall" name="checkall" class="checkall">전부선택
			<input type="submit" id="codeIdSubmit" value="선택 삭제"  class="btn btn-danger">
		</div>
	</form>
	<div align="center" id="searchbottom" class="searchbottom">
	<form action="<%=request.getContextPath() %>/admin/reportboard/list" method="get">
		<input type="text" name="search" value="${search }">
		<input type="submit" id="codeIdSubmit" value="검색" class="btn btn-info">
	</form>
	</div>
	<c:import url="../../layout/adminpaging.jsp"/>
</div>
</div>

</body>
</html>