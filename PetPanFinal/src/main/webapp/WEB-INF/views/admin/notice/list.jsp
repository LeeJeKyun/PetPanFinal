<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="../../layout/adminHeader.jsp" />

<style>
  .theme-button {
    margin: 5px;
  }
  .active-theme {
    font-weight: bold;
  }
  .board-list {
    margin-top: 20px;
  }
  .board-list table {
    width: 100%;
    border-collapse: collapse;
  }
  .board-list th,
  .board-list td {
    padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ddd;
  }
  .board-list tr:hover {
    background-color: #f5f5f5;
  }
</style>

<div class="container2">
  <h1>테마별 게시판</h1>
  <div align="right">
    <a href = "./write"><button type="button" class="btn btn-success" data-theme="1" style = "margin-left: 700px">공지 등록</button></a>
   </div>
    
  <div class="btn-group" role="group" aria-label="Themes" >
  	<span style = "margin : 0 auto;">
 
	    <button type="button" class="btn btn-primary theme-button active-theme" data-theme="1" >품앗이</button>
	    <button type="button" class="btn btn-primary theme-button" data-theme="2">자유</button>
	    <button type="button" class="btn btn-primary theme-button" data-theme="3">중고거래</button>
	    <button type="button" class="btn btn-primary theme-button" data-theme="4">병원</button>

    </span>
  </div>
  <hr>
  <div id="boardContainer">
    <!-- 테마별 게시판이 표시될 영역 -->
  </div>
</div>

<script>
  $(document).ready(function() {
    // 초기 테마로 자유 선택
    var theme = 1; // theme 변수 선언 및 초기화
    showBoard(theme);

    // 테마 버튼 클릭 이벤트 처리
    $('.theme-button').on('click', function() {
      theme = $(this).data('theme');
      $('.theme-button').removeClass('active-theme');
      $(this).addClass('active-theme');
      showBoard(theme);
    });

    // 테마별 게시판 표시 함수
    function showBoard(theme) {
      var boardContainer = $('#boardContainer');
      boardContainer.empty(); // 기존 게시판 내용 제거

      // Ajax 요청을 통해 해당 테마의 게시판 데이터를 가져와서 표시합니다.
      $.ajax({
        url: '../notice/getlist?theme=' + theme,
        type: 'GET',
        dataType: 'html',
        success: function(response) {
          boardContainer.html(response);
        },
        error: function(xhr, status, error) {
          console.error('Error:', status, error);
        }
      });
    }
  });
</script>