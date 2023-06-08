
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

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
	z-index: 2;
}

#H{
	text-align: center;
	margin-top: 50px;
	color: #FF5050;
	font-size: 30px;
	font-weight: 550;
}

#Hot {
	text-align: center;
	margin-top: 25px;
	color: #FF5050;
	font-size: 30px;
	font-weight: 550;
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

.bb {
	float: left;
}

.bb ul {
	text-decoration: none;
	color: #FF5060;
    list-style:none;
    margin:0;
    padding:0;
}

.bb li {
    margin: 0 0 0 0;
    padding: 0 0 0 0;
    border : 0;
    float: left;
}

#old {
	margin-left: 500px;
 	margin-right: 500px; 
}

#free {
	margin-left: 300px;
}

#free2 {
	margin-left: 100px;
}

.th-border{
	border-top: solid #FFDAD7 3px ; 
	border-bottom:solid #FFDAD7 3px ;
}
 .td-style{ 
 	text-align: center; 
 	border-bottom: solid #FFDAD7 2px;
 } 



.clearbox {
	height: 0;
	overflow: hidden;
	line-height: 0;
	clear: both;
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
<div><img src="<%=request.getContextPath()%>/resources/member/img/아라.jpg"></div>
<div><img src="<%=request.getContextPath()%>/resources/member/img/토토.jpg"></div>

</div>



<p id="H">병원 검색</p>
<form action=".list/" method="post" >

<fieldset id="field">
	<div id="Hdiv">
	<input type="text" id="search">
	<a href="<%=request.getContextPath()%>/board/hospital/list"><img src="<%=request.getContextPath()%>/resources/member/img/검색.png"  width="20px" height="20px" class="img"></a>
	</div>
</fieldset>

</form>


<div class="hot">
<p id="Hot">인기 게시글</p>

	<div class="bb">
	<ul>
		<li>
		<p id="free">자유 게시판</p>
		</li>
		<li>
		<p id="old">중고 거래</p>
		</li>
	</ul>
	</div>
	

</div> <!-- hot -->


<table class = "table-list" style = "width:500px" border-collapse: collapse">
	<tr class = "th-border items">
		<th>글 번호</th>
		<th>글 제목	</th>
		<th>조회수</th>
	</tr>


<c:forEach items="${free }" var="free" >
	<tr class = "td-style items">
		<td> ${free.boardNo }</td>
		<td><a href="../../board/board/detail?boardNo=${free.boardNo }"> ${free.boardTitle }</a></td>
		<td class = "date">${free.hit }</td>
	</tr>
</c:forEach>

</table>





<%-- <c:forEach items="${free }" var="free" > --%>
<%-- <%-- 	<h2 style="color: #263959;">${free.boardNo }</h2> --%> 
<%-- 	<h4 style="color: #52616a;">${free.boardNo } <a href="">${free.boardTitle } </a></h4> --%>
<%-- </c:forEach> --%>






<div class="hot">
<p id="Hot">새로 나온 게시글</p>

	<div class="bb">
	<ul>
		<li>
		<p id="free2">자유 게시판</p>
		</li>
		<li>
		<p id="old">중고 거래</p>
		</li>
		<li>
		<p id="poom">품앗이</p>
		</li>
	</ul>
	</div>
</div> <!-- hot -->


<div class="clearbox">
</div>









<div class="main">

<!-- userId eq null || email eq null -->

<c:choose>
<c:when test="${login eq null || login eq false  }">
<%-- <h2>세션 상태: ${not empty login}</h2> --%>
<button onclick="location.href='/member/login/join'">회원가입</button>
<button onclick="location.href='/member/login/login'">로그인</button>
</c:when>


<c:when test="${login eq true  }">
<%-- <h2>세션 상태: ${login eq true }</h2> --%>


<c:forEach items="${petDetail }" var="petDetail" >
<img alt=".." src="<%=request.getContextPath()%>/petfile/${petDetail.storedName}" width="90px" height="90px" class="img">
</c:forEach>
<%-- <img alt=".." src="<%=request.getContextPath()%>/petfile/${petDetail.storedName}" width="90px" height="90px" class="img"> --%>
<h3>${detail.userName}님</h3>
<button onclick="location.href='/admin/main'">관리자페이지</button>
<button onclick="location.href='/member/mypage/mypage'">마이페이지</button>
<button onclick="location.href='/member/login/logout'">로그아웃</button>
</c:when>

</c:choose>








</div> <!-- main -->









<c:import url="./layout/footer.jsp" />







