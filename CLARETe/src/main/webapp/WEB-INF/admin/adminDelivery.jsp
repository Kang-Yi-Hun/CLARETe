<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*" %>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/admin_section.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/admin_header.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script>


<style type="text/css">
div#pageBar {
      border: solid 0px red;
      width: 80%;
      margin: 3% auto 0 auto;
      display: flex;
   }
   
   div#pageBar > nav {
      margin: auto;
   }
</style>


<script type="text/javascript">

$(document).ready(function() {
    
    $("select[name='searchType']").val("${requestScope.searchSelect}");
    $("input:text[name='searchWord']").val("${requestScope.searchWord}");
    
    $("select[name='sizePerPage']").val("${requestScope.sizePerPage}");
    
    $("input:text[name='searchWord']").bind("keydown", function(e){
       
       if(e.keyCode == 13) {
          goSearch();
       }
    });
    
    // **** select 태그에 대한 이벤트는 click 이 아니라 change 이다. **** // 
    $("select[name='sizePerPage']").bind("change", function(){
       const frm = document.order_search_frm;
       // frm.action = "memberList.up"; // form 태그에 action 이 명기되지 않았으면 현재보이는 URL 경로로 submit 되어진다.
       // frm.method="get"; // form 태그에 method 를 명기하지 않으면 "get" 방식이다.
          frm.submit();
    });
</script>




<title>주문 배송 관리</title>

<header class="side-header">
    <nav class="header-nav">
        <ul>
			<li>
				<div>회원관리</div>
				<ul>
					<li><a href="<%= request.getContextPath() %>/admin/admin.cl">회원조회</a></li>
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
					<li><a
						href="<%=request.getContextPath()%>/admin/adminOrder.cl">주문회원조회</a></li>
					<li><a
						href="<%=request.getContextPath()%>/admin/adminDelivery.cl">주문배송관리</a></li>
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
				<form name="delivery_search_frm">
					<select name="searchType">
						<!-- required 속성이 있으면, 사용자가 값을 선택하지 않을 경우 브라우저가 기본적으로 유효성 검사를 수행 -->
						<option value="">검색대상</option>
						<option value="m_id">회원아이디</option>
						<option value="d_name">받는사람 이름</option>
					</select> <input type="text" name="searchWord" placeholder="검색어 입력" required />
					<button type="submit" class="btn btn-secondary"
						onclick="goSearch()">검색</button>
				</form>

				<script>
						function goSearch() {
					        const searchType = document.querySelector("select[name='searchType']").value;
					
					        if (searchType === "") {
					            alert("검색대상을 선택하세요 !!");
					            return; // goSearch() 함수를 종료
					        }

							const frm = document.delivery_search_frm;
							frm.action ="adminDelivery.cl";
							frm.submit();
						}
					</script>
			</div>
		</div>
	</div>
	<%-- first --%>



	<!-- 주문배송 테이블 -->
	<div class="second-div">
		<h4
			style="font-weight: bold; text-align: center; margin-top: 50px; padding: 2% 0;">주문배송조회</h4>
		<div class="table-container">
			<table style="width: 100% !important;" class="table">
				<thead class="thead-light">
					<tr>
						<th>번호</th>
						<th>배송지번호</th>
						<th>회원아이디</th>
						<th>받는사람 주소</th>
						<th>받는사람 상세주소</th>
						<th>받는사람 우편번호</th>
						<th>받는사람 참고항목</th>
						<th>받는사람 전화번호</th>
						<th>받는사람 이름 </th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty deliveryList}">
    					<c:forEach var="delivery" items="${deliveryList}" varStatus="status">
							<c:set var="mobile" value="${d_mobile}" />
							<%-- member.m_mobile 만 선언해서 하이픈 넣을 수 있게함 --%>

							<tr class="orderInfo">

								<fmt:parseNumber var="currentShowPageNo"
									value="${requestScope.currentShowPageNo}" />
								<fmt:parseNumber var="sizePerPage"
									value="${requestScope.sizePerPage}" />
								<%-- fmt:parseNumber 은 문자열을 숫자형식으로 형변환 시키는 것이다. --%>

								<td>${(currentShowPageNo - 1) * sizePerPage + (status.index + 1)}</td>
								<%-- >>> 페이징 처리시 보여주는 순번 공식 <<<
	                           			 데이터개수 - (페이지번호 - 1) * 1페이지당보여줄개수 - 인덱스번호 => 순번 --%>
								<td>${delivery.d_num}</td>
								<td>${delivery.fk_m_id}</td>
								<td>${delivery.d_address}</td>
								<td>${delivery.d_detail_address}</td>
								<td>${delivery.d_postcode}</td>
								<td>${delivery.d_extra}</td>
								<td>
								    ${fn:substring(delivery.d_mobile, 0, 3)}-
								    ${fn:substring(delivery.d_mobile, 3, 7)}-
								    ${fn:substring(delivery.d_mobile, 7, 11)}
								</td>
								<td>${delivery.d_name}</td>
							</tr>
						</c:forEach>
					</c:if>

					<c:if test="${empty deliveryList}">
						<tr>
							<td colspan="10" style="text-align: center; font-weight: bold;">
								주문 배송 정보가 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>

			<%-- 페이징 네비게이션 --%>
			<div id="pageBar">
				<nav>
					<ul class="pagination">${requestScope.pageBar}</ul>
				</nav>
			</div>
		</div>
	</div>   <!-- 세컨 -->
</section>


