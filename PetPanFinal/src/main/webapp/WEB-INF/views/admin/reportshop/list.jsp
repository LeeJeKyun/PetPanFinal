<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
  <script type="text/javascript">
  $(document).ready(function(){
	  $("#codeIdSubmit").on('click',function(){
	    var deleteArr = [];
	   
	    $("input[name=delete]:checked").each(function(){
	      deleteArr.push($(this).val());
	    });
	  });

	  //checkall
	  $("#checkall").click(function(){
	    if($("#checkall").is(":checked")){
	      $(".delete").prop("checked", true);
	    }else{
	      $(".delete").prop("checked", false);
	    }
	  });

	  //한개 해제하면 전체도 해제
	  $(".delete").click(function(){
	    if($("input[name='delete']:checked").length == ${fn:length(list)}){
	      $("#checkall").prop("checked", true);
	    }else{
	      $("#checkall").prop("checked", false);
	    }
	  });
	});

  </script>

 <c:import url="../../layout/adminHeader.jsp"/>
 <body>
<div class="container2">
<div align="center">
	<form action="<%=request.getContextPath() %>/admin/reportshop/delete" method="get">
		<table class = "table table-striped" style="width:1400px">
		<tr>
		<th>신고번호</th>
		<th>신고상품번호</th>
		<th>신고 받은 상품이름</th>
		<th>신고 받은 내용</th>
		<th>신고자번호</th>
		<th>신고자이름</th>
		<th>신고날짜</th>
		<th>처리여부</th>
		<th>삭제</th>
		
		</tr>
		<c:forEach var="list" items="${list}">
		<tr>
		<td>${list.objreportNo }</td>
		<td>${list.objectNo }</td>
		<td>${list.name }</td>
		<td><a href ="<%=request.getContextPath() %>/admin/reportshop/view/?objreportNo=${list.objreportNo}">${list.reportdetail }</a></td>
		<td>${list.userNo }</td>
		<td>${list.userName }</td>
		<td><fmt:formatDate value="${list.reportDate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
		<td>${list.complete }</td>
		<td><c:if test="${list.complete eq 'N' }"><input type="checkbox" id="delete" name="delete" class="delete" value="${list.objreportNo }"></c:if>
			<c:if test="${list.complete eq 'Y' }"><input type="checkbox" disabled="disabled"></c:if></td>
		</tr>
		</c:forEach>
		</table>
		<div align="right" id="searchdelete" class="searchdelete">
			<input type="checkbox" id="checkall" name="checkall" class="checkall">전부선택
			<input type="submit" id="codeIdSubmit" value="선택 삭제"  class="btn btn-danger">
		</div>
	</form>
	<div align="center" id="searchbottom" class="searchbottom">
	<form action="<%=request.getContextPath() %>/admin/reportshop/list" method="get">
		<input type="text" name="search" value="${search }">
		<input type="submit" id="codeIdSubmit" value="검색" class="btn btn-info">
	</form>
	</div>
	<c:import url="../../layout/adminpaging.jsp"/>
</div>
</div>

</body>
</html>