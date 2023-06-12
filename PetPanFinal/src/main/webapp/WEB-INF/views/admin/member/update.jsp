<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--  <%@ include file="../../layout/adminHeader.jsp" %> --%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>  <!-- 다음 postcode -->


<style type="text/css">
#search {
	margin-left: 400px;
}"
 table th{
	width : 400px;
}
</style>

<c:import url="../../layout/adminHeader.jsp" />
<script type="text/javascript">

$(function() {
	
	$("#btnPostcode").click(function() {
		
		new daum.Postcode({
			oncomplete: function(data) {
			
			
			
			// 우편번호 적용하기
			$("#zipCode").val( data.zonecode )
			 //$("#input class="form-control"[name~='postcode']").val(data.zonecode)
			 
			
			if( data.userSelectedType == 'J' ) {
				// 사용자가 지번 주소를 선택
			
				$("#address").val( data.jibunAddress )
				
			} else if( data.userSelectedType == 'R') {
				// 사용자가 도로명 주소를 선택
				
				$("#address").val( data.roadAddress )
				
			}
			
			$("#address_detail").trigger("focus")
			
			}
		
		}).open();
		
	})
	

	
})

</script>




<div class="container2">
	<form action="./update?userno=${member.userNo}" method="post">

		<table class="table table-striped" style="width: 1300px">
			<tr> <th>유저번호</th><td><input class="form-control" value = "${member.userNo}" name = "userNo" readonly /></td></tr>
			<tr> <th>유저이름</th><td><input class="form-control" value = "${member.userName }" name = "userName" /></td></tr>
			<tr> <th>유저닉네임</th><td><input class="form-control" value = "${member.userNick }" name = "userNick" /></td></tr>
			<tr><th>유저아이디</th> <td><input class="form-control" value = "${member.userId}" name = "userId" readonly/></td></tr>
			<tr><th>유저비밀번호</th><td><input class="form-control" value = "${member.userPw}" name = "userPw"/></td></tr>
			<tr><th>유저이메일</th><td><input class="form-control" value = "${member.email}" name = "email" /></td>	</tr>
			<tr>
      			<th>우편번호 찾기</td>
				<td><button type = "button"id= "btnPostcode" class="btn btn-primary">우편번호 찾기</button> <input class="form-control" type="text" id="zipCode" name="zipCode" value="${member.zipCode}" required></td>
      		</tr>
      		<tr>
      			<th>우편번호</td>
      			<td><input class="form-control" type="text"  id="address" name="address" value="${member.address}" required></td>
      		</tr>
      		<tr>	
      			<th>상세주소</td>
      			<td><input class="form-control" type="text" id="detailaddress" name="detailaddress" value="${member.detailaddress}" placeholder="상세주소를 입력해주세요" required ></td>
      		</tr>

			<tr><th>유저핸드폰번호</th><td><input class="form-control" value = "${member.phone}" name = "phone"/></td></tr>
			<tr> 
			  <th>유저 포지션</th>
			  <td>
						<input type="radio" class="form-check-input" name="positionNo" value="1" id="userPosition1"
						      ><label for="userPosition1">일반유저</label>
						<input type="radio" class="form-check-input" name="positionNo" value="2" id="userPosition2"
						      ><label for="userPosition2">병원관계자</label>
						<input type="radio" class="form-check-input" name="positionNo" value="3" id="userPosition3"
						      ><label for="userPosition3">관리자 승격!!</label>
			  </td>				
			</tr>

					
		</table>
<script>
  const memberPositionNo = "${member.positionNo}"; // 유저의 포지션 값을 가져옵니다.

  // 가져온 포지션 값을 기반으로 radio 버튼을 선택합니다.
  if (memberPositionNo === "1") {
    document.getElementById("userPosition1").checked = true;
  } else if (memberPositionNo === "2") {
    document.getElementById("userPosition2").checked = true;
  } else if (memberPositionNo === "3") {
    document.getElementById("userPosition3").checked = true;
  }
</script>
		<button class = "btn btn-primary">정보 수정</button>
		<a href = "./view?userno=${member.userNo}"><button type="button" class = "btn btn-danger">취소</button></a>
		
</form>		
		
</div>
</body>
</html>