<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<c:import url = "../../layout/header.jsp" />

<link rel ="stylesheet" href = "<%= request.getContextPath() %>/resources/hospital/list.css" />
<style>
#enroll-area{
	text-align: right;
}
#enroll-area a{
	display:inline-block;
	text-align:center;
	width: 114px;
	height: 30px;
	background-color: #ffdad7;
	border-radius: 10px 10px 10px 10px;
	padding-top: 10px;
}
.name{
	font-size: 0.8em;
	color: #6d6db6;
	text-decoration: underline;
}
.hosPicture img{
	width: 300px;
	height: 200px;
}
</style>
<script type="text/javascript">
$(function(){
	$("#radius option:eq(${paging.radius})").prop("selected", true);
	$("#choose").click(function(){
		if($("#choose").is(":checked") ){
			$("#animals").css("display", "");
		}else{
			$("#animals").css("display", "none");
		}
	})
})
</script>

<div id = "container">
	<h2>병원</h2>
	
 	<c:forEach var = "i" items = "${hospitalList }" >
	<div class = "block">
		<div class = "img" data-hospitalNo = ${i.HOSPITALNO }>
			<a class = "hosPicture" href = "./detail?hospitalNo=${i.HOSPITALNO}">
				<img alt="이미지" src="<%=request.getContextPath() %>/upload/${i.STOREDNAME}">
			</a>
		</div>
		<div class = "info-name">
			<h4>병원이름${i.HOSPITALNAME }</h4>
		</div>
		<div class = "info">
			<div>운영시간: ${i.OPEN } ~ ${i.CLOSE }</div>
			<div>전화번호: ${i.PHONE }</div>
			<div>특수동물 가능</div>
 			<c:if test="${i.MAMMALIA eq 'y' }"> 
				<span class = "name">포유류</span>
 			</c:if> 
 			<c:if test="${i.REPTILIA eq 'y' }"> 
				<span class = "name">파충류</span>
 			</c:if> 
 			<c:if test="${i.RODENT eq 'y' }"> 
				<span class = "name">설치류</span>
			</c:if>
			<c:if test="${i.BIRDS eq 'y' }">
				<span class = "name">조류</span>
			</c:if>
		</div>
	</div>
	</c:forEach>
	<form action = "./list" method = "get">
		<select name = "radius" id = "radius">
			<option value = "0">전체</option>
			<option value = "1">2km 반경</option>
			<option value = "2">4km 반경</option>
			<option value = "3">6km 반경</option>
			<option value = "4">8km 반경</option>
			<option value = "5">10km 반경</option>
		</select>
		<div>
		<label><input type = "checkbox" id = "choose">종 선택</label>
		<div id = "animals" style = "display: none;">
			<label><input type = "checkbox" name = "reptile" value = "y">파충류</label>
			<label><input type = "checkbox" name = "mammlia" value = "y">포유류</label>
			<label><input type = "checkbox" name = "rodent" value = "y">설치류</label>
			<label><input type = "checkbox" name = "birds" value = "y">조류</label>
		</div>
	</div>
		<input type = "text" name = "search" placeholder = "검색할 병원을 입력하세요" value = "${paging.search }">
		<button>검색</button>
		
	</form>
</div>
<c:import url = "./paging_hospital.jsp" />
<c:import url = "../../layout/footer.jsp" />