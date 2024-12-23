<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String ctxPath = request.getContextPath();
    //    /CLARETe
%> 

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/index/header.css" />
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/index/footer.css" />
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/cart/cart.css" />

<!-- Optional JavaScript -->
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script> 

<%-- jQueryUI CSS 및 JS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
<script type="text/javascript" src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script> 

<%-- 직접 만든 JS --%>
<script type="text/javascript" src="<%= ctxPath%>/js/cart/cart.js"></script>

<jsp:include page="../header.jsp"></jsp:include>

<section style="width: 100%; height: auto; margin-bottom: 100px; padding-top: 4.5rem">

	<div id="all_container">
		<div id="fistDiv">
			<a class="cart">장바구니</a>
			<a class="step"><strong>1.장바구니</strong>&nbsp;&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;&nbsp;&nbsp;2.주문서 작성&nbsp;&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;&nbsp;&nbsp;3.주문완료</a>
		</div>	
		
		<div id="productList">
			<div class="cart_container" style="border-top: none; border-bottom: 1px solid black; border-left: none; border-right: none;">
				
				<div class="menuDiv" style="border-top: 1px solid black; border-bottom: none; border-left: none; border-right: none;">
				
					<a class="a_cart">장바구니 상품</a>
					<button type="button" class="all">전체선택</button>
					<button type="button" class="all" id="btn_cancel">전체해제</button>
				</div>
				
				<div class="product">
					<div class="upDiv">
						
						<input type="checkbox">
							<%-- <img src="<%= ctxPath%>/images/kakao.png" />--%>
							<div style="width: 64px; height: 64px; background-color: black; "></div>
							<div class="infoDiv">
								향수이름
								<br>
								32,500
							</div>
							<span class="priceSpan">32,500</span>
							<svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="#6D6D6D" stroke-width="1" stroke-linecap="round" stroke-linejoin="round">
							  <line x1="18" y1="6" x2="6" y2="18"></line>
							  <line x1="6" y1="6" x2="18" y2="18"></line>
							</svg>

					</div>
					<div class="downDiv">
						<span class="optionSpan">-옵션 50ml</span>
						
						<div>		
							<div class="counter-container">
						        <div class="counter-button" id="decrease">-</div>
						        <div class="counter-value" id="counter">1</div>
						        <div class="counter-button" id="increase">+</div>
						    </div>
						    <a class="orderA">주문하기<a>
						</div>
  
					</div>
					
					
				</div>
				
				
				<div class="product">
					<div class="upDiv">
						
						<input type="checkbox">
							<%-- <img src="<%= ctxPath%>/images/kakao.png" />--%>
							<div style="width: 64px; height: 64px; background-color: black; "></div>
							<div class="infoDiv">
								향수이름
								<br>
								32,500
							</div>
							<span class="priceSpan">32,500</span>
							<svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="#6D6D6D" stroke-width="1" stroke-linecap="round" stroke-linejoin="round">
							  <line x1="18" y1="6" x2="6" y2="18"></line>
							  <line x1="6" y1="6" x2="18" y2="18"></line>
							</svg>

					</div>
					<div class="downDiv">
						<span class="optionSpan">-옵션 50ml</span>
						
						<div>		
							<div class="counter-container">
						        <div class="counter-button" id="decrease">-</div>
						        <div class="counter-value" id="counter">1</div>
						        <div class="counter-button" id="increase">+</div>
						    </div>
						    <a class="orderA">주문하기<a>
						</div>
  
					</div>
					
					
				</div>

			</div>
			
			<div class="payment_go_box">
			
    			<div class="payment_info">
        			<a class="order_product">주문상품</a>
        			<hr>

        			<!-- 총 상품 금액 -->
        			<div style="display: flex; justify-content: space-between; margin: 10px 0;">
            			<span class="product_amount">총 상품 금액</span>
            			<span class="product_price">76,000</span>
        			</div>

        			<!-- 총 할인 금액 -->
        			<div style="display: flex; justify-content: space-between; margin: 10px 0;">
            			<span class="shipping">배송비</span>
            			<span class="shipping_price">0</span>
        			</div>
        			<hr>

        			<!-- 결제 예정 금액 -->
        			<div style="display: flex; justify-content: space-between; font-weight: bold; margin: 20px 0;">
            			<span class="total">결제 예정 금액</span>
            			<span class="total_price">76,000</span>
        			</div>
    			</div>

	    		<!-- 주문하기 버튼 -->
	    		<button id="btn-order">주문하기</button>
			</div>

		</div>
		
		<div class="totalDiv">
			<div class="totalA">
				<a>[배송]</a> <br> <hr class="totalHr">
				<a>상품구매금액 : </a>
				<a>76,000</a>
				<a> + 배송비 0 (무료)</a> <br> <hr class="totalHr">
				<a>합계 : </a> 
				<a>76,000</a>
			</div>	
		</div>
			
	</div>


</section>

<jsp:include page="../footer.jsp"></jsp:include>

