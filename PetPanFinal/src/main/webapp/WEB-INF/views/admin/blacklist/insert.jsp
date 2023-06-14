<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
  <meta charset="utf-8" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
    />
    <title>PetPanAdmin</title>
    <meta name="description" content="" />
    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="../../resources/assets//img/favicon/favicon.ico" />
    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com"  />
    <link
      href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
      rel="stylesheet"
    />
    <!-- Icons. Uncomment required icon fonts -->

    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/assets/vendor/fonts/boxicons.css" />

    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/assets/vendor/css/core.css" class="template-customizer-core-css" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/assets/css/demo.css" />
    <style type="text/css">
    .container2{ 

    	margin-top: 100px; 
    
   } 
    
    
    </style>
</head>
<body>
<div class="container2">
    <div>
        <h1>블랙유저 등록</h1>
        <hr>
        <form id="blackForm" action="./insert" method="post">
            <table class="table table-striped" style="width:800px">
                <tr>
                    <th>유저 번호</th>
                    <td><input type="text" name="userno" value="${userno}" required class="form-control"></td>
                </tr>
                <tr>
                    <th>정지 사유</th>
                    <td><input type="text" name="reason" required class="form-control" id="reason"></td>
                </tr>
            </table>
            <button type="button" id="btn" class="btn btn-primary">삽입하기</button>
      		<button type="button" id="cancelBtn" class="btn btn-danger">취소</button>
        </form>
    </div>
   </div>
   <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
$(document).ready(function() {
	$('#reason').focus();
	
  // Enter 키 이벤트 처리
  $(document).keypress(function(e) {
    if (e.which === 13) {
      $('#btn').click();
    }
  });
  
  // 삽입하기 버튼 클릭 시 폼 수동 제출
  $('#btn').click(function() {
    $('#blackForm').submit();
  });

  // 폼 제출 시 삽입 후 알림 창 표시
  $('#blackForm').submit(function(e) {
    e.preventDefault();
    
    // AJAX를 사용하여 삽입 요청 보내기
    $.ajax({
      url: $(this).attr('action'),
      type: $(this).attr('method'),
      data: $(this).serialize(),
      success: function(response) {
        alert('삽입이 완료되었습니다.');
        opener.location.reload();
        window.close();
      },
      error: function() {
        alert('삽입에 실패했습니다. 다시 시도해주세요.');
      }
    });
  });

  // 취소 버튼 클릭 시 창 닫기
  $('#cancelBtn').click(function() {
    window.close();
  });
});
</script>

</body>
</html>