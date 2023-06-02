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
</style>
<script type="text/javascript">
$(function(){
	
})
</script>

<div id = "container">
	<h2>����</h2>
	<c:if test="${user.positionNo == 2}">
	<div id = "enroll-area">
		<a href = "./enroll">���� ���� ���</a>
	</div>
	</c:if>
	<div class = "block">
<%-- 		<c:forEach var = "i" items = "${list }"> --%>
		<div class = "img">
			<a href = "./detail?${i.HOSPITALNO}">
				<img alt="�̹���" src="<%=request.getContextPath() %>/upload/${i.STOREDNAME}">
			</a>
		</div>
		<div class = "info-name">
			<h4>�����̸�${i.HOSPITALNAME }</h4>
		</div>
		<div class = "info">
			<div>��ð�: ${i.OPEN } ~ ${i.CLOSE }</div>
			<div>��ȭ��ȣ: ${i.PHONE }</div>
			<div>Ư������ ���� ���</div>
<%-- 			<c:if test="${i.MAMMALIA eq 'y' }"> --%>
				<span class = "name">������</span>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${i.REPTILIA eq 'y' }"> --%>
				<span class = "name">�����</span>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${i.RODENT eq 'y' }"> --%>
				<span class = "name">��ġ��</span>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${i.BIRDS eq 'y' }"> --%>
				<span class = "name">����</span>
<%-- 			</c:if> --%>
		</div>
<%-- 		</c:forEach> --%>
	</div>
</div>
<c:import url = "./paging_hospital.jsp" />
<c:import url = "../../layout/footer.jsp" />