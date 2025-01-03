<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
 String ctxPath = request.getContextPath();
	
%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>금쪽이</title>

	<link rel="stylesheet" href="<%= ctxPath %>/css/mypage/mapage.css">
	<link rel="stylesheet" href="<%= ctxPath %>/css/mypage/media_nav.css">   
	 <link rel="stylesheet" href="<%= ctxPath %>/css/mypage/delivery_detail.css">
	
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    
    <!-- Optional JavaScript -->


	<%-- 해더 include 받아옴 --%>
	<%-- <jsp:include page="<%= ctxPath %>/WEB-INF/header.jsp"> --%>
    <jsp:include page="../header.jsp"></jsp:include>
	<%-- 해더 include 받아옴 --%>
	
	<%-- 마이페이지해더 include 받아옴 --%>
    <jsp:include page="mypageheader.jsp"></jsp:include>    
	<%-- 마이페이지해더 include 받아옴 --%>
	
	 <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> 
	
	  <script type="text/javascript" src="<%= ctxPath %>/js/mypage/deleteDeliveryInfo.js"></script> 
	   <script type="text/javascript" src="<%= ctxPath %>/js/mypage/insertDelivery.js"></script> 


	<%-- =============================================== --%>
	
		
        <div class="mypage_contants_bottom">
        
	        <div class="content-flex" style="display:flex; flex-direction: row;">
	
	        	<c:forEach var="dvo" items="${requestScope.deliveryList}" >
	        	<div class="delivery-content-box">
	        		<ul class="delivery-info">
			           		<li><span id="info_title">받는 사람 주소</span>${dvo.d_address}</li>
			           		<li><span id="info_title">받는 사람 상세 주소</span>${dvo.d_detail_address}</li>
			           		<li><span id="info_title">받는 우편 번호</span>${dvo.d_postcode}</li>
			           		<li><span id="info_title">받는 사람 참고 항목</span>${dvo.d_extra}</li>
			           		<li><span id="info_title">받는 사람 전화번호</span>${dvo.d_mobile}</li>
			           		<li><span id="info_title">받는 사람 이름</span>${dvo.d_name}</li>
		           	</ul>
		           	<div class="deletebutton">
		           	<button type="button" class="delivery_info_delete">삭제</button>
		           	</div>
		           	</div>
	        	</c:forEach>
			</div>
			
				
            </div>
            
          
            
	         <div class="delivery_info_insert" style="text-align: center; margin-top:10%;">
	          <button type="button" id="toggleButton" style="display:inline-block; background-color:#ffff; border:#ffff; font-size:30px;">
	          배송지 추가하기
	          </button>
	        </div>
	        
        </div>

              <div class="delivery_delete">
             	<div id="clickOk"></div>
             	<div id="deliveryFrm">
             		<form id="frm" action="deleteDelivery.cl" method="post" style="text-align: center;">
             			<h4>배송지를 삭제하시겠습니까?</h4>
             			<input type="button" class="btn_ok_delete" value="삭제" onclick="goDeleteDelivery()">
             			<input type="button" class="btn_no_delete" value="취소">
             		</form>
             	</div>
             </div>    
					
           
				
				
             <div class="delivery_Insert">
             	<div id="clickOk"></div>
             	<div id="deliveryFrm">
             		<form id="frm" name="frm">
             			<div style="text-align: right;">
	             			<h4>
	             				<button type="button" class="toggleColse" style="border:#ffff; background-color:#ffff; color:black; font-size: 30px;">
	             				x
	             				</button>
	             			</h4>
             			</div>
             			<div class="delivery_info_title" style="text-align: center;">
             				<h3>배송지를 입력해주세요</h3>
             			</div>
             			
             			<ul style="margin-top: 5%;">
	             			<li style="margin-top:10px;">
		             			<label id="infotitle">받는 사람</label>
		             			<input type="text" name="d_name" id="userinfo" class="d_deliveryInfo"></input>
	             			</li>
	             			
	             			<li style="margin-top:10px;">
	             			<label id="infotitle">받는 사람 전화번호</label>
	             			<input type="text" name="d_mobile" id="userinfo" class="d_deliveryInfo" maxlength="12"></input>
	             			</li>
	             			
	             			<li style="margin-top:10px;">
		             			<label id="infotitle">받는 사람 우편번호</label>
		             			<input type="text" name="d_postcode" id="postcode" class="d_deliveryInlfo" style=" width:230px;" value=""></input>
		             			<input type="button" name="postCodeButton" id="postCodeButton"  class="PostCodeFind" value="우편번호검색"
		             			style="display:inline-block; width:100px; height: 48px;"/>
	             			</li>
	             			
	             			<li style="margin-top:10px;">
		             			<label id="infotitle">받는 사람 주소</label>
		             			<input type="text" name="d_address" id="address" class="d_deliveryInfo" value=""></input>
	             			</li>
	             			
	             			<li style="margin-top:10px;">
		             			<label id="infotitle">받는 사람 상세주소</label>
		             			<input type="text" name="d_detail_address" id="detailaddress" class="d_deliveryInfo" value=""></input>
	             			</li>
	             			
	             			<li style="margin-top:10px;">
		             			<label id="infotitle">받는 사람 참고항목</label>
		             			<input type="text" name="d_extra" id="extra" class="d_deliveryInfo"></input>
	             			</li>
	             			
	             			<li style="margin-top: 20px; text-align: center;">
	             				<input type="button" class="btn_success" value="배송지 입력" id="insertBtn" onclick="goInsertDelivery()" />
	             				<input type="reset"  class="btn_reset" value="취소하기" id="insertBtn" />
	             				
	             				<input type="hidden" name="fk_m_id" id="userinfo" class="d_deliveryInfo" value="<%= session.getAttribute("id")%>"></input>
	             			</li>
             			</ul>
             		</form>
             	</div>
             </div> 
             
<%-- =============================================== --%>

<%-- 마이페이지푸터 include 받아옴 --%>
<jsp:include page="mypagefooter.jsp"></jsp:include>
<%-- 마이페이지푸터 include 받아옴 --%> 

<%-- 푸터 include 받아옴 --%>
<jsp:include page="../footer.jsp"></jsp:include>
<%-- 푸터 include 받아옴 --%>
    
    
