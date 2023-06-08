<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:import url="../../layout/adminHeader.jsp"/>
<body>
    <div class="container2">
        <h1>공지사항 상세보기</h1>
        <table class = "table table-striped" style="width:1300px">
			<tr><th>공지번호</th> <td>${notice.noticeno}</td></tr>
			<tr><th>공지제목</th> <td>${notice.noticetitle}</td></tr>
			<tr><th>공지내용</th> <td>${notice.noticecontent}
			
			<c:forEach items="${fileList }" var="fileList">
			<img alt=".." src="<%=request.getContextPath()%>/upload/${fileList.storedName}" width="800px" height="auto"  class="img">
			</c:forEach>
			
			</td></tr>
			<tr><th>공지작성일</th> <td>${notice.noticewritedate}</td></tr>
			<tr><th>유저번호</th> <td>${notice.userno}</td></tr>
			<tr><th>작성번호</th> <td>${notice.boardtypeno}</td></tr>
			<tr>
			  
			</tr>
		</table>        
	<div align="right">
  		<button onclick="location.href='<%=request.getContextPath() %>/admin/notice/update?noticeNo=${notice.noticeno}'" class="btn btn-info">수정</button>
  		<button onclick="location.href='<%=request.getContextPath() %>/admin/notice/delete?noticeNo=${notice.noticeno }'" class="btn btn-danger">삭제</button>
	</div>
	</div>
</body>
</html>






