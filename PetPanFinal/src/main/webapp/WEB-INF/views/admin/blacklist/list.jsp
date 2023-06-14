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
		<form action="./delete" method="get">
		<table class = "table table-striped" style="width:800px">
		<tr>
		<th>블랙번호</th>
		<th>유저 번호</th>
		<th>유저 이름</th>
		<th>블랙된 날짜</th>
		<th>블랙 이유</th>
		<th>체크</th>
		
		</tr>


		<c:forEach var="list" items="${list}">
		<tr>
		<td>${list.BLACKNO }</td>
		<td> <a href = "../member/view?userno=${list.USERNO}">${list.USERNO}</a></td>
		<td>${list.USERNAME }</td>
		<td><fmt:formatDate value="${list.BLACKDATE }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
		<td>${list.REASON }</td>
		<td><input type="checkbox" id="delete" name="delete" class="delete" value="${list.BLACKNO }">
		</tr>
		</c:forEach>
		</table>
		<input type="checkbox" id="checkall" name="checkall" class="checkall">전부선택
		<input type="submit" id="codeIdSubmit" value="선택 삭제" class="btn btn-danger">
	</form>
	</div>	
	<div class = "" style="margin-left: 170px;">	
	<c:import url="../../layout/adminpaging.jsp"/>
	</div>
	<div>
		
	</div>
</div>
</body>
</html>