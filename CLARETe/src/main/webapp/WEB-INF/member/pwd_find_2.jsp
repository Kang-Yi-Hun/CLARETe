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
$(document).ready(function(){
   
   
   
   const method = "${requestScope.method}";
   
//   console.log("~~~확인용 method : "+method);
   
   
   $(document).on('click', 'div.find_go', function(){
      goFind();
   });

   
    
}); // end of $(document).ready(function(){}) 

// Function Declaration
//'input:text[name="m_email"]'
function goFind() {
   
   const check = $('#m_email').val();
//   alert(check);
   
   $('input:text[name="m_id"]').val("${requestScope.m_id}");
//   console.log($('#m_email').val());
//   console.log(check);
   
   
   if("${requestScope.certification_code}" == check) {
       alert("비밀번호 변경으로 이동합니다.");
       const frm = document.pwdFindFrm;
        frm.action = "<%= ctxPath%>/member/pwd_find_3.cl";
        frm.method = "get";
        frm.submit();
   }
   else {
      alert("인증번호가 일치하지 않습니다.");
   }
   
   
    
}// end of function goFind(){}-----------------------
   
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
                <input type="text" id = "m_email" name="m_email" placeholder="인증번호를 입력해주세요" />
            </div>
            
            <input type="hidden" name="m_id" value="${requestScope.m_id}" />
            
            <div class="find_go">
                <a style="color: white;" class="close">인증번호 입력하기</a>
            </div>
        </div>
    </div>
</form>