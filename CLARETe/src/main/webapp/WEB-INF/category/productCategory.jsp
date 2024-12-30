<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String ctxPath = request.getContextPath();
    //    /MyMVC
%>

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/index/header.css" />
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/index/footer.css" />
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/category/category.css" />

<jsp:include page="../header.jsp"></jsp:include>

<section class="section">
    <div class="category-container">
        <div class="man genger">
	        <a href="<%= ctxPath%>/shop/allProduct.cl?cname=5">
	            <div class="forHimBox">
	                <div class="for_box">
	                    <div class="for_title">For Him</div>
	                    <div>남성들이 선호하는 향수를 찾아보세요</div>
	                </div>
	            </div>
            </a>
        </div>
        <div class="all genger">
        	<a href="<%= ctxPath%>/shop/allProduct.cl?cname=7">
	            <div class="forHimBox">
	                <div class="for_box">
	                    <div class="for_title">ALL</div>
	                    <div>모두가 선호하는 향수를 찾아보세요</div>
	                </div>
	            </div>
            </a>
        </div>
        <div class="woman genger">
	        <a href="<%= ctxPath%>/shop/allProduct.cl?cname=6">
	            <div class="forHimBox">
	                <div class="for_box">
	                    <div class="for_title">For Her</div>
	                    <div>여성들이 선호하는 향수를 찾아보세요</div>
	                </div>
	            </div>
            </a>
        </div>
    </div>
</section>

<script>
    $(document).ready(function(){
        
    	$('body').css({
            'background-color': '#000'
        });
    	
        $('.header-container').css({
            'background-color': '#fff'
        });
	
        
        
    })

</script>

<jsp:include page="../footer.jsp"></jsp:include>