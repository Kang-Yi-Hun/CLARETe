<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
    //    /CLARETe
%> 

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/index/header.css" />
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/index/footer.css" />
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/cart/cart_order.css" />

<!-- Optional JavaScript -->
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script> 

<%-- jQueryUI CSS 및 JS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
<script type="text/javascript" src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script> 

<%-- 직접 만든 JS --%>
<script type="text/javascript" src="<%= ctxPath%>/js/cart/cart_order.js"></script>


<jsp:include page="../header.jsp"></jsp:include>

<section style="width: 100%; height: auto; margin-bottom: 100px; padding-top: 4.5rem">


	<div id="all_container">
	
		<div id="fistDiv">
			<a class="cart">장바구니</a>
			<a class="step">1.장바구니&nbsp;&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;&nbsp;&nbsp;<strong>2.주문서 작성</strong>&nbsp;&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;&nbsp;&nbsp;3.주문완료</a>
		</div>	
		
		<div id="secondDiv">
		
			<div id="infoDiv">
			
				<div id="memberInfoDiv">
					
					<div id="menu1">
						<strong>주문고객</strong>
						<span class="triangle" data-target="memberInfo">&#9660;</span>
					</div>
					
					<div id="memberInfo">
						<div class="space">
							<span class="infoSpan">이름</span>		<span>강이훈</span> <br>
						</div>
						<div class="space">
							<span class="infoSpan">전화번호</span>	<span>010-1234-1234</span> <br>
						</div>
						
					</div>
	   
	            </div> <!-- memberInfoDiv -->
	
	            <div id="orderInfoDiv">
	            
	            	<div id="menu2">
						<strong>배송정보</strong>
						<span class="triangle" data-target="orderInfo">&#9660;</span>
					</div>
					
					<div id="orderInfo">
					
						<div class="space">
							<span class="infoSpan">이름</span>	<span>장민규</span> <br>
						</div>
						<div class="space">
							<span class="infoSpan">전화번호</span>	<span>010-1234-1234</span> <br>
						</div>
						<div class="space">
							<span class="infoSpan">상세주소</span>	<span>(12345) 주소입니다.</span>
						</div>
						
					</div>
					
					<button type="button" id="selectDelivery">배송지 선택</button>
	           		
	            </div> <!-- orderInfoDiv -->
				
				<div id="productInfoDiv">
	            
	            	<div id="menu3">
						<strong>주문 상품 정보 (2건)</strong>
						<span class="triangle" data-target="productList">&#9660;</span>
					</div>
					
					<div id="productList">
					
						<div id="productInfo">
							<div style="width: 64px; height: 64px; background-color: black; "></div>
		                	<div class="perfume">
								향수이름
								<br>
								32,500
							</div>
		                	<div class="option"><span class="optionSpan">- 옵션 50ml</span>/1개</div>
						</div>

						<div id="productInfo">
							<div style="width: 64px; height: 64px; background-color: black; "></div>
		                	<div class="perfume">
								향수이름
								<br>
								32,500
							</div>
		                	<div class="option"><span class="optionSpan">- 옵션 50ml</span>/1개</div>
						</div>
						
					</div> <!-- productList -->
					
	           		
	            </div> <!-- productInfoDiv -->
	            
			</div> <!-- infoDiv -->
			
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
        			
        			<!-- 적립금 -->
        			<div style="display: flex; justify-content: space-between; margin: 10px 0;">
            			<span class="point">적립금</span>
            			<input class="point_price" />
        			</div>
        			<hr>

        			<!-- 결제 예정 금액 -->
        			<div style="display: flex; justify-content: space-between; font-weight: bold; margin: 20px 0;">
            			<span class="total">결제 예정 금액</span>
            			<span class="total_price">76,000</span>
        			</div>
    			</div>
				
				<!-- 주문하기 버튼 -->
	    		<button id="btn-order">결제하기</button>
	    		
			</div> <!-- payment_go_box -->
			
			
		</div>
		
		
		
		
		
	</div>

</div>


</section>

<jsp:include page="../footer.jsp"></jsp:include>