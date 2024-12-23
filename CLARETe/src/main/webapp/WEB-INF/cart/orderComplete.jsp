<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String ctxPath = request.getContextPath();
//    /CLARETe
%>

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css"
	href="<%=ctxPath%>/css/index/header.css" />
<link rel="stylesheet" type="text/css"
	href="<%=ctxPath%>/css/index/footer.css" />
<link rel="stylesheet" type="text/css"
	href="<%=ctxPath%>/css/cart/orderComplete.css" />

<!-- Optional JavaScript -->
<script type="text/javascript"
	src="<%=ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript"
	src="<%=ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js"></script>

<%-- jQueryUI CSS 및 JS --%>
<link rel="stylesheet" type="text/css"
	href="<%=ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
<script type="text/javascript"
	src="<%=ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script>

<%-- 직접 만든 JS --%>
<script type="text/javascript"
	src="<%=ctxPath%>/js/cart/orderComplete.js"></script>


<jsp:include page="../header.jsp"></jsp:include>


<section
	style="width: 100%; height: auto; margin-bottom: 100px; padding-top: 4.5rem">

	<div id="all_container">

		<div id="fistDiv">
			<a class="cart">장바구니</a> <a class="step">1.장바구니&nbsp;&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;&nbsp;&nbsp;2.주문서
				작성&nbsp;&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;&nbsp;&nbsp;<strong>3.주문완료</strong>
			</a>
		</div> <!-- div#fistDiv -->

		<div id="secondDiv">

			<div id="orderImg">
				<img src="../images/shoppingbag.png" />
			</div>

			<div id="ordernum">
				<span>2024-12-21 주문하신 향수의</span><br> <span>주문번호는 <span
					id="num">1234</span> 입니다
				</span>
			</div> <!-- div#ordernum -->

		</div> <!-- div#secondDiv -->

		<div id="thirdDiv">

			<div id="productInfoDiv">

				<div id="menu1">
					<strong>주문 상품 정보 (2건)</strong>
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
					
				</div> <!-- div#productList -->

			</div> <!-- div#productInfoDiv -->

		</div> <!-- div#thirdDiv -->
	
		<div id="fourth">
			
			<div id="leftDiv">
				
				<div id="menu2">
					<strong>배송정보</strong>
				</div>
				
				<table >
					<tr>
						<td class="infoTd">이름</td>
						<td>장민규</td>
					</tr>
					<tr>
						<td class="infoTd">전화번호</td>
						<td>010-1212-3434</td>
					</tr>
					<tr>
						<td class="infoTd">상세주소</td>
						<td>(12345) 주소입니다.</td>
					</tr>
				</table>
				
			</div> <!-- div#leftDiv -->
			
			<div id="rightDiv">
				
				<div id="menu3">
					<strong>결제정보</strong>
				</div>
				
				<table>
					<tr>
						<td class="priceTd">상품금액</td>
						<td>76,000</td>
					</tr>
					<tr>
						<td class="priceTd">할인금액</td>
						<td>0</td>
					</tr>
					<tr>
						<td class="priceTd">배송비</td>
						<td>0</td>
					</tr>
					<tr>
						<td class="priceTd total">최종 결제 금액</td>
						<td class="total">76,000</td>
					</tr>
				</table>
				
			</div> <!-- div#rightDiv -->
			
		</div> <!-- div#fourth -->
		
		<a>주문 계속하기</a>
	</div> <!-- div#all_container -->

</section>

<jsp:include page="../footer.jsp"></jsp:include>




