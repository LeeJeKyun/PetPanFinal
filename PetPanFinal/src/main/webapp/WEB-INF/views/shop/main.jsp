
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/header.jsp" />

<style>

section#content ul li {
	display: inline-block;
	margin: 10px;
}

section#content div.shoping img{
	width: 200px;
	height: 200px;
}

section#content div.shopname{
	padding: 10px 0;
	text-align: center;
}

section#content div.shopname a{
	color : #000;
}

section#content{
width: 1000px;
margin: 0 auto;

}
</style>


<br><br>
<div>

<section id="container">

	<div id="container_box">
	
	<section id="content">
	
	
	<ul>
	   <c:forEach items="${list}" var="list">
	   <li>
	    <div class="shoping">
	    <a href="/shop/view?objectno=${list.objectno}"><img src=""></a>
	    </div>   
	    <div class="shopname">
	     <a href="/shop/view?objectno=${list.objectno}">${list.name}</a>
	    </div>
	   </li>
	   </c:forEach>
	</ul>
	
	</section>
	
	</div>

</section>

</div>

<c:import url="../layout/shoppaging.jsp"/>

<c:import url="../layout/footer.jsp" />







