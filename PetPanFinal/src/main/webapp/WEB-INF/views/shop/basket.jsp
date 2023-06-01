
<%@page import="member.dto.Member"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
	    height: 400px;  	
  	}
	.content_top:after {
	    clear: both;
	    display: table;
	    
	}    	
  	.ct_left_area{
	    width: 40%;
	    height: 100%;
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
  		}
  		.line{
		    width: 100%;
		    border-top:1px solid #c6c6cf;  		
  		}
 	.content_bottom{
	    width: 80%;
	    height: 120px;  	
	    text-align: end;
	    
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
  	.ajax{
  		color: #7b8ed1;
  		height: auto;
  	}
  	.total{
  		text-align: end;
  	}

  	.ct_right_area{
	    width: 40%;
	    height: 100%;
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
  		.button{
  			padding: 2% 0 2% 3%;
  		}
  			.button_quantity{
  				margin-bottom: 2%;
  				
  			}
	  			.button_quantity input{
				    height: 26px;
				    width: 40px;
				    text-align: center;
				    font-weight: bold;  			
	  			}
		  	  		.btn_list{
						display: inline-block;
    					width: 140px;
					    text-align: center;
					    height: 50px;
    					line-height: 50px;
    					background-color: #5e6b9f;
    					border: 1px solid #5e6b9f;
    					color: #fff;
		  	  		}
  	
  	}

</style>

<div class="line">
</div>		
<div class="ajax">
	<table class="table">
	  <tr>
	    <th>상품명</th>
	    <th>개당 가격</th>
	    <th>총 가격</th>
	    <th>주문 수량</th>
	    <th></th>
	  </tr>
	<c:forEach items="${list}" var="list" varStatus="status">
	  <tr data-objectno="${list.OBJECTNO}" class="tr">
	    <td>${list.NAME}</td>
	    <td>가격 : <fmt:formatNumber value="${list.PRICE}" pattern="#,### 원" /></td>
	    <td>총 가격 : <fmt:formatNumber value="${list.PRICE * list.QUANTITY}" pattern="#,### 원" /></td>
	    <td><input type="text"  class="order_cnt" value="${list.QUANTITY }" readonly="readonly" style="text-align:center;"/></td>
	    <td><button class="order_delete">삭제</button>
	  </tr>
	</c:forEach>	
	</table>
	<div class="total">
		<div class="price_total">총 가격 : <fmt:formatNumber value="<%=totalPrice %>" pattern="#,### 원" /></div>
		<div class="price_count">총 개수 : <fmt:formatNumber value="<%=totalQuantity %>" pattern="#,### " /></div>
	</div>
</div>
	







