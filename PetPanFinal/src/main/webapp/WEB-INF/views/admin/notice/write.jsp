<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>글 작성중</title>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <!-- Custom styles -->
	<script type="text/javascript" src="smarteditor2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	 <script type="text/javascript">
        // 네이버 스마트 에디터 초기화
        $(document).ready(function(){
            var oEditors = [];

            nhn.husky.EZCreator.createInIFrame({
                oAppRef: oEditors,
                elPlaceHolder: "content",
                sSkinURI: "smarteditor2/SmartEditor2Skin.html",
                fCreator: "createSEditor2"
            });
        });

        // 폼 전송 시 에디터 내용 업데이트
        function submitForm() {
            oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);

            // 폼 전송
            document.getElementById("myForm").submit();
        }
        
        // 전송 버튼 클릭 시 폼 전송
        $("button[type=submit]").click(function(e){
            e.preventDefault(); // 기본 동작 중단
            submitForm();
        });
    </script>
    
  
        
        <!-- 전송 버튼 -->

    
    
</head>
<body>

<body>
<c:import url="../../layout/adminHeader.jsp"/>
  <form id="myForm" action="./write" method="post">
        <!-- 네이버 스마트 에디터가 표시될 영역 -->
        <textarea name="content" id="content" rows="10" cols="100"></textarea>
    <div class="container2">
        <h1>공지사항 상세보기</h1>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${noticetitle}</h5>
                <p class="card-text">${noticecontent}</p>
                <p class="card-text">게시판 유형: ${BoardTypeNo}</p>
                <p class="card-text">제목:<input class="form-control title"  type="text" ></p>
                <p class="card-text">내용:<textarea class="form-control" name="content" id="content" rows="10" cols="100"></textarea></p>
 				<a href="./list" > <button type="submit" class = "btn btn-primary">게시</button></a>
 				<button type="button" class = "btn btn-danger">취소</button>
    </form>
            </div>
        </div>
    </div>
</body>
</html>