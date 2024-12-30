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

<title>주문 회원 조회</title>

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
                
                   <%--  <!-- 회원 유형 필터 -->
                    <div style="display: flex; gap: 8px; flex-wrap: wrap;">
                        <div class="input-box">
                            <div>
                                <label for="memberType">회원 유형</label>
                            </div>
                            <div>
                                <select id="memberType" name="memberType">
                                    <option value="all" <%="all".equals(request.getParameter("memberType")) ? "selected" : ""%>>전체회원</option>
                                    <option value="order" <%="order".equals(request.getParameter("memberType")) ? "selected" : ""%>>주문회원</option>
                                </select>
                            </div>
                        </div>
                    </div>

                     <script>
                        $(document).ready(function() {
                            $('#memberType').change(function() {
                                const memberType = $(this).val();
                                if (memberType === 'all') {
                                    alert('전체 회원 조회');
                                } else if (memberType === 'order') {
                                    alert('주문 회원 조회');
                                }
                                $(this).closest('form').submit(); // 폼 제출
                            });
                        });
                    </script> --%>

                    <!-- 유저 아이디와 유저명 -->
                    <div style="display: flex; gap: 8px; flex-wrap: wrap;">
                        <div class="input-box">
                            <div>
                                <label for="userid">유저 아이디</label>
                            </div>
                            <div>
                                <input type="text" id="userid" name="userid" placeholder="유저 아이디를 입력하세요">
                            </div>
                        </div>
                        <div class="input-box">
                            <div>
                                <label for="username">유저명</label>
                            </div>
                            <div>
                                <input type="text" id="username" name="username" placeholder="유저명을 입력하세요">
                            </div>
                        </div>
                    </div>

                    <!-- 삭제 여부와 휴면 여부 -->
                    <div style="display: flex; gap: 8px; flex-wrap: wrap;">
                        <div class="input-box">
                            <div>
                                <label for="deleted">삭제여부</label>
                            </div>
                            <div>
                                <input type="text" id="deleted" name="deleted" placeholder="전체">
                            </div>
                        </div>
                        <div class="input-box">
                            <div>
                                <label for="dormant">휴면여부</label>
                            </div>
                            <div>
                                <input type="text" id="dormant" name="dormant" placeholder="전체">
                            </div>
                        </div>
                    </div>

                    <!-- 버튼 -->
                    <div class="form-btn-box">
                        <input type="submit" class="btn search" value="검색" />
                        <input type="reset" class="btn reset" value="초기화" />
                    </div>
                </form>
            </div>

             <!-- 주문 회원 테이블 -->
            <div class="second-div">
                <h4 style="font-weight: bold; text-align: center; margin-top: 50px; padding: 2% 0;">주문회원조회</h4>
				<div class="table-container">
					<table style="width: 100% !important;" class="table">
						<thead class="thead-light">
							<tr>
								<th>주문번호</th>
								<th>주문날짜</th>
								<th>회원아이디</th>
								<th>상품명</th>
								<th>제품수량</th>
								<th>주문금액</th>
								<th>배송현황</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty requestScope.orderList}">
								<c:forEach var="order" items="${requestScope.orderList}">
									<tr>
										<td>${order.o_num}</td>
										<td>${order.o_date}</td>
										<td>${order.fk_m_id}</td>
										<td>${order.productvo.p_name}</td>
										<td>${order.orderdetailvo.od_count}</td>
	    								<td>${order.o_price}</td>
										<td>
											<c:choose>
												<c:when test="${order.status == 0}">결제 대기</c:when>
												<c:when test="${order.status == 1}">결제 완료</c:when>
												<c:when test="${order.status == 2}">배송 중</c:when>
												<c:otherwise>배송 완료</c:otherwise>
											</c:choose>
										</td>
										
<%-- 									<td>${order.membervo.m_name}</td>
										<td>${order.membervo.m_email}</td>
										<td>${order.membervo.m_mobile}</td> --%>
										
										
										
									</tr>
								</c:forEach>	
							</c:if>
							<c:if test="${empty requestScope.orderList}">
								<tr>
									<td colspan="7" style="text-align: center; font-weight: bold;">
										주문 회원 정보가 없습니다.</td>
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
            </div>
        </div>
    </div>
</section>
