<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String ctxPath = request.getContextPath();
    //    /MyMVC
    
    String cname = "";
%>

<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/index/header.css" />
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/index/footer.css" />
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/shop/productDetail.css" />

<script type="text/javascript" src="<%= ctxPath%>/js/myshop/goCartJson.js"></script>

<jsp:include page="../header.jsp"></jsp:include>

<section class="section1">
            <div class="product_all_container">
                <div class="product_img_container">
                    <div style="display: flex; gap: calc(2vw); width: var(--size930); height: auto; margin-bottom: 60px;">
                        <div class="product_main_img" style="background-image:url(<c:out value="${requestScope.pvo.p_image}" />);"></div>
                        <ul class="product_sub_imgs">
                            <!-- 서브 이미지 -->
                            <li></li>
                            <li></li>
                            <!-- 서브 이미지 -->
                        </ul>
                    </div>

                    <div style="width: var(--size700); background-color: #D9D9D9;">
					    <img src="<c:out value='${requestScope.pvo.p_detail_image}' />" 
					         alt="상세 이미지" 
					         style="width: 100%; height: auto;" />
					</div>
                </div>

            
				
	                <div class="productInfo_contaienr">
	                <form name="productFrm"> 
	                <input type="hidden" name="p_num" value="${requestScope.pvo.p_num}"/> <%-- === 상품 번호 input 자리 === --%>
	                <input type="hidden" name="m_id" value="${requestScope.m_id}"/> <%-- === 로그인한 사람 input 자리 비로그인시 0으로 나옴 === --%>
	                    <div class="product_title"><c:out value="${requestScope.pvo.p_name}" /></div> <!-- 상품명이 들어설 자리입니다. -->
	                    <div class="product_price"> 
	                    	<div class="consumerPrice">
	                    		<span>소비자가</span> <span>${requestScope.pvo.p_price}</span><span>원</span>
	                    	</div>
	                        <span id="p_price">
	                        	<c:out value="${requestScope.pvo.discountPrice}" />
	                        </span>
	                        <span><input name="p_price" type="hidden" value="${requestScope.pvo.p_price}"/></span> <%-- === 상품 가격 input 자리 === --%>
	                        <span>원</span> <!-- 상품가격이 들어설 자리입니다. -->
	                        <span class="sale">-<c:out value="${requestScope.pvo.p_sale}" />%</span>
	                    </div>
	                    <div class="product_info">
	                        <div>
	                            <span>국내 · 해외배송</span><span>국내배송</span>
	                        </div>
	                        <div>
	                            <span>배송방법</span><span>택배</span>
	                        </div>
	                        <div>
	                            <span>배송비</span><span>3,000(20,000원 이상 구매 시 무료)</span>
	                        </div>
	                        <div>
	                            <span>사용후기</span><span>116</span> <!-- 리뷰개수가 들어설 자리입니다. -->
	                        </div>
	                        <div>
	                            <span>향수문의</span><span>67</span> <!-- 보류 -->
	                        </div>
	                        <div>
	                            <span>예상 적립금</span><span>상품의 1%</span>
	                        </div>
	                    </div>
	                    <div class="option_container">
	                        <div>옵션 (용량)</div>
	                        <div class="select-container">
	                            <select name="selectOption"> <%-- === 상품 옵션 select 자리 === --%>
	                                <option value="" selected disabled hidden style="color: #7B7B7B;">- [필수] 옵션을 선택해 주세요 -</option> 
	                                	<c:forEach var="ovo" items="${requestScope.ovo}">
	                                		<c:choose>
	                                			<c:when test="${ovo.op_ml eq '1'}">
	                                				<option class="${ovo.op_price}" value="${ovo.op_num}">50ml / +${ovo.op_price}원</option>
	                                			</c:when>
	                                			<c:when test="${ovo.op_ml eq '2'}">
	                                				<option class="${ovo.op_price}" value="${ovo.op_num}">75ml / +${ovo.op_price}원</option>
	                                			</c:when>
	                                			<c:otherwise>
	                                				<option class="${ovo.op_price}" value="${ovo.op_num}">100ml / +${ovo.op_price}원</option>
	                                			</c:otherwise>
	                                		</c:choose>
	                                	</c:forEach>
	                            </select>
	                        </div>
	                    </div>
	                    <div class="product_pricebox">
	                        <span>TOTAL</span>
	                        <span>
	                            <span id="totalPrice">0<input type="hidden" name="totalPrice"/></span><!-- 전체 합산금액 -->
	                            <span>원</span> 
	                            <span>
	                                <span>(</span><span id="totalCnt">0</span><span>개)</span> <!-- 전체 개수 -->
	                            </span>
	                        </span>
	                    </div>
	                    <div class="product_btn_container">
	                        <div onClick="goPurchase()">바로 구매하기</div>
	                        <div onClick="goCart()">장바구니</div> <!-- 버튼 -->
	                    </div>
	                    
	                    </form>
	                </div>
				
                
            </div>
        </section>
<script>
    $(document).ready(function() {
    	// === 가격 콤마처리 === //
        const priceElement = $('span#p_price');
        const consumer = $('body > section > div > div.productInfo_contaienr > form > div.product_price > div > span:nth-child(2)');
        
        let price = parseInt(priceElement.html());
        let numerconsumer = parseInt(consumer.html());
        
        priceElement.html(price.toLocaleString('en'));
        consumer.html(numerconsumer.toLocaleString('en'));
     	// === 가격 콤마처리 === //
        
        
     	// === select 태그 변경시 + 옵션가격 된 금액 표출 === //
        $('select[name="selectOption"]').change(function(){
        	// console.log($(this).val());
        	
        	const selectOp = $('select[name="selectOption"] option:selected'); // 선택자 select태그 아래의 option 중 선택된 녀석을 잡음
        	
        	const classPrice = selectOp.attr('class'); // 위에서 선택된 녀석의 attr의 class를 불러옴
        	
        	const totalPrice = price + Number(classPrice); // 클래스가 매칭된 값이 즉 옵션의 가격이니 더해서 넣으면 끝
        	
        	// alert(totalPrice);
        	$('input[name="totalPrice"]').val(totalPrice);
        	
        	$('span#totalPrice').html(totalPrice.toLocaleString('en')); // 대 ㅡ 시 ㅡ 진	
        	$('span#totalCnt').html("1")
        }) // end of $('select[name="selectOption"]').change(function()------------
     	// === select 태그 변경시 + 옵션가격 된 금액 표출 === //
     	
     	
     	
     	
    });
</script>
<jsp:include page="../footer.jsp"></jsp:include>