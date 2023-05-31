<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<meta charset="UTF-8">
<title>Insert title here</title>
  <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <style type="text/css">
.boardinfo{
	width: 1000px;
}
</style>
 <c:import url="../../layout/adminHeader.jsp"/>
<body>
<div class="container2">
<div>
	<div>
		  <h3>신고 정보</h3>
		  <p>신고 번호: ${list.coreportNo}</p>
		  <p>신고 내용: ${list.reportDetail}</p>
		  <p>신고 일자: <fmt:formatDate value="${list.reportDate}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
		  <p>처리 여부: ${list.complete}</p>
		</div>
		<hr>
		<form action="<%=request.getContextPath() %>/admin/reportcomment/view/delete" method="post">
		<input type = "hidden" name="coreportNo" value="${list.coreportNo}">
		<div class="commentinfo">
		  <h3>신고 댓글 정보</h3>
		  <p>댓글 번호: ${comment.commentNo}</p>
		  <p>댓글 내용: ${comment.content}</p>
		  <p>작성 일자: <fmt:formatDate value="${comment.writeDate}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
		  <p>원 댓글: <a href="javascript:void(window.open('<%=request.getContextPath() %>/admin/reportcomment/commentdetail?commentno=${comment.refCommentNo}', '참조중인 댓글','width=500px, height=500px'))">${comment.refCommentNo}</a></p>
		  신고게시글 삭제 <input type="checkbox" id="delete" name="docommentNo" value="${comment.commentNo }">
		</div>
		<hr>
		
		<div>
		  <h3>신고자 정보</h3>
		  <p>신고자 번호: ${domember.userNo}</p>
		  <p>신고자 ID: ${domember.userId}</p>
		  <p>신고자 이름: ${domember.userName}</p>
		  사유 <textarea rows="3" cols="20" name="getdoblackres"></textarea><br>
		  신고자 블랙리스트 추가 <input type="checkbox" id="delete" name="getdoblack" value="${domember.userNo }">
		  
		</div>
		<hr>
		
		<div>
		  <h3>피신고자 정보</h3>
		  <p>피신고자 번호: ${getmember.userNo}</p>
		  <p>피신고자 ID: ${getmember.userId}</p>
		  <p>피신고자 이름: ${getmember.userName}</p>
		  사유 <textarea rows="3" cols="20" name="getgetblackres"></textarea><br>
		  피신고자 블랙리스트 추가 <input type="checkbox" id="delete" name="getgetblack" value="${getmember.userNo }">
		</div>
		<hr>
		<input type="submit" id="codeIdSubmit" value="선택 게시글 삭제/선택 회원 블랙리스트 추가">
		</form>
	</div>
	</div>
</body>
</html>

	

</body>
</html>