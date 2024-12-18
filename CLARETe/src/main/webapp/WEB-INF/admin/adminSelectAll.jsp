<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    String ctxPath = request.getContextPath();
    //    /MyMVC
%>    

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>

<link rel="stylesheet" href="<%= ctxPath%>/css/admin/admin_section.css">
<link rel="stylesheet" href="<%= ctxPath%>/css/admin/admin_header.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
<!-- Optional JavaScript -->
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script> 

<%-- jQueryUI CSS 및 JS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
<script type="text/javascript" src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script> 


    <title>관리자 회원관리</title>
        <!-- 부트스트랩 CSS 파일 경로 -->

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
                        <li>상품조회</li>
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
				<form action="<%=ctxPath%>/memberSearch" method="GET">
				
				
					<%-- <!-- 회원 유형 필터 -->
					<div style="display: flex; gap: 8px; flex-wrap: wrap;">
						<div class="input-box">
							<div>
								<label for="memberType">회원 유형</label>
							</div>
							<div>
								<select id="memberType" name="memberType">
									<option value="all"
										<%="all".equals(request.getParameter("memberType")) ? "selected" : ""%>>전체회원</option>
									<option value="order"
										<%="order".equals(request.getParameter("memberType")) ? "selected" : ""%>>주문회원</option>
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
								<input type="text" id="userid" name="userid"
									placeholder="유저 아이디를 입력하세요">
							</div>
						</div>
						<div class="input-box">
							<div>
								<label for="username">유저명</label>
							</div>
							<div>
								<input type="text" id="username" name="username"
									placeholder="유저명을 입력하세요">
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
						<input type="submit" class="btn search" value="검색" /> <input
							type="reset" class="btn reset" value="초기화" />
					</div>
				</form>
			</div>



			<div class="second-div">
				<h4
					style="font-weight: bold; text-align: center; margin-top: 50px; padding: 2% 0;">모든회원조회</h4>

				<div class="table-container">
					<table style="width: 100% !important;" class="table">
						<thead class="thead-light">

							<tr>
							</tr>
							<tr>
								<th>회원이름</th>
								<th>이메일</th>
								<th>전화번호</th>
								<th>우편번호</th>
								<th>주소</th>
								<th>상세주소</th>
								<th>참고항목</th>
								<th>생년월일</th>
								<th>성별</th>
								<th>포인트</th>
								<th>가입일자</th>
								<th>마지막비밀번호변경</th>
								<th>회원탈퇴유무</th>
								<th>휴면유무</th>
							</tr>
						</thead>
						<tbody>
							<tbody id="selectAll">
           <c:if test="${not empty requestScope.selectAll_list}">
            <c:forEach var="member" items="${requestScope.selectAll_list}" varStatus="m_status">
               <tr>
                  <th>${member.m_name}</th>
                  <td>${member.m_email}</td>
                  <td>${member.m_mobile}</td>
                  <td>${member.m_postcode}</td>
                  <td>${member.m_address}</td>
                  <td>${member.m_detail_address}</td>
                  <td>${member.m_extra}</td>
                  <td>${member.m_gender}</td>
                  <td>${member.m_birth}</td>
                  <td>${member.m_point}</td>
                  <td>${member.m_register}</td>
                  <td>${member.m_lastpwd}</td>
                  <td>${member.m_status}</td>
                  <td>${member.m_idle}</td>
               </tr>
            </c:forEach>
         </c:if>
           </tbody>

					</table>
				</div>

				<nav aria-label="Page navigation example">
					<ul class="pagination" style="justify-content: center;">
						<li class="page-item"><a class="page-link" href="#"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
						<li class="page-item"><a class="page-link" href="#">1</a></li>
						<li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li>
						<li class="page-item"><a class="page-link" href="#"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</ul>
				</nav>

			</div>



		</div></section>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
