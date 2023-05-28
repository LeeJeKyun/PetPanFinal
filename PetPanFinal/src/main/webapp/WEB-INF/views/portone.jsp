
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="./layout/header.jsp" />

<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

<script type="text/javascript" >
	const IMP = window.IMP; // 생략 가능
	IMP.init("imp74821035"); // 예: imp00000000a
	  
	
	function requestPay() {
		IMP.request_pay({
			pg: "kakaopay",
			pay_method: "card",
			merchant_uid: "3",   // 주문번호
			name: "노르웨이 회전 의자",
			amount: 100,                         // 숫자 타입
			buyer_email: "zxsi2003@naver.com",
			buyer_name: "이제균",
			buyer_tel: "010-3692-4075",
			buyer_addr: "서울특별시 강남구 신사동",
			buyer_postcode: "02541"
			}, function (rsp) { // callback
				
				console.log(rsp)
				
				});
	}
</script>

<div>
<h1>포트원 테스트페이지</h1>
<hr>

<button onclick="requestPay()">결제</button>

</div>

<c:import url="./layout/footer.jsp" />







