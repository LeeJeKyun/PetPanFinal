
<%@page import="java.math.BigDecimal"%>
<%@page import="member.dto.Member"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp" />

<% 
	if(null != session.getAttribute("login")){
		int totalQuantity = 0;
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
	}
%>

<style type="text/css">
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
  			width: 364px;
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
    					width: 120px;
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
    					width: 120px;
					    text-align: center;
					    height: 50px;
    					line-height: 50px;
    					background-color: #7b8ed1;
    					border: 1px solid #7b8ed1;
    					color: #fff;			  	  		
		  	  		}
		  	  		.btn_main{
						display: inline-block;
    					width: 120px;
					    text-align: center;
					    height: 50px;
    					line-height: 50px;
    					background-color: darkcyan;
    					border: 1px solid darkcyan;
    					color: #fff;			  	  	
    					float: right;	
		  	  		}.btn_main2{
						display: inline-block;
    					width: 380px;
					    text-align: center;
					    height: 50px;
    					line-height: 50px;
    					background-color: darkcyan;
    					border: 1px solid darkcyan;
    					color: #fff;			  	  	
    					float: right;	
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
		height: auto;
	    min-height: 600px;  	
	    margin-left: 10%;
	    margin-right: 10%;
	    margin-top: 5%;
	    margin-bottom: 5%;
  	}
  	.review_img{
  		float: left;
  		width: 200px;
  		height: 150px;
  		padding: 5px;
  	}
  	.review_con{
  		width: 150px;
  		height: 100px;
  		padding: 5px;
  	}
  	
  	.table{
  		margin: 0 auto;
  		text-align: center;
  		height: 200px;
  	}
  	.table_th{
  		width: 200px
  	}
  	.img_div{
  		display: inline-block;
  	}
  	.report{
  		width: 30px;
  		cursor: pointer;
  	}
  	.rep{
  		text-align: end;
  	}

</style>
<div class="line">
</div>			

<div class="content_top">
	<div class="ct_left_area">
		<div class="image_wrap">
			<img src="/upload/${view.img1 }">
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
			        <button type="button" style="cursor: pointer;" id="minus">-</button>
			        <input type="text"  class="order_cnt" value="1" readonly="readonly" style="text-align:center;"/>
				    <button type ="button" style="cursor: pointer;" id="plus">+</button>
				<br>
			</div>
			<% if( null != session.getAttribute("login")){ %>
				<div class="button_set">
					<button class="btn_basket" style="cursor: pointer;">장바구니 담기</button>
					<input type="hidden" class = "quantity" name ="quantity" value = "1">
					<input type = "hidden" id ="basketInsert" name ="objectno" value = "${view.objectno }">
					<a href="./main"><button class="btn_main" style="cursor: pointer;">목록으로</button></a>
					<form action="./buy" method="post">
						<button class="btn_buy" style="cursor: pointer;">바로구매</button>
						<input type="hidden" class = "quantity" name = "quantity" value = "1">
						<input type = "hidden" id = "buy" name ="objectno" value = "${view.objectno }">
					</form>
				</div>
			<% }else{ %>
			<div class="button_set">
				<a href="./main" style="cursor: pointer;"><button class="btn_main2">목록으로</button></a>
			</div>
			<%} %>
		</div>
	</div>
</div>

<div id="result"></div>	

<div class="line">
</div>				

<div class="content_middle">
	<div class="shop_content">
	<c:forEach items= "${file}" var="file" >
		<img src="/upload/${file.storedname }">
	</c:forEach>
	</div>
</div>
<div title="신고하기" class="rep">
	<button onclick="report()"><img src="./../../resources/img/report.jpg" alt="신고하기" class="report"></button>
</div>
<div class="line">
</div>				

<div class="content_bottom">
	<div class="review_title">
		<h3>상품평</h3>
	</div>
	<c:set var="review" value="${reviewList }" />
	<c:if test="${review eq null }">
		<h2 style="color: blue; text-align: center;" >상품평이 아직 하나도 없어용</h2>
	</c:if>
	<c:forEach items="${reviewList}" var="review">
	<div>
		<img src="/upload/${review.IMAGE1 }" class="review_img">
	</div>
		
	<table class="table">
		<tr>
			<th class="table_th">상품명</th>
			<th class="table_th">닉네임</th>
			<th>평점</th>
		</tr>
		<tr>
	
		    <td>${view.name }</td>
		    <td>${review.USERNICK }</td>
		    <td>
		    <c:set var="star" value="${review.REVIEWSTAR }" />
				<c:if test="${star eq '1'}">
				    <img src="./../../resources/img/1star.jpg">
				</c:if>
				<c:if test="${star eq '2'}">
				    <img src="./../../resources/img/2star.jpg">
				</c:if>
				<c:if test="${star eq '3'}">
				    <img src="./../../resources/img/3star.jpg">
				</c:if>
				<c:if test="${star eq '4'}">
				    <img src="./../../resources/img/4star.jpg">
				</c:if>
				<c:if test="${star eq '5'}">
				    <img src="./../../resources/img/5star.jpg">
				</c:if>
		    </td>
		</tr>
		<tr>
			<td colspan="">${review.REVIEWCONTENT }</td>
		</tr>
	</table>
	<div style="text-align: center;">
		<c:forEach items="${filelist}" var="filelist">
			<c:forEach items="${filelist.value }" var="ReviewFile">
				<c:if test="${ReviewFile.reviewno eq review.REVIEWNO}">
					<div class="img_div">
						<img src="/upload/${ReviewFile.storedname }" class="review_con" style="cursor: pointer;" onclick="doImgPop('/upload/${ReviewFile.storedname }')">
					</div>
				</c:if>
			</c:forEach>
		</c:forEach>	
	</div>
	<div class="line">
	</div>		
	</c:forEach>
	
</div>

<script type="text/javascript">

function doImgPop(img){ 
	 img1= new Image(); 
	 img1.src=(img); 
	 imgControll(img); 
	} 
	  
	function imgControll(img){ 
	 if((img1.width!=0)&&(img1.height!=0)){ 
	    viewImage(img); 
	  } 
	  else{ 
	     controller="imgControll('"+img+"')"; 
	     intervalID=setTimeout(controller,20); 
	  } 
	}

	function viewImage(img){ 
	 W=img1.width; 
	 H=img1.height; 
	 O="width="+W+",height="+H+",scrollbars=yes"; 
	 imgWin=window.open("","",O); 
	 imgWin.document.write("<html><head><title>:*:*:*: 이미지상세보기 :*:*:*:*:*:*:</title></head>");
	 imgWin.document.write("<body topmargin=0 leftmargin=0>");
	 imgWin.document.write("<img src="+img+" onclick='self.close()' style='cursor:pointer;' title ='클릭하시면 창이 닫힙니다.'>");
	 imgWin.document.close();
	}
$(function(){
	
	$('#plus').click(function(){
		
		console.log("plus click")
		var cnt = $(".order_cnt").val()
				
		cnt = parseInt(cnt)
		
		cnt++
		
		$(".order_cnt").val(cnt);
		$(".quantity").val(cnt);
		
	})
		
	$("#minus").click(function () {
		
		console.log("minus click")
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

$(function() {
	
	$(".btn_basket").click(function() {
		console.log("#ajax click")
		
		var objectno = $("#basketInsert").val();
    	var quantity = $(".quantity").val();
		
		$.ajax({
			
			type: "post"
			, url: "./basket"
			, data:{
				objectno: objectno,
       			quantity: quantity
			} 
			, dataType: "html"
			, success: function( res ) {
				console.log("AJAX 성공")
				
				$("#result").html(res)
				
			}
			, error: function() {
				console.log("AJAX 실패")	
			}
			
		})
		
		
	})
	
	$(document).on('click', '.order_delete', function() {
		
		console.log("click")
		
		console.log($(this).closest('tr').attr("data-objectno"))
		
		var objectno = $(this).closest('tr').attr("data-objectno")
		
		console.log(objectno)
    	console.log($(this).closest($(".tr")))
		
		$.ajax({
		    url: './delete',
		    type: 'GET',
		    data: {objectno:objectno },
		    dataType: "html",
		    success: function(res) {
				
// 		    	console.log(res)
				$("#result").html(res)
		    	
		    },
		    error: function(error) {
		    	
		    	console.log("delete 실패")
		    	
		    	
		    }
		})
		
    	
		
	})	

})

function report() {
	
	window.open("./report?objectno=" + ${view.objectno}, "신고", "width=400, height=500, resizable=no");
}

</script>
<c:import url="../layout/footer.jsp" />







