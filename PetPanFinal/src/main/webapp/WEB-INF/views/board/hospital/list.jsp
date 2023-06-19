<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
#searchBox{
	width: 500px;
	height: 50px;
	margin: 0 auto;
}
#map-area{
	width: 1000px;
	height: 500px;
}
</style>
<script type="text/javascript">
$(function(){

	$("option[value='" + ${paging.radius} + "']").prop("selected", true);
	
	$("#choose").click(function(){
		if($("#choose").is(":checked") ){
			$("#animals").css("display", "");
		}else{
			$("#animals").css("display", "none");
		}
	})
	if(${fn:contains(paging.rodent, 'y')} ||
			${fn:contains(paging.reptile, 'y')} ||
			${fn:contains(paging.mammalia, 'y')} ||
			${fn:contains(paging.birds, 'y')}){
		$("#choose").prop("checked", true);
	}
	if($("#choose").is(":checked") ){
		$("#animals").css("display", "");
	}else{
		$("#animals").css("display", "none");
	}
	if(${fn:contains(paging.rodent, 'y')} ){
		$("[name='rodent']").prop("checked", true)
	}
	if(${fn:contains(paging.reptile, 'y')} ){
		$("[name='reptile']").prop("checked", true)
	}
	if(${fn:contains(paging.mammalia, 'y')} ){
		$("[name='mammalia']").prop("checked", true)
	}
	if(${fn:contains(paging.birds, 'y')} ){
		$("[name='birds']").prop("checked", true)
	}
	$("#reset").click(function(){
		console.log("reset clicked")
		$("input[name='search']").attr("value", "");
	})
})
//--------------------------------------------------------------------------------

</script>

<div id = "container">
	<h2>병원</h2>
	<c:if test="${hospitalList != null }">
 	<c:forEach var = "i" items = "${hospitalList }" varStatus="c">
	<div class = "block">
		<div class = "img" data-hospitalNo = ${i.HOSPITALNO }>
			<a class = "hosPicture" href = "./detail?hospitalNo=${i.HOSPITALNO}">
				<img alt="이미지" src="<%=request.getContextPath() %>/upload/${i.STOREDNAME}">
			</a>
		</div>
		<div class = "info-name">
			<h4>${i.HOSPITALNAME }</h4>
		</div>
		<div class = "info">
			<div>운영시간: ${i.OPEN } ~ ${i.CLOSE }</div>
			<c:set var = "phone" value = "${i.PHONE }" />
			<div>전화번호: 
				<span class = "phone">
					${fn:substring(phone, 0, 3) }-${fn:substring(phone, 3, 7) }-${fn:substring(phone, 7, 11) }
				</span>
			</div>
			<div>특수동물 가능</div>
 			<c:if test="${i.MAMMALIA eq 'y' }"> 
				<span class = "name">포유류</span>
 			</c:if> 
 			<c:if test="${i.REPTILE eq 'y' }"> 
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
	</c:if>
	<form action = "./list" method = "get" id = "searchBox">
		<select name = "radius" id = "radius" style = "display:inline-block">
			<option value = "0">전체</option>
			<option value = "1">2km 반경</option>
			<option value = "2">4km 반경</option>
			<option value = "3">6km 반경</option>
			<option value = "4">8km 반경</option>
			<option value = "5">10km 반경</option>
		</select>
		<div style = "display:inline-block">
			<label><input type = "checkbox" id = "choose">종 선택</label>
		</div>
		<input type = "text" name = "search" placeholder = "검색할 병원을 입력하세요" value = "${paging.search }">
		<button>검색</button>
		<div >
			<button type = "reset" id = "reset">검색어 초기화</button>
		</div>
		<div id = "animals" style = "display: none;">
			<label><input type = "checkbox" name = "reptile" value = "y">파충류</label>
			<label><input type = "checkbox" name = "mammalia" value = "y">포유류</label>
			<label><input type = "checkbox" name = "rodent" value = "y">설치류</label>
			<label><input type = "checkbox" name = "birds" value = "y">조류</label>
		</div>
	</form>
</div>
<c:import url = "./paging_hospital.jsp" />
<c:import url = "../../layout/footer.jsp" />