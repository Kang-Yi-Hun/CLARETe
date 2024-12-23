<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String ctxPath = request.getContextPath();
%>

    <header class="side-header">
        <nav class="header-nav">
            <ul>	
                <li>
                    <div>회원관리</div>
                    <ul>
                        <li>회원조회</li>
                        <li>탈퇴회원조회</li>
                    </ul>
                </li>
                <li>
                    <div>상품관리</div>
                    <ul>
                        <li>상품조회</li>
                        <li>상품 카테고리 별 조회</li>
                        <li><a href="<%= ctxPath%>/admin/adminProductInsertGo.cl">상품등록</a></li>
                        <li>상품 재고 관리</li>
                        <li>상품 삭제</li>
                    </ul>
                </li>
                <li>
                    <div>주문관리</div>
                    <ul>
                        <li>주문상세조회</li>
                        <li>배송상태변경</li>
                    </ul>
                </li>
            </ul>
        </nav>
    </header>
    <nav class="top-nav">
        <div class="nav-logo">
            <div>LOGO</div>
        </div>
        <div class="nav-btn">
            <a href="<%= ctxPath%>/admin/adminMain.cl"><div class="home-btn">홈으로</div></a>
            <a href="#"><div class="end-btn">종료</div></a>
        </div>
    </nav>