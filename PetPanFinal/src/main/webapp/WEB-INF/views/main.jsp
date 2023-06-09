
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<c:import url="./layout/header.jsp" />


<style type="text/css">
.main {
	float: right;
	border: 1px solid #ccc;
	padding: 21 10 10;
	
}

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
	margin: 0px 29%;
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
	margin-left: 500px;
 	margin-right: 500px; 
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

.select a{
	color: #606060;
	font-size:12px;
	
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
   height: 35px;
   
}

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

<div><img src="<%=request.getContextPath()%>/resources/member/img/뽀비.jpg"></div>
<div><img src="<%=request.getContextPath()%>/resources/member/img/연두.jpg"></div>
<div><img src="<%=request.getContextPath()%>/resources/member/img/써니.jpg"></div>
<div><img src="<%=request.getContextPath()%>/resources/member/img/갱얼쥐.jpg"></div>
<div><img src="<%=request.getContextPath()%>/resources/member/img/사슴.jpg"></div>
<div><img src="<%=request.getContextPath()%>/resources/member/img/아라.jpg"></div>
<div><img src="<%=request.getContextPath()%>/resources/member/img/토토.jpg"></div>

</div>



<p id="H">병원 검색</p>
<form action="<%=request.getContextPath()%>/board/hospital/list?search=${paging.search} method="get" >

<fieldset id="field">
	<div id="Hdiv">
<!-- 	<input type="text" id="search"> -->
	<input type = "text" name = "search"  value = "${paging.search }">
	<button><img src="<%=request.getContextPath()%>/resources/member/img/검색.png"  width="30px" height="25px" class="img"></button>
<%-- 	<button><img src="<%=request.getContextPath()%>/resources/member/img/검색.png"  width="30px" height="25px" class="img"></a> --%>
	</div>
</fieldset>

</form>



<div class="main">

<!-- userId eq null || email eq null -->

	<c:choose>


	<c:when test="${login eq null || login eq false  }">
	<%-- <h2>세션 상태: ${not empty login}</h2> --%>
	<button onclick="location.href='/member/login/login'" id="btn">로그인</button>
	   <div class="select">
	      <a href="/member/login/id" >아이디 찾기 | </a>
	      <a href="/member/login//pw" >비밀번호 찾기 | </a>
	      <a href="/member/login/join" >회원가입</a>
	   </div>
	
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


<div class="freeTable">

	<table class = "table-list" style = "width:500px; margin-left:80px; border-collapse: collapse;">
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

</div>


<div class="oldTable">


	<table class = "table-list" style = "width:500px; margin-left:80px; border-collapse: collapse;">
		<tr class = "th-border items">
			<th>글 번호</th>
			<th>글 제목	</th>
			<th>조회수</th>
		</tr>
	
	
	<c:forEach items="${old }" var="old" >
		<tr class = "td-style items">
			<td> ${old.boardNo }</td>
			<td><a href="../../board/board/detail?boardNo=${old.boardNo }"> ${old.boardTitle } </a></td>
			<td class = "date">${old.hit }</td>
		</tr>
	</c:forEach>
	
	</table>

</div>


<hr>


<div class="clearbox"></div>
<div class="clearbox"></div>
<div class="clearbox"></div>

<hr>
<div class="hot">
<p id="Hot">새로 나온 게시글</p>

	<div class="bb">
	<ul>
		<li>
		<p id="free2">자유 게시판</p>
		</li>
		<li>
		<p id="old2">중고 거래</p>
		</li>
		<li>
		<p id="poom">품앗이</p>
		</li>
	</ul>
	</div>
</div> <!-- hot -->






<div class="newFreeTable">

	<table class = "table-list" style = "width:500px; margin-left:80px; border-collapse: collapse;">
		<tr class = "th-border items">
			<th>글 번호</th>
			<th>글 제목	</th>
			<th>조회수</th>
		</tr>
	
	<c:forEach items="${newFree }" var="newFree" >
		<tr class = "td-style items">
			<td> ${newFree.boardNo }</td>
			<td><a href="../../board/board/detail?boardNo=${newFree.boardNo }"> ${newFree.boardTitle }</a></td>
			<td class = "date">${newFree.hit }</td>
		</tr>
	</c:forEach>
	
	</table>

</div>


<div class="newOldTable">


	<table class = "table-list" style = "width:500px; margin-left:80px; border-collapse: collapse;">
		<tr class = "th-border items">
			<th>글 번호</th>
			<th>글 제목	</th>
			<th>조회수</th>
		</tr>
	
	
	<c:forEach items="${newOld }" var="newOld" >
		<tr class = "td-style items">
			<td> ${newOld.boardNo }</td>
			<td><a href="../../board/board/detail?boardNo=${newOld.boardNo }"> ${newOld.boardTitle } </a></td>
			<td class = "date">${newOld.hit }</td>
		</tr>
	</c:forEach>
	
	</table>
	
	</div>



<div class="poomTable">


	<table class = "table-list" style = "width:500px; margin-left:80px; border-collapse: collapse;">
		<tr class = "th-border items">
			<th>글 번호</th>
			<th>글 제목	</th>
			<th>조회수</th>
		</tr>
	
	
	<c:forEach items="${poom }" var="poom" >
		<tr class = "td-style items">
			<td> ${poom.boardNo }</td>
			<td><a href="../../board/board/detail?boardNo=${poom.boardNo }"> ${poom.boardTitle } </a></td>
			<td class = "date">${poom.hit }</td>
		</tr>
	</c:forEach>
	
	</table>

</div>














<div class="clearbox"></div>
<div class="clearbox"></div>
<div class="clearbox"></div>


















<c:import url="./layout/footer.jsp" />







