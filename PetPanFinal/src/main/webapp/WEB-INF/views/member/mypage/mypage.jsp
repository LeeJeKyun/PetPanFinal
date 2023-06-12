
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="./mypage_header.jsp" />


<style type="text/css">

/* 회원탈퇴 */
.ur{  
	color: #767678;
	text-decoration: none; 
}


.r {
	text-align: center;
	margin-top: 100px;
}

 .m{ 
	text-align: center;
	border: 1px solid #263959;
	border-radius: 2em;
	width: 500px;
	margin: auto;
 } 

 .my{ 
	text-align: center;
	color: #FF5050;
 } 
 .myp{ 
	text-align: center;
	color: #FF5050;
	margin-top: 100px;
/* 	margin: auto; */
 } 

 .pe { 
	text-align: center;
	border: 1px solid #263959;
	border-radius: 2em;
	width: 500px;
	margin: 0px auto;
 } 
 
 .img {
 	position: absolute;
  	float: left; 
  	margin: 15px -170px;
 }

.petUp{
	margin: 2px 48%;
}

.petUp a{
	color: #FF5050;
 	text-decoration: none; 
}


</style>


<!-- <!--   <script type="text/javascript"> --> 
<!-- //   $(document).ready(function(){ -->
<!-- // 	  $(".deleteBtn").on('click',function(){ -->
<!-- // 	    var deleteArr = []; -->
	   
<!-- // 	    $("input[name=delete]:checked").each(function(){ -->
<!-- // 	      deleteArr.push($(this).val()); -->
<!-- // 	    }); -->
<!-- // 	  }); -->

<!-- // 	  //checkall -->
<!-- // 	  $("#checkall").click(function(){ -->
<!-- // 	    if($("#checkall").is(":checked")){ -->
<!-- // 	      $(".delete").prop("checked", true); -->
<!-- // 	    }else{ -->
<!-- // 	      $(".delete").prop("checked", false); -->
<!-- // 	    } -->
<!-- // 	  }); -->

<!-- // 	  //한개 해제하면 전체도 해제 -->
<!-- // 	  $(".delete").click(function(){ -->
<%-- // 	    if($("input[name='delete']:checked").length == ${fn:length(Info)}){ --%>
<!-- // 	      $("#checkall").prop("checked", true); -->
<!-- // 	    }else{ -->
<!-- // 	      $("#checkall").prop("checked", false); -->
<!-- // 	    } -->
<!-- // 	  }); -->
	  
	  
<!-- // 	}); -->

<!-- <!--   </script>  -->


<script type="text/javascript">

	
function cancelok(){
	if(confirm("정말,, 삭제하시겠습니까?"))
	{
		alert("삭제 되었습니다.");
	return true;
	} else {
	return false;

	}
	
}


		

</script>

<div class="text">


<div class="my">
<h3>내 정보</h3>
</div>

<div class="m">

<h2 style="color: #263959;">이름 : ${detail.userName }</h2>
<h4 style="color: #52616a;">닉네임 : ${detail.userNick }</h4>
<h4 style="color: #52616a;">아이디 : ${detail.userId }</h4>
<h4 style="color: #52616a;">Phone : ${detail.phone }</h4>

</div> <!-- m -->



<c:choose>
<c:when test="${empty hospital }">

<div class="myp">
<h3>펫 정보</h3>
</div>

<div class="pe">



		<c:forEach var="Info" items="${petInfo}">
		<c:forEach items="${petDetail}" var="Detail"  >
			<c:choose>
			<c:when test="${Info.petNo  eq Detail.petNo}">
	<a href="./petUpdate?petNo=${Detail.petNo }"><img alt=".." src="<%=request.getContextPath()%>/petfile/${Detail.storedName}" width="90px" height="90px" class="img"></a>
			</c:when>
			</c:choose>
		</c:forEach>
	<form action="./delete" method="post" onsubmit="return cancelok();">
			<h2 style="color: #263959;">이름 : ${Info.petName }</h2>
			<h4 style="color: #52616a;">종류 : ${Info.type }</h4>
			<input name="petNo" value="${Info.petNo }" type="hidden">
<%-- 			<input type="checkbox" id="delete" name="delete" class="delete" value="${Info.petNo }"> --%>
			<button class="deleteBtn">삭제</button>
	</form>
		</c:forEach>
		<div id="allDelete">
<!-- 			<input type="checkbox" id="checkall" name="checkall" class="checkall">전체 선택 -->
		</div> <!-- allDelete -->
</div> <!-- pe -->


</c:when>

</c:choose>



<div class="r">

<a href="../unregist/cheak" class="ur">회원탈퇴</a>

</div>

















</div>







