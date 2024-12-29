<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>

<%-- jQueryUI CSS 및 JS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
<script type="text/javascript" src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script> 

<jsp:include page="../header.jsp"></jsp:include>

<section style="width: 100%; height: auto; margin-bottom: 100px; padding-top: 4.5rem">
<form method="post" action="<%= ctxPath%>/cart/orderComplete.cl">

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
							<span class="infoSpan">이름</span>		<span id="buyName">${requestScope.m_name}</span> <br>
						</div>
						<div class="space">
							<span class="infoSpan">전화번호</span>	
							<span id="buyMobile">
								<c:if test="${not empty requestScope.m_mobile and fn:length(requestScope.m_mobile) == 11}">
								        ${fn:substring(requestScope.m_mobile, 0, 3)} -
								        ${fn:substring(requestScope.m_mobile, 3, 7)} -
								        ${fn:substring(requestScope.m_mobile, 7, 11)}
								</c:if>					
							</span> <br>
							
							<input type="hidden" name="buyEmail" val="${requestScope.m_email}" />
						</div>
						
					</div>
	   
	            </div> <!-- memberInfoDiv -->
	
	            <div id="orderInfoDiv">
	            
	            	<div id="menu2">
						<strong>배송정보</strong>
						<span class="triangle" data-target="orderInfo">&#9660;</span>
					</div>
					
					<div id="orderInfo">
						
						<!-- JS에서 동적으로 처리 -->
						
						
					</div>
					
					<button type="button" id="selectDelivery">배송지 선택</button>
	           		
	           		<div id="deliveryModal">
	           		
					    <div class="modal-content">
					        <span class="close">&times;</span>
					        <h2>배송지 선택</h2>
					
					        <div id="deliveryList">
					            <!-- 배송지 리스트 생성 -->
					            <c:forEach var="delivery" items="${deliveryList}" varStatus="status">
					            
					                <div class="delivery-item">
					                
					                    <div><input type="radio" name="delivery" value="${delivery.d_num}" /></div>
					                    <div>
						                    <label>
						                    	<div>
						                    		<span id="info">이름</span><span class="deliveryName">${delivery.d_name}</span><br>
						                    	</div>
						                        <div>
						                        	<span id="info">전화번호</span>
							                        <span class="deliveryMobile">${fn:substring(delivery.d_mobile, 0, 3)}-
							                              ${fn:substring(delivery.d_mobile, 3, 7)}-
							                              ${fn:substring(delivery.d_mobile, 7, 11)}</span> <br>
						                        </div>
						                        <div>
							                        <span id="info">주소</span><span class="deliveryPostcode">(${delivery.d_postcode})</span> <span class="deliveryAddress">${delivery.d_address} </span>
						                        </div>
						                    </label>
					                    </div>
					                </div>		<!-- <div class="delivery-item"> -->
					                
					            </c:forEach>
					        </div>
					
					        <div id="deliveryButton">
					    		배송지 선택
					    	</div>
					        
					    </div>	<!-- <div class="modal-content"> -->
					    
					    
					    
					</div>	<!-- <div id="deliveryModal"> -->
	
	
	           		
	            </div> <!-- orderInfoDiv -->
				
				<div id="productInfoDiv">
	            
	            	<div id="menu3">
						<strong>주문 상품 정보 (${fn:length(requestScope.index)}건)</strong>
						<span class="triangle" data-target="productList">&#9660;</span>
					</div>
					
					<div id="productList">
					
						<c:forEach var="i" begin="0" end="${fn:length(requestScope.index) - 1}">
						    <div id="productInfo">
						        <div style="width: 64px; height: 64px; background-color: black;"></div>
						        <div class="perfume">
						            ${requestScope.perfumeName[i]} 
						            <br>
						            <fmt:formatNumber value="${requestScope.priceQuantity[i]}" pattern="#,###,###"/>	            
						        </div>
						        <div class="option">
						            <span class="optionSpan">- 옵션 
							            <c:choose>
									        <c:when test="${requestScope.option[i] == '1'}">25ml</c:when>
									        <c:when test="${requestScope.option[i] == '2'}">50ml</c:when>
									        <c:when test="${requestScope.option[i] == '3'}">75ml</c:when>
									    </c:choose>
						            </span>/ ${requestScope.quantity[i]}개
						        </div>
						    </div>
						</c:forEach>

						
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
            			<span class="product_price">
            				<fmt:formatNumber value="${requestScope.totalProduct}" pattern="#,###,###"/>
            			</span>
        			</div>

        			<!-- 배송비 -->
        			<div style="display: flex; justify-content: space-between; margin: 10px 0;">
            			<span class="shipping">배송비</span>
            			<span class="shipping_price">
            				<fmt:formatNumber value="${requestScope.totalShipping}" pattern="#,###,###"/>
            			</span>
        			</div>
        			
        			<!-- 적립금 -->
        			<div style="display: flex; justify-content: space-between; margin: 10px 0;">
            			<span class="point">적립금</span>
            			<input class="point_price" /> / <span class="myPoint">${requestScope.m_point}</span>
        			</div>
        			<hr>

        			<!-- 결제 예정 금액 -->
        			<div style="display: flex; justify-content: space-between; font-weight: bold; margin: 20px 0;">
            			<span class="total">결제 예정 금액</span>
            			<span class="total_price">
            				<fmt:formatNumber value="${requestScope.totalTotal}" pattern="#,###,###"/>
            			</span>
        			</div>
    			</div>
				
				<!-- 주문하기 버튼 -->
	    		<button type="submit" id="btn-order" >결제하기</button>
	    		
	    		<!-- 값 넘기기 용도 -->
	    		<input type="hidden" name="totalInput" value="${requestScope.totalTotal}" />
	    		<input type="hidden" name="selectedDNum" value="selectedDNum" />
	    		<input type="text" id="contextPath" value="<%= ctxPath%>" />
	    		
			</div> <!-- payment_go_box -->
			
			
			
		</div>

	</div>


</form>
</section>



<%-- 직접 만든 JS --%>
<script type="text/javascript" src="<%= ctxPath%>/js/cart/cart_order.js"></script>

<jsp:include page="../footer.jsp"></jsp:include>