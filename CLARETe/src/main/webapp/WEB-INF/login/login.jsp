<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    String ctxPath = request.getContextPath();
    //    /CLARETe
%> 

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/login/login.css" />

<!-- Optional JavaScript -->
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script> 

<%-- jQueryUI CSS 및 JS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
<script type="text/javascript" src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script> 

<%-- 직접 만든 JS --%>
<script type="text/javascript" src="<%= ctxPath%>/js/login/login.js"></script>


<%-- 로그인 폼 --%>
<c:if test="${empty sessionScope.loginuser}">

<div class="login-container">
	<h2>LOGO</h2>
		<h4>로그인</h4>
		<form name="loginFrm" action="<%= ctxPath%>/login/login.cl" method="post">
			<input type="text" name="id" id="id" placeholder="아이디를 입력해주세요" class="input-field">
			<input type="password" name="pwd" id="pwd" placeholder="비밀번호를 입력해주세요" class="input-field">
			<div class="options">
	    		<label><input type="checkbox"> 아이디 기억</label>
	    		<div class="find-links">
	        		<a href="#">아이디 찾기</a> | <a href="#">비밀번호 찾기</a>
	    		</div>
			</div>
			<button type="submit" class="login-btn" >로그인</button>
			<button type="button" class="signup-btn" onclick="location.href='<%= ctxPath%>/member/memberRegister.cl';">회원가입</button>
		</form>
	<div class="divider">
		<span>또는</span>
	</div>
    <button type="button" class="kakao-btn">
		<img src="../images/kakao.png" alt="Kakao Logo" class="kakao-logo">
			Login with KaKao
    </button>
</div>

</c:if>    

