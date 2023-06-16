<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="../../layout/adminHeader.jsp"/>
<form method="get" action="./message">
<div class="container2" style="text-align:center; width: 1300; margin-top: 100px;" >
<h1>전체 메세지</h1>
<textarea name = "content" placeholder = "메시지를 입력하세요." class="form-control" rows="30" cols="100" ></textarea>
<br>
<button style="width: 100%" class="btn btn-success">전체 쪽지 보내기</button>
</div>
</form>
</body>
</html>