
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<c:import url="../../layout/header.jsp" />


<style type="text/css">

.btn{
   background-color: #FFDAD7;
   color : #FF5050;
   border-radius: 7px;
   border-color: #FFDAD7;
   width: 170px;
   height: 35px;
   margin-top: 40px;
}


h1 {

	text-decoration: none;
	color: #FF5050;
	line-height: 3em;
}

</style>





<h1>그동안 감사했습니다.</h1>

<button id="button" class="btn" type="button" onclick="location.href='<%=request.getContextPath() %>/'">PetPan홈으로</button>




<c:import url="../../layout/footer.jsp" />







