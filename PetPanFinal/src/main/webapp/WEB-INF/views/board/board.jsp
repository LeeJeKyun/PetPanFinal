<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<c:import url = "../layout/header.jsp" />

<style>
#fcontainer{
	width: 800px;
	height: 1000px;
	margin: 0 auto;
	margin-top: 200px;
}
.th-border{
	border-top: solid #ccc 3px ; 
	border-bottom:solid #ccc 3px ;
	height: 30px;
}
 .td-style{ 
 	text-align: center; 
 	border-bottom: solid #ccc 1px;
 	height: 50px;
 } 
 .date{
 	font-size: 0.7em;
 }
 #category{
 	margin-bottom: 20px;
 }
 #writeBtn{
 	text-align: right;
 	padding-right: 20px;
 	padding-bottom: 10px;
 }
#searchBox{
	width: 333px;
	height: 30px;
	margin: 0 auto;
	margin-top: 20px;
} 
#searchImg{
	width:28px;
	height: 28px;
}
.btn{
	border: none;
	background-color: #ffdad7;
	cursor: pointer;
	
}
#write-link{
    width: 100px;
    height: 30px;
    display: block;
    text-align: center;
    padding-top: 10px;
    border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
    margin-left: 85%;
}
.cate{
	margin-bottom: 0;
	padding-left: 20px;
}
.commentCnt{
	font-size: 0.7em;
	color:black;
	font-style: italic;
}
</style>

<div id = "fcontainer">
	<c:if test="${list[0].BOARDTYPENO == 2 }">
	<h1 class = "cate">자유게시판 </h1>
	</c:if>
	<c:if test="${list[0].BOARDTYPENO == 3}">
	<h1 class = "cate">중고거래</h1>
	</c:if>
	
	<div id = "writeBtn">
		<a href = "./board/write" class = "btn" id = "write-link">글쓰기</a>
	</div>
	<table class = "table-list" style = "width: 100%; border-collapse: collapse">
		<tr class = "th-border items">
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>조회수</th>
			<th>추천수</th>
			<th>등록일</th>
		</tr>
		<c:forEach var = "e" items = "${listNotice }">
	    <tr class = "td-style items">
	    	<td>${e.NOTICENO }</td>
			<td><a href ="./notice/view?noticeno=${e.NOTICENO }">${e.NOTICETITLE}</a></td>
			<td>관리자</td>
			<td></td>
			<td></td>
			<td class = "date"><fmt:formatDate value = "${e.NOTICEWRITEDATE }" pattern = "yyyy.MM.dd"  /></td>
	    </tr>
		</c:forEach>
		<c:forEach var = "i" items="${list }">
		<c:if test="${i.BOARDTYPENO != 5 }">
		<tr class = "td-style items">
			<td>${i.BOARDNO }</td>
			<td><a href ="./board/detail?boardNo=${i.BOARDNO }">${i.BOARDTITLE}<span class = "commentCnt">[${i.COMMENTCNT }]</span></a></td>
			<td>${i.USERNAME }</td>
			<td>${i.HIT }</td>
			<td>${i.RECOMMEND }</td>
			<td class = "date"><fmt:formatDate value = "${i.WRITEDATE }" pattern = "yyyy.MM.dd"  /></td>
		</tr>
		</c:if>
		</c:forEach>
	</table>
	<div id = "searchBox" >
		<form action = "./board" method = "get">
		<select id = "category"  name = "category" style = "display: inline-block">
			<option value = "2">자유게시판</option>
			<option value = "3">중고거래</option>
		</select>
		<input type = "text" id = "search" name = "search" value = "${search }" placeholder = "검색"><!-- <img id = "searchImg" alt="검색" src="../resources/img/searchIcon.png"> -->
		<button type = "submit" class = "btn">검색</button>
		</form>
	</div>
	<c:import url="../layout/paging2.jsp" />

</div>
<c:import url = "../layout/footer.jsp" />