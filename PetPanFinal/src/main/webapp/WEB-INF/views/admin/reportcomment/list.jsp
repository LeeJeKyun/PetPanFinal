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
	<form action="/petpan/admin/reportcomment/delete" method="get">
		<table class = "table table-striped" style="width:800px">
		<tr>
		<th>신고번호</th>
		<th>신고자번호</th>
		<th>신고받은 ID</th>
		<th>신고받은 댓글</th>
		<th>신고내용</th>
		<th>신고일자</th>
		<th>처리여부</th>
		<th>삭제</th>
		
		</tr>
		<c:forEach var="list" items="${list}">
		<tr>
		<td>${list.coreportNo }</td>
		<td>${list.userNo }</td>
		<td>${list.userId }</td>
		<td><a href ="/petpan/admin/reportcomment/view/?coreportNo=${list.coreportNo}">${list.content }</a></td>
		<td>${list.reportDetail }</td>
		<td><fmt:formatDate value="${list.reportDate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
		<td>${list.complete }</td>
		<td><input type="checkbox" id="delete" name="delete" class="delete" value="${list.coreportNo }"></td>
		</tr>
		</c:forEach>
		</table>
		<div align="right" id="searchdelete" class="searchdelete">
		<input type="checkbox" id="checkall" name="checkall" class="checkall">전부선택
		<input type="submit" id="codeIdSubmit" value="선택 삭제"  class="btn btn-danger">
		</div>
	</form>
	<c:import url="../../layout/adminpaging.jsp"/>
</div>
</div>

</body>
</html>