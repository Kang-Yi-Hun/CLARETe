<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
    //    /CLARETe
%> 
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>금쪽이</title>

	<link rel="stylesheet" href="<%= ctxPath %>/css/mypage/mapage.css">
	<link rel="stylesheet" href="<%= ctxPath %>/css/mypage/media_nav.css">    
	<link rel="stylesheet" href="<%= ctxPath %>/css/mypage/mypage_memberUpdate.css">    
	
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    
    <!-- Optional JavaScript -->
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script> 

<%-- jQueryUI CSS 및 JS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
<script type="text/javascript" src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script> 

<%-- 직접 만든 JS --%>
<script type="text/javascript" src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/js/mypage/memberUpdate.js"></script>

</head>
<body> 
	<%-- 해더 include 받아옴 --%>
    <jsp:include page="../header.jsp"></jsp:include>
	<%-- 해더 include 받아옴 --%>
	
	<%-- 마이페이지해더 include 받아옴 --%>
    <jsp:include page="mypageheader.jsp"></jsp:include>    
	<%-- 마이페이지해더 include 받아옴 --%>
	

		<%-- =============================================== --%>
        <div class="mypage_memberUpdate">
            <form name="registerFrm">
         <h4>내 정보 수정</h4>
         <span id="mandatoryNote" > 변경하실 정보만 기입해주세요</span>
         <hr class="divider">

            <div id="divName" class="formDiv">
               <span>이름</span>
               <input type="hidden" name="m_id" value="${sessionScope.loginuser.m_id}" />
               <input type="text" name="m_name" id="m_name" class="register-input requiredInfo" placeholder="이름을 입력해주세요" value="${sessionScope.loginuser.m_name}" />
            </div>
            
            <div id="divMobile" class="formDiv">
               <span>휴대폰</span>
               <input type="text" name="m_mobile" id="m_mobile" class="register-input requiredInfo" placeholder="숫자만 입력해주세요" value="${sessionScope.loginuser.m_mobile}"/>
               <span class="error">숫자만 입력해주세요.</span>
            </div>
            
            <div id="divPostcode" class="formDiv">
               <span>우편번호</span>
               <input type="text" name="m_postcode" id="m_postcode" class="address-input requiredInfo" placeholder="우편번호" value="${sessionScope.loginuser.m_postcode}"/>
               <%-- 우편번호 찾기 --%>
               <input type="button" id="zipcodeSearch" value="우편번호 찾기" onclick="searchPostcode()"/>
               <span class="error">우편번호 형식에 맞지 않습니다.</span>
            </div>
            
            <div id="divAddress" class="formDiv">
               <span>주소</span>
               <input type="text" name="m_address" id="m_address" class="register-input requiredInfo" size="40" maxlength="200" placeholder="주소" value="${sessionScope.loginuser.m_address}"/><br>      
            </div>
            
            <div id="divAddress" class="formDiv">
            	<span>상세주소</span>
               <input type="text" name="m_detail_address" id="m_detail_address" class="address-input requiredInfo" size="40" maxlength="200" placeholder="상세주소" value="${sessionScope.loginuser.m_detail_address}" />&nbsp;
               <input type="text" name="m_extra" id="m_extra" class="address-input requiredInfo" size="40" maxlength="200" placeholder="참고항목" value="${sessionScope.loginuser.m_extra}" />  
               <span class="error">주소를 입력하세요.</span>
            </div>
            <div id="submit">
           <input type="button" value="변경하기" onclick="goUpdate()" />
        </div>
         
         
      </form>
        </div>
		<%-- =============================================== --%>
		
		

<%-- 마이페이지푸터 include 받아옴 --%>
<jsp:include page="mypagefooter.jsp"></jsp:include>
<%-- 마이페이지푸터 include 받아옴 --%> 

<%-- 푸터 include 받아옴 --%>
<jsp:include page="../footer.jsp"></jsp:include>
<%-- 푸터 include 받아옴 --%>
    
    
</body>
</html>
