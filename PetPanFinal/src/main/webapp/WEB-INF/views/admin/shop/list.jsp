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
<div class="container2">
<div align="center">
	<form action="/petpan/admin/shop/delete" method="get">
		<table class = "table table-striped" style="width:800px">
		<tr>
		<th>상품번호</th>
		<th>상품이름</th>
		<th>상품가격</th>
		<th>남은수량</th>
		<th>판매 여부</th>
		<th>삭제</th>
		
		</tr>
		<c:forEach var="list" items="${list}">
		<tr>
		<td>${list.objectno }</td>
		<td><a href ="/petpan/admin/shop/view/?objectno=${list.objectno}">${list.name }</a></td>
		<td>${list.price }</td>
		<td>${list.remain }</td>
		<td>
			<c:if test="${list.deleteobj eq 1 }">판매중</c:if>
			<c:if test="${list.deleteobj ne 1 }">판매중단</c:if>
		</td>
		<td><input type="checkbox" id="delete" name="delete" class="delete" value="${list.objectno }"></td>
		</tr>
		</c:forEach>
		</table>
		
		<div align="right" id="searchdelete" class="searchdelete"  style= "float:right;">
			<input type="checkbox" id="checkall" name="checkall" class="checkall">전부선택
			<input type="submit" id="codeIdSubmit" value="선택 삭제"  class="btn btn-danger">
		</div>
	</form>
	<div align="left" id="searchbottom" class="searchbottom">
	<form action="/petpan/admin/shop/write" method="get">
		<input type="submit" id="codeIdSubmit" value="상품등록" class="btn btn-info">
	</form>
	</div>
	<div align="center" id="searchbottom" class="searchbottom">
	<form action="/petpan/admin/shop/list" method="get">
		<input type="text" name="search" value="${search }">
		<input type="submit" id="codeIdSubmit" value="검색" class="btn btn-info">
	</form>
	</div>
	<c:import url="../../layout/adminpaging.jsp"/>
</div>
</div>

</body>
</html>