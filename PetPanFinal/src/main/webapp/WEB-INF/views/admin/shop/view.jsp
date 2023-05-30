<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
  <c:import url="../../layout/adminHeader.jsp"/>
  
${shop }

<%-- <c:forEach var="shopFile" items="${shopFile}"> --%>
<%-- ${shopFile.fileno} --%>
<%-- ${shopFile.originname} --%>
<%-- ${shopFile.storedname} --%>
<%-- ${shopFile.objectno} --%>
<%-- </c:forEach> --%>
<div align="center">
<button onclick="location.href='<%=request.getContextPath() %>/admin/shop/change?objectno=${shop.objectno }'">수정</button>
<button onclick="location.href='<%=request.getContextPath() %>/admin/shop/delete?objectno=${shop.objectno }'">삭제</button>
</div>
</body>
</html>