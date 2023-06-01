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
	<form action="<%=request.getContextPath() %>/admin/buyer/complete" method="get">
		<table class = "table table-striped" style="width:1400px">
		<tr>
		<th>구매번호</th>
		<th>구매자번호</th>
		<th>구매자이름</th>
		<th>구매상품번호</th>
		<th>구매상품이름</th>
		<th>구매일자</th>
		<th>구매수량</th>
		<th>구매자 연락처</th>
		<th>배송여부</th>
		<th>배송처리</th>
		
		</tr>
		<c:forEach var="list" items="${list}">
		<tr>
		<td>${list.BUYNO }</td>
		<td>${list.USERNO }</td>
		<td>${list.BUYERNAME }</td>
		<td>${list.OBJECTNO }</td>
		<td>${list.NAME }</td>
<%-- 		<td><fmt:formatDate value="${list.BUYDATE }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td> --%>
		<td>${list.BUYDATE }</td>
		<td>${list.QUANTITY }</td>
		<td>${list.BUYERPHONE}</td>
		<td><c:if test="${list.COMPLETE eq 'n' }">배송중</c:if>
			<c:if test="${list.COMPLETE eq 'y' }">배송완료</c:if></td>
		<td><c:if test="${list.COMPLETE eq 'n' }"><input type="checkbox" id="delete" name="delete" class="delete" value="${list.BUYNO }"></c:if>
			<c:if test="${list.COMPLETE eq 'y' }"><input type="checkbox" disabled="disabled"></c:if></td>
		</tr>
		</c:forEach>
		</table>
		<div align="right" id="searchdelete" class="searchdelete">
			<input type="checkbox" id="checkall" name="checkall" class="checkall">전부선택
			<input type="submit" id="codeIdSubmit" value="배송 완료"  class="btn btn-danger">
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