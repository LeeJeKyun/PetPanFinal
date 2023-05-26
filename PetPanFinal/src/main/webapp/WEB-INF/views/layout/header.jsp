<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>+ + + PETPAN + + +</title>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- 스마트에디터2  -->
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/se2/js/service/HuskyEZCreator.js"></script>

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




<div id="member">
<small><a href="<%=request.getContextPath() %>/member/login/login">로그인</a></small>


<small><a href="<%=request.getContextPath() %>/member/login/join">회원가입</a></small>

</div>


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
		<li><a href="#">병원</a>
			<ul>
				<li><a href="#">병원</a></li>
				<li><a href="#">병원검색</a></li>
				<li><a href="#">병원리뷰</a></li>
			</ul>
		</li>
		<li><a href="#">쇼핑</a>
			<ul>
				<li><a href="#">쇼핑</a></li>
				<li><a href="#">상품검색</a></li>
				<li><a href="#">장바구니</a></li>
			</ul>
		</li>
		<li><a href="#">게시판</a>
			<ul>
				<li><a href="#">자유게시판</a></li>
				<li><a href="#">중고거래</a></li>
				<li><a href="<%=request.getContextPath() %>/board/notice/list">공지사항</a></li>
			</ul>
		</li>
		<li><a href="/member/mypage">마이페이지</a>
			<ul>
				<li><a href="/member/join">회원가입</a></li>
				<li><a href="/member/login">로그인</a></li>
				<li><a href="/member/mypage">정보수정</a></li>
			</ul>
		</li>
	</ul>
</div>






</div><!-- header -->













