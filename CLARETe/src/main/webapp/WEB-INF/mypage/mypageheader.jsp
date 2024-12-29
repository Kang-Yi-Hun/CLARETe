<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%
    String ctxPath = request.getContextPath();
    //    /CLARETe
%>

<%-- 직접 만든 JS --%>
<script type="text/javascript" src="<%= ctxPath%>/js/template/template.js"></script>

<script type="text/javascript">

</script>

<section class="section1">
            <div class="mypage_container">
                <div class="mypage_sadebar">
                    <div class="mapage_hello">WELLCOME!</div>
                    <ul>
                        <li>
                            <div>내 정보</div>
                            <ul>
                                <li><a href="<%= ctxPath%>/mypage/mypage_memberUpdate.cl">내 정보 수정</a></li>
                                <li><a href="#">배송지 입력</a></li>
                                <li><a href="<%= ctxPath%>/mypage/memberDelete.cl">회원탈퇴</a></li>
                            </ul>
                        </li>
                        <li>
                            <div>향수</div>
                            <ul>
                                <li><a href="<%= ctxPath%>/mypage/mypage_recent_orders.cl">최근 주문 향수</a></li>
                                <li><a href="<%= ctxPath%>/mypage/mypage_current_product.cl">최근 본 향수</a></li>
                                <li><a href="<%= ctxPath%>/mypage/mypageCart.cl">장바구니</a></li>
                            </ul>
                        </li>
                        <li>
                            <div>나의 리뷰</div>
                            <ul>
                                <li><a href="<%= ctxPath%>/mypage/mypage_myreview.cl">내가 쓴 리뷰</a></li>
                                <li><a href="<%= ctxPath%>/mypage/mypage_write_review.cl">리뷰작성</a></li>
                            </ul>
                        </li>
                        <li>
                            <div>문의하기</div>
                        </li>
                    </ul>
                </div>

                <div class="mypage_contants">
                    <div class="mypage_contants_top">
                        <div class="top_title">
                            <span>강이훈</span><span>님 반갑습니다.</span>
                            <div><a href="#">회원 정보 수정</a></div>
                        </div>
                        <div class="top_contants">
                            <ul>
                                <li>
                                    <div>
                                        장바구니
                                    </div>
                                    <div>
                                        <span>3</span><span>개</span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        최근 본 상품
                                    </div>
                                    <div>
                                        <span>0</span><span>개</span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        POINT
                                    </div>
                                    <div>
                                        <span>500</span><span>P</span>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <!-- === 반응형 nav바 === -->
                    <ul class="media_nav">
                        <li>
                            <div style="pointer-events: none;">내 정보</div>
                            <ul>
                                <li><a href="#">내 정보 수정</a></li>
                                <li><a href="#">배송지 입력</a></li>
                                <li><a href="<%= ctxPath%>/mypage/memberDelete.cl">회원탈퇴</a></li>
                            </ul>
                        </li>
                        <li>
                            <div style="pointer-events: none;">향수</div>
                            <ul>
                                <li><a href="<%= ctxPath%>/mypage/mypage_recent_orders.cl">최근 주문 향수</a></li>
                                <li><a href="<%= ctxPath%>/mypage/mypage_current_product.cl">최근 본 향수</a></li>
                                <li><a href="<%= ctxPath%>/mypage/mypageCart.cl">장바구니</a></li>
                            </ul>
                        </li>
                        <li>
                            <div style="pointer-events: none;">나의 리뷰</div>
                            <ul>
                                <li><a href="<%= ctxPath%>/mypage/mypage_myreview.cl">내가 쓴 리뷰</a></li>
                                <li><a href="<%= ctxPath%>/mypage/mypage_write_review.cl">리뷰작성</a></li>
                            </ul>
                        </li>
                        <li>
                            <div style="pointer-events: none;">문의하기</div>
                        </li>
                    </ul>
                    <!-- === 반응형 nav바 === -->