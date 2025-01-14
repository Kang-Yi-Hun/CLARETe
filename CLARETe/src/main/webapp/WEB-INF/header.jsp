<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
    
<%
    String ctxPath = request.getContextPath();
    //    /MyMVC
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>금쪽이</title>
    
    <!-- Link Swiper's CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    
    <!-- 해더, 푸터 css -->
    <link rel="stylesheet" href="<%= ctxPath %>/css/index/header.css">
   <link rel="stylesheet" href="<%= ctxPath %>/css/index/footer.css">
   
   <!-- Optional JavaScript -->
   <script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
</head>
<body> 
    <div id="container">
        <div class="header-container">
            <header>
                <div class="hamburgerbar" id="hamburger">
                    <ul>
                        <li class="hamburger"></li>
                        <li class="hamburger"></li>
                        <li class="hamburger"></li>
                    </ul>
                </div>
                <a href="<%= ctxPath%>/">	
                   <div class="header-logo"></div>
                </a>
                <div>


<div class="header-icon-wrapper">
    <ul>
        <c:choose>
            <c:when test="${not empty sessionScope.loginuser && sessionScope.loginuser.getM_id() == 'admin'}">
                <li><a href="<%= ctxPath %>/login/logout.cl">Logout</a></li>
                <li style="position: relative;">
                	<div id="showAdminGo" style="cursor: pointer;">admin</div> 
                	<div id="showAdmin" style="display:none; box-shadow: 0.1px 0.1px 0.1px gray; border-radius:8px; overflow:hidden; cursor: pointer; position: absolute; top: 3rem; background-color: #000; width: 300%; left: -100%;">
                		<ul style="text-align: center;">
                			<li style="box-sizing: border-box; margin:15px;">
                				<a style="color:#fff !important;" href="<%= ctxPath %>/admin/admin.cl">회원관리</a>
                			</li>
                			<li style="box-sizing: border-box; margin:15px;">
                				<a style="color:#fff;" href="<%= ctxPath %>/admin/adminProduct.cl">상품관리</a>
                			</li>
                			<li style="box-sizing: border-box; margin:15px;">
                				<a style="color:#fff;" href="<%= ctxPath %>/admin/adminMain.cl">관리자메인</a>
                			</li>
                		</ul>
                	</div>
                </li>
            </c:when>
            
            <c:when test="${not empty sessionScope.loginuser}">
                <li><a href="<%= ctxPath %>/login/logout.cl">Logout</a></li>
                <li><a href="<%= ctxPath %>/mypage/mypage.cl">My Page</a></li>
            </c:when>
            
            <c:otherwise>
                <li><a href="<%= ctxPath %>/login/loginView.cl">Login</a></li>
                <li><a href="<%= ctxPath %>/member/memberRegister.cl">Sign Up</a></li>
                <li><a href="<%= ctxPath %>/login/loginView.cl">My Page</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>



                </div>
            </header>
        </div>

        <div class="nav-container">
            <nav>
                <div class="nav_inner">
                    <ul class="nav_list">
                        <li class="menu">
                            <a href="<%= ctxPath%>/brand/brandStory.cl">ABOUT</a>
                            <div class="depth">
                                <ul>
                                    <li><a href="<%= ctxPath%>/brand/brandStory.cl">BRAND STORY</a></li>
                                    <li><a href="<%= ctxPath%>/brand/brandTimeLine.cl">BRAND TIMELINE</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="menu">
                            <a href="<%= ctxPath%>/shop/allProduct.cl?cname=0">PRODUCT</a>
                            <div class="depth">
                                <ul>
                                    <li><a href="<%= ctxPath%>/shop/allProduct.cl?cname=1">SPRING</a></li>
                                    <li><a href="<%= ctxPath%>/shop/allProduct.cl?cname=2">SUMMER</a></li>
                                    <li><a href="<%= ctxPath%>/shop/allProduct.cl?cname=3">AUTUMN</a></li>
                                    <li><a href="<%= ctxPath%>/shop/allProduct.cl?cname=4">WINTER</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="menu">
                            <a href="<%= ctxPath%>/category/productCategory.cl">CATEGORY</a>
                            <div class="depth">
                                <ul>
                                    <li><a href="<%= ctxPath%>/shop/allProduct.cl?cname=5">For Him</a></li>
                                    <li><a href="<%= ctxPath%>/shop/allProduct.cl?cname=6">For Her</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="menu">
                            <a>BOARD</a>
                            <div class="depth">
                                <ul>
                                    <li><a href="<%= ctxPath%>/faq/faq_board.cl">FAQ</a></li>
                                    <li><a href="<%= ctxPath%>/qna/qna_board.cl">INQUIRY</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>

        
    </div>
    <script>
        // === 토글 되는 nav바 시작 === //
        $(document).ready(function(){
           let hamburger_cnt=0;

            $('#hamburger').click(()=>{
                hamburger_cnt++;

                if(hamburger_cnt%2!=0){
                    $('.nav-container').css({
                        'left': '0'
                    });
                }
                else if(hamburger_cnt%2==0){
                    $('.nav-container').css({
                        'left': ''
                    });
                }
            });
        })
        // === 토글 되는 nav바 끝 === //
        

        // === --- 스크롤링 이벤트 끝 --- === //

		$('#showAdminGo').click(function(){
			$('#showAdmin').slideToggle();
		})


    </script>
</body>
</html>
