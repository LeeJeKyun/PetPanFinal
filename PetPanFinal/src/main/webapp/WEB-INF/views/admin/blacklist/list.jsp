<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--  <%@ include file="../../layout/adminHeader.jsp" %> --%>

<c:import url="../../layout/adminHeader.jsp"/>
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
 

<div class="container2">
	<div>
		<form action="/petpan/admin/blacklist/delete" method="get">
		<table class = "table table-striped" style="width:800px">
		<tr>
		<th>블랙번호</th>
		<th>유저 번호</th>
		<th>블랙된 날짜</th>
		<th>블랙 이유</th>
		<th>체크</th>
		
		</tr>
		<c:forEach var="list" items="${list}">
		<tr>
		<td>${list.blackno }</td>
		<td>${list.userno }</td>
		<td><fmt:formatDate value="${list.blackdate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
		<td>${list.reason }</td>
		<td><input type="checkbox" id="delete" name="delete" class="delete" value="${list.blackno }">
		</tr>
		</c:forEach>
		</table>
		<input type="checkbox" id="checkall" name="checkall" class="checkall">전부선택
		<input type="submit" id="codeIdSubmit" value="선택 삭제">
		
	</form>
	</div>	
	<div class = "">	
	<c:import url="../../layout/adminpaging.jsp"/>
	</div>
	<div>
		<a href="./insert"><button>삽입하기</button></a>
	</div>
</div>
</body>
</html>