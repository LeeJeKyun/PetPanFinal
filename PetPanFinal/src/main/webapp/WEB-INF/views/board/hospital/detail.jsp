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
	<img id = "img" alt="�̹���" src="<%=request.getContextPath() %>/upload/${map.STOREDNAME}">
	
	<h2>${map.HOSPITALNAME } <span style = "font-size: 0.6em; color: #6c6a6a;">��������</span>
	<c:if test="${map.MAMMALIA eq 'y'} ">
		<span>������</span>
	</c:if>
	<c:if test="${map.RODENT eq 'y'} ">
		<span>��ġ��</span>
	</c:if>
	<c:if test="${map.BIRDS eq 'y'} ">
		<span>����</span>
	</c:if>
	<c:if test="${map.REPTILE eq 'y'} ">
		<span>�����</span>
	</c:if>
	</h2>
	<div>� �ð� : ${map.OPEN } ~ ${map.CLOSE }</div>
	<div >&#9742;��ȭ��ȣ : <span id = "phone"></span></div>
	<div>�̸��� : <span>${map.EMAIL }</span></div>
	<div>�츮������ �������� �Ÿ�.. <span class = "gray-font"><fmt:formatNumber value = "${map.distance }" pattern=".0" />km</span></div>
	<div>���� ��ġ : <span>${map.ADDRESS } ${map.DETAILADDRESS }</span></div>
	<div id = "map-area"> ���� api </div>
	<div id = "hospital-info">��ǥ�ڸ� : <span>${map.USERNAME }</span> | ����� ��ȣ : <span id = "code"></span></div>
</div>

<c:import url = "../../layout/footer.jsp" />