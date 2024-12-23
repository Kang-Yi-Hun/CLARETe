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
    <link rel="stylesheet" href="./css/header.css">
    <link rel="stylesheet" href="./css/section.css">
    
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
                <div class="header-logo">
                    <a href="<%= ctxPath%>/">
                        <h2>LOGO</h2>
                    </a>
                </div>
                <div>
                    <div class="header-icon-wrapper">
                        <ul>
                        	<c:if test="${not empty sessionScope.loginuser}">
                        		<li><a href="<%= ctxPath%>/login/logout.cl">Logout</a></li>
                        		<li><a href="<%= ctxPath%>/mypage/mypage.cl">My Page</a></li>
                        	</c:if>
                        	
                        	<c:if test="${empty sessionScope.loginuser}">
                        		<li><a href="<%= ctxPath%>/login/loginView.cl">Login</a></li>
                            	<li><a href="<%= ctxPath%>/member/memberRegister.cl">Sign Up</a></li>
                            	<li><a href="<%= ctxPath%>/login/loginView.cl">My Page</a></li>
                        	</c:if>
                            
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
                            <a>ABOUT</a>
                            <div class="depth">
                                <ul>
                                    <li><a href="#">BRAND STORY</a></li>
                                    <li><a href="#">BRAND CAMPAIGN</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="menu">
                            <a>PRODUCT</a>
                            <div class="depth">
                                <ul>
                                    <li><a href="#">SPRING</a></li>
                                    <li><a href="#">SUMMER</a></li>
                                    <li><a href="#">AUTUMN</a></li>
                                    <li><a href="#">WINTER</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="menu">
                            <a>CATEGORY</a>
                            <div class="depth">
                                <ul>
                                    <li><a href="#">For Him</a></li>
                                    <li><a href="#">For Her</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="menu">
                            <a>BOARD</a>
                            <div class="depth">
                                <ul>
                                    <li><a href="#">FAQ</a></li>
                                    <li><a href="#">INQUIRY</a></li>
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
        // === 토글 되는 nav바 끝 === //
        

        // === --- 스크롤링 이벤트 시작 --- === //
        const section3_position = $('.section3').offset().top; // 색션2 위치

        $(window).scroll(()=>{
            let scroll_position = parseInt($(window).scrollTop()); // 스크롤 실시간 위치
            let y = 5/94*scroll_position;

            // === 스크롤 했을 때의 경우 === //
            if(scroll_position != 0){
                $('.section2').css({
                    'transform' : 'translateY(-100px)',
                    'opacity' : '1'
                    // 'transform' : `translateX(-${y}%)`
                });
                $('.forOpacity').css({
                    'opacity' : '1',
                    'top' : '0'
                })
            } // end of if(scroll_position != 0)------

            // === 스크롤을 가장 위로 올렸을 때의 경우 === //
            else if(scroll_position == 0){
                $('.section2').css({
                    'transform' : '',
                    'opacity' : ''
                });
            } // end of else if(scroll_position == 0)------



            if(scroll_position >= section3_position){ // 만약 스크롤이 색션2를 넘어섰을 때
                $('.header-container').css({
                    'background' : 'rgba(255, 255, 255, 0.7)',
                    'backdrop-filter' : 'blur(3px)',
                    '-webkit-backdrop-filter' : 'blur(3px)'
                })
                $('.section4').css({
                    'opacity' : '1',
                    'top' : '0'
                })
            }
            if(scroll_position <= section3_position){
                $('.header-container').css({
                    'background' : '',
                    'backdrop-filter' : '',
                    '-webkit-backdrop-filter' : ''
                })
            }


        });
        // === --- 스크롤링 이벤트 끝 --- === //


        $(document).ready(function(){
        
            const title = $('.title').text();

            // console.log(title.length);

            let sum_span=``;
            let html = ``;
            for(let i=0; i<title.length; i++){
                console.log(title.charAt(i));
                html+=`<span class="title${i} titleall style="left:${i}00px;">${title.charAt(i)}</span>`;
                
                setTimeout( function(){
                    $(`span.title${i}`).css({
                        'opacity': '1',
                        'top': '0px'
                    })
                }, i*50);
            }
            $('.title').html(html);
        })




        $(document).ready(function(){
        
        const title = $('.title2').text();

        // console.log(title.length);

        let sum_span=``;
        let html = ``;
        for(let i=0; i<title.length; i++){
            console.log(title.charAt(i));
            html+=`<span class="title${i} titleall style="left:${i}00px;">${title2.charAt(i)}</span>`;
            
            setTimeout( function(){
                $(`span.title${i}`).css({
                    'opacity': '1',
                    'top': '0px'
                })
            }, i*50);
        }
        $('.title2').html(html);
    })





    </script>    
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script>
        var swiper = new Swiper(".mySwiper", {
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
        pagination : {
            el:".swiper-pagination",
            clickable:true,
        },
        mousewheel:false,
        keyboard:true,
        loop: true,
        autoplay:true,
        sildesPerView:1,
        spaceBetween:0,
        effect:'fade',
        speed:1000,
        
        });

        var swiper = new Swiper('.mySwiper',{
            sildesPerView: 1,
            breakpoints:{
                640:{
                    sildesPerView:2,
                },
                768:{
                    sildesPerView:3,
                },
                1024:{
                    sildesPerView:4,
                },
            }
        });
    </script>
</body>
</html>
