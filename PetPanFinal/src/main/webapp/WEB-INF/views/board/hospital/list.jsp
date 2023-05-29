<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<c:import url = "../../layout/header.jsp" />

<link rel ="stylesheet" href = "<%= request.getContextPath() %>/resources/hospital/list.css" />

<script type="text/javascript">
$(function(){
	
})
</script>

<div id = "container">
	<h2>����</h2>
	<div class = "block">
		<div class = "img">
			<a href = "./detail?${i.hospitalNo }">
				<img alt="�̹���" src="<%=request.getContextPath() %>/upload/${i.storedName}">
			</a>
		</div>
		<div class = "info-name">
			<h4>�����̸�</h4>
		</div>
		<div class = "info">
			<div>��ð�: 9:00 ~ 18:00</div>
			<div>��ȭ��ȣ: 031-123-6789</div>
			<div>Ư������ ����</div>
		</div>
	</div>
</div>
<c:import url = "./paging_hospital.jsp" />
<c:import url = "../../layout/footer.jsp" />