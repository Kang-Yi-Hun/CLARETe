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
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/brandStory/Brand_Story.css" />

<jsp:include page="../header.jsp"></jsp:include>

<section class="section">
    <div class="brand_title">
        <div>당신의 평범함을 재정의하는,</div>
        <div>OBJET PERFUME BRAND.</div>
    </div>
    <div class="scroll_info">
        <span>SCROLL</span>
        <div class="scrollbar"></div>
    </div>
</section>

<section class="section2" style="height: 3000px;">
    <div class="center-box">
        <video style="position: sticky; top: 0; left: 50%; transform: translate(-50%, 0); object-fit: fill;" src="<%= ctxPath%>/video/MAISON 21G SAEKYEONG.mp4" height="100%" loop muted autoplay></video>
        <div style="position: relative; top: -105%;">
            <img src="<%= ctxPath%>/images/keyimg.png" class="overlay-image">
        </div>
    </div>
</section>

<section class="section3">
    <div class="brand_story_container">
        <div>
            <div>
                <div class="brand_story_title">FROM. CLARTÉ</div>
                <div class="brand_story">
                    우리의 목표는 단순히 향기를 판매하는 것이 아니라, 각 계절의 이야기를 향기를 통해 전달하며, <br>
                    당신의 감각과 추억을 풍요롭게 만드는 것입니다. 
                </div>
                <div class="key"></div>
                <div class="brand_story">
                    모든 제품은 자연의 아름다움과 인간의 감각적 경험을 결합하여, <br>
                    당신만의 계절을 항상 곁에 둘 수 있게 설계되었습니다.
                </div>
                <div class="brand_story">
                    계절이 가져다주는 행복과 위로를 현대인의 삶에 녹여내는 것을 미션으로 삼습니다. <br>
                    당신이 원하는 계절의 순간을 언제든 꺼내어 느낄 수 있도록, 우리는 품질과 감각을 고집합니다.
                </div>
                <div class="brand_story_end">
                    이로써 우리의 고객들은 자신의 일상을 판타지로 채워나갑니다.
                </div>
            </div>
        </div>
    </div>
</section>

<section class="section4">
    <div class="category_container">
        <div>
            SHOP BY CATEGORY
        </div>
        <div class="category_box">
            <a class="category" href="<%= ctxPath%>/shop/allProduct.cl?cname=1"><div></div><div>SPRING</div></a>
            <a class="category" href="<%= ctxPath%>/shop/allProduct.cl?cname=2"><div></div><div>SUMMER</div></a>
            <a class="category" href="<%= ctxPath%>/shop/allProduct.cl?cname=3"><div></div><div>AUTUMN</div></a>
            <a class="category" href="<%= ctxPath%>/shop/allProduct.cl?cname=4"><div></div><div>WINTER</div></a>
        </div>
    </div>
</section>

<section class="section5">

</section>

<script>
    $(document).ready(function(){
        
    	
    	
        $('.brand_title > div:nth-child(1)').css({
            'opacity': '1',
            'top': '-20px'
        });
        

        setTimeout(function(){
            $('.brand_title > div:nth-child(2)').css({
            'opacity': '1',
            'top': '0px'
        });
        }, 200)
        
        
        
        
        // === --- 스크롤링 이벤트 시작 --- === //
    $(window).scroll(() => {
        let scroll_position = $(window).scrollTop(); // 스크롤 실시간 위치
        let scroll_ratio = Math.min(scroll_position / 1080, 1); 

        let section2_offset = $('.section2').offset().top; // .section2의 화면에서의 위치
        let section2_middle = section2_offset + ($('.section2').outerHeight() / 2); // .section2의 중간 지점

        // RGB 색상과 투명도를 스크롤 비율에 따라 계산
        let background_color = "rgba(0, 0, 0, "+scroll_ratio+")";

        $('.header-container').css({
            'background-color': '#fff'
        });

        $('body').css({
            'background-color': background_color
        });

        $('.scroll_info').css({
            'display' : 'none'
        });

        if (scroll_position >= section2_middle) {
            let max_scroll = $(document).height() - $(window).height(); // 최대 스크롤 값
            let scroll_ratio = Math.min(Math.max((scroll_position - section2_middle) / 500, 0), 1); // 스크롤 비율 (0 ~ 1, 500px 기준)

            // 투명도 및 크기 조정
            $('.overlay-image').css({
                'opacity': scroll_ratio, // 투명도 증가
                'transform': "scale(" + (3 - (2 * scroll_ratio)) + ")" // 크기 감소
            });
        }
        else{
            $('.overlay-image').css({
                'opacity': '', // 투명도 증가
                'transform': '' // 크기 감소
            });
        }

        // 스크롤 비율에 따라 width 조정
        let width_percentage = scroll_ratio * 100; // 0% ~ 100%
        $('.center-box').css({
            'width': ""+scroll_ratio * 100+"%"
        });

        

        // 스크롤 맨 위로 올릴 때 초기화
        if (scroll_position == 0) {
            $('.header-container').css({
                'background-color': ''
            });

            $('body').css({
                'background-color': ''
            });

            $('.scroll_info').css({
            'display' : ''
        });
        }
    });

    // === --- 스크롤링 이벤트 끝 --- === //
        
        
    })

</script>

<jsp:include page="../footer.jsp"></jsp:include>