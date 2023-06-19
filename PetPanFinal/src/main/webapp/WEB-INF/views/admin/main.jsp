<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    /* CSS to style the button */
    .button-grid {
        display: grid;
        grid-template-columns: repeat(4, 1fr);
        grid-gap: 10px 0;
        gap: 10px;
        width: 900px; /* Adjust the width as per your requirements */
        margin: 50px auto; /* Center the button grid and add margin */
    }

    #button {
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #6989ff;
        color: white;
        width :200px;
        height: 180px; /* Adjust the height as per your requirements */
        font-size: 18px;
        margin-bottom: 10px;
    }

</style>

<c:import url="../layout/adminHeader.jsp" />

<body>
    <div class="container" style="padding-top: 100px; padding-left: 220px;">
        <!-- Add necessary container div or adjust existing container -->
        <div class="button-grid" align="center">
            <div><button id="button" class="btn" type="button" onclick="location.href='<%=request.getContextPath() %>/admin/member/list'">유저 관리</button></div>
            <div><button id="button" class="btn" type="button" onclick="location.href='<%=request.getContextPath() %>/admin/blacklist/list'">블랙리스트 관리</button></div>
            <div><button id="button" class="btn" type="button" onclick="location.href='<%=request.getContextPath() %>/admin/notice/list'">공지 관리</button></div>
            <div><button id="button" class="btn" type="button" onclick="location.href='<%=request.getContextPath() %>/admin/reportboard/list'">신고 게시글</button></div>
            <div><button id="button" class="btn" type="button" onclick="location.href='<%=request.getContextPath() %>/admin/reportcomment/list'">신고 댓글</button></div>
            <div><button id="button" class="btn" type="button" onclick="location.href='<%=request.getContextPath() %>/admin/shop/list'">상품 관리</button></div>
            <div><button id="button" class="btn" type="button" onclick="location.href='<%=request.getContextPath() %>/admin/reportshop/list'">신고 상품</button></div>
            <div><button id="button" class="btn" type="button" onclick="location.href='<%=request.getContextPath() %>/'">PetPan홈으로</button></div>
        </div>
    </div>
</body>

</body>
</html>
