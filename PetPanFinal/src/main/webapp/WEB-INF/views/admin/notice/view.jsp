<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:import url="../../layout/adminHeader.jsp"/>
<body>
    <div class="container2">
        <h1>공지사항 상세보기</h1>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${noticetitle}</h5>
                <h6 class="card-subtitle mb-2 text-muted">작성일: ${noticewritedate}</h6>
                <p class="card-text">${noticecontent}</p>
                <p class="card-text">작성자: ${userno}</p>
                <p class="card-text">게시판 유형: ${BoardTypeNo}</p>
                <a href="list.jsp" class="card-link">목록으로 돌아가기</a>
            </div>
        </div>
    </div>
    <!-- 부트스트랩 JS 링크 추가 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>






