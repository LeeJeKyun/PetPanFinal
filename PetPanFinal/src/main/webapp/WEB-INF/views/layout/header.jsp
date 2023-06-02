<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>+ + + PETPAN + + +</title>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- timepicker -->
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<!-- 스마트에디터2  -->
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/se2/js/service/HuskyEZCreator.js"></script>

<!-- 슬라이더 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.min.css">

<style type="text/css">

body{
	margin: 0 auto;
	width: 1500px;

}

#header {
	text-align: center;

}

#footer {
	text-align: center;
	background: #FFDAD7;
}

#header h1 {
	line-height: 3em;
}

#footer {
	margin-top: 30px;
}

#header a {
	text-decoration: none;
	color: #FF5050;
	
	
}

ul {
	padding: 0;
}

ul li {
	list-style: none;

}

a {
	text-decoration: none;
	color: #FF5060;
}

#menu {
	
	
	background: #FFDAD7;
	
	color: #FF5050; 
	line-height: 50px;
	text-align: center;
	
	height: 50px;
	width : 100%;
	
	
	
}

#menu > ul > li {
	float: left;
	width: 20%;
	position: relative;
	
 	background: #FFDAD7; 
	color: #FF5050;
	
/* 	opacity: 0.9; */
	
	
}



#menu > ul > li > ul {
	width: 100%;
	display: none;
	position: absolute;
	background: #FFDAD7;
/* 	opacity: 0.9; */
	z-index: 1;

}

#menu:hover > ul > li > ul {
	display:block;
	background: #FFDAD7;
	opacity: 0.95;
}

#member {
	text-align:right;
	
	font-size: 16px;
	background: #FFDAD7;
}


</style>

</head>
<body>

<div id="header">  <!-- header -->


<c:choose>
<c:when test="${login eq null  }">
<%-- <h2>세션 상태: ${not empty login}</h2> --%>


<div id="member">
<small><a href="<%=request.getContextPath() %>/member/login/login">로그인</a></small>
<small><a href="<%=request.getContextPath() %>/member/login/join">회원가입</a></small>

</div>
</c:when>

<c:when test="${login eq true  }">
<%-- <h2>세션 상태: ${login eq true }</h2> --%>
<div id="member">
<small><a href="<%=request.getContextPath() %>/member/login/logout">로그아웃</a></small>
<small><a href="<%=request.getContextPath() %>/member/mypage/mypage">마이페이지</a></small>
</div>

</c:when>

</c:choose>


<!-- <div id="member"> -->
<%-- <small><a href="<%=request.getContextPath() %>/member/login/login">로그인</a></small> --%>


<%-- <small><a href="<%=request.getContextPath() %>/member/login/join">회원가입</a></small> --%>

<!-- </div> -->


<h1><a href="/">PET PAN</a></h1>





<div id="menu">
	<ul>
		<li><a href="<%=request.getContextPath() %>/board/care/list">품앗이</a>
			<ul>
				<li><a href="<%=request.getContextPath() %>/board/care/list">품앗이</a></li>
				<li><a href="#">임보</a></li>
				<li><a href="#">유기동물</a></li>
			</ul>
		</li>
		<li><a href="<%=request.getContextPath() %>/board/hospital/list">병원</a>
			<ul>
				<li><a href="<%=request.getContextPath() %>/board/hospital/list">병원</a></li>
				<li><a href="<%=request.getContextPath() %>/board/hospital/list">병원검색</a></li>
				<li><a href="#">병원리뷰</a></li>
			</ul>
		</li>
		<li><a href="<%=request.getContextPath() %>/shop/main">쇼핑</a>
			<ul>
				<li><a href="<%=request.getContextPath() %>/shop/main">쇼핑</a></li>
				<li><a href="<%=request.getContextPath() %>/shop/headBasket">장바구니</a></li>
				<li><a href="<%=request.getContextPath() %>/shop/orderList">주문현황</a></li>
			</ul>
		</li>
		<li><a href="<%=request.getContextPath() %>/board/board">게시판</a>
			<ul>
				<li><a href="<%=request.getContextPath() %>/board/board">자유게시판</a></li>
				<li><a href="<%=request.getContextPath() %>/board/board?category=3">중고거래</a></li>
				<li><a href="<%=request.getContextPath() %>/board/notice/list">공지사항</a></li>
			</ul>
		</li>
			<li><a href="<%=request.getContextPath() %>/member/mypage/mypage">마이페이지</a>
			<ul>
				
			<li><a href="<%=request.getContextPath() %>/member/mypage/mypage">마이페이지</a></li>
				<c:choose>
				
				<c:when test="${login eq null  }">
				<li><a href="<%=request.getContextPath() %>/member/login/join">회원가입</a></li>
				
				
				<li><a href="<%=request.getContextPath() %>/member/login/login">로그인</a></li>
				</c:when>
				
				<c:when test="${login eq true  }">
				<li><a href="<%=request.getContextPath() %>/member/mypage/mypage">정보수정</a></li>
				<li><a href="<%=request.getContextPath() %>/member/login/logout">로그아웃</a></li>
			
				</c:when>
				</c:choose>
				
			</ul>
		</li>
	</ul>
</div>






</div><!-- header -->













