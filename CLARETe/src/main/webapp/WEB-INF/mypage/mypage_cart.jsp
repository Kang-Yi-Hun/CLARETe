<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
 String ctxPath = request.getContextPath();
		
		
%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>금쪽이</title>

	<link rel="stylesheet" href="<%= ctxPath %>/css/mypage/mapage.css">
	<link rel="stylesheet" href="<%= ctxPath %>/css/mypage/media_nav.css">    
	<link rel="stylesheet" href="<%= ctxPath %>/css/mypage/product_mypage.css">   
	
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    
    <!-- Optional JavaScript -->
	<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>

</head>
<body> 
	<%-- 해더 include 받아옴 --%>
    <jsp:include page="../header.jsp"></jsp:include>
	<%-- 해더 include 받아옴 --%>
	
	<%-- 마이페이지해더 include 받아옴 --%>
    <jsp:include page="mypageheader.jsp"></jsp:include>    
	<%-- 마이페이지해더 include 받아옴 --%>
	

		<%-- =============================================== --%>
        <div class="mypage_contants_bottom">
            <div style="height: auto; display: flex; align-items: center; gap: 4px;">
                <span class="recent-orders-title">장바구니</span>
            </div>

            <ul class="cardcontainer">
                <!-- === for문 대상 시작 === -->
                <li class="cardbox">
                    <a href="#">
                        <div class="cardimg">

                        </div>
                        <div class="cardname">
                            상품명
                        </div>
                        <div class="cardprice">
                            ##,###원
                        </div>
                    </a>
                </li>
                <!-- === for문 대상 끝 === -->
                 
                <li class="cardbox">
                    <a href="#">
                        <div class="cardimg">

                        </div>
                        <div class="cardname">
                            상품명
                        </div>
                        <div class="cardprice">
                            ##,###원
                        </div>
                    </a>
                </li>

                <li class="cardbox">
                    <a href="#">
                        <div class="cardimg">

                        </div>
                        <div class="cardname">
                            상품명
                        </div>
                        <div class="cardprice">
                            ##,###원
                        </div>
                    </a>
                </li>

                <li class="cardbox">
                    <a href="#">
                        <div class="cardimg">

                        </div>
                        <div class="cardname">
                            상품명
                        </div>
                        <div class="cardprice">
                            ##,###원
                        </div>
                    </a>
                </li>


            </ul>
        </div>
        <div class="cartbox">
            <div>
                <div class="cartgo"><a href="<%= ctxPath%>/cart/cart.cl">모두 구매하기</a></div>
            </div>
        </div>
		<%-- =============================================== --%>
		
		

<%-- 마이페이지푸터 include 받아옴 --%>
<jsp:include page="mypagefooter.jsp"></jsp:include>
<%-- 마이페이지푸터 include 받아옴 --%> 

<%-- 푸터 include 받아옴 --%>
<jsp:include page="../footer.jsp"></jsp:include>
<%-- 푸터 include 받아옴 --%>
    
    
</body>
</html>
