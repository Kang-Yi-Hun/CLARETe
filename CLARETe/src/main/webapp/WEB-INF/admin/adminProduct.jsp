<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*" %>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/admin_section.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/admin_header.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script>

<title>상품 회원 조회</title>

<header class="side-header">
    <nav class="header-nav">
        <ul>
			<li>
				<div>회원관리</div>
				<ul>
					<li><a href="<%= request.getContextPath() %>/admin/admin.cl">회원조회</a></li>
					<li><a
						href="<%=request.getContextPath()%>/admin/adminOrder.cl">주문회원조회</a></li>
					<li>탈퇴회원조회</li>
				</ul>
			</li>
			<li>
				<div>상품관리</div>
				<ul>
					<a href="<%= request.getContextPath() %>/admin/adminProduct.cl">상품조회</a>
					<li>상품 카테고리 별 조회</li>
					<li>상품등록</li>
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
        <a href="#"><div class="home-btn">홈으로</div></a>
        <a href="#"><div class="end-btn">종료</div></a>
    </div>
</nav>

<section>
    <div style="display: flex; flex-wrap: wrap;">
        <div class="first-div">
            <div style="margin: 30px 0px 0px 30px;">
                <form action="<%= request.getContextPath() %>/admin/adminOrder.cl" method="GET">

                    <form name="member_search_frm">
                    	<select name="searchType">
                    		<option value="">검색대상</option>
                    		<option value="userid"></option>
                    		<option value="username">유저명</option>
                    		<option value="delete">삭제여부</option>
                    		<option value="hyumen">휴면여부</option>
                    	</select>
                    	&nbsp;
                    	<input type="text" name="searchWord" />
                    </form>

                    <!-- 버튼 -->
                    <div class="form-btn-box">
                        <input type="submit" class="btn search" value="검색" />
                        <input type="reset" class="btn reset" value="초기화" />
                    </div>
                </form>
            </div>

        </div>
        
<!-- 상품 조회 테이블 -->
            <div class="second-div">
                <h4 style="font-weight: bold; text-align: center; margin-top: 50px; padding: 2% 0;">상품조회</h4>
				<div class="table-container" stlye=" overflow-x: scroll;">
					<table style="width: 100% !important;" class="table">
						<thead class="thead-light">
							<tr>  
								<th>상품번호</th>
								<th>카테고리(계절)</th>
								<th>제품명</th>
								<th>제품설명</th>
								<th>가격</th>
								<th>재고</th>
								<th>제품등록일</th>
								<th>발매일</th>
								<th>할인률</th>
								<th>성별향수</th>
<!-- 								<th>상품이미지</th>
								<th>상품상세이미지</th> -->
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty requestScope.productAll_list}">
								<c:forEach var="product" items="${requestScope.productAll_list}">
									<tr>
										<td>${product.p_num}</td>
										<td><c:choose>
												<c:when test="${product.p_season == '1'}">봄</c:when>
												<c:when test="${product.p_season == '2'}">여름</c:when>
												<c:when test="${product.p_season == '3'}">가을</c:when>
												<c:otherwise>겨울</c:otherwise>
											</c:choose></td>
										<td>${product.p_name}</td>
										<td>${product.p_ex}</td>
										<td>
									    	<fmt:formatNumber value="${product.p_price}" type="number" groupingUsed="true" />
										</td>
										<td>${product.p_inven}</td>
										<td>${product.p_register}</td>
										<td>${product.p_release}</td>
										<td>${product.p_sale}</td>
										<td><c:choose>
												<c:when test="${product.p_gender == '1'}">남</c:when>
												<c:otherwise>여</c:otherwise>
											</c:choose></td>
<%-- 										<td>${product.p_image}</td>
										<td>${product.p_detail_image}</td> --%>
										
<%-- 									<td>${order.membervo.m_name}</td>
										<td>${order.membervo.m_email}</td>
										<td>${order.membervo.m_mobile}</td> --%>
										
										
										
									</tr>
								</c:forEach>	
							</c:if>
							<c:if test="${empty requestScope.productAll_list}">
								<tr>
									<td colspan="12" style="text-align: center; font-weight: bold;">
										상품 정보가 없습니다.</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
				<nav aria-label="Page navigation example">
                    <ul class="pagination" style="justify-content: center;">
                        <li class="page-item"><a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                    </ul>
                </nav>
            </div> <!-- 세컨 -->
        
    </div>
</section>
