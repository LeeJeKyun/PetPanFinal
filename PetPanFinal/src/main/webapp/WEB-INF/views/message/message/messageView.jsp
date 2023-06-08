<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- 쪽지삭제 모달창 --%>
<script type="text/javascript" >
$(function(){
	
	// 모달창 로직
	const modal = document.getElementById("modal");
	const openModalBtn = document.getElementById("messageDelete");
	const submitModalBtn = document.getElementById("submit");
	const closeModalBtn = document.getElementById("cancel")
	// 모달창 열기
	openModalBtn.addEventListener("click", () => {
	  modal.style.display = "block";
	  document.body.style.overflow = "hidden"; // 스크롤바 제거
	  $("#submit").attr({'messageno' : ${viewMessage.messageno}})
	});
	// 모달창 닫기
	closeModalBtn.addEventListener("click", () => {
	  modal.style.display = "none";
	  document.body.style.overflow = "auto"; // 스크롤바 보이기
	});

})

</script>


	<div style="text-align: center;"><h3>상세보기</h3></div>
	<div style="padding: 10px;">From.${sendMember.userNick }</div>
	<div style="padding: 10px 10px 10px 10px;">
		${viewMessage.content }
	</div>
	<div style="text-align: left; padding: 10px 10px 10px 10px; ">
		<button style="border:none;background-color: #ffdad7;border-radius: 10px 10px 10px 10px;margin-top: 20px;cursor: pointer;" onclick="sendMessage('${sendMember.userId}')">
			답장
		</button>
		
			<button id="${viewMessage.messageno }" 
				<c:if test="${viewMessage.saveMessage eq 'Y' }"> 
					class="fontWhite" 
				</c:if> 
				style="border:none;background-color: #ffdad7;border-radius: 10px 10px 10px 10px;margin-top: 20px;cursor: pointer;" onclick="saveMessage('${viewMessage.messageno}', '${viewMessage.saveMessage }')">
				저장
			</button>
		
		<button id="messageDelete" style="border:none;background-color: #ffdad7;border-radius: 10px 10px 10px 10px;margin-top: 20px;cursor: pointer;">
			삭제
		</button>
	</div>