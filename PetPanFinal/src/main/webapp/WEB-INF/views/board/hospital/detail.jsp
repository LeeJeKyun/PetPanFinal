<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<c:import url = "../../layout/header.jsp" />
<style>
#container{
	width: 800px;
	margin: 0 auto;
	margin-top: 200px;
}	
#img{
	width:800px;
	height: 300px;
}
#container div{
	margin-bottom: 10px;
}
.gray-font{
	color: #6c6a6a;
}
#map-area{
	width: 800px;
	height: 400px;
}
#hospital-info{
	font-size: 0.8em;
}
</style>
<script type="text/javascript">
$(function(){
	var phone = '${map.PHONE}'
	var f = String(phone).substring(0, 3);
	f += '-';
	var m = String(phone).substring(3, 7);
	m += '-'
	var e = String(phone).substring(7, phone.length);
	console.log(f + m + e);
	$("#phone").html(f + m + e)
	
	var hospitalCode = '${map.HOSPITALCODE}'
	f = String(hospitalCode).substring(0, 3);
	f += '-';
	m = String(hospitalCode).substring(3, 5);
	m += '-';
	e = String(hospitalCode).substring(5, 10);
	$("#code").html(f + m + e)
})
</script>
<div id = "container">
	<img id = "img" alt="이미지" src="<%=request.getContextPath() %>/upload/${map.STOREDNAME}">
	
	<h2>${map.HOSPITALNAME } <span style = "font-size: 0.6em; color: #6c6a6a;">동물병원</span>
	<c:if test="${map.MAMMALIA eq 'y'} ">
		<span>포유류</span>
	</c:if>
	<c:if test="${map.RODENT eq 'y'} ">
		<span>설치류</span>
	</c:if>
	<c:if test="${map.BIRDS eq 'y'} ">
		<span>조류</span>
	</c:if>
	<c:if test="${map.REPTILE eq 'y'} ">
		<span>파충류</span>
	</c:if>
	</h2>
	<div>운영 시간 : ${map.OPEN } ~ ${map.CLOSE }</div>
	<div >&#9742;전화번호 : <span id = "phone"></span></div>
	<div>이메일 : <span>${map.EMAIL }</span></div>
	<div>우리집에서 병원까지 거리.. <span class = "gray-font"><fmt:formatNumber value = "${map.distance }" pattern=".0" />km</span></div>
	<div>병원 위치 : <span>${map.ADDRESS } ${map.DETAILADDRESS }</span></div>
	<div id = "map-area"> 지도 api </div>
	<div id = "hospital-info">대표자명 : <span>${map.USERNAME }</span> | 사업자 번호 : <span id = "code"></span></div>
</div>

<c:import url = "../../layout/footer.jsp" />