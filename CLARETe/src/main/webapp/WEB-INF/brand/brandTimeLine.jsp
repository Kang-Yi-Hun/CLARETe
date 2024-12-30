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
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/brandStory/Time_Line.css" />

<!-- AOS 라이브러리 불러오기-->
<link rel="stylesheet" href="https://unpkg.com/aos@2.3.1/dist/aos.css"> 
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script> 

<jsp:include page="../header.jsp"></jsp:include>

<section class="section">
    <div class="section_container">
        <div class="time_line_container">
            <div class="time_line_title">TIMELINE</div>
            <div class="time_line">
                <ul class="time_line_box">



                    <li>
                        <div class="time_line_contant">
                            <div data-aos="fade-up" 
                                data-aos-offset="100" 
                                data-aos-easing="ease-out-cubic"
                                data-aos-duration="1500" 
                                data-aos-once="false">
                                <div>2024.11.27</div>
                                <div>
                                    <div>· 세미 프로젝트 시작</div>
                                    <div>· 팀원선정</div>
                                </div>
                            </div>
                        </div>
                        <div class="time_line_img" 
                            data-aos="fade" 
                            data-aos-offset="100" 
                            data-aos-easing="ease-out-cubic"
                            data-aos-duration="1500" 
                            data-aos-once="false"
                            style="background-image: url(https://d3ha2047wt6x28.cloudfront.net/25xQUQHiiAA/pr:GOODS_DETAIL/czM6Ly9hYmx5LWltYWdlLWxlZ2FjeS9kYXRhL2dvb2RzLzIwMjMwNTEwXzE2ODM3MDE3NDY3MzgxMTNtLmpwZw);
                                   background-position: center;
                                   background-size: cover;">
                        </div>
                    </li>
                    

                    <li>
                        <div class="time_line_contant">
                            <div data-aos="fade-up" 
                                data-aos-offset="300" 
                                data-aos-easing="ease-out-cubic"
                                data-aos-duration="1500" 
                                data-aos-once="false">
                                <div>2024.12.07</div>
                                <div>
                                    <div>· 레퍼런스 참고</div>
                                    <div>· 피그마 페이지 전체 디자인</div>
                                    <div>· 브랜드 CLARTÉ 설립</div>
                                </div>
                            </div>
                        </div>
                        <div class="time_line_img" 
                            data-aos="fade" 
                            data-aos-offset="300" 
                            data-aos-easing="ease-out-cubic"
                            data-aos-duration="1500" 
                            data-aos-once="false"
                            style="background-image: url(https://d2v80xjmx68n4w.cloudfront.net/members/portfolios/vZOvF1655171935.jpg?w=500);
                                   background-position: center;
                                   background-size: cover;">
                        </div>
                    </li>


                    <li>
                        <div class="time_line_contant">
                            <div data-aos="fade-up" 
                                data-aos-offset="300" 
                                data-aos-easing="ease-out-cubic"
                                data-aos-duration="1500" 
                                data-aos-once="false">
                                <div>2024.12.11</div>
                                <div>
                                    <div>· GIT 연습</div>
                                    <div>· ORACLE 테이블 설계 및 생성</div>
                                </div>
                            </div>
                        </div>
                        <div class="time_line_img" 
                            data-aos="fade" 
                            data-aos-offset="300" 
                            data-aos-easing="ease-out-cubic"
                            data-aos-duration="1500" 
                            data-aos-once="false"
                            style="background-image: url(https://d3ha2047wt6x28.cloudfront.net/ZY06nVyIyEE/pr:GOODS_DETAIL/czM6Ly9hYmx5LWltYWdlLWxlZ2FjeS9kYXRhL2dvb2RzLzIwMjMwNTEwXzE2ODM3MDE3OTI0MTI0NDRtLmpwZw);
                                   background-position: center;
                                   background-size: cover;">
                        </div>
                    </li>
                    
                    
                    <li>
                        <div class="time_line_contant">
                            <div data-aos="fade-up" 
                                data-aos-offset="300" 
                                data-aos-easing="ease-out-cubic"
                                data-aos-duration="1500" 
                                data-aos-once="false">
                                <div>2024.12.14</div>
                                <div>
                                    <div>· 본격적인 기능 구현 돌입</div>
                                    <div>· GIT main branch 저장소 지운 날</div>
                                </div>
                            </div>
                        </div>
                        <div class="time_line_img" 
                            data-aos="fade" 
                            data-aos-offset="300" 
                            data-aos-easing="ease-out-cubic"
                            data-aos-duration="1500" 
                            data-aos-once="false"
                            style="background-image: url(https://d2v80xjmx68n4w.cloudfront.net/members/portfolios/ciZk31655172084.jpg?w=500);
                                   background-position: center;
                                   background-size: cover;">
                        </div>
                    </li>



                    <li>
                        <div class="time_line_contant">
                            <div data-aos="fade-up" 
                                data-aos-offset="300" 
                                data-aos-easing="ease-out-cubic"
                                data-aos-duration="1500" 
                                data-aos-once="false">
                                <div>2024.12.25</div>
                                <div>
                                    <div>· 메리크리스마스</div>
                                </div>
                            </div>
                        </div>
                        <div class="time_line_img" 
                            data-aos="fade" 
                            data-aos-offset="300" 
                            data-aos-easing="ease-out-cubic"
                            data-aos-duration="1500" 
                            data-aos-once="false"
                            style="background-image: url(https://d3ha2047wt6x28.cloudfront.net/z7yewF98AnA/pr:NEW_GOODS_THUMB_WEBP/czM6Ly9hYmx5LWltYWdlLWxlZ2FjeS9kYXRhL2dvb2RzLzMxYjgyMzM5MDE0NDczMGRiZGViOTRmYzQ5Y2U2ZjU0LmpwZw);
                                   background-position: center;
                                   background-size: cover;">
                        </div>
                    </li>

                    
                    <li>
                        <div class="time_line_contant">
                            <div data-aos="fade-up" 
                                data-aos-offset="300" 
                                data-aos-easing="ease-out-cubic"
                                data-aos-duration="1500" 
                                data-aos-once="false">
                                <div>2024.01.08</div>
                                <div>
                                    <div>· CLARTÉ 프로젝트 종료 및 발표</div>
                                </div>
                            </div>
                        </div>
                        <div class="time_line_img" 
                            data-aos="fade" 
                            data-aos-offset="300" 
                            data-aos-easing="ease-out-cubic"
                            data-aos-duration="1500" 
                            data-aos-once="false"
                            style="background-image: url(https://d2v80xjmx68n4w.cloudfront.net/members/portfolios/a2iWk1655172083.jpg?w=500);
                                   background-position: center;
                                   background-size: cover;">
                        </div>
                    </li>

                </ul>
            </div>

            

            <div class="scroll_bar">
               <div class="bar">
                   <div></div>
               </div>
            </div>

            
            <div class="back_logo">CLARTÉ</div>
        </div>
    </div>
</section>

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
$(window).scroll(() => {
    let scroll_position = $(window).scrollTop(); // 스크롤 실시간 위치
    let scroll_container = $('.time_line_container').height(); // 스크롤 위치 크기
    let scroll_ratio = Math.min(scroll_position / 300, 0); 

    let background_color = scroll_ratio;

    console.log(scroll_position);
    console.log(scroll_container);

    let scroll_now = Math.min(Math.max(((scroll_position / scroll_container)* 100), 0), 90);

    console.log(scroll_now)

    $('.bar').css({
        'top': "calc("+scroll_now+"%)"
    })
    $('.time_line_title').css({
        'opacity': background_color
    })

    if(scroll_position == 0){
        $('.time_line_title').css({
        'opacity': ''
    })
    }

});
// === --- 스크롤링 이벤트 끝 --- === //


$(document).ready(function(){
    $('.header-container').css({
        'background-color': '#fff',
    });

    $('.back_logo').css({
        'right': '20%',
        'opacity': '0.1'
    })
})

</script>
<!-- AOS 스크립트 시작 -->
<script> 
    AOS.init(); // 자바스크립트로 init()을 해야 동작한다.
</script>
<jsp:include page="../footer.jsp"></jsp:include>