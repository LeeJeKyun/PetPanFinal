
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="./layout/header.jsp" />

<style type="text/css">
.main {
	float: right;
}

/* #slick div { */
/* 	width: 500px; */
/* 	height: 500px; */
/* } */

#slick div img {
	width: 100%;
	height: 300px;
	margin: auto;
}

#H{
	text-align: center;
	margin-top: 50px;
	color: #FF5050;
}

#Hdiv{
	text-align: center;
/* 	margin-top: 50px; */
}



#search {
	border-radius: 5px;
	border: 1.5px solid #FF5050;
}

#field{
 	border: none;
	width: 250px;
	height: 50px;
	text-align: center;
	margin: 0px 42%;
}


</style>

<script type="text/javascript">

$(function(){
    $('#slick').slick({
//   	  dots: true,
//   	  infinite: true,
//   	  speed: 500,
//   	  fade: true,
//   	  cssEase: 'linear'

//   		  lazyLoad: 'ondemand',
//   		  slidesToShow: 3,
//   		  slidesToScroll: 1

//   		  dots: true,
//   		  infinite: true,
//   		  speed: 300,
//   		  slidesToShow: 1,
//   		  adaptiveHeight: true
  	  
    	  dots: true,
    	  infinite: true,
    	  speed: 500,
    	  fade: true,
    	  cssEase: 'linear'
    });
  })


</script>



<div id="slick">

<div><img src="<%=request.getContextPath()%>/resources/member/img/뽀비.jpg"></div>
<div><img src="<%=request.getContextPath()%>/resources/member/img/연두.jpg"></div>
<div><img src="<%=request.getContextPath()%>/resources/member/img/써니.jpg"></div>
<div><img src="<%=request.getContextPath()%>/resources/member/img/갱얼쥐.jpg"></div>
<div><img src="<%=request.getContextPath()%>/resources/member/img/사슴.jpg"></div>

</div>



<h1 id="H">병원 검색</h1>
<form action=".list/" method="post" >

<fieldset id="field">
	<div id="Hdiv">
	<input type="text" id="search">
	<img src="<%=request.getContextPath()%>/resources/member/img/검색.png"  width="20px" height="20px" class="img">
	</div>
</fieldset>

</form>






<div class="main">

<!-- userId eq null || email eq null -->

<c:choose>
<c:when test="${login eq null  }">
<%-- <h2>세션 상태: ${not empty login}</h2> --%>
<button onclick="location.href='/member/login/join'">회원가입</button>
<button onclick="location.href='/member/login/login'">로그인</button>
</c:when>

<c:when test="${login eq true  }">
<%-- <h2>세션 상태: ${login eq true }</h2> --%>
<h2>안녕하세요, ${userName}님</h2>
<button onclick="location.href='/member/mypage/mypage'">마이페이지</button>
<button onclick="location.href='/member/login/logout'">로그아웃</button>
</c:when>

</c:choose>












</div>

<c:import url="./layout/footer.jsp" />







