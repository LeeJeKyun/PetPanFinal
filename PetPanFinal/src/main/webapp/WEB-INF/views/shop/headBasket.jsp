
<%@page import="java.math.BigDecimal"%>
<%@page import="member.dto.Member"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="../layout/header.jsp" />

<style>

.content_top{
	    width: 80%;
	    height: auto;  	
	    margin: 10%;
	    color: #7b8ed1;
  	}
  	.ct_left_area{
	    width: 50%;
	    height: 100%;
	    float: left;
  	}
  		.image_wrap{
		    height: 80%;
		    width: 80%;
		    margin: auto;
		    top: 10%;
		    position: relative;  		
  		}
  		.image_wrap img{
	  		max-width: 100%;
		    height: 80%;
		    display: block;
		    float: left;   		
  		}
	.list{
		height: 300px;
	}
  	.order_cnt{
  		width: 20px;
  		color: #7b8ed1;
  	}
  	.order_delete{
  		color: #7b8ed1;
  		width: 45px;
  	}
  	.table{
  		text-align: center;
  		margin: 0 auto;
  	}
  	.total{
  		text-align: end;
  		padding: 3%;
  	}

  	.ct_right_area{
	    width: 50%;
	    height: 100%;
	    float: right;
  	}
  		.title{
		    height: 28%;
		    font-size: 17px;
		    line-height: 110px;
		    color: #3a60df;
  		}
  		.price{	
		    padding: 2% 0 2% 3%;  		
  		}
	.button_set{
		text-align: center;
	}
	.button{
		background: black;
		color: white;
		width: 500px;
	}
	  		
}

</style>

<div class="line">
</div>			

<div class="content_top">
	<c:forEach items="${list}" var="list" varStatus="status">
		<div class="list ${list.OBJECTNO }">
			<div class="ct_left_area">
				<div class="image_wrap">
					<img src="/upload/${list.IMG1 }">
				</div>
			</div>
			<div class="ct_right_area">
				<table class="table" data-objectno="${list.OBJECTNO}" >
				  <tr>
				    <th>상품명</th>
				    <th>개당 가격</th>
				    <th>총 가격</th>
				    <th>주문 수량</th>
				    <th></th>
				  </tr>
				  <tr id="tr">
				    <td>${list.NAME}</td>
				    <td>가격 : <fmt:formatNumber value="${list.PRICE}" pattern="#,### 원" /></td>
				    <td>총 가격 : <fmt:formatNumber value="${list.PRICE * list.QUANTITY}" pattern="#,### 원" /></td>
				    <td><input type="text"  class="order_cnt" value="${list.QUANTITY }" readonly="readonly" style="text-align:center;"/></td>
				    <td><button class="order_delete" onclick="deleteBasket(${list.OBJECTNO})">삭제</button>
				  </tr>
				</table>
			</div>		
		</div>	
	</c:forEach>
	<div class="button_set">
		<a href="./main"><button class="button" id="btn_list">목록으로</button></a>
		<form action="./buy" method="get">
			<button class="button" id="btn_buy">바로구매</button>
<!-- 			<input type="hidden" class = "quantity" name = "quantity" value = "1"> -->
			<input type="hidden" name = "userno" value = "${member.userNo }">
<%-- 			<c:set var="listSize" value="${list.size()}" /> --%>
<%-- 			<input type = "hidden" id = "buy" name ="objectno" value = "${list[0].USERNO}"> --%>
		</form>
	</div>
</div>
	
<script type="text/javascript">
	  
function deleteBasket(objectno) {

   	console.log(objectno)
   	
	$.ajax({
	    url: './deleteBasket',
	    type: 'GET',
	    data: {objectno:objectno },
	    dataType: "html",
	    success: function(res) {
			
	    	$("."+objectno).remove();
	    	
	    },
	    error: function(error) {
	    	
	    	console.log("delete 실패")
	    	
	    	
	    }
	})
		
}

</script>
<c:import url="../layout/footer.jsp" />







