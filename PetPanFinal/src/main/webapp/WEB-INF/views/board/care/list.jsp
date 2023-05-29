
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<c:import url="../../layout/header.jsp" />

<style type="text/css" >
#boardselect{
	bottom: 6px;
	left: 30px;
	font-size:15px;
	width: 100px;
	padding-left: 4px;
}
</style>
<script type="text/javascript">
function searchInit(){
// 	console.log("클릭했다")
	$("#search").val('');
	$("#searchBtn").click();
}
</script>

<br>

<div style="width: 1500px; margin:0 auto;">

<div style="text-align: left; width: 1100px; margin: 0 auto;">
	<span style="font-size: 2em; font-weight: bold;">품앗이</span>
	<select name="boardselect" id="boardselect" onchange="location.href=(this.value);" style="position: relative; height: 30px; font-size: 16px;">
	<option value="" selected="selected">품앗이</option>
	<option value="">임시보호</option>
	<option value="" >유기동물</option>
</select>
</div>

<br>

<table style="margin: 0 auto; width: 1000px; text-align: center; border-collapse: collapse;">
	<tr style="height: 50px">
		<th>번호</th>
		<th></th>
		<th>제목</th>
		<th>글쓴이</th>
		<th>등록일</th>
		<th>조회수</th>
		<th>추천수</th>
	</tr>
<c:forEach items="${list }" var="care">
	<tr style="height: 40px; border-top: 2px solid #f5cbcb;">
		<td>${care.BOARDNO }</td>
		<td style="height: 90px;"><img width="120px" height="90px" alt="아가사진" src="<%=request.getContextPath() %>/upload/${care.STOREDNAME}" style="vertical-align: middle;" ></td>
		<td><a href="../care/view?boardNo=${care.BOARDNO }">${care.BOARDTITLE }</a></td>
		<td>${care.USERID }</td>
		<td><fmt:formatDate value="${care.WRITEDATE }" pattern="yyyy-MM-dd" /></td>
		<td>${care.HIT }</td>
		<td>${care.RECOMMEND }</td>
	</tr>
</c:forEach>
</table>

<br>
<div style="width: 1000px; margin:0 auto;">
	<form action="./list" method="get">
	<table>
	<tr>
		<td style="width: 10%">
		<c:if test="${login eq true }">
		<a href="./write">
			<button type="button"
			style="width: 85px; height: 33px; font-size: 17px; font-weight: bold; background-color: #f5cbcb; border-radius: 10px 10px 10px 10px / 10px 10px 10px 9px; border: none; color: #FF5050; cursor: pointer;">
				글쓰기
			</button>
		</a>
		</c:if>
		</td>
		<td style="width: 80%; text-align: right;">
			<input type="text" id="search" name="search" value="${paging.search }" style=" width:290px; height: 24px; border: 1px solid #ccc;">
			
		</td>
		<td style="width: 6%">
			<button type="submit" id="searchBtn"
			style="width: 60px; height:30px; font-size: 14px; font-weight: bold; background-color: #f5cbcb; border-radius: 10px 10px 10px 10px / 10px 10px 10px 9px; border: none; color: #FF5050; cursor: pointer;">
				검색
			</button>
		</td>
		<td>
			<button type="button" onclick="searchInit()"
			style="width: 60px; height:30px; font-size: 14px; font-weight: bold; background-color: #f5cbcb; border-radius: 10px 10px 10px 10px / 10px 10px 10px 9px; border: none; color: #FF5050; cursor: pointer;">
				초기화
			</button>
		</td>
	</tr>
	</table>
	</form>
	<br>
</div>

<c:import url="./paging.jsp" />

<!-- 컨텐츠영역 끝 -->
</div>

<c:import url="../../layout/footer.jsp" />







