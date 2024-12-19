<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
    //    /MyMVC
%>

<%-- Required meta tags --%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<%-- css--%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/member/find.css" > 

<%-- Optional JavaScript --%>
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script> 

<script type="text/javascript">

</script>

<form name="pwdFindFrm">
    <div class="find_check_container">
        <div class="find_logo">
            LOGO
        </div>
        <div class="find_title">
            비밀번호 찾기
        </div>
        <div class="input_box">

            <div class="input_container">
                <input type="password" name="name" placeholder="새로운 비밀번호를 입력해주세요" />
                <input type="password" name="name" placeholder="비밀번호를 한번 더 입력해주세요" />
            </div>

            <div class="find_go">
            	<span class="check"></span><a style="color: white;" class="close" href="<%= ctxPath%>/login/loginView.cl">변경완료</a>
            </div>
        </div>
    </div>
</form>