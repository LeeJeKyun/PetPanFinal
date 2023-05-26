
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp" />

<script type="text/javascript">
$(function(){
	
	$('#plus').click(function(){
		
		var cnt = $(".order_cnt").val()
		
		cnt = parseInt(cnt)
		
		cnt++
		
		$(".order_cnt").val(cnt);
		$(".quantity").val(cnt);
		
	})
		
	$("#minus").click(function () {
		
		var cnt = $(".order_cnt").val()
		
		cnt = parseInt(cnt)
		
		cnt--
		
		if(cnt<1){
			alert("더이상 줄일수 없습니다");
			cnt = 1;
		}
		$(".order_cnt").val(cnt);
		$(".quantity").val(cnt);
	})
		
})
	

</script>

<style>

.content_top{
	    width: 80%;
	    height: 600px;  	
	    margin-left: 10%;
	    margin-right: 10%;
	    margin-top: 8%;
	    margin-bottom: 3%;
  	}
	.content_top:after {
	    clear: both;
	    display: table;
	    
	}    	
  	.ct_left_area{
		float: left;
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
  		
  		

  	.ct_right_area{
	    float: left;
	    width: 40%;
	    height: 100%;
  	}
  		.title{
		    height: 28%;
		    font-size: 17px;
		    line-height: 110px;
		    color: #3a60df;
		    padding-left: 3%;
  		}
  		.author{
		    font-size: 16px;
		    line-height: 50px;
		    padding-left: 3%;  		
  		}
  		.price{
		    line-height: 30px;
		    padding: 2% 0 2% 3%;  		
  		}
  			.discount_price_number{
			    line-height: 30px;
			    font-size: 22px;
			    color: #f84450;
			    font-weight: bold;  			
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
		  	  	.button_quantity button{
					border: 1px solid #aaa;
				    color: #3a60df;
				    width: 20px;
				    height: 20px;
				    padding: 3px;
				    background-color: #fff;
				    font-weight: bold;
				    font-size: 15px;
				    line-height: 15px;	  	  	
		  	  	}
		  	  		.btn_basket{
						display: inline-block;
    					width: 140px;
					    text-align: center;
					    height: 50px;
    					line-height: 50px;
    					background-color: #5e6b9f;
    					border: 1px solid #5e6b9f;
    					color: #fff;
    					margin-right: 2px;  	  		
    					float: left;
		  	  		}
		  	  		.btn_buy{
						display: inline-block;
    					width: 140px;
					    text-align: center;
					    height: 50px;
    					line-height: 50px;
    					background-color: #7b8ed1;
    					border: 1px solid #7b8ed1;
    					color: #fff;			  	  		
		  	  		}
  	
  	.content_middle{
		width: 80%;
	    min-height: 600px;  	
	    margin-left: 10%;
	    margin-right: 10%;
	    margin-top: 5%;
	    margin-bottom: 5%;
  	}

  	
  	.content_bottom{
		width: 80%;
	    min-height: 600px;  	
	    margin-left: 10%;
	    margin-right: 10%;
	    margin-top: 5%;
	    margin-bottom: 5%;
	    background-color: #e7dbdb;  	
  	}

</style>
<div class="line">
			</div>			
			<div class="content_top">
				<div class="ct_left_area">
					<div class="image_wrap">
						<img src="/images/download.jpg">
					</div>
				</div>
				<div class="ct_right_area">
					<div class="title">
						<h1>
							${view.name}
						</h1>
					</div>
					<div class="line">
					</div>
					<div class="price">
						<div class="sale_price">가격 : <fmt:formatNumber value="${view.price}" pattern="#,### 원" /></div>
					</div>			
					<div class="line">
					</div>	
					<div class="button">						
						<div class="button_quantity">
							주문수량
							    <button type ="button" style="cursor: pointer;" id="plus">+</button>
						        <input type="text"  class="order_cnt" value="1" readonly="readonly" style="text-align:center;"/>
						        <button type="button" style="cursor: pointer;" id="minus">-</button>
							<br>
						</div>
						<div class="button_set">
							<form action="./basket" method="post">
								<button class="btn_basket">장바구니 담기</button>
								<input type="hidden" class = "quantity" name = "quantity" value = "1">
								<input type = "hidden" id = "objectno" name = "objectno" value = ${view.objectno }>
							</form>
<%-- 							<form action="./buy?objectno=${view.objectno }" method="post"> --%>
							<form action="./buy" method="post">
<%-- 								<a class="btn_buy" href="./buy?objectno=${view.objectno }">바로구매</a> --%>
								<button class="btn_buy">바로구매</button>
								<input type="hidden" class = "quantity" name = "quantity" value = "1">
								<input type = "hidden" id = "objectno" name = "objectno" value = ${view.objectno }>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="line">
			</div>				
			<div class="content_middle">
				<div class="shop_content">
					${view.shopcontent }
				</div>
			</div>
			<div>
				<a href="./main"> <button>상품 목록</button> </a>
			</div>
			<div class="line">
			</div>				
			<div class="content_bottom">
				리뷰
			</div>


<c:import url="../layout/footer.jsp" />







