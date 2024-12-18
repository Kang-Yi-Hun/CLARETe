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

            <div class="info_box">
                <div>
                    비밀번호가 <span>이메일로 발송</span> 되었습니다.
                </div>
                <div>
                    입력해주신 이메일 주소로 인증번호가 발송되었습니다.<br>
                    이메일에서 인증번호를 확인해주세요.
                </div>
            </div>

            <div class="input_container">
                <input type="text" name="name" placeholder="인증번호를 입력해주세요" />
            </div>
            
            <div class="find_btn">
                <a href="#">아이디 찾기</a>
            </div>
            <div class="find_go">
                <span class="check"></span><a style="color: white;" class="close" href="<%= ctxPath%>/member/pwd_find_3.cl">인증번호 입력하기</a>
            </div>
        </div>
    </div>
</form>