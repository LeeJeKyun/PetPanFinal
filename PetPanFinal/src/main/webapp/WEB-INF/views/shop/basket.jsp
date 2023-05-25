
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp" />


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
  		.price{
		    line-height: 30px;
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
							${list.NAME}
						</h1>
					</div>
					<div class="line">
					</div>
					<div class="price">
						<div class="sale_price">가격 : <fmt:formatNumber value="${list.PRICE * list.QUANTITY}" pattern="#,### 원" /></div>
					</div>			
					<div class="line">
					</div>	
					<div class="button">						
						<div class="button_quantity">
							주문수량
						        <input type="text"  class="order_cnt" value="${list.QUANTITY }" readonly="readonly" style="text-align:center;"/>
							<br>
						</div>
							<form action="./buy" method="post">
								<button class="btn_buy">바로구매</button>
								<input type="hidden" class = "quantity" name = "quantity" value = "1">
								<input type = "hidden" id = "objectno" name = "objectno" value = ${view.objectno }>
								
							</form>
							<a href="./main"><button>목록으로</button></a>
						</div>
					</div>
				</div>
			

<c:import url="../layout/footer.jsp" />







