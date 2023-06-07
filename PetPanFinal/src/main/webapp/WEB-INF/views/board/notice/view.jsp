
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../../layout/header.jsp" />

<br>

<!-- 컨텐츠영역 시작 -->
<div style="width: 1500px; margin:0 auto;">

<div style="text-align: left; width: 1100px; margin: 0 auto;">
	<span style="font-size: 2em; font-weight: bold;">공지사항 상세보기</span>
</div>

<br>

<div style=" width:1100px; margin: 0 auto;">
<br>
<div style="border-top: 3px solid #f5cbcb;">
	<p style="font-size: 30px; margin: 5px 0 5px 0;">${notice.noticetitle }</p>
</div>
<div style="border-top: 3px solid #f5cbcb; padding: 10px; font-size: 20px; border-bottom: 3px solid #f5cbcb; text-align: center;" >
	<c:forEach items="${filelist }" var="file" >
	
		<img alt="공지사진" src="<%=request.getContextPath()%>/upload/${file.storedName}">
	
	</c:forEach>
	<div style="text-align: left;">
		${notice.noticecontent}
	</div>
</div>
<br>
<div style="text-align: center;">
	<a href="<%=request.getContextPath() %>/board/notice/list">
	<button style="width: 100px; height: 40px; font-size: 22px; font-weight: bold; background-color: #f5cbcb; border-radius: 10px 10px 10px 10px / 10px 10px 10px 9px; border: none; color: #FF5050; cursor: pointer;" >
		목록
	</button>
	</a>
</div>

</div>


<!-- 컨텐츠영역 끝 -->
</div>

<c:import url="../../layout/footer.jsp" />







