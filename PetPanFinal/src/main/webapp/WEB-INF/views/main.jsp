
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
	height: 500px;
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



<div id="H">
병원 검색  <input type="text">
</div>










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







