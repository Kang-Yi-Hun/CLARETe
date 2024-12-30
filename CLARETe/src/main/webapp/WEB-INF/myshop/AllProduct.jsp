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
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/shop/product.css" />

<%-- <script type="text/javascript" src="<%= ctxPath%>/js/myshop/categoryListJSON.js"></script> --%>
<script type="text/javascript" src="<%= ctxPath%>/js/myshop/mallHomeScroll.js"></script>

<jsp:include page="../header.jsp"></jsp:include>

<section class="section1">
    <div class="section_container">

        <div style="display: flex; gap: calc(4vw);">
            <div class="side_img"></div>

            
            <div class="product_container">

                <!-- 카테고리 목록 -->	
                <div class="category_title"> 
                    <div>CATEGORY<span class="cname" style="display: none;">${requestScope.cname}</span></div>
                    <div>
                        <span><a href="<%= ctxPath%>/shop/allProduct.cl?cname=0">all</a></span>
                        <span><a href="<%= ctxPath%>/shop/allProduct.cl?cname=1">spring</a></span>
                        <span><a href="<%= ctxPath%>/shop/allProduct.cl?cname=2">summer</a></span>
                        <span><a href="<%= ctxPath%>/shop/allProduct.cl?cname=3">autumn</a></span>
                        <span><a href="<%= ctxPath%>/shop/allProduct.cl?cname=4">winter</a></span>
                        <span>
                            <span>For Him</span>
                            <span>/</span>
                            <span>For Her</span>
                        </span>
                    </div>
                </div>
                <!-- 카테고리 목록 -->

                <div style="width: calc(100% - 80px);">
                    <div style="display: flex; justify-content: space-between; margin-bottom: 20px;">
                        <div>
                            <div>총 <span>${requestScope.totalHITCount}</span>개</div>
                        </div>
                        <div>
                            <div>신상품순</div>
                        </div>
                    </div>

                        <ul class="cardcontainer" style="height: auto;">
                            <!-- 해당 위치에 상품들이 나열됩니다!!!!!! -->
                        </ul>


                        <div style="display: flex; justify-content: space-between; margin-top: 60px;">
                        	<span id="end" style="display:block; margin:20px; font-size: 14pt; font-weight: bold; color: red;"></span>
	                        <span id="totalHITCount">${requestScope.totalHITCount}</span>   
		           			<span id="countHIT">0</span>    
                        </div>
                        
                        <div style="display: flex;">
					         <div style="margin: 20px 0 20px auto;">
					            <button class="btn btn-info" onclick="goTop()">맨위로가기(scrollTop 1로 설정함)</button>
					         </div>
					      </div>

                </div>
            </div>
            
        </div>


        
    </div>

    
</section>

<jsp:include page="../footer.jsp"></jsp:include>