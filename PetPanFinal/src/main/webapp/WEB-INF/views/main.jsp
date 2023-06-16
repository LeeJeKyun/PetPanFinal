
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<c:import url="./layout/header.jsp" />


<style type="text/css">

.find a {
	text-decoration: none;
	color: #606060;
	font-size:12px;
}

.find {
	text-align: center;
}

.mypage a {
	text-decoration: none;
	color: #606060;
	font-size:12px;
}

.mypage {
	text-align: center;
	padding: 30px 30px 20px;
}
.main {
	float: right;
	border: 1px solid #ccc;
	padding: 21 10 10;
	
}

#slick div img {
	width: 100%;
	height: 400px;
	margin: auto;
	z-index: 2;
}

#H{
	text-align: center;
	margin-top: 40px;
	color: #FF5050;
	font-size: 30px;
	font-weight: 550;
	margin-bottom: 10px;
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
}

#search {
	border-radius: 5px;
	border: 2px solid #FFA2AD;
	width: 450px;
	height: 33px;
}

#field{
 	border: none;
	width: 600px;
	height: 50px;
	text-align: center;
	margin-left: 40px;
}

.bb {
	float: left;
}

.bb ul {
	text-decoration: none;
	color: #464646;
    list-style:none;
    margin:0;
    padding:0;
}

.bb li {
	font-weight: border;
    margin: 0 0 0 0;
    padding: 0 0 0 0;
    border : 0;
    float: left;
}

#old {
	margin-left: 1050px;
 	margin-right: 250px; 
}

#old2 {
	margin-left: 370px;
 	margin-right: 390px; 
}

#free {
	margin-left: 300px;
}

#free2 {
	margin-left: 290px;
}

.th-border{
	border-top: solid #FFDAD7 3px ; 
	border-bottom:solid #FFDAD7 3px ;
}
 .td-style{ 
 	text-align: center; 
 	border-bottom: solid #FFDAD7 2px;
 } 


.freeTable{

	float:left;
	width:50%;
}

.freeTable a{
	color: #606060;
}

.oldTable{
	float:left;
	width:50%;
}

.oldTable a{ 
	color: #606060;
}

.newFreeTable{

	float:left;
	width:30%;
}

.newFreeTable a{
	color: #606060;
}


.newOldTable{
	float:left;
	width:30%;
}

.newOldTable a{
	color: #606060;
}

.poomTable{
	float:left;
	width:30%;
}

.poomTable a{
	color: #606060;
}


.clearbox {
	height: 0;
	overflow: hidden;
	line-height: 0;
	clear: both;
}

#btn{
   background-color: #FFDAD7;
   color : #FF5050;
   border-radius: 7px;
   border-color: #FFDAD7;
   width: 300px;
   height: 55px;
   
}

.Nick{
	font-weight:bold;
	margin: 0;
	font-size: 18px;
}

.email {
	margin: 5px;
	font-size: small;
}
</style>


<style type="text/css">

#hospital {
	float: left;
	margin-left: 28%;
}
#gridWrap{
	display: grid;
	grid-template-columns: 500px 500px;
	grid-column-gap: 200px;
	margin-top: 20px;
}
#grid1{
	display: grid;
	grid-template-columns: 120px 240px 120px;
	width: 500px;
	margin: 0 auto;
	position: relative;
}

#grid2{
	display: grid;
	grid-template-columns: 120px 240px 120px;
	width: 500px;
	margin: 0 auto;
	position: relative;
}

.th{
	border-top: 2px solid pink;
	border-bottom: 1px solid pink;
	text-align: center;
}
.td{
	border-bottom: 1px solid pink;
	height: 25px;
	text-align: center;
}	
#middleWrap{
	width:1500px;
	display: flex;
	justify-content: center;
}
.title{
	width: 500px;
}
#oldet{
	top: -35px;
	left: 180px;
	position: absolute;
}
#freet{
	top: -35px;
	left: 180px;
	position: absolute;
}


#btn22{
   background-color: #FFDAD7;
   color : #FF5050;
   border-radius: 7px;
   border-color: #FFDAD7;
   width: 50px;
   height: 35px;
   
}


</style>


<style type="text/css">

.poomPhoto {
	width: 240px;
	height: 180px;
}

.poomTable {
	width:1500px;
	display: flex;
	justify-content: center;
}


#mid {
	display: grid;
	grid-template-columns: 736px 400px 400px;
/* 		grid-column-gap: 30px; */
	text-align: center;
	
}

#grid {
	display: grid;
	grid-template-columns: 550px 400px 400px;
/* 		grid-column-gap: 60px; */
	margin-top: 20px;
		text-align: center;
	
}

#photo {
	margin: 0 auto;
}

#title {
	margin: 0 auto;
}
/* #grid4{ */
/* 	display: grid; */
/* 	grid-template-columns: 120px 240px 120px; */
/* 	width: 500px; */
/* 	margin: 0 auto; */
/* 	position: relative; */
/* } */





</style>

<script type="text/javascript">

$(function(){
    $('#slick').slick({
  	  
    	  dots: true,
    	  infinite: true,
    	  speed: 500,
    	  fade: true,
    	  cssEase: 'linear'
    });
  })


</script>





<div id="slick">

<div><img src="<%=request.getContextPath()%>/resources/member/img/petpan1.jpg"></div>
<div><img src="<%=request.getContextPath()%>/resources/member/img/뽀비.png"></div>
<div><img src="<%=request.getContextPath()%>/resources/member/img/연두.jpg"></div>
<div><img src="<%=request.getContextPath()%>/resources/member/img/써니.jpg"></div>
<%-- <div><img src="<%=request.getContextPath()%>/resources/member/img/갱얼쥐.jpg"></div> --%>
<div><img src="<%=request.getContextPath()%>/resources/member/img/갱쥐.png"></div>
<div><img src="<%=request.getContextPath()%>/resources/member/img/사슴.jpg"></div>
<div><img src="<%=request.getContextPath()%>/resources/member/img/아라.jpg"></div>	
<div><img src="<%=request.getContextPath()%>/resources/member/img/토토.jpg"></div>

</div>


<div id="hospital">
	<p id="H">병원 검색</p>
	<form action="<%=request.getContextPath()%>/board/hospital/list?search=${paging.search}" method="get" >
	
	<fieldset id="field">
<!-- 		<div id="Hdiv"> -->
	<!-- 	<input type="text" id="search"> -->
		<input type="text" id="search" name="search"  value = "${paging.search }">
		<button id="btn22">검색</button>
<%-- 		<button><img src="<%=request.getContextPath()%>/resources/member/img/검색.png"  width="35px" height="27px" class="img"></button> --%>
	<%-- 	<button><img src="<%=request.getContextPath()%>/resources/member/img/검색.png"  width="30px" height="25px" class="img"></a> --%>
<!-- 		</div> -->
	</fieldset>
	
	</form>

</div>


<div class="main">


	<c:choose>


	<c:when test="${login eq null || login eq false  }">
	<button onclick="location.href='/member/login/login'" id="btn">로그인</button>
	   <div class="find">
	      <a href="/member/login/id" >아이디 찾기 | </a>
	      <a href="/member/login//pw" >비밀번호 찾기 | </a>
	      <a href="/member/login/join" >회원가입</a>
	</div>
	</c:when>



	<c:when test="${login eq true and empty mgr }">
	<%-- <h2>세션 상태: ${login eq true }</h2> --%>
	   <div class="mypage">
	
	
	<c:forEach items="${petDetail }" var="petDetail" >
		<img alt=".." src="<%=request.getContextPath()%>/petfile/${petDetail.storedName}" width="90px" height="90px" class="img">
	</c:forEach>
	<p class="Nick">${detail.userNick}님</p>
	
	
	
	<c:choose>
	
	<c:when test="${doread eq 0 }">
		<div style="color:F606060;"><a href="/message/message/list" >쪽지</a></div>
	</c:when>
	<c:when test="${doread > 0}"  >
		<div ><a href="/message/message/list" ><span style="color:#FF5050;">쪽지 ${doread}</span></a></div>
	</c:when>
	
	</c:choose>
	
	
	<div>
	<p class="email">${detail.email}</p>
	</div>
	
		<a href="/member/mypage/mypage">마이페이지 |</a>
		<a href="/member/login/logout">로그아웃</a>
	</div>
	</c:when>
	
	
	<c:when test="${ not empty mgr and mgr}">
		
	<p class="Nick">${detail.userNick}님</p>
	<div>
	<p class="email">${detail.email}</p>
	</div>
		<a href="/admin/main">관리자페이지 |</a>
		<a href="/member/login/logout">로그아웃</a>
		
	
	</c:when>

</c:choose>



</div> <!-- main -->


<div class="clearbox"></div>
<a href="/board/care/list" style="margin-left: 1200px;">더보기</a>
<div class="poomTable">
	<div id="mid">
		<div id="grid">
<!-- 			<div id="grid4"> -->
				<c:forEach var="content" items="${poom}">
				<c:forEach var="photo" items="${poomPhoto}" >
					<c:choose>
					<c:when test="${content.boardNo  eq photo.boardno}">
					<div id="photo"><a href="../../board/board/detail?boardNo=${content.boardNo }"><img alt=".." src="<%=request.getContextPath() %>/upload/${photo.storedName}"  class="poomPhoto"></a>
					<div id="title"><a href="../../board/board/detail?boardNo=${content.boardNo }">${content.boardTitle }</a></div>
					</div>
					</c:when>
					</c:choose>
				</c:forEach>
				</c:forEach>
					
			</div><!-- drid2 -->
		</div><!-- grid -->
	
	</div><!-- mid -->
<!-- </div> -->





<div class="clearbox"></div>


<div class="hot">
<p id="Hot">인기 게시글</p>

<div id="middleWrap">
	<div id="gridWrap">
		<div id="grid1">
			<div id="freet" class="title">자유 게시판 <a href="/board/board">더보기</a></div>
			<div class="th">글번호</div>
			<div class="th">글제목</div>
			<div class="th">조회수</div>
			<c:forEach items="${free }" var="free" >
					<div class="td"> ${free.boardNo }</div>
					<div class="td"><a style="color:#606060;" href="../../board/board/detail?boardNo=${free.boardNo }"> ${free.boardTitle }</a></div>
					<div class="td">${free.hit }</div>
			</c:forEach>
		</div>
		<div id="grid2">
			<div id="oldet" class="title">중고 거래 <a href="/board/board?category=3">더보기</a></div>
			<div class="th">글번호</div>
			<div class="th">글제목</div>
			<div class="th">조회수</div>
			<c:forEach items="${old }" var="old" >
					<div class="td"> ${old.boardNo }</div>
					<div class="td"><a style="color:#606060;" href="../../board/board/detail?boardNo=${old.boardNo }"> ${old.boardTitle }</a></div>
					<div class="td">${old.hit }</div>
			</c:forEach>
		</div>
	</div>
</div>

	
</div>




<div class="clearbox"></div>
<div class="clearbox"></div>
<div class="clearbox"></div>








<c:import url="./layout/footer.jsp" />







