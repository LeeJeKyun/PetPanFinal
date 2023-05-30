
<%@page import="shop.dto.Basket"%>
<%@page import="member.dto.Member"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<c:import url="../layout/header.jsp" />

<% int totalQuantity = 0;
	List<Map<String,Object>> list = (List<Map<String,Object>>)request.getAttribute("list");
	Member member = (Member)request.getAttribute("member");
	
	int totalPrice = 0;
	
	for( int i=0; i<list.size(); i++){
		BigDecimal bd = (BigDecimal)list.get(i).get("QUANTITY");
		totalQuantity += bd.intValue();
	}

	for( int i=0; i<list.size(); i++){
		BigDecimal quantity = (BigDecimal)list.get(i).get("QUANTITY");
		BigDecimal price = (BigDecimal)list.get(i).get("PRICE");
		totalPrice += quantity.intValue() * price.intValue();
	}
	
	int merchant_uid = 0;
	for(int i = 0; i <8; i++){
		merchant_uid += (Math.random()*100000000);
	}
	
	
%>
	 
<style>
.content_top{
    width: 80%;
    height: auto; 
    margin: 10%;
    margin-bottom: 0%;
    background: aliceblue;

}
	.order_head{
		padding: 20px;
	}
	
	.table{
		width: 100%;
		text-align: center;
	}
	
.line{
    width: 100%;
    border-top:1px solid #c6c6cf;  		
}	

.content_mid{
    width: 80%;
    height: 400px; 
    margin: 10%;
    margin-top: 0%;
    background: aliceblue;

}

	.addinfo{
		float: left;
		width: 50%;
		text-align: center;
		height: 370px;
	}
	
		.member_info{
			padding: 20px;
		}
	.semiinfo{
		float: right;
		width : 50%;
		text-align: center;
		height: 370px;
	}
.content_bottom{
	text-align: center;

}
	.button{
	    height: 80px;
	    width: 200px;
	    font-size: xx-large;
	    background: black;
	    color: white;	
	}

</style>

<script type="text/javascript">

IMP.init('imp11118386')

function requestPay() {
	
	IMP.request_pay({
		pay_method:'card'				//결재 수단 (필수)
		, merchant_uid:	'<%=merchant_uid%>'//고유 주문 번호 (필수)
		, amount: <%=totalPrice%>		//금액 (필수)
		, buyer_tel:<%=member.getPhone()%> //주문자 전화번호 (필수)
		
		, pg: "kakaopay"			//결제 지원 PG 선택
		, name: "<%= list.get(0).get("NAME") %>외 <%=totalQuantity - 1%>개"
		
		, buyer_name: " <%=member.getUserName()%> "	
	
		
	}, function(rsp) {	
		
		if(rsp.success) {
				
			var form = document.createElement('form');
			form.setAttribute('method','post');
			form.setAttribute('action',"./pay?buylistno="+<%=merchant_uid%> + "&amount=" + <%=totalPrice%> + "&userno=" + <%=member.getUserNo()%>)
				/* 보낼값 적기 */
			
			location.href = "./pay";
		
			console.log(rsp)
				
		}else {
      		console.log(rsp);
      		var msg = '결제가 실패하였습니다.';
      		msg += '에러내용 : ' + rsp.error_msg;
      		alert(msg);
      	}
		
		
		
	})
	
}

</script>

<div class="content_top">
	<div class=order_head>
		<h3>주문/결제</h3>
	</div>
	<div class="order_form">
		<table class="table">
			<thead>
			<tr class="thead_th">
				<th class="thead_info">상품정보</th>
				<th>배송비</th>
				<th>수량</th>
				<th>상품금액</th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${list}" var="list">
			<tr class="tbody_tr">
				<td>${list.NAME}</td>
				<td><h6>무료</h6></td>
				<td>${list.QUANTITY }</td>
				<td>${list.QUANTITY * list.PRICE }</td>
			</tr>
			</c:forEach>
			</tbody>
		
		</table>
	</div>
</div>

<div class="line"></div>

<div class="content_mid">
	<div class="addinfo">
		<div>
			<h3>배송지 정보</h3>
		</div>
		
		
		<div class="member_info">
			<div>
				<h4>이름 : ${member.userName }</h4>
			</div>
			<div>
				<h4>전화번호 : ${member.phone }</h4>
			</div>
			<div>
				<h4>주소 : ${member.address }</h4>
				<h4>${member.detailaddress }</h4>
			</div>
<!-- 			<div> -->
<!-- 				<h4>요청 사항</h4> -->
<!-- 				<select class="request"style="height: 20px; width: 250px;"> -->
<!-- 					<option value="callme">배송 전 연락바랍니다</option> -->
<!-- 					<option value="guardian">부재시 경비실에 맡겨 주세요</option> -->
<!-- 					<option value="nomecallme">부재시 전화주시거나 문자남겨주세요</option> -->
<!-- 				</select> -->
<!-- 			</div> -->
		</div>
		
	</div>

	<div class="semiinfo">
		<h3>주문자 정보</h3>
		<br><Br>
		<div><h4>${member.userName }</h4></div>
		<div><h4>${member.phone }</h4></div>
		<div><h4>${member.email }</h4></div>
	</div>

</div>


<div class="content_bottom">
	<button type="submit" class="button" name="pay" onclick="requestPay()">결제하기</button>
	<a href="./main"><button class="button" name="list" >목록으로</button></a>
</div>
<c:import url="../layout/footer.jsp" />


