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
<script type="text/javascript" src="<%= ctxPath%>/js/member/memberRegister.js"></script>

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
               <input type="text" name="name" id="name" class="register-input requiredInfo" placeholder="이름을 입력해주세요" />
               <span class="error">이름은 필수입력 사항입니다.</span>
            </div>
            
            <div id="divPwd" class="formDiv">
               <span>비밀번호</span>
               <input type="password" name="pwd" id="pwd" class="register-input requiredInfo" placeholder="비밀번호를 입력해주세요" />
               <span class="error">영문/숫자/특수문자(공백제외)만 허용하며 2가지를 조합하세요.</span>
            </div>
            
            
            <div id="divPwdCheck" class="formDiv">
               <span>비밀번호확인</span>
               <input type="password" id="pwdCheck" class="register-input requiredInfo" placeholder="비밀번호를 한번 더 입력해주세요" />
               <span class="error">비밀번호가 일치하지 않습니다.</span>
            </div>
         
            
            
            <div id="divEmail" class="formDiv">
               <span>이메일</span>
               <input type="text" name="email" id="email" class="register-input requiredInfo" placeholder="예: hongkd@naver.com" />
               <input type="button" id="emailCheck" value="중복확인" onclick="duplicateEmail()" />
               <span id="emailCheckResult"></span>
               <span class="error">이메일 형식에 맞지 않습니다.</span>
            </div>
            
            <div id="divMobile" class="formDiv">
               <span>휴대폰</span>
               <input type="text" name="mobile" id="mobile" class="register-input requiredInfo" placeholder="숫자만 입력해주세요" />
               <span class="error">숫자만 입력해주세요.</span>
            </div>
            
            <div id="divPostcode" class="formDiv">
               <span>우편번호</span>
               <input type="text" name="postcode" id="postcode" class="address-input requiredInfo" placeholder="우편번호" />
               <%-- 우편번호 찾기 --%>
               <input type="button" id="zipcodeSearch" value="우편번호 찾기" onclick="searchPostcode()"/>
               <span class="error">우편번호 형식에 맞지 않습니다.</span>
            </div>
            
            <div id="divAddress" class="formDiv">
               <span>주소</span>
               <input type="text" name="address" id="address" class="register-input requiredInfo" size="40" maxlength="200" placeholder="주소" /><br>      
            </div>
            
            <div id="divAddress" class="formDiv">
            	<span>상세주소</span>
               <input type="text" name="detailaddress" id="detailAddress" class="address-input requiredInfo" size="40" maxlength="200" placeholder="상세주소" />&nbsp;
               <input type="text" name="extraaddress" id="extraAddress" class="address-input requiredInfo" size="40" maxlength="200" placeholder="참고항목" />  
               <span class="error">주소를 입력하세요.</span>
            </div>
            <div id="submit">
           <input type="button" value="가입하기" onclick="goRegister()" />
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
